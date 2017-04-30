import {
  connect
} from 'react-redux';
import {
  addDestination,
  removeDestination,
  clearDestinations
} from '../actions/itinerary';
import * as ItineraryComponent from '../components/Itinerary';

const mapStateToItineraryProps = (state) => {
  return {
    destinations: state.destinations,
    useKm: state.settings.useKm
  }
};

const mapDispatchToItineraryProps = (dispatch) => {
  return {
    handleAddDestination: (destination) => {
      dispatch(addDestination(destination));
    },
    handleClearDestinations: () => {
      dispatch(clearDestinations());
    }
  }
};

const mapStateToItineraryObjProps = (state) => {
  return {
    useKm: state.settings.useKm
  }
};

const mapDispatchToItineraryObjProps = (dispatch) => {
  return {
    handleRemoveDestination: (destinationId) => {
      dispatch(removeDestination(destinationId));
    }
  }
};

const Itinerary = connect(
  mapStateToItineraryProps,
  mapDispatchToItineraryProps
)(ItineraryComponent.default);

export const ItineraryObj = connect(
  mapStateToItineraryObjProps,
  mapDispatchToItineraryObjProps
)(ItineraryComponent.ItineraryObj);

export default Itinerary;
