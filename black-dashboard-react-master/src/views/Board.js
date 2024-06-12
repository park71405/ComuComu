import axios from "axios";
import { useState } from "react";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  CardTitle,
  Col,
  Row,
  Table,
} from "reactstrap";

function Board() {
  const [cateList, setCateList] = useState([]);

  const clickButton = () => {
    axios({
      method: "GET",
      url: "cate/searchAll",
    })
      .then((res) => {
        console.log(res.data);

        const list = [];

        res.data.map((response) => {
          return list.push({ no: response.no, name: response.categoryName });
        });

        setCateList(list);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">Free Board</CardTitle>
              </CardHeader>
              <Button variant="secondary" onClick={() => clickButton()}>
                Category Add Click Me!
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

export default Board;
