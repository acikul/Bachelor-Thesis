import React, {Component} from 'react'
import AuthService from '../services/AuthService';

class LoginComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            email: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
        // this.loginClicked = this.loginClicked.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(event) {
        this.setState( 
            {
                [event.target.name] : event.target.value
            }
        )
    }

    handleSubmit = e => {
        e.preventDefault()

        AuthService.executeAuthService(this.state.email, this.state.password)
        .then((response) => {
            this.setState({showSuccessMessage: true})
            this.setState({hasLoginFailed: false})
            AuthService.successfulJwtLogin(this.state.email, response.data)
            this.props.history.push(`/`)
            alert("Uspješna prijava!")
        }).catch((err) => {
                        console.log(err)
                        this.setState({showSuccessMessage: false})
                        this.setState({hasLoginFailed: true})
                        }
                )
    }

    render() {
        return (
            <div>
                <div className="container">
                <h1>Prijava</h1>
                    <form onSubmit={this.handleSubmit}>
                        
                        <input placeholder="email" type="text" name="email" value={this.state.email} onChange={this.handleChange} />
                        <span> </span>
                        <input placeholder="password" type="password" name="password" value={this.state.password} onChange={this.handleChange} />
                        <span> </span>
                        <button className="btn btn-success">Prijava</button>
                    </form>
                    {this.state.hasLoginFailed && <div className="alert alert-warning">Pogreška pri prijavi!</div>}
                    {this.state.showSuccessMessage && <div>Uspješna prijava!</div>}
                </div>
            </div>
        )
    }
}

export default LoginComponent