import { Component } from "react";
import PrimkaService from "../services/PrimkaService";
import SkladisteService from "../services/SkladisteService";
import PoslovniParterService from "../services/PoslovniPartnerService"


class AddPrimkaComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            datumPrimka: null,
            valutaPrimka: null,
            sifSkladiste: null,
            sifPartner: null,
            skladista: [],
            partneri: []
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this._onSelectSkladiste = this._onSelectSkladiste.bind(this)
        this._onSelectPartner = this._onSelectPartner.bind(this)
    }

    async componentDidMount() {
        let sklRes = await SkladisteService.allSkladistes()
        let partRes = await PoslovniParterService.allPartners()
        this.setState({
            skladista: sklRes,
            partneri: partRes
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
      _onSelectPartner (e) {
        console.log('You selected ', e.target.value)
        this.setState(
            {
                sifPartner: e.target.value
            }
        )
        console.log(this.state)
      }

    async handleSubmit(e) {
        e.preventDefault()
        console.log('SUBMIT PRIMKA')

        const payload = {
            datumPrimka: this.state.datumPrimka,
            valutaPrimka: this.state.valutaPrimka,
            sifSkladiste: this.state.sifSkladiste,
            sifPartner: this.state.sifPartner
        }

        try {
            let response = await PrimkaService.createPrimka(payload)
            if (response.status === 200) {
                this.props.history.push(`/primka/${response.data.sifPrimka}`)
                alert('Uspjesno dodana primka')
            }
        } catch(err) {
            console.log(err)
            alert('Pogreska pri dodavanju primke')
        }
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="col-md-4">
                    <br />
                <h3>Primka</h3>
                    <table className="table">
                        <tbody>
                    <tr>
                        <td>                    
                        Datum:
                        </td>
                        <td>
                            <input type="date"
                               name="datumPrimka"
                               defaultValue={this.state.datumPrimka} 
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
                               name="valutaPrimka"
                               defaultValue={this.state.valutaPrimka} 
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
                        Partner:
                    </td>
                    <td>
                    <select onChange={this._onSelectPartner}>
                                <option value=""> </option>
                                {
                                    this.state.partneri.map ( partner => 
                                        <option value={partner.sifPartner} key={partner.sifPartner}>{partner.nazivPartner}</option>    
                                    )
                                }
                            </select>
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
export default AddPrimkaComponent