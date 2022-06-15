import { Avatar, List, ListItem, ListItemAvatar, ListItemText } from "@mui/material"
import React from "react"

export const Board = () => {
    const data = [{primary: 'a', secondary:'b', person: 'c'},
        {primary: 'a', secondary:'b', person: 'c'}];
    return(
        <>
            <h1>게시판</h1>
            <List>
                {data.map(({ primary, secondary, person }, index) => (
                <ListItem button key={index + person}>
                    <ListItemAvatar>
                    <Avatar alt="Profile Picture" src={person} />
                    </ListItemAvatar>
                    <ListItemText primary={primary} secondary={secondary} />
                </ListItem>
                ))}
            </List>
        </>
    )
}