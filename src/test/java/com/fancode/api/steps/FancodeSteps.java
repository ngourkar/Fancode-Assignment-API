package com.fancode.api.steps;

import com.fancode.api.businessLayer.FancodeBL;
import io.cucumber.java.en.And;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
public class FancodeSteps {

    @Given("User has the todo tasks")
    public void userHasTheTodoTasks() {
        new FancodeBL().verifyUserHasTodoTask().getTodoTaskCompletedCountForEachUser();
    }

    @And("User belongs to the city FanCode")
    public void userBelongsToTheCityFanCode() {
        new FancodeBL().verifyUserBelongsToCityFanCode();
    }

    @Then("User Completed task percentage should be greater than {int}%")
    public void userCompletedTaskPercentageShouldBeGreaterThan(int taskCompletionPercentage) {
        new FancodeBL().verifyTaskCompletionPercentageForFancodeCityUsers(taskCompletionPercentage);
    }
}
