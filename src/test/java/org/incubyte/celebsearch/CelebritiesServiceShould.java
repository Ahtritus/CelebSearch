package org.incubyte.celebsearch;

import io.micronaut.context.annotation.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CelebritiesServiceShould {

    @Mock
    CelebrityClient celebrityClient;

    @Value("micronaut.tmdb.api_key")
    private String API_KEY;

    @Test
    void invoke_search_by_name_in_client() {
        Celebrities dummyCelebrities = new Celebrities();

        when(celebrityClient.searchByName("Tom",API_KEY)).thenReturn(Optional.of(dummyCelebrities));

        CelebrityService celebrityService = new CelebrityService(celebrityClient);
        Optional<CelebrityResult[]> retrievedCelebrities = celebrityService.search("Tom");

        verify(celebrityClient).searchByName("Tom",API_KEY);

    }

    @Test
    void invoke_get_by_person_id_in_client() {
        CelebrityResult celebrityResult = new CelebrityResult();

        when(celebrityClient.getByPersonId(500L,API_KEY)).thenReturn(Optional.of(celebrityResult));

        CelebrityService celebrityService = new CelebrityService(celebrityClient);
        celebrityService.getPersonById(500L);

        verify(celebrityClient).getByPersonId(500L,API_KEY);

    }


}