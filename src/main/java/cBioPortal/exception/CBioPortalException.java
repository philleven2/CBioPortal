package cBioPortal.exception;

public class CBioPortalException extends Exception {

  private static final long serialVersionUID = 5182137632236335764L;

  /**
   * Constructor for CBioPortalException.
   */
  public CBioPortalException() {

    super();

  }

  /**
   * Constructor for CBioPortalException.
   * 
   * @param s
   */
  public CBioPortalException(String s) {

    super(s);

  }

  /**
   * Constructor for CBioPortalException.
   * 
   * @param s
   * @param t
   */

  public CBioPortalException(String s, Throwable t) {

    super(s, t);

  }

}
