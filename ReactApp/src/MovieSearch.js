import React, {useState,useEffect } from 'react';

function Movielist() {
    const yearRef=React.createRef();
    const [isApiHit,setIsApiHit] = useState(false);
    const [movieList, setMovieList] = useState([]);

    useEffect(() => { console.log('useeffect')},[movieList]);

    const search = () => {
        //console.log(yearRef.current.value);
        //console.log(movieList);
        //console.log(isApiHit);
        let lst = [];
        if (yearRef.current.value<2000)
            lst = [{ "Title": "Pulp fiction", "Rating": "8" }, { "Title": "Joker", "Rating": "7" }];
        setMovieList(lst);
        setIsApiHit(true);
        //console.log(movieList.length);
        //console.log(isApiHit);

    }

    const showAll = () => {
        let movieItems = [];
        movieList.forEach(ele => {
            movieItems.push(<option value={ele.Title}>{ele.Title}</option>) });
        return movieItems;
    }
    return (
        <div>
            <input type="text" ref={yearRef}/>
            <input type="button" value="Search" onClick={() => search()}></input>
            {(isApiHit && movieList.length > 0) ? <select>{showAll()}</select> : null}
            {(isApiHit && movieList.length=== 0) ? <h1>Nothing</h1> : null}
        </div>);
}
export default Movielist;