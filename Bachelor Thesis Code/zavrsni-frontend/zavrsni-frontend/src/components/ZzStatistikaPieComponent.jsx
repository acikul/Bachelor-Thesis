import {React, Component} from "react";
import { BarChart, Bar, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { PieChart, Pie, Sector} from 'recharts';
import StatistikaProizvodService from "../services/StatistikaProizvodService";

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];
const RADIAN = Math.PI / 180;
const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
  const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
  const x = cx + radius * Math.cos(-midAngle * RADIAN);
  const y = cy + radius * Math.sin(-midAngle * RADIAN);

  return (
    <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
      {`${(percent * 100).toFixed(0)}%`}
    </text>
  );
};

class ZzStatistikaPieComponent extends Component{

    constructor(props) {
        super(props)
        this.state = {
            mjeseciPrometi: []
        }
    }

    async componentDidMount() {
        let response = await StatistikaProizvodService.getPrometSvihProizvoda(this.props.match.params.godina)
        this.setState({
            mjeseciPrometi: response.mjeseciPrometi
        })
    }

    render() {
        return (
            <div>
        <br />
        <h3>Usporedba prometa po proizvodima - {this.props.match.params.godina}</h3>
      <ResponsiveContainer width="100%" aspect={3}>
        <PieChart width={400} height={400}>
          <Pie
            data={this.state.mjeseciPrometi}
            cx="50%"
            cy="50%"
            labelLine={false}
            label={renderCustomizedLabel}
            outerRadius={80}
            fill="#8884d8"
            nameKey="mjesec"
            dataKey="promet"
          >
            {this.state.mjeseciPrometi.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip/>
          <Legend/>
        </PieChart>
      </ResponsiveContainer>

        </div>
        )
    }

}

export default ZzStatistikaPieComponent