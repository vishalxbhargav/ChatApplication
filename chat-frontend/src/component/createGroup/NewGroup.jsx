import { Button, CircularProgress } from '@mui/material';
import React, { useState } from 'react'
import { BsArrowLeft, BsCheck2 } from 'react-icons/bs';
import { useDispatch } from 'react-redux';
import { createGroupChat } from '../../redux/Chat/Action';
export default function NewGroup({groupMember,setIsGroup}) {
    const dispatch=useDispatch();
    const [isImageUploading,SetImageUploading]=useState(null);
    const [groupImage,setGroupImage]=useState(null);
    const [groupName,setGroupName]=useState("");
    const cloudinaryUpload = async (pics) => {
        SetImageUploading(true);
        const formData = new FormData();
        formData.append('file', pics);
        formData.append('upload_preset', 'bnwuewiu'); // Replace with your actual Cloudinary preset
        formData.append('cloud_name', 'vishalxbhargav'); // Replace with your Cloudinary cloud nam
        fetch(`https://api.cloudinary.com/v1_1/vishalxbhargav/image/upload`,{
              method: 'POST',
              body: formData,
        }).then((res)=>res.json())
        .then((data)=>{
            setGroupImage(data.url.toString());
            console.log(data.url.toString())
            SetImageUploading(false);
        })
      };
    const handleCreateGroup=()=>{
        let users=[];
        for(let user of groupMember) users.push(user.id);
        console.log(groupName)
        const data={
            users,
            chat_name:groupName,
            chat_image:groupImage,
        }
        const groupdata={data,token:localStorage.getItem('token')}
        dispatch(createGroupChat(groupdata))
        setIsGroup(false);
    }
    console.log(groupMember)
  return (
    <div className='w-full h-full'>
        <div className='flex items-center space-x-10 bg-[#008059] text-white pt-16 px-10 pb-5'>
            <BsArrowLeft className='cursor-pointer text-2xl font-bold'/>
            <p className="text-xl font-semibold">New Group</p>
        </div>
        <div className='flex flex-col justify-center items-center my-12 '>
            <label htmlFor="imgInput"className='relative'>
                <img className='w-[5rem] h-[5rem] rounded-full' src={ groupImage ||"https://thumbs.dreamstime.com/z/people-flat-icon-group-round-colorful-button-team-circular-vector-sign-shadow-effect-style-design-92997577.jpg?w=768"} alt="" srcset="" />
                {isImageUploading&&<CircularProgress className='absolute top-[5rem] left-[6rem]'/>}
            </label>
            <input type="file" id='imgInput' className='hidden' onChange={(e)=>cloudinaryUpload(e.target.files[0])} />

        </div>
        <div className='w-full flex justify-between items-center px-5 py-2'>
            <input className='w-full outline-none border-b-2 border-green-700 px-2 bg-transparent ' placeholder="group subject" value={groupName} type="text" onChange={(e)=>setGroupName(e.target.value)}/>
        </div>
        {groupName&& <div className='py-10 bg-slate-200 flex items-center justify-center'>
                <Button onClick={handleCreateGroup}>
                    <div className='bg-[#0c977d] rounded-full p-4'>
                    <BsCheck2 className='text-white font-bold text-3xl'/>
                    </div>
                </Button>
            </div>
        }
    </div>
  )}
