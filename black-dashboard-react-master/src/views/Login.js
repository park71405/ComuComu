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

function Login() {

  const navigate = useNavigate();

  // 로그인 버튼 클릭 이벤트
  const clickLogin = () => {
    console.log("hahaha");

    let idData = document.getElementById("id").value;
    let pw = document.getElementById("pw").value;

    let reqData = {id: idData, password: pw}

    axios({
      method: "POST",
      data: reqData,
      url: "login",
    }).then((res) => {

      let token = res.data;
      //axios 요청 마다 헤더에 accessToekn 담도록 설정
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;

      // 메인페이지로 이동
      navigate("/admin/dashboard")

    }).catch((err)=>{
      console.log(err);
      Swal.fire({
        title: "로그인 실패",
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
            <Card className="p-5">
              
              <CardBody className="p-5">
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
                <Row className="pt-5 justify-content-center">
                  <Button  variant="primary" onClick={clickLogin}>
                  Login
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

export default Login;
