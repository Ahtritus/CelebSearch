package org.incubyte.celebsearch;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class CelebrityService {

    private final CelebrityClient celebrityClient;

    @Value("${micronaut.tmdb.api_key}")
    private String API_KEY;


    public CelebrityService(CelebrityClient celebrityClient) {
        this.celebrityClient = celebrityClient;
    }

    public Optional<CelebrityResult[]> search(String person) {
        Optional<Celebrities> retrievedCelebrities = celebrityClient.searchByName(person,API_KEY);
        if (retrievedCelebrities.get().getResults().length == 0) {
            return Optional.empty();
        }
        return Optional.of(retrievedCelebrities.get().getResults());
    }

    public Optional<CelebrityResult> getPersonById(long personId) {
        Optional<CelebrityResult> retrievedCelebrityDetails = celebrityClient.getByPersonId(personId,API_KEY);
        if (Optional.of(retrievedCelebrityDetails.get()).isEmpty()) {
            return Optional.empty();
        }


        return retrievedCelebrityDetails;
    }
}
