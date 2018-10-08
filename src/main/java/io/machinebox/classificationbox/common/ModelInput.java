package io.machinebox.classificationbox.common;


public class ModelInput {
    private String key;
    private FeatureType type;
    private String value;

    public ModelInput(String key, FeatureType type, String value) {
        this.key = key;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ModelInput{" +
                "key='" + key + '\'' +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
