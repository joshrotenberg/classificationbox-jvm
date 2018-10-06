package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.common.FeatureType;
import io.machinebox.classificationbox.common.ModelInput;
import io.machinebox.classificationbox.request.MakePredictionsRequest;
import io.machinebox.classificationbox.response.MakePredictionsResponse;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MakePredictionsIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testMakePredictions() throws IOException {
        MakePredictionsRequest mpr = new MakePredictionsRequest(10);
        mpr.addInput(new ModelInput("user_age", FeatureType.NUMBER, "25"));
        mpr.addInput(new ModelInput("user_interests", FeatureType.LIST, "music,cooking,ml"));
        mpr.addInput(new ModelInput("user_location", FeatureType.KEYWORD, "London"));

        Response<MakePredictionsResponse> response = getClient().service().makePredictions(TEST_MODEL_ID, mpr).execute();
        assertTrue(response.isSuccessful());
        assertEquals(3, response.body().getClasses().size());
    }
}
