package io.machinebox.classificationbox.request;

import io.machinebox.classificationbox.common.ModelInput;

import java.util.ArrayList;
import java.util.List;

public class MakePredictionsRequest {
    private Integer limit;
    private List<ModelInput> inputs;

    public MakePredictionsRequest(Integer limit) {
        this.limit = limit;
        this.inputs = new ArrayList<ModelInput>();
    }

    public MakePredictionsRequest(List<ModelInput> inputs) {
        this.inputs = inputs;
    }

    public MakePredictionsRequest(Integer limit, List<ModelInput> inputs) {
        this.limit = limit;
        this.inputs = inputs;
    }

    public MakePredictionsRequest addInput(ModelInput input) {
        this.inputs.add(input);
        return this;
    }
}
