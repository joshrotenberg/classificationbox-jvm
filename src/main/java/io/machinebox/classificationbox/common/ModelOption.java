package io.machinebox.classificationbox.common;

import com.google.gson.annotations.SerializedName;

public enum ModelOption {
    /**
     * The number of word n-grams to generate from a body of text (default is 1)
     */
    @SerializedName("ngrams") NGRAMS,

    /**
     * The distance between words from which to generate n-grams (default is 0, no skipping)
     */
    @SerializedName("skipgrams") SKIPGRAMS;
}
