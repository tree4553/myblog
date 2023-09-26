package me.jeongcheol.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.jeongcheol.springbootdeveloper.domain.Article;
import me.jeongcheol.springbootdeveloper.dto.AddArticleRequest;
import me.jeongcheol.springbootdeveloper.dto.UpdateArticleRequest;
import me.jeongcheol.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor    // final 키워드나 @NotNull이 붙은 필드로 생성자를 만들어준다.
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional  // 메서드를 하나의 트랜잭션으로 묶는다.
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
