FANCODE - API ASSIGNMENT
The Repository is intended to have the fancode scenario implemented with RestAssured for API Automation along with the support of Cucumber for BDD style of development.

We have support of cucumber for BDD style of automation.
Project structure:
1. feature file -> Gherkin language
2. Steps file -> Convert the gherkin language to java and directs the BL layer
3. FancodeBL file -> Business layer where the logic and orchestration is done.
   Responsible for having assertions at this layer.
   Responsible for having business logic and keeping communicating with API layer
4. FancodeAPI file -> Having the implementations for the API, clean and seperate layer for code reuse.

Use of Gradle for dependency management.
Use of cucumber.properties for publishing the cucumber reports
Use of assertJ for required assertions

To run the project:
1. Clone the repo
2. In terminal execute the command
   **_./gradlew cucumber_**
OR
   ****CLICK ON THE RUN OPTION FROM IDE OR FROM THE FEATURE FILE****

![](/Users/nayan.gourkar/Desktop/Screenshot 2024-04-01 at 2.35.57 PM.png)

Report link : https://reports.cucumber.io/reports/5792283f-2277-4833-9a59-6642d9bd5b12