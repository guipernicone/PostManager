import React from 'react';
import { PostStyle } from 'Page/Home/Style/PostStyle';
import NavigationRoundedIcon from '@material-ui/icons/NavigationRounded';

/**
 * Post component
 * 
 * @param function text The post text
 * @param function upvote The post upvote Number
 * @param function postId The post ID
 * @param function handleUpvote A function responsible to handle the update upvote
 */
const Post = ({text, upvote, postId, handleUpvote}) => {
    return (
        <PostStyle>
            <div className="post-text">{text}</div>
            <div className="post-upvote-buttom" onClick={() => handleUpvote(postId)}><NavigationRoundedIcon className="upvote-icon"/></div>
            <div className="post-upvote">{upvote}</div>
        </PostStyle>
    );
};

export default Post;