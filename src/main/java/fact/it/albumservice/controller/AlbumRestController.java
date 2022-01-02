package fact.it.albumservice.controller;


import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class AlbumRestController {
    @Autowired
    private AlbumRepository albumRepository;

    @PostConstruct
    public void fillDB() {
        if(albumRepository.count()==0) {
            albumRepository.save(new Album("Typhoons", "Royal Blood", "rock", "2021-04-30", "https://i.scdn.co/image/ab67616d00001e02712b9c0f9a8d380e26a95c1c"));
            albumRepository.save(new Album("The Bends", "Radiohead", "rock", "1995-03-13", "https://i.scdn.co/image/ab67616d00001e029293c743fa542094336c5e12"));
            albumRepository.save(new Album("Pablo Honey", "Radiohead", "rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95"));
            albumRepository.save(new Album("Reggatta de Blanc", "The Police", "rock", "1979-10-02", "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486"));
            albumRepository.save(new Album("Black Holes and Revelations", "Muse", "rock", "2006-07-03", "https://i.scdn.co/image/ab67616d00001e0228933b808bfb4cbbd0385400"));
            albumRepository.save(new Album("The Resistance", "Muse", "rock", "2009-09-11", "https://i.scdn.co/image/ab67616d00001e02b6d4566db0d12894a1a3b7a2"));
        }
    }

    @GetMapping("/albums")
    public List<Album> getArtists() {
        return this.albumRepository.findAll();
    }

    @GetMapping("/albums/{name}")
    public Album getArtistByName(@PathVariable String name) {
        return this.albumRepository.findAlbumByName(name);
    }

}
