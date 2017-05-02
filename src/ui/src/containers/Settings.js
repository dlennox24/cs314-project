import {
  connect
} from 'react-redux';
import {
  toggleUnits,
  importTrip
} from '../actions/settings';
import {
  restoreDefaults
} from '../actions/filters';
import * as SettingsComponent from '../components/Settings';

const mapStateToSettingsProps = (state) => {
  return {
    filters: state.filters
  }
};

const mapDispatchToSettingsProps = (dispatch) => {
  return {
    handleRestoreDefaults: () => {
      dispatch(restoreDefaults());
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
    },
    handleImportTrip: (currentDestinations, destinations) => {
      dispatch(importTrip(currentDestinations, destinations));
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
