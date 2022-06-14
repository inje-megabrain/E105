import { Avatar, List, ListItem, ListItemAvatar, ListItemText } from "@mui/material"
import React from "react"

export const Notice = () => {
    const data = ['a', 'b'];
    return(
        <>
            <List>
                {/* {data.map(({ primary, secondary, person }, index) => (
                <ListItem button key={index + person}>
                    <ListItemAvatar>
                    <Avatar alt="Profile Picture" src={person} />
                    </ListItemAvatar>
                    <ListItemText primary={primary} secondary={secondary} />
                </ListItem>
                ))} */}
            </List>
        </>
    )
}