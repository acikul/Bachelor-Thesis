import axios from "axios";

class RadnoMjestoService {

    async allRadMjest() {
        try {
            let response = await axios.get(`/radnomjesto`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allRadnaMjesta")
            console.log(err)
            return null
        }
    }

}

export default new RadnoMjestoService()