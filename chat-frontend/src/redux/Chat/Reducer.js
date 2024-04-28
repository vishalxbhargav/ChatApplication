import { CREATE_CHAT, CREATE_GROUP, GET_USERS_CHAT } from "./ActionType"

const initialValue={
    chats:[],
    createdgroup:null,
    createdchat:null,
}

export const chatReducer=(store=initialValue,{type,payload})=>{
    if(type==CREATE_CHAT) return {...store,createdchat:payload}
    else if(type==CREATE_GROUP) return {...store,createdgroup:payload}
    else if(type==GET_USERS_CHAT) return {...store,chats:payload}
    else return store;
}