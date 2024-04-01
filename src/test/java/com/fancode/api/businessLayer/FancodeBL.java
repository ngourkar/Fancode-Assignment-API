package com.fancode.api.businessLayer;

import com.fancode.api.services.FancodeAPI;

import io.restassured.response.ValidatableResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class FancodeBL {

    private static ValidatableResponse validatableResponse;
    private String todoEndpoint = "http://jsonplaceholder.typicode.com/todos";
    private String userEndpoint = "https://jsonplaceholder.typicode.com/users";
    private static final int successStatusCode = 200;
    private static HashMap<String, Integer> hmapUserTotalCount = new HashMap<>();
    private static HashMap<String, Integer> hmapUserTaskCount = new HashMap<>();
    private static ArrayList<String> listOfUserInFancode = new ArrayList<>();

    public FancodeBL verifyUserHasTodoTask() {
        validatableResponse = FancodeAPI.getAPI(todoEndpoint);
        validatableResponse.assertThat().statusCode(successStatusCode);
        return this;
    }

    public FancodeBL getTodoTaskCompletedCountForEachUser() {
        System.out.println("Get the completed status count for each user");
        JSONArray jsonArr = new JSONArray(validatableResponse.extract().asString());

        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String userId = jsonObj.get("userId").toString();

            //Map to have total count of user
            if (!hmapUserTotalCount.containsKey(userId)) {
                hmapUserTotalCount.put(userId, 1);
            } else {
                hmapUserTotalCount.put(userId, hmapUserTotalCount.get(userId) + 1);
            }

            //Map to have count of user with task status as completed
            if ((boolean) jsonObj.get("completed")) {
                if (!hmapUserTaskCount.containsKey(userId)) {
                    hmapUserTaskCount.put(userId, 1);
                } else {
                    hmapUserTaskCount.put(userId, hmapUserTaskCount.get(userId) + 1);
                }
            }
        }

        System.out.println("HashMap with user with total task count" + hmapUserTotalCount);
        System.out.println("HashMap with user and task as completed" + hmapUserTaskCount);
        return this;
    }

    public FancodeBL verifyUserBelongsToCityFanCode() {
        System.out.println("Validating user which belongs to Fancode city");
        validatableResponse = FancodeAPI.getAPI(userEndpoint);
        validatableResponse.assertThat().statusCode(successStatusCode);

        JSONArray jsonArr = new JSONArray(validatableResponse.extract().asString());
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String userId = jsonObj.get("id").toString();

            JSONObject jsonGeoLocationObject = jsonObj
                    .getJSONObject("address")
                    .getJSONObject("geo");

            double latitude = Double.parseDouble(jsonGeoLocationObject.getString("lat"));
            double longitude = Double.parseDouble(jsonGeoLocationObject.getString("lng"));

            if (latitude >= -40 && latitude <= 5 && longitude >= 5 && longitude <= 100) {
                listOfUserInFancode.add(userId);
            }
        }

        System.out.println("List of users residing in fancode city" + listOfUserInFancode);

        assertThat(listOfUserInFancode.isEmpty())
                .as("There is no user leaving in fancode city")
                .isFalse();

        return this;
    }

    public FancodeBL verifyTaskCompletionPercentageForFancodeCityUsers(int taskCompletionPercent) {
        System.out.println("Verify for the users of fancode city, task completion is above percent: " + taskCompletionPercent);

        for (String userInFancode : listOfUserInFancode) {
            assertThat(((hmapUserTaskCount.get(userInFancode) * 100) / hmapUserTotalCount.get(userInFancode)) >= taskCompletionPercent)
                    .as("Users of Fancode city does not have task completion percent greater than " + taskCompletionPercent )
                    .isTrue();
        }
        return this;
    }
}
