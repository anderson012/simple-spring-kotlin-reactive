import { SnackbarProvider } from 'notistack'
import '../styles/globals.css'

function MyApp({ Component, pageProps }) {
  return <SnackbarProvider maxSnack={3}><Component {...pageProps} /></SnackbarProvider>
}

export default MyApp
