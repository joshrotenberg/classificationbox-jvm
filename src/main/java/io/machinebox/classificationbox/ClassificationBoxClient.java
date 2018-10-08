package io.machinebox.classificationbox;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * ClassificationBoxClient wraps a {@link ClassificationBoxService} suitable for interacting with a
 * <a href="https://machinebox.io">Machine Box</a> <a href="https://machinebox.io/docs/classificationbox"> Classificationbox</a>
 * instance.
 */
public class ClassificationBoxClient {

    private final ClassificationBoxService classificationBoxService;

    private ClassificationBoxClient(ClassificationBoxService service) {
        this.classificationBoxService = service;
    }

    /**
     * Access the actual service instance.
     *
     * @return the {@link ClassificationBoxService} service handle
     */
    public ClassificationBoxService service() {
        return this.classificationBoxService;
    }

    /**
     * Get a default client pointing at localhost:8080 with no authentication.
     *
     * @return a ClassificationBoxClient with the default configuration
     */
    public static ClassificationBoxClient defaultClient() {
        return ClassificationBoxClient.newBuilder().build();
    }

    /**
     * A builder for configuring a {@link ClassificationBoxClient}.
     */
    public static class Builder {
        public static final String DEFAULT_BASE_URL = "http://localhost:8080/";

        private final OkHttpClient.Builder okHttpClientBuilder;
        private final Retrofit.Builder retrofitBuilder;

        private String baseUrl;

        private Builder() {
            this.okHttpClientBuilder = new OkHttpClient.Builder();
            this.retrofitBuilder = new Retrofit.Builder();

            this.baseUrl = DEFAULT_BASE_URL;
        }

        /**
         * Set the baseUrl for your Classificationbox instance, i.e. "http://my.cb.org/"
         *
         * @param baseUrl a fully qualified top level url
         * @return {@link ClassificationBoxClient.Builder}
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Set the HTTP Basic Authentication username and password for your Classificationbox instance. Setting these will
         * implicitly use authentication for all calls to the configured instance.
         *
         * @param username your Basic Authentication username
         * @param password your Basic Authentication password
         * @return {@link ClassificationBoxClient.Builder}
         */
        public Builder withAuthentication(String username, String password) {
            this.okHttpClientBuilder.authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    if (response.request().header("Authorization") != null) {
                        return null;
                    }
                    String credential = Credentials.basic(username, password);

                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                }
            });
            return this;
        }

        /**
         * Add an OkHttpInterceptor to the underlying OkHttp client.
         *
         * @param interceptor an OkHttp interceptor
         * @return {@link ClassificationBoxClient.Builder}
         */
        public Builder addInterceptor(Interceptor interceptor) {
            this.okHttpClientBuilder.addInterceptor(interceptor);
            return this;
        }

        /**
         * Build the client.
         *
         * @return {@link ClassificationBoxClient}
         */
        public ClassificationBoxClient build() {

            Gson gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            Retrofit retrofit = this.retrofitBuilder
                    .baseUrl(this.baseUrl)
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return new ClassificationBoxClient(retrofit.create(ClassificationBoxService.class));
        }
    }

    /**
     * Get a new {@link ClassificationBoxClient.Builder}
     *
     * @return {@link ClassificationBoxClient.Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }
}
