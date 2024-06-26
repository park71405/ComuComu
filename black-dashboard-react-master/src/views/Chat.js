import { backgroundColors } from "contexts/BackgroundColorContext";
import { useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import { socket } from "socket";
import {io} from 'socket.io-client';
import Swal from "sweetalert2";

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

function Chat(props) {

  const navigate = useNavigate();

  const [message, setMessage] = useState([]);

  const clickSendChatBtn = () => {
    let userInputChat = document.getElementById("inputChat").value;

    let addmsg = {msg: userInputChat, type: "my"};

    setMessage(msg => [...msg, addmsg]);

    document.getElementById("inputChat").value = '';

    socket.emit('chat', addmsg);
    
  };

  // 채팅 로직 start
  const [isConnected, setIsConnected] = useState(socket.connected);

  useEffect(() => {

    if(props.isLogin === true){
      socket.connect();
    }else{

      
      Swal.fire({
        title: '로그인이 필요한 페이지 입니다. ',
        text: '로그인 페이지로 이동하겠습니다.',
        icon: "warning"
      });

      // 로그인 페이지로 이동
      navigate("/admin/login");
    }
    

    socket.on('connect', () =>{
      setIsConnected(true);
    });
    socket.on('disconnect', () => {
      setIsConnected(false);
    });
    socket.on('sendChat', (data) => {
      console.log("채팅 도착");
      console.log(data);
    })

    return () => {
      socket.off('connect', () =>{
        setIsConnected(true);
      });
      socket.off('disconnect', () => {
        setIsConnected(false);
      });
    };
  }, [props.isLogin]);
 
  // 채팅 로직 end

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
                <CardBody className="pl-3 d-flex align-items-end">
                  <Table
                    id="chatList"
                    className="tablesorter text-center mb-5 mt-3 "
                  >
                    <tbody>
                      {message.map((message, index) => {
                        if(message.type == "my"){
                          return (
                            <tr key={index}>
                              <td className="text-right px-4">
                                {message.msg}
                              </td>
                            </tr>
                          );
                        }else{
                          return (
                            <tr key={index}>
                              <td className="text-left px-4">
                                {message.msg}
                              </td>
                            </tr>
                          );
                        }
                        
                      })}
                    </tbody>
                  </Table>
                </CardBody>
                <Row className="mb-2 mx-2">
                  <Col xs={9} className="pl-5 mt-1">
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
