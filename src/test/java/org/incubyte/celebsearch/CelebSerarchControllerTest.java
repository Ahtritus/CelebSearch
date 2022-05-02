package org.incubyte.celebsearch;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class CelebSerarchControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  public void search_for_a_celeb_name_should_return_list_of_celebs() {
    // Act
    Celebrity retrieveCelebrities;

    retrieveCelebrities =
        this.httpClient
            .toBlocking()
            .retrieve(
                HttpRequest.GET(
                    "https://api.themoviedb.org/3/search/person?api_key=3074c42af940fd86278523b116782eef&query="
                        + "Tom%20Hanks"),
                Argument.of(Celebrity.class));

    //Assert
    assertThat(retrieveCelebrities.getResults()).isNotEmpty();
  }

  @Test
  public void search_for_a_celeb_name_should_return_null_if_name_not_found() {
    // Act
    Celebrity retrieveCelebrities;

    retrieveCelebrities =
            this.httpClient
                    .toBlocking()
                    .retrieve(
                            HttpRequest.GET(
                                    "https://api.themoviedb.org/3/search/person?api_key=3074c42af940fd86278523b116782eef&query="
                                            + "AbcXyz"),
                            Argument.of(Celebrity.class));

    //Assert
    assertThat(retrieveCelebrities.getResults()).isEmpty();
  }
}
