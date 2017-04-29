const settings = (state = {}, action) => {
  switch (action.type) {
    case 'TOGGLE_UNITS':
      return Object.assign({}, state, {
        useKm: !state.useKm
      })
    case 'SET_OPTIMIZATION':
      return Object.assign({}, state, {
        optimization: action.optimization
      })
    default:
      return state;
  }
}

export default settings;
