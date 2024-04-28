package com.vishalxbhargav.chatbackend.service.imp;

import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.Chat;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.repository.ChatRepository;
import com.vishalxbhargav.chatbackend.request.GroupChatRequest;
import com.vishalxbhargav.chatbackend.service.ChatService;
import com.vishalxbhargav.chatbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Override
    public Chat createChat(User reqUser, Integer user2) throws UserException {
        User user =userService.findUserById(user2);
        Chat chat =chatRepository.findSingleChatByUserIds(reqUser,user);
        if(chat!=null) return chat;
        Chat newChat=new Chat();
        newChat.setCreatedBy(reqUser);
        newChat.getUsers().add(user);
        newChat.getUsers().add(reqUser);
        newChat.setGroup(false);
        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {
        Optional<Chat> chat =chatRepository.findById(chatId);
        if(chat.isPresent()) return chat.get();
        throw new ChatException("Chat not found with id "+chatId);
    }

    @Override
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException {
        User user=userService.findUserById(userId);
        return chatRepository.findAllChatByUserId(user.getId());
    }

    @Override
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
        Chat group =new Chat();
        group.setGroup(true);
        group.setChat_image(req.getChat_image());
        group.setChat_name(req.getChat_name());
        group.setCreatedBy(reqUser);
        group.getAdmins().add(reqUser);
        group.getUsers().add(reqUser);
        for(Integer userId:req.getUsers())
            group.getUsers().add(userService.findUserById(userId));
        return chatRepository.save(group);
    }
    @Override
    public Chat addUserToGroup(Integer userId, Integer chatId,User reqUser) throws UserException, ChatException {
          Optional<Chat> opt =chatRepository.findById(chatId);
          User user = userService.findUserById(userId);
          if(opt.isPresent()){
              Chat chat=opt.get();
              if(chat.getAdmins().contains(reqUser)){
                  chat.getUsers().add(user);
                  return chatRepository.save(chat);
              }else throw new UserException("You are not admin");
          }
          throw new ChatException("Chat not found wiht id "+chatId);
    }

    @Override
    public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt =chatRepository.findById(chatId);
        if(opt.isPresent()){
            Chat chat=opt.get();
            if(chat.getUsers().contains(reqUser)){
               chat.setChat_name(groupName);
               return chatRepository.save(chat);
            }else throw new UserException("You are not mamber of the group");
        }
        throw new ChatException("Chat not found wiht id "+chatId);
    }

    @Override
    public Chat removeFromGroup(Integer chatId, Integer userId, User reqUser) throws UserException, ChatException {
        Optional<Chat> opt =chatRepository.findById(chatId);
        User user = userService.findUserById(userId);
        if(opt.isPresent()){
            Chat chat=opt.get();
            if(chat.getAdmins().contains(reqUser)){
                chat.getUsers().remove(user);
                return chatRepository.save(chat);
            }else if(chat.getUsers().contains(reqUser)){
                if(user.getId().equals(reqUser.getId())){
                    chat.getUsers().remove(user);
                    return chatRepository.save(chat);
                }
            }throw new UserException("You are not admin");
        }
        throw new ChatException("Chat not found wiht id "+chatId);
    }

    @Override
    public void deleteChat(Integer chatId, Integer userId) throws UserException, ChatException {
        Optional<Chat> opt =chatRepository.findById(chatId);
        opt.ifPresent(chat -> chatRepository.deleteById(chat.getId()));
    }
}
