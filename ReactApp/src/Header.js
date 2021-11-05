import React from 'react';
import location from './location.jpg';
import './Header.css';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';

class Header extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {appname:'Infy Movies',location:'chennai'};

    }
    updateloc=(e)=>
    {
        this.setState({location:e.target.value});
        this.props.prop.updateLocation(e.target.value);

    }

    render()
    {
        const welcomMsg=()=>{
            if(this.props.prop.isValidUser)
                return (<div className='welcomemsg'>
                    Welcome {this.props.prop.username.toUpperCase()}   
                    <Button type='submit' onClick={this.props.prop.logout}>Logout</Button>
                    </div>);
        }
        return (<div className="header">
            <div className="title">{this.state.appname}</div>
            <div className='headerright'>
            <select id="loc" className="locdrop" value={this.state.location} onChange={this.updateloc}>  
                <option value="null">Select</option>
                <option value="chennai">Chennai</option>
                <option value="bengaluru">Bengaluru</option>
                <option value="chandigarh">Chandigarh</option>
            </select>
            <img src={location} className="loclogo"/>
            {welcomMsg()}
            </div>
            
        </div>);
    }
}

export default Header;