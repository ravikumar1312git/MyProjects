import React from 'react';
import axios from 'axios';
import Movie from './Movie';
import './MoveList.css';

class MovieList extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state={movies:[],theater:[],sortBy:"Popularity",language:"select"};
        this.locBasedMovies = [];
        this.uniqueLanguages = [];
        this.nameRef = React.createRef();
    }

    locBasedFilter=()=>
    {
        let distinctLangOption = [];
        this.state.movies.forEach ((el,ind)=>
        {
            if(el.location==this.props.prop.location)
            { 
                if(!(this.uniqueLanguages.includes(el.language)))
                {
                    this.uniqueLanguages.push(el.language);
                    distinctLangOption.push(<option value={el.language}>{el.language}</option>);
                }
                this.locBasedMovies.push(el);
            }
        });

        return distinctLangOption;

    }

    filteredMovies=()=>
    {   
        var filterMoviesList = [];
        var searchText = '';
        var filterMovies = [];
        if(this.nameRef.current!=null)
            searchText = this.nameRef.current.value;
        this.locBasedMovies.forEach((el,ind)=>
        {
            if(searchText!=null && searchText!='' && this.state.language!='select')
            {
                if( el.name.toLowerCase().includes(searchText.toLowerCase()) && el.language==this.state.language)
                    filterMoviesList.push(el);
            }
            else if(searchText!=null && searchText!='' && el.name.toLowerCase().includes(searchText.toLowerCase()))
            {
                filterMoviesList.push(el);   
            }
            else if(this.state.language!='select' && el.language==this.state.language)
            {
                filterMoviesList.push(el);  
            }
            else if(searchText=='' && this.state.language=='select')
            {
                filterMoviesList.push(el);  
            }
        });
        
        if(filterMoviesList.length>0)
        {
            this.sortBy(filterMoviesList);
        }

        filterMoviesList.forEach((el,ind)=>{
            filterMovies.push(<Movie movie={el} bookMovie={(mov)=>this.props.selectMovie(mov)}/>);
        })
        return filterMovies;
    }

    sortBy=(filterMoviesList)=>
    {
        var temp;
        for(var i=0;i<filterMoviesList.length-1;i++)
        {
            for(var j=i+1;j<=filterMoviesList.length-1;j++)
            {
                console.log(filterMoviesList[i]+' '+filterMoviesList[j]);
                if(this.state.sortBy=='popularity')
                {
                    if(filterMoviesList[i].rating<filterMoviesList[j].rating)
                    {
                        temp=filterMoviesList[i];
                        filterMoviesList[i] = filterMoviesList[j];
                        filterMoviesList[j] = temp;
                    }

                }
                else if(this.state.sortBy=='name')
                {
                    if(filterMoviesList[i].name[0]<filterMoviesList[j].name[0])
                    {
                        temp=filterMoviesList[i];
                        filterMoviesList[i] = filterMoviesList[j];
                        filterMoviesList[j] = temp;
                    }
                    else if(filterMoviesList[i].name[0]==filterMoviesList[j].name[0] && filterMoviesList[i].name[1]<filterMoviesList[j].name[1]) 
                    {
                        temp=filterMoviesList[i];
                        filterMoviesList[i] = filterMoviesList[j];
                        filterMoviesList[j] = temp;
                    }
                }
            }
        }
    }

    render()
    {
        this.locBasedMovies = [];
        this.uniqueLanguages = [];

        return (<div className={"container"}>
                    <div className={"movielistheader"}>Movies Now in Theater</div>
                    <div className={"filter"}>
                        <div style={{"float":"left","margin":"5px"}}>
                            Sort by :
                            <select onChange={(e)=>this.setState({sortBy:e.target.value})} value={this.state.sortBy}>
                                    <option value='popularity'>Popularity</option>
                                    <option value='name'>Name</option>
                            </select>
                        </div>
                        <div style={{"float":"left","margin":"5px"}}>
                            Language : 
                            <select onChange={(e)=>this.setState({language:e.target.value})}>
                                <option value='select'>All</option>
                                {this.locBasedFilter()}
                            </select>
                        </div>
                        
                        <div style={{"float":"right","margin":"5px"}}>
                            <input type="text" ref={this.nameRef}></input>
                            <button onClick={(e)=>this.setState({search:e.target.value})}><img src='images/search.png' style={{"maxHeight":"24px"}}/></button>
                        </div>
                    </div>
                    <div className={"movielist"}>{this.filteredMovies()}</div>
                </div>);
    }

    componentDidMount=()=>
    {
        axios.get('http://localhost:3001/movies').then(res=>this.setState({movies:res.data})).catch(err=>console.log(err));
        
    }
}

export default MovieList;