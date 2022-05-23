import { Component } from "react";
import AuthService from "../services/AuthService";
import UserDataService from "../services/UserDataService";

class HomeComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            ime: null,
            prezime: null
        }
    }

    async componentDidMount() {
        const isUserLoggedIn = AuthService.isUserLoggedIn();
        if (isUserLoggedIn) {
            let resMe = await UserDataService.currentUser()
            this.setState({
                ime: resMe.imeZaposlenik,
                prezime: resMe.prezimeZaposlenik
            })
        }
        
    }

    render() {
        const isUserLoggedIn = AuthService.isUserLoggedIn();

        return (
            <div className="container">
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                {
                    (isUserLoggedIn) &&
                    <h3>Welcome, {this.state.ime} {this.state.prezime}</h3>
                }
                {
                    (!isUserLoggedIn) &&
                    <h3>Please, log in</h3>
                }
                
            </div>
        )
    }
}

export default HomeComponent;