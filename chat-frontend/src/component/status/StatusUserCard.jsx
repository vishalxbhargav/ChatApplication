import React from 'react'

export default function StatusUserCard() {
  return (
    <div className='flex items-center p-3'>
        <div>
            <img className='h-7 w-7 lg:w-10 rounded-full' src="https://images.pexels.com/photos/21715866/pexels-photo-21715866/free-photo-of-fishermans-bastion.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"  alt="" srcset="" />
        </div>
        <div className='ml-2 text-white'>
            <p>tony stark</p>
        </div>
    </div>
  )
}
