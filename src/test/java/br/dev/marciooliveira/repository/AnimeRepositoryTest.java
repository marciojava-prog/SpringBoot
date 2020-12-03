package br.dev.marciooliveira.repository;

import br.dev.marciooliveira.domain.Anime;
import br.dev.marciooliveira.util.AnimeCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Test for anime repository")
@Log4j2
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persist anime when Successfull")
     void save_PersistAnime_WhenSuccessfull(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(anime);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(savedAnime.getName());

    }

    @Test
    @DisplayName("Save updates anime when Successfull")
    void update_UpdateAnime_WhenSuccessfull(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);
        animeSaved.setName("Overlord");

        Anime animeUpdated = this.animeRepository.save(animeSaved);
        log.info(animeUpdated.getName());
        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());

    }
    @Test
    @DisplayName("Delete remove anime when Successfull")
    void delete_RemovesAnime_WhenSuccessfull(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);
        this.animeRepository.delete(animeSaved);
        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();

    }
    @Test
    @DisplayName("Find by name anime when Successfull")
    void findByName_ReturnsAnime_WhenSuccessfull(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);
        String name = animeSaved.getName();
        List<Anime> animes = this.animeRepository.findByName(name);
        Assertions.assertThat(animes).isNotEmpty()
                                     .contains(animeSaved);

    }

    @Test
    @DisplayName("Find by name anime when failure")
    void findByName_ReturnsEmptyOfAnime_WhenFailure(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(anime);
        String name = animeSaved.getName();
        List<Anime> animes = this.animeRepository.findByName("Anima not exist");
        Assertions.assertThat(animes).isEmpty();

    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty(){
        Anime anime = new Anime();

//        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
//                .isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The Anime name cannot be empty");



        Anime savedAnime = this.animeRepository.save(anime);



    }



}