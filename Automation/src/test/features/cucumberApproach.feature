@Functionaltests
Feature: Flock Apps Testing

Background: To be run each time
Given methods are all correct

@Login
Scenario Outline: Login to flock
Given Enter username "<user name>"
And Enter password "<password>"
When Click on Login button
Then Flock home page opens
Examples:
|user name| password|
|test1| pass1|

@Apptest
Scenario: Work on Apps
Given All apps are listed in the sidebar

