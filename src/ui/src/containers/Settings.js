import {
  connect
} from 'react-redux';
import {
  setOptimization,
  toggleUnits,
  toggleIsOptimizing,
  toggleDisable
} from '../actions/settings';
import {
  restoreDefaults
} from '../actions/filters';
import {
  setDestinations
} from '../actions/itinerary';
import {updateTotalDistance} from '../actions/totalDistance';
import * as SettingsComponent from '../components/Settings';

const mapStateToSettingsProps = (state) => {
  return {
    filters: state.filters,
    optimization: state.settings.optimization,
    disabled: state.settings.disabled,
    isOptimizing: state.settings.isOptimizing,
    destinations: state.destinations
  }
};

const mapDispatchToSettingsProps = (dispatch) => {
  return {
    handleSetOptimization: (optimization) => {
      dispatch(setOptimization(optimization));
    },
    handleRestoreDefaults: () => {
      dispatch(restoreDefaults());
    },
    handleSetDestinations: (destinations) => {
      dispatch(setDestinations(destinations));
    },
    handleUpdateTotalDistance: (destinations) => {
     dispatch(updateTotalDistance(destinations));
  },
    handleToggleIsOptimizing: () => {
      dispatch(toggleIsOptimizing());
    },
    handleToggleDisableSettings: () => {
      dispatch(toggleDisable());
    }
  }
};

const mapStateToUseMetricProps = (state) => {
  return {
    useMetric: state.settings.useMetric
  }
};

const mapDispatchToUseMetricProps = (dispatch) => {
  return {
    handleToggleUnits: () => {
      dispatch(toggleUnits());
    }
  }
};

const Settings = connect(
  mapStateToSettingsProps,
  mapDispatchToSettingsProps
)(SettingsComponent.default);

export const UseMetric = connect(
  mapStateToUseMetricProps,
  mapDispatchToUseMetricProps
)(SettingsComponent.useMetric);

export default Settings;
