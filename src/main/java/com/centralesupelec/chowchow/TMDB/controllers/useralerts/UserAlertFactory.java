package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

public class UserAlertFactory {

  public UserAlert getUserAlert(AlertDTO alert, boolean isRated) {
    if (isRated) {
      return new LikedUserAlert(alert);
    }
    return new OnWatchListUserAlert(alert);
  }
}
