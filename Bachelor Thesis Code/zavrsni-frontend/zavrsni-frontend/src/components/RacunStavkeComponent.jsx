import { Component } from "react";
import ProizvodService from "../services/ProizvodService";
import RacunService from "../services/RacunService";
import StavkaRacunService from "../services/StavkaRacunService";



class RacunStavkeComponent extends Component {

    constructor(props) {
        super(props)
        this.state =  {
            sifRacun: null,
            datumRacun: null,
            valutaRacun: null,
            placen: null,
            ukupnaCijena: null,
            nazivSkladiste: null,
            nazivPoslovnica: null,
            emailZaposlenik: null,
            stavke: [],
            formaSifProizvod: null,
            formaKolicinaProizvodRacun: null,
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
        let response = await RacunService.getRacun(this.props.match.params.id)
        this.setState({
            sifRacun: response.sifRacun,
            datumRacun: response.datumRacun,
            valutaRacun: response.valutaRacun,
            placen: response.placen,
            nazivSkladiste: response.skladiste.nazivSkladiste,
            nazivPoslovnica: response.poslovnica.nazivPoslovnica,
            emailZaposlenik: response.zaposlenik.emailZaposlenik,
            ukupnaCijena: response.ukupnaCijena,
            stavke: response.stavke
        })
    }

    async deleteStavkaClicked(idR, idP) {
        let response = await StavkaRacunService.deleteStavka(idR, idP)
        console.log('deleteStavkaRacuna got status: ' + response.status)
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
        console.log('submit stavka racuna')

        const payload = {
            sifProizvod: this.state.formaSifProizvod,
            kolicinaProizvodRacun: this.state.formaKolicinaProizvodRacun
        }

        try {
            let response = await StavkaRacunService.createStavka(this.props.match.params.id, payload)
            if (response.status === 200) {
                this.setState({
                    formaSifProizvod: null,
                    formaKolicinaProizvodRacun: null
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
                    <h3>Račun</h3>
                    <table className="table">
                        <tbody>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Šifra računa</span>
                                    </strong>
                                </td>
                                <td>{this.state.sifRacun}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Datum</span>
                                    </strong>
                                </td>
                                <td>{this.state.datumRacun}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Plaćen</span>
                                    </strong>
                                </td>
                                <td>{(this.state.placen) === 0 ? 'ne' : 'da'}</td>
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
                                        <span className="">Izdao</span>
                                    </strong>
                                </td>
                                <td>{this.state.emailZaposlenik}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Poslovnica</span>
                                    </strong>
                                </td>
                                <td>{this.state.nazivPoslovnica}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Ukupna cijena</span>
                                    </strong>
                                </td>
                                <td>{this.state.ukupnaCijena}</td>
                            </tr>
                            <tr>
                                <td>
                                    <strong>
                                        <span className="">Valuta</span>
                                    </strong>
                                </td>
                                <td>{this.state.valutaRacun}</td>
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
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.stavke.map( stavka => 
                                <tr key={stavka.kompozitSif.sifProizvod}>
                                    <td>{stavka.proizvod.nazivProizvod}</td>
                                    <td>{stavka.kolicinaProizvodRacun}</td>
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
                                {/* <td>
                                    <input type="number" name="formaSifProizvod" onChange={this.handleChange}/>
                                </td> */}
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
                                    <input type="number" name="formaKolicinaProizvodRacun" onChange={this.handleChange} />
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

export default RacunStavkeComponent