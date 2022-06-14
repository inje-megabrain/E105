import * as React from 'react';
import { Box, CssBaseline, BottomNavigation, BottomNavigationAction, Paper, AppBar, Container, IconButton, Toolbar, Typography }  from '@mui/material';
import { Info, Group, TableRestaurant, AccountCircle, LaptopMac } from '@mui/icons-material';
import { Routes, Route, useNavigate } from 'react-router-dom';
import { MeetingTable } from './pages/components/MeetingTable';
import { Notice } from './pages/components/Notice';
import { Login } from './pages/accounts/Login';
import { NotFound } from './pages/components/NotFound';
import { TablePosition } from './pages/components/TablePosition';

export default function App() {
  const [tabBarSelect, setTabBarSelect] = React.useState('/');

  const navigate = useNavigate();
  
  const handleTabBarSelect = (event: React.SyntheticEvent, newValue: string) => {
    if (newValue !== tabBarSelect){
      navigate(newValue);
      setTabBarSelect(newValue);
    }
  };

  return (
    <Box sx={{ pb: 7 }}>
      <CssBaseline />
      <AppBar position="static">
        <Toolbar>
          <LaptopMac />
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            E105 관리 시스템
          </Typography>
        </Toolbar>
      </AppBar>
      <Container maxWidth="sm">
      <Routes>
        <Route path="/" element={<Notice />} />
        <Route path="/meetingtable/*" element={<MeetingTable />} />
        <Route path="/tablesetup/*" element={<TablePosition />} />
        <Route path="/account/*" element={<Login />} />
        <Route path="/*" element={<NotFound />} />
      </Routes>
      </Container>
      <Paper sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} elevation={3}>
        <BottomNavigation
          value={tabBarSelect}
          onChange={handleTabBarSelect}
        >
          <BottomNavigationAction label="공지" value="/" icon={<Info />} />
          <BottomNavigationAction label="미팅 테이블 예약" value="/meetingtable" icon={<Group />} />
          <BottomNavigationAction label="자리 배치" value="/tablesetup" icon={<TableRestaurant />} />
          <BottomNavigationAction label="로그인" value="/account" icon={<AccountCircle />} />
        </BottomNavigation>
      </Paper>
    </Box>
  );
}