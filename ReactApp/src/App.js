import React from 'react';
import logo from './logo.svg';
import './App.css';
import Header from './Header';
import Login from './Login';
import MovieList from './MoveList';
import MovieDetails from './MovieDetails';
import BookSummary from './BookSummary';
import Checkout from './Checkout';

class App extends React.Component
{
  constructor()
  {
    super();
    this.state={username:'',isValidUser:false,location:'',selectedMovie:null,proceedToBook:false,isPaymentDone:false,summary:null};
  }

  updateValid=(isValid,user)=>
  {
    this.setState({isValidUser:isValid,username:user});
  }
  
  updateLocation=(loc)=>
  {
    this.setState({location:loc});
  }

  logout=()=>
  {
    this.setState({isValidUser:false,username:'',selectedMovie:null,proceedToBook:false,isPaymentDone:false,summary:null});
  }

  render()
  {
    let {isValidUser,selectedMovie,proceedToBook,isPaymentDone} = this.state;
    const showPage = ()=>{
      if(isValidUser && selectedMovie==null)
        return <MovieList prop={{location:this.state.location}} selectMovie={(mov)=>{this.setState({selectedMovie:mov});}}/>;
      else if(selectedMovie!=null && !proceedToBook)
        return <MovieDetails movie={this.state.selectedMovie} location={this.state.location} proceedToBook={(smry)=>{console.log(smry);this.setState({proceedToBook:true,summary:smry})}}/>
      else if(proceedToBook && !isPaymentDone)
        return <BookSummary summary={this.state.summary} modifyBooking={()=>this.setState({selectedMovie:null,proceedToBook:false})}
                 confirmBooking={()=>this.setState({isPaymentDone:true})}></BookSummary>;
      else if(isPaymentDone)
        return <Checkout totalamount={this.state.summary.noOfTickets*this.state.summary.price}/>
      else
        return <Login prop={this.updateValid}/>;
    }
    return (<React.Fragment>
      <Header prop={{username:this.state.username,isValidUser:this.state.isValidUser,updateLocation:this.updateLocation,logout:this.logout}}/>
      {showPage()}
    </React.Fragment>);
  }
} 

export default App;
