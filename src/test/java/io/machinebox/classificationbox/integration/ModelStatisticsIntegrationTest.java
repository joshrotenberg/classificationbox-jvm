package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.response.ModelStatisticsResponse;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelStatisticsIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testModelStatistics() throws IOException {
        Response<ModelStatisticsResponse> response = getClient().service().modelStatistics(TEST_MODEL_ID).execute();

        assertTrue(response.isSuccessful());
        assertEquals(1, (int)response.body().getPredictions());
        assertEquals(3, (int)response.body().getExamples());
        assertEquals("class1", response.body().getClasses().get(0).getName());
        assertEquals(3, (int)response.body().getClasses().get(0).getExamples());
    }
}
