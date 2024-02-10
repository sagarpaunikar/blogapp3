package com.blogapp3.blogapp3.Service.impl;

import com.blogapp3.blogapp3.Repository.CommentRepository;
import com.blogapp3.blogapp3.Repository.PostRepository;
import com.blogapp3.blogapp3.Service.CommentService;
import com.blogapp3.blogapp3.entities.Comment;
import com.blogapp3.blogapp3.entities.Post;
import com.blogapp3.blogapp3.exception.ResourceNotFoundException;
import com.blogapp3.blogapp3.payload.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentrepo;
    private PostRepository postrepo;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentrepo, PostRepository postrepo,ModelMapper mapper) {
        this.commentrepo = commentrepo;
        this.postrepo = postrepo;
        this.mapper=mapper;
    }

    @Override
    public CommentDto createComment(long postId,CommentDto commentDto) {
        Post post = postrepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = mapToComment(commentDto);
        comment.setPost(post);
        Comment comment1 = commentrepo.save(comment);
        CommentDto dto = mapToCommentDto(comment1);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> comment = commentrepo.findByPostId(postId);
        return comment.stream().map(comment1->mapToCommentDto(comment1)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
        Post post = postrepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentrepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id)
        );
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setName(commentDto.getName());
        Comment save = commentrepo.save(comment);
        CommentDto dto = mapToCommentDto(save);
        return dto;
    }

    @Override
    public void deletecomment(long postId, long id) {
        Post post = postrepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentrepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id)
        );
        commentrepo.deleteById(id);
    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto dto = mapper.map(comment, CommentDto.class);
//        CommentDto dto=new CommentDto();
//        dto.setId(comment.getId());
//        dto.setBody(comment.getBody());
//        dto.setName(comment.getName());
//        dto.setEmail(comment.getEmail());
        return dto;
    }

    private Comment mapToComment(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment=new Comment();
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
