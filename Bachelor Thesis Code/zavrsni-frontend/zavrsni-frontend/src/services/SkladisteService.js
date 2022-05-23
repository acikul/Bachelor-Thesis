import axios from 'axios'

class SkladisteService {

    async allSkladistes() {
        try {
            let response = await axios.get(`/skladiste/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allSkladistes")
            console.log(err)
            return null
        }
    }

    async getSkladiste(id) {
        try {
            let response = await axios.get(`/skladiste/${id}`)
            console.log("get skladiste by id service")
            console.log(response.data)
            return response.data
        } catch(err) {
            console.log(err)
            return null
        }
    }


}

export default new SkladisteService()