package br.com.mnz.reddit.service;

import br.com.mnz.reddit.dto.request.PostRequest;
import br.com.mnz.reddit.dto.response.PostResponse;
import br.com.mnz.reddit.entity.PostEntity;
import br.com.mnz.reddit.repository.PostRepository;
import br.com.mnz.reddit.service.exception.PostNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostResponse> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostResponse::new);
    }

    @Transactional
    public PostResponse createPost(PostRequest postRequest) {
        PostEntity postEntity = PostEntity.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .username(postRequest.getUsername())
                .createdAt(LocalDateTime.now())
                .votes(1)
                .build();

        return new PostResponse(postRepository.save(postEntity));
    }

    @Transactional
    public PostResponse updatePost(UUID id, PostRequest postRequest) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Posted message not found"));
        BeanUtils.copyProperties(postRequest, postEntity, "id");
        postEntity.setUpdatedAt(LocalDateTime.now());
        return new PostResponse(postRepository.save(postEntity));
    }

    @Transactional
    public PostResponse upVote(UUID id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Posted message not found"));
        postEntity.setVotes(postEntity.getVotes() + 1);
        postEntity.setUpdatedAt(LocalDateTime.now());
        return new PostResponse(postRepository.save(postEntity));
    }

    @Transactional
    public PostResponse downVote(UUID id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Posted message not found"));
        postEntity.setVotes(postEntity.getVotes() - 1);
        postEntity.setUpdatedAt(LocalDateTime.now());
        return new PostResponse(postRepository.save(postEntity));
    }

    @Transactional
    public void deletePost(UUID id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Posted message not found"));
        postRepository.delete(postEntity);
    }
}
