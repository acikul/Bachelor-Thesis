import axios from "axios"

class PoslovnicaService {

    async allPoslovnicas() {
        try {
            let response = await axios.get(`/poslovnica/all`)
            console.log("all poslovnica service")
            console.log(response.data)
            return response.data
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async createPoslovnica(payload) {
        try {
            let response = await axios.post(`/poslovnica`, payload)
            console.log("create poslovnica service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async deletePoslovnica(id) {
        try {
            let response = await axios.delete(`/poslovnica/${id}`)
            console.log("delete poslovnica by id service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

}

export default new PoslovnicaService()