package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.common.FeatureType;
import io.machinebox.classificationbox.common.ModelInput;
import io.machinebox.classificationbox.request.MakePredictionsRequest;
import io.machinebox.classificationbox.request.TeachModelRequest;
import io.machinebox.classificationbox.response.MakePredictionsResponse;
import io.machinebox.classificationbox.response.ModelStatisticsResponse;
import io.machinebox.classificationbox.response.TeachModelResponse;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelStatisticsIntegrationTest extends AbstractIntegrationTest {

    private void teachModel() throws IOException {

        TeachModelRequest tmr = new TeachModelRequest(TEST_MODEL_CLASS1);
        tmr.addInput(new ModelInput("age", FeatureType.NUMBER, "21"));

        Response<TeachModelResponse> response = getClient().service().teachModel(TEST_MODEL_ID, tmr).execute();
        assertTrue(response.isSuccessful());
    }

    private void makePredictions() throws IOException {
        MakePredictionsRequest mpr = new MakePredictionsRequest(2);
        mpr.addInput(new ModelInput("age", FeatureType.NUMBER, "25"));

        Response<MakePredictionsResponse> response = getClient().service().makePredictions(TEST_MODEL_ID, mpr).execute();
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testModelStatistics() throws IOException {

        teachModel();
        makePredictions();

        Response<ModelStatisticsResponse> response = getClient().service().modelStatistics(TEST_MODEL_ID).execute();

        assertTrue(response.isSuccessful());
        assertEquals(1, (int)response.body().getPredictions());
        assertEquals(1, (int) response.body().getExamples());
        assertEquals(TEST_MODEL_CLASS1, response.body().getClasses().get(0).getName());
        assertEquals(1, (int) response.body().getClasses().get(0).getExamples());
    }
}
