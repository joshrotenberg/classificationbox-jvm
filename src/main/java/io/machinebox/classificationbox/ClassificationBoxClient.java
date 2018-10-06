package io.machinebox.classificationbox;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ClassificationBoxClient {

    private final ClassificationBoxService classificationBoxService;

    private ClassificationBoxClient(ClassificationBoxService service) {
        this.classificationBoxService = service;
    }

    public ClassificationBoxService service() {
        return this.classificationBoxService;
    }

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

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

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

        public Builder addInterceptor(Interceptor interceptor) {
            this.okHttpClientBuilder.addInterceptor(interceptor);
            return this;
        }

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

    public static Builder newBuilder() {
        return new Builder();
    }
}
