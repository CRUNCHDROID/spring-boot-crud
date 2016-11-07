package net.crunchdroid.repositories;

import net.crunchdroid.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author CrunchDroid
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("from Article a where a.slug = :slug")
    Article findArticle(@Param("slug") String slug);

    Article findBySlug(String slug);

    @Query("from Article a where a.title like :term or a.content like :term")
    List<Article> findByTitleOrContent(@Param("term") String term);

}
