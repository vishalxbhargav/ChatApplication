import { useEffect, useState } from "react";
import { story } from "./DummyStory";
import ProgressBar from "./ProgressBar";
import { BsArrowLeft } from "react-icons/bs";
import { AiOutlineClose } from "react-icons/ai";
import { useNavigate } from "react-router-dom";
const StatusViewer=()=>{
    const [storyIndex,setStoryIndex]=useState(0);
    const [activeIndex,setActiveIndex]=useState(0);
    const navigate=useNavigate();
    const handleNextStory=()=>{
        if(storyIndex<story?.length-1){
            setStoryIndex(storyIndex+1);
            setActiveIndex(activeIndex+1);
        }else{
            setStoryIndex(0);
            setActiveIndex(0);
        }

    }
    useEffect(()=>{
        const intervalId=setInterval(()=>{
            handleNextStory();
        },2000)
        return ()=>{
            clearInterval(intervalId);
        }
    },[storyIndex])
    return (
        <div>
            <div className=" relative flex justify-center items-center h-[100vh] bg-slate-900">
                <div className="relative">
                    <img className="max-h-[96vh] object-cover" src={story?.[storyIndex].image} alt="" srcset="" />
                    <div className="absolute top-0 flex w-full">
                        {story.map((item,index)=><ProgressBar key={index} duration={2000} index={index} activeIndex={activeIndex}/>)}
                    </div>
                </div>
                <div>
                    <BsArrowLeft onClick={()=>{navigate(-1)}} className="text-white tex-4xl cursor-pointer absolute top-10 left-10"/>
                    <AiOutlineClose onClick={()=>{navigate(-1)}} className="text-white tex-4xl cursor-pointer absolute top-10 right-10"/>
                </div>
            </div>
        </div>
    )
}
export default StatusViewer;