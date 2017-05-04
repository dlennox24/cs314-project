import {
  connect
} from 'react-redux';
import {
  updateConnectStatus
} from '../actions/connected';
import * as ConnectingDialogComponent from '../components/ConnectingDialog';

const mapStateToProps = (state) => {
  return {
    connected: state.connected
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleUpdateConnectStatus: (status) => {
      dispatch(updateConnectStatus(status));
    }
  }
};

const ConnectingDialog = connect(
  mapStateToProps,
  mapDispatchToProps
)(ConnectingDialogComponent.default);

export default ConnectingDialog;
