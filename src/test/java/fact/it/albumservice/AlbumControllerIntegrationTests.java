package fact.it.albumservice;

import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AlbumControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlbumRepository albumRepository;

    private Album album1 = new Album("Pablo Honey", "Radiohead", "rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95");
    private Album album2 = new Album("Reggatta de Blanc", "The Police", "rock", "1979-10-02", "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486");
    private Album album3 = new Album("Black Holes and Revelations", "Muse", "rock", "2006-07-03", "https://i.scdn.co/image/ab67616d00001e0228933b808bfb4cbbd0385400");

    @BeforeEach
    public void beforeAllTests() {
        albumRepository.deleteAll();
        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);
    }

    @AfterEach
    public void afterAllTests() {
        albumRepository.deleteAll();
    }

    @Test
    public void givenAlbum_whenGetAlbumByAlbumName_thenReturnJsonAlbum() throws Exception {
        mockMvc.perform(get("/albums/{name}","Pablo Honey"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Pablo Honey")))
                .andExpect(jsonPath("$.artist", is("Radiohead")))
                .andExpect(jsonPath("$.genre", is("rock")))
                .andExpect(jsonPath("$.release", is("1993-02-22")))
                .andExpect(jsonPath("$.image", startsWith("https://i.scdn.co/image/")));
    }

    @Test
    public void givenAlbum_whenGetAlbums_thenReturnJsonAlbums() throws Exception {
        List<Album> albumList = new ArrayList<>();
        albumList.add(album1);
        albumList.add(album2);
        albumList.add(album3);

        mockMvc.perform(get("/albums"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Pablo Honey")))
                .andExpect(jsonPath("$[0].artist", is("Radiohead")))
                .andExpect(jsonPath("$[0].genre", is("rock")))
                .andExpect(jsonPath("$[0].release", is("1993-02-22")))
                .andExpect(jsonPath("$[0].image", startsWith("https://i.scdn.co/image/")))

                .andExpect(jsonPath("$[1].name", is("Reggatta de Blanc")))
                .andExpect(jsonPath("$[1].artist", is("The Police")))
                .andExpect(jsonPath("$[1].genre", is("rock")))
                .andExpect(jsonPath("$[1].release", is("1979-10-02")))
                .andExpect(jsonPath("$[1].image", startsWith("https://i.scdn.co/image/")))

                .andExpect(jsonPath("$[2].name", is("Black Holes and Revelations")))
                .andExpect(jsonPath("$[2].artist", is("Muse")))
                .andExpect(jsonPath("$[2].genre", is("rock")))
                .andExpect(jsonPath("$[2].release", is("2006-07-03")))
                .andExpect(jsonPath("$[2].image", startsWith("https://i.scdn.co/image/")));
    }
}
