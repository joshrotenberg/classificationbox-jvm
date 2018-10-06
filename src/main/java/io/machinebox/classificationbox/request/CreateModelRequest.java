package io.machinebox.classificationbox.request;

import io.machinebox.classificationbox.common.ModelOption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateModelRequest {
    private String id;
    private String name;
    private Map<ModelOption, Integer> options;
    private List<String> classes;

    public CreateModelRequest(String name, List<String> classes) {
        this.name = name;
        this.classes = classes;
        this.options = new HashMap<ModelOption, Integer>();
    }

    public CreateModelRequest withId(String id) {
        this.id = id;
        return this;
    }

    public CreateModelRequest withOptions(Map<ModelOption, Integer> options) {
        this.options = options;
        return this;
    }

    public CreateModelRequest addOption(ModelOption option, Integer number) {
        this.options.put(option, number);
        return this;
    }

    @Override
    public String toString() {
        return "CreateModelRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", options=" + options +
                ", classes=" + classes +
                '}';
    }
}
