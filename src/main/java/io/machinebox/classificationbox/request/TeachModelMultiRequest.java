package io.machinebox.classificationbox.request;

import com.google.gson.annotations.SerializedName;
import io.machinebox.classificationbox.common.ModelInput;

import java.util.*;

public class TeachModelMultiRequest {
    private List<Example> examples;

    public static class Example {
        @SerializedName("class")
        private String clazz;
        private List<ModelInput> inputs;

        public Example(String clazz, List<ModelInput> inputs) {
            this.clazz = clazz;
            this.inputs = inputs;
        }

        public Example(String clazz) {
            this.clazz = clazz;
            this.inputs = new ArrayList<ModelInput>();
        }

        public Example addInput(ModelInput input) {
            this.inputs.add(input);
            return this;
        }
    }

    public TeachModelMultiRequest() {
        this.examples = new ArrayList<Example>();
    }

    public TeachModelMultiRequest(List<Example> examples) {
        this.examples = examples;
    }

    public TeachModelMultiRequest addExample(Example example) {
        this.examples.add(example);
        return this;
    }
}
