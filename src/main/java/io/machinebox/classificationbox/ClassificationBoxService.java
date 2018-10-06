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

public interface ClassificationBoxService {

    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models")
    Call<CreateModelResponse> createModel(@Body CreateModelRequest request);

    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models/{modelId}/teach")
    Call<TeachModelResponse> teachModel(@Path("modelId") String modelId, @Body TeachModelRequest request);

    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models/{modelId}/teach-multi")
    Call<TeachModelMultiResponse> teachModelMulti(@Path("modelId") String modelId, @Body TeachModelMultiRequest request);

    @Headers({"Accept: application/json; charset=utf-8",  "Content-type: application/json; charset=utf-8"})
    @POST("classificationbox/models/{modelId}/predict")
    Call<MakePredictionsResponse> makePredictions(@Path("modelId") String modelId, @Body MakePredictionsRequest request);

    @Headers({"Accept: application/json; charset=utf-8"})
    @GET("classificationbox/models/{modelId}/stats")
    Call<ModelStatisticsResponse> modelStatistics(@Path("modelId") String modelId);

    @Headers({"Accept: application/json; charset=utf-8"})
    @GET("classificationbox/models")
    Call<ListModelsResponse> listModels();

    @Headers({"Accept: application/json; charset=utf-8"})
    @GET("classificationbox/models/{modelId}")
    Call<GetModelResponse> getModel(@Path("modelId") String modelId);

    @DELETE("classificationbox/models/{modelId}")
    Call<Void> deleteModel(@Path("modelId") String modelId);

    @Streaming
    @GET("classificationbox/state/{modelId}")
    Call<ResponseBody> downloadState(@Path("modelId") String modelId);

    @Multipart
    @POST("classificationbox/state")
    Call<UploadStateResponse> uploadState(@Part MultipartBody.Part file);
    // TODO: handle model state upload

}
