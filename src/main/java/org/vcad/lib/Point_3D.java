/*
 * Copyright 2017 Jeffrey Davis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * File: Point_3D.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Point_3D {
    
    private double x;
    private double y;
    private double z;
    
    public Point_3D(double x_val, double y_val, double z_val)
    {
        super();
        x = x_val;
        y = y_val;
        z = z_val;
    }
    
    public double get_x()
    {
        return x;
    }
    
    public double get_y()
    {
        return y;
    }
    
    public double get_z()
    {
        return z;
    }
    
    public double get_r() // x-y radius for cylindrical points
    {
        if (x == 0 && y == 0)
            return 0;
        else
            return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    } 
    
    public double get_r(Point_3D origin) // x-y radius for cylindrical points
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
    
    public double get_p() // x-y-z radius for spherical points
    {
        if (x == 0 && y == 0 && z == 0)
            return 0;
        else
            return Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
    }
    
    public double get_p(Point_3D origin) // x-y-z radius for spherical points
    {
        if (x == origin.get_x() && y == origin.get_y() && z == origin.get_z())
            return 0;
        else
        {
            double x_val = x - origin.get_x();
            double y_val = y - origin.get_y();
            double z_val = z - origin.get_z();
            return Math.sqrt(Math.pow(x_val,2) + Math.pow(y_val,2) + Math.pow(z_val,2));
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
    
    public double get_theta(Point_3D origin)
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
    
    public double get_phi() // for spherical points
    {
        if (x == 0 && y == 0)
            return 0;
        else
        {
            double phi = Math.acos(Vector_3D.dot_product(new Vector_3D(x,y,z), new Vector_3D(0,0,1)) / this.get_p());
            if (phi < 0)
                phi = (Math.PI / 2) - phi;
            return phi;
        }
    }
    
    public double get_phi(Point_3D origin) // for spherical points
    {
        if (x == origin.get_x() && y == origin.get_y())
            return 0;
        else
        {
            double phi = Math.acos(Vector_3D.dot_product(
                    new Vector_3D(x - origin.get_x(), y - origin.get_y(), z - origin.get_z()), new Vector_3D(0,0,1)) / this.get_p(origin));
            if (phi < 0)
                phi = (Math.PI / 2) - phi;
            return phi;
        }
    }
    
    private Point_3D rotate_about_axis(Point_3D p, double angle, Vector_3D axis, Point_3D origin)
    {
        Vector_3D v = new Vector_3D(origin, p);

        // only process if point is not on the axis
        if (Vector_3D.cross_product(v, axis).length() != 0)
        {
            // rotation using right hand rule with thumb pointing in direction of axis
            
            // orthog_v is an orthogonal projection of v along the axis ('z' axis)
            Vector_3D orthog_v = Vector_3D.orthogonal_projection(v,axis);
            if (orthog_v.length() == 0)
            {
                // v is the 'x' axis
                Vector_3D other_axis = Vector_3D.cross_product(axis,v); // 'y' axis
                // axis is 'z' axis
                
                double radius = v.length();
                
                // 'x' axis
                v.normalize();
                v.scale(radius * Math.cos(angle));
                
                // 'y' axis
                other_axis.normalize();
                other_axis.scale(radius * Math.sin(angle));
                
                return Point_3D.translate(Point_3D.translate(origin, v), other_axis);
            }
            else 
            {
                // radius_v is a perpendicular vector from the line origin + axis to point p ('x' axis)
                // other_axis is the third axis ('y' axis)
                Vector_3D radius_v = Vector_3D.minus(v, orthog_v);
                Vector_3D other_axis = Vector_3D.cross_product(axis, radius_v);

                double radius = radius_v.length();

                // 'x' vector
                radius_v.normalize();
                radius_v.scale(radius * Math.cos(angle));

                // 'y' vector
                other_axis.normalize();
                other_axis.scale(radius * Math.sin(angle));

                return Point_3D.translate(Point_3D.translate(Point_3D.translate(origin, orthog_v), other_axis), radius_v);
            }
        }
        return p;
    }
    
    public Point_3D rotate(Angle angle)
    {
        return this.rotate(angle, new Point_3D(0,0,0));
    }
    
    public Point_3D rotate(double angle, Vector_3D axis)
    {
        return this.rotate(angle, axis, new Point_3D(0,0,0));
    }
    
    public Point_3D rotate(Angle angle, Point_3D origin)
    {
        if (angle.get_x() != 0)
        {
            Point_3D p = this.rotate_about_axis(this, angle.get_x(), new Vector_3D(1,0,0), origin);
            x = p.get_x();
            y = p.get_y();
            z = p.get_z();
        }

        if (angle.get_y() != 0)
        {
            Point_3D p = this.rotate_about_axis(this, angle.get_y(), new Vector_3D(0,1,0), origin);
            x = p.get_x();
            y = p.get_y();
            z = p.get_z();
        }

        if (angle.get_z() != 0)
        {
            Point_3D p = this.rotate_about_axis(this, angle.get_z(), new Vector_3D(0,0,1), origin);
            x = p.get_x();
            y = p.get_y();
            z = p.get_z();
        }

        return this;
    }
    
    public Point_3D rotate(double angle, Vector_3D axis, Point_3D origin)
    {
        Point_3D p = this.rotate_about_axis(this, angle, axis, origin);
        x = p.get_x();
        y = p.get_y();
        z = p.get_z();
        return this;
    }
    
    public Point_3D scale(double x_scalar, double y_scalar, double z_scalar)
    {
        x *= x_scalar;
        y *= y_scalar;
        z *= z_scalar;
        return this;
    }
    
    public Point_3D scale(double x_scalar, double y_scalar, double z_scalar, Point_3D origin)
    {
        x = origin.get_x() + (x - origin.get_x()) * x_scalar;
        y = origin.get_y() + (y - origin.get_y()) * y_scalar;
        z = origin.get_z() + (z - origin.get_z()) * z_scalar;
        return this;
    }
    
    public Point_3D translate(double x_val, double y_val, double z_val)
    {
        x += x_val;
        y += y_val;
        z += z_val;
        return this;
    }
    
    public Point_3D translate(Vector_3D vector)
    {
        x += vector.get_x();
        y += vector.get_y();
        z += vector.get_z();
        return this;
    }
    
    private void move(Point_3D new_origin, Vector_3D new_x_axis, Vector_3D new_y_axis, 
            Vector_3D new_z_axis, Point_3D ref_origin) // =Point_3D(0,0,0)
    {
        Vector_3D v = new Vector_3D(ref_origin, this);

        Vector_3D x_axis = new Vector_3D(new_x_axis.get_x(), new_x_axis.get_y(), new_x_axis.get_z());
        x_axis.normalize();
        x_axis.scale(v.get_x());
        
        Vector_3D y_axis = new Vector_3D(new_y_axis.get_x(), new_y_axis.get_y(), new_y_axis.get_z());
        y_axis.normalize();
        y_axis.scale(v.get_y());
        
        Vector_3D z_axis = new Vector_3D(new_z_axis.get_x(), new_z_axis.get_y(), new_z_axis.get_z());
        z_axis.normalize();
        z_axis.scale(v.get_z());
        
        Point_3D p = Point_3D.translate(Point_3D.translate(Point_3D.translate(new_origin, x_axis), y_axis), z_axis);
        x = p.get_x();
        y = p.get_y();
        z = p.get_z();
    }
    
    /*
     * move the point to a different coordinate system and origin.
     * A new coordinate system must be supplied and this is done by 
     * specifying a new origin, a new axis, and one more point that is
     * not on the axis to define a plane.  ref_origin is an option to 
     * move based on a different origin than 0,0,0.
     * 
     * There are six methods to allow for specifying the new coordinate 
     * system. The names of the methods start with move.  Then next 
     * letter is the axis specified in the vector argument - x, y, or z 
     * axis.  The last part of the name is identifying where the third 
     * point is located - xy plane, xz plane, or yz plane.
     */
    public Point_3D move_x_pxy(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xy_plane)
    {
        return move_x_pxy(new_origin, x_axis, pt_xy_plane, new Point_3D(0,0,0));
    }
    
    public Point_3D move_x_pxy(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xy_plane, Point_3D ref_origin)
    {
        // determine y_axis by using pt_xy_plane point
        Vector_3D y_axis = new Vector_3D(new_origin, pt_xy_plane);
        y_axis.minus(Vector_3D.orthogonal_projection(y_axis, x_axis));
        
        move(new_origin, x_axis, y_axis, Vector_3D.cross_product(x_axis, y_axis), ref_origin);
        return this;
    }
    
    public Point_3D move_x_pxz(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xz_plane)
    {
        return move_x_pxz(new_origin, x_axis, pt_xz_plane, new Point_3D(0,0,0));
    }
    
    public Point_3D move_x_pxz(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xz_plane, Point_3D ref_origin)
    {
        // determine z_axis by using pt_xz_plane point
        Vector_3D z_axis = new Vector_3D(new_origin, pt_xz_plane);
        z_axis.minus(Vector_3D.orthogonal_projection(z_axis, x_axis));
        
        move(new_origin, x_axis, Vector_3D.cross_product(z_axis, x_axis), z_axis, ref_origin);
        return this;
    }
    
    public Point_3D move_y_pxy(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_xy_plane)
    {
        return move_y_pxy(new_origin, y_axis, pt_xy_plane, new Point_3D(0,0,0));
    }
    
    public Point_3D move_y_pxy(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_xy_plane, Point_3D ref_origin)
    {
        // determine x_axis by using pt_xy_plane point
        Vector_3D x_axis = new Vector_3D(new_origin, pt_xy_plane);
        x_axis.minus(Vector_3D.orthogonal_projection(x_axis, y_axis));
        
        move(new_origin, x_axis, y_axis, Vector_3D.cross_product(x_axis, y_axis), ref_origin);
        return this;
    }
    
    public Point_3D move_y_pyz(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_yz_plane)
    {
        return move_y_pyz(new_origin, y_axis, pt_yz_plane, new Point_3D(0,0,0));
    }
    
    public Point_3D move_y_pyz(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_yz_plane, Point_3D ref_origin)
    {
        // determine z_axis by using pt_yz_plane point
        Vector_3D z_axis = new Vector_3D(new_origin, pt_yz_plane);
        z_axis.minus(Vector_3D.orthogonal_projection(z_axis, y_axis));
        
        move(new_origin, Vector_3D.cross_product(y_axis, z_axis), y_axis, z_axis, ref_origin);
        return this;
    }
    
    public Point_3D move_z_pxz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_xz_plane)
    {
        return move_z_pxz(new_origin, z_axis, pt_xz_plane, new Point_3D(0,0,0));
    }
    
    public Point_3D move_z_pxz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_xz_plane, Point_3D ref_origin)
    {
        // determine x_axis by using pt_xz_plane point
        Vector_3D x_axis = new Vector_3D(new_origin, pt_xz_plane);
        x_axis.minus(Vector_3D.orthogonal_projection(x_axis, z_axis));
        
        move(new_origin, x_axis, Vector_3D.cross_product(z_axis, x_axis), z_axis, ref_origin);
        return this;
    }
    
    public Point_3D move_z_pyz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_yz_plane)
    {
        return move_z_pyz(new_origin, z_axis, pt_yz_plane, new Point_3D(0,0,0));
    }
    
    public Point_3D move_z_pyz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_yz_plane, Point_3D ref_origin)
    {
        // determine y_axis by using pt_yz_plane point
        Vector_3D y_axis = new Vector_3D(new_origin, pt_yz_plane);
        y_axis.minus(Vector_3D.orthogonal_projection(y_axis, z_axis));
        
        move(new_origin, Vector_3D.cross_product(y_axis, z_axis), y_axis, z_axis, ref_origin);
        return this;
    }
    
    public static Point_3D translate(Point_3D pt, double x_val, double y_val, double z_val)
    {
        return new Point_3D(pt.get_x() + x_val, pt.get_y() + y_val, pt.get_z() + z_val);
    }
    
    public static Point_3D translate(Point_3D pt, Vector_3D v)
    {
        return new Point_3D(pt.get_x() + v.get_x(), pt.get_y() + v.get_y(), pt.get_z() + v.get_z());
    }
    
    public static Point_3D scale(Point_3D pt, double x_scalar, double y_scalar, double z_scalar)
    {
        return new Point_3D(pt.get_x() * x_scalar, pt.get_y() * y_scalar, pt.get_z() * z_scalar);
    }
    
    public static Point_3D scale(Point_3D pt, double x_scalar, double y_scalar, double z_scalar, Point_3D origin)
    {
        return new Point_3D(origin.get_x() + (pt.get_x() - origin.get_x()) * x_scalar, 
                origin.get_y() + (pt.get_y() - origin.get_y()) * y_scalar,
                origin.get_z() + (pt.get_z() - origin.get_z()) * z_scalar);
    }
    
    public static Point_3D scale(Point_3D pt, double scalar)
    {
        return new Point_3D(pt.get_x() * scalar, pt.get_y() * scalar, pt.get_z() * scalar);
    }
    
    public static boolean is_equal(Point_3D point1, Point_3D point2, double precision)
    {
//#ifdef DEBUG_POINT_3D_IS_EQUAL
//        cout << "Point_3D is_equal begin\n";
//        cout << "Point_3D is_equal point1 x: " << point1.get_x() << " y: " << point1.get_y() << " z: " << point1.get_z() << "\n";
//        cout << "Point_3D is_equal point2 x: " << point2.get_x() << " y: " << point2.get_y() << " z: " << point2.get_z() << "\n";
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

            if (Math.abs(point1.get_y() - point2.get_y()) <= error_bound)
            {
                largest = Math.abs(point1.get_z());
                error_bound = Math.abs(point2.get_z());
                if (error_bound > largest)
                    largest = error_bound;
                
                error_bound = largest > 1.0 ? (precision * largest) : precision;
//#ifdef DEBUG_POINT_3D_IS_EQUAL
//                if (fabs(point1.get_z() - point2.get_z()) > error_bound)
//                {
//                    // determine what multiple of DBL_EPSILON is needed to succeed
//                    Vector_3D::Measurement val(fabs(point1.get_z() - point2.get_z()));
//                    Vector_3D::Measurement divisor(DBL_EPSILON);
//                    if (largest > 1.0)
//                        divisor *= largest;
//                    cout << "Point_3D is_equal z (|p1z: " << point1.get_z() << " - p2z: " << point2.get_z() << "| = " << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//                }
//#endif                
                return Math.abs(point1.get_z() - point2.get_z()) <= error_bound;
            }
//#ifdef DEBUG_POINT_3D_IS_EQUAL
//            else
//            {
//                // determine what multiple of DBL_EPSILON is needed to succeed
//                Vector_3D::Measurement val(fabs(point1.get_y() - point2.get_y()));
//                Vector_3D::Measurement divisor(DBL_EPSILON);
//                if (largest > 1.0)
//                    divisor *= largest;
//                cout << "Point_3D is_equal y (|p1y: " << point1.get_y() << " - p2y: " << point2.get_y() << "| = " << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//            }
//#endif
        }
//#ifdef DEBUG_POINT_3D_IS_EQUAL
//        else
//        {
//            // determine what multiple of DBL_EPSILON is needed to succeed
//            Vector_3D::Measurement val(fabs(point1.get_x() - point2.get_x()));
//            Vector_3D::Measurement divisor(DBL_EPSILON);
//            if (largest > 1.0)
//                divisor *= largest;
//            cout << "Point_3D is_equal x (|p1x: " << point1.get_x() << " - p2x: " << point2.get_x() << "| = " << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//        }
//#endif
        
        return false;
    }
    
    public static Point_3D cylindrical_point(double r, double theta, double z) throws IllegalArgumentException
    {
        if (r < 0)
            throw new IllegalArgumentException("r must be greater than or equal to zero. r: " + r);
        
        return new Point_3D(r * Math.cos(theta), r * Math.sin(theta), z);
    }
    
    public static Point_3D cylindrical_point(double r, double theta, double z, Point_3D origin) throws IllegalArgumentException
    {
        if (r < 0)
            throw new IllegalArgumentException("r must be greater than or equal to zero. r: " + r);

        return new Point_3D(origin.get_x() + (r * Math.cos(theta)), 
                origin.get_y() + (r * Math.sin(theta)), origin.get_z() + z);
    }
    
    public static Point_3D spherical_point(double p, double theta, double phi) throws IllegalArgumentException
    {
        if (p < 0)
            throw new IllegalArgumentException("p must be greater or equal to zero. p: " + p);

        double two_pi = Math.PI * 2;
        // 0 <= theta < 2 pi
        // 0 <= phi <= pi
        while (phi > two_pi)
            phi -= two_pi;
        while (phi < 0)
            phi += two_pi;
        if (phi > Math.PI)
            phi -= Math.PI;
        
        return new Point_3D(p * Math.sin(phi) * Math.cos(theta), p * Math.sin(phi) * Math.sin(theta), p * Math.cos(phi));
    }
    
    public static Point_3D spherical_point(double p, double theta, double phi, Point_3D origin) throws IllegalArgumentException
    {
        if (p < 0)
            throw new IllegalArgumentException("p must be greater than or equal to zero. p: " + p);

        double two_pi = Math.PI * 2;
        // 0 <= theta < 2 pi
        // 0 <= phi <= pi
        while (phi > two_pi)
            phi -= two_pi;
        while (phi < 0)
            phi += two_pi;
        if (phi > Math.PI)
            phi -= Math.PI;
        
        return new Point_3D(origin.get_x() + (p * Math.sin(phi) * Math.cos(theta)), 
                origin.get_y() + (p * Math.sin(phi) * Math.sin(theta)), 
                origin.get_z() + (p * Math.cos(phi)));
    }
}
