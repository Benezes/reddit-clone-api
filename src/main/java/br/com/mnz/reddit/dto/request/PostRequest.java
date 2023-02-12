package br.com.mnz.reddit.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String username;
}
