package fact.it.albumservice.repository;

import fact.it.albumservice.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
    Album findAlbumByName(String name);
}
