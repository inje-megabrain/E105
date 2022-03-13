import * as React from 'react';
import { Box, Drawer, CssBaseline, AppBar, Toolbar, List, Typography, Divider, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import { MoveToInbox, Mail } from '@mui/icons-material';
import SeatTable from './pages/contents/SeatTable';

const drawerWidth = 240;

function App() {
  return (
      <Box className="App" sx={{ display: 'flex' }}>
        <CssBaseline />
        <AppBar
            position="fixed"
            sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}
        >
          <Toolbar>
            <Typography variant="h6" noWrap component="div">
              E105
            </Typography>
          </Toolbar>
        </AppBar>
        <Drawer
            sx={{
              width: drawerWidth,
              flexShrink: 0,
              '& .MuiDrawer-paper': {
                width: drawerWidth,
                boxSizing: 'border-box',
              },
            }}
            variant="permanent"
            anchor="left"
        >
          <Toolbar />
          <Divider />
          <List>
            {['게시판', '미팅 테이블 예약', '자리 배정', 'IP 테이블'].map((text, index) => (
                <ListItem button key={text}>
                  <ListItemIcon>
                    {index % 2 === 0 ? <MoveToInbox /> : <Mail />}
                  </ListItemIcon>
                  <ListItemText primary={text} />
                </ListItem>
            ))}
          </List>
        </Drawer>
        <Box
            component="main"
            sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}
        >
          <Toolbar />
            <SeatTable />
        </Box>
      </Box>
  );
}

export default App;
