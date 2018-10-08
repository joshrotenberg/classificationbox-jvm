package io.machinebox.classificationbox.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TeachModelIntegrationTest.class,
        MakePredictionsIntegrationTest.class,
        ModelStatisticsIntegrationTest.class,
        ListModelsIntegrationTest.class,
        GetModelIntegrationTest.class,
        StateIntegrationTest.class
})

public class IntegrationTestSuite {
}
