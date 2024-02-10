package com.blogapp3.blogapp3.Service.impl;

import com.blogapp3.blogapp3.Repository.PostRepository;
import com.blogapp3.blogapp3.Service.PostService;
import com.blogapp3.blogapp3.entities.Post;
import com.blogapp3.blogapp3.exception.ResourceNotFoundException;
import com.blogapp3.blogapp3.payload.PostDto;
import com.blogapp3.blogapp3.payload.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postrepo;
    private ModelMapper mapper;
    public PostServiceImpl(PostRepository postrepo,ModelMapper mapper) {
        this.mapper=mapper;
        this.postrepo = postrepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post postEntity = postrepo.save(post);
        PostDto dto = mapToDto(postEntity);

        return dto;
    }

    @Override
    public PostResponse getAllPost(int pageNo,int pageSize,String sortby,String  sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();
        Pageable page= PageRequest.of(pageNo,pageSize, sort);
        Page<Post> post = postrepo.findAll(page);
        List<PostDto> collect = post.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        PostResponse p=new PostResponse();
        p.setContent(collect);
        p.setPageNo(post.getNumber());
        p.setPageSize(post.getSize());
        p.setTotalPages(post.getTotalPages());
        p.setTotalElement(post.getTotalElements());
        p.setIslast(post.isLast());
        return p;
    }

    @Override
    public PostDto getById(long id) {
        Post post = postrepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post post1 = postrepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        post1.setTitle(postDto.getTitle());
        post1.setDescription(postDto.getDescription());
        post1.setContent(postDto.getContent());
        Post save = postrepo.save(post1);
        PostDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public void deletebyid(long id) {
        Post post = postrepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        postrepo.deleteById(id);
    }

    private PostDto mapToDto(Post postEntity) {
        PostDto dto = mapper.map(postEntity, PostDto.class);
//        PostDto dto=new PostDto();
//        dto.setId(postEntity.getId());
//        dto.setTitle(postEntity.getTitle());
//        dto.setDescription(postEntity.getDescription());
//        dto.setContent(postEntity.getContent());
        return dto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post p = mapper.map(postDto, Post.class);
//        Post p=new Post();
//        p.setTitle(postDto.getTitle());
//        p.setDescription(postDto.getDescription());
//        p.setContent(postDto.getContent());

        return p;
    }
}
