package org.incubyte.celebsearch;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Optional;

@Controller("/")
public class CelebrityController {
    private CelebrityService celebrityService;

    public CelebrityController(CelebrityService celebrityService) {
        this.celebrityService = celebrityService;
    }

    @Get("/persons/{person}")
    public Optional<Result[]> search(String person) {
        Optional<Result[]> results = celebrityService.search(person);
        return results;
    }
}
