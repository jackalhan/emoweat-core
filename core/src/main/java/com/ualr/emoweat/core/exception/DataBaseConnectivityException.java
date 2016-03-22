package com.ualr.emoweat.core.exception;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class DataBaseConnectivityException extends java.sql.SQLException {
    public int errorCode = 0;
    /**
     * NoConnectionException constructor comment.
     */
    public DataBaseConnectivityException()
    {
        super();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/30/00 10:32:11 AM)
     * @param s java.lang.String
     */
    public DataBaseConnectivityException(String s)
    {
        super(s);
    }
}