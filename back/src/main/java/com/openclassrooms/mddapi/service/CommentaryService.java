package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.DTO.commentary.CommentaryRequest;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Commentary;
import com.openclassrooms.mddapi.model.User;
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

    public Optional<Commentary> getMessage(Long id){
        return commentaryRepository.findById(id);
    }

    public void deleteMessage( Long id ){
        commentaryRepository.deleteById(id);
    }

    public Commentary saveMessage(CommentaryRequest commentaryRequest, User author, Article article){

        Commentary commentary = new Commentary();
        commentary.setMessage(commentaryRequest.getMessage());
        commentary.setArticle(article);
        commentary.setAuthor(author);
        return commentaryRepository.save(commentary);
    }
}
