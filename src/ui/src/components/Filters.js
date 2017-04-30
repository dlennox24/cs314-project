import React, {
  Component
} from 'react';
import AutoComplete from 'material-ui/AutoComplete'
import {
  List,
  ListItem
} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Chip from 'material-ui/Chip';
import Subheader from 'material-ui/Subheader';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';
import {
  Toolbar,
  ToolbarGroup,
  ToolbarSeparator,
  ToolbarTitle
} from 'material-ui/Toolbar';
import Dialog from 'material-ui/Dialog';
import Snackbar from 'material-ui/Snackbar';

import {
  CloseButton
} from './Utils';

import config from '../json/config.json';
import './Filters.css';

export default class Filters extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false,
      snackBarIsOpen: false,
      snackBarMessage: 'Filter added to ' + this.props.name + ' Filters',
      snackBarBg: config.statusTheme.success,
      searchText: ''
    };
  }

  handleOpenToggle = () => this.setState({
    isOpen: !this.state.isOpen
  });

  handleNewRequest = (value) => {
    if (this.props.filters.includes(value)) {
      this.setState({
        searchText: '',
        snackBarIsOpen: true,
        snackBarBg: config.statusTheme.warning,
        snackBarMessage: 'Filter already exists in ' + this.props.name + ' Filter'
      })
    } else {
      this.setState({
        searchText: '',
        snackBarIsOpen: true,
        snackBarBg: config.statusTheme.success,
        snackBarMessage: 'Filter added to ' + this.props.name + ' Filters'
      });
      this.props.handleAddFilter(this.props.filterType, value);
    }
  }

  handleUpdateInput = (searchText) => {
    this.setState({
      searchText: searchText
    });
  }

  handleSnackbarClose = () => {
    this.setState({
      snackBarIsOpen: false
    });
  }

  populateFilterList = () => {
      if (this.props.filters.length === 0) {
        return (
          <Subheader style={{textAlign:'center'}}>
          No Filters Applied
        </Subheader>
        );
      }
      return this.props.filters.sort().map((text, i) => (
            <ListItem
        key={i}
        primaryText={text}
        rightIcon={
          <IconButton
            iconClassName='material-icons'
            className='remove-filter-btn'
            onTouchTap={this.props.handleRemoveFilter.bind(this, this.props.filterType, text)}
            >
              remove_circle_outline
          </IconButton>
        }
        style={{textTransform:'capitalize'}}
        />
    ))
  }

  render() {
    const dialogTitle = (
      <div>
        <Toolbar>
          <ToolbarGroup firstChild={true}>
            <CloseButton
              onTouchTap={this.handleOpenToggle}
              tooltipPosition='bottom-right'
              />
            <ToolbarSeparator style={{'margin':'0 10px 0 0'}} />
            <ToolbarTitle text={this.props.name+' Filters'} />
          </ToolbarGroup>
          <ToolbarGroup lastChild={true}>
            <IconButton
              onTouchTap={this.props.handleClearFilters.bind(this, this.props.filterType)}
              tooltip='Clear Filters'
              tooltipPosition='bottom-left'
              >
              <FontIcon className='material-icons'>clear_all</FontIcon>
            </IconButton>
          </ToolbarGroup>
        </Toolbar>
      </div>
    );
    return (
      <div>
        <AutoComplete
          searchText={this.state.searchText}
          dataSource={this.props.dataSource}
          floatingLabelText={'Filter by '+this.props.name}
          fullWidth={true}
          filter={AutoComplete.caseInsensitiveFilter}
          maxSearchResults={5}
          onNewRequest={this.handleNewRequest}
          onUpdateInput={this.handleUpdateInput}
          />
        <Chip
          onTouchTap={this.handleOpenToggle}
          className='pull-right'
          >
          <Avatar size={32}>{this.props.filters.length}</Avatar>
          Applied Filters
        </Chip>
        <Dialog
          title={dialogTitle}
          open={this.state.isOpen}
          onRequestChange={(open) => this.setState({open})}
          titleStyle={{padding:0}}
          autoScrollBodyContent={true}
          onRequestClose={this.handleOpenToggle}
          >
          <List>
            {this.populateFilterList()}
          </List>
        </Dialog>
        <Snackbar
          open={this.state.snackBarIsOpen}
          message={this.state.snackBarMessage}
          autoHideDuration={config.snackbarAutoHide}
          onRequestClose={this.handleSnackbarClose}
          bodyStyle={{
            backgroundColor: 'rgba(0,0,0,.5)'
          }}
          style={{
            backgroundColor: this.state.snackBarBg
          }}
          />
      </div>
    );
  }
}
