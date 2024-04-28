import React, { useState } from 'react'
import {BsArrowLeft, BsArrowRight} from 'react-icons/bs'
import { useNavigate } from 'react-router-dom';
import ChatCard from '../chatCard/ChatCard'
import NewGroup from './NewGroup'
import SelectedMember from './SelectedMember'
import { useDispatch, useSelector} from 'react-redux'
import { searchUser } from '../../redux/Auth/Action';
export default function CreateGroup({setIsGroup}) {
    const dispatch=useDispatch();
    const [newGroup,setNewGroup]=useState(false);
    const [query,setQuery]=useState("");
    const [groupMember,setGroupMember]=useState(new Set());
    const navigate=useNavigate();
    const {auth}=useSelector(store=>store)
    const handleRemoveMamber=(item)=>{
        groupMember.delete(item);
        setGroupMember(groupMember);
    }
    const handleSearch=(keyword)=>{
        if(keyword)
            dispatch(searchUser({keyword,token:localStorage.getItem('token')}))
    }
    console.log(auth.searchUser,groupMember)
  return (
    <div className='w-full h-full'>
        {
            !newGroup&&(
                <div>
                    <div className='flex items-center space-x-10 bg-[#008069] text-white pt-16 px-10 pb-5'>
                        <BsArrowLeft onClick={()=>{navigate(-1)}} className="cursor-pointer text-2xl font-bold"/>
                        <p className='text-xl font-semibold '>Add Group Participats</p>
                    </div>
                    <div className='relative bg-white py-4 px-3'>
                        <div className="flex space-x-2 flex-wrap space-y-1">
                            {
                                groupMember.size>0&&
                                    Array.from(groupMember).map((item)=>(
                                        <SelectedMember handleRemoveMamber={()=>{handleRemoveMamber(item)}} member={item}/>
                                    ))
                            }
                        </div>
                        <input type="text" onChange={(e)=>{
                            handleSearch(e.target.value);
                            setQuery(e.target.value);
                            console.log(e.target.value)
                            }}
                            className="outline-none bg-white border-b border-[#8888] p-2 w-[93%]"
                            placeholder='search user'
                            value={query}
                        />
                    </div>
                    <div className='bg-white overflow-y-scroll h-[50.2vh] '>
                            {query&&auth.searchUser?.map((item)=>(
                                <div onClick={()=>{
                                    groupMember.add(item)
                                    setGroupMember(groupMember)
                                    setQuery("")
                                }} key={item?.id}>
                                    <hr />
                                    <ChatCard
                                        name={item.full_name}
                                        userImg={
                                        item.profile_picture||
                                        'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png'
                                        }
                                        />
                                </div>
                            ))}
                    </div>
                    <div className='botom-10 py-10 bg-slate-200 flex items-center justify-center'>
                            <div className="bg-green-600 rounded-full p-4 cursor-pointer" onClick={()=>{setNewGroup(true)}}>
                                <BsArrowRight className='text-white font-bold text-3xl'/>
                            </div>
                    </div>
                </div>
            )
        }
        {
            newGroup&&
                <NewGroup setIsGroup={setIsGroup} groupMember={groupMember}/>
        }
        
    </div>
  )
}
