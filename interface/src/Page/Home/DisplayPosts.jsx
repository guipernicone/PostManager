import React from 'react';
import { DisplayPostsStyle } from 'Page/Home/Style/DisplayPostsStyle'
import Pagination from '@material-ui/lab/Pagination';
import Post from 'Page/Home/Post';

/**
 * Post display section
 * 
 * @param String posts An array containing the posts
 * @param String pageNumber The max number of pages
 * @param function handleUpvote A function responsible to handle the upvote
 * @param function handlePagination A function responsible to handle the pagination
 */
const DisplayPosts = ({posts, pageNumber, handleUpvote, handlePagination}) => {

    /**
     * Build the post list
     */
    const buildPosts = () => {
        return posts.map((post, index) =>{
            let date = new Date(post.date)
            let formattedDate = date.getDate() + '/' + date.getMonth() + '/' + date.getFullYear() + ' ' +
                                date.getHours() + ':'+ date.getMinutes() + ':' + date.getSeconds()
                                
            return <Post key={"post_" + index} postId={post.id} text={post.text} upvote={post.upvote} date={formattedDate} handleUpvote={handleUpvote}/>
        }) 
    }

    return (
        <DisplayPostsStyle>
            <div className="post-list">
                {buildPosts()}
            </div>
            <Pagination count={pageNumber} variant="outlined" color="primary" className="pagination" onChange={(event, value) => handlePagination(value -1)}/>
        </DisplayPostsStyle>
    );
};

export default DisplayPosts;