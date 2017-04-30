export const addDestination = (destination) => {
  return {
    type: 'ADD_DESTINATION',
    destination
  }
}

export const removeDestination = (destinationId) => {
  return {
    type: 'REMOVE_DESTINATION',
    destinationId
  }
}

export const clearDestinations = () => {
  return {
    type: 'CLEAR_DESTINATIONS'
  }
}
