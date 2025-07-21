package com.automation.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/web")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, 
                       value = "pretty," +
                               "html:build/reports/cucumber/web/cucumber-web-report.html," +
                               "json:build/reports/cucumber/web/cucumber-web-report.json," +
                               "junit:build/reports/cucumber/web/cucumber-web-report.xml")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.automation.steps.web")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@web")
public class WebTestRunner {
    // This class will run only Web UI tests
}
