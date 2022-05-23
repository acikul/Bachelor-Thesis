import React, {Component} from 'react'
import {Link, withRouter} from 'react-router-dom'
import { RadioGroup, RadioButton } from 'react-radio-buttons';
import PoslovniPartnerService from "../services/PoslovniPartnerService";
import ProizvodService from "../services/ProizvodService";

class ZzStatistikaComponent extends Component {

  constructor(props) {
    super(props);
    this.state = {
      godina: 2020,
      partneri: [],
      proizvodi: []
    };
  }

  async componentDidMount() {
    let response = await PoslovniPartnerService.allPartners()
    let responseProizvodi = await ProizvodService.allProizvods()
    this.setState({
      partneri: response,
      proizvodi: responseProizvodi
    })
  }

  onRadiochange = value => {
    this.setState({
      godina: value
    })
    console.log(value);
  };

  render() {
    return(
      <div className="container">
        <br />
        <h3>Godina</h3>
        <div className="row">
        <RadioGroup horizontal onChange={this.onRadiochange} value="2020">
          <RadioButton value="2018">2018</RadioButton>
          <RadioButton value="2019">2019</RadioButton>
          <RadioButton value="2020">2020</RadioButton>
          <RadioButton value="2021">2021</RadioButton>
        </RadioGroup>
        </div>
        <br />
        <h3>Prodaja po kupcima</h3>
        {
          this.state.partneri.map( partner =>
            <div>
              <Link to={`/statistika/partner/${partner.sifPartner}/${this.state.godina}`}>{partner.nazivPartner}</Link>
            </div>
            )
        }
        <br />
        
        <h3>Prodaja po proizvodima</h3>
        {
          this.state.proizvodi.map( proizvod =>
            <div>
              <Link to={`/statistika/proizvod/${proizvod.sifProizvod}/${this.state.godina}`}>{proizvod.nazivProizvod}</Link>
            </div>
            )
        }
        <br />
        <h3>
          <Link to={`/statistika/sviProizvodi/${this.state.godina}`}>Usporedba proizvoda</Link>
        </h3>
        

      </div>
    )
  }
}

export default ZzStatistikaComponent