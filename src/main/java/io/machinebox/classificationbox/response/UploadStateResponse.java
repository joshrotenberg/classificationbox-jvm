package io.machinebox.classificationbox.response;

import com.google.gson.annotations.SerializedName;
import io.machinebox.classificationbox.common.ModelOption;

import java.util.List;
import java.util.Map;

public class UploadStateResponse extends BaseResponse {
    private String id;
    private String name;
    private Map<ModelOption, Integer> options;
    private List<String> classes;
    @SerializedName("predict_only")
    private Boolean predictOnly;

    public UploadStateResponse() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<ModelOption, Integer> getOptions() {
        return options;
    }

    public List<String> getClasses() {
        return classes;
    }

    public Boolean getPredictOnly() {
        return predictOnly;
    }
}
