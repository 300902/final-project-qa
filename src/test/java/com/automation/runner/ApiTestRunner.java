package com.automation.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/api")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, 
                       value = "pretty," +
                               "html:build/reports/cucumber/api/cucumber-api-report.html," +
                               "json:build/reports/cucumber/api/cucumber-api-report.json," +
                               "junit:build/reports/cucumber/api/cucumber-api-report.xml")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.automation.steps.api")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@api")
public class ApiTestRunner {
    // This class will run only API tests
}
