package com.photwo.app.PhotwoAppUsers.service;

import com.photwo.app.PhotwoAppUsers.model.Album;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "albums-ws")
public interface AlbumServiceClient {

    @GetMapping(path = "/users/{id}/albums")
    public List<Album> getAlbums(@PathVariable String id);
}
