package fast.bootboard.service;

import fast.bootboard.domain.Article;
import fast.bootboard.domain.type.SearchType;
import fast.bootboard.dto.ArticleDto;
import fast.bootboard.dto.ArticleWithCommentsDto;
import fast.bootboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
       if (searchKeyword== null || searchKeyword.isBlank()){
           return articleRepository.findAll(pageable).map(ArticleDto::from);
       }

       return switch (searchType) {
           case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
           case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
           case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
           case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
           case HASHTAG -> articleRepository.findByHashTag("#"+searchKeyword);
       };

    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto searchArticle(long articleId) {
        return articleRepository.findById(articleId).map(ArticleWithCommentsDto::from)
                .orElseThrow(()->new EntityNotFoundException("게시글이 없습니다 - articleId:"+ articleId));
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(long articleId, ArticleDto dto) {
        Article article = articleRepository.getReferenceById(dto.id());
        if (dto.title() != null){ article.setTitle(dto.title());}
        if (dto.content() != null){ article.setContent(dto.content());}
        article.setHashtag(dto.hashtag());
        articleRepository.save(article);        // TODO : update에서 save 방식 ?

    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }
}
