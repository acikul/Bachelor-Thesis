import { Component } from "react";
import GradService from "../services/GradService";

class AddSkladisteComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            nazivSkladiste: null,
            povrsina: null,
            adresaSkladiste: null,
            sifGrad: null,
            gradovi: []
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this._onSelect = this._onSelect.bind(this)
    }

    async componentDidMount() {

        let gradoviRes = await GradService.allGrads()
        this.setState({
            gradovi: gradoviRes
        })
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
        console.log(this.state)
    }

    _onSelect (e) {
        console.log('You selected ', e.target.value)
        this.setState(
            {
                sifGrad: e.target.value
            }
        )
        console.log(this.state)
      }

    async handleSubmit(e) {
        e.preventDefault()
        console.log('submit partner')

        const payload = {
            nazivPartner: this.state.nazivPartner,
            brTelPartner: this.state.brTelPartner,
            adresaSjedistePartner: this.state.adresaSjedistePartner,
            odgodaPartner: this.state.odgodaPartner,
            sifGrad: this.state.sifGrad
        }
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="col-md-4">
                    <br />
                <h3>Skladište</h3>
                    <table className="table">
                        <tbody>
                    <tr>
                        <td>                    
                        Naziv skladište:
                        </td>
                        <td>
                            <input type="text"
                               name="nazivSkladiste"
                               defaultValue={this.state.nazivSkladiste} 
                               onChange={this.handleChange}
                            />
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Površina (m2):
                    </td>
                    <td>
                    <input type="number"
                               name="povrsina"
                               defaultValue={this.state.povrsina} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Adresa skladišta:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="adresaSkladista"
                               defaultValue={this.state.adresaSkladiste} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
      
                    {/* <tr>
                    <td>
                        Šifra grada:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="sifGrad"
                               defaultValue={this.state.sifGrad} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr> */}

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

                    </tbody>
                    </table>
                    <button className="submit">Spremi</button>
                    </div>
                </form>
        )
    }
}

export default AddSkladisteComponent