package org.incubyte.celebsearch;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CelebControllerShould {
  @Mock CelebrityService celebrityService;

  @Test
  void call_search_method_from_service() {
    CelebrityController celebrityController = new CelebrityController(celebrityService);
    String pathParameter = "Tom";
    Optional<List<SearchResult>> results = celebrityController.search(pathParameter);
    verify(celebrityService).search("Tom");
  }

  @Test
  void call_get_person_by_id_method_from_service() {
    CelebrityController celebrityController = new CelebrityController(celebrityService);
    long personId = 500L;
    Optional<CelebrityResult> result = celebrityController.getPersonById(personId);
    verify(celebrityService).getPersonById(500L);
  }
}
