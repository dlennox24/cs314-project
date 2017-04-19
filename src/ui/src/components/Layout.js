import React, { Component } from 'react';
import $ from 'jquery';
import config from '../json/config.json';

export default class Layout extends Component {
  componentDidMount(){
    $.getScript('https://static.colostate.edu/logo/reslogo/logo.min.js').done(function(){
      $('#unit-title').removeClass('display-none');
    });
  }
  render(){
    // Removes dropdown menu for mobile on navigation
    if($('#navbar').hasClass('in')){
      $('#navbar').removeClass('in');
    }
    return (
      <div className="full-width">
        <header id='site-header'>
          <div className='topbar'>
            <div className='container top-header'>
              <div className='signature'>
                <section id='BrandLogo' className='fontLarge'>
                  <div className='responsiveLogoContainer'>
                    <div id='responsiveLogo' className='screenMD'>
                    </div>{/* /#responsiveLogo */}
                    <div id='responsiveLogoSubsytem'>
                      <h2><a id='unit-title' className='display-none' href={config.unitUrl}>{config.unitTitle}</a></h2>
                    </div>{/* /#responsiveLogoSubsytem */}
                  </div>{/* /.responsiveLogoContainer */}
                </section>{/* /#BrandLogo */}
              </div>{/* /.signature */}
            </div>
          </div>
        </header>
        <div id='main-content' className='container-fluid'>
          {this.props.children}
        </div>
      </div>
    );
  }
}

export class Sidebar extends Component{
  render() {
    return (
      <div>
        {this.props.children}
      </div>
    );
  }
}

export class ItineraryObj extends Component{
  render() {
    return (
      <div className="panel panel-default">
        <div className="panel-heading">Panel heading</div>
        <div className="panel-body">
          Panel content
        </div>
      </div>
    );
  }
}
