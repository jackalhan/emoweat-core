package com.ualr.emoweat.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class Converter {

    public String eliminateNonUnicodes(String text) {
        String manipulatedText = "";
        try {
            byte[] textBytes = text.getBytes("UTF-8");
            manipulatedText = new String(textBytes, "UTF-8");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Pattern unicodeOutliers = Pattern.compile("[^\\x00-\\x7F]", Pattern.UNICODE_CASE | Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
        Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(manipulatedText);
        manipulatedText = unicodeOutlierMatcher.replaceAll(" ");
        return manipulatedText;
    }
}
