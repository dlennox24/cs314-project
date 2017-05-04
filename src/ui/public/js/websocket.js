let websocket;

function websocketInit() { // eslint-disable-line
  websocket = new WebSocket(
    (window.location.protocol === 'https:' ? 'wss:' : 'ws:') +
    '//129.82.44.137:3000/websocket'
  );
  websocket.onerror = (error) => {
    console.error(error);
  };
  websocket.onclose = (event) => {
    if (!event.wasClean) {
      console.error(event);
    }
  };
}
