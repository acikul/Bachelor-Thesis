import { Component } from "react";
import SkladisteService from "../services/SkladisteService";
import UserDataService from "../services/UserDataService";


class ListSkladistesComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            skladista: [],
            role: null
        }

        this.stanjeClicked = this.stanjeClicked.bind(this)
        this.addSkladisteClicked = this.addSkladisteClicked.bind(this)
    }

    async componentDidMount() {
        let response = await SkladisteService.allSkladistes()
        let resMe = await UserDataService.currentUser()
        this.setState({
            skladista: response,
            role: resMe.radnoMj.nazivRadnoMjesto
        })
    }

    stanjeClicked(id) {
        console.log('stanje od skladista: ' + id + ' clicked')
        this.props.history.push(`/skladiste/stanje/${id}`)
    }

    addSkladisteClicked() {
        console.log('add skladiste clicked')
        this.props.history.push(`/addSkladiste`)
    }

    render() {
        return (
            <div>
                <br />
                <div className="container">
                <h3>Sva skladišta</h3>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Šifra</th>
                                <th>Naziv</th>
                                <th>Adresa</th>
                                <th>Stanje</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.skladista.map( skladiste =>
                                    <tr key={skladiste.sifSkladiste}>
                                        <td>{skladiste.sifSkladiste}</td>
                                        <td>{skladiste.nazivSkladiste}</td>
                                        <td>{skladiste.adresaSkladiste}</td>
                                        <td><button className="btn" onClick={() => this.stanjeClicked(skladiste.sifSkladiste)}>Stanje</button></td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>

                    {
                        (this.state.role === "admin" || this.state.role === "direktor") &&
                            <div className="row">
                                <button className="btn btn-success" onClick={this.addSkladisteClicked}>Add</button>
                            </div>                    
                    }

                    
                </div>
            </div>

        )
    }


}

export default ListSkladistesComponent