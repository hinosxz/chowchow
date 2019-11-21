package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

public class GoldUserAlert extends UserAlert {

  public GoldUserAlert(AlertDTO alert) {
    super(alert);
  }

  @Override
  public AlertDTO getAlert() {
    return alert;
  }
}
