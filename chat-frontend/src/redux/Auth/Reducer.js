import { searchUser, updateUser } from "./Action"
import { LOGIN, REGISTER, REQ_USER, SEARCH_USER, UPDATE_USER ,LOGOUT} from "./ActionType"

const initialValue={
    signup:null,
    signin:null,
    searchUser:[],
}
export const authReducer=(store=initialValue,{type,payload})=>{
    if(type==REGISTER) return {...store,signup:payload}
    else if(type==LOGIN) return {...store,signin:payload} 
    else if(type==REQ_USER) return{...store,reqUser:payload}
    else if(type==SEARCH_USER) return {...store,searchUser:payload}
    else if(type==UPDATE_USER) return{...store,updateUser:payload}
    else if(type==LOGOUT) return{...store,logout:payload}
    return store;
}