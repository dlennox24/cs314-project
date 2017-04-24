import React, {Component} from 'react';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';

export class CloseButton extends Component {
  render() {
    const tooltipPosition = this.props.tooltipPosition == null ?
    'bottom-center' : this.props.tooltipPosition;
    return(
      <div>
        <IconButton
          onTouchTap={this.props.onTouchTap}
          tooltip='Close'
          tooltipPosition={tooltipPosition}
          >
          <FontIcon className='material-icons'>close</FontIcon>
        </IconButton>
      </div>
    );
  }
}
