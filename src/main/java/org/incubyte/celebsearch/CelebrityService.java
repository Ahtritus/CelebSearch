package org.incubyte.celebsearch;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
public class CelebrityService {

  private final CelebrityClient celebrityClient;

  @Value("${micronaut.tmdb.api_key}")
  private String API_KEY;

  public CelebrityService(CelebrityClient celebrityClient) {
    this.celebrityClient = celebrityClient;
  }

  public Optional<List<SearchResult>> search(String person) {
    Optional<Celebrities> retrievedCelebrities = celebrityClient.searchByName(person, API_KEY);
    Celebrities celebrities = retrievedCelebrities.get();
    List<SearchResult> celebrityResults = celebrities.getResults();
    if (celebrityResults.size() == 0 || celebrityResults.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(celebrityResults);
  }

  public Optional<CelebrityResult> getPersonById(long personId) {
    Optional<CelebrityResult> retrievedCelebrityDetails =
        this.celebrityClient.getByPersonId(personId, API_KEY);

    if (retrievedCelebrityDetails.isEmpty()) {
      return Optional.empty();
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    try {
      int year = 0;
      CelebrityResult celebrityResult = retrievedCelebrityDetails.get();
      if (celebrityResult.getBirthday() != null) {
        Date birthDate = format.parse(celebrityResult.getBirthday());
        year = birthDate.getYear();
      }
      long millsec = Clock.systemDefaultZone().millis();
      int thisYear = new Date(millsec).getYear();
      celebrityResult.setAge(thisYear - year);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return retrievedCelebrityDetails;
  }
}
