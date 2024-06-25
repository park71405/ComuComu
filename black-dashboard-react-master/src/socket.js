import { io } from 'socket.io-client';

const URL = process.env.NODE_ENV === 'production' ? undefined : 'http://localhost:3030';

// socket.connect() 를 호출해야만 연결됨
export const socket = io(URL, {
    autoConnect: false
  });