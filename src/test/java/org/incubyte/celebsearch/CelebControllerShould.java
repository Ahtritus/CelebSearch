package org.incubyte.celebsearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CelebControllerShould {
  @Mock CelebrityService celebrityService;

  @Test
  void call_search_method_from_service() {
    CelebrityController celebrityController = new CelebrityController(celebrityService);
    String pathParameter = "Tom";
    celebrityController.search(pathParameter);
    verify(celebrityService).search(pathParameter);
  }
}
