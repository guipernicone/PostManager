import styled from 'styled-components'

export const PostStyle = styled.div`
    display: flex;
    background-color: #1e1e1e;
    width:100%;
    height:10%;
    justify-content: center;
    align-items: center;

    .post-text{
        width:90%;
    }
    .upvote-icon{
        margin-left:10px;
        margin-right:10px;
        color: #bb86fc;
        cursor: pointer;
    }
`;