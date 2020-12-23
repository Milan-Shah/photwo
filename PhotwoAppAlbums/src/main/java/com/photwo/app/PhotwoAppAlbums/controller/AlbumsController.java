package com.photwo.app.PhotwoAppAlbums.controller;

import com.photwo.app.PhotwoAppAlbums.model.Album;
import com.photwo.app.PhotwoAppAlbums.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/albums")
public class AlbumsController {

    @Autowired
    Environment env;

    @Autowired
    AlbumService albumService;

    @GetMapping(path = "/status")
    public ResponseEntity<String> status() {
        String response = "Working on port: " + env.getProperty("local.server.port");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping()
    public List<Album> getAllAlbums(@PathVariable String userId) {
        return albumService.findAll(userId);
    }

    @PostMapping()
    public ResponseEntity<Album> createAlbums(@Valid @RequestBody Album album) {
        Album createdAlbum = albumService.createAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlbum);
    }

}
