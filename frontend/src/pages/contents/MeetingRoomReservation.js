import {
    Button,
    FormControl,
    FormControlLabel,
    FormLabel,
    Radio,
    RadioGroup,
    TextField,
    Typography
} from "@mui/material";
import React from "react";
import {DateTimePicker, LocalizationProvider} from "@mui/lab";
import DateAdapter from '@mui/lab/AdapterDateFns';
import axios from "axios";

export default function MeetingRoomReservation(){
    const [starttime, setstarttime] = React.useState(new Date('2022-01-01T00:00:00.000Z'));
    const [endtime, setendtime] = React.useState(new Date('2022-01-01T00:00:00.000Z'));
    const [title, settitle] = React.useState('ㅇㅇ`');
    const [group, setgroup] = React.useState('메가브레인');
    const [num, setnum] = React.useState(1);
    return (
        <>
            <Typography typography="h2">미팅 테이블 예약 시스템</Typography>
            <br/>
                <FormLabel id="demo-radio-buttons-group-label">사용목적</FormLabel>
                <TextField id="outlined-basic" variant="outlined" onChange={(event)=>settitle(event.target.value)}/>
                <br/>`
                <FormLabel id="demo-radio-buttons-group-label">동아리 선택</FormLabel>
                <RadioGroup
                    row
                    aria-labelledby="demo-radio-buttons-group-label"
                    defaultValue="mega"
                    name="radio-buttons-group"
                    onSelect={(event)=>setgroup(event.target.value)}
                >
                    <FormControlLabel value="mega" control={<Radio />} label="메가브레인" />
                    <FormControlLabel value="dot" control={<Radio />} label="돗가비" />
                    <FormControlLabel value="aug" control={<Radio />} label="AUG" />
                </RadioGroup>
                <br/>
                <FormLabel id="demo-radio-buttons-group-label">사용 예상 인원</FormLabel>
                <TextField
                    id="standard-number"
                    label="Number"
                    type="number"
                    onChange={(event)=>setnum(Number.valueOf(event.target.value))}
                    InputLabelProps={{
                        shrink: true,
                    }}
                    variant="standard"
                />
                <br/>
                <LocalizationProvider dateAdapter={DateAdapter}>
                    <DateTimePicker
                        renderInput={(props) => <TextField {...props} />}
                        value={starttime}
                        label="시작시간"
                        onChange={(newValue) => {
                            setstarttime(newValue);
                            console.log(starttime);
                        }}/>
                    <DateTimePicker
                        renderInput={(props) => <TextField {...props} />}
                        value={endtime}
                        label="종료시간"
                        format="YYYY-MM-DD H:i"
                        onChange={(newValue) => {
                            setendtime(newValue);
                            console.log(endtime);
                        }}
                    />
                </LocalizationProvider>
                <Button onClick={()=>{
                    const data = {
                        purpose: title,
                        team: group,
                        usingPeople: num,
                        starTime: starttime.toISOString(),
                        endTime: endtime.toISOString(),
                    };
                    axios.post('http://203.241.228.70:18080/api/v1/meeting', data, {
                        headers: {
                            'Access-Control-Allow-Origin': '*',
                        },
                    })
                        .then((Response)=>{
                            console.log(Response.data)
                            console.log(data)
                        })
                        .catch((Error)=>{console.log(Error); console.log(data);})
                }}>예약하기</Button>
        </>
    )
}