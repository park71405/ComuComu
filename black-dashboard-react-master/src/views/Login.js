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
  return (
    <>
      <div className="content">
        <Row>
          <Col md="12">
            <Card className="p-5" style={{ backgroundColor: "#1d8cf8" }}>
              <CardHeader >
                <CardTitle >
                    <h2 style={{ color: "black" }}>LogIn</h2>
                </CardTitle>
              </CardHeader>
              <CardBody>
                <Card>
                    
                </Card>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Login;
