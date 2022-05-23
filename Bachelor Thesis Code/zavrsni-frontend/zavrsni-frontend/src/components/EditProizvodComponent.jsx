import { Component } from "react";
import ProizvodacService from "../services/ProizvodacService";
import ProizvodService from "../services/ProizvodService";

class EditProizvodComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            sif: null,
            barkodProizvod: null,
            nazivProizvod: null,
            mjeraProizvod: null,
            vrstaProizvod: null,
            podgrupaProizvod: null,
            kolicinaKutijaProizvod: null,
            masaProizvod: null,
            cijenaProizvod: null,
            sifProizvodac: null,
            proizvodaci: []
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)

        this._onSelect = this._onSelect.bind(this)
    }

    async componentDidMount() {
        let proizRes = await ProizvodacService.allProizvodacs()
        this.setState({
            proizvodaci: proizRes
        })

        if (this.props.location.pathname === '/addProizvod') {
            console.log("addProizvod")
            return
        }
        
        let id = this.props.match.params.id
        let proizvodData = await ProizvodService.getProizvod(id)
        if (proizvodData) {
            console.log(proizvodData)
            this.setState({
                sif: proizvodData.sifProizvod,
                barkodProizvod: proizvodData.barkodProizvod,
                nazivProizvod: proizvodData.nazivProizvod,
                mjeraProizvod: proizvodData.mjeraProizvod,
                vrstaProizvod: proizvodData.vrstaProizvod,
                podgrupaProizvod: proizvodData.podgrupaProizvod,
                kolicinaKutijaProizvod: proizvodData.kolicinaKutijaProizvod,
                masaProizvod: proizvodData.masaProizvod,
                cijenaProizvod: proizvodData.cijenaProizvod,
                sifProizvodac: proizvodData.proizvodac.sifProizvodac
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
                sifProizvodac: e.target.value
            }
        )
        console.log(this.state)
      }

    async handleSubmit(e) {
        e.preventDefault()
        console.log("SUBMIT PROIZVOD")

        const payload = {
            barkodProizvod: this.state.barkodProizvod,
            nazivProizvod: this.state.nazivProizvod,
            mjeraProizvod: this.state.mjeraProizvod,
            vrstaProizvod: this.state.vrstaProizvod,
            podgrupaProizvod: this.state.podgrupaProizvod,
            kolicinaKutijaProizvod: this.state.kolicinaKutijaProizvod,
            masaProizvod: this.state.masaProizvod,
            cijenaProizvod: this.state.cijenaProizvod,
            sifProizvodac: this.state.sifProizvodac
        }

        if (this.props.location.pathname === '/addProizvod') {
            try {
                let response = await ProizvodService.createProizvod(payload)
                if (response.status === 200) {
                    this.props.history.push(`/sviProizvodi`)
                    alert('Uspješno dodan proizvod!')
                }
            } catch (error) {
                console.log(error)
                alert('Pogreška pri dodavanju proizvoda')
            }
            return
        }
        try {
            let response = await ProizvodService.updateProizvod(this.state.sif, payload)

            if (response.status === 200) {
                this.props.history.push(`/sviProizvodi`)
                alert('Uspješno izmijenjen proizvod!')
            }
        } catch(error) {
            console.log(error)
            alert('Pogreška pri izmijeni proizvoda')
        }
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="col-md-4">
                    <br />
                <h3>Proizvod</h3>
                    <table className="table">
                        <tbody>
                    <tr>
                        <td>                    
                        Barkod:
                        </td>
                        <td>
                            <input type="text"
                               name="barkodProizvod"
                               defaultValue={this.state.barkodProizvod} 
                               onChange={this.handleChange}
                            />
                        </td>
                    </tr>
                    <tr>
                    <td>
                        Naziv:
                    </td>
                    <td>
                    <input type="text"
                               name="nazivProizvod"
                               defaultValue={this.state.nazivProizvod} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Mjera:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="mjeraProizvod"
                               defaultValue={this.state.mjeraProizvod} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Vrsta:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="vrstaProizvod"
                               defaultValue={this.state.vrstaProizvod} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Podgrupa:
                        
                    </td>
                    <td>
                    <input type="text"
                               name="podgrupaProizvod"
                               defaultValue={this.state.podgrupaProizvod} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Količina u kutiji:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="kolicinaKutijaProizvod"
                               defaultValue={(this.state.kolicinaKutijaProizvod)} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Masa:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="masaProizvod"
                               defaultValue={this.state.masaProizvod} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Cijena:
                        
                    </td>
                    <td>
                    <input type="number"
                               name="cijenaProizvod"
                               defaultValue={this.state.cijenaProizvod} 
                               onChange={this.handleChange}
                        />
                    </td>
                    </tr>
                    <tr>
                    <td>
                        Proizvođač:
                        
                    </td>
                    <td>
                                    <select onChange={this._onSelect}>
                                    <option value=""> </option>
                                    {
                                        this.state.proizvodaci.map ( proizvodac => 
                                            <option value={proizvodac.sifProizvodac} key={proizvodac.sifProizvodac}>{proizvodac.nazivProizvodac}</option>    
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

export default EditProizvodComponent