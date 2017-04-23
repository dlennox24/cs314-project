import React, { Component } from 'react';

import CsuSvgLogo from './CsuBranding.js';


export default class Layout extends Component {
  render(){
    return (
      <div className="full-width">
        <header id='site-header'>
          <div className='topbar'>
            <div className='container top-header'>
              <CsuSvgLogo />
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

// export default class Layout extends Component {
//   componentDidMount(){
//     $.getScript('https://static.colostate.edu/logo/reslogo/logo.min.js').done(function(){
//       $('#unit-title').removeClass('display-none');
//     });
//   }
//   render(){
//     return (
//       <div className="full-width">
//         <header id='site-header'>
//           <div className='topbar'>
//             <div className='container top-header'>
//               <div className='signature'>
//                 <section id='BrandLogo' className='fontLarge'>
//                   <div className='responsiveLogoContainer'>
//                     <div id='responsiveLogo' className='screenMD'>
//                     </div>{/* /#responsiveLogo */}
//                     <div id='responsiveLogoSubsytem'>
//                       <h2><a id='unit-title' className='display-none' href={config.unitUrl}>{config.unitTitle}</a></h2>
//                     </div>{/* /#responsiveLogoSubsytem */}
//                   </div>{/* /.responsiveLogoContainer */}
//                 </section>{/* /#BrandLogo */}
//               </div>{/* /.signature */}
//             </div>
//           </div>
//         </header>
//         <div id='main-content' className='container-fluid'>
//           {this.props.children}
//         </div>
//       </div>
//     );
//   }
// }

export class ItineraryObj extends Component{
  render() {
    return (
      <div className='well'>
        location
      </div>
    );
  }
}
