package org.incubyte.celebsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CelebrityResult {

  private int gender;
  private long id;
  private String knownForDepartment;
  private String profilePath;
  private String name;
  private int age;
  private String homepage;
  private String placeOfBirth;

  public CelebrityResult() {}

  public int getGender() {
    return gender;
  }

  public long getId() {
    return id;
  }

  @JsonProperty("known_for_department")
  public String getKnownForDepartment() {
    return knownForDepartment;
  }

  @JsonProperty("profile_path")
  public String getProfilePath() {
    return profilePath;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getHomepage() {
    return homepage;
  }

  @JsonProperty("place_of_birth")
  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

}
