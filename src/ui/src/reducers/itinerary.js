const destinations = (state = [], action) => {
  switch (action.type) {
    case 'ADD_DESTINATION':
      return [
        ...state,
        action.destination
      ]
    case 'REMOVE_DESTINATION':
      let destinations = state.filter(function(obj) {
        return obj.id !== action.destinationId;
      });
      return destinations;
    case 'CLEAR_DESTINATIONS':
      return [];
    default:
      return state;
  }
}

export default destinations;
