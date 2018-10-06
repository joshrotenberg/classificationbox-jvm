package io.machinebox.classificationbox.integration;

import io.machinebox.classificationbox.ClassificationBoxClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Before;

public abstract class AbstractIntegrationTest {
    public static final String TEST_MODEL_ID = "sentiment1";
    public static final String TEST_MODEL_NAME = "sentimentModel";

    private ClassificationBoxClient classificationBoxClient;

    public ClassificationBoxClient getClient() {
        return classificationBoxClient;
    }

    @Before
    public void setUp() {
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
    }
}
