const settings = (state = {}, action) => {
  switch (action.type) {
    case 'TOGGLE_UNITS':
      return {
        ...state,
        useMetric: !state.useMetric
      };
    default:
      return state;
  }
}

export default settings;
