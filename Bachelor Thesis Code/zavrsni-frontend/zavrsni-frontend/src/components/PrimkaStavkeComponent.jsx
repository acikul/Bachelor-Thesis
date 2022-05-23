import { Component } from "react";
import PrimkaService from "../services/PrimkaService";
import ProizvodService from "../services/ProizvodService";
import StavkaPrimkaService from "../services/StavkaPrimkaService";


class PrimkaStavkeComponent extends Component {

    constructor(props) {
        super(props)
        this.state =  {
            sifPrimka: null,
            datumPrimka: null,
            valutaPrimka: null,
            nazivSkladiste: null,
            nazivPartner: null,
            stavke: [],
            formaSifProizvod: null,
            formaKolicinaProizvodPrimka: null,
            formaKupovnaCijena: null,
            proizvodi: []
        }

        this.deleteStavkaClicked = this.deleteStavkaClicked.bind(this)
        this.refreshStavke = this.refreshStavke.bind(this)

        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)

        this._onSelect = this._onSelect.bind(this)

    }

    async componentDidMount() {
        let proizvodiRes = await ProizvodService.allProizvods();
        this.setState({
            proizvodi: proizvodiRes
        })

        this.refreshStavke()
    }

    async refreshStavke() {
        let response = await PrimkaService.getPrimka(this.props.match.params.id)
        this.setState({
            sifPrimka: response.sifPrimka,
            datumPrimka: response.datumPrimka,
            valutaPrimka: response.valutaPrimka,
            nazivSkladiste: response.skladiste.nazivSkladiste,
            nazivPartner: response.poslovniPartner.nazivPartner,
            stavke: response.stavke
        })
    }

    async deleteStavkaClicked(idPrimka, idProizvod) {
        let response = await StavkaPrimkaService.deleteStavka(idPrimka, idProizvod)
        console.log('deleteStavkaPrimka got status: ' + response.status)
        if (response.status === 200) {
            this.refreshStavke()
        }
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        })
        console.log(this.state)
    }

    _onSelect (e) {
        console.log('You selected ', e.target.value)
        this.setState(
            {
                formaSifProizvod: e.target.value
            }
        )
        console.log(this.state)
      }

    async handleSubmit(e) {
        e.preventDefault()
        console.log('submit stavka primke')

        const payload = {
            sifProizvod: this.state.formaSifProizvod,
            kolicinaProizvodPrimka: this.state.formaKolicinaProizvodPrimka,
            kupovnaCijena: this.state.formaKupovnaCijena
        }

        try {
            let response = await StavkaPrimkaService.createStavka(this.props.match.params.id, payload)
            if (response.status === 200) {
                this.setState({
                    formaSifProizvod: null,
                    formaKolicinaProizvodPrimka: null,
                    formaKupovnaCijena: null
                })
                document.forma.reset()
                this.refreshStavke()
            }
        } catch(err) {
            console.log(err)
            alert('Pogreska pri dodavanju stavke')
        }
    }

    render() {
        return (
            <div className="container">

                <div className="col-md-6">
                    <br />
                    <h3>Primka</h3>
                    <table className="table">
                        <tbody>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Šifra primke</span>
                                    </strong>
                                </td>
                                <td>{this.state.sifPrimka}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Datum</span>
                                    </strong>
                                </td>
                                <td>{this.state.datumPrimka}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Skladište</span>
                                    </strong>
                                </td>
                                <td>{this.state.nazivSkladiste}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Poslovni partner</span>
                                    </strong>
                                </td>
                                <td>{this.state.nazivPartner}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Valuta</span>
                                    </strong>
                                </td>
                                <td>{this.state.valutaPrimka}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <br />
                <h3>Stavke</h3>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Proizvod</th>
                            <th>Količina</th>
                            <th>Kupovna cijena</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.stavke.map( stavka => 
                                <tr key={stavka.kompozitSif.sifProizvod}>
                                    <td>{stavka.proizvod.nazivProizvod}</td>
                                    <td>{stavka.kolicinaProizvodPrimka}</td>
                                    <td>{stavka.kupovnaCijena}</td>
                                    <td><button className="btn btn-warning" onClick={() => this.deleteStavkaClicked(this.props.match.params.id, stavka.proizvod.sifProizvod)}>Delete</button></td>
                                </tr>
                                )
                        }
                    </tbody>
                </table>
                <form name="forma" onSubmit={this.handleSubmit}>
                    <table className="table">
                        <tbody>
                            <tr>
                            <td>
                                    <select onChange={this._onSelect}>
                                    <option value=""> </option>
                                    {
                                        this.state.proizvodi.map ( proizvod => 
                                            <option value={proizvod.sifProizvod} key={proizvod.sifProizvod}>{proizvod.nazivProizvod}</option>    
                                        )
                                    }
                                    </select>
                                </td>
                                <td>
                                    <input type="number" name="formaKolicinaProizvodPrimka" onChange={this.handleChange} />
                                </td>
                                <td>
                                    <input type="number" name="formaKupovnaCijena" onChange={this.handleChange} />
                                </td>
                                <td><button className="btn btn-success">Dodaj</button></td>
                            </tr>
                        </tbody>
                    </table>
                                    
                    </form>
            </div>
        )
    }

}

export default PrimkaStavkeComponent