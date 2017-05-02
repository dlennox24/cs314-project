import config from '../json/config.json';
const filters = (state = {}, action) => {
  switch (action.type) {
    case 'ADD_FILTER':
      return {
        ...state,
        [action.filterType]: [...state[action.filterType], action.filter]
      };
    case 'REMOVE_FILTER':
      state[action.filterType].splice(action.filter, 1); // TODO: Bug when adding custom filter
      return {
        ...state,
        [action.filterType]: [...state[action.filterType]]
      };
    case 'CLEAR_FILTERS':
      return {
        ...state,
        [action.filterType]: []
      };
    case 'RESTORE_DEFAULTS':
      console.log(config);
      debugger
      return {
        ...state,
        airportSize: config.tripSettings.defaults.filters.airportSize,
        municipality: config.tripSettings.defaults.filters.municipality,
        region: config.tripSettings.defaults.filters.region,
        country: config.tripSettings.defaults.filters.country,
        continent: config.tripSettings.defaults.filters.continent
      };
    default:
      return state;
  }
}

export default filters;
