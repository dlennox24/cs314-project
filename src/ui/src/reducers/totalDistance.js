const destinations = (state = null, action) => {
  switch (action.type) {
    case 'UPDATE_TOTAL_DISTANCE':
      console.log(action.destinations);
      if (action.destinations != null) {
        if (action.destinations.length > 0 && action.destinations[0].distance != null) {
          let totalDistance = 0;
          for (let i = 0; i < action.destinations.length; i++) {
            totalDistance += action.destinations[i].distance;
          }
          console.log(totalDistance);
          return totalDistance;
        }
      }
      return state;
    default:
      return state;
  }
}

export default destinations;
