package br.com.mnz.reddit.controller;

import br.com.mnz.reddit.dto.request.PostRequest;
import br.com.mnz.reddit.dto.response.PostResponse;
import br.com.mnz.reddit.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPostsPaged(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(postService.findAllPosts(pageable));
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postRequest));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable UUID id, @RequestBody @Valid PostRequest postRequest) {
        return ResponseEntity.ok(postService.updatePost(id, postRequest));
    }

    @PatchMapping(value = "/{id}/upvote")
    public ResponseEntity<PostResponse> upvotePost(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.upVote(id));
    }

    @PatchMapping(value = "/{id}/downvote")
    public ResponseEntity<PostResponse> downvotePost(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.downVote(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
