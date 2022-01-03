package fact.it.albumservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.albumservice.model.Album;
import fact.it.albumservice.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AlbumControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumRepository albumRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenAlbum_whenGetAlbumByName_thenReturnJsonAlbum() throws Exception {
        Album album1 = new Album("cd76f76b-ff15-3784-a71d-4da3078a6851","Pablo Honey", "a74b1b7f-71a5-4011-9441-d0b5e4122711", "rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95");

        given(albumRepository.findAlbumByMAID("cd76f76b-ff15-3784-a71d-4da3078a6851")).willReturn(album1);

        mockMvc.perform(get("/albums/{maid}","cd76f76b-ff15-3784-a71d-4da3078a6851"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$.name", is("Pablo Honey")))
                .andExpect(jsonPath("$.artist", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$.genre", is("rock")))
                .andExpect(jsonPath("$.release", is("1993-02-22")))
                .andExpect(jsonPath("$.image", startsWith("https://i.scdn.co/image/")));
    }

    @Test
    public void whenGetAlbums_thenReturnJsonAlbums() throws Exception {
        Album album1 = new Album("cd76f76b-ff15-3784-a71d-4da3078a6851","Pablo Honey", "a74b1b7f-71a5-4011-9441-d0b5e4122711", "rock", "1993-02-22", "https://i.scdn.co/image/ab67616d00001e02df55e326ed144ab4f5cecf95");
        Album album2 = new Album("2b98e6d7-a521-332f-961e-d281ba33ba3d","Reggatta de Blanc", "9e0e2b01-41db-4008-bd8b-988977d6019a", "rock", "1979-10-02", "https://i.scdn.co/image/ab67616d00001e028ec81cc654b45ade8bdf1486");
        Album album3 = new Album("af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Black Holes and Revelations", "9c9f1380-2516-4fc9-a3e6-f9f61941d090", "rock", "2006-07-03", "https://i.scdn.co/image/ab67616d00001e0228933b808bfb4cbbd0385400");

        List<Album> albumList = new ArrayList<>();
        albumList.add(album1);
        albumList.add(album2);
        albumList.add(album3);

        given(albumRepository.findAll()).willReturn(albumList);

        mockMvc.perform(get("/albums"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maid", is("cd76f76b-ff15-3784-a71d-4da3078a6851")))
                .andExpect(jsonPath("$[0].name", is("Pablo Honey")))
                .andExpect(jsonPath("$[0].artist", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")))
                .andExpect(jsonPath("$[0].genre", is("rock")))
                .andExpect(jsonPath("$[0].release", is("1993-02-22")))
                .andExpect(jsonPath("$[0].image", startsWith("https://i.scdn.co/image/")))

                .andExpect(jsonPath("$[1].maid", is("2b98e6d7-a521-332f-961e-d281ba33ba3d")))
                .andExpect(jsonPath("$[1].name", is("Reggatta de Blanc")))
                .andExpect(jsonPath("$[1].artist", is("9e0e2b01-41db-4008-bd8b-988977d6019a")))
                .andExpect(jsonPath("$[1].genre", is("rock")))
                .andExpect(jsonPath("$[1].release", is("1979-10-02")))
                .andExpect(jsonPath("$[1].image", startsWith("https://i.scdn.co/image/")))

                .andExpect(jsonPath("$[2].maid", is("af2e8e23-e9c3-4e67-8ad8-66387c5898fd")))
                .andExpect(jsonPath("$[2].name", is("Black Holes and Revelations")))
                .andExpect(jsonPath("$[2].artist", is("9c9f1380-2516-4fc9-a3e6-f9f61941d090")))
                .andExpect(jsonPath("$[2].genre", is("rock")))
                .andExpect(jsonPath("$[2].release", is("2006-07-03")))
                .andExpect(jsonPath("$[2].image", startsWith("https://i.scdn.co/image/")));
    }
}
