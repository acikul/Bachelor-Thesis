import { Component } from "react";
import PoslovniPartnerService from "../services/PoslovniPartnerService";
import GradService from "../services/GradService";

class AddEditPartnerComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            sifPartner: null,
            nazivPartner: null,
            brTelPartner: null,
            adresaSjedistePartner: null,
            odgodaPartner: null,
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

        if (this.props.location.pathname === '/addPartner') {
            console.log("addPartner")
            return
        }

        let id = this.props.match.params.id

        let partner = await PoslovniPartnerService.getPartner(id)
        if (partner) {
            console.log(partner)
            this.setState({
                sifPartner: partner.sifPartner,
                nazivPartner: partner.nazivPartner,
                brTelPartner: partner.brTelPartner,
                adresaSjedistePartner: partner.adresaSjedistePartner,
                odgodaPartner: partner.odgodaPartner,
                sifGrad: partner.grad.sifGrad
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

        if (this.props.location.pathname === '/addPartner') {
            try {
                let response = await PoslovniPartnerService.createPartner(payload)

                if (response.status === 200) {
                    this.props.history.push(`/sviPartneri`)
                    alert('Uspješno dodan partner!')
                }
            } catch (error) {
                console.log(error)
                alert('Pogreška pri dodavanju partnera!')
            }
            return
        }

        try {
            let response = await PoslovniPartnerService.updatePartner(this.state.sifPartner, payload)

            if (response.status === 200) {
                this.props.history.push(`/sviPartneri`)
                alert('Uspješno izmijenjen partner!')
            }
        } catch (error) {
            console.log(error)
            alert('Pogreška pri izmjeni partnera!')
        }
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="col-md-4">
                    <br />
                <h3>Poslovni partner</h3>
                    <table className="table">
                        <tbody>
                    <tr>
                        <td>                    
                        Naziv partner:
                        </td>
                        <td>
                            <input type="text"
                               name="nazivPartner"
                               defaultValue={this.state.nazivPartner} 
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
                               name="brTelPartner"
                               defaultValue={this.state.brTelPartner} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Adresa sjedišta:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="adresaSjedistePartner"
                               defaultValue={this.state.adresaSjedistePartner} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Odgoda:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="odgodaPartner"
                               defaultValue={this.state.odgodaPartner} 
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

export default AddEditPartnerComponent