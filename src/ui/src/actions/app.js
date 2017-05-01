export const createWebsocket = (handleSetWebsocketError) => {
  return {
    type: 'CREATE_WEBSOCKET',
    handleSetWebsocketError
  }
}

export const setWebsocket = (websocket) => {
  return {
    type: 'SET_WEBSOCKET',
    websocket
  }
}

export const setWebsocketError = (error) => {
  return {
    type: 'SET_WEBSOCKET_ERROR',
    error
  }
}
