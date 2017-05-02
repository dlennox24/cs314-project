import {
  combineReducers
} from 'redux';
import settings from './Settings';
import filters from './Filters';
import destinations from './Itinerary';
import connected from './connected';

const reducers = combineReducers({
  connected,
  filters,
  settings,
  destinations
});

export default reducers;
