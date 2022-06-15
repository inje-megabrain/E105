import axios from "axios";

const apiLink = 'http://localhost:8080/api/v1';

export const GetBoardData = () => {
    const data = axios.get(apiLink + '/board',{
     })
    .then(response => {
        console.log(response);
        return response.data;
    })
    .catch(error => {
        alert(error);
    });
}