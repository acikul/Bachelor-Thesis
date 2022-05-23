import axios from "axios";

class GradService {

    async allGrads() {
        try {
            let response = await axios.get(`/grad/all`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR allGrads")
            console.log(err)
            return null
        }
    }

}

export default new GradService()