import { backgroundColors } from "contexts/BackgroundColorContext";
import { useState } from "react";

const {
  Row,
  Col,
  Card,
  CardBody,
  CardHeader,
  CardTitle,
  Table,
  Input,
  Button,
  CardFooter,
} = require("reactstrap");

function Chat() {
  const [message, setMessage] = useState([]);

  const clickSendChatBtn = () => {
    let userInputChat = document.getElementById("inputChat").value;

    setMessage([...message, userInputChat]);

    document.getElementById("inputChat").value = '';
  };

  return (
    <>
      <div className="content">
        <Row>
          <Col sm={5}>
            <div style={{ minHeight: "fit-content" }}>
              <Card style={{ height: "calc(100vh - 100px)" }}>
                <CardHeader>
                  <CardTitle tag="h4">Chat List</CardTitle>
                  <Input
                    placeholder="SEARCH"
                    type="text"
                    className="mb-5 mt-4"
                    style={{ backgroundColor: "white", color: "black" }}
                  />
                </CardHeader>
                <CardBody>
                  <Table className="tablesorter text-center">
                    <tbody>
                      <tr>
                        <td>haha</td>
                      </tr>
                    </tbody>
                  </Table>
                </CardBody>
              </Card>
            </div>
          </Col>
          <Col>
            <div style={{ minHeight: "fit-content" }}>
              <Card style={{ height: "calc(100vh - 100px)" }}>
                <CardHeader>
                  <CardTitle tag="h4">Chat Name</CardTitle>
                </CardHeader>
                <CardBody className="d-flex align-items-end">
                  <Table
                    id="chatList"
                    className="tablesorter text-center mb-5 mt-3 "
                  >
                    <tbody>
                      {message.map((message, index) => {
                        return (
                          <tr key={index}>
                            <td>{message}</td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                </CardBody>
                <Row className="mb-2 mx-2">
                  <Col xs={9} className="mt-1">
                    <Input
                      placeholder="SEARCH"
                      type="text"
                      className=""
                      id="inputChat"
                      style={{ backgroundColor: "white", color: "black" }}
                    />
                  </Col>
                  <Col xs={3} className="">
                    <Button className="" onClick={clickSendChatBtn}>
                      <i className="tim-icons icon-send" />
                    </Button>
                  </Col>
                </Row>
              </Card>
            </div>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Chat;
