package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.common.ModelOption;
import io.machinebox.classificationbox.response.UploadStateResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertFalse;

public class StateIntegrationTest extends AbstractIntegrationTest {

    private static String FILE_NAME = "state.data";

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void testDownloadUploadState() throws IOException {
        Response<ResponseBody> response = getClient().service().downloadState(TEST_MODEL_ID).execute();
        assertTrue(response.isSuccessful());

        writeStateFile(response.body(), FILE_NAME);

        MultipartBody.Part body = readStateFile(FILE_NAME);
        Response<UploadStateResponse> response2 = getClient().service().uploadState(body).execute();

        assertTrue(response2.isSuccessful());
        assertEquals(TEST_MODEL_ID, response2.body().getId());
        assertEquals(TEST_MODEL_NAME, response2.body().getName());
        assertThat(response2.body().getOptions(), hasEntry(ModelOption.NGRAMS, 1));
        assertThat(response2.body().getOptions(), hasEntry(ModelOption.SKIPGRAMS, 1));
        assertFalse(response2.body().getPredictOnly());

    }

    private File createStateFile(String fileName) {
        return new File(testFolder.getRoot() + File.separator + fileName);
    }

    private MultipartBody.Part readStateFile(String fileName) {

        File stateFile = createStateFile(fileName);
        RequestBody requestBody = RequestBody.create( MediaType.parse("application/octet-stream"), stateFile);
        return MultipartBody.Part.createFormData("file", stateFile.getName(), requestBody);
    }

    private void writeStateFile(ResponseBody body, String fileName) throws IOException {

        File stateFile = createStateFile(fileName);
        BufferedSink sink = Okio.buffer(Okio.sink(stateFile));
        sink.writeAll(body.source());
        sink.close();
    }
}
