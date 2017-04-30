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
