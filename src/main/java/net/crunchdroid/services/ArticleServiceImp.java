package net.crunchdroid.services;

import net.crunchdroid.entities.Article;
import net.crunchdroid.exceptions.ArticleNotFoundException;
import net.crunchdroid.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CrunchDroid
 */
@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticle(Long id) throws ArticleNotFoundException {
        Article article = articleRepository.findOne(id);
        if (article == null)
            throw new ArticleNotFoundException(String.format("Article id %s is not found", id));
        return article;
    }

    @Override
    public Article getArticle(String slug) throws ArticleNotFoundException {
        Article article = articleRepository.findBySlug(slug);
        if (article == null)
            throw new ArticleNotFoundException(String.format("Article id %s is not found", slug));
        return article;
    }

    @Override
    public List<Article> search(String term) {
        return articleRepository.findByTitleOrContent("%" + term + "%");
    }

    @Override
    public void delete(Long id) {
        articleRepository.delete(id);
    }


}
