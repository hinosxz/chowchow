package com.centralesupelec.chowchow.TMDB.controllers.user_alerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

/**
 * The factory for UserAlert.
 *
 * <p>This class is used to instantiate LikedUserAlert and OnWatchListUserAlert objects. That way we
 * ensure that there is no non-rated show in a LikedUserAlert wrapper or a rated show in a
 * OnWatchListUserAlert. It allows us to encapsulate the logic by dealing only with UserAlert
 * abstract class.
 *
 * @see UserAlert
 * @see LikedUserAlert
 * @see OnWatchListUserAlert
 */
public class UserAlertFactory {

  /**
   * Returns a new UserAlert wrapping the given alert according to the given isRated boolean.
   *
   * @param alert the given alert
   * @param isRated whether or not the show has been rated
   * @return A new wrapper around the given alert
   */
  public UserAlert getUserAlert(AlertDTO alert, boolean isRated) {
    if (isRated) {
      return new LikedUserAlert(alert);
    }
    return new OnWatchListUserAlert(alert);
  }
}
