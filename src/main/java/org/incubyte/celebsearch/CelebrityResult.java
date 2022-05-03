package org.incubyte.celebsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CelebrityResult {

  private int gender;
  private long id;

  @JsonProperty("known_for_department")
  private String knownForDepartment;

  @JsonProperty("profile_path")
  private String profilePath;

  private String name;

  @JsonProperty("birthday")
  private String birthday;

  private int age;
  private String homepage;

  @JsonProperty("place_of_birth")
  private String placeOfBirth;

  public CelebrityResult() {}

  public int getGender() {
    return gender;
  }

  public long getId() {
    return id;
  }

  public String getKnownForDepartment() {
    return knownForDepartment;
  }

  public String getProfilePath() {
    return profilePath;
  }

  public String getName() {
    return name;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
