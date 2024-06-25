//npm i express
//npm i http
//npm i socket.io

const express = require('express');
const cors = require('cors');

const app = express();
app.use(cors());

// http 서버 생성
const server = require('http').createServer(app);
// http 서버를 socket.io 서버로 전환
const io = require('socket.io')(server, {
    cors: {
        origin: "http://localhost:3000"
    }
});

// client가 socket.io 서버에 접속할 경우 connection 이벤트 발생
io.on('connection', (socket)=>{
    console.log("client 서버 접속함 id: " + socket.id);

    socket.on('chat', (data) => {

        console.log(data);

        socket.emit('sendChat', data);

    });

    socket.on('forceDisconnect', () => {
        socket.disconnect();
    })

    socket.on('disconnect', () => {
        console.log('user disconnected : ' + socket.name);
    })
});

server.listen(3030, () => {
    console.log('서버 실행중 ...');
});