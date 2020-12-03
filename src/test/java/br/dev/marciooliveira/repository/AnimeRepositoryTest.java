package br.dev.marciooliveira.repository;

import br.dev.marciooliveira.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Test for anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save create anime when Successfull")
     void save_PersistAnime_WhenSuccessfull(){
        Anime anime = createAnime();
        Anime savedAnime = this.animeRepository.save(anime);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(savedAnime.getName());

    }
    private Anime createAnime(){
        return Anime.builder()
                .name("Anime test")
                .build();
    }


}