package br.dev.marciooliveira.controller;

import br.dev.marciooliveira.domain.Anime;
import br.dev.marciooliveira.service.AnimeService;
import br.dev.marciooliveira.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

   /* @Autowired
    private DateUtil dateUtil;

     // Não recomendado*/


    @GetMapping
    public ResponseEntity<List<Anime>> list(){

        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
    }


}
