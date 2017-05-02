const connected = (state = null, action) => {
  switch (action.type) {
    case 'UPDATE_CONNECT_STATUS':
      switch (action.status) {
        case 0:
          return null;
        case 1:
          return true;
        case 2:
        case 3:
          return false;
        default:
          return null;
      }
    default:
      return state;
  }
}

export default connected;
