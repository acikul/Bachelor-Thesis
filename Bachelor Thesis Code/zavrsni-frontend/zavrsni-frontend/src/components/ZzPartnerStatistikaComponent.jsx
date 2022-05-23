import {React, Component} from "react";
import { BarChart, Bar, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import PoslovniPartnerService from "../services/PoslovniPartnerService";
import StatistikaPartnerService from "../services/StatistikaPartnerService";


class ZzPartnerStatistikaComponent extends Component{

    constructor(props) {
        super(props)
        this.state = {
            nazivPartner: null,
            mjeseciPrometi: [],
            ukupno: null
        }
    }

    async componentDidMount() {
        let nazivResponse = await PoslovniPartnerService.getPartner(this.props.match.params.id)
        let response = await StatistikaPartnerService.getPrometPoMjZaPartner(this.props.match.params.id, this.props.match.params.godina)
        this.setState({
            nazivPartner: nazivResponse.nazivPartner,
            mjeseciPrometi: response.mjeseciPrometi,
            ukupno: response.ukupno
        })
    }

    render() {
        return (
            <div>
        <br />
        <h3>Promet</h3>
        <h4>{this.state.nazivPartner} - {this.props.match.params.godina}</h4>
            <ResponsiveContainer width="100%" aspect={4}>
                <BarChart
                width={500}
                height={300}
                data={this.state.mjeseciPrometi}
                margin={{
                    top: 5,
                    right: 30,
                    left: 20,
                    bottom: 5,
                }}
                >
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="mjesec" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey="promet" fill="#8884d8" />
                </BarChart>
            </ResponsiveContainer>

            <h5>Ukupno: {this.state.ukupno} kn</h5>
        </div>
        )
    }

}

export default ZzPartnerStatistikaComponent