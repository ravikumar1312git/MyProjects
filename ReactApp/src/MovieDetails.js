import React from 'react';
import axios from 'axios';
import './MovieDetails.css';

class MovieDetails extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state={theater:[],selectedTheater:''};
        this.dateRef = React.createRef();
        this.timeRef = React.createRef();
        this.noOfTicketRef = React.createRef();
    }

    showTheaters=()=>
    {
        let theaters=[];
        if(this.state.theater!=null && this.state.theater.theaters!=null)
        {
            this.state.theater.theaters.forEach((value,ind)=>
            {
                theaters.push(<a href='javascript:void(0);' onClick={(e)=>this.setState({selectedTheater:e.target.name})} name={value}>{value}</a>);
                theaters.push(<br/>);
            });
        }
       
        return theaters;
    }

    showTheaterDetails=(e)=>
    {
        let theaterTimings=[];
        let dates=[];
        let times=[];
        if(this.state.selectedTheater!='')
        {
            theaterTimings.push('Theater Selected : '+this.state.selectedTheater);
            theaterTimings.push(<br/>);
            theaterTimings.push('Date :');
            theaterTimings.push(<br/>);
            this.state.theater.dates.forEach((val,ind)=>
            {
                dates.push(<option value={val}>{val}</option>);
            });
            this.state.theater.time.forEach((val,ind)=>
            {
                times.push(<option value={val}>{val}</option>);
            });
            theaterTimings.push(<select ref={this.dateRef}>{dates}</select>);
            theaterTimings.push(<br/>);
            theaterTimings.push('Time :');
            theaterTimings.push(<br/>);
            theaterTimings.push(<select ref={this.timeRef}>{times}</select>);
            theaterTimings.push(<br/>);
            theaterTimings.push('Number of seates :');
            theaterTimings.push(<br/>);
            theaterTimings.push(<input type='text' ref={this.noOfTicketRef}></input>);
            theaterTimings.push(<br/>);
            theaterTimings.push(<input type='button' onClick={()=>this.submit()} value='Proceed'></input>);
        }
        return theaterTimings;
    }

    submit=()=>
    {
        console.log(this.state.selectedTheater+' '+this.dateRef.current.value+' '+this.timeRef.current.value+' '+this.noOfTicketRef.current.value);
        let summary={selectedTheater:this.state.selectedTheater,
                    date:this.dateRef.current.value,
                    time:this.timeRef.current.value,
                    noOfTickets:this.noOfTicketRef.current.value,
                    location:this.props.location,
                    moviename:this.props.movie.name,
                    price:this.state.theater.price[0]
                };
        this.props.proceedToBook(summary);
    }

    render()
    {
        console.log(this.props.movie);
        return (<div className={'main'}>
            <div className={'moviedetails'}>
                <div style={{"background":"darkblue","color":"white","padding":"2px","margin":"-1px"}}>Movie Details</div>
                <div style={{"padding":"5px"}}>
                    <img src={'images/'+this.props.movie.imgSrc} style={{"height":"500px"}}/><br/>
                    Movie Name : {this.props.movie.name}<br/>
                    Language   : {this.props.movie.language}<br/>
                    Format     : {this.props.movie.twoorthreeD}
                </div>
            </div>
            <div className={'theaterdetails'}>
                <div style={{"background":"darkblue","color":"white","padding":"2px","margin":"-1px"}}>Booking Details</div>
                <div style={{"padding":"5px"}}>
                    Select Your Theater:<br/>
                    {this.showTheaters()}
                 </div>
                 {this.showTheaterDetails()}
            </div>
        </div>);
    }

    componentDidMount=()=>
    {
        
        let urlToGetTheaters='';
        if(this.props.location=='chennai')
            urlToGetTheaters='http://localhost:3001/Chennai-theater';
        else if(this.props.location=='bengaluru')
            urlToGetTheaters='http://localhost:3001/Bengaluru-theater';
        else if(this.props.location=='chandigarh')
            urlToGetTheaters='http://localhost:3001/Chandigar-theater'; 
        console.log(this.props.location+' '+urlToGetTheaters);
        axios.get(urlToGetTheaters).then(res=>{this.setState({theater:res.data});}).catch(err=>console.log(err));
    }
}
export default MovieDetails;