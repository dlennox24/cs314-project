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
    filterType
  }
}

export const getFilterLike = (query, filterType) => {
  return {
    type: 'GET_FILTER_LIKE',
    query,
    filterType
  }
}

export const restoreDefaults = () => {
  return {
    type: 'RESTORE_DEFAULTS'
  }
}
