import React from 'react'

export default function MessageCard({isRequestMesage,content}) {
  return (
    <div className={`py-2 px-2 rounded-md max-w-[50%] ${isRequestMesage ?"self-start bg-white" : "self-end bg-[#d9fdd3]"}`}>
        <p className='text-gray-400 '>{content}</p>
    </div>
  )
}
