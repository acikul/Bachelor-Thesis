import { Component } from "react";
import PoslovniPartnerService from "../services/PoslovniPartnerService"
import RabatService from "../services/RabatService"

class PartnerRabatComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            nazivPartner: null,
            rabati: []
        }
    }

    async componentDidMount() {
        let id = this.props.match.params.id
        let partnerResponse = await PoslovniPartnerService.getPartner(id)
        let rabatiResponse = await RabatService.getRabatsForPartner(id)

        this.setState({
            nazivPartner: partnerResponse.nazivPartner,
            rabati: rabatiResponse
        })
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
                                <th>Šifra proizvod</th>
                                <th>Naziv</th>
                                <th>Iznos rabat(%)</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.rabati.map( rabat =>
                                    <tr key={rabat.proizvod.sifProizvod}>
                                        <td>{rabat.proizvod.sifProizvod}</td>
                                        <td>{rabat.proizvod.nazivProizvod}</td>
                                        <td>{rabat.iznosRabat}</td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default PartnerRabatComponent