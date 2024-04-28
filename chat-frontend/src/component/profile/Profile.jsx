import { BsArrowLeft, BsPencil,BsCheck } from "react-icons/bs";
import { useNavigate} from "react-router-dom";
import {useState} from 'react';
import { useDispatch, useSelector } from "react-redux";
import { updateUser } from "../../redux/Auth/Action";
import { useEffect } from "react";

const Profile=()=>{
    const navigate=useNavigate();
    const dispatch=useDispatch();
    const [username,setUsername]=useState("");
    const [flag,setFlag]=useState(true);
    const [picture,setPicture]=useState(null);
    const {auth}=useSelector(store=>store);

    const cloudinaryUpload = async (pics) => {
        const formData = new FormData();
        formData.append('file', pics);
        formData.append('upload_preset', 'bnwuewiu'); // Replace with your actual Cloudinary preset
        formData.append('cloud_name', 'vishalxbhargav'); // Replace with your Cloudinary cloud nam
        fetch(`https://api.cloudinary.com/v1_1/vishalxbhargav/image/upload`,{
              method: 'POST',
              body: formData,
        }).then((res)=>res.json())
        .then((data)=>{
            setPicture(data.url.toString());
            console.log("image",data.url.toString());
        })
      };
    const updateUsername=()=>{
        setFlag(!flag);
        if(username) dispatch(updateUser({token:localStorage.getItem('token'),data:{full_name:username}}))
    }
    useEffect(()=>{
    if(picture) dispatch(updateUser({
            token:localStorage.getItem('token'),
            data:{profile_picture:picture,full_name:auth.reqUser.full_name}
        }))
    },[picture])
    console.log(auth.reqUser);
    return(
        <div className="w-full h-full">
            <div className="flex items-center space-x-10 bg-[#008069] text-white pt-16 px-10 pb-5">
                <BsArrowLeft className="cursor-pointer text-2xl font-bold " onClick={()=>{navigate(0)}}/>
                <p className="cursor-pointer font-semibold">Profile</p>
            </div>
            <div className="flex justify-center items-center p-10">
                <label htmlFor="imgInput">
                    <img className="rounded-full w-[15vw] h-[15vw] cursor-pointer" src={picture || auth.reqUser?.profile_picture || "https://image.shutterstock.com/image-photo/astronaut-light-260nw-549784012.jpg"} alt="" srcset="" />
                </label>
                <input onChange={(e)=>cloudinaryUpload(e.target.files[0])} type="file" className="hidden" id="imgInput" />
            </div>
            <div className="bg-white px-3">
                <p className="py-3">Your name</p>
               { flag && <div className="w-full flex justify-between items-center">
                    <p className="py-3">{auth.reqUser.full_name}</p>
                    <BsPencil className="cursor-pointer" onClick={()=>setFlag(!flag)} />
                </div>}
                { !flag&&<div className="w-full flex justify-between items-center">
                    <input type="text" onChange={(e)=>setUsername(e.target.value)} name="" className="w-[80%] outline-none border-none bg-white border-b-2 border-blue-700 p-2" placeholder="enter your name" />
                    <BsCheck className="cursor-pointer" onClick={updateUsername}/>
                </div>}

            </div>
            <div className="px-3 my-5">
                <p className="py-10">this is not your username this name will be visible to your WhatsApp contects</p>
            </div>
        </div>
    )
}
export default Profile;