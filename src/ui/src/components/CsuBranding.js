import React, { Component } from 'react';
import $ from 'jquery';
import config from '../json/config.json';

export default class SvgLogo extends Component{
  componentDidMount(){
    $.getScript('https://static.colostate.edu/logo/reslogo/logo.min.js').done(function(){
      $('#unit-title').removeClass('display-none');
    });
  }
  render (){
    return (
      <div className='signature'>
        <section id='BrandLogo' className='fontLarge'>
          <div className='responsiveLogoContainer'>
            <div id='responsiveLogo' className='screenMD'>
            </div>
            <div id='responsiveLogoSubsytem'>
              <h2><a id='unit-title' className='display-none' href={config.unitUrl}>{config.unitTitle}</a></h2>
            </div>
          </div>
        </section>
      </div>
    );
  }
}
