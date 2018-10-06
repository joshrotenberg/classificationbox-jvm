package io.machinebox.classificationbox.common;

import com.google.gson.annotations.SerializedName;

public enum ModelOption {
    @SerializedName("ngrams") NGRAMS,
    @SerializedName("skipgrams") SKIPGRAMS;
}
