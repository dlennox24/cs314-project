export const addFilter = (filterType, filter) => {
  return {
    type: 'ADD_FILTER',
    filterType,
    filter
  }
}

export const removeFilter = (filterType, filter) => {
  return {
    type: 'REMOVE_FILTER',
    filterType,
    filter
  }
}

export const clearFilters = (filterType) => {
  return {
    type: 'CLEAR_FILTERS',
    filterType,
  }
}

export const addDestination = (destination) => {
  return {
    type: 'ADD_DESTINATION',
    destination
  }
}

export const removeDestination = (destination) => {
  return {
    type: 'REMOVE_DESTINATION',
    destination
  }
}

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
