(1) I pledge the highest level of ethical principles in support of academic excellence.  
	I ensure that all of my work reflects my own abilities and not those of someone else.

(2) Saying we want to add a cool feature - button "x" to run multiplication.
	What code do we need to change/add/remove to support this feature?
	Which tests can we run on the calculator? On the activity? On the app?
	
	First, we'd need to add the function to the interface SimpleCalculator
	and the actual multiplication logic to our SimpleCalculatorImpl.
	Then, we'll add the view of a multiplication button (text "x" like in the plus/minus/equals signs) on the calculator to the 
	activity_main xml.
	Finally, set on click listener on MainActivity that binds the logic to the display.
	Regarding tests - first we'll test the connection between the logic and the display at MainActivityTest with Mockito:
	verify that indeed after clicking button "x" the multiplication function of SimpleCalculatorImpl performs.
	Then we can add a few tests to AppFlowTests checking the logic like "4x5=" -> "20", etc...