import React from 'react';
import CreateDOM from 'react-dom/client';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';

const rootElement = document.getElementById('root');
if (!rootElement) throw new Error('Failed to find the root element');
const root = CreateDOM.createRoot(rootElement);

const theme = createTheme({
  palette: {
    primary: {
      main: '#43a047',
    },
    secondary: {
      main: '#fb8c00',
    },
  },
  shape: {
    borderRadius: 20,
  },
});

root.render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
    </ThemeProvider>
  </React.StrictMode>
);