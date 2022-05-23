import React, {Component} from 'react'
import {Link, useLocation, withRouter, WithRouter} from 'react-router-dom'
import AuthService from '../services/AuthService';


class MenuComponent extends Component {

    render() {
        const isUserLoggedIn = AuthService.isUserLoggedIn();
        const location = window.location.pathname;
        const roles = AuthService.getLoggedInRoles();
        
        let isAdmin = roles.includes('ROLE_ADMIN') ? true : false;
        console.log(roles)
        console.log(isAdmin)

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div className="navbar-brand">Zavrsni-app</div>
                    <ul className="navbar-nav">
                        {/* <li><Link className="nav-link" to="/">Naslovnica</Link></li> */}

                        {(isUserLoggedIn && (roles.includes('ROLE_ADMIN') || roles.includes('ROLE_DIREKTOR') || roles.includes('ROLE_SKLADISTAR'))) &&
                            <li>
                                <Link className="nav-link" to="/sviProizvodi">Proizvodi</Link>
                            </li>
                        }
                        
                        {(isUserLoggedIn && (roles.includes('ROLE_ADMIN') || roles.includes('ROLE_DIREKTOR') || roles.includes('ROLE_SKLADISTAR'))) &&
                            <li>
                                <Link className="nav-link" to="/svaSkladista">Skladišta</Link>
                            </li>
                        }

                        {(isUserLoggedIn && (roles.includes('ROLE_ADMIN') || roles.includes('ROLE_DIREKTOR') || roles.includes('ROLE_SKLADISTAR'))) &&
                            <li>
                                <Link className="nav-link" to="/sviRacuni">Računi</Link>
                            </li>
                        }

                        {(isUserLoggedIn && (roles.includes('ROLE_ADMIN') || roles.includes('ROLE_DIREKTOR') || roles.includes('ROLE_SKLADISTAR'))) &&
                            <li>
                                <Link className="nav-link" to="/svePrimke">Primke</Link>
                            </li>
                        }

                        {(isUserLoggedIn && (roles.includes('ROLE_ADMIN') || roles.includes('ROLE_DIREKTOR') || roles.includes('ROLE_SKLADISTAR'))) &&
                            <li>
                                <Link className="nav-link" to="/sviPartneri">Partneri</Link>
                            </li>
                        }

                        {(isUserLoggedIn && (roles.includes('ROLE_ADMIN') || roles.includes('ROLE_DIREKTOR'))) &&
                            <li>
                                <Link className="nav-link" to="/sviZaposlenici">Zaposlenici</Link>
                            </li>
                        }


                        {(isUserLoggedIn && (roles.includes('ROLE_ADMIN') || roles.includes('ROLE_DIREKTOR'))) &&
                            <li>
                                <Link className="nav-link" to="/statistika/home">Statistika</Link>
                            </li>
                        }

                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Prijava</Link></li>}
                        
                        {isUserLoggedIn && <li><Link className="nav-link" to="/profil">Moji podaci</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthService.logout}>Odjava</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}       

export default withRouter(MenuComponent)