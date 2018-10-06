package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.response.ListModelsResponse;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListModelsIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testListModels() throws IOException {
        Response<ListModelsResponse> response = getClient().service().listModels().execute();

        assertTrue(response.isSuccessful());
        assertEquals(1, response.body().getModels().size());
        assertEquals(TEST_MODEL_ID, response.body().getModels().get(0).getId());
        assertEquals(TEST_MODEL_NAME, response.body().getModels().get(0).getName());
    }
}
