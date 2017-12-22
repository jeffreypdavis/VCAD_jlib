/*
 * STLException.java
 */
package org.vcad;

import java.io.IOException;

/**
 *
 * @author Jeffrey Davis
 */
public class STLException extends IOException {
    
    public STLException(String message) {
        super(message);
    }
    
    public STLException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
