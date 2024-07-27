Feature: Test lock database functionality

Scenario: Lock Database and Confirm Lock Status

Given the user is on the teamspaces module's Tasks section.

When the user clicks on the "..." (meatball) button in the top right corner 

And selects "Lock Database" from the menu.

Then the page should be locked, preventing any edits

And a Locked status should be displayed on the top of the page.



