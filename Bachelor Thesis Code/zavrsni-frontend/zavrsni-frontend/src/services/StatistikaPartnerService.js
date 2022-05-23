import axios from "axios";

class StatistikaPartnerService {

    async getPrometPoMjZaPartner(id, godina) {
        try {
            let response = await axios.get(`/statistika/partner/${id}/${godina}`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR PrometiPoMjesecimaZaPartner")
            console.log(err)
            return null
        }
    }

}

export default new StatistikaPartnerService()