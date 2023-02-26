package br.com.mnz.reddit.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PostRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String username;
}
