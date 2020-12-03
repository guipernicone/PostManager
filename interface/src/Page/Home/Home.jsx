import React, { useState, useEffect} from 'react';
import 'Common/css/PostManager.css'
import { IndexStyle } from 'Page/Home/Style/IndexStyle'
import NewPost from 'Page/Home/NewPost';
import DisplayPosts from 'Page/Home/DisplayPosts';
import { sendNewPost, getPosts, sendUpvote } from 'Service/PostService';

/**
 * Post Manager home page
 */
const Home = () => {

    const [content, setContent] = useState({});

    useEffect(() => {
        if (!Object.keys(content).length > 0)
        {  
            sendGetPosts(0)
        }
    },[content])


    const sendGetPosts = (page) => {
        getPosts(page)
        .then((response) => {
            if (response.status === 200) {
                return setContent(response.data);
            }
            return setContent({'noPost': true})
        })
        .catch((error) => {
            console.log(error);
        })
    }

    const handlePagination = (page) => {
        sendGetPosts(page);
    } 

    const handleUpvote = (postId) => {
        sendUpvote(postId)
        .then((response) => {
            if (response.status === 200) {
                return setContent({});
            }
            return setContent({'noPost': true})
        })
        .catch((error) => {
            console.log(error);
        })
    } 

    const handlePost = (submit) =>{
        submit.preventDefault();
        submit.stopPropagation();
        let post = submit.target.elements.formBasicPost.value;
        console.log(post);

        sendNewPost(post)    
        .then((response) => {
            console.log(response);
            if (response.status === 201) {
                return setContent({});
            }
        })
        .catch((error) => {
            console.log(error);
        });
    }

    return (
        <div className="pm-background">
            <IndexStyle>
                <div className="post-display">
                    {console.log(content)}
                    {content !== undefined && Object.keys(content).length > 0 && !content.hasOwnProperty("noPost")? 
                        <DisplayPosts 
                            posts={content.content}
                            pageNumber={content.totalPages}
                            handlePagination={handlePagination}
                            handleUpvote={handleUpvote}
                        /> 
                    : null}
                </div>
                <div className="post-new">
                    <NewPost handlePost={handlePost}/>
                </div>
            </IndexStyle>
        </div>
    );
};

export default Home;