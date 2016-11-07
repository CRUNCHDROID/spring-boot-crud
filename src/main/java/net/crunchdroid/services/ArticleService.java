package net.crunchdroid.services;

import net.crunchdroid.entities.Article;
import net.crunchdroid.exceptions.ArticleNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author CrunchDroid
 */
public interface ArticleService {

    List<Article> getArticles();

    Article save(Article article);

    Article getArticle(Long id) throws ArticleNotFoundException;

    Article getArticle(String slug) throws ArticleNotFoundException;

    List<Article> search(String term);

    void delete(Long id);
}
