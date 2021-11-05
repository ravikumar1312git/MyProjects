import React from 'react';
import './Login.css';
import Axios from 'axios';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';

class Login extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state={username:'ravi',password:'t1rk',users:[],isValidUser:false};
    }

    getInput=(e)=>
    {
       if(e.target.name=='username')
            this.setState({username:e.target.value});
        else if (e.target.name=='password')
            this.setState({password:e.target.value});
    }

    submit=()=>
    {
        let isValidUser = false;
        this.state.users.forEach(user=>{
            if(this.state.username == user.username && this.state.password == user.password)
            {
                isValidUser = true;
            }
        });
        
        if(isValidUser)
            this.setState({isValidUser:true});
        else
            this.setState({isValidUser:false});
        this.props.prop(isValidUser,this.state.username);
    }

    componentDidMount()
    {
        Axios.get("http://localhost:3001/users").then(result=>this.setState({users:result.data})).catch(err=>console.log(err));
    }
    render()
    {
        return (<div className='panel'>
            <div>Login</div>
            <div style={{"textAlign":"center"}}>
                <p>InfyMovies is an online application where you can book tickets for your favorite movie. Please login to the application</p>
                Username : <input name='username' type='text' value={this.state.username} onChange={this.getInput}></input><br/>
                Password : <input name='password' type='password' value={this.state.password} onChange={this.getInput}></input><br/>
                <Button type='submit' onClick={this.submit}>Submit</Button>

            </div>
        </div>)
    }
}

export default Login;