import { Component } from "react";
import PoslovnicaService from "../services/PoslovnicaService";
import RacunService from "../services/RacunService";
import SkladisteService from "../services/SkladisteService";
import UserDataService from "../services/UserDataService";


class AddRacunComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            datumRacun: null,
            valutaRacun: null,
            placenRacun: null,
            sifSkladiste: null,
            sifPoslovnica: null,
            sifZaposlenik: null,
            skladista: [],
            poslovnice: []
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this._onSelectSkladiste = this._onSelectSkladiste.bind(this)
        this._onSelectPoslovnica = this._onSelectPoslovnica.bind(this)



    }

    async componentDidMount() {
        let sklRes = await SkladisteService.allSkladistes()
        let poslRes = await PoslovnicaService.allPoslovnicas()
        this.setState({
            skladista: sklRes,
            poslovnice: poslRes
        })

        let userData = await UserDataService.currentUser()
        this.setState({
            sifZaposlenik: userData.sifZaposlenik
        })
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        })
        console.log(this.state)
    }
    _onSelectSkladiste (e) {
        console.log('You selected ', e.target.value)
        this.setState(
            {
                sifSkladiste: e.target.value
            }
        )
        console.log(this.state)
      }
      _onSelectPoslovnica (e) {
        console.log('You selected ', e.target.value)
        this.setState(
            {
                sifPoslovnica: e.target.value
            }
        )
        console.log(this.state)
      }

    async handleSubmit(e) {
        e.preventDefault()
        console.log('SUBMIT RACUN')

        const payload = {
            datumRacun: this.state.datumRacun,
            valutaRacun: this.state.valutaRacun,
            placenRacun: this.state.placenRacun,
            sifSkladiste: this.state.sifSkladiste,
            sifPoslovnica: this.state.sifPoslovnica,
            sifZaposlenik: this.state.sifZaposlenik
        }

        try {
            let response = await RacunService.createRacun(payload)
            if (response.status === 200) {
                this.props.history.push(`/racun/${response.data.sifRacun}`)
                alert('Uspjesno dodan racun')
            }
        } catch(err) {
            console.log(err)
            alert('Pogreska pri dodavanju racuna')
        }
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="col-md-4">
                    <br />
                <h3>Račun</h3>
                    <table className="table">
                        <tbody>
                    <tr>
                        <td>                    
                        Datum:
                        </td>
                        <td>
                            <input type="date"
                               name="datumRacun"
                               defaultValue={this.state.datumRacun} 
                               onChange={this.handleChange}
                            />
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Valuta:
                    </td>
                    <td>
                    <input type="text"
                               name="valutaRacun"
                               defaultValue={this.state.valutaRacun} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Plaćen:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="placenRacun"
                               defaultValue={this.state.placenRacun} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Skladište:
                    </td>
                    <td>
                    <select onChange={this._onSelectSkladiste}>
                                <option value=""> </option>
                                {
                                    this.state.skladista.map ( skladiste => 
                                        <option value={skladiste.sifSkladiste} key={skladiste.sifSkladiste}>{skladiste.nazivSkladiste}</option>    
                                    )
                                }
                            </select>
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Poslovnica
                    </td>
                    <td>
                    <select onChange={this._onSelectPoslovnica}>
                                <option value=""> </option>
                                {
                                    this.state.poslovnice.map ( poslovnica => 
                                        <option value={poslovnica.sifPoslovnica} key={poslovnica.sifPoslovnica}>{poslovnica.nazivPoslovnica}</option>    
                                    )
                                }
                            </select>
                    </td>
                    </tr>
                    {/* <tr>
                    <td>
                        Šifra poslovnice:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="sifPoslovnica"
                               defaultValue={this.state.sifPoslovnica} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr> */}
                    <tr>
                    <td>
                        Šifra zaposlenika:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="sifZaposlenik"
                               defaultValue={this.state.sifZaposlenik} 
                               onChange={this.handleChange}
                               disabled
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
export default AddRacunComponent