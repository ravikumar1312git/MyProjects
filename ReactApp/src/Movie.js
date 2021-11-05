import React from 'react';
import './Movie.css';
import Rater from 'react-rater';
import 'react-rater/lib/react-rater.css';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';

class Movie extends React.Component
{
    constructor(props)
    {
        super(props);

    }

    render()
    {
        return (<div className='moviecard'>
            <img src={'/images/'+this.props.movie.imgSrc} className='poster'></img>
            <h1 style={{"text-align":"center"}}>{this.props.movie.name}</h1>
            <h2 style={{"text-align":"center"}}>{this.props.movie.language}</h2>
            <h2 style={{"text-align":"center"}}>{this.props.movie.twoorthreeD}</h2>
            <Rater total={5} rating={this.props.movie.rating}></Rater><br/>
            <Button type='submit' onClick={()=>this.props.bookMovie(this.props.movie)}>Book</Button>
        </div>);
    }
}
export default Movie;