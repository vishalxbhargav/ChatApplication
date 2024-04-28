import React from 'react'
import { AiOutlineClose } from 'react-icons/ai'

export default function SelectedMember({handleRemoveMamber,member}) {
  return (
    <div className='flex items-center bg-slate-300 rounded-full'>
        <img className="w-7 h-7 rounded-full" src={member?.prifile_picture||'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png'} alt="" srcset="" />
        <p px-2>{member.full_name}</p>
        <AiOutlineClose onClick={()=>handleRemoveMamber()} className='pr-1 cursor-pointer'/>
    </div>
  )
}
