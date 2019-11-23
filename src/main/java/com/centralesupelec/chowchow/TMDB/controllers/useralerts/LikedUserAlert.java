package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

/**
 * Implements UserAlert to wrap a rated shows on the user's watch list.
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
