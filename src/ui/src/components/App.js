import React, { Component } from 'react';
import Layout, {Sidebar} from './Layout';

class Map extends Component {
  render() {
    return (
      <div/>
    );
  }
}

export default class App extends Component{
  render() {
    return (
      <Layout>
        <div className='row'>
          <div className='white-grey-bg-color col-md-2 sidebar'>
            <Sidebar>
              <h3>Itinerary</h3>
            </Sidebar>
          </div>
          <div className='white-grey-bg-color col-md-8'>
            <Map />
          </div>
          <div className='white-grey-bg-color col-md-2 sidebar'>
            <Sidebar>
              <h3>Settings</h3>
            </Sidebar>
          </div>
        </div>
      </Layout>
    );
  }
}
