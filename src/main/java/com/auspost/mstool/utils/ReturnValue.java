package com.auspost.mstool.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by managea on 10/12/2014.
 */
public class ReturnValue {

    public static String STATUS = "STATUS";
    public static String MESSAGE = "MESSAGE";
    public static String DATA = "DATA";
    public static String CLIENT = "CLIENT";
    public static String SESSION = "SESSION";

    public enum ResultStatus {
        SUCCESSFUL,
        FAILED,
    }

    public static Map success(String msg, Object... args) {
        return createReturnValueMap(msg, ResultStatus.SUCCESSFUL, args);
    }

    public static Map success(String msg, Object value) {
        return createReturnValueMap(msg, ResultStatus.SUCCESSFUL, DATA, value);
    }

    /*
    Short success. Without message.
     */
    public static Map ssuccess(Object... args) {
        return createReturnValueMap(null, ResultStatus.SUCCESSFUL, args);
    }

    public static Map success(Object value) {
        return createReturnValueMap(null, ResultStatus.SUCCESSFUL, DATA, value);
    }

    public static Map ssuccess(String msg) {
        return createReturnValueMap(msg, ResultStatus.SUCCESSFUL);
    }

    public static Map failed(String msg, Object... args) {
        return createReturnValueMap(msg, ResultStatus.FAILED, args);
    }

    public static Map failed(String msg, Object value) {
        return createReturnValueMap(msg, ResultStatus.FAILED, DATA, value);
    }

    public static Map sfailed(String msg) {
        return createReturnValueMap(msg, ResultStatus.FAILED);
    }

    /*
    Short failed. Without message.
     */
    public static Map sfailed(Object... args) {
        return createReturnValueMap(null, ResultStatus.FAILED, args);
    }

    public static Map failed(Object value) {
        return createReturnValueMap(null, ResultStatus.FAILED, DATA, value);
    }

    private static Map createReturnValueMap(String msg, ResultStatus status, Object... args) {
        HashMap result = new HashMap();

        if (args.length % 2 == 1) {
            throw new IllegalArgumentException("Even number of parameters expected.");
        }

        for (int i = 0; i < args.length - 1; i++) {
            if (i % 2 == 0) {
                result.put(args[i], args[i + 1]);
            }
        }
        if (msg != null)
            result.put(MESSAGE, msg);
        if (status != null) {
            result.put(STATUS, status);
        }
        return result;
    }


    public static boolean isSuccess(Map result) {
        return result.get(STATUS) != null && result.get(STATUS) == ResultStatus.SUCCESSFUL;
    }

    public static String getMessage(Map map) {
        return (String) map.get(MESSAGE);
    }

    public static Object getData(Map map) {
        return map.get(DATA);
    }

}
