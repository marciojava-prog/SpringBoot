package br.dev.marciooliveira.service;

import br.dev.marciooliveira.domain.Anime;
import br.dev.marciooliveira.exception.BadRequestException;
import br.dev.marciooliveira.mapper.AnimeMapper;
import br.dev.marciooliveira.repository.AnimeRepository;
import br.dev.marciooliveira.requests.AnimePostRequestBody;
import br.dev.marciooliveira.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AnimeService {


    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable){return animeRepository.findAll(pageable); }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name){return animeRepository.findByName(name); }


    public Anime findByIdOrThrowBadRequestException(long id){

        return animeRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Anime not found"));

    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {

        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {

        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);

    }


}
