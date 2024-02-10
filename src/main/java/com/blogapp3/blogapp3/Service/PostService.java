package com.blogapp3.blogapp3.Service;

import com.blogapp3.blogapp3.payload.PostDto;
import com.blogapp3.blogapp3.payload.PostResponse;
import javafx.geometry.Pos;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortby,String sortDir);

    PostDto getById(long id);

    PostDto updatePost(long id, PostDto postDto);

    void deletebyid(long id);
}
