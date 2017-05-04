import {
  connect
} from 'react-redux';
import {
  addDestinations,
  setDestinations
} from '../actions/itinerary';
import {
  toggleDisable,
  toggleIsOptimizing
} from '../actions/settings';
import * as ImportExportComponent from '../components/ImportExport';

const mapStateToProps = (state) => {
  return {
    destinations: state.destinations,
    optimization: state.settings.optimization,
    disabled: state.settings.disabled
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleAddDestinations: (destinations) => {
      dispatch(addDestinations(destinations));
    },
    handleSetDestinations: (destinations) => {
      dispatch(setDestinations(destinations));
    },
    handleToggleDisableSettings: () => {
      dispatch(toggleDisable());
    },
    handleToggleIsOptimizing: () => {
      dispatch(toggleIsOptimizing());
    }
  }
};

const ImportExport = connect(
  mapStateToProps,
  mapDispatchToProps
)(ImportExportComponent.default);

export default ImportExport;
