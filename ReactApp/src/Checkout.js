import React from 'react';
import './Checkout.css';

class Checkout extends React.Component
{

    constructor(props)
    {
        super(props);

    }

    render()
    {
        return (<div className={'coouter'}>
        <div className={'cotitle'}>Checkout Details</div>
        <div style={{"padding":"5px"}}>
            Total Amount To Be Paid : Rs.{this.props.totalamount}
            <br/>
            <br/>
            <br/>
            Thanks for booking ticket online. ENJOY YOUR CINEMA !!!
        </div>
       
    </div>);
    }
}

export default Checkout;