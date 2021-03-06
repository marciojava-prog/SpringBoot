package br.dev.marciooliveira.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AnimePostRequestBody {

    @NotEmpty(message = "The Anime name cannot be empty")
    private String name;

}
