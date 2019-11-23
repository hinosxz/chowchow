package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

public class OnWatchListUserAlert extends UserAlert {

  public OnWatchListUserAlert(AlertDTO alert) {
    super(alert);
  }

  @Override
  public AlertDTO getAlert() {
    return null;
  }
}
