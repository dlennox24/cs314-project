import React, { Component } from 'react';
import $ from 'jquery';
import config from '../json/config.json';
import Slider from 'rc-slider';

export class UseKm extends Component{
  render() {
    return (
      <div className='togglebutton'>
        <span><strong>Use Kilometers</strong></span>
        <label className='pull-right'>
          <input id='use=km' type='checkbox'/>
        </label>
      </div>
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
        <p className='text-center'><strong>Trip Optimization</strong></p>
        <Slider dots min={0} max={99} marks={optMarks} step={33} defaultValue={66} />
      </div>
    );
  }
}

export class AirportSizes extends Component{
  render() {
    return (
      <div>
        <p><strong>Airport Size</strong></p>
        <div className='col-md-offset-1'>
          <div className='checkbox'>
            <label>
              <input type='checkbox'/>
              <span className='checkbox-material'></span>
              <span> Small</span>
            </label>
          </div>
        </div>
        <div className='col-md-offset-1'>
          <div className='checkbox'>
            <label>
              <input type='checkbox'/>
              <span className='checkbox-material'></span>
              <span> Medium</span>
            </label>
          </div>
        </div>
        <div className='col-md-offset-1'>
          <div className='checkbox'>
            <label>
              <input type='checkbox'/>
              <span className='checkbox-material'></span>
              <span> Large</span>
            </label>
          </div>
        </div>
      </div>
    );
  }
}
