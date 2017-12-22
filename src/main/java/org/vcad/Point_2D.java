/*
 * Point_2D.java
 */
package org.vcad;

/**
 *
 * @author Jeffrey Davis
 */
public class Point_2D {
    
    private double x;
    private double y;
    
    public Point_2D(double x_val, double y_val)
    {
        super();
        x = x_val;
        y = y_val;
    }
    
    public double get_x()
    {
        return x;
    }
    
    public double get_y()
    {
        return y;
    }
    
    public double get_r() // x-y radius for cylindrical points
    {
        if (x == 0 && y == 0)
            return 0;
        else
            return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    } 
    
    public double get_r(Point_2D origin) // x-y radius for cylindrical points
    {
        if (x == origin.get_x() && y == origin.get_y())
            return 0;
        else
        {
            double x_val = x - origin.get_x();
            double y_val = y - origin.get_y();
            return Math.sqrt(Math.pow(x_val,2) + Math.pow(y_val,2));
        }
    }
    
    public double get_theta()
    {
        if (x == 0 && y == 0)
            return 0;
        else
        {
            double theta = Math.acos(x / this.get_r());
            if (y < 0) // third or fourth quadrant
                theta = (Math.PI * 2) - theta;
            return theta;
        }
    }
    
    public double get_theta(Point_2D origin)
    {
        if (x == origin.get_x() && y == origin.get_y())
            return 0;
        else
        {
            double theta = Math.acos((x - origin.get_x()) / this.get_r(origin));
            if (y - origin.get_y() < 0) // third or fourth quadrant
                theta = (Math.PI * 2) - theta;
            return theta;
        }
    }
    
    public Point_2D rotate(double angle)
    {
        if (!(x == 0 && y == 0))
        {
            double theta = this.get_theta() + angle;
            double r = this.get_r();
            x = r * Math.cos(theta);
            y = r * Math.sin(theta);
        }
        
        return this;
    }
    
    public Point_2D rotate(double angle, Point_2D origin)
    {
        if (!(x == origin.get_x() && y == origin.get_y()))
        {
            double theta = this.get_theta(origin) + angle;
            double r = this.get_r(origin);
            x = r * Math.cos(theta) + origin.get_x();
            y = r * Math.sin(theta) + origin.get_y();
        }
        return this;
    }
    
    public Point_2D scale(double x_scalar, double y_scalar)
    {
        x *= x_scalar;
        y *= y_scalar;
        return this;
    }
    
    public Point_2D scale(double x_scalar, double y_scalar, Point_2D origin)
    {
        x = origin.get_x() + (x - origin.get_x()) * x_scalar;
        y = origin.get_y() + (y - origin.get_y()) * y_scalar;
        return this;
    }
    
    public Point_2D translate(double x_val, double y_val)
    {
        x += x_val;
        y += y_val;
        return this;
    }
    
    public Point_2D translate(Vector_2D vector)
    {
        x += vector.get_x();
        y += vector.get_y();
        return this;
    }
    
    public Point_2D move(Point_2D new_origin, Vector_2D axis, boolean is_x_axis) {
        return this.move(new_origin, axis, is_x_axis, new Point_2D(0,0));
    }
    
    public Point_2D move(Point_2D new_origin, Vector_2D axis, boolean is_x_axis, 
            Point_2D ref_origin) // =Point_2D(0,0)
    {
        Vector_2D v = new Vector_2D(ref_origin, this);

        if (is_x_axis) {
            // determine y-axis
            Point_2D y_axis_pt = new Point_2D(axis.get_x(), axis.get_y());
            y_axis_pt.rotate((Math.PI * 2) / 4);
            
            Vector_2D y_axis = new Vector_2D(y_axis_pt);
            
            Vector_2D x_axis = new Vector_2D(axis.get_x(), axis.get_y());
            x_axis.normalize();
            x_axis.scale(v.get_x());

            y_axis.normalize();
            y_axis.scale(v.get_y());

            Point_2D p = Point_2D.translate(Point_2D.translate(new_origin, x_axis), y_axis);
            x = p.get_x();
            y = p.get_y();
            
        } else {
            
            // determine x-axis
            Point_2D x_axis_pt = new Point_2D(axis.get_x(), axis.get_y());
            x_axis_pt.rotate(-(Math.PI * 2) / 4);
            
            Vector_2D x_axis = new Vector_2D(x_axis_pt);

            x_axis.normalize();
            x_axis.scale(v.get_x());
            
            Vector_2D y_axis = new Vector_2D(axis.get_x(), axis.get_y());
            y_axis.normalize();
            y_axis.scale(v.get_y());

            Point_2D p = Point_2D.translate(Point_2D.translate(new_origin, x_axis), y_axis);
            x = p.get_x();
            y = p.get_y();
        }
        
        return this;
    }
    
    public static Point_2D translate(Point_2D pt, double x_val, double y_val)
    {
        return new Point_2D(pt.get_x() + x_val, pt.get_y() + y_val);
    }
    
    public static Point_2D translate(Point_2D pt, Vector_2D v)
    {
        return new Point_2D(pt.get_x() + v.get_x(), pt.get_y() + v.get_y());
    }
    
    public static Point_2D scale(Point_2D pt, double x_scalar, double y_scalar)
    {
        return new Point_2D(pt.get_x() * x_scalar, pt.get_y() * y_scalar);
    }
    
    public static Point_2D scale(Point_2D pt, double x_scalar, double y_scalar, Point_2D origin)
    {
        return new Point_2D(origin.get_x() + (pt.get_x() - origin.get_x()) * x_scalar, 
                origin.get_y() + (pt.get_y() - origin.get_y()) * y_scalar);
    }
    
    public static Point_2D scale(Point_2D pt, double scalar)
    {
        return new Point_2D(pt.get_x() * scalar, pt.get_y() * scalar);
    }
    
    public static boolean is_equal(Point_2D point1, Point_2D point2, double precision)
    {
//#ifdef DEBUG_POINT_2D_IS_EQUAL
//        cout << "Point_2D is_equal begin\n";
//        cout << "Point_2D is_equal point1 x: " << point1.get_x() << " y: " << point1.get_y() << "\n";
//        cout << "Point_2D is_equal point2 x: " << point2.get_x() << " y: " << point2.get_y() << "\n";
//#endif
        double largest = Math.abs(point1.get_x());
        double error_bound = Math.abs(point2.get_x());
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (precision * largest) : precision;

        if (Math.abs(point1.get_x() - point2.get_x()) <= error_bound)
        {
            largest = Math.abs(point1.get_y());
            error_bound = Math.abs(point2.get_y());
            if (error_bound > largest)
                largest = error_bound;

            error_bound = largest > 1.0 ? (precision * largest) : precision;

//#ifdef DEBUG_POINT_2D_IS_EQUAL
//            if (fabs(point1.get_y() - point2.get_y()) > error_bound)
//            {
//                // determine what multiple of DBL_EPSILON is needed to succeed
//                Vector_2D::Measurement val(fabs(point1.get_y() - point2.get_y()));
//                Vector_2D::Measurement divisor(DBL_EPSILON);
//                if (largest > 1.0)
//                    divisor *= largest;
//                cout << "Point_2D is_equal y (|p1y: " << point1.get_y() << " - p2y: " << point2.get_y() << "| = " << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//            }
//#endif                
            return Math.abs(point1.get_y() - point2.get_y()) <= error_bound;
        }
//#ifdef DEBUG_POINT_2D_IS_EQUAL
//        else
//        {
//            // determine what multiple of DBL_EPSILON is needed to succeed
//            Vector_2D::Measurement val(fabs(point1.get_x() - point2.get_x()));
//            Vector_2D::Measurement divisor(DBL_EPSILON);
//            if (largest > 1.0)
//                divisor *= largest;
//            cout << "Point_2D is_equal x (|p1x: " << point1.get_x() << " - p2x: " << point2.get_x() << "| = " << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//        }
//#endif
        
        return false;
    }
    
    public static Point_2D polar_point(double r, double theta)
    {
        if (r < 0)
            throw new IllegalArgumentException("r must be greater than or equal to zero. r: " + r);
        return new Point_2D(r * Math.cos(theta), r * Math.sin(theta));
    }
    
    public static Point_2D polar_point(double r, double theta, Point_2D origin)
    {
        if (r < 0)
            throw new IllegalArgumentException("r must be greater than or equal to zero. r: " + r);
        return new Point_2D(r * Math.cos(theta) + origin.get_x(), r * Math.sin(theta) + origin.get_y());
    }
}
