package io.machinebox.classificationbox.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.machinebox.classificationbox.common.FeatureType;
import io.machinebox.classificationbox.common.ModelInput;
import org.junit.Before;
import org.junit.Test;

public class TeachModelMultiRequestTest {

    private Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder().setPrettyPrinting().create();

    }

    @Test
    public void testTeachModelMultiRequest() {
        TeachModelMultiRequest tmmr = new TeachModelMultiRequest();

        TeachModelMultiRequest.Example e1 = new TeachModelMultiRequest.Example("class1");
        ModelInput mi1 = new ModelInput("foo", FeatureType.IMAGE_BASE64, "stuff");
        ModelInput mi2 = new ModelInput("bar", FeatureType.IMAGE_BASE64, "other stuff");
        e1.addInput(mi1);
        e1.addInput(mi2);
        tmmr.addExample(e1);

        TeachModelMultiRequest.Example e2 = new TeachModelMultiRequest.Example("class2");
        ModelInput mi3 = new ModelInput("baz", FeatureType.IMAGE_BASE64, "stuff");
        e2.addInput(mi3);
        tmmr.addExample(e2);

        System.out.println(gson.toJson(tmmr));

    }
}
