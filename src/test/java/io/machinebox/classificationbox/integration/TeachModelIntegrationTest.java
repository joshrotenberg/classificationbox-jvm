package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.common.FeatureType;
import io.machinebox.classificationbox.common.ModelInput;
import io.machinebox.classificationbox.request.TeachModelMultiRequest;
import io.machinebox.classificationbox.request.TeachModelRequest;
import io.machinebox.classificationbox.response.TeachModelMultiResponse;
import io.machinebox.classificationbox.response.TeachModelResponse;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeachModelIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testTeachModel() throws IOException {

        TeachModelRequest tmr = new TeachModelRequest("class1");
        tmr.addInput(new ModelInput("user_age", FeatureType.NUMBER, "25"));
        tmr.addInput(new ModelInput("user_interest", FeatureType.LIST, "music,cooking,ml"));
        tmr.addInput(new ModelInput("user_location", FeatureType.KEYWORD, "London"));

        Response<TeachModelResponse> response = getClient().service().teachModel(TEST_MODEL_ID, tmr).execute();
        assertTrue(response.isSuccessful());
        assertEquals(true, response.body().getSuccess());
    }

    @Test
    public void testTeachModelMulti() throws IOException {

        TeachModelMultiRequest tmmr = new TeachModelMultiRequest();

        TeachModelMultiRequest.Example e1 = new TeachModelMultiRequest.Example("class1");
        e1.addInput(new ModelInput("user_age", FeatureType.NUMBER, "25"));

        tmmr.addExample(e1);

        TeachModelMultiRequest.Example e2 = new TeachModelMultiRequest.Example("class1");
        e2.addInput(new ModelInput("user_age", FeatureType.NUMBER, "55"));

        tmmr.addExample(e1);

        Response<TeachModelMultiResponse> response = getClient().service().teachModelMulti(TEST_MODEL_ID, tmmr).execute();
        assertTrue(response.isSuccessful());
    }
}
