import axios from "axios"

class StavkaPrimkaService {

    async createStavka(id, payload) {
        try {
            let response = await axios.post(`/stavkePrimki/primka/${id}`, payload)
            console.log("create stavkaPrimka service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }

    async deleteStavka(idPrimka, idProizvod) {
        try {
            let response = await axios.delete(`/stavkePrimki/primka/${idPrimka}/proizvod/${idProizvod}`)
            console.log("delete stavkaPrimka by id service")
            console.log(response.data)
            return response
        } catch(err) {
            console.log(err)
            return null
        }
    }
}

export default new StavkaPrimkaService()