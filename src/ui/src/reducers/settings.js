const settings = (state = {}, action) => {
  switch (action.type) {
    case 'TOGGLE_UNITS':
      return {
        ...state,
        useKm: !state.useKm
      }
    case 'SET_OPTIMIZATION':
      return {
        ...state,
        optimization: action.optimization
      }
    default:
      return state;
  }
}

export default settings;
