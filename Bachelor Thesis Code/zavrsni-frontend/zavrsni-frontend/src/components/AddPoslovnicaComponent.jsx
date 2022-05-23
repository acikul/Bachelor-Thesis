import { Component } from "react";
import PoslovnicaService from "../services/PoslovnicaService";

class AddPoslovnicaComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            nazivPoslovnica: null,
            brTelPoslovnica: null,
            adresaPoslovnica: null,
            sifGrad: null
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
    }

    async componentDidMount() {
        
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        })
        console.log(this.state)
    }

    async handleSubmit(e) {
        e.preventDefault()
        console.log('SUBMIT poslovnica')

        const payload = {
            nazivPoslovnica: this.state.nazivPoslovnica,
            brTelPoslovnica: this.state.brTelPoslovnica,
            adresaPoslovnica: this.state.adresaPoslovnica,
            sifGrad: this.state.sifGrad,
            sifPartner: this.props.match.params.id
        }

        try {
            let response = await PoslovnicaService.createPoslovnica(payload)
            if (response.status === 200) {
                this.props.history.push(`/partner/${this.props.match.params.id}/poslovnice`)
                alert('Uspjesno dodana poslovnica')
            }
        } catch(err) {
            console.log(err)
            alert('Pogreska pri dodavanju poslovnice')
        }
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="col-md-4">
                    <br />
                <h3>Poslovnica</h3>
                    <table className="table">
                        <tbody>
                    <tr>
                        <td>                    
                        Naziv:
                        </td>
                        <td>
                            <input type="text"
                               name="nazivPoslovnica"
                               defaultValue={this.state.nazivPoslovnica} 
                               onChange={this.handleChange}
                            />
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Broj telefona:
                    </td>
                    <td>
                    <input type="text"
                               name="brTelPoslovnica"
                               defaultValue={this.state.brTelPoslovnica} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Adresa poslovnice:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="adresaPoslovnica"
                               defaultValue={this.state.adresaPoslovnica} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
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
                    </tr>
                    </tbody>
                    </table>
                    <button className="submit">Spremi</button>
                    </div>
                </form>
        )
    }
}

export default AddPoslovnicaComponent