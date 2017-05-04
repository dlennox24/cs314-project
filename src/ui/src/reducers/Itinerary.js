import _ from 'lodash';

const destinations = (state = [], action) => {
  switch (action.type) {
    case 'ADD_DESTINATIONS':
      if (Array.isArray(action.destinations)) {
        return _.uniqBy([...state, ...action.destinations], 'id');
      }
      return _.uniqBy([...state, action.destinations], 'id');
    case 'REMOVE_DESTINATION':
      let destinations = state.filter(function(obj) {
        return obj.id !== action.destinationId;
      });
      return destinations;
    case 'CLEAR_DESTINATIONS':
      return [];
    case 'SET_DESTINATIONS':
      return action.destinations;
    default:
      return state;
  }
}

export default destinations;
