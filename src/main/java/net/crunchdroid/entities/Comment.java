package net.crunchdroid.entities;

import javax.persistence.*;

/**
 * @author CrunchDroid
 */
@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

    private String author;
    private String email;
    private String content;
    private String ip;
    private boolean approved;
    private String userAgent;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Comment() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
