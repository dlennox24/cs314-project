import {
  combineReducers
} from 'redux';
import settings from './Settings';
import filters from './Filters';
import destinations from './Itinerary';

const reducers = combineReducers({
  filters,
  settings,
  destinations
});

export default reducers;
