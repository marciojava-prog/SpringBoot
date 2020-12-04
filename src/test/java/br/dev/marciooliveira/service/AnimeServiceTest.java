package br.dev.marciooliveira.service;

import br.dev.marciooliveira.domain.Anime;
import br.dev.marciooliveira.exception.BadRequestException;
import br.dev.marciooliveira.repository.AnimeRepository;
import br.dev.marciooliveira.util.AnimeCreator;
import br.dev.marciooliveira.util.AnimePostRequestBodyCreator;
import br.dev.marciooliveira.util.AnimePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;
    @Mock
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Anime> animePage =  new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepositoryMock.findAll())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createValidAnime());



        BDDMockito.doNothing().when(animeRepositoryMock).delete(ArgumentMatchers.any(Anime.class));


    }


    @Test
    @DisplayName("List returns list of aniome inside page when sucessful")
    void listAll_ReturnsListOfAnimesInsidePageObject_WhenSuccessful(){

        String expectedName = AnimeCreator.createValidAnime().getName();
        Page<Anime> animePage = animeService.listAll(PageRequest.of(0,5));

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList()).isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("List returns list of aniome inside page when sucessful")
    void listAllNonPageable_ReturnsListOfAnimes_WhenSuccessful(){

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeService.listAllNonPageable();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }
    @Test
    @DisplayName("findById returns anime for id")
    void findByIdOrThrowBadRequestException_ReturnsAnimes_WhenSuccessful(){

        Long expectedId = AnimeCreator.createValidAnime().getId();
        Anime anime = animeService.findByIdOrThrowBadRequestException(1);

        Assertions.assertThat(anime).isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(expectedId);
    }
    @Test
    @DisplayName("findById returns anime for id")
    void findByIdOrThrowBadRequestException_WhenAnimeIsNotFound(){

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> animeService.findByIdOrThrowBadRequestException(1));

    }

    @Test
    @DisplayName("findByName returns anime for name")
    void findByName_ReturnsAnimes_WhenSuccessful(){

        String expectedName = AnimeCreator.createValidAnime().getName();
        List<Anime> animes = animeService.findByName("Anime Test");

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName an empty list returns anime anime is not found")
    void findByName_ReturnsAnimesEmpty_WhenSuccessful(){

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> animes = animeService.findByName("Anime Test");

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();

    }


    @Test
    @DisplayName("save returns anime for id")
    void save_ReturnsAnimes_WhenSuccessful(){

        Anime anime = animeService.save(AnimePostRequestBodyCreator.createAnimePostRequestBody());

        Assertions.assertThat(anime).isNotNull().isEqualTo(AnimeCreator.createValidAnime());

    }

    @Test
    @DisplayName("Replace update returns anime for id")
    void replace_ReturnsAnimes_WhenSuccessful(){

        Assertions.assertThatCode(() -> animeService.replace(AnimePutRequestBodyCreator
                .createAnimePutRequestBody()));

    }

    @Test
    @DisplayName("delete  returns anime for id")
    void delete_RemoveAnimes_WhenSuccessful(){

        Assertions.assertThatCode(() -> animeService.delete(1)).doesNotThrowAnyException();



    }



}