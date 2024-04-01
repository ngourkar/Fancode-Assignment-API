package com.fancode.api.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = {"src/test/resources/features/fancode.feature"}, glue = {"com.fancode.api.steps"}, tags = "@FancodeUserTaskCompletion")
public class TestRunner {

}