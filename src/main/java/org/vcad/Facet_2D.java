/*
 * Facet_2D.java
 */
package org.vcad;

/**
 * Facets are maintained to have counter-clockwise point order.  This way
 * the unit normal vector will follow the right hand rule:
 * 
 * Counter-clockwise point order:
 * P3 -- P2
 *  \    /
 *    P1
 * Right hand rule:
 * Align your hand with fingers out along P1-P2.
 * Close your fingers moving from P1-P2 to P1-P3.  Thumb will point in
 * direction of unit normal vector
 *
 * @author Jeffrey Davis
 */
public class Facet_2D {
    private final Point_2D p1;
    private final Point_2D p2;
    private final Point_2D p3;

    public Facet_2D(Point_2D pt1, Point_2D pt2, Point_2D pt3) {
        super();
        
        validate_points(pt1, pt2, pt3);
        
        // change point order to counter-clockwise orientation
        // swap points to make sure of counter clockwise point order
        if (Vector_2D.cross_product(new Vector_2D(pt1, pt2), new Vector_2D(pt1, pt3)) < 0) {
            p1 = pt1;
            p2 = pt3;
            p3 = pt2;
        } else {
            p1 = pt1;
            p2 = pt2;
            p3 = pt3;
        }
    }
    
    private void validate_points(Point_2D pt1, Point_2D pt2, Point_2D pt3) {
        // check if p1 is the same as p2
        if (pt1.get_x() == pt2.get_x() && pt1.get_y() == pt2.get_y())
            throw new IllegalArgumentException("invalid facet points: point 1 is the same as point 2");
        
        // check if p1 is the same as p3
        if (pt1.get_x() == pt3.get_x() && pt1.get_y() == pt3.get_y())
            throw new IllegalArgumentException("invalid facet points: point 1 is the same as point 3");
        
        // check if p2 is the same as p3
        if (pt2.get_x() == pt3.get_x() && pt2.get_y() == pt3.get_y())
            throw new IllegalArgumentException("invalid facet points: point 2 is the same as point 3");
        
        // check if the three points are in a straight line
        // test if the cross product is exactly zero
        if (Vector_2D.cross_product(new Vector_2D(pt1, pt2), new Vector_2D(pt1, pt3)) == 0)
            throw new IllegalArgumentException("invalid facet points: points do not form a triangle, but a straight line");
    }
    
    // exception safety: no throw
    public Point_2D get_point1() 
    { 
        return p1; 
    }
    
    // exception safety: no throw
    public Point_2D get_point2()
    { 
        return p2; 
    }
    
    // exception safety: no throw
    public Point_2D get_point3()
    { 
        return p3; 
    }
    
    // exception safety: no throw
    public Point_2D get_inside_point()
    {
        Vector_2D v1 = new Vector_2D(p1,p2);
        Vector_2D v2 = new Vector_2D(p2,p3);
        return Point_2D.translate(p1, Vector_2D.plus(Vector_2D.scale(v1, 0.5), Vector_2D.scale(v2, 0.25)));
    }
    
    // determine if the point is inside or on one of the sides of the facet
    // exception safety: no throw
    public boolean contains_point(Point_2D pt, Bool pt_is_on_side, double precision)
    {
        // if the point is on p1-p2 or p1-p3, then it is contained in the facet
        if (Vector_2D.is_pt_on_vector(pt, p1, p2, precision) || 
                Vector_2D.is_pt_on_vector(pt, p1, p3, precision) || 
                Vector_2D.is_pt_on_vector(pt, p2, p3, precision))
        {
//            cout << "contains_point: point is on a vector returning true\n";
            pt_is_on_side.set_val(true);
            return true;
        }

        double cp_v1v2 = Vector_2D.cross_product(new Vector_2D(p1, p2), new Vector_2D(p1, p3));
        double cp_v1vp = Vector_2D.cross_product(new Vector_2D(p1, p2), new Vector_2D(p1, pt));
        double cp_vpv2 = Vector_2D.cross_product(new Vector_2D(p1, pt), new Vector_2D(p1, p3));

//        cout << "contains_point: cp_v1v2 x: " << cp_v1v2.get_x() << " y: " << cp_v1v2.get_y() << "\n";
//        cout << "contains_point: cp_v1vp x: " << cp_v1vp.get_x() << " y: " << cp_v1vp.get_y() << "\n";
//        cout << "contains_point: cp_vpv2 x: " << cp_vpv2.get_x() << " y: " << cp_vpv2.get_y() << "\n";
//        cout << "contains_point: p1 dot_product(cp_v1v2, cp_v1vp): " << dot_product(cp_v1v2, cp_v1vp) << "\n";
//        cout << "contains_point: p1 dot_product(cp_v1v2, cp_vpv2): " << dot_product(cp_v1v2, cp_vpv2) << "\n";
        if (((cp_v1v2 > 0 && cp_v1vp > 0) || (cp_v1v2 < 0 && cp_v1vp < 0)) && ((cp_v1v2 > 0 && cp_vpv2 > 0) || (cp_v1v2 < 0 && cp_vpv2 < 0)))
        {
        
            cp_v1v2 = Vector_2D.cross_product(new Vector_2D(p2, p3), new Vector_2D(p2, p1));
            cp_v1vp = Vector_2D.cross_product(new Vector_2D(p2, p3), new Vector_2D(p2, pt));
            cp_vpv2 = Vector_2D.cross_product(new Vector_2D(p2, pt), new Vector_2D(p2, p1));

//            cout << "contains_point: cp_v1v2 x: " << cp_v1v2.get_x() << " y: " << cp_v1v2.get_y() << "\n";
//            cout << "contains_point: cp_v1vp x: " << cp_v1vp.get_x() << " y: " << cp_v1vp.get_y() << "\n";
//            cout << "contains_point: cp_vpv2 x: " << cp_vpv2.get_x() << " y: " << cp_vpv2.get_y() << "\n";
//            cout << "contains_point: p1 dot_product(cp_v1v2, cp_v1vp): " << dot_product(cp_v1v2, cp_v1vp) << "\n";
//            cout << "contains_point: p1 dot_product(cp_v1v2, cp_vpv2): " << dot_product(cp_v1v2, cp_vpv2) << "\n";
            if (((cp_v1v2 > 0 && cp_v1vp > 0) || (cp_v1v2 < 0 && cp_v1vp < 0)) && ((cp_v1v2 > 0 && cp_vpv2 > 0) || (cp_v1v2 < 0 && cp_vpv2 < 0)))
            {

                cp_v1v2 = Vector_2D.cross_product(new Vector_2D(p3, p1), new Vector_2D(p3, p2));
                cp_v1vp = Vector_2D.cross_product(new Vector_2D(p3, p1), new Vector_2D(p3, pt));
                cp_vpv2 = Vector_2D.cross_product(new Vector_2D(p3, pt), new Vector_2D(p3, p2));

//                cout << "contains_point: cp_v1v2 x: " << cp_v1v2.get_x() << " y: " << cp_v1v2.get_y() << "\n";
//                cout << "contains_point: cp_v1vp x: " << cp_v1vp.get_x() << " y: " << cp_v1vp.get_y() << "\n";
//                cout << "contains_point: cp_vpv2 x: " << cp_vpv2.get_x() << " y: " << cp_vpv2.get_y() << "\n";
//                cout << "contains_point: p1 dot_product(cp_v1v2, cp_v1vp): " << dot_product(cp_v1v2, cp_v1vp) << "\n";
//                cout << "contains_point: p1 dot_product(cp_v1v2, cp_vpv2): " << dot_product(cp_v1v2, cp_vpv2) << "\n";
                if (((cp_v1v2 > 0 && cp_v1vp > 0) || (cp_v1v2 < 0 && cp_v1vp < 0)) && ((cp_v1v2 > 0 && cp_vpv2 > 0) || (cp_v1v2 < 0 && cp_vpv2 < 0)))
                {
                    pt_is_on_side.set_val(false);
                    return true;
                }
            }
        }
        
        return false;
    }

    /* 
     * determines if two facets are within a rounding error of each other
     */
    public static boolean is_equal(Facet_2D f1, Facet_2D f2, double precision)
    {
        return (Point_2D.is_equal(f1.get_point1(), f2.get_point1(), precision) && Point_2D.is_equal(f1.get_point2(), f2.get_point2(), precision) && Point_2D.is_equal(f1.get_point3(), f2.get_point3(), precision)) || 
                (Point_2D.is_equal(f1.get_point1(), f2.get_point2(), precision) && Point_2D.is_equal(f1.get_point2(), f2.get_point3(), precision) && Point_2D.is_equal(f1.get_point3(), f2.get_point1(), precision)) || 
                (Point_2D.is_equal(f1.get_point1(), f2.get_point3(), precision) && Point_2D.is_equal(f1.get_point2(), f2.get_point1(), precision) && Point_2D.is_equal(f1.get_point3(), f2.get_point2(), precision));
    }
}
