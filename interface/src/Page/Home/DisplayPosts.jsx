import React from 'react';
import { DisplayPostsStyle } from 'Page/Home/Style/DisplayPostsStyle'
import Pagination from '@material-ui/lab/Pagination';
import Post from 'Page/Home/Post';

const DisplayPosts = ({posts, pageNumber, handleUpvote, handlePagination}) => {

    const buildPosts = () => {
        return posts.map((post, index) =>{
            return <Post key={"post_" + index} postId={post.id} text={post.text} upvote={post.upvote} handleUpvote={handleUpvote}/>
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