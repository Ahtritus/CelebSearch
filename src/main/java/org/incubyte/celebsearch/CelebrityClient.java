package org.incubyte.celebsearch;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;

import java.util.Optional;

@Client(value = "https://api.themoviedb.org/3/")
public interface CelebrityClient {

  @Get("search/person{?query}{&api_key}")
  Optional<Celebrity> searchByName(@QueryValue String query, @QueryValue String api_key);
}
