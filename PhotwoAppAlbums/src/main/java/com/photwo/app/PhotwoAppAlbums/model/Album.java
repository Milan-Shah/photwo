package com.photwo.app.PhotwoAppAlbums.model;

import javax.persistence.*;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue
    public Long id;
    @Column
    public String albumId;
    @Column(nullable = false)
    public String userId;
    @Column(nullable = false)
    public String name;
    @Column
    public String description;

    public Album(String albumId, String userId, String name, String description) {
        this.albumId = albumId;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    protected Album() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
