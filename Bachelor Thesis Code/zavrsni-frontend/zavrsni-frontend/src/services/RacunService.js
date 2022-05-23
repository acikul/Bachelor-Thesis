import axios from "axios"

class RacunService {
    async allRacuns() {
        try {
            let response = await axios.get(`/racun/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allRacuns")
            console.log(err)
            return null
        }
    }

    async getRacun(id) {
        try {
            let response = await axios.get(`/racun/${id}`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR getRacun")
            console.log(err)
            return null
        }
    }

    async createRacun(payload) {
        try {
            let response = await axios.post(`/racun`, payload)
            console.log("create racun service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async deleteRacun(id) {
        try {
            let response = await axios.delete(`/racun/${id}`)
            console.log(response.data)
            return response
        } catch (err) {
            console.log("ERROR delRacun")
            console.log(err)
            return null
        }
    }
}

export default new RacunService()