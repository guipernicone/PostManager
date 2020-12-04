import React from 'react';
import { Form, Button } from 'react-bootstrap';
import { NewPostStyle } from 'Page/Home/Style/NewPostStyle';
import SendIcon from '@material-ui/icons/Send';

/**
 * New post Section
 * 
 * @param function handlePost A function responsible to handle creation of a new post
 */
const NewPost = ({handlePost}) => {

    return (
        <NewPostStyle>
            <Form className="post-form" onSubmit={(e) => {handlePost(e)}}>
                <Form.Group controlId="formBasicPost" className="post-input">
                    <Form.Control type="text" placeholder="Insira seu comentario"/>
                </Form.Group>
                <Button type="submit" className="post-submit">
                    <SendIcon className="submit-icon"/>
                </Button>
            </Form>
        </NewPostStyle>
        
    );
};

export default NewPost;