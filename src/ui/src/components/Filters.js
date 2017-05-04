import React, {
  Component
} from 'react';
import AutoComplete from 'material-ui/AutoComplete'
import {
  List,
  ListItem
} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Snackbar from 'material-ui/Snackbar';
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
      snackbarIsOpen: false,
      snackbarSuccess: false,
      searchText: '',
      delayInput: null,
      filters: []
    };
  }
  handleOpenToggle = () => this.setState({
    isOpen: !this.state.isOpen
  });
  handleNewRequest = (value) => {
    if (this.props.filters.includes(value)) {
      this.setState({
        searchText: '',
        snackbarSuccess: false,
        snackbarIsOpen: true
      })
    } else {
      this.props.handleAddFilter(this.props.filterType, value);
      this.setState({
        searchText: '',
        snackbarSuccess: true,
        snackbarIsOpen: true
      });
    }
  }
  handleUpdateInput = (searchText) => {
    /* global websocket */
    websocket.send(JSON.stringify({
      endpoint: this.props.filterType,
      data: searchText
    }));
    websocket.onmessage = (message) => {
      this.setState({
        filters: message.data === 'null' ? [] : JSON.parse(message.data),
        searchText: searchText
      });
    }
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
            onTouchTap={this.props.handleRemoveFilter.bind(this, this.props.filterType, text)} >
              remove_circle_outline
          </IconButton>
        }
        style={{textTransform:'capitalize'}} />
    ))
  }
  handleSnackbarClose = () => {
    this.setState({
      snackbarIsOpen: false
    });
  }
  render() {
    const dialogTitle = (
      <div>
        <Toolbar>
          <ToolbarGroup firstChild={true}>
            <CloseButton
              onTouchTap={this.handleOpenToggle}
              tooltipPosition='bottom-right' />
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
          disabled={this.props.disabled}
          searchText={this.state.searchText}
          listStyle={{maxHeight:'200px',overflow:'auto'}}
          dataSource={this.state.filters}
          floatingLabelText={'Filter by '+this.props.name}
          fullWidth={true}
          filter={AutoComplete.caseInsensitiveFilter}
          onNewRequest={this.handleNewRequest}
          onUpdateInput={this.handleUpdateInput} />
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
          open={this.state.snackbarIsOpen}
          bodyStyle={{backgroundColor: 'rgba(0,0,0,.5)'}}
          message={this.state.snackbarSuccess ?
            'Filter added!' : 'Filter already applied!'}
          autoHideDuration={config.snackbarAutoHide}
          style={{backgroundColor: this.state.snackbarSuccess ?
            config.statusTheme.success : config.statusTheme.warning}} />
      </div>
    );
  }
}
