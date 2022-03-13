import {Box, Button, Card, CardActions, CardContent, Grid, Paper, Typography} from "@mui/material";
import axios from "axios";
import React, {useEffect, useState} from "react";

const getSeatInfo = async() => {
    let seatinfo;
    await axios
        .get('http://192.168.0.7:8080/api/v1/seat', {
            headers: {
                'Access-Control-Allow-Origin': "*",
            }
        })
        .then((Response)=>{
            seatinfo = Response.data;
            console.log(Response.data)
        })
        .catch((Error)=>{console.log(Error)})
    return seatinfo;
}

function card(seat){
    return (
        <Grid item xs={6}>
            <Card sx={{minWidth: 275}} variant="outlined" disabled={seat.status==='ASSIGN'}>
                <CardContent>
                    <Typography variant="h5" component="div">
                        { seat.position }
                    </Typography>
                    <Typography variant="body1" component="div">
                        { seat.team } <br/>
                        { seat.createdTime } <br />
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button size="small" disabled={seat.status==='ASSIGN'}>신청하기</Button>
                </CardActions>
            </Card>
        </Grid>
    );
}

export default function SeatTable(){
    let [info, changeinfo] = useState("");
    useEffect(() => {
        changeinfo(getSeatInfo)
    }, [])
    console.log(info);
    return(
        <>
            <Box padding={4}>
                <Grid container spacing={2}>
                    {['A1', 'A2', 'A3', 'A4'].map((seatinfo) => (
                         <></>
                        )
                    )
                    }
                </Grid>
            </Box>
        </>
    )
}
