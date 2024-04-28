import React from 'react'
import StatusUserCard from './StatusUserCard'
import { useNavigate } from 'react-router-dom';
import { AiOutlineClose } from 'react-icons/ai'

export default function Status() {
    const navigate=useNavigate();
  return (
    <div>
        <div className='flex items-center px-[14vw] py-[7vh] '>
            <div className='left h-[85vh] bg-[#1e262c] lg:w-[30%] w-[50%] px-5'>
                <div>
                    <StatusUserCard/>
                </div>
                    <hr />
                    <div className='overflow-auto h-[86%] pt-2 '>
                    {
                        [1,1,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,11,1,11,1,1,].map((item)=><StatusUserCard/>)
                    }
                    </div>
                
            </div>
            <div className='relative h-[85vh] lg:w-[70%] w-[50%] bg-[#0b141a] '>
                    <AiOutlineClose onClick={()=>{navigate(-1)}} className='text-white cursor-pointer absolute top-5 right-10'/>
            </div>
        </div>
    </div>
  )
}
