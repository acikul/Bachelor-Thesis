import axios from 'axios'


class AuthService {
    async executeAuthService(email, password, changeUser) {
        let response = await axios.post(`/auth/signin`, {email, password})
        console.log("auth response: ")
        console.log(response.data)
        return response
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }

    successfulJwtLogin(username, data) {
        sessionStorage.setItem('user', username)
        sessionStorage.setItem('roles', JSON.stringify(data.roles))
        sessionStorage.setItem('token', data.accessToken)

        axios.defaults.headers.common['Authorization'] = 'Bearer ' + data.accessToken
        // this.setupAxiosInterceptors(this.createJWTToken(data.accessToken))
    }

    logout() {
        sessionStorage.clear()
        // sessionStorage.removeItem('user');
        // sessionStorage.removeItem('roles');
        // sessionStorage.removeItem('token');

        axios.defaults.headers.common['Authorization'] = ''
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('user')
        if (user === null) return false
        return true
    }

    getLoggedInUsername() {
        let user = sessionStorage.getItem('user')
        if (user === null) return ''
        return user
    }

    getLoggedInRoles() {
        let roles = JSON.parse(sessionStorage.getItem('roles'))
        if (roles === null) return []
        return roles
    }

    setupAxiosInterceptors(token) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }
}

export default new AuthService()