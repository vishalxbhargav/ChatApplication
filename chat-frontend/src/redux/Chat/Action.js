import { CREATE_CHAT, CREATE_GROUP, GET_USERS_CHAT } from "./ActionType";
import { BASE_URL } from "../config/Config";
import {toast} from 'react-hot-toast'
const token=localStorage.getItem("token");
export const createChat=(userId)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/api/chat/single`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+token
            },
            body:JSON.stringify(userId)
        })
        const resData=await res.json();
        console.log(resData);
        dispatch({type:CREATE_CHAT,payload:resData});
    }catch(error){
        toast.error(error.message);
        console.log(error)
    }
}

export const createGroupChat=(groupdata)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/api/chat/group`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+(groupdata.token),
            },
            body:JSON.stringify(groupdata.data)
        })
        const resData=await res.json();
        console.log(resData);
        dispatch({type:CREATE_GROUP,payload:resData});
    }catch(error){
        toast.error(error.message);
        console.log(error)
    }
}

export const getUsersChat=(groupdata)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/api/chat/user`,{
            method:"GET",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+(groupdata.token),
            },
        })
        const resData=await res.json();
        console.log(resData);
        dispatch({type:GET_USERS_CHAT,payload:resData});
    }catch(error){
        toast.error(error.message);
        console.log(error)
    }
}