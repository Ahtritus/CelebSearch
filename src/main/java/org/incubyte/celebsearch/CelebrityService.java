package org.incubyte.celebsearch;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

import javax.swing.text.html.Option;
import java.util.Optional;

@Singleton
public class CelebrityService {

    private final CelebrityClient celebrityClient;

    @Value("${micronaut.tmdb.api_key}")
    private String API_KEY;


    public CelebrityService(CelebrityClient celebrityClient) {
        this.celebrityClient = celebrityClient;
    }

    public Optional<Result[]> search(String person) {
        Optional<Celebrity> retrievedCelebrities = celebrityClient.searchByName(person,API_KEY);
        if (retrievedCelebrities.get() == null || retrievedCelebrities.get().getResults().length == 0) {
            return Optional.empty();
        }
        return Optional.of(retrievedCelebrities.get().getResults());
    }
}
