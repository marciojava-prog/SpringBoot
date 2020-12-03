package br.dev.marciooliveira.util;

import br.dev.marciooliveira.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Anime test")
                .build();
    }
    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Anime test")
                .id(100L)
                .build();
    }
    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Anime test 2")
                .id(100L)
                .build();
    }
}
