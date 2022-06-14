import * as React from 'react';
import { Box, CssBaseline, BottomNavigation, BottomNavigationAction, Paper, AppBar, Button, IconButton, Toolbar, Typography }  from '@mui/material';
import { Info, Group, TableRestaurant, AccountCircle, LaptopMac, Login } from '@mui/icons-material';
import { Routes, Route } from 'react-router-dom';
import { MeetingTable } from './pages/components/MeetingTable';
import { Notice } from './pages/components/Notice';
import { NotFound } from './pages/components/NotFound';
import { TablePosition } from './pages/components/TablePosition';

export default function App() {
  const [tabBarSelect, setTabBarSelect] = React.useState('notice');

  const handleTabBarSelect = (event: React.SyntheticEvent, newValue: string) => {
    setTabBarSelect(newValue);
    
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
      <Routes>
        <Route path="/" element={<Notice />} />
        <Route path="/meetingtable/*" element={<MeetingTable />} />
        <Route path="/tablesetup/*" element={<TablePosition />} />
        <Route path="/account/*" element={<Login />} />
        <Route path="/*" element={<NotFound />} />
      </Routes>
      <Paper sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }} elevation={3}>
        <BottomNavigation
          value={tabBarSelect}
          onChange={handleTabBarSelect}
        >
          <BottomNavigationAction label="공지" value="notice" icon={<Info />} />
          <BottomNavigationAction label="미팅 테이블 예약" value="meetingtable" icon={<Group />} />
          <BottomNavigationAction label="자리 배치" value="tableposition" icon={<TableRestaurant />} />
          <BottomNavigationAction label="로그인" value="login" icon={<AccountCircle />} />
        </BottomNavigation>
      </Paper>
    </Box>
  );
}