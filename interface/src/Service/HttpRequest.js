import axios from 'axios';
import  { serverIP, serverPort } from 'Config/Config'

/**
 * Send a http post request
 * 
 * @param {String} params A JSON Object containing the request parameters
 * @param {String} route The route to send the request
 */
export const sendPost = (params, route) => {
    let config = {
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        validateStatus: () => true
    }
    let convertedParams = convertObjectToRouteParams(params);
    return axios.post(`http://${serverIP}:${serverPort}/${route}?${convertedParams}`, convertedParams, config)
    .then((response) => {
        return response;
    })
    .catch((error) => {
        console.log(error);
        throw error;
    })
}

/**
 * Send a http get request
 * 
 * @param {String} params A JSON Object containing the request parameters
 * @param {String} route The route to send the request
 */
export const sendGet = (params, route) => {
    let config = {
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        validateStatus: () => true
    }
    let convertedParams = convertObjectToRouteParams(params);
    return axios.get(`http://${serverIP}:${serverPort}/${route}?${convertedParams}`, config)
    .then((response) => {
        return response;
    })
    .catch((error) => {
        console.log(error);
        throw error;
    })
}

/**
 * Send a http patch request
 * 
 * @param {String} params A JSON Object containing the request parameters
 * @param {String} route The route to send the request
 */
export const sendPatch = (body, route) => {
    let config = {
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        validateStatus: () => true
    }
    return axios.patch(`http://${serverIP}:${serverPort}/${route}`, body, config)
    .then((response) => {
        return response;
    })
    .catch((error) => {
        console.log(error);
        throw error;
    })
}

/**
 * Convert a JSON Object to a route params
 * 
 * Ex: {"key" : "value"} => key=value
 * 
 * @param {Object} object 
 */
function convertObjectToRouteParams(object) {
    let paramConverted = '';

    let length = Object.keys(object).length;

    let i = 0;
    for (let key in object) {

        paramConverted += key + '=' + object[key];
        i++;
        if (length !== i) {
            paramConverted += '&';
        } 
    }
    return paramConverted;
}