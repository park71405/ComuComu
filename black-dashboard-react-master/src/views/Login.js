import axios from "axios";
import { useState } from "react";
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

function Login() {


  // 로그인 버튼 클릭 이벤트
  const clickLogin = () => {
    console.log("hahaha");

    let id = document.getElementById("id").value;
    let pw = document.getElementById("pw").value;

    console.log("id : " + id);
    console.log("pw : " + pw);

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
