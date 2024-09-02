import axios from "axios";
import PageManage from "components/PageManage";
import { useEffect, useState } from "react";
import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  CardTitle,
  Col,
  Container,
  Input,
  Modal,
  ModalBody,
  ModalFooter,
  Row,
  Table,
} from "reactstrap";

function Board(props) {
  // 게시글 추가 버튼 클릭 관련 로직
  const [addModalShow, setAddModalShow] = useState(false);
  const AddModalHandleClose = () => setAddModalShow(false);
  const AddModalHandleShow = () => setAddModalShow(true);

  // 카테고리 추가 Form
  const [boardForm, setBoardForm] = useState({
    title: "",
    content: "",
    userId: props.userInfo,
    category: "",
  });

  // 게시글 추가의 창닫기 버튼 클릭 시 boardForm 값 초기화
  const resetBoardForm = () => {
    setBoardForm({
      title: "",
      content: "",
      userId: "",
      category: "",
    });
    AddModalHandleClose();
  };

  // 글 작성 추가 모달의 값 변경 관리
  const onChangeBoardForm = (e) => {
    setBoardForm({
      ...boardForm,
      [e.target.name]: e.target.value,
    });
  };

  // 글 작성 추가 모달의 save버튼 클릭
  const addBoard = () => {
    // 글 작성 추가 모달창 닫기
    AddModalHandleClose();

    console.log(boardForm);


  };

  const [cateList, setCateList] = useState([]);
  const [page, setPage] = useState(1);
  const [boardCount, setBoardCount] = useState(0);

  useEffect(() => {
    // 페이지 총 개수 확인
    getTotalPage();

    // 페이지 전체 조회
    getBoard();
  }, [page, props.cateInfo.no]);

  const pageHandler = (res) => {
    setPage(res);
  };

  const getTotalPage = () => {
    axios({
      method: "GET",
      url: "board/getTotalPage?categoryNo=" + props.cateInfo.no,
    })
      .then((res) => {
        console.log("board/getTotalPage");
        console.log(res.data);
        setBoardCount(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const getBoard = () => {
    axios({
      method: "GET",
      url: "board/searchAllByCate?no=" + props.cateInfo.no + "&page=" + page,
    })
      .then((res) => {
        console.log("board/searchAllByCate");
        console.log(res.data);

        const list = [];

        res.data.map((response) => {
          let tmp = response.regDate;
          tmp = tmp.split("T");

          return list.push({
            no: response.no,
            title: response.title,
            content: response.content,
            count: response.count,
            regDate: tmp[0],
            nickname: response.username,
          });
        });

        setCateList(list);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const clickButton = () => {};

  return (
    <>
      <div className="content">
        <Row>
          <Modal isOpen={addModalShow}>
            <ModalBody className="mt-1">
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  제목 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    id="boardTitleInput"
                    placeholder="제목을 입력해주세요"
                    defaultValue={boardForm.title}
                    style={{ color: "black" }}
                    name="title"
                    onChange={onChangeBoardForm}
                  />
                </Col>
              </Row>
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  내용 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    id="boardContentInput"
                    placeholder="내용을 입력해주세요"
                    defaultValue={boardForm.content}
                    style={{ color: "black" }}
                    name="content"
                    type="textarea"
                    onChange={onChangeBoardForm}
                  />
                </Col>
              </Row>
            </ModalBody>
            <ModalFooter className="justify-content-end m-3">
              <Button
                className="m-1"
                size="sm"
                variant="primary"
                onClick={addBoard}
              >
                Save
              </Button>
              <Button size="sm" variant="primary" onClick={resetBoardForm}>
                Close
              </Button>
            </ModalFooter>
          </Modal>
          <Col md="12">
            <Card style={{ height: "calc(100vh - 210px)" }}>
              <CardHeader className="mx-3">
                <CardTitle tag="h4">{props.cateInfo.name}</CardTitle>
              </CardHeader>
              <Button
                className="mx-4"
                variant="secondary"
                onClick={AddModalHandleShow}
              >
                글 작성
              </Button>
              <CardBody className="mt-4">
                {cateList.length === 0 ? (
                  <h3 className="text-center">no data ...</h3>
                ) : (
                  <Table className="tablesorter text-center">
                    <thead className="text-primary">
                      <tr>
                        <th>No.</th>
                        <th>제목</th>
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
                            <td className="col-1">{cate.no}</td>
                            <td className="col-3">{cate.title}</td>
                            <td className="col-4">{cate.content}</td>
                            <td className="col-1">{cate.count}</td>
                            <td className="col-2">{cate.regDate}</td>
                            <td className="col-1">{cate.nickname}</td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                )}
              </CardBody>
              <CardFooter>
                <Container>
                  <PageManage
                    count={cateList.length}
                    page={page}
                    pageHandler={pageHandler}
                    boardCount={boardCount}
                  />
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
