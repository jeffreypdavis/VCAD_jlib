/*
 * Facet_3D.java
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
public class Facet_3D {
    private Point_3D p1;
    private Point_3D p2;
    private Point_3D p3;

    public Facet_3D(Point_3D pt1, Point_3D pt2, Point_3D pt3) {
        this(pt1, pt2, pt3, false);
    }
    
    public Facet_3D(Point_3D pt1, Point_3D pt2, Point_3D pt3, boolean clockwise_order) {
        super();
        
        validate_points(pt1, pt2, pt3);
        
        // change point order to counter-clockwise orientation
        if (clockwise_order) {
            p1 = pt1;
            p2 = pt3;
            p3 = pt2;
        } else {
            p1 = pt1;
            p2 = pt2;
            p3 = pt3;
        }
    }
    
    public Facet_3D(Point_3D pt1, Point_3D pt2, Point_3D pt3, Vector_3D un_vector) {
        this(pt1, pt2, pt3, un_vector, false);
    }
    
    public Facet_3D(Point_3D pt1, Point_3D pt2, Point_3D pt3, Vector_3D un_vector, boolean clockwise_order) {
        super();

        validate_points(pt1, pt2, pt3);
        
        // change point order to counter-clockwise if clockwise
        if (clockwise_order) {
            p1 = pt1;
            p2 = pt3;
            p3 = pt2;
        } else {
            p1 = pt1;
            p2 = pt2;
            p3 = pt3;
        }

        if (un_vector.length() > 0)
        {
            Vector_3D unv = Vector_3D.cross_product(new Vector_3D(p1, p2), new Vector_3D(p1, p3)).normalize();
            if (Vector_3D.dot_product(unv, un_vector) < 0) // point order/unv error
                throw new IllegalArgumentException("Error: point order and unit normal vector do not align.");
        }
    }
    
    private void validate_points(Point_3D pt1, Point_3D pt2, Point_3D pt3) {
        // check if p1 is the same as p2
        if (pt1.get_x() == pt2.get_x() && pt1.get_y() == pt2.get_y() && pt1.get_z() == pt2.get_z())
            throw new IllegalArgumentException("invalid facet points: point 1 is the same as point 2");
        
        // check if p1 is the same as p3
        if (pt1.get_x() == pt3.get_x() && pt1.get_y() == pt3.get_y() && pt1.get_z() == pt3.get_z())
            throw new IllegalArgumentException("invalid facet points: point 1 is the same as point 3");
        
        // check if p2 is the same as p3
        if (pt2.get_x() == pt3.get_x() && pt2.get_y() == pt3.get_y() && pt2.get_z() == pt3.get_z())
            throw new IllegalArgumentException("invalid facet points: point 2 is the same as point 3");
        
        // check if the three points are in a straight line
        // test if the cross product is exactly zero
        Vector_3D cp = Vector_3D.cross_product(new Vector_3D(pt1, pt2), new Vector_3D(pt1, pt3));
        if (cp.get_x() == 0 && cp.get_y() == 0 && cp.get_z() == 0)
            throw new IllegalArgumentException("invalid facet points: points do not form a triangle, but a straight line");
    }
    
    // exception safety: no throw
    public Point_3D get_point1() 
    { 
        return p1; 
    }
    
    // exception safety: no throw
    public Point_3D get_point2()
    { 
        return p2; 
    }
    
    // exception safety: no throw
    public Point_3D get_point3()
    { 
        return p3; 
    }
    
    // exception safety: strong guarantee throws length_error if calculated vector has zero length
    public Vector_3D get_unv()
    {
        return Vector_3D.cross_product(new Vector_3D(p1, p2), new Vector_3D(p1, p3)).normalize();
    }
    
    // exception safety: no throw
    public void invert_unv()
    {
        // reorder points
        Point_3D temp = p2;
        p2 = p3;
        p3 = temp;
    }
    
    // exception safety: no throw
    public Point_3D get_inside_point()
    {
        Vector_3D v1 = new Vector_3D(p1,p2);
        Vector_3D v2 = new Vector_3D(p2,p3);
        return Point_3D.translate(p1, Vector_3D.plus(Vector_3D.scale(v1, 0.5), Vector_3D.scale(v2, 0.25)));
    }
    
    // determine if the point is inside or on one of the sides of the facet
    // exception safety: no throw
    public boolean contains_point(Point_3D pt, Bool pt_is_on_side, double precision)
    {
        // make sure point is on facet plane
        if (!Facet_3D.is_pt_on_facet_plane(pt, this, precision))
        {
//            cout << "contains_point: point is not on facet plane returning false\n";
            return false;
        }
        
        // if the point is on p1-p2 or p1-p3, then it is contained in the facet
        if (Vector_3D.is_pt_on_vector(pt, p1, p2, precision) || 
                Vector_3D.is_pt_on_vector(pt, p1, p3, precision) || 
                Vector_3D.is_pt_on_vector(pt, p2, p3, precision))
        {
//            cout << "contains_point: point is on a vector returning true\n";
            pt_is_on_side.set_val(true);
            return true;
        }

        Vector_3D cp_v1v2 = Vector_3D.cross_product(new Vector_3D(p1, p2), new Vector_3D(p1, p3));
        Vector_3D cp_v1vp = Vector_3D.cross_product(new Vector_3D(p1, p2), new Vector_3D(p1, pt));
        Vector_3D cp_vpv2 = Vector_3D.cross_product(new Vector_3D(p1, pt), new Vector_3D(p1, p3));

//        cout << "contains_point: cp_v1v2 x: " << cp_v1v2.get_x() << " y: " << cp_v1v2.get_y() << " z: " << cp_v1v2.get_z() << "\n";
//        cout << "contains_point: cp_v1vp x: " << cp_v1vp.get_x() << " y: " << cp_v1vp.get_y() << " z: " << cp_v1vp.get_z() << "\n";
//        cout << "contains_point: cp_vpv2 x: " << cp_vpv2.get_x() << " y: " << cp_vpv2.get_y() << " z: " << cp_vpv2.get_z() << "\n";
//        cout << "contains_point: p1 dot_product(cp_v1v2, cp_v1vp): " << dot_product(cp_v1v2, cp_v1vp) << "\n";
//        cout << "contains_point: p1 dot_product(cp_v1v2, cp_vpv2): " << dot_product(cp_v1v2, cp_vpv2) << "\n";
//        bool from_p1 = dot_product(cp_v1v2, cp_v1vp) > 0 && dot_product(cp_v1v2, cp_vpv2) > 0;
        if (Vector_3D.dot_product(cp_v1v2, cp_v1vp) > 0 && Vector_3D.dot_product(cp_v1v2, cp_vpv2) > 0)
        {
        
            cp_v1v2 = Vector_3D.cross_product(new Vector_3D(p2, p3), new Vector_3D(p2, p1));
            cp_v1vp = Vector_3D.cross_product(new Vector_3D(p2, p3), new Vector_3D(p2, pt));
            cp_vpv2 = Vector_3D.cross_product(new Vector_3D(p2, pt), new Vector_3D(p2, p1));

    //        cout << "contains_point: cp_v1v2 x: " << cp_v1v2.get_x() << " y: " << cp_v1v2.get_y() << " z: " << cp_v1v2.get_z() << "\n";
    //        cout << "contains_point: cp_v1vp x: " << cp_v1vp.get_x() << " y: " << cp_v1vp.get_y() << " z: " << cp_v1vp.get_z() << "\n";
    //        cout << "contains_point: cp_vpv2 x: " << cp_vpv2.get_x() << " y: " << cp_vpv2.get_y() << " z: " << cp_vpv2.get_z() << "\n";
    //        cout << "contains_point: p1 dot_product(cp_v1v2, cp_v1vp): " << dot_product(cp_v1v2, cp_v1vp) << "\n";
    //        cout << "contains_point: p1 dot_product(cp_v1v2, cp_vpv2): " << dot_product(cp_v1v2, cp_vpv2) << "\n";
//            bool from_p2 = dot_product(cp_v1v2, cp_v1vp) > 0 && dot_product(cp_v1v2, cp_vpv2) > 0;
            if (Vector_3D.dot_product(cp_v1v2, cp_v1vp) > 0 && Vector_3D.dot_product(cp_v1v2, cp_vpv2) > 0)
            {

                cp_v1v2 = Vector_3D.cross_product(new Vector_3D(p3, p1), new Vector_3D(p3, p2));
                cp_v1vp = Vector_3D.cross_product(new Vector_3D(p3, p1), new Vector_3D(p3, pt));
                cp_vpv2 = Vector_3D.cross_product(new Vector_3D(p3, pt), new Vector_3D(p3, p2));

        //        cout << "contains_point: cp_v1v2 x: " << cp_v1v2.get_x() << " y: " << cp_v1v2.get_y() << " z: " << cp_v1v2.get_z() << "\n";
        //        cout << "contains_point: cp_v1vp x: " << cp_v1vp.get_x() << " y: " << cp_v1vp.get_y() << " z: " << cp_v1vp.get_z() << "\n";
        //        cout << "contains_point: cp_vpv2 x: " << cp_vpv2.get_x() << " y: " << cp_vpv2.get_y() << " z: " << cp_vpv2.get_z() << "\n";
        //        cout << "contains_point: p1 dot_product(cp_v1v2, cp_v1vp): " << dot_product(cp_v1v2, cp_v1vp) << "\n";
        //        cout << "contains_point: p1 dot_product(cp_v1v2, cp_vpv2): " << dot_product(cp_v1v2, cp_vpv2) << "\n";
//                bool from_p3 = dot_product(cp_v1v2, cp_v1vp) > 0 && dot_product(cp_v1v2, cp_vpv2) > 0;
                if (Vector_3D.dot_product(cp_v1v2, cp_v1vp) > 0 && Vector_3D.dot_product(cp_v1v2, cp_vpv2) > 0)
                {

            //        cout << "contains_point: from_p1: " << from_p1 << " from_p2: " << from_p2 << " from_p3: " << from_p3 << "\n";
            //        return (from_p1 && from_p2) || (from_p1 && from_p3) || (from_p2 && from_p3);
//                    return from_p1 && from_p2 && from_p3;
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
    public static boolean is_equal(Facet_3D f1, Facet_3D f2, double precision)
    {
        return (Point_3D.is_equal(f1.get_point1(), f2.get_point1(), precision) && Point_3D.is_equal(f1.get_point2(), f2.get_point2(), precision) && Point_3D.is_equal(f1.get_point3(), f2.get_point3(), precision)) || 
                (Point_3D.is_equal(f1.get_point1(), f2.get_point2(), precision) && Point_3D.is_equal(f1.get_point2(), f2.get_point3(), precision) && Point_3D.is_equal(f1.get_point3(), f2.get_point1(), precision)) || 
                (Point_3D.is_equal(f1.get_point1(), f2.get_point3(), precision) && Point_3D.is_equal(f1.get_point2(), f2.get_point1(), precision) && Point_3D.is_equal(f1.get_point3(), f2.get_point2(), precision));
    }

    // determine if the specified point is on the facet plane
    // exception safety: no throw
    public static boolean is_pt_on_facet_plane(Point_3D point, Facet_3D facet, double precision)
    {
        if (Point_3D.is_equal(point, facet.get_point1(), precision))
            return true;
        
        // The equation of the plane P through (x0, y0, z0) that has a normal vector 
        // n=Ai + Bj + ck is:
        //    A(x - x0) + B(y - y0) + C(z - z0) = 0
        Vector_3D unv = facet.get_unv();
        Point_3D fp1 = facet.get_point1();
        double result = unv.get_x() * (point.get_x() - fp1.get_x());
        double largest = Math.abs(result);
        double next = unv.get_y() * (point.get_y() - fp1.get_y());
        result += next;
        double error_bound = Math.abs(next);
        if (error_bound > largest)
            largest = error_bound;
        next = unv.get_z() * (point.get_z() - fp1.get_z());
        result += next;
        error_bound = Math.abs(next);
        if (error_bound > largest)
            largest = error_bound;
        
        error_bound = largest > 1.0 ? (largest * precision) : precision;
//        cout << "is_pt_on_facet_plane: result: " << result << " largest: " << largest << " (precision * largest): " << (precision * largest) << " needs a precision of DBL_EPSILON * " << (fabs(result) / (largest * DBL_EPSILON)) << "\n";
        return Math.abs(result) <= error_bound;
    }
    
    // line is defined by the vector and point.  Facet plane is defined by f.
    // returns true if an intersect point was found.  Intersect point is stored
    // in the i_point argument
    // exception safety: no throw
    public static boolean intersect_line_facet_plane(Vector_3D v, Point_3D o, Facet_3D facet, Intersect_Point_3D i_point, double precision)
    {
        // facet - the facet to check if the line intersects
        // v - the vector forming the line
        // o - the vector's origin
        //
        // The parametric equations of a line l through points P=(x1,y1,z1) and Q=(x2,y2,z2)
        // are:
        //    x=x1 + (x2 - x1)t
        //    y=y1 + (y2 - y1)t
        //    z=z1 + (z2 - z1)t
        // where (x,y,z) is the general point of l, and the parameter t takes on all real values.
        //
        // line equations
        // x=p1X + (p2X - p1X)t
        // y=p1Y + (p2Y - p1Y)t
        // z=p1Z + (p2Z - p2Z)t
        //
        // plane equation
        // The equation of the plane P through (x0, y0, z0) that has a normal vector 
        // n=Ai + Bj + ck is:
        //    A(x - x0) + B(y - y0) + C(z - z0) = 0
        //
        // substitute line equations into equation
        // A(p1X + (p2X - p1X)t - x0) + B(p1Y + (p2Y - p1Y)t - y0) + C(p1Z + (p2Z - p1Z)t - z0) = 0
        // Ap1X + A(p2X - p1X)t - Ax0 + Bp1Y + B(p2Y - p1Y)t - By0 + Cp1Z + C(p2Z - p1Z)t - Cz0 = 0
        // A(p2X - p1X)t + B(p2Y - p1Y)t + C(p2Z - p1Z)t = -Ap1X + Ax0 - Bp1Y + By0 - Cp1Z + Cz0
        // t(A(p2X - p1X) + B(p2Y - p1Y) + C(p2Z - p1Z)) = Ax0  + By0 + Cz0 - Ap1X - Bp1Y - Cp1Z
        // t = (Ax0  + By0 + Cz0 - Ap1X - Bp1Y - Cp1Z) / (A(p2X - p1X) + B(p2Y - p1Y) + C(p2Z - p1Z))
        // t = (A(x0 - p1X) + B(y0 - p1Y) + C(z0 - p1Z)) / (A(p2X - p1X) + B(p2Y - p1Y) + C(p2Z - p1Z))
        //
        // solve x,y,z using line equations
        
        // A(x - x0) + B(y - y0) + C(z - z0) = 0
        // A(p1X + t*vX - x0) + B(p1Y + t*vY - y0) + C(p1Z + t*vZ - z0) = 0
        // A*p1X + t*A*vX - A*x0 + B*p1Y + t*B*vY - B*y0 + C*p1Z + t*C*vZ - C*z0 = 0
        // t*(A*vX + B*vY + C*vZ) = A*x0 - A*p1X + B*y0 - B*p1Y + C*z0 - C*p1Z
        // t = (A*x0 - A*p1X + B*y0 - B*p1Y + C*z0 - C*p1Z) / (A*vX + B*vY + C*vZ)
        //
        // possible points to use in plane equation
        // unit normal vector to use in plane equation
        Vector_3D unv = facet.get_unv();
        // the following two points are used for the line equations
        double bottom = unv.get_x() * v.get_x();
        double largest = Math.abs(bottom);
        double next = unv.get_y() * v.get_y();
        double error_bound = Math.abs(next);
        bottom += next;
        if (error_bound > largest)
            largest = error_bound;
        next = unv.get_z() * v.get_z();
        error_bound = Math.abs(next);
        bottom += next;
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (largest * precision) : precision;
        if (Math.abs(bottom) > error_bound)
        {
//            cout << "ilfp: calculating from facet p1\n";
            // t = (A*x0 - A*p1X + B*y0 - B*p1Y + C*z0 - C*p1Z) / (A*vX + B*vY + C*vZ)
            double t = unv.get_x() * (facet.get_point1().get_x() - o.get_x()) + unv.get_y() * (facet.get_point1().get_y() - o.get_y()) + unv.get_z() * (facet.get_point1().get_z() - o.get_z());
            t /= bottom;
            // check if point does lie on the plane
            Point_3D p = Point_3D.translate(o, Vector_3D.scale(v, t));

//            cout << "ilfp i_point x: " << p.get_x() << " y: " << p.get_y() << " z: " << p.get_z() << "\n";
            if (Facet_3D.is_pt_on_facet_plane(p, facet, precision))
            {
                i_point.setPoint(p);
                return true;
            }
            
//            cout << "ilfp: calculating from facet p2\n";
            // t = (A*x0 - A*p1X + B*y0 - B*p1Y + C*z0 - C*p1Z) / (A*vX + B*vY + C*vZ)
            t = unv.get_x() * (facet.get_point2().get_x() - o.get_x()) + unv.get_y() * (facet.get_point2().get_y() - o.get_y()) + unv.get_z() * (facet.get_point2().get_z() - o.get_z());
            t /= bottom;
            // check if point does lie on the plane
            p = Point_3D.translate(o, Vector_3D.scale(v, t));

//            cout << "ilfp i_point x: " << p.get_x() << " y: " << p.get_y() << " z: " << p.get_z() << "\n";
            if (Facet_3D.is_pt_on_facet_plane(p, facet, precision))
            {
                i_point.setPoint(p);
                return true;
            }
            
//            cout << "ilfp: calculating from facet p3\n";
            // t = (A*x0 - A*p1X + B*y0 - B*p1Y + C*z0 - C*p1Z) / (A*vX + B*vY + C*vZ)
            t = unv.get_x() * (facet.get_point3().get_x() - o.get_x()) + unv.get_y() * (facet.get_point3().get_y() - o.get_y()) + unv.get_z() * (facet.get_point3().get_z() - o.get_z());
            t /= bottom;
            // check if point does lie on the plane
            p = Point_3D.translate(o, Vector_3D.scale(v, t));

//            cout << "ilfp i_point x: " << p.get_x() << " y: " << p.get_y() << " z: " << p.get_z() << "\n";
            if (Facet_3D.is_pt_on_facet_plane(p, facet, precision))
            {
                i_point.setPoint(p);
                return true;
            }
        }
        else if (Facet_3D.is_pt_on_facet_plane(o, facet, precision)) // vector is parallel, so see if origin is on facet plane
        {
            i_point.setPoint(o);
            return true;
        }
        
        return false;
    }
}
