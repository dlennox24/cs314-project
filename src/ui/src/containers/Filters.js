import {
  connect
} from 'react-redux';
import {
  addFilter,
  removeFilter,
  clearFilters
} from '../actions/filters';
import * as FiltersComp from '../components/Filters';

const mapStateToProps = (state, ownProps) => {
  return {
    filters: state.filters[ownProps.filterType]
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
    }
  }
};

const Filters = connect(
  mapStateToProps,
  mapDispatchToProps
)(FiltersComp.default);

export default Filters;
