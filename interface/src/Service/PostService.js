import {sendPost, sendGet, sendPatch} from "Service/HttpRequest"

/**
 * Send a request to create a new post
 * 
 * @param {String} post The post text
 * 
 * @returns The request promise
 */
export const sendNewPost = (post) => {

    let params = {
        text:  post,
    }
    let route = 'post'
    
    return sendPost(params, route)
}

/**
 * Send a request to get the post of the given page
 * 
 * * @param {String} page The page number
 * 
 * @returns The request promise
 */
export const getPosts = (page) => {

    let params = {
        page:  page,
        size: 10
    }
    let route = 'post'
    
    return sendGet(params, route)
}

/**
 * Send a request to update the upvote from a given post ID
 * 
 * * @param {String} id The post ID
 * 
 * @returns The request promise
 */
export const sendUpvote = (id) => {
    let route = 'post/' + id    
    return sendPatch({}, route)
}