/*
 * Vector_2D_IData.java
 */
package org.vcad;

/**
 *
 * @author Jeffrey Davis
 */
public class Intersection_Data_2D {
    private int num; // number of intersection points found (0,1, or 2))
    private Point_2D p1;
    private Point_2D p2;
    
    public Intersection_Data_2D()
    {
        super();
        num = 0;
        p1 = new Point_2D(0,0);
        p2 = new Point_2D(0,0);
    }
    
    public void set_num(int val)
    {
        num = val;
    }
    
    public int get_num()
    {
        return num;
    }
    
    public void set_p1(Point_2D pt)
    {
        p1 = pt;
    }
    
    public Point_2D get_p1()
    {
        return p1;
    }
    
    public void set_p2(Point_2D pt)
    {
        p2 = pt;
    }
    
    public Point_2D get_p2()
    {
        return p2;
    }
}
