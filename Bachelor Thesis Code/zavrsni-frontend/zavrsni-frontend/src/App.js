import logo from './logo.svg';
import './App.css';
import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import MenuComponent from './components/MenuComponent'
import LoginComponent from './components/LoginComponent'
import LogoutComponent from './components/LogoutComponent'
import AuthenticatedRoute from './components/AuthenticatedRoute'
import HomeComponent from './components/HomeComponent'
import UserDataService from './services/UserDataService';
import ProfilComponent from './components/ProfilComponent';
//import EditProfilComponent from './components/EditProfilComponent';
import ListUsersComponent from './components/ListUsersComponent';
import ZaposlenikComponent from './components/ZaposlenikComponent';
import ListProizvodsComponent from './components/ListProizvodsComponent';
import EditProizvodComponent from './components/EditProizvodComponent';
import ListSkladistesComponent from './components/ListSkladistesComponent';
import ImaNaStanjuComponent from './components/ImaNaStanjuComponent';
import ListRacunsComponent from './components/ListRacunsComponent';
import AddRacunComponent from './components/AddRacunComponent';
import ListPartnersComponent from './components/ListPartnersComponent';
import AddEditPartnerComponent from './components/AddEditPartnerComponent';
import PartnerPoslovniceComponent from './components/PartnerPoslovniceComponent';
import AddPoslovnicaComponent from './components/AddPoslovnicaComponent';
import RacunStavkeComponent from './components/RacunStavkeComponent';
import ZzStatistikaComponent from './components/ZzStatistikaComponent';
import ListPrimkasComponent from './components/ListPrimkasComponent';
import AddPrimkaComponent from './components/AddPrimkaComponent';
import PrimkaStavkeComponent from './components/PrimkaStavkeComponent';
import PartnerRabatComponent from './components/PartnerRabatiComponent';
import ZzPartnerStatistikaComponent from './components/ZzPartnerStatistikaComponent';
import ZzProizvodStatistikaComponent from './components/ZzProizvodStatistikaComponent';
import ZzStatistikaPieComponent from './components/ZzStatistikaPieComponent';
import AddSkladisteComponent from './components/AddSkladisteComponent';


class App extends React.Component {

  // constructor() {
  //   super()
  //   this.state = {
  //     user: null
  //   }
  //   this.changeUser = this.changeUser.bind(this)
  // }

  // async componentDidMount() {
  //   console.log("App.js comp did mnt")
  //   let userData = await CurrentUserService()
  //   if (userData) {
  //     this.setState({
  //       user: userData
  //     })
  //   }
  // }

  // changeUser = (user) => {
  //   this.setState({
  //     user: user
  //   })
  // }


  render() {
    let user = sessionStorage.getItem('user')
    console.log(user)

    return (
      <div className="container">
        <Router>
          <>
            <MenuComponent />
            <Switch>
              {/* <Route path="/" exact component={LoginComponent} /> */}
              {/* <Route exact path='/' render={props => <HomeComponent user={this.state.user}/> } />
              <Route exact path='/login' render={props => <LoginComponent user={this.state.user} changeUser={this.changeUser} />} />
              <AuthenticatedRoute path="/logout" exact component={LogoutComponent} /> */}


              <Route path='/' exact component={HomeComponent} />
              <Route path='/login' exact component={LoginComponent} />
              <AuthenticatedRoute path="/logout" exact component={LogoutComponent} />
              
              <Route path='/profil' render={props => <ProfilComponent {...props} />} />
              <Route path='/editProfil' render={props => <ZaposlenikComponent {...props} />} />

              <Route path='/sviZaposlenici' render={props => <ListUsersComponent {...props} />} />
              <Route path='/addZaposlenik' render={props => <ZaposlenikComponent {...props} />} />
              <Route path='/zaposlenik/:id' render={props => <ZaposlenikComponent {...props} />} />

              <Route path='/sviProizvodi' render= {props => <ListProizvodsComponent {...props} />} />
              <Route path='/addProizvod' render={props => <EditProizvodComponent {...props} />} />
              <Route path='/proizvod/:id' render={props => <EditProizvodComponent {...props} />} />

              <Route path='/svaSkladista' render= {props => <ListSkladistesComponent {...props} />} />
              <Route path='/skladiste/stanje/:id' render={props => <ImaNaStanjuComponent {...props} />} />
              <Route path='/addSkladiste' render={props => <AddSkladisteComponent {...props} />} />


              <Route path='/sviRacuni' render= {props => <ListRacunsComponent {...props} />} />
              <Route path='/addRacun' render= {props => <AddRacunComponent {...props} />} />
              <Route path='/racun/:id' render= {props => <RacunStavkeComponent {...props} />} />

              <Route path='/svePrimke' render= {props => <ListPrimkasComponent {...props} />} />
              <Route path='/addPrimka' render= {props => <AddPrimkaComponent {...props} />} />
              <Route path='/primka/:id' render= {props => <PrimkaStavkeComponent {...props} />} />

              <Route path='/sviPartneri' render= {props => <ListPartnersComponent {...props} />} />
              <Route path='/addPartner' render= {props => <AddEditPartnerComponent {...props} />} />
              <Route path='/editPartner/:id' render= {props => <AddEditPartnerComponent {...props} />} />
              
              <Route path='/partner/:id/poslovnice' render= {props => <PartnerPoslovniceComponent {...props} />} />
              <Route path='/addPoslovnica/partner/:id' render= {props => <AddPoslovnicaComponent {...props} />} />

              <Route path='/partner/:id/rabati' render= {props => <PartnerRabatComponent {...props} />} />


              <Route path='/statistika/home' render= {props => <ZzStatistikaComponent {...props} />} />
              <Route path="/statistika/partner/:id/:godina" render= {props => <ZzPartnerStatistikaComponent {...props} />} />
              <Route path="/statistika/proizvod/:id/:godina" render= {props => <ZzProizvodStatistikaComponent {...props} />} />
              <Route path="/statistika/sviProizvodi/:godina" render= {props => <ZzStatistikaPieComponent {...props} />} />


              {/* <AuthenticatedRoute path="/profil" exact component={ProfilComponent} />
              <AuthenticatedRoute path="/editProfil" exact component={EditProfilComponent} /> */}
            </Switch>
          </>
        </Router>
      </div>
    )
  }
}

export default App;


{/* <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div> */}