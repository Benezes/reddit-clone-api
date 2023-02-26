package br.com.mnz.reddit.dto.response;

import br.com.mnz.reddit.entity.PostEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PostResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID userId;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int votes;

    public PostResponse(PostEntity entity) {
        this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.username = entity.getUsername();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.votes = entity.getVotes();
    }
}
