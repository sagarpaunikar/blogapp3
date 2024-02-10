package com.blogapp3.blogapp3.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotNull
    @Size(min = 2,message = "Post title should be min 2 charachter")
    private String title;
    @NotNull
    @Size(min = 10,message = "Post description should have min 2 charachter")
    private String description;
    @NotNull
    @NotEmpty
    private String content;

}
