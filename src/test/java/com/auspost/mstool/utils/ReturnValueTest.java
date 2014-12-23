package com.auspost.mstool.utils;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ReturnValueTest {

    @Test
    public void testCreateSSuccessReturnValue_just_msg() throws Exception {
        Map successReturnValue = ReturnValue.ssuccess("OK");
        assertTrue(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.MESSAGE).equals("OK"));
    }

    @Test
    public void testCreateSuccessReturnValue() throws Exception {
        Map successReturnValue = ReturnValue.success("OK", ReturnValue.CLIENT, "CLIENT_OBJ", ReturnValue.SESSION, "SESSION_OBJ");
        assertTrue(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.CLIENT).equals("CLIENT_OBJ"));
        assertTrue(successReturnValue.get(ReturnValue.SESSION).equals("SESSION_OBJ"));
        assertTrue(successReturnValue.get(ReturnValue.MESSAGE).equals("OK"));
    }

    @Test
    public void testCreateSSuccessReturnValue() throws Exception {
        Map successReturnValue = ReturnValue.ssuccess(ReturnValue.CLIENT, "CLIENT_OBJ", ReturnValue.SESSION, "SESSION_OBJ");
        assertTrue(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.CLIENT).equals("CLIENT_OBJ"));
        assertTrue(successReturnValue.get(ReturnValue.SESSION).equals("SESSION_OBJ"));
    }

    @Test
    public void testCreateSuccessReturnValue_one_arg() throws Exception {
        Map successReturnValue = ReturnValue.success("OK", "SESSION_OBJ");
        assertTrue(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.DATA).equals("SESSION_OBJ"));
        assertTrue(successReturnValue.get(ReturnValue.MESSAGE).equals("OK"));
    }

    @Test
    public void testCreateSuccessReturnValue_one_arg_without_msg() throws Exception {
        Map successReturnValue = ReturnValue.success("SESSION_OBJ");
        assertTrue(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.DATA).equals("SESSION_OBJ"));
    }
    @Test
    public void testCreateFailedReturnValue() throws Exception {
        Map successReturnValue = ReturnValue.failed("NOT OK", ReturnValue.CLIENT, "CLIENT_OBJ", ReturnValue.SESSION, "SESSION_OBJ");
        assertFalse(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.CLIENT).equals("CLIENT_OBJ"));
        assertTrue(successReturnValue.get(ReturnValue.SESSION).equals("SESSION_OBJ"));
        assertTrue(successReturnValue.get(ReturnValue.MESSAGE).equals("NOT OK"));
    }

    @Test
    public void testCreateSFailedReturnValue() throws Exception {
        Map successReturnValue = ReturnValue.sfailed(ReturnValue.CLIENT, "CLIENT_OBJ", ReturnValue.SESSION, "SESSION_OBJ");
        assertFalse(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.CLIENT).equals("CLIENT_OBJ"));
        assertTrue(successReturnValue.get(ReturnValue.SESSION).equals("SESSION_OBJ"));
    }

    @Test
    public void testCreateSFailedReturnValue_just_msg() throws Exception {
        Map successReturnValue = ReturnValue.sfailed("NOT OK");
        assertFalse(ReturnValue.isSuccess(successReturnValue));
        assertTrue(successReturnValue.get(ReturnValue.MESSAGE).equals("NOT OK"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSuccessReturnValue_odd_no_params() throws Exception {
        ReturnValue.success("OK", ReturnValue.CLIENT, "CLIENT_OBJ", ReturnValue.SESSION);
    }

}