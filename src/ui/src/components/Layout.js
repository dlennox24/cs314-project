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
        <footer id='site-footer'>
          <div className='container'>
            <div className='row'>
              <div className='col col-lg-8 col-md-6'>
                <ul>
                  <li><a href='http://www.colostate.edu/equal-opportunity'>Equal Opportunity</a></li>
                  <li><a href='http://www.colostate.edu/privacy'>Privacy Statement</a></li>
                  <li><a href='http://www.colostate.edu/disclaimer'>Disclaimer</a></li>
                </ul>
                <p className='copyright'>&copy; 2017 Colorado State University, Fort Collins, Colorado 80523 USA</p>
              </div>
              <div className='col col-lg-4 col-md-6'>
                <div className='bottom-logo'>
                  <a href='http://www.colostate.edu/'>
                    <img src={config.appRoot+'images/signature-oneline.svg'} alt='Colorado State University' />
                  </a>
                </div>
              </div>
            </div>
          </div>
        </footer>
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
