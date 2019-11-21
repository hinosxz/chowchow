package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

public class UserAlertFactory {

  public UserAlert getUserAlert(AlertDTO alert, boolean isRated) {
    if (isRated) {
      return new GoldUserAlert(alert);
    }
    return new BasicUserAlert(alert);
  }
}
