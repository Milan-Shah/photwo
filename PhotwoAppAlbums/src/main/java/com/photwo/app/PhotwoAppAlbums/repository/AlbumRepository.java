package com.photwo.app.PhotwoAppAlbums.repository;

import com.photwo.app.PhotwoAppAlbums.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    public Album findByUserId(String userId);
}
