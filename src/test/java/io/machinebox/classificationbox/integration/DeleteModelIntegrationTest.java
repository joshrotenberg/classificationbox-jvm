package io.machinebox.classificationbox.integration;

import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class DeleteModelIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testDeleteModel() throws IOException {
        Response<Void> response = getClient().service().deleteModel(TEST_MODEL_ID).execute();
        assertTrue(response.isSuccessful());
    }
}
