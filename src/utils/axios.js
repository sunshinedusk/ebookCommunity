import axios from "axios";
import { Message } from "element-ui";

const service=axios.create({
    baseURL:'http://localhost:8090',
    timeout:5000
})



export default service