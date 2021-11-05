import React from 'react';
import './BookingSummary.css';

class BookSummary extends React.Component
{

    constructor(props)
    {
        super(props);
    }

    render()
    {
        return (<div className={'outer'}>
            <div className={'bstitle'}>Booking Summary</div>
            <div style={{"padding":"5px","overflow":"hidden"}}>
                You have selected the following details
                <br/>
                Theater Name    :{this.props.summary.selectedTheater}<br/>
                Location        :{this.props.summary.location}<br/>
                Seats Booked    :{this.props.summary.noOfTickets}<br/>
                Movie Name      :{this.props.summary.moviename}<br/>
                Price           :{this.props.summary.price}<br/>
                Show Date       :{this.props.summary.date}<br/>
                Show Timimg     :{this.props.summary.time}<br/>
                <div>
                    <input type='button' value='Modify Booking' style={{"float":"left","maxWidth":"45%","margin":"5px"}} onClick={()=>this.props.modifyBooking()}></input>
                    <input type='button' value='Confirm Booking' style={{"float":"left","maxWidth":"45%","margin":"5px"}} onClick={()=>this.props.confirmBooking()}></input>
                </div>
            </div>
           
        </div>);
    }

}

export default BookSummary;