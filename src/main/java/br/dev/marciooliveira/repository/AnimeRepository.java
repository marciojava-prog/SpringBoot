package br.dev.marciooliveira.repository;

import br.dev.marciooliveira.domain.Anime;

import java.util.List;

public interface AnimeRepository {

    List<Anime> listAll();
}
