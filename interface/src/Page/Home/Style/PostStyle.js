import styled from 'styled-components'

export const PostStyle = styled.div`
    display: flex;
    background-color: #1e1e1e;
    width:100%;
    height:10%;
    justify-content: center;
    align-items: center;
    border-bottom: 2px solid rgb(18, 18, 18, 0.5);

    .post-text{
        width:75%;
    }
    .post-date{
        width:15%;
        opacity:0.2;
    }
    .upvote-icon{
        margin-left:10px;
        margin-right:10px;
        color: #bb86fc;
        cursor: pointer;
    }
`;