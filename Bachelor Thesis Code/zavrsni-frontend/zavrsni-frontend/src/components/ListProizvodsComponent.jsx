import { Component } from "react";
import ProizvodService from "../services/ProizvodService";
import UserDataService from "../services/UserDataService";

class ListProizvodsComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            proizvodi: [],
            role: null
        }

        this.deleteProizvodClicked = this.deleteProizvodClicked.bind(this)
        this.updateProizvodClicked = this.updateProizvodClicked.bind(this)
        this.addProizvodClicked = this.addProizvodClicked.bind(this)
        this.refreshProizvodi = this.refreshProizvodi.bind(this) 
    }

    async componentDidMount() {
        let resMe = await UserDataService.currentUser()
        this.setState({
            role: resMe.radnoMj.nazivRadnoMjesto
        })
        this.refreshProizvodi();
    }

    async refreshProizvodi() {
        let response = await ProizvodService.allProizvods();
        this.setState({
            proizvodi: response
        })
    }

    async deleteProizvodClicked(id) {
        let response = await ProizvodService.deleteProizvod(id)
        console.log('deleteProizvodClicked got status: ' + response.status)
        if (response.status === 200) {
            this.refreshProizvodi()
        }
    }

    updateProizvodClicked(id) {
        console.log('update ' + id)
        this.props.history.push(`/proizvod/${id}`)
    }

    addProizvodClicked() {
        console.log('add proizvod clicked')
        this.props.history.push(`/addProizvod`)
    }


    render() {
        return (
            <div className="container">
                <br />
                <h3>Svi proizvodi</h3>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Šifra</th>
                            <th>Naziv</th>
                            <th>Mjera</th>
                            <th>Količina/kutija</th>
                            <th>Masa</th>
                            <th>Cijena</th>
                            {
                                    (this.state.role === "admin" || this.state.role === "direktor") &&
                                    <th></th>                        
                            }
                            {
                                    (this.state.role === "admin" || this.state.role === "direktor") &&
                                    <th></th>                        
                            }
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.proizvodi.map( proizvod => 
                                <tr key={proizvod.sifProizvod}>
                                    <td>{proizvod.sifProizvod}</td>
                                    <td>{proizvod.nazivProizvod}</td>
                                    <td>{proizvod.mjeraProizvod}</td>
                                    <td>{proizvod.kolicinaKutijaProizvod}</td>
                                    <td>{proizvod.masaProizvod}</td>
                                    <td>{proizvod.cijenaProizvod}</td>
                                    {
                                        (this.state.role === "admin" || this.state.role === "direktor") &&
                                        <td><button className="btn" onClick={() => this.updateProizvodClicked(proizvod.sifProizvod)}>Edit</button></td>
                                    }
                                    {
                                        (this.state.role === "admin" || this.state.role === "direktor") &&
                                        <td><button className="btn btn-warning" onClick={() => this.deleteProizvodClicked(proizvod.sifProizvod)}>Delete</button></td>
                                    }
                                </tr>
                                )
                        }
                    </tbody>
                </table>
                <div className="row">
                {
                    (this.state.role === "admin" || this.state.role === "direktor") &&
                    <button className="btn btn-success" onClick={this.addProizvodClicked}>Add</button>
                }
                </div>
            </div>
        )
    }
}

export default ListProizvodsComponent