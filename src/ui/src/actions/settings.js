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
