import {
  connect
} from 'react-redux';
import {
  addFilter,
  removeFilter,
  clearFilters,
  getFilterLike
} from '../actions/filters';
import * as FiltersComp from '../components/Filters';

const mapStateToProps = (state, ownProps) => {
  return {
    filters: state.filters[ownProps.filterType],
    disabled: state.settings.disabled
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    handleAddFilter: (filterType, filter) => {
      dispatch(addFilter(filterType, filter));
    },
    handleRemoveFilter: (filterType, filter) => {
      dispatch(removeFilter(filterType, filter));
    },
    handleClearFilters: (filterType) => {
      dispatch(clearFilters(filterType));
    },
    handleGetFilterLike: (query, filterType) => {
      dispatch(getFilterLike(query, filterType));
    }
  }
};

const Filters = connect(
  mapStateToProps,
  mapDispatchToProps
)(FiltersComp.default);

export default Filters;
