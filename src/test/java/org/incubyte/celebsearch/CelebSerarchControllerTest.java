package org.incubyte.celebsearch;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
public class CelebSerarchControllerTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  void search_for_a_celeb_name_should_return_list_of_celebs_with_their_details() {
    // Act
    List<CelebrityResult> retrievedCelebrities =
        this.httpClient
            .toBlocking()
            .retrieve(HttpRequest.GET("search/persons/" + "Tom%20Cruise"), Argument.listOf(
                CelebrityResult.class));

    // Assert
    assertThat(retrievedCelebrities).isNotEmpty();
    assertThat(retrievedCelebrities.get(0).getName()).isEqualTo("Tom Cruise");
    assertThat(retrievedCelebrities.get(0).getGender()).isEqualTo(2);
    assertThat(retrievedCelebrities.get(0).getId()).isEqualTo(500L);
    assertThat(retrievedCelebrities.get(0).getKnownForDepartment()).isEqualTo("Acting");
    assertThat(retrievedCelebrities.get(0).getProfilePath()).isEqualTo("/8qBylBsQf4llkGrWR3qAsOtOU8O.jpg");
  }

  @Test
  void search_for_a_celeb_name_should_return_404_list_of_celebs() {
    // Assert
    HttpClientResponseException httpClientResponseException =
        assertThrows(
            HttpClientResponseException.class,
            () -> {
              List<CelebrityResult> retrievedCelebrities =
                  this.httpClient
                      .toBlocking()
                      .retrieve(
                          HttpRequest.GET("search/persons/" + "abc%20xyz"),
                          Argument.listOf(CelebrityResult.class));
            });

    assertThat(httpClientResponseException.getMessage()).isEqualTo("Not Found");
    assertThat(httpClientResponseException.getStatus().getCode()).isEqualTo(404);
  }


  @Test
  void detailed_view_of_a_celebrity_returns_their_information() {
    CelebrityResult celebrityResult = this.httpClient
        .toBlocking()
        .retrieve(HttpRequest.GET("person/" + 500L), Argument.of(CelebrityResult.class));

    assertThat(celebrityResult).isNotNull();
    assertThat(celebrityResult.getName()).isEqualTo("Tom Cruise");
    assertThat(celebrityResult.getGender()).isEqualTo(2);
    assertThat(celebrityResult.getId()).isEqualTo(500L);
    assertThat(celebrityResult.getKnownForDepartment()).isEqualTo("Acting");
    assertThat(celebrityResult.getProfilePath()).isEqualTo("/8qBylBsQf4llkGrWR3qAsOtOU8O.jpg");
    assertThat(celebrityResult.getAge()).isEqualTo(59);
    assertThat(celebrityResult.getHomepage()).isEqualTo("http://www.tomcruise.com");
    assertThat(celebrityResult.getPlaceOfBirth()).isEqualTo("Syracuse, New York, USA");

  }

}
