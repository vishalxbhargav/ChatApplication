import React from 'react'
import {Route,Routes} from 'react-router-dom'
import HomePage from './component/HomePage'
import Status from './component/status/Status'
import StatusViewer from './component/status/StatusViewer'
import Signin from './component/signin/Signin'
import Signup from './component/signin/Signup'
import {Toaster} from 'react-hot-toast'

function App() {
  return (
    <div className="text-black">
      <Routes>
        <Route path='/' exact element={<HomePage/> }/>
        <Route path='/signin' exact element={<Signin/> }/>
        <Route path='/signup' exact element={<Signup/> }/>
        <Route path='/status' exact element={<Status/>}/>
        <Route path='/status/:userId' exact element={<StatusViewer/>}/>
      </Routes>
      <Toaster/>
    </div>
  )
}

export default App
