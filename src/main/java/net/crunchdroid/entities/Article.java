package net.crunchdroid.entities;

import javax.persistence.*;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author CrunchDroid
 */
@Entity
@Table(name = "articles")
public class Article extends AbstractEntity {

    private String title;
    private String slug;
    private String content;
    private boolean activated;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories;

    @OneToMany
    private List<Comment> comments;

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String text) {
        Pattern WITH_SPACE = Pattern.compile("\\s");
        Pattern EXCEPT_WORD_HYPHEN = Pattern.compile("[^\\w-]");
        Pattern NON_MULTI_HYPHEN = Pattern.compile("[\\-]+");

        String noWithSpace = WITH_SPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(noWithSpace, Normalizer.Form.NFD);
        String wordHyphen = EXCEPT_WORD_HYPHEN.matcher(normalized).replaceAll("").toLowerCase(Locale.ENGLISH);
        this.slug = NON_MULTI_HYPHEN.matcher(wordHyphen).replaceAll("-");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @PrePersist
    private void onPrePersist() {
        setSlug(getTitle());
    }

    @PreUpdate
    private void onPreUpdate() {
        setSlug(getTitle());
    }

}
