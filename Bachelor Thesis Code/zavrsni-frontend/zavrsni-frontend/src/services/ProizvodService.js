import axios from "axios"

class ProizvodService {

    async allProizvods() {
        try {
            let response = await axios.get(`/proizvod/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allProizvods")
            console.log(err)
            return null
        }
    }

    async getProizvod(id) {
        try {
            let response = await axios.get(`/proizvod/${id}`)
            console.log("get proizvod by id service")
            console.log(response.data)
            return response.data
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async updateProizvod(id, payload) {
        try {
            let response = await axios.put(`/proizvod/${id}`, payload)
            console.log("update proizvod by id service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async createProizvod(payload) {
        try {
            let response = await axios.post(`/proizvod`, payload)
            console.log("create proizvod service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async deleteProizvod(id) {
        try {
            let response = await axios.delete(`/proizvod/${id}`)
            console.log("delete proizvod by id service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

}

export default new ProizvodService()