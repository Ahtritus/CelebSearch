Feature: Demographics

  Scenario: Search for an actor, director etc.

    Given A person name "Tom Cruise"
    When A fan searches for  it
    Then display all persons matching the name
    And each result should display image, full name
    And there is an option to view other details

  Scenario: if searched name is not avaialble in the records.

    Given A person name "Tom Cruise"
    When A fan searches for  it
    And if there is no such person available
    Then display a funny error message

  Scenario: if searched name does not have image

    Given A person name "Tom Cruise"
    When A fan searches for  it
    And if there is no image available for that person
    Then display a generic avatar

  Scenario: name search must only allow alpha-numeric and dot(.)

    Given A person name "Tom Cruise//\-"
    When A fan searches for  it
    Then display results identical to "Tom Cruise"

  Scenario: View Details of the actor

    Given The fan is on the view details page
    Then it should display name, image, age, gender, place of birth
    And it also displays top five movies the person was part of