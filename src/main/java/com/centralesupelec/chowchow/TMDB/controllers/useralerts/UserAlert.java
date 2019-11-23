package com.centralesupelec.chowchow.TMDB.controllers.useralerts;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;

/**
 * Abstract that is a wrapper around an AlertDTO object.
 *
 * <p>It is used to retrieve or not the wrapped show according to whether or not it has been rated.
 *
 * @see AlertDTO
 */
public abstract class UserAlert {
  /** The wrapped alert */
  protected AlertDTO alert;

  /**
   * Constructor
   *
   * @param alert The AlertDTO to wrap
   */
  public UserAlert(AlertDTO alert) {
    this.alert = alert;
  }

  /**
   * Returns the wrapper alert if the show is rated, null otherwise.
   *
   * @return The wrapped alert or null
   */
  public abstract AlertDTO getAlert();
}
