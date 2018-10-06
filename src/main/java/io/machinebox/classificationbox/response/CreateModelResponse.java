package io.machinebox.classificationbox.response;

import io.machinebox.classificationbox.common.ModelOption;

import java.util.List;
import java.util.Map;

public class CreateModelResponse extends BaseResponse {
    private String id;
    private String name;
    private Map<ModelOption, Integer> options;
    private List<String> classes;

    public CreateModelResponse() {
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

    @Override
    public String toString() {
        return "CreateModelResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", options=" + options +
                ", classes=" + classes +
                '}';
    }
}
