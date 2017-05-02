import _ from 'lodash';

const destinations = (state = [], action) => {
  switch (action.type) {
    case 'ADD_DESTINATION':
      var a = [...state, action.destination];
      for (let i = 0; i < a.length; i++) {
        /*global google*/
        a[i] = new google.maps.LatLng(a[i]);
      }
      console.log(google.maps.geometry.spherical.computeLength(a));
      return [
        ...state,
        action.destination
      ];
    case 'REMOVE_DESTINATION':
      let destinations = state.filter(function(obj) {
        return obj.id !== action.destinationId;
      });
      return destinations;
    case 'CLEAR_DESTINATIONS':
      return [];
    case 'IMPORT_TRIP':
      return _.uniqBy([...state, ...action.destinations], 'id');
    default:
      return state;
  }
}

export default destinations;
