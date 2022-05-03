package org.incubyte.celebsearch;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;
import java.util.Optional;

@Controller("/")
public class CelebrityController {
    private final CelebrityService celebrityService;

    public CelebrityController(CelebrityService celebrityService) {
        this.celebrityService = celebrityService;
    }

    @Get("search/persons/{person}")
    public Optional<List<SearchResult>> search(String person) {
        return celebrityService.search(person);
    }


    public Optional<CelebrityResult> getPersonById(long personId) {
        return celebrityService.getPersonById(personId);
    }
}
