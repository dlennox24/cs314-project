import {
  connect
} from 'react-redux';
import {
  updateConnectStatus
} from '../actions/connected';
import * as AppComponent from '../components/App';

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

const App = connect(
  mapStateToProps,
  mapDispatchToProps
)(AppComponent.default);

export default App;
