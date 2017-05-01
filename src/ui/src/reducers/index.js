import {
  combineReducers
} from 'redux';
import settings from './Settings';
import filters from './Filters';
import destinations from './Itinerary';
import websocket from './App';

const reducers = combineReducers({
  websocket,
  filters,
  settings,
  destinations
});

export default reducers;
