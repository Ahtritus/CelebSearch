package org.incubyte.celebsearch;

import io.micronaut.context.annotation.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CelebritiesServiceShould {

    @Mock
    CelebrityClient celebrityClient;
    CelebrityResult celebrityResult;
    Celebrities dummyCeleb1;
    Celebrities dummyCeleb2;
    private final Clock clock = Clock.fixed(ZonedDateTime.parse("2021-10-25T00:00:00.000+09:00[Asia/Seoul]").toInstant(), ZoneId.of("Asia/Seoul"));

    @Value("micronaut.tmdb.api_key")
    private String API_KEY;

    @BeforeEach
    public void init()
    {
        dummyCeleb1 = new Celebrities();
        SearchResult searchResult1 = new SearchResult();
        searchResult1.setName("Tom Cruise");
        searchResult1.setProfilePath("/ABc");

        SearchResult searchResult2 = new SearchResult();
        searchResult2.setName("James Bond 007");
        searchResult2.setProfilePath("/here");

        List<SearchResult> allResults = new ArrayList<SearchResult>();
        allResults.add(searchResult1);
        allResults.add(searchResult2);
        dummyCeleb1.setResults(allResults);

        dummyCeleb2 = new Celebrities();
        dummyCeleb2.setResults(new ArrayList<>());

        celebrityResult = new CelebrityResult();
        celebrityResult.setBirthday("2022-01-01");
    }

    @Test
    void invoke_search_by_name_in_client() {
        when(celebrityClient.searchByName("Tom",API_KEY)).thenReturn(Optional.of(dummyCeleb1));

        CelebrityService celebrityService = new CelebrityService(celebrityClient);
        Optional<List<SearchResult>> tom = celebrityService.search("Tom");

        verify(celebrityClient).searchByName("Tom",API_KEY);

    }

    @Test
    void invoke_get_by_person_id_in_client() {
        when(celebrityClient.getByPersonId(500L,API_KEY)).thenReturn(Optional.of(celebrityResult));
        var mockStatic = mockStatic(Clock.class);
        when(clock.systemDefaultZone()).thenReturn(clock);
        CelebrityService celebrityService = new CelebrityService(celebrityClient);
        celebrityService.getPersonById(500L);
        verify(celebrityClient).getByPersonId(500L,API_KEY);

    }

}