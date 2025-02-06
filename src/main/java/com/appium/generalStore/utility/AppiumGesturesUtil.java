package com.appium.generalStore.utility;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;

public class AppiumGesturesUtil {
    private final AppiumDriver driver; // Use WebElement type

    public AppiumGesturesUtil(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Perform a swipe in the specified direction.
     *
     * @param element   The element to swipe on.
     * @param direction The direction to swipe (e.g., "left", "right", "up", "down").
     * @param percentage The percentage of the element's size to swipe (0-100).
     */
    public void swipe(WebElement element, String direction, int percentage) { // Updated to WebElement
        driver.executeScript("gesture: swipe", Map.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percentage", percentage
        ));
    }

    /**
     * Scroll to make an element visible.
     *
     * @param element The element to scroll to.
     */
    public void scrollToElement(WebElement element) { // Updated to WebElement
        driver.executeScript("gesture: scroll", Map.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "down",
                "percent", 100
        ));
    }

    /**
     * Double tap on an element.
     *
     * @param element The element to double-tap on.
     */
    public void doubleTap(WebElement element) { // Updated to WebElement
        driver.executeScript("gesture: doubleTap", Map.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    /**
     * Perform a long press on an element.
     *
     * @param element The element to long-press on.
     * @param duration The duration of the press in milliseconds.
     */
    public void longPress(WebElement element, int duration) { // Updated to WebElement
        driver.executeScript("gesture: longPress", Map.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", duration
        ));
    }

    /**
     * Zoom in on an element.
     *
     * @param element The element to zoom in on.
     */
    public void zoom(WebElement element) { // Updated to WebElement
        driver.executeScript("gesture: zoom", Map.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    /**
     * Perform a multi-finger swipe.
     *
     * @param element   The element to perform the multi-finger swipe on.
     * @param direction The direction to swipe (e.g., "left", "right", "up", "down").
     * @param fingers   The number of fingers to use.
     */
    public void multiFingerSwipe(WebElement element, String direction, int fingers) { // Updated to WebElement
        driver.executeScript("gesture: multiFingerSwipe", Map.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "fingers", fingers
        ));
    }

    /**
     * Drag and drop an element to another location.
     *
     * @param source The source element to drag.
     * @param target The target element to drop.
     */
    public void dragAndDrop(WebElement source, WebElement target) { // Updated to WebElement
        driver.executeScript("gesture: dragAndDrop", Map.of(
                "sourceId", ((RemoteWebElement) source).getId(),
                "targetId", ((RemoteWebElement) target).getId()
        ));
    }
}