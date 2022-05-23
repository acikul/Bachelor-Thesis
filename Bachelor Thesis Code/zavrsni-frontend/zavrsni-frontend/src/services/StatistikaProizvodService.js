import axios from "axios";

class StatistikaProizvodService {

    async getPrometPoMjZaProizvod(id, godina) {
        try {
            let response = await axios.get(`/statistika/proizvod/${id}/${godina}`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR PrometiPoMjesecimaZaProizvod")
            console.log(err)
            return null
        }
    }

    async getPrometSvihProizvoda(godina) {
        try {
            console.log('došao sam u getPrometSvihProizvodaService za godinu ' + godina);
            let response = await axios.get(`/statistika/sviProizvodi/${godina}`)
            console.log(response.data)
            return response.data
        } catch (err) {
            console.log("ERROR PrometiZaSve Proizvode")
            console.log(err)
            return null
        }
    }

}

export default new StatistikaProizvodService()