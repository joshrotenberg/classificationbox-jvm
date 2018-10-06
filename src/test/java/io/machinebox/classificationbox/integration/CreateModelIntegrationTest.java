package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.common.ModelOption;
import io.machinebox.classificationbox.request.CreateModelRequest;
import io.machinebox.classificationbox.response.CreateModelResponse;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateModelIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testCreateModel() throws IOException {

        List<String> classes = new ArrayList<String>();
        classes.add("class1");
        classes.add("class2");
        classes.add("class3");

        CreateModelRequest cmr = new CreateModelRequest(TEST_MODEL_NAME, classes)
                .withId(TEST_MODEL_ID)
                .addOption(ModelOption.NGRAMS, 1)
                .addOption(ModelOption.SKIPGRAMS, 1);

        Response<CreateModelResponse> response = getClient().service().createModel(cmr).execute();
        assertTrue(response.isSuccessful());

        assertTrue(response.body().getSuccess());
        assertEquals(TEST_MODEL_ID, response.body().getId());
        assertEquals(TEST_MODEL_NAME, response.body().getName());
        assertThat(response.body().getOptions(), hasEntry(ModelOption.NGRAMS, 1));
        assertThat(response.body().getOptions(), hasEntry(ModelOption.SKIPGRAMS, 1));

        assertTrue(response.body().getClasses().containsAll(Arrays.asList("class1", "class2", "class3")));

    }
}
