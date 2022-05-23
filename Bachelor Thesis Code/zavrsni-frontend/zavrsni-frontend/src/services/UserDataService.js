import axios from 'axios'


class UserDataService {

    async currentUser() {
        try {
            let response = await axios.get(`/zaposlenik/me`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log(err)
            return null
        }
    }

    async allUsers() {
        try {
            let response = await axios.get(`/zaposlenik/all`)
            console.log("All users service")
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log(err)
            return null
        }
    }

    async getUser(id) {
        try {
            let response = await axios.get(`/zaposlenik/${id}`)
            console.log("get user by id service")
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log(err)
            return null
        }
    }

    async updateUser(id, payload) {
        try {
            let response = await axios.put(`/zaposlenik/${id}`, payload)
            console.log("update user service")
            console.log(response.data)
            return response
        } catch (err) {
            console.log(err)
            return null
        }
    }

    async createUser(payload) {
        try {
            let response = await axios.post(`/zaposlenik`, payload)
            console.log("create user service")
            console.log(response.data)
            return response
        } catch (err) {
            console.log(err)
            return null
        }
    }

    async deleteUser(id) {
        try {
            let response = await axios.delete(`/zaposlenik/${id}`)
            console.log("delete user by id service")
            console.log(response.data)
            return response
        } catch (err) {
            console.log(err)
            return null
        }
    }
}

export default new UserDataService()