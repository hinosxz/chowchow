package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import com.centralesupelec.chowchow.likes.domain.Mark;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserAlertsManager {

  private UserAlertFactory userAlertFactory;
  private List<UserAlert> userAlerts;

  public UserAlertsManager(HashMap<AlertDTO, Mark> alertsMap) {
    this.userAlertFactory = new UserAlertFactory();
    this.userAlerts = new ArrayList<>();
    alertsMap
        .keySet()
        .forEach(
            alertDTO ->
                this.addAlert(alertDTO, !Objects.isNull(alertsMap.getOrDefault(alertDTO, null))));
  }

  public void addAlert(AlertDTO alert, boolean isRated) {
    this.userAlerts.add(this.userAlertFactory.getUserAlert(alert, isRated));
  }

  public List<AlertDTO> getAlerts() {
    System.out.println(userAlerts);
    return userAlerts.stream()
        .map(userAlerts -> userAlerts.getAlert())
        .filter(alertDTO -> !Objects.isNull(alertDTO))
        .filter(alertDTO -> alertDTO.isEpisodeSoon())
        .collect(Collectors.toList());
  }
}
