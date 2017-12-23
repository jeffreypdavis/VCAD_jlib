/*
 * Intersect_Point_3D.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Intersect_Point_3D {
    private Point_3D pt;
    
    public Intersect_Point_3D() {
        super();
        pt = null;
    }
    
    public void setPoint(Point_3D point) {
        pt = point;
    }
    
    public Point_3D getPoint() {
        return pt;
    }
}
