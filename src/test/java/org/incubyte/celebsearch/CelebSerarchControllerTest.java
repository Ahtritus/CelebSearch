package org.incubyte.celebsearch;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
public class CelebSerarchControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  public void search_for_a_celeb_name_should_return_list_of_celebs() {
    // Act
    List<Result> retrievedCelebrities =
        this.httpClient
            .toBlocking()
            .retrieve(HttpRequest.GET("/persons/" + "Tom%20Hanks"), Argument.listOf(Result.class));

    // Assert
    assertThat(retrievedCelebrities).isNotEmpty();
    assertThat(retrievedCelebrities.get(0).getName()).isEqualTo("Tom Hanks");
  }

  @Test
  public void search_for_a_celeb_name_should_return_404_list_of_celebs() {
    // Assert
    HttpClientResponseException httpClientResponseException =
        assertThrows(
            HttpClientResponseException.class,
            () -> {
              List<Result> retrievedCelebrities =
                  this.httpClient
                      .toBlocking()
                      .retrieve(
                          HttpRequest.GET("/persons/" + "abc%20xyz"),
                          Argument.listOf(Result.class));
            });

    assertThat(httpClientResponseException.getMessage()).isEqualTo("Not Found");
    assertThat(httpClientResponseException.getStatus().getCode()).isEqualTo(404);
  }
}
