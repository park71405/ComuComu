import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  CardTitle,
  Col,
  Input,
  Row,
  Table,
} from "reactstrap";
import Swal from "sweetalert2";

function Join() {

  const navigate = useNavigate();

  // 로그인 버튼 클릭 이벤트
  const clickJoin = () => {

    let idData = document.getElementById("id").value;
    let pw = document.getElementById("pw").value;
    let nicknameData = document.getElementById("nickname").value;
    let emailData = document.getElementById("email").value;

    let reqData = {id: idData, password: pw, nickname: nicknameData, email: emailData};

    axios({
      method: "POST",
      data: reqData,
      url: "user",
    }).then((res) => {

      Swal.fire({
        title: '회원가입 성공',
        text: '로그인 페이지로 이동하겠습니다.',
        icon: "success"
      });

      // 로그인 페이지로 이동
      navigate("/admin/login");

    }).catch((err)=>{
      console.log(err);
      Swal.fire({
        title: "회원가입 실패",
        text: "다시 시도해주세요",
        icon: "error",
      });
    })

  }

  return (
    <>
      <div className="content">
        <Row>
          <Col md="12">
            <Card className="p-5" style={{ height: "calc(100vh - 140px)" }}>
              
              <CardBody className="p-5 mt-5">
                <Row className="m-3 text-center">
                  <Col md="3 mt-1">
                    <h4>ID : </h4>
                  </Col>
                  <Col>
                    <Input id="id" placeholder="아이디를 입력해주세요" />
                  </Col>
                </Row>
                <Row className="m-3 text-center">
                  <Col md="3 mt-1">
                    <h4>PW : </h4>
                  </Col>
                  <Col>
                    <Input type="password" id="pw" placeholder="비밀번호를 입력해주세요"  />
                  </Col>
                </Row>
                <Row className="m-3 text-center">
                  <Col md="3 mt-1">
                    <h4>Name : </h4>
                  </Col>
                  <Col>
                    <Input id="nickname" placeholder="Nickname를 입력해주세요" />
                  </Col>
                </Row>
                <Row className="m-3 text-center">
                  <Col md="3 mt-1">
                    <h4>Email : </h4>
                  </Col>
                  <Col>
                    <Input id="email" placeholder="Email를 입력해주세요" />
                  </Col>
                </Row>
                <Row className="pt-5 justify-content-center">
                  <Button  variant="primary" onClick={clickJoin}>
                  Join
                  </Button>
                </Row>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Join;
