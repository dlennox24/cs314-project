import {
  connect
} from 'react-redux';
import {
  addDestination,
  removeDestination,
  clearDestinations,
  importTrip
} from '../actions/itinerary';
import * as ItineraryComponent from '../components/Itinerary';

const mapStateToItineraryProps = (state) => {
  return {
    destinations: state.destinations,
    useMetric: state.settings.useMetric,
    filters: state.filters
  }
};

const mapDispatchToItineraryProps = (dispatch) => {
  return {
    handleAddDestination: (destination) => {
      dispatch(addDestination(destination));
    },
    handleClearDestinations: () => {
      dispatch(clearDestinations());
    },
    handleImportTrip: (destinations) => {
      dispatch(importTrip(destinations));
    }
  }
};

const mapStateToItineraryObjProps = (state) => {
  return {
    useMetric: state.settings.useMetric
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
