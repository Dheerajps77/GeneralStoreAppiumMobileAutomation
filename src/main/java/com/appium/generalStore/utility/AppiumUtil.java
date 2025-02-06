package com.appium.generalStore.utility;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class AppiumUtil {

    private final AppiumDriver driver;

    public AppiumUtil(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Swipe left on the screen
     *
     * @param element The element to start the swipe
     */
    public void swipeLeft(WebElement element) {
        int startX = element.getLocation().getX() + (element.getSize().getWidth() * 3 / 4);
        int endX = element.getLocation().getX() + (element.getSize().getWidth() / 4);
        int y = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        performSwipe(startX, y, endX, y);
    }

    /**
     * Swipe right on the screen
     *
     * @param element The element to start the swipe
     */
    public void swipeRight(WebElement element) {
        int startX = element.getLocation().getX() + (element.getSize().getWidth() / 4);
        int endX = element.getLocation().getX() + (element.getSize().getWidth() * 3 / 4);
        int y = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        performSwipe(startX, y, endX, y);
    }

    /**
     * Swipe up on the screen
     */
    public void swipeUp() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        performSwipe(width / 2, height * 3 / 4, width / 2, height / 4);
    }

    /**
     * Swipe down on the screen
     */
    public void swipeDown() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        performSwipe(width / 2, height / 4, width / 2, height * 3 / 4);
    }

    /**
     * Scroll an element into view using Android's UiScrollable
     *
     * @param text The text of the element to scroll to
     */
    public void scrollElementIntoView(String text) {
    	driver.findElement(MobileBy.AndroidUIAutomator(
    		    "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
    }

    /**
     * Performs a long press on an element
     *
     * @param element The element to long press
     */
    public void longPress(WebElement element) {
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        performPointerAction(centerX, centerY, Duration.ofSeconds(2));
    }

    /**
     * Performs a generic swipe action using W3C Actions
     *
     * @param startX  Start X coordinate
     * @param startY  Start Y coordinate
     * @param endX    End X coordinate
     * @param endY    End Y coordinate
     */
    private void performSwipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        // Start action
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Move to end action
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    /**
     * Generic pointer action for long press or single tap
     *
     * @param x         X coordinate of the action
     * @param y         Y coordinate of the action
     * @param duration  Duration for which to hold the press
     */
    private void performPointerAction(int x, int y, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence action = new Sequence(finger, 0);

        // Move to target and press
        action.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        action.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Wait for the specified duration using Actions (simulate a pause)
        action.addAction(new Pause(finger, duration)); // This replaces createPause

        action.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(action));
    }
}