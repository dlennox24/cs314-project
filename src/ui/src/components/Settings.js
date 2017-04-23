import React, { Component } from 'react';
import Slider from 'rc-slider';
import AutoComplete from 'material-ui/AutoComplete';
import Toggle from 'material-ui/Toggle';
import Dialog from 'material-ui/Dialog';

import testData from '../json/testData.json';

export default class Settings extends Component{
  constructor(props) {
    super(props);
    const open = this.props.open == null ? false : this.props.open;
    this.state = {'open': open};
  }
  handleSettingsToggle = () => this.setState({open: !this.state.open});
  render() {
    return (
      <Dialog
        title='Settings'
        modal={false}
        open={this.state.open}
        onRequestClose={this.handleSettingsToggle}
        >
        <div className='row'>
          <div className='col-md-offset-2 col-md-8'>
            <TripOptimizations />
          </div>
        </div>
        <div className='row'>
          <div className='col-md-6 col-md-offset-3'>
            <AirportSizes />
          </div>
        </div>
        <div className='row'>
          <div className='col-md-6'>
            <AutoComplete
              dataSource={testData.municipalities}
              floatingLabelText='Filter by Municipality'
              fullWidth={true}
              filter={AutoComplete.caseInsensitiveFilter}
              maxSearchResults={5}
              />
          </div>
          <div className='col-md-6'>
            <AutoComplete
              dataSource={testData.regions}
              floatingLabelText='Filter by Region'
              fullWidth={true}
              filter={AutoComplete.caseInsensitiveFilter}
              maxSearchResults={5}
              />
          </div>
        </div>
        <div className='row'>
          <div className='col-md-6'>
            <AutoComplete
              dataSource={testData.countries}
              floatingLabelText='Filter by Country'
              fullWidth={true}
              filter={AutoComplete.caseInsensitiveFilter}
              maxSearchResults={5}
              dataSourceConfig={{'text':'name','value':'code'}}
              />
          </div>
          <div className='col-md-6'>
            <AutoComplete
              dataSource={testData.continents}
              floatingLabelText='Filter by Continent'
              fullWidth={true}
              filter={AutoComplete.caseInsensitiveFilter}
              maxSearchResults={5}
              />
          </div>
        </div>
      </Dialog>
    );
  }
}

export const UseKm = () => {
  return (
    <Toggle
      label="Use Kilometers"
      labelPosition='left'
      style={{'textAlign':'right'}}
      />
  );
}

const TripOptimizations = () => {
  const optMarks = {
    0:'None',
    33:'Nearest Neighbor',
    66:'2-opt',
    99:'3-opt',
  };
  return (
    <div style={{'margin':'0 0 50px'}}>
      <p className='text-center'>Trip Optimization</p>
      <div className='rc-slider-marks-container'>
        <Slider dots min={0} max={99} marks={optMarks} step={33} defaultValue={66} />
      </div>
      <hr className='visible-xs-block'/>
    </div>
  );
}

const AirportSizes = () => {
  return (
    <div>
      <AutoComplete
        dataSource={testData.airportSizes}
        floatingLabelText='Filter by Airport Size'
        fullWidth={true}
        filter={AutoComplete.caseInsensitiveFilter}
        maxSearchResults={5}
        />
    </div>
  );
}
