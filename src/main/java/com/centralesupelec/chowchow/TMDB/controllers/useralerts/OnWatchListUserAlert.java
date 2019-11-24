package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

/**
 * Implements UserAlert interface to wrap an alert corresponding to a show on the user's watch list.
 *
 * <p>The alert is never returned by the getAlert() function because we do not send alerts for shows
 * on watch list that has not been rated (i.e liked)
 *
 * @see UserAlert
 */
public class OnWatchListUserAlert extends UserAlert {

  /**
   * Constructor
   *
   * @param alert The AlertDTO to wrap
   */
  public OnWatchListUserAlert(AlertDTO alert) {
    super(alert);
  }

  /**
   * Returns null.
   *
   * @return null
   * @see UserAlert#getAlert()
   */
  @Override
  public AlertDTO getAlert() {
    return null;
  }
}
