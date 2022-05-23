import axios from "axios"

class PrimkaService {
    async allPrimkas() {
        try {
            let response = await axios.get(`/primka/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allPrimkas")
            console.log(err)
            return null
        }
    }

    async getPrimka(id) {
        try {
            let response = await axios.get(`/primka/${id}`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR getPrimka")
            console.log(err)
            return null
        }
    }

    async createPrimka(payload) {
        try {
            let response = await axios.post(`/primka`, payload)
            console.log("create primka service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async deletePrimka(id) {
        try {
            let response = await axios.delete(`/primka/${id}`)
            console.log(response.data)
            return response
        } catch (err) {
            console.log("ERROR delPrimka")
            console.log(err)
            return null
        }
    }
}    

export default new PrimkaService()