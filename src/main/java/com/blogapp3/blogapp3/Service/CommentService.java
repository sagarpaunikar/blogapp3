package com.blogapp3.blogapp3.Service;

import com.blogapp3.blogapp3.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deletecomment(long postId, long id);
}
