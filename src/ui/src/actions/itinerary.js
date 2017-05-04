export const addDestinations = (destinations) => {
  return {
    type: 'ADD_DESTINATIONS',
    destinations
  }
}

export const removeDestination = (destinationId) => {
  return {
    type: 'REMOVE_DESTINATION',
    destinationId
  }
}

export const setDestinations = (destinations) => {
  return {
    type: 'SET_DESTINATIONS',
    destinations
  }
}

export const clearDestinations = () => {
  return {
    type: 'CLEAR_DESTINATIONS'
  }
}
