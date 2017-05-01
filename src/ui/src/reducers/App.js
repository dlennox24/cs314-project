import config from '../json/config.json';

const websocket = (state = {}, action) => {
  switch (action.type) {
    case 'CREATE_WEBSOCKET':
      let websocket = new WebSocket(
        (window.location.protocol === 'https:' ? 'wss:' : 'ws:') +
        config.websocketUrl
      );
      websocket.onerror = (error) => {
        action.handleSetWebsocketError(error);
      };
      websocket.onclose = (event) => {
        if (!event.wasClean) {
          action.handleSetWebsocketError(event);
        }
      };
      return {
        ...state,
        websocket: websocket
      }
    case 'SET_WEBSOCKET':
      return {
        ...state,
        websocket: action.websocket
      }
    case 'SET_WEBSOCKET_ERROR':
      return {
        ...state,
        error: true
      }
    default:
      return state;
  }
}

export default websocket;
