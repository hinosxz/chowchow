package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import com.centralesupelec.chowchow.likes.domain.Mark;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The polymorphism Main class to handle alerts.
 *
 * <p>This class uses a factory pattern to wrap all the alerts. That way everything is encapsulated
 * into the abstract class UserAlert.
 *
 * <p>The user's alerts are returned using an observer pattern by calling 'getAlerts'. This is
 * possible because of the polymorphism around UserAlert and the classes extending it. That way the
 * logic around returning or not an alert is encapsulated into the OOP class structure.
 *
 * @see UserAlertFactory
 * @see UserAlert
 */
public class UserAlertsManager {

  /** The factory used to create the wrappers around the alerts */
  private UserAlertFactory userAlertFactory;
  /** The wrappers around the alerts */
  private List<UserAlert> userAlerts;

  /**
   * Constructor that takes a given HashMap mapping alerts with their marks.
   *
   * <p>The HashMap is used to create the 'userAlerts': For every key in the map, the factory is
   * used to create a wrapper. The wrapper is then added to the list
   *
   * @param alertsMap the given HashMap
   */
  public UserAlertsManager(HashMap<AlertDTO, Mark> alertsMap) {
    this.userAlertFactory = new UserAlertFactory();
    this.userAlerts = new ArrayList<>();
    alertsMap
        .keySet()
        .forEach(
            alertDTO ->
                this.addAlert(alertDTO, !Objects.isNull(alertsMap.getOrDefault(alertDTO, null))));
  }

  /**
   * Creates a wrapper around a given alert and adds it to the userAlerts.
   *
   * @param alert the given alert
   * @param isRated whether or not the show has been rated
   */
  public void addAlert(AlertDTO alert, boolean isRated) {
    this.userAlerts.add(this.userAlertFactory.getUserAlert(alert, isRated));
  }

  /**
   * Returns the alerts.
   *
   * <p>The observer pattern is used here by calling getAlert method on UserAlert objects. That way
   * the logic is hidden by polymorphism and encapsulation.
   *
   * @return The list of the alerts.
   */
  public List<AlertDTO> getAlerts() {
    return userAlerts.stream()
        .map(userAlerts -> userAlerts.getAlert())
        .filter(alertDTO -> !Objects.isNull(alertDTO) && alertDTO.isEpisodeSoon())
        .collect(Collectors.toList());
  }
}
