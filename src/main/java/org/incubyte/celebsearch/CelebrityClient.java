package org.incubyte.celebsearch;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import java.util.Optional;

@Client(value = "https://api.themoviedb.org/3/")
public interface CelebrityClient {

  @Get("search/person{?query}{&api_key}")
  Optional<Celebrities> searchByName(@QueryValue String query, @QueryValue String api_key);

  @Get("person/{personId}{?api_key}")
  Optional<CelebrityResult> getByPersonId(long personId, String api_key);
}
