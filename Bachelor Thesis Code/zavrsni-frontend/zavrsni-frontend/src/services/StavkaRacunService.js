import axios from "axios"

class StavkaRacunService {

    async createStavka(id, payload) {
        try {
            let response = await axios.post(`/stavke/racun/${id}`, payload)
            console.log("create stavkaRAcuna service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async deleteStavka(idR, idP) {
        try {
            let response = await axios.delete(`/stavke/racun/${idR}/proizvod/${idP}`)
            console.log("delete stavkaRacuna by id service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }
}

export default new StavkaRacunService()