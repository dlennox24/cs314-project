const filters = (state = {}, action) => {
  switch (action.type) {
    case 'ADD_FILTER':
      return {
        ...state,
        [action.filterType]: [...state[action.filterType], action.filter]
      }
    case 'REMOVE_FILTER':
      state[action.filterType].splice(action.filter, 1);
      return {
        ...state,
        [action.filterType]: [...state[action.filterType]]
      }
    case 'CLEAR_FILTERS':
      return {
        ...state,
        [action.filterType]: []
      }
    default:
      return state;
  }
}

export default filters;
