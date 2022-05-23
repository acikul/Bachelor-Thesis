import { Component } from "react"
import PoslovniPartnerService from "../services/PoslovniPartnerService"
import PoslovnicaService from "../services/PoslovnicaService"
import UserDataService from "../services/UserDataService"

class PartnerPoslovniceComponent extends Component {
    
    constructor(props) {
        super(props)
        this.state = {
            nazivPartner: null,
            poslovnice: [],
            role: null
        }
        this.refreshPoslovnicas = this.refreshPoslovnicas.bind(this)
        this.addPoslovnicaClicked = this.addPoslovnicaClicked.bind(this)
        this.deletePoslovnicaClicked = this.deletePoslovnicaClicked.bind(this)
    }

    async componentDidMount() {
        let resMe = await UserDataService.currentUser()
        this.setState({
            role: resMe.radnoMj.nazivRadnoMjesto
        })

        this.refreshPoslovnicas();
    }

    async refreshPoslovnicas() {
        let id = this.props.match.params.id
        let partnerResponse = await PoslovniPartnerService.getPartner(id)

        this.setState({
            nazivPartner: partnerResponse.nazivPartner,
            poslovnice: partnerResponse.poslovnicas
        })
    }

    async deletePoslovnicaClicked(id) {
        let response = await PoslovnicaService.deletePoslovnica(id)
        console.log('deletePoslovnicaClicked got status: ' + response.status)
        if (response.status === 200) {
            this.refreshPoslovnicas()
        }
    }

    addPoslovnicaClicked() {
        console.log('add poslovnica clicked')
        this.props.history.push(`/addPoslovnica/partner/${this.props.match.params.id}`)
    }

    render() {
        return(
            <div>
                <br />
                <div className="container">
                <h3>Poslovni partner: {this.state.nazivPartner}</h3>
                <br />
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Šifra</th>
                                <th>Naziv</th>
                                <th>Broj tel.</th>
                                <th>Adresa</th>
                                {
                                (this.state.role === "admin" || this.state.role === "direktor") &&
                                <th></th>
                                }
                                
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.poslovnice.map( poslovnica =>
                                    <tr key={poslovnica.sifPoslovnica}>
                                        <td>{poslovnica.sifPoslovnica}</td>
                                        <td>{poslovnica.nazivPoslovnica}</td>
                                        <td>{poslovnica.brTelPoslovnica}</td>
                                        <td>{poslovnica.adresaPoslovnica}, {poslovnica.grad.nazivGrad}</td>
                                        {
                                        (this.state.role === "admin" || this.state.role === "direktor") &&
                                        <td><button className="btn btn-warning" onClick={() => this.deletePoslovnicaClicked(poslovnica.sifPoslovnica)}>Delete</button></td>
                                    }
                                        

                                    </tr>
                                )
                            }
                        </tbody>
                    </table>

                    {
                        (this.state.role === "admin" || this.state.role === "direktor") &&
                        <div className="row">
                        <button className="btn btn-success" onClick={this.addPoslovnicaClicked}>Add</button>
                        </div>                                    
                    }

                    
                </div>
            </div>
        )
    }

}

export default PartnerPoslovniceComponent