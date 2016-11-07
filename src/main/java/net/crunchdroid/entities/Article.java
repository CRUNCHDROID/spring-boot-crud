package net.crunchdroid.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author CrunchDroid
 */
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String slug;
    private String content;
    private boolean activated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;


    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Pattern NON_WORD = Pattern.compile("[^\\w-]");
        String noWithSpace = WITH_SPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(noWithSpace, Normalizer.Form.NFD);
        this.slug = NON_WORD.matcher(normalized).replaceAll("").toLowerCase(Locale.ENGLISH);
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @PrePersist
    private void onPrePersist() {
        setCreated(new Date());
        setSlug(getTitle());
    }

    @PreUpdate
    private void onPreUpdate() {
        setUpdated(new Date());
        setSlug(getTitle());
    }

}
