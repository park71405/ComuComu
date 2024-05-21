import axios from "axios";
import Swal from "sweetalert2";
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

  // 카테고리 창닫기 버튼 클릭 시 cateForm 값 초기화
  const resetCateForm = () => {
    setCateForm({
      categoryName: "",
      path: "",
      icon: "",
      component: "",
    });
    AddModalHandleClose();
  };

  // 카테고리 추가 Form
  const [cateForm, setCateForm] = useState({
    categoryName: "",
    path: "",
    icon: "",
    component: "",
  });

  const onChangeCateForm = (e) => {
    setCateForm({
      ...cateForm,
      [e.target.name]: e.target.value,
    });
  };

  // 카테고리 추가 비동기 작업
  const addCategory = () => {
    // 카테고리 추가 모달창 닫기
    AddModalHandleClose();

    console.log(cateForm);

    if (cateForm.categoryName == "") {
      alert("카테고리 이름을 입력해주세요");
    } else if (cateForm.component == "") {
      alert("레이아웃을 입력해주세요");
    } else if (cateForm.icon == "") {
      alert("아이콘을 입력해주세요");
    } else if (cateForm.path == "") {
      alert("경로를 입력해주세요");
    } else {
      axios({
        method: "POST",
        data: cateForm,
        url: "http://localhost:8080/cate/addCategory",
      })
        .then((res) => {
          console.log(res);
          alert("카테고리가 추가되었습니다.");
        })
        .catch((res) => {
          alert("카테고리 추가를 실패하였습니다.\n 잠시 후 다시 시도해주세요");
        })
        .finally(() => {
          // 모달창 닫고 cateForm 값 초기화
          resetCateForm();
          searchCategoryAll();
        });
    }
  };

  // 카테고리 삭제 비동기 함수
  const deleteCategory = () => {
    Swal.fire({
      title: "카테고리를 삭제하시겠습니까?",
      text: "해당 카테고리에 존재하는 게시글 또한 삭제됩니다.",
      icon: "warning",
      confirmButtonText: "Yes",
      denyButtonText: "No",
      showCancelButton: true,
    }).then((res) => {
      if (res.isConfirmed) {
        //삭제 요청 처리
        console.log("yews");
      } else {
      }
    });
  };

  // 카테고리 조회 비동기 함수
  const searchCategoryAll = () => {
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
  };

  useEffect(() => {
    searchCategoryAll();
  }, []);

  return (
    <>
      <div className="content">
        <Row>
          <Modal isOpen={addModalShow}>
            <ModalBody className="mt-1">
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  카테고리명 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    placeholder="ex) 카테고리"
                    style={{ color: "black" }}
                    name="categoryName"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  path :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    placeholder="ex) /category"
                    style={{ color: "black" }}
                    name="path"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  아이콘 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    placeholder="ex) 나중에 모달 하나 더 띄우고 선택하도록..."
                    style={{ color: "black" }}
                    name="icon"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  레이아웃 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    placeholder="ex) 나중에 모달 하나 더 띄우고 선택하도록..."
                    style={{ color: "black" }}
                    name="component"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
            </ModalBody>
            <ModalFooter className="justify-content-end m-3">
              <Button
                className="m-1"
                size="sm"
                variant="primary"
                onClick={addCategory}
              >
                Save
              </Button>
              <Button size="sm" variant="primary" onClick={resetCateForm}>
                Close
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
                            <td>
                              <i
                                className="tim-icons icon-trash-simple"
                                onClick={deleteCategory}
                              ></i>
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
