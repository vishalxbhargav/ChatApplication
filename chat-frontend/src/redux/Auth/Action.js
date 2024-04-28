import { LOGIN, LOGOUT, REGISTER, REQ_USER, SEARCH_USER, UPDATE_USER } from "./ActionType";
import {toast} from 'react-hot-toast'
import { BASE_URL } from '../config/Config';


export const register=(data)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/auth/signup`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        })
        const resData=await res.json();
        if(resData.jwt)localStorage.setItem('token',resData.jwt);
        console.log(resData);
        dispatch({type:REGISTER,payload:resData});
        toast.success("Your Account Successfully! Create");
    }catch(error){
        toast.error(error.message);
        console.log(error)
    }
}

export const login=(data)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/auth/login`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(data)
        })
        const resData=await res.json();
        if(localStorage.getItem("token")) localStorage.removeItem("token");
        if(resData.jwt)localStorage.setItem('token',resData.jwt);
        console.log(resData);
        dispatch({type:LOGIN,payload:resData});
        toast.success("Login Successfully!");
    }catch(error){
        toast.error(error.message);
        console.log(error)
    }
}

export const currentUser=(token)=>async (dispatch)=>{
    try{
        const res=await fetch(`${BASE_URL}/api/users/profile`,{
            method:"GET",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+token
            }
        })
        const resData=await res.json();
        console.log(resData);
        if(resData)
            dispatch({type:REQ_USER,payload:resData});
        else throw new Error("data is null")
        
    }catch(error){
        console.log(error)
    }
}

export const searchUser=(data)=>async(dispatch)=>{
    console.log(data);
    try{
        const res=await fetch(`${BASE_URL}/api/users/${data.keyword}`,{
            method:"GET",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+data.token
            },
        })
        const resData=await res.json();
        console.log(resData)
        dispatch({type:SEARCH_USER,payload:resData});
    }catch(error){
        console.log(error)
    }
}

export const updateUser=(updataData)=>async (dispatch)=>{
    console.log(updataData);
    try{
        const res=await fetch(`${BASE_URL}/api/users/update`,{
            method:"PUT",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+updataData.token
            },
            body:JSON.stringify(updataData.data)
        })
        const resData=await res.json();
        dispatch({type:UPDATE_USER,payload:resData});
    }catch(error){
        console.log(error)
    }
}
export const logout=()=>(dispatch)=>{
    localStorage.removeItem("token");
    dispatch({type:LOGOUT,payload:null});
    dispatch({type:REQ_USER,paylod:null})
}
