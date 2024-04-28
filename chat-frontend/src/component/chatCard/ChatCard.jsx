import React from 'react'

export default function ChatCard({name,userImg}) {
  return (
    <div className='flex justify-center items-center py-2 group cursor-pointer'>
        <div className='w-[20%]'>
            <img className='h-14 w-14 rounded-full' src={userImg} alt="image logo" srcset="" />
        </div>
        <div className='pl-5 w-[80%]'>
            <div className='flex justify-between items-center'>
                <p className='text-lg'>{name}</p>
                <p className='text-sm'>timestamp</p>
            </div>
            <div className='flex justify-between items-center'>
                <p>message..</p>
                <div className='flex space-x-2 items-center'>
                    <p className='text-xs p-1 px-2 text-white bg-green-500 rounded-full'>4</p>
                </div>
            </div>
        </div>
    </div>
  )
}
