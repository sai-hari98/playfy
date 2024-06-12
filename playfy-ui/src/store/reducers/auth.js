import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    userId : null
}

const authSlice = createSlice(
    {
        name : 'auth',
        initialState,
        reducers: {
            logIn(state, action){
                const {id, userId} = action.payload;
                state.userId = userId;
            },
            logOut(state, action){
                state.userId = null;
            }
        }
    }
);

export const {logIn, logOut} = authSlice.actions;
export default authSlice.reducer;