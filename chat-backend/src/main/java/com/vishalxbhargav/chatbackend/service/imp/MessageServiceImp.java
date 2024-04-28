package com.vishalxbhargav.chatbackend.service.imp;

import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.exception.MessageException;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.Chat;
import com.vishalxbhargav.chatbackend.model.Message;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.repository.MessageRepository;
import com.vishalxbhargav.chatbackend.request.SendMessageRequest;
import com.vishalxbhargav.chatbackend.service.ChatService;
import com.vishalxbhargav.chatbackend.service.MessageService;
import com.vishalxbhargav.chatbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @Override
    public Message sendMessage(SendMessageRequest req) throws ChatException, UserException {
        User user=userService.findUserById(req.getUserId());
        Chat chat=chatService.findChatById(req.getChatId());
        Message message=new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getChatMessages(Integer chatId,User reqUser) throws ChatException, UserException {
        Chat chat=chatService.findChatById(chatId);
        if(!chat.getUsers().contains(reqUser)) throw new UserException("You are not related to this chat "+chatId);
        return messageRepository.findByChatId(chatId);
    }

    @Override
    public Message findMessageById(Integer messageId) throws MessageException {
        Optional<Message> optional=messageRepository.findById(messageId);
        if(optional.isPresent()) return optional.get();
        throw new MessageException("message not found with id "+messageId);
    }

    @Override
    public void deleteMessage(Integer messageId,User reqUser) throws MessageException, UserException {
        Message message=findMessageById(messageId);
        if(message.getUser().getId().equals(reqUser.getId()))
            messageRepository.deleteById(messageId);
        else throw new UserException("you can't delete another user's message "+message.getUser().getFull_name());
    }
}
