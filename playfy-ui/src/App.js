import './App.css';
import PageLayout from "./containers/PageLayout/PageLayout";
import { BrowserRouter } from "react-router-dom";
import { Provider } from 'react-redux';
import { thunk } from 'redux-thunk';
import { configureStore } from '@reduxjs/toolkit';
import authReducer from './store/reducers/auth';
import { compose } from '@reduxjs/toolkit';
function App() {
  const composeEnhancers = process.env.NODE_ENV === 'development' ? window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ : null || compose;
  const store = configureStore({ reducer: authReducer, middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(thunk), composeEnhancers: composeEnhancers });
  return (
    <div className="container">
      <Provider store={store}>
        <BrowserRouter>
          <PageLayout />
        </BrowserRouter>
      </Provider>
    </div>
  );
}

export default App;
