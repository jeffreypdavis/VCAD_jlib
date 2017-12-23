/*
 * Vector_3D_IData.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Intersection_Data_3D {
    private int num; // number of intersection points found (0,1, or 2))
    private Point_3D p1;
    private Point_3D p2;
    
    public Intersection_Data_3D()
    {
        super();
        num = 0;
        p1 = new Point_3D(0,0,0);
        p2 = new Point_3D(0,0,0);
    }
    
    public void set_num(int val)
    {
        num = val;
    }
    
    public int get_num()
    {
        return num;
    }
    
    public void set_p1(Point_3D pt)
    {
        p1 = pt;
    }
    
    public Point_3D get_p1()
    {
        return p1;
    }
    
    public void set_p2(Point_3D pt)
    {
        p2 = pt;
    }
    
    public Point_3D get_p2()
    {
        return p2;
    }
}
