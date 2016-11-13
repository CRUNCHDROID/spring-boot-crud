package net.crunchdroid.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author CrunchDroid
 */
@Entity
@Table(name = "categories")
public class Category extends AbstractEntity {

    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Article> articles;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
