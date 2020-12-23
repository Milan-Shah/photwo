package com.photwo.app.PhotwoAppAlbums.service;

import com.photwo.app.PhotwoAppAlbums.model.Album;
import com.photwo.app.PhotwoAppAlbums.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumService {

    AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAll(String userId) {

        Album one = new Album("albumId1", userId, "Traveling", "Going around the world");
        Album two = new Album("albumId2", userId,"Chilling", "Chilling in NYC");

        List<Album> userAlbums = new ArrayList<>();

        albumRepository.findAll().forEach(album -> {
            if (album.getUserId().equalsIgnoreCase(userId)) {
                userAlbums.add(album);
            }
        });

        userAlbums.add(one);
        userAlbums.add(two);
        return userAlbums;
    }

    public Album createAlbum(Album album) {
        album.setAlbumId(UUID.randomUUID().toString());
        albumRepository.save(album);
        return album;
    }
}
