import axios from "axios";
import { useEffect, useState } from "react";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  CardTitle,
  Col,
  Input,
  Modal,
  ModalBody,
  ModalFooter,
  ModalHeader,
  Row,
  Table,
} from "reactstrap";

function Category() {
  const [cateList, setCateList] = useState([]);

  // 카테고리 추가 버튼 클릭 관련 로직
  const [addModalShow, setAddModalShow] = useState(false);

  const AddModalHandleClose = () => setAddModalShow(false);
  const AddModalHandleShow = () => setAddModalShow(true);

  // 카테고리 추가 Form
  const [cateName, setCateName] = useState();

  useEffect(() => {
    axios({
      method: "GET",
      url: "http://localhost:8080/cate/searchAll",
    })
      .then((res) => {
        console.log(res.data);

        let list = [];

        res.data.map((response) => {
          return list.push({
            no: response.no,
            name: response.categoryName,
            icon: response.icon,
            path: response.path,
          });
        });

        setCateList(list);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <>
      <div className="content">
        <Row>
          <Modal isOpen={addModalShow}>
            <ModalHeader className="justify-content-end">
              <i
                className="tim-icons icon-simple-remove"
                onClick={AddModalHandleClose}
              />
            </ModalHeader>
            <ModalBody>
              <Row className="m-1">
                <Col sm={3} className="mt-1">
                  카테고리명 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    placeholder="카테고리 이름"
                    type="text"
                    value={cateName}
                    onChange={(e) => {
                      setCateName(e.target.value);
                    }}
                  />
                </Col>
              </Row>
            </ModalBody>
            <ModalFooter className="justify-content-end m-3">
              <Button size="sm" variant="primary" onClick={AddModalHandleClose}>
                Save Changes
              </Button>
            </ModalFooter>
          </Modal>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">Categirory Manage</CardTitle>
              </CardHeader>
              <Button variant="secondary" onClick={AddModalHandleShow}>
                Category Add
              </Button>

              <CardBody>
                {cateList.length === 0 ? (
                  <h3 className="text-center">no data ...</h3>
                ) : (
                  <Table className="tablesorter text-center">
                    <thead className="text-primary">
                      <tr>
                        <th>No.</th>
                        <th>Category Name</th>
                      </tr>
                    </thead>
                    <tbody>
                      {cateList.map((cate) => {
                        return (
                          <tr key={cate.no}>
                            <td>{cate.no}</td>
                            <td>{cate.name}</td>
                            <td>
                              <i className="tim-icons icon-settings"></i>
                            </td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                )}
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Category;
