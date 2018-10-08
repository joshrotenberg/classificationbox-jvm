package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.response.HealthzResponse;
import io.machinebox.classificationbox.response.InfoResponse;
import okhttp3.ResponseBody;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class InfoStatusIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testInfo() throws IOException {
        Response<InfoResponse> response = getClient().service().info().execute();
        assertTrue(response.isSuccessful());
        assertTrue(response.body().getSuccess());
        assertEquals(InfoResponse.Status.READY, response.body().getStatus());
        assertEquals("classificationbox", response.body().getName());
    }

    @Test
    public void testHealthz() throws IOException {
        Response<HealthzResponse> response = getClient().service().healthz().execute();
        assertTrue(response.isSuccessful());
        assertTrue(response.body().getSuccess());
        assertEquals("classificationbox", response.body().getMetaData().getBoxName());
    }

    @Test
    public void testLiveness() throws IOException {
        Response<ResponseBody> response = getClient().service().liveness().execute();
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testReadyz() throws IOException {
        Response<ResponseBody> response = getClient().service().readyz().execute();
        assertTrue(response.isSuccessful());
    }
}
