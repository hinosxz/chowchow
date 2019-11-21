package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

public class BasicUserAlert extends UserAlert {

  public BasicUserAlert(AlertDTO alert) {
    super(alert);
  }

  @Override
  public AlertDTO getAlert() {
    return null;
  }
}
