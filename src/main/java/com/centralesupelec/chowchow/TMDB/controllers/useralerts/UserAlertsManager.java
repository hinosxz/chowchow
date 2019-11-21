package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserAlertsManager {

  private UserAlertFactory userAlertFactory;
  private List<UserAlert> userAlerts;

  public UserAlertsManager() {
    this.userAlertFactory = new UserAlertFactory();
    this.userAlerts = new ArrayList<>();
  }

  public void addAlert(AlertDTO alert, boolean isRated) {
    this.userAlerts.add(this.userAlertFactory.getUserAlert(alert, isRated));
  }

  public List<AlertDTO> getAlerts() {
    return userAlerts.stream()
        .map(userAlerts -> userAlerts.getAlert())
        .filter(alertDTO -> alertDTO.isEpisodeSoon())
        .collect(Collectors.toList());
  }
}
