import {
  connect
} from 'react-redux';
import {
  setOptimization,
  toggleUnits
} from '../actions/settings';
import * as SettingsComponent from '../components/Settings';

const mapStateToSettingsProps = (state) => {
  return {
    filters: state.filters,
    optimization: state.settings.optimization
  }
};

const mapDispatchToSettingsProps = (dispatch) => {
  return {
    handleOptimizationChange: (optimization) => {
      dispatch(setOptimization(optimization));
    }
  }
};

const mapStateToUseKmProps = (state) => {
  return {
    useKm: state.settings.useKm
  }
};

const mapDispatchToUseKmProps = (dispatch) => {
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

export const UseKm = connect(
  mapStateToUseKmProps,
  mapDispatchToUseKmProps
)(SettingsComponent.UseKm);

export default Settings;
