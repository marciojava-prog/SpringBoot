package br.dev.marciooliveira.mapper;

import br.dev.marciooliveira.domain.Anime;
import br.dev.marciooliveira.requests.AnimePostRequestBody;
import br.dev.marciooliveira.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);

}
