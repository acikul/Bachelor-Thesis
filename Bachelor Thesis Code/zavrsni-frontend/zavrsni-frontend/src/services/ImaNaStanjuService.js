import axios from 'axios'

class ImaNaStanjuService {

    async getStanjeSkladiste(id) {
        try {
            let response = await axios.get(`/stanje/skladiste/${id}`)
            console.log("get stanje skladiste by id service")
            console.log(response.data)
            return response.data
        } catch(err) {
            console.log(err)
            return null
        }
    }
}

export default new ImaNaStanjuService()