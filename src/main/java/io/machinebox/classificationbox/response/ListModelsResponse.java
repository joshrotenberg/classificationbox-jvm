package io.machinebox.classificationbox.response;

import io.machinebox.classificationbox.common.ModelOption;

import java.util.List;

public class ListModelsResponse extends BaseResponse {
    private List<Model> models;

    public static class Model {
        private String id;
        private String name;
        private List<ModelOption> options;

        public Model() {
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<ModelOption> getOptions() {
            return options;
        }
    }

    public ListModelsResponse() {
    }

    public List<Model> getModels() {
        return models;
    }

}
