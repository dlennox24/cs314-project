import {
  connect
} from 'react-redux';
import {
  setOptimization
} from '../actions';
import * as SettingsComp from '../components/Settings';

const mapStateToProps = (state) => {
  return {
    filters: state.filters,
    optimization: state.settings.optimization
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleOptimizationChange: (optimization) => {
      dispatch(setOptimization(optimization));
    }
  }
};

const Settings = connect(
  mapStateToProps,
  mapDispatchToProps
)(SettingsComp.default);

export default Settings;
