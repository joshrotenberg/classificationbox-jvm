package io.machinebox.classificationbox;

import io.machinebox.classificationbox.request.CreateModelRequest;
import io.machinebox.classificationbox.request.MakePredictionsRequest;
import io.machinebox.classificationbox.request.TeachModelMultiRequest;
import io.machinebox.classificationbox.request.TeachModelRequest;
import io.machinebox.classificationbox.response.*;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * The Classificationbox <a href="https://square.github.io/retrofit/">Retrofit</a> service interface. This API follows
 * the Classificationbox REST API almost directly, so some familiarity with it is suggested. See
 * {@link ClassificationBoxClient} for usage.
 */
public interface ClassificationBoxService {

    /**
     * Create a model.
     *
     * @param request a {@link CreateModelRequest}
     * @return {@link CreateModelResponse}
     */
    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models")
    Call<CreateModelResponse> createModel(@Body CreateModelRequest request);

    /**
     * Teach a model.
     *
     * @param modelId the ID of the model
     * @param request a {@link TeachModelRequest}
     * @return {@link TeachModelResponse}
     */
    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models/{modelId}/teach")
    Call<TeachModelResponse> teachModel(@Path("modelId") String modelId, @Body TeachModelRequest request);

    /**
     * Teach multiple classes of a model.
     *
     * @param modelId the ID of the model
     * @param request a {@link TeachModelMultiRequest}
     * @return {@link TeachModelResponse}
     */
    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models/{modelId}/teach-multi")
    Call<TeachModelMultiResponse> teachModelMulti(@Path("modelId") String modelId, @Body TeachModelMultiRequest request);

    /**
     * Make predictions based on a model.
     *
     * @param modelId the ID of the model
     * @param request a {@link MakePredictionsRequest}
     * @return {@link MakePredictionsResponse}
     */
    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models/{modelId}/predict")
    Call<MakePredictionsResponse> makePredictions(@Path("modelId") String modelId, @Body MakePredictionsRequest request);

    /**
     * Get statistics about a model.
     *
     * @param modelId the ID of the model
     * @return {@link ModelStatisticsResponse}
     */
    @Headers({"Accept: application/json; charset=utf-8"})
    @GET("classificationbox/models/{modelId}/stats")
    Call<ModelStatisticsResponse> modelStatistics(@Path("modelId") String modelId);

    /**
     * List the available models.
     *
     * @return {@link ListModelsResponse}
     */
    @Headers({"Accept: application/json; charset=utf-8"})
    @GET("classificationbox/models")
    Call<ListModelsResponse> listModels();

    /**
     * Get an individual model.
     *
     * @param modelId the ID of the model
     * @return {@link GetModelResponse}
     */
    @Headers({"Accept: application/json; charset=utf-8"})
    @GET("classificationbox/models/{modelId}")
    Call<GetModelResponse> getModel(@Path("modelId") String modelId);

    /**
     * Delete a model.
     *
     * @param modelId the ID of the model
     * @return {@link ResponseBody}
     */
    @DELETE("classificationbox/models/{modelId}")
    Call<ResponseBody> deleteModel(@Path("modelId") String modelId);

    /**
     * Download a model's state.
     *
     * @param modelId the ID of the model
     * @return {@link ResponseBody}
     */
    @Streaming
    @GET("classificationbox/state/{modelId}")
    Call<ResponseBody> downloadState(@Path("modelId") String modelId);

    /**
     * Upload a model's state.
     *
     * @param file a {@link MultipartBody.Part}
     * @return {@link UploadStateResponse}
     */
    @Multipart
    @POST("classificationbox/state")
    Call<UploadStateResponse> uploadState(@Part MultipartBody.Part file);
    // TODO: handle model state upload

}
