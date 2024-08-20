import axios from "axios";
import PageManage from "components/PageManage";
import { useState } from "react";
import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  CardTitle,
  Col,
  Container,
  Row,
  Table,
} from "reactstrap";

function Board(props) {
  const [cateList, setCateList] = useState([]);
  const [page, setPage] = useState(1);

  const clickButton = () => {
    axios({
      method: "GET",
      url: "board/searchAllByCate?no=" + props.cateInfo.no + "&page=" + page,
    })
      .then((res) => {
        console.log(res.data);

        const list = [];

        res.data.map((response) => {
          return list.push({ no: response.no, title: response.title, content:response.content, count: response.count, regDate: response.regDate, nickname: response.username });
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
            <Card style={{ height: "calc(100vh - 210px)" }}>
              <CardHeader className="mx-3">
                <CardTitle tag="h4">{props.cateInfo.name}</CardTitle>
              </CardHeader>
              <Button className="mx-4" variant="secondary" onClick={() => clickButton()}>
                조회
              </Button>
              <CardBody>
                {cateList.length === 0 ? (
                  <h3 className="text-center">no data ...</h3>
                ) : (
                  <Table className="tablesorter text-center">
                    <thead className="text-primary">
                      <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>조회수</th>
                        <th>등록일</th>
                        <th>작성자</th>
                      </tr>
                    </thead>
                    <tbody>
                      {cateList.map((cate) => {
                        return (
                          <tr key={cate.no}>
                            <td>{cate.no}</td>
                            <td>{cate.title}</td>
                            <th>{cate.content}</th>
                            <th>{cate.count}</th>
                            <th>{cate.regDate}</th>
                            <th>{cate.nickname}</th>
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                )}
              </CardBody>
              <CardFooter>
                <Container>
                  <PageManage count={cateList.length} page={page} />
                </Container>
              </CardFooter>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Board;
