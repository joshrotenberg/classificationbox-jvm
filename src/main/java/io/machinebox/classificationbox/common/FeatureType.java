package io.machinebox.classificationbox.common;

import com.google.gson.annotations.SerializedName;

public enum FeatureType {
    /**
     * An integer or floating point number (e.g. likes, age, size)
     */
    @SerializedName("number") NUMBER,

    /**
     * A blob of text that will broken down into tokens (e.g. body of an article)
     */
    @SerializedName("test") TEXT,

    /**
     * A string value that will not be tokenized (e.g. cities, authors)
     */
    @SerializedName("keyword") KEYWORD,

    /**
     * A comma separated list of keywords (e.g. visit history articleA,articleB,articleC,...)
     */
    @SerializedName("list") LIST,

    /**
     * The URL to an image (which will be downloaded by Classificationbox)
     */
    @SerializedName("image_url") IMAGE_URL,

    /**
     * A string containing the base64 encoded image data
     */
    @SerializedName("image_base64") IMAGE_BASE64,


    /**
     * A blob of text in English
     */
    @SerializedName("text_en") TEXT_EN,

    /**
     * A blob of text in Spanish
     */
    @SerializedName("text_sp") TEXT_SP,

    /**
     * A blob of text in French
     */
    @SerializedName("text_fr") TEXT_FR,

    /**
     * A blob of text in Russian
     */
    @SerializedName("text_ru") TEXT_RU,

    /**
     * A blob of text in Swedish
     */
    @SerializedName("text_sv") TEXT_SV,

    /**
     * A blob of text in Chinese
     */
    @SerializedName("text_zh") TEXT_ZH,

    /**
     * A blob of text in German
     */
    @SerializedName("text_ge") TEXT_GE,

    /**
     * A blob of text in Dutch
     */
    @SerializedName("text_nl") TEXT_NL,

    /**
     * A blob of text in Portuguese
     */
    @SerializedName("text_pt") TEXT_PT;
}
