package br.dev.marciooliveira.service;

import br.dev.marciooliveira.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    // private final AnimeRepository animeRepository;

    public List<Anime> listAll(){


        return List.of(new Anime(1L,"Boku no Hero"), new Anime(2L,"Yu Yu Hakusho"));
    }
}
