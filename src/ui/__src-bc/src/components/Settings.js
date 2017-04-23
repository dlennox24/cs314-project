import React, { Component } from 'react';
import $ from 'jquery';
import Slider from 'rc-slider';
import AutoComplete from 'material-ui/AutoComplete';
import Toggle from 'material-ui/Toggle';
import Chip from 'material-ui/Chip';

import testData from '../json/testData.json';

export default class Settings extends Component{
  componentDidMount(){
    $('#btn-adv-opts').off().on('click',function(){
      $('.btn-adv-opts-chevron').toggleClass('fa-rotate-180');
    });
  }
  render() {
    return (
      <div className='modal fade' id='settings-modal' tabIndex='-1' role='dialog' aria-labelledby='settings-modal'>
        <div className='modal-dialog modal-lg' role='document'>
          <div className='modal-content'>
            <div className='modal-header'>
              <button type='button' className='close' data-dismiss='modal' aria-label='Close'>
                <span aria-hidden='true'>&times;</span>
              </button>
              <h3 className='modal-title'><i className="material-icons">settings</i> Settings</h3>
            </div>
            <div className='modal-body'>
              <div className='row'>
                <div className='col-md-8 col-md-offset-2'>
                  <AutoComplete
                    dataSource={testData.airports}
                    floatingLabelText='Add a Destination'
                    fullWidth={true}
                    filter={AutoComplete.caseInsensitiveFilter}
                    maxSearchResults={10}
                    dataSourceConfig={{'text':'name','value':'code'}}
                    />
                </div>
              </div>
              <div className='row'>
                <div className='col-md-8 col-md-offset-2'>
                  <div className='pull-right'>
                    <UseKm />
                  </div>
                </div>
              </div>
              <h5 id='btn-adv-opts' className='text-center' role='button' data-toggle='collapse' href='#adv-opts' aria-expanded='false' aria-controls='adv-opts'>
                <i className='btn-adv-opts-chevron fa fa-chevron-down'/>
                <span> Advanced Settings </span>
                <i className='btn-adv-opts-chevron fa fa-chevron-down'/>
              </h5>
              <div id='adv-opts' className='collapse'>
                <hr/>
                <div className='row'>
                  <div className='col-md-6'>
                    <TripOptimizations />
                  </div>
                  <div className='col-md-6'>
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
                <div className='row'>
                  <div className='col-md-offset-2 col-md-8'>
                    <div className='form-group'>
                      <input type='file' id='inputFile4' multiple='true'/>
                      <div className='input-group'>
                        <span className='input-group-btn input-group-sm'>
                          <button type='button' className='btn btn-fab btn-fab-mini'>
                            <i className='fa fa-upload'/>
                          </button>
                        </span>
                        <input type='text' readOnly='true' className='form-control' placeholder='Import Existing Trip' />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export class UseKm extends Component{
  render() {
    return (
      <Toggle
        label="Kilometers"
        labelPosition="right"
        />
    );
  }
}

export class TripOptimizations extends Component{
  render() {
    const optMarks = {
      0:'None',
      33:'Nearest Neighbor',
      66:'2-opt',
      99:'3-opt',
    };
    return (
      <div>
        <p className='text-center'>Trip Optimization</p>
        <div className='rc-slider-marks-container'>
          <Slider dots min={0} max={99} marks={optMarks} step={33} defaultValue={66} />
        </div>
        <hr className='visible-xs-block'/>
      </div>
    );
  }
}

export class AirportSizes extends Component{
  deleteChip(){
    console.log('delete');
  }
  render() {
    return (
      <div>
        <AutoComplete
          dataSource={testData.airportSizes}
          floatingLabelText='Filter by Airport Size'
          fullWidth={true}
          filter={AutoComplete.caseInsensitiveFilter}
          maxSearchResults={5}
          />
        <div>
          <Chip
            onRequestDelete={this.deleteChip}
            onTouchTap={this.deleteChip}
            >
            Small
          </Chip>
        </div>
      </div>
    );
  }
}
