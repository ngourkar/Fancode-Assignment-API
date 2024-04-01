package com.fancode.api.steps;

import com.fancode.api.businessLayer.FancodeBL;
import io.cucumber.java.en.And;
import io.restassured.http.ContentType;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
public class FancodeSteps {

    private ValidatableResponse validatableResponse;

    private String endpoint = "http://dummy.restapiexample.com/api/v1/employee/1";

    @Given("I send a request to the URL to get user details")
    public void sendRequest(){
        validatableResponse = given().contentType(ContentType.JSON)
                .when().get(endpoint).then();

        System.out.println("Response :"+validatableResponse.extract().asPrettyString());
    }

    @Then("the response will return status {int} and id {int} and salary {int} and name {string} and age {int} and message {string}")
    public void verifyStatus(int statusCode, int id, int emp_Salary, String emp_name, int emp_age, String message ){

        validatableResponse.assertThat().statusCode(statusCode);

        validatableResponse.assertThat().body("data.id",equalTo(id));

        validatableResponse.assertThat().body("data.employee_salary",equalTo(emp_Salary));

        validatableResponse.assertThat().body("data.employee_name",equalTo(emp_name));

        validatableResponse.assertThat().body("data.employee_age",equalTo(emp_age));

        validatableResponse.assertThat().body("message",equalTo(message));

    }

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
