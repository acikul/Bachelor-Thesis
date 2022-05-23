import { Component } from "react"
import ImaNaStanjuService from "../services/ImaNaStanjuService"
import SkladisteService from "../services/SkladisteService"


class ImaNaStanjuComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            nazivSkladiste: null,
            proizvodStanja: []
        }
    }

    async componentDidMount() {
        let id = this.props.match.params.id
        let skladisteResponse = await SkladisteService.getSkladiste(id);
        let stanjaResponse = await ImaNaStanjuService.getStanjeSkladiste(id);

        this.setState({
            nazivSkladiste: skladisteResponse.nazivSkladiste,
            proizvodStanja: stanjaResponse
        })
    }

    render() {
        return (
            <div>
                <br />
                <div className="container">
                <h3>Skladište: {this.state.nazivSkladiste}</h3>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Šifra</th>
                                <th>Naziv</th>
                                <th>Stanje</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.proizvodStanja.map( proizvodStanje =>
                                    <tr key={proizvodStanje.proizvod.sifProizvod}>
                                        <td>{proizvodStanje.proizvod.sifProizvod}</td>
                                        <td>{proizvodStanje.proizvod.nazivProizvod}</td>
                                        <td>{proizvodStanje.kolicinaStanje}</td>
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
export default ImaNaStanjuComponent