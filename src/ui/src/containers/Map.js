import {
  connect
} from 'react-redux';
import * as MapComponent from '../components/Map';

const mapStateToProps = (state) => {
  return {
    destinations: state.destinations
  }
};

const Map = connect(
  mapStateToProps,
)(MapComponent.default);

export default Map;
