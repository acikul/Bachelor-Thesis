import axios from "axios";

class ProizvodacService {

    async allProizvodacs() {
        try {
            let response = await axios.get(`/proizvodac/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allProizvodacs")
            console.log(err)
            return null
        }
    }

}

export default new ProizvodacService()