import { Component } from "react";
import { Link } from "react-router-dom";
import UserDataService from "../services/UserDataService";

class ProfilComponent extends Component {

    constructor (props) {
        super(props)
        this.state = {
            editButton: null,
            userData: null
        }
        this.editProfilClicked = this.editProfilClicked.bind(this)
    }

    // async componentDidUpdate() {
    //     let userData = await CurrentUserDataService()
    //     if (userData) {
    //         console.log("ProfilCmpDidUPDATE")
    //         console.log(userData)
    //         this.setState({
    //             userData: userData
    //         })
    //     }
    // }

    async componentDidMount() {
        let userData = await UserDataService.currentUser()
        if (userData) {
            console.log("ProfilCmpDidMnt")
            console.log(userData)
            this.setState({
                userData: userData
            })
        }

        this.setState({
            editButton:

            
                <button className="btn">
                <Link to={{
                    pathname: '/editProfil',
                    state: {
                        sifZaposlenik: this.state.userData.sifZaposlenik,
                        oibZaposlenik: this.state.userData.oibZaposlenik,
                        imeZaposlenik: this.state.userData.imeZaposlenik,
                        prezimeZaposlenik: this.state.userData.prezimeZaposlenik,
                        emailZaposlenik: this.state.userData.emailZaposlenik,
                        brojTelZaposlenik: this.state.userData.brojTelZaposlenik,
                        datumRodenja: this.state.userData.datumRodenja,
                        datumPocetka: this.state.userData.datumPocetka,
                        datumKraj: this.state.userData.datumKraj,
                        password: null,
                        sifRadnoMjesto: this.state.userData.radnoMj.sifRadnoMjesto,
                        sifGrad: this.state.userData.grad.sifGrad,
                        adresaZaposlenik: this.state.userData.adresaZaposlenik
                    }
                }}>
                    Promijeni osobne podatke
                </Link>
                </button>
        })
    }

    editProfilClicked() {
        this.props.history.push('/editProfil')
    }

    render() {
        if (this.state.userData) {
            return (
                <div className="col-md-6">
                    <br />
                    <table className="table">
                        <tbody>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">OIB</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.oibZaposlenik}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Ime</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.imeZaposlenik}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Prezime</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.prezimeZaposlenik}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Email</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.emailZaposlenik}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Broj telefona</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.brojTelZaposlenik}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Datum rođenja</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.datumRodenja}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Datum početka rada</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.datumPocetka}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Datum kraja rada</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.datumKraj}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Naziv radnog mjesta</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.radnoMj.nazivRadnoMjesto}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Grad</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.grad.nazivGrad}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Adresa</span>
                                    </strong>
                                </td>
                                <td>{this.state.userData.adresaZaposlenik}</td>
                            </tr>
                        </tbody>
                    </table>
                    {
                        (this.state.role === "admin") &&
                        <button className="btn" onClick={this.editProfilClicked}>Izmijeni osobne podatke</button>

                        
                    }
                </div>
            )
        } else {
            return (
                <>
                </>
                // <p>Dohvaćanje podataka...</p>
            )
        }
    }
}

export default ProfilComponent