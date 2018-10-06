package io.machinebox.classificationbox.response;

import io.machinebox.classificationbox.common.ModelOption;

import java.util.List;
import java.util.Map;

public class GetModelResponse {
    private String id;
    private String name;
    private Map<ModelOption, Integer> options;
    private List<String> classes;
    private Boolean predictOnly;

    public GetModelResponse() {
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
