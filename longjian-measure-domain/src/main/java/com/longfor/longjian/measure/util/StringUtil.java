package com.longfor.longjian.measure.util;

public class StringUtil {

    public static String[] getPathSlice(String keyPath) {
        if (keyPath.charAt(0) == '/') {
            keyPath = keyPath.substring(1);
        }
        if (keyPath.charAt(keyPath.length() - 1) == '/') {
            keyPath = keyPath.substring(0, keyPath.length() - 1);
        }
        return keyPath.split("/");
    }

    public static String keysToPath(String[] keys) {
        if (keys.length > 0) {
            String result = "";
            if (keys.length == 1) {
                return "/" + keys[0] + "/";
            } else {
                for (String s : keys
                ) {
                    result += s + "/";
                }
                return "/" + result;
            }
        }
        return "/";
    }


//    public static void main(String[] args) {
//        System.out.println(keysToPath(new String[]{"1"}));
//    }
}
