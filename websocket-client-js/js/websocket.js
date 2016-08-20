document.addEventListener('DOMContentLoaded', () => {
  const hostInput = document.getElementById('host');
  const usernameInput = document.getElementById('username');
  const connectBtn = document.getElementById('connect');
  const disconnectBtn = document.getElementById('disconnect');
  const messagesTextArea = document.getElementById('messages');
  const messageInput = document.getElementById('message');
  const sendBtn = document.getElementById('send');

  function randomizeUsername() {
    const userInput = usernameInput.value;

    if (userInput.trim() === '') {
      const randomizedValue = Math.floor(Math.random() * 1000);
      usernameInput.value = `Anonymous${randomizedValue}`;
    }
  }
  window.addEventListener('load', randomizeUsername);
  usernameInput.addEventListener('input', randomizeUsername);

  let socket;

  connectBtn.addEventListener('click', () => {
    const host = hostInput.value;
    usernameInput.setAttribute('readonly', true);

    const url = host + `?${usernameInput.name}=${usernameInput.value}`
    socket = new WebSocket(url);

    socket.addEventListener('open', (event) => {
    });

    socket.addEventListener('message', (event) => {
      messagesTextArea.value += `${event.data}\n`;
    });

    socket.addEventListener('close', (event) => {
      messagesTextArea.value += 'Connection closed\n';
    });
  });

  disconnectBtn.addEventListener('click', () => {
    if (socket) {
      socket.close();
    }
    usernameInput.removeAttribute('readonly');
  });

  sendBtn.addEventListener('click', () => {
    if (socket && socket.readyState === WebSocket.OPEN) {
      socket.send(messageInput.value);
      messageInput.value = '';
    } else {
      messagesTextArea.value += 'Not connected to the server\n';
    }
  });
});
