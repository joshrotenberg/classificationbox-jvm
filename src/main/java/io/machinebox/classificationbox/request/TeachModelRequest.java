package io.machinebox.classificationbox.request;

import com.google.gson.annotations.SerializedName;
import io.machinebox.classificationbox.common.ModelInput;

import java.util.ArrayList;
import java.util.List;

public class TeachModelRequest {
    @SerializedName("class")
    private String clazz;
    private List<ModelInput> inputs;

    public TeachModelRequest(String clazz, List<ModelInput> inputs) {
        this.clazz = clazz;
        this.inputs = inputs;
    }

    public TeachModelRequest(String clazz) {
        this.clazz = clazz;
        this.inputs = new ArrayList<ModelInput>();
    }

    public TeachModelRequest withInputs(List<ModelInput> inputs) {
        this.inputs = inputs;
        return this;
    }

    public TeachModelRequest addInput(ModelInput input) {
        this.inputs.add(input);
        return this;
    }

    @Override
    public String toString() {
        return "TeachModelRequest{" +
                "clazz='" + clazz + '\'' +
                ", inputs=" + inputs +
                '}';
    }
}
