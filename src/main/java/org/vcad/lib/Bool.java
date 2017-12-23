/*
 * Bool.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Bool {
    private boolean val;
    
    public Bool()
    {
        super();
        val = false;
    }
    
    public Bool(boolean value)
    {
        super();
        val = value;
    }

    public void set_val(boolean value)
    {
        val = value;
    }
    
    public boolean get_val()
    {
        return val;
    }
}
