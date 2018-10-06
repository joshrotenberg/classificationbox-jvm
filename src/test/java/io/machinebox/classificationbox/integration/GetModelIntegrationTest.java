package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.common.ModelOption;
import io.machinebox.classificationbox.response.GetModelResponse;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GetModelIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testModel() throws IOException {
        Response<GetModelResponse> response = getClient().service().getModel(TEST_MODEL_ID).execute();

        assertTrue(response.isSuccessful());
        assertEquals(TEST_MODEL_ID, response.body().getId());
        assertEquals(TEST_MODEL_NAME, response.body().getName());
        assertThat(response.body().getOptions(), hasEntry(ModelOption.NGRAMS, 1));
        assertThat(response.body().getOptions(), hasEntry(ModelOption.SKIPGRAMS, 1));
        assertTrue(response.body().getClasses().containsAll(Arrays.asList("class1", "class2", "class3")));
        assertFalse(response.body().getPredictOnly());
    }
}
