package com.blogapp3.blogapp3.Controller;

import com.blogapp3.blogapp3.Service.PostService;
import com.blogapp3.blogapp3.payload.PostDto;
import com.blogapp3.blogapp3.payload.PostResponse;
import com.blogapp3.blogapp3.utils.AppContants;
import javafx.geometry.Pos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllPost(
            @RequestParam(name = "pageNo",defaultValue = AppContants.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = AppContants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(name = "sortby",defaultValue = AppContants.DEFAULT_SORT_BY,required = false) String sortby,
            @RequestParam(name = "sortDir",defaultValue = AppContants.DEFAULT_SORT_DIR,required = false)String  sortDir
    ){
        PostResponse post = postService.getAllPost(pageNo, pageSize, sortby,sortDir);
        return post;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") long id){
        return ResponseEntity.ok(postService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatebyId(@PathVariable("id")long id,@RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletebyId(@PathVariable("id") long id){
        postService.deletebyid(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }

}
