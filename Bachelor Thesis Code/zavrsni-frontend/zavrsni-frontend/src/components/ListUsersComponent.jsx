import { Component } from "react";
import UserDataService from "../services/UserDataService";


class ListUsersComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            users: [],
            role: null
        }

        this.deleteUserClicked = this.deleteUserClicked.bind(this)
        this.updateUserClicked = this.updateUserClicked.bind(this)
        this.addUserClicked = this.addUserClicked.bind(this)
        this.refreshUsers = this.refreshUsers.bind(this)
    }

    async componentDidMount() {
        let resMe = await UserDataService.currentUser()
        this.setState({
            role: resMe.radnoMj.nazivRadnoMjesto
        })
        this.refreshUsers();
    }

    async refreshUsers() {
        let response = await UserDataService.allUsers()
        this.setState({
            users: response
        })
    }

    async deleteUserClicked(id) {
        let response = await UserDataService.deleteUser(id)
        console.log(response.status)
        if (response.status === 200) {
            this.refreshUsers()
        }
    }

    updateUserClicked(id) {
        console.log('update ' + id)
        this.props.history.push(`/zaposlenik/${id}`)
    }

    addUserClicked() {
        console.log("add zaposlenik")
        this.props.history.push(`/addZaposlenik`)
    }

    render() {
        return ( 
            <div>
                <br />
                <div className="container">
                <h3>Svi zaposlenici</h3>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Šifra</th>
                                <th>Ime</th>
                                <th>Prezime</th>
                                <th>Email</th>
                                {
                                    (this.state.role === "admin") &&
                                    <th></th>                        
                                }
                                {
                                    (this.state.role === "admin") &&
                                    <th></th>                        
                                }
                                
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.map( user =>
                                    <tr key={user.sifZaposlenik}>
                                        <td>{user.sifZaposlenik}</td>
                                        <td>{user.imeZaposlenik}</td>
                                        <td>{user.prezimeZaposlenik}</td>
                                        <td>{user.emailZaposlenik}</td>
                                        {
                                            (this.state.role === "admin") &&
                                            <td><button className="btn" onClick={() => this.updateUserClicked(user.sifZaposlenik)}>Edit</button></td>
                                        }
                                        {
                                            (this.state.role === "admin") &&
                                            <td><button className="btn btn-warning" onClick={() => this.deleteUserClicked(user.sifZaposlenik)}>Delete</button></td>

                                        }
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                    {
                        (this.state.role === "admin") &&
                            <div className="row">
                            <button className="btn btn-success" onClick={this.addUserClicked}>Add</button>
                            </div>
                        
                    }
                    
                </div>
            </div>
        )
    }
}

export default ListUsersComponent