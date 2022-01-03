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
            albumRepository.save(new Album("dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Typhoons", "aa62b28e-b6d4-4086-91d4-e5fac1ed56f3", "rock", "2021-04-30", "https://i.scdn.co/image/ab67616d00001e02712b9c0f9a8d380e26a95c1c"));

            albumRepository.save(new Album("b8048f24-c026-3398-b23a-b5e50716cbc7","The Bends", "a74b1b7f-71a5-4011-9441-d0b5e4122711", "rock", "1995-03-13", "https://i.scdn.co/image/ab67616d00001e029293c743fa542094336c5e12"));
            albumRepository.save(new Album("cd76f76b-ff15-3784-a71d-4da3078a6851","Pablo Honey", "a74b1b7f-71a5-4011-9441-d0b5e4122711", "rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95"));

            albumRepository.save(new Album("2b98e6d7-a521-332f-961e-d281ba33ba3d","Reggatta de Blanc", "9e0e2b01-41db-4008-bd8b-988977d6019a", "rock", "1979-10-02", "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486"));

            albumRepository.save(new Album("af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Black Holes and Revelations", "9c9f1380-2516-4fc9-a3e6-f9f61941d090", "rock", "2006-07-03", "https://i.scdn.co/image/ab67616d00001e0228933b808bfb4cbbd0385400"));
            albumRepository.save(new Album("8411a4db-e104-4e45-995f-24b2f1849437","The Resistance", "9c9f1380-2516-4fc9-a3e6-f9f61941d090", "rock", "2009-09-11", "https://i.scdn.co/image/ab67616d00001e02b6d4566db0d12894a1a3b7a2"));
        }
    }

    @GetMapping("/albums")
    public List<Album> getAlbums() {
        return this.albumRepository.findAll();
    }

    @GetMapping("/albums/{MAID}")
    public Album getAlbumByMAID(@PathVariable String MAID) {
        return this.albumRepository.findAlbumByMAID(MAID);
    }

}
