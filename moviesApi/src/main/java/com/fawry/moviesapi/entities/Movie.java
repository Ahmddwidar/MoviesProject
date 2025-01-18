package com.fawry.moviesapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "imdbID")
    private String imdbID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Year")
    private int year;

    @Column(name = "Type")
    private String type;

    @Column(name = "Poster")
    private String poster;

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private Set<User> users;

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}