package io.machinebox.classificationbox.common;

import com.google.gson.annotations.SerializedName;

public enum FeatureType {
    @SerializedName("number") NUMBER,
    @SerializedName("test") TEXT,
    @SerializedName("keyword") KEYWORD,
    @SerializedName("list") LIST,
    @SerializedName("image_url") IMAGE_URL,
    @SerializedName("image_base64") IMAGE_BASE64,

    @SerializedName("text_en") TEXT_EN,
    @SerializedName("text_sp") TEXT_SP,
    @SerializedName("text_fr") TEXT_FR,
    @SerializedName("text_ru") TEXT_RU,
    @SerializedName("text_sv") TEXT_SV,
    @SerializedName("text_zh") TEXT_ZH,
    @SerializedName("text_ge") TEXT_GE,
    @SerializedName("text_nl") TEXT_NL,
    @SerializedName("text_pt") TEXT_PT;
}
