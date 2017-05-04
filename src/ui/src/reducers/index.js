import {
  combineReducers
} from 'redux';
import settings from './Settings';
import filters from './Filters';
import destinations from './Itinerary';
import connected from './connected';
import totalDistance from './totalDistance';

const reducers = combineReducers({
  connected,
  filters,
  settings,
  destinations,
  totalDistance
});

export default reducers;
