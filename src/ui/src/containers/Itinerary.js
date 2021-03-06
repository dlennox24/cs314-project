import {
  connect
} from 'react-redux';
import {
  addDestinations,
  removeDestination,
  clearDestinations,
  setDestinations
} from '../actions/itinerary';
import {
  toggleDisable,
  toggleIsOptimizing
} from '../actions/settings';
import {
  updateTotalDistance
} from '../actions/totalDistance';
import * as ItineraryComponent from '../components/Itinerary';

const mapStateToItineraryProps = (state) => {
  return {
    destinations: state.destinations,
    useMetric: state.settings.useMetric,
    filters: state.filters,
    optimization: state.settings.optimization,
    disabled: state.settings.disabled,
    isOptimizing: state.settings.isOptimizing,
    totalDistance: state.totalDistance
  }
};

const mapDispatchToItineraryProps = (dispatch) => {
  return {
    handleAddDestination: (destinations) => {
      dispatch(addDestinations(destinations));
    },
    handleSetDestinations: (destinations) => {
      dispatch(setDestinations(destinations));
    },
    handleClearDestinations: () => {
      dispatch(clearDestinations());
    },
    handleUpdateTotalDistance: (destinations) => {
      dispatch(updateTotalDistance(destinations));
    },
    handleToggleDisableSettings: () => {
      dispatch(toggleDisable());
    },
    handleToggleIsOptimizing: () => {
      dispatch(toggleIsOptimizing());
    }
  }
};

const mapStateToItineraryObjProps = (state) => {
  return {
    useMetric: state.settings.useMetric,
    optimization: state.settings.optimization,
    destinations: state.destinations
  }
};

const mapDispatchToItineraryObjProps = (dispatch) => {
  return {
    handleRemoveDestination: (destinationId) => {
      dispatch(removeDestination(destinationId));
    },
    handleToggleDisableSettings: () => {
      dispatch(toggleDisable());
    },
    handleSetDestinations: (destinations) => {
      dispatch(setDestinations(destinations));
    },
    handleToggleIsOptimizing: () => {
      dispatch(toggleIsOptimizing());
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
