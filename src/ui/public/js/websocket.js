let websocket;

function websocketInit(url) { // eslint-disable-line
  websocket = new WebSocket(
    (window.location.protocol === 'https:' ? 'wss:' : 'ws:') +
    url
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
