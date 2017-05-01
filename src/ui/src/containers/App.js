import {
  connect
} from 'react-redux';
import {
  createWebsocket,
  setWebsocket,
  setWebsocketError
} from '../actions/app';
import * as AppComponent from '../components/App';

const mapStateToProps = (state) => {
  return {
    websocket: state.websocket
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleCreateWebsocket: (handleSetWebsocketError) => {
      dispatch(createWebsocket(handleSetWebsocketError));
    },
    handleSetWebsocket: (websocket) => {
      dispatch(setWebsocket(websocket));
    },
    handleSetWebsocketError: (error) => {
      dispatch(setWebsocketError(error));
    }
  }
};

const App = connect(
  mapStateToProps,
  mapDispatchToProps
)(AppComponent.default);

export default App;
