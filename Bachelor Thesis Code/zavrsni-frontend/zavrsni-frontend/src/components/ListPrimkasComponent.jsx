import { Component } from "react";
import PrimkaService from "../services/PrimkaService";

class ListPrimkasComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            primke: []
        }

        this.refreshPrimke = this.refreshPrimke.bind(this)
        this.detaljiPrimkaClicked = this.detaljiPrimkaClicked.bind(this)
        this.addPrimkaClicked = this.addPrimkaClicked.bind(this)
        this.deletePrimkaClicked = this.deletePrimkaClicked.bind(this)
    }

    componentDidMount() {
        this.refreshPrimke();
    }

    async refreshPrimke() {
        let response = await PrimkaService.allPrimkas();
        this.setState({
            primke: response
        })
    }

    addPrimkaClicked(){
        console.log('add primka clicked')
        this.props.history.push(`/addPrimka`)
    }

    detaljiPrimkaClicked(id) {
        console.log('detalji od primka ' + id + ' clicked')
        this.props.history.push(`/primka/${id}`)
    }

    async deletePrimkaClicked(id) {
        let response = await PrimkaService.deletePrimka(id);
        if (response.status === 200) {
            this.refreshPrimke()
        }
    }

    render() {
        return (
            <div className="container">
                <br />
                <h3>Sve primke</h3>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Šifra</th>
                            <th>Datum</th>
                            <th>Valuta</th>
                            <th>Detalji</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.primke.map( primka => 
                                <tr key={primka.sifPrimka}>
                                    <td>{primka.sifPrimka}</td>
                                    <td>{primka.datumPrimka}</td>
                                    <td>{primka.valutaPrimka}</td>
                                    <td><button className="btn" onClick={() => this.detaljiPrimkaClicked(primka.sifPrimka)}>Detalji</button></td>
                                    <td><button className="btn btn-warning" onClick={() => this.deletePrimkaClicked(primka.sifPrimka)}>Delete</button></td>
                                </tr>
                                )
                        }
                    </tbody>
                </table>
                <div className="row">
                    <button className="btn btn-success" onClick={this.addPrimkaClicked}>Add</button>
                </div>
            </div>
        )
    }

}

export default ListPrimkasComponent