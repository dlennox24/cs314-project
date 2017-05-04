export const toggleUnits = () => {
  return {
    type: 'TOGGLE_UNITS'
  }
}

export const setOptimization = (optimization) => {
  return {
    type: 'SET_OPTIMIZATION',
    optimization
  }
}

export const toggleDisable = () => {
  return {
    type: 'TOGGLE_DISABLE'
  }
}

export const toggleIsOptimizing = () => {
  return {
    type: 'TOGGLE_IS_OPTIMIZING'
  }
}
