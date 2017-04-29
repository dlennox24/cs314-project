import {combineReducers} from 'redux';
import settings from './settings';
import filters from './filters';

const reducers = combineReducers({filters, settings});

export default reducers;
