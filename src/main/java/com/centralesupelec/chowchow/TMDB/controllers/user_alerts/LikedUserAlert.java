package com.centralesupelec.chowchow.TMDB.controllers.user_alerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

/**
 * Implements UserAlert to wrap an alert corresponding to a rated shows on the user's watch list.
 *
 * <p>The alert is always returned by the getAlert() function because we do send alerts for shows on
 * watch list that has been rated (i.e liked)
 *
 * @see UserAlert
 */
public class LikedUserAlert extends UserAlert {

  /**
   * Constructor
   *
   * @param alert The AlertDTO to wrap
   */
  public LikedUserAlert(AlertDTO alert) {
    super(alert);
  }

  /**
   * Returns the wrapped alert.
   *
   * @return the wrapped alert
   * @see UserAlert#getAlert()
   */
  @Override
  public AlertDTO getAlert() {
    return alert;
  }
}
