import {CREATE_NEW_MESSAGE, GET_ALL_MESSAGE } from "./ActionType";
import { BASE_URL } from "../config/Config";

export const createMessage=(messagedata)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/api/messages/create`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+(messagedata.token),
            },
            body:JSON.stringify(messagedata.data)
        })
        const resData=await res.json();
        console.log(resData);
        dispatch({type:CREATE_NEW_MESSAGE,payload:resData});
    }catch(error){
        toast.error(error.message);
        console.log(error)
    }
}
export const getAllMessage=(messagedata)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/api/messages/chat/${messagedata.chatId}`,{
            method:"GET",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+(messagedata.token),
            },
        })
        const resData=await res.json();
        console.log(resData);
        dispatch({type:GET_ALL_MESSAGE,payload:resData});
    }catch(error){
        toast.error(error.message);
        console.log(error)
    }
}
