import axios from "axios";
import PageManage from "components/PageManage";
import { useEffect, useState } from "react";
import Swal from "sweetalert2";
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
import { Link, useNavigate } from "react-router-dom";

function Board(props) {
  const navigate = useNavigate();

  // 게시글 추가 버튼 클릭 관련 로직
  const [addModalShow, setAddModalShow] = useState(false);
  const AddModalHandleClose = () => setAddModalShow(false);
  const AddModalHandleShow = () => setAddModalShow(true);

  // 카테고리 추가 Form
  const [boardForm, setBoardForm] = useState({
    title: "",
    content: "",
    userId: props.isLogin ? props.userInfo.id : "",
    category: props.cateInfo.no,
    files: "",
  });

  // 글 작성 버튼 클릭
  const AddModalHandle = () => {
    if (boardForm.userId == "") {
      Swal.fire({
        title: "로그인이 필요한 기능입니다.",
        text: "로그인 페이지로 이동하겠습니다.",
        icon: "info",
      });
      // 모달창 닫고 boardForm 값 초기화
      resetBoardForm();

      // 로그인 페이지로 이동
      navigate("/admin/login");
      return;
    } else if (boardForm.category == "") {
      Swal.fire({
        title: "잘못된 접근입니다.",
        icon: "error",
      });
      // 모달창 닫고 boardForm 값 초기화
      resetBoardForm();
      return;
    } else {
      AddModalHandleShow();
    }
  };

  // 게시글 추가의 창닫기 버튼 클릭 시 boardForm 값 초기화
  const resetBoardForm = () => {
    setBoardForm({
      title: "",
      content: "",
      userId: props.isLogin ? props.userInfo.id : "",
      category: props.cateInfo.no,
      files: "",
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

  // 글 작성 추가 모달의 파일 업로드 클릭 시 이벤트
  const fileUpload = (e) => {

    console.log("123123123")
    console.log(e.target.files);

    setBoardForm({
      ...boardForm,
      ["files"]: e.target.files,
    });
  };

  // 글 작성 추가 모달의 save버튼 클릭
  const addBoard = () => {
    // 글 작성 추가 모달창 닫기
    AddModalHandleClose();

    if (boardForm.title == "") {
      Swal.fire({
        title: "제목을 입력해주세요.",
        icon: "info",
      });
      // 모달창 닫고 boardForm 값 초기화
      resetBoardForm();
      return;
    } else if (boardForm.content == "") {
      Swal.fire({
        title: "내용을 입력해주세요.",
        icon: "info",
      });
      // 모달창 닫고 boardForm 값 초기화
      resetBoardForm();
      return;
    }

    const formData = new FormData();
    formData.append(
      "boardForm",
      new Blob([JSON.stringify(boardForm)], { type: "application/json" })
    );

    for(let i=0; i<boardForm.files.length; i++){
      formData.append("fileList", boardForm.files[i]);
    }
    
    
    axios({
      method: "post",
      headers: { "Content-Type": "multipart/form-data" },
      data: formData,
      url: "/board",
    })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
        Swal.fire({
          title: "게시글 작성 실패",
          text: "잠시 후 다시 시도해주세요",
          icon: "error",
        });
      })
      .finally(() => {
        // 모달창 닫고 boardForm 값 초기화
        resetBoardForm();

        // 페이지 총 개수 확인
        getTotalPage();

        // 페이지 전체 조회
        getBoard();
      });
     
  };

  const [cateList, setCateList] = useState([]);
  const [page, setPage] = useState(1);
  const [boardCount, setBoardCount] = useState(0);

  useEffect(() => {
    // 페이지 총 개수 확인
    getTotalPage();

    // 페이지 전체 조회
    getBoard();

    // 게시글 작성 Form 초기화
    resetBoardForm();
  }, [page, props.cateInfo.no, props.isLogin]);

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
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  파일 :
                </Col>
                <Col sm={9}>
                  <Input
                    type="file"
                    placeholder="Upload Image"
                    multiple
                    onChange={fileUpload}
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
            <Card style={{ height: "calc(100vh - 140px)" }}>
              <CardHeader className="mx-3">
                <CardTitle tag="h4">{props.cateInfo.name}</CardTitle>
              </CardHeader>
              <Button
                className="mx-4"
                variant="secondary"
                onClick={AddModalHandle}
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
                        {props.isLogin ? (
                          <>
                            <th></th>
                          </>
                        ) : null}
                      </tr>
                    </thead>
                    <tbody>
                      {cateList.map((cate) => {
                        return (
                          <tr key={cate.no}>
                            <td className="col-1">{cate.no}</td>
                            <td className="col-2">{cate.title}</td>
                            <td className="col-3">{cate.content}</td>
                            <td className="col-1">{cate.count}</td>
                            <td className="col-2">{cate.regDate}</td>
                            <td className="col-1">{cate.nickname}</td>
                            {(props.isLogin && cate.nickname === props.userInfo.nickname) ? (
                              <>
                                <td className="col-1">
                                  <i
                                    className="tim-icons icon-settings"
                                    id={cate.no}
                                  ></i>
                                </td>
                                <td className="col-1">
                                  <i
                                    className="tim-icons icon-trash-simple"
                                    id={cate.no}
                                  ></i>
                                </td>
                              </>
                            ) : null}
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
