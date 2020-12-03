import styled from 'styled-components'

export const IndexStyle = styled.div`
    background-color: #121212;
    /* background-color: red; */
    width: 1200px;
    height: 800px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display:flex;
    flex-direction: column;

    .post-display{
        width: calc(100% - 40px);
        height:95%;
        margin:20px;
        background-color: #454545;
    }

    .post-new{
        position: relative;
        width: calc(100% - 40px);
        height: 5%;
        margin:20px;
        margin-top: 0px;
    }
`