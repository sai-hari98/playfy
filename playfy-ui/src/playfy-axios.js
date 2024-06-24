import axios from 'axios';

const playfyAxios = axios.create({
    baseURL : "http://localhost:8080"
});

export default playfyAxios;