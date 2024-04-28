import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import {toast} from 'react-hot-toast'
import { useDispatch, useSelector } from "react-redux";
import { currentUser, login } from "../../redux/Auth/Action";

const Signin=()=>{
    const navigate=useNavigate();
    const [inputData,setInputData]=useState({email:"",password:""});
    const {auth}=useSelector(store=>store);
    console.log(auth.reqUser);
    const token=localStorage.getItem('token');
    const dispatch=useDispatch();
    const handlerSubmit=(e)=>{
        e.preventDefault();
        dispatch(login(inputData));
    }
    useEffect(()=>{
        if(token) dispatch(currentUser(token));
    },[token]);
    useEffect(()=>{
        if(auth.reqUser) navigate("/");
    },[auth.reqUser])
    return (
        <div>
            <div className="flex justify-center h-screen items-center ">
                <div className="w-[30%] p-10 shadow-md bg-white">
                    <form onSubmit={handlerSubmit} className="space-y-5">
                        <div>
                            <p className="mb-2">Email</p>
                            <input
                                placeholder="Enter your email"
                                name='email'
                                value={inputData.email}
                                onChange={(e)=>{setInputData({...inputData,email:e.target.value})}}
                                type="email"
                                className="py-2 bg-white outline outline-green-600 w-full rounded-md border"
                            />
                        </div>
                        <div>
                            <p className="mb-2">Password</p>
                            <input
                                placeholder="Enter your password"
                                name='password'
                                value={inputData.password}
                                onChange={(e)=>{setInputData({...inputData,password:e.target.value})}}
                                type="password"
                                className="py-2 bg-white outline outline-green-600 w-full rounded-md border"
                            />
                        </div>
                        <div>
                            <Button type='submit' className='w-full bg-green-600' variant='contained'> Sign In</Button>
                        </div>
                    </form>
                    <div className="flex spacee-x-3 justify-evenly items-center mt-5 ">
                        <p className='m-0'>create new account? </p>
                        <button className="text-sm text-gray-400 hover:text-blue-400" onClick={()=>{navigate("/signup")}}>
                            Sign Up
                        </button>

                    </div>
                </div>

            </div>
        </div>
    )
}
export default Signin;