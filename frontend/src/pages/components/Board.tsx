import { Avatar, List, ListItem, ListItemAvatar, ListItemText } from "@mui/material"
import React, { lazy } from "react"
import { Loading } from "./Loading";
import { GetBoardData } from "../api/API";
export const Board = () => {
    const data = [{primary: 'a', secondary:'b', person: 'c'},
        {primary: 'a', secondary:'b', person: 'c'}];
    return(
        <>
            <h1>게시판</h1>
            <Loading />
        </>
    )
}