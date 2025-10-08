package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import com.openclassrooms.mddapi.model.Commentary;
import com.openclassrooms.mddapi.repository.CommentaryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    public Optional<Commentary> getMessage(Long id){ return commentaryRepository.findById(id); }

    public MessageResponseDTO deleteMessage( Long id ){
        MessageResponseDTO messageSuccess = new MessageResponseDTO();
        messageSuccess.setMessage("Message deleted with success!");
        commentaryRepository.deleteById(id);
        return messageSuccess;
    }

    public MessageResponseDTO saveMessage(Commentary messageArticle){
        MessageResponseDTO messageSuccess = new MessageResponseDTO();
        messageSuccess.setMessage("Message send with success!");
        commentaryRepository.save(messageArticle);
        return messageSuccess;
    }
}
