package com.blogapp3.blogapp3.Controller;

import com.blogapp3.blogapp3.Repository.CommentRepository;
import com.blogapp3.blogapp3.Service.CommentService;
import com.blogapp3.blogapp3.payload.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getAllCommentByPostId(@PathVariable("postId") long postId){
        List<CommentDto> dto = commentService.getCommentByPostId(postId);
        return dto;
    }

    @PutMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable("postId") long postId,
            @PathVariable("id") long id,
            @RequestBody CommentDto commentDto
    ){
        CommentDto dto = commentService.updateComment(postId, id, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<String> deletecomment(
            @PathVariable("postId") long postId,
            @PathVariable("id") long id
    ){
        commentService.deletecomment(postId,id);
        return new ResponseEntity<>("comment is deleted",HttpStatus.OK);
    }
}
