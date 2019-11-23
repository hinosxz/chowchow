package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

public class LikedUserAlert extends UserAlert {

  public LikedUserAlert(AlertDTO alert) {
    super(alert);
  }

  @Override
  public AlertDTO getAlert() {
    return alert;
  }
}
