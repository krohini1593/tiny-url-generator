package org.inf.java;

import java.util.HexFormat;

public class Utils {
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b: bytes) {
            var hexdigitL = (b>>4) & 0x0F;
            sb.append(String.format("%x",hexdigitL));
            var hexdigitR = b & 0x0F;
            sb.append(String.format("%x",hexdigitR));
        }
        return sb.toString();
    }
}
