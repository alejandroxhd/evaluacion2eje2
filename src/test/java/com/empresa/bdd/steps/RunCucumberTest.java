package com.empresa.bdd.steps;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.empresa.bdd.steps")
// Genera SIEMPRE estos outputs (HTML + JSON) en target/
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "pretty, json:target/cucumber.json, html:target/cucumber-report.html"
)
public class RunCucumberTest { }
