package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

/**
 * Implements UserAlert to wrap a non-rated shows on the user's watch list.
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
