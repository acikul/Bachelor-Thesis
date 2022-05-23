import { Component } from "react";
import GradService from "../services/GradService";
import RadnoMjestoService from "../services/RadnoMjestoService";
import UserDataService from "../services/UserDataService";

class ZaposlenikComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            sif: null,
            oibZaposlenik: null,
            imeZaposlenik: null,
            prezimeZaposlenik: null,
            emailZaposlenik: null,
            brojTelZaposlenik: null,
            datumRodenja: null,
            datumPocetka: null,
            datumKraj: null,
            password: null,
            sifRadnoMjesto: null,
            sifGrad: null,
            adresaZaposlenik: null,
            gradovi: [],
            radnaMjesta: []
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)

        this._onSelectGrad = this._onSelectGrad.bind(this)
        this._onSelectRadMjest = this._onSelectRadMjest.bind(this)

    }

    async componentDidMount() {

        let gradoviRes = await GradService.allGrads()
        let radnMjestRes = await RadnoMjestoService.allRadMjest()

        this.setState({
            gradovi: gradoviRes,
            radnaMjesta: radnMjestRes
        }) 

        if (this.props.location.pathname === '/addZaposlenik') {
            console.log("addZaposlenik")
            return
        }

        if (this.props.location.pathname === '/editProfil') {
            console.log("editProfil")
            let userData = await UserDataService.currentUser()
            if (userData) {
                console.log(userData)
                this.setState({
                    sif: userData.sifZaposlenik,
                    oibZaposlenik: userData.oibZaposlenik,
                    imeZaposlenik: userData.imeZaposlenik,
                    prezimeZaposlenik: userData.prezimeZaposlenik,
                    emailZaposlenik: userData.emailZaposlenik,
                    brojTelZaposlenik: userData.brojTelZaposlenik,
                    datumRodenja: userData.datumRodenja,
                    datumPocetka: userData.datumPocetka,
                    datumKraj: userData.datumKraj,
                    password: null,
                    sifRadnoMjesto: userData.radnoMj.sifRadnoMjesto,
                    sifGrad: userData.grad.sifGrad,
                    adresaZaposlenik: userData.adresaZaposlenik
                })
            }
            return
        }

        let id = this.props.match.params.id

        let userData = await UserDataService.getUser(id)
        if (userData) {
            console.log(userData)
            this.setState({
                sif: userData.sifZaposlenik,
                oibZaposlenik: userData.oibZaposlenik,
                imeZaposlenik: userData.imeZaposlenik,
                prezimeZaposlenik: userData.prezimeZaposlenik,
                emailZaposlenik: userData.emailZaposlenik,
                brojTelZaposlenik: userData.brojTelZaposlenik,
                datumRodenja: userData.datumRodenja,
                datumPocetka: userData.datumPocetka,
                datumKraj: userData.datumKraj,
                password: null,
                sifRadnoMjesto: userData.radnoMj.sifRadnoMjesto,
                sifGrad: userData.grad.sifGrad,
                adresaZaposlenik: userData.adresaZaposlenik
            })
        }
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
        console.log(this.state)
    }

    _onSelectGrad (e) {
        console.log('You selected ', e.target.value)
        this.setState(
            {
                sifGrad: e.target.value
            }
        )
        console.log(this.state)
      }
      _onSelectRadMjest (e) {
        console.log('You selected ', e.target.value)
        this.setState(
            {
                sifRadnoMjesto: e.target.value
            }
        )
        console.log(this.state)
      }

    async handleSubmit(e) {
        e.preventDefault()
        console.log("SUBMIT ZAPOSLENIK")

        const payload = {
            oib: this.state.oibZaposlenik,
            ime: this.state.imeZaposlenik,
            prezime: this.state.prezimeZaposlenik,
            email: this.state.emailZaposlenik,
            brojTel: this.state.brojTelZaposlenik,
            datumRod: this.state.datumRodenja,
            datumPoc: this.state.datumPocetka,
            datumKraj: this.state.datumKraj,
            password: this.state.password,
            sifRadnoMjesto: this.state.sifRadnoMjesto,
            sifGrad: this.state.sifGrad,
            adresa: this.state.adresaZaposlenik
        }

        if (this.props.location.pathname === '/addZaposlenik') {
            try {
                let response = await UserDataService.createUser(payload)
                // let response = await axios.put(`/zaposlenik/` + this.state.sifZaposlenik, payload)
                if (response.status === 200) {
    
                    this.props.history.push(`/sviZaposlenici`)
                    alert('Uspješno dodan zaposlenik!')
                }
            } catch(error) {
                console.log(error)
                alert('Pogreška pri dodavanju zaposlenika!')
            }
            return
        }

        try {
            let response = await UserDataService.updateUser(this.state.sif, payload)
            // let response = await axios.put(`/zaposlenik/` + this.state.sifZaposlenik, payload)
            if (response.status === 200) {

                if (this.props.location.pathname === '/editProfil') {
                    this.props.history.push(`/profil`)
                } else {
                    this.props.history.push('/sviZaposlenici')
                }
                alert('Uspješna izmjena!')
            }
        } catch(error) {
            console.log(error)
            alert('Pogreška pri izmjeni!')
        }
    }
    

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="col-md-4">
                    <br />
                <h3>Zaposlenik</h3>
                    <table className="table">
                        <tbody>
                    <tr>
                        <td>                    
                        OIB:
                        </td>
                        <td>
                            <input type="text"
                               name="oibZaposlenik"
                               defaultValue={this.state.oibZaposlenik} 
                               onChange={this.handleChange}
                            />
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Ime:
                    </td>
                    <td>
                    <input type="text"
                               name="imeZaposlenik"
                               defaultValue={this.state.imeZaposlenik} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Prezime:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="prezimeZaposlenik"
                               defaultValue={this.state.prezimeZaposlenik} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Email:
                        
                    </td>
                    <td>
                    <input type="email"
                               name="emailZaposlenik"
                               defaultValue={this.state.emailZaposlenik} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Tel:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="brojTelZaposlenik"
                               defaultValue={this.state.brojTelZaposlenik} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Datum rođenja:
                        
                    </td>
                    <td>
                    <input type="date"
                               name="datumRodenja"
                               defaultValue={(this.state.datumRodenja)} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Datum početka rada:
                        
                    </td>
                    <td>
                    <input type="date"
                               name="datumPocetka"
                               defaultValue={this.state.datumPocetka} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Datum kraja rada:
                        
                    </td>
                    <td>
                    <input type="date"
                               name="datumKraj"
                               defaultValue={this.state.datumKraj} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Novi password:
                        
                    </td>
                    <td>
                    <input type="password"
                               name="password"
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    {/* <td>
                        Šifra radnog mjesta:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="sifRadnoMjesto"
                               defaultValue={this.state.sifRadnoMjesto} 
                               onChange={this.handleChange}
                        />
                    </td> */}
                    <td>Radno mjesto</td>
                        <td>
                            <select onChange={this._onSelect}>
                                <option value=""> </option>
                                {
                                    this.state.radnaMjesta.map ( radMj => 
                                        <option value={radMj.sifRadnoMjesto} key={radMj.sifRadnoMjesto}>{radMj.nazivRadnoMjesto}</option>    
                                    )
                                }
                            </select>
                        </td>
                    </tr>
                    <tr>
                    <td>Grad</td>
                        <td>
                            <select onChange={this._onSelect}>
                                <option value=""> </option>
                                {
                                    this.state.gradovi.map ( grad => 
                                        <option value={grad.sifGrad} key={grad.sifGrad}>{grad.nazivGrad}</option>    
                                    )
                                }
                            </select>
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Adresa:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="adresaZaposlenik"
                               defaultValue={this.state.adresaZaposlenik} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    
                    </tbody>
                    </table>
                    <button className="submit">Spremi</button>
                    </div>
                </form>
        )
    }
}

export default ZaposlenikComponent