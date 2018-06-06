package org.jtlabs.demo.unittesting.search;

/**
 * When element was not found during search.
 *
 * @author jssingla
 */
public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(final String message) {
        super(message);
    }

}
