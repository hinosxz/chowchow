package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

public abstract class UserAlert {
  protected AlertDTO alert;

  public UserAlert(AlertDTO alert) {
    this.alert = alert;
  }

  public abstract AlertDTO getAlert();
}
