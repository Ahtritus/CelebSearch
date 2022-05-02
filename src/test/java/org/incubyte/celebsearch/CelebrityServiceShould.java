package org.incubyte.celebsearch;

import io.micronaut.context.annotation.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CelebrityServiceShould {

    @Mock
    CelebrityClient celebrityClient;

    @Value("micronaut.tmdb.api_key")
    private String API_KEY;

    @Test
    public void invoke_search_by_name_in_client() {
        Celebrity dummyCelebrity = new Celebrity();

        when(celebrityClient.searchByName("Tom",API_KEY)).thenReturn(Optional.of(dummyCelebrity));

        CelebrityService celebrityService = new CelebrityService(celebrityClient);
        celebrityService.search("Tom");

        verify(celebrityClient).searchByName("Tom",API_KEY);

    }


}