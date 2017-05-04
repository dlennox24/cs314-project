const settings = (state = {}, action) => {
  switch (action.type) {
    case 'TOGGLE_UNITS':
      return {
        ...state,
        useMetric: !state.useMetric
      };
    case 'SET_OPTIMIZATION':
      return {
        ...state,
        optimization: action.optimization + 1
      };
    case 'TOGGLE_DISABLE':
      return {
        ...state,
        disabled: !state.disabled
      };
    case 'TOGGLE_IS_OPTIMIZING':
      return {
        ...state,
        isOptimizing: !state.isOptimizing
      };
    default:
      return state;
  }
}

export default settings;
