package hellojpa.domain;

import jakarta.persistence.Entity;

@Entity
public class Book extends Item{

    private String author;
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
