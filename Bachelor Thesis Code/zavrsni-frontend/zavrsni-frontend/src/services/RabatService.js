import axios from "axios";

class RabatService {
    
    async getAllRabats() {
        try {
            let response = await axios.get(`/rabati/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allRabati")
            console.log(err)
            return null
        }
    }

    async getRabatsForPartner(id) {
        try {
            let response = await axios.get(`/rabati/partner/${id}`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR getRabatiForPartner")
            console.log(err)
            return null
        }
    }
}

export default new RabatService()