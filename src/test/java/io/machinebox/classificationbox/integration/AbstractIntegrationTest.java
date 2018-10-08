package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.ClassificationBoxClient;
import io.machinebox.classificationbox.common.ModelOption;
import io.machinebox.classificationbox.request.CreateModelRequest;
import io.machinebox.classificationbox.response.CreateModelResponse;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.After;
import org.junit.Before;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public abstract class AbstractIntegrationTest {
    public static final String TEST_MODEL_ID = "sentiment1";
    public static final String TEST_MODEL_NAME = "sentimentModel";
    public static final String TEST_MODEL_CLASS1 = "class1";
    public static final String TEST_MODEL_CLASS2 = "class2";
    public static final String TEST_MODEL_CLASS3 = "class3";

    private ClassificationBoxClient classificationBoxClient;

    public ClassificationBoxClient getClient() {
        return classificationBoxClient;
    }

    @Before
    public void setUp() throws IOException {
        String basicAuthUsername = System.getenv("MB_BASICAUTH_USER");
        String basicAuthPassword = System.getenv("MB_BASICAUTH_PASS");
        String baseUrl = System.getenv("CLASSIFICATIONBOX_BASE_URL");
        String enableLogging = System.getenv("CLASSIFICATIONBOX_ENABLE_LOGGING");

        ClassificationBoxClient.Builder builder = ClassificationBoxClient.newBuilder();

        if (basicAuthUsername != null && basicAuthPassword != null) {
            builder.withAuthentication("josh", "p");
        }

        if (enableLogging != null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    System.out.println(message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        if (baseUrl != null) {
            builder.baseUrl(baseUrl);
        }
        this.classificationBoxClient = builder.build();
        testModelCreate();
    }

    private void testModelCreate() throws IOException {
        List<String> classes = new ArrayList<String>();
        classes.add(TEST_MODEL_CLASS1);
        classes.add(TEST_MODEL_CLASS2);
        classes.add(TEST_MODEL_CLASS3);

        CreateModelRequest cmr = new CreateModelRequest(TEST_MODEL_NAME, classes)
                .withId(TEST_MODEL_ID)
                .addOption(ModelOption.NGRAMS, 1)
                .addOption(ModelOption.SKIPGRAMS, 1);

        Response<CreateModelResponse> response = getClient().service().createModel(cmr).execute();
        assertTrue(response.isSuccessful());
    }

    @After
    public void tearDownModel() throws IOException {
        Response<ResponseBody> response = getClient().service().deleteModel(TEST_MODEL_ID).execute();
        assertTrue(response.isSuccessful());
    }
}
