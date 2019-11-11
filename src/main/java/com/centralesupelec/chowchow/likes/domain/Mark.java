package com.centralesupelec.chowchow.likes.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Mark {
  GOOD,
  GREAT,
  AWESOME;

  @JsonValue
  public int toValue() {
    return ordinal();
  }
}
