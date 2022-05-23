import { Component } from "react";
import PoslovniPartnerService from "../services/PoslovniPartnerService";
import UserDataService from "../services/UserDataService";

class ListPartnersComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            partneri: [],
            role: null

        }

        this.rabatiClicked = this.rabatiClicked.bind(this)
        this.poslovniceClicked = this.poslovniceClicked.bind(this)
        this.addPartnerClicked = this.addPartnerClicked.bind(this)
        this.deletePartnerClicked = this.deletePartnerClicked.bind(this)
        this.refreshPartners = this.refreshPartners.bind(this)
    }

    async refreshPartners() {
        let response = await PoslovniPartnerService.allPartners()
        this.setState({
            partneri: response
        })
    }

    async componentDidMount() {
        let resMe = await UserDataService.currentUser()
        this.setState({
            role: resMe.radnoMj.nazivRadnoMjesto
        })

        this.refreshPartners();
    }

    rabatiClicked(id) {
        console.log('rabati od partner: ' + id + ' clicked')
        this.props.history.push(`/partner/${id}/rabati`)
    }

    poslovniceClicked(id) {
        console.log('poslovnice od partner: ' + id + ' clicked')
        this.props.history.push(`/partner/${id}/poslovnice`)
    }

    editPartnerClicked(id) {
        console.log('edit partner: ' + id + ' clicked')
        this.props.history.push(`/editPartner/${id}`)
    }

    addPartnerClicked() {
        console.log('add partner clicked')
        this.props.history.push(`/addPartner`)
    } 

    async deletePartnerClicked(id) {
        let response = await PoslovniPartnerService.deletePartner(id)
        console.log('deletePartnerClicked got status: ' + response.status)
        if (response.status === 200) {
            this.refreshPartners()
        }
    }

    render() {
        return (
            <div>
                <br />
                <div className="container">
                    <h3>Svi poslovni partneri</h3>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Šifra</th>
                                <th>Naziv</th>
                                <th>Adresa</th>
                                <th></th>
                                <th></th>
                                {
                                    (this.state.role === "admin" || this.state.role === "direktor") &&
                                    <th></th>                        
                            }
                            {
                                    (this.state.role === "admin" || this.state.role === "direktor") &&
                                    <th></th>                        
                            }
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.partneri.map( partner =>
                                    <tr key={partner.sifPartner}>
                                        <td>{partner.sifPartner}</td>
                                        <td>{partner.nazivPartner}</td>
                                        <td>{partner.adresaSjedistePartner}</td>
                                        <td><button className="btn" onClick={() => this.rabatiClicked(partner.sifPartner)}>Rabati</button></td>
                                        <td><button className="btn" onClick={() => this.poslovniceClicked(partner.sifPartner)}>Poslovnice</button></td>
                                        {
                                    (this.state.role === "admin" || this.state.role === "direktor") &&
                                    <td><button className="btn" onClick={() => this.editPartnerClicked(partner.sifPartner)}>Edit</button></td>
                                }
                            {
                                    (this.state.role === "admin" || this.state.role === "direktor") &&
                                    <td><button className="btn btn-warning" onClick={() => this.deletePartnerClicked(partner.sifPartner)}>Delete</button></td>
                                }
                                        
                                    </tr>
                                    )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                    {
                    (this.state.role === "admin" || this.state.role === "direktor") &&
                    <button className="btn btn-success" onClick={this.addPartnerClicked}>Add</button>
                }
                    </div>
                </div>
            </div>
        )
    }

}

export default ListPartnersComponent