import axios from "axios"

class PoslovniParterService {

    async allPartners() {
        try {
            let response = await axios.get(`/poslovniPartner/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allPartners")
            console.log(err)
            return null
        }
    }

    async getPartner(id) {
        try {
            let response = await axios.get(`/poslovniPartner/${id}`)
            console.log("get partner by id service")
            console.log(response.data)
            return response.data
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async createPartner(payload) {
        try {
            let response = await axios.post(`/poslovniPartner`, payload)
            console.log("create partner service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async updatePartner(id, payload) {
        try {
            let response = await axios.put(`/poslovniPartner/${id}`, payload)
            console.log("update partner by id service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }


    async deletePartner(id) {
        try {
            let response = await axios.delete(`/poslovniPartner/${id}`)
            console.log("delete partner by id service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }
}

export default new PoslovniParterService()