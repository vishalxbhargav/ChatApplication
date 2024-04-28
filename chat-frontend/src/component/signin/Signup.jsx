import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import { useDispatch,useSelector} from "react-redux";
import { currentUser, register } from "../../redux/Auth/Action";

const Signup=()=>{
    const navigate=useNavigate();
    const token =localStorage.getItem("token");
    const [inputData,setInputData]=useState({full_name:"",email:"",password:""});
    const {auth}=useSelector(store=>store);
    const dispatch=useDispatch();
    const handlerSubmit=(e)=>{
        e.preventDefault();
        dispatch(register(inputData)); 
    }
    useEffect(()=>{
        if(token)dispatch(currentUser(token));
    },[token])

    useEffect(()=>{
        if(auth.reqUser?.full_name){
            navigate('/')
        }
    },[auth.reqUser]);
    return (
        <div>
            <div className="flex justify-center h-screen items-center ">
                <div className="w-[30%] p-10 shadow-md bg-white">
                    <form onSubmit={handlerSubmit} className="space-y-5">
                        <div>
                            <p className="mb-2">Full Name</p>
                            <input
                                placeholder="Enter your fullname"
                                name='full_name'
                                value={inputData.full_name}
                                onChange={(e)=>{setInputData({...inputData,full_name:e.target.value})}}
                                type="text"
                                className="py-2 bg-white outline outline-green-600 w-full rounded-md border"
                            />
                        </div>
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
                            <Button type='submit' className='w-full bg-green-600' variant='contained'> Sign Up</Button>
                        </div>
                    </form>
                    <div className="flex spacee-x-3 justify-evenly items-center mt-5 ">
                        <p className='m-0 text-sm'>alredy have account? </p>
                        <button className="text-gray-300 hover:text-blue-400" onClick={()=>{navigate("/signin")}}>
                            Sign In
                        </button>

                    </div>
                </div>

            </div>
        </div>
    )
}
export default Signup;