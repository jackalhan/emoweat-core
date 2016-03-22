package com.ualr.emoweat.core.exception;
/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/
public class NoDBConnectionException extends DataBaseConnectivityException {
/**
 * NoDBConnectionException constructor comment.
 */
public NoDBConnectionException() {
	super();
}
/**
 * NoDBConnectionException constructor comment.
 * @param s java.lang.String
 */
public NoDBConnectionException(String s) {
	super(s);
}
}
