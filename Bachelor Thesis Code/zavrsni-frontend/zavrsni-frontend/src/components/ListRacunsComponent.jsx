import { Component } from "react";
import RacunService from "../services/RacunService";


class ListRacunsComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            racuni: []
        }

        this.refreshRacuns = this.refreshRacuns.bind(this)
        this.detaljiClicked = this.detaljiClicked.bind(this)
        this.addRacunClicked = this.addRacunClicked.bind(this)
        this.deleteRacunClicked = this.deleteRacunClicked.bind(this)
    }

    componentDidMount() {
        this.refreshRacuns();
    }

    async refreshRacuns() {
        let response = await RacunService.allRacuns();
        this.setState({
            racuni: response
        })
    }

    addRacunClicked() {
        console.log('add racun clicked')
        this.props.history.push(`/addRacun`)
    }

    detaljiClicked(id) {
        console.log('detalji od ' + id + ' clicked')
        this.props.history.push(`/racun/${id}`)
    }

    async deleteRacunClicked(id) {
        let response = await RacunService.deleteRacun(id);
        if (response.status === 200) {
            this.refreshRacuns()
        }
    }

    render() {
        return (
            <div className="container">
                <br />
                <h3>Svi računi</h3>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Šifra</th>
                            <th>Datum</th>
                            <th>Valuta</th>
                            <th>Ukupna cijena</th>
                            <th>Plaćen</th>
                            <th>Detalji</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.racuni.map( racun => 
                                <tr key={racun.sifRacun}>
                                    <td>{racun.sifRacun}</td>
                                    <td>{racun.datumRacun}</td>
                                    <td>{racun.valutaRacun}</td>
                                    <td>{racun.ukupnaCijena}</td>
                                    <td>{(racun.placen) === 0 ? 'ne' : 'da'}</td>
                                    <td><button className="btn" onClick={() => this.detaljiClicked(racun.sifRacun)}>Detalji</button></td>
                                    <td><button className="btn btn-warning" onClick={() => this.deleteRacunClicked(racun.sifRacun)}>Delete</button></td>
                                </tr>
                                )
                        }
                    </tbody>
                </table>
                <div className="row">
                    <button className="btn btn-success" onClick={this.addRacunClicked}>Add</button>
                </div>
            </div>
        )
    }
}

export default ListRacunsComponent