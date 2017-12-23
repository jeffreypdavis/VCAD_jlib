/*
 * Shapes.java
 */
package org.vcad.lib;

import java.util.ArrayDeque;

/**
 *
 * @author Jeffrey Davis
 */
public class Shapes {

    public static void rectangle(Mesh_2D mesh, double x_length, double y_length) {
        rectangle(mesh, x_length, y_length, false, new Point_2D(0,0));
    }
    
    public static void rectangle(Mesh_2D mesh, double x_length, double y_length, 
            boolean center) {
        rectangle(mesh, x_length, y_length, center, new Point_2D(0,0));
    }
    
    public static void rectangle(Mesh_2D mesh, double x_length, double y_length, 
            boolean center, Point_2D origin) {
        
        double orig_x = center ? origin.get_x() - (x_length / 2) : origin.get_x();
        double orig_y = center ? origin.get_y() - (y_length / 2) : origin.get_y();

        Point_2D p1 = new Point_2D(orig_x, orig_y);
        Point_2D p2 = new Point_2D(orig_x, orig_y + y_length);
        Point_2D p3 = new Point_2D(orig_x + x_length, orig_y);
        Point_2D p4 = new Point_2D(orig_x + x_length, orig_y + y_length);
        
        mesh.add(new Facet_2D(p1, p4, p2));
        mesh.add(new Facet_2D(p1, p3, p4));
    }

    public static void circle(Mesh_2D mesh, double radius, int steps_per_quarter) {
        circle(mesh, radius, steps_per_quarter, new Point_2D(0,0));
    }
    
    public static void circle(Mesh_2D mesh, double radius, int steps_per_quarter, 
            Point_2D origin) {
        
        double two_pi = Math.PI * 2; //6.283185307179586;
        double step = two_pi / (4 * steps_per_quarter);
        // generate 2 dimensional circle
        double angle = step;
        Point_2D begin = new Point_2D(origin.get_x() + radius, origin.get_y());
        Point_2D middle = Point_2D.polar_point(radius, angle, origin);
        angle += step;
        while (angle < two_pi) {
            Point_2D last = Point_2D.polar_point(radius, angle, origin);
            
            mesh.add(new Facet_2D(begin, middle, last));
            middle = last;
            angle += step;
        }
    }
    
    public static void ellipse(Mesh_2D mesh, double x_radius, double y_radius, 
            int steps_per_quarter) {
        ellipse(mesh, x_radius, y_radius, steps_per_quarter, new Point_2D(0,0));
    }
    
    public static void ellipse(Mesh_2D mesh, double x_radius, double y_radius, 
            int steps_per_quarter, Point_2D origin) {
        // Ax^2 + By^2 = C
        // first equation = x_radius = sqrt(C / A)
        // second equation = y_radius = sqrt(C / B)
        // choose C to be A*B
        // x_radius = sqrt(B)
        // y_radius = sqrt(A)
        // A = y_radius^2
        // B = x_radius^2
        // C = A*B
        double y_mult = Math.pow(x_radius,2);
        double x_mult = Math.pow(y_radius,2);
        double result = x_mult * y_mult;
        // Ax^2 + By^2 = C
        // y^2 = ( C - Ax^2 ) / B
        // y = +/- sqrt((C - Ax^2) / B)
        
        // find x range, set y = 0
        // Ax^2 = C
        // x = +/- sqrt(C / A)
        double x_max = Math.sqrt(result / x_mult);
        double step = x_max / steps_per_quarter;
        double orig_x = origin.get_x();
        double orig_y = origin.get_y();
        double x_pos = -x_max;
        Point_2D begin = new Point_2D(orig_x + x_pos,orig_y);
        x_pos += step;
        double y_val = Math.sqrt((result - x_mult * Math.pow(x_pos, 2)) / y_mult);
        Point_2D t_middle = new Point_2D(orig_x + x_pos,orig_y + y_val);
        Point_2D b_middle = new Point_2D(orig_x + x_pos,orig_y - y_val);
        x_pos += step;
        while (x_pos < x_max) {
            y_val = Math.sqrt((result - x_mult * Math.pow(x_pos, 2)) / y_mult);
            Point_2D t_last = new Point_2D(orig_x + x_pos,orig_y + y_val);
            Point_2D b_last = new Point_2D(orig_x + x_pos,orig_y - y_val);
            // create facets
            mesh.add(new Facet_2D(begin,t_last,t_middle));
            mesh.add(new Facet_2D(begin,b_middle,b_last));
            // move last to middle
            t_middle = t_last;
            b_middle = b_last;
            // update x_pos
            x_pos += step;
        }
        
        // create last facets
        Point_2D end = new Point_2D(orig_x + x_max,orig_y);
        mesh.add(new Facet_2D(begin,end,t_middle));
        mesh.add(new Facet_2D(begin,b_middle,end));
    }

    public static void cuboid(Mesh_3D mesh, double x_length, double y_length, double z_length) {
        cuboid(mesh, x_length, y_length, z_length, false, new Point_3D(0,0,0));
    }
    
    public static void cuboid(Mesh_3D mesh, double x_length, double y_length, double z_length, 
            boolean center) {
        cuboid(mesh, x_length, y_length, z_length, center, new Point_3D(0,0,0));
    }
    
    public static void cuboid(Mesh_3D mesh, double x_length, double y_length, double z_length, 
            boolean center, Point_3D origin) {
        
        double orig_x = center ? origin.get_x() - (x_length / 2) : origin.get_x();
        double orig_y = center ? origin.get_y() - (y_length / 2) : origin.get_y();
        double orig_z = center ? origin.get_z() - (z_length / 2) : origin.get_z();

        Point_3D p1 = new Point_3D(orig_x,orig_y,orig_z);
        Point_3D p2 = new Point_3D(orig_x,orig_y + y_length,orig_z);
        Point_3D p3 = new Point_3D(orig_x + x_length,orig_y + y_length,orig_z);
        Point_3D p4 = new Point_3D(orig_x + x_length,orig_y,orig_z);
        Point_3D p5 = new Point_3D(orig_x,orig_y,orig_z + z_length);
        Point_3D p6 = new Point_3D(orig_x,orig_y + y_length,orig_z + z_length);
        Point_3D p7 = new Point_3D(orig_x + x_length,orig_y + y_length,orig_z + z_length);
        Point_3D p8 = new Point_3D(orig_x + x_length,orig_y,orig_z + z_length);
        
        // bottom
        mesh.add(new Facet_3D(p1, p2, p3));
        mesh.add(new Facet_3D(p1, p3, p4));
        // top
        mesh.add(new Facet_3D(p5, p7, p6));
        mesh.add(new Facet_3D(p5, p8, p7));
        // sides
        mesh.add(new Facet_3D(p1, p5, p6));
        mesh.add(new Facet_3D(p1, p6, p2));
        mesh.add(new Facet_3D(p4, p7, p8));
        mesh.add(new Facet_3D(p4, p3, p7));
        mesh.add(new Facet_3D(p1, p8, p5));
        mesh.add(new Facet_3D(p1, p4, p8));
        mesh.add(new Facet_3D(p2, p6, p7));
        mesh.add(new Facet_3D(p2, p7, p3));
    }

    public static void cylinder(Mesh_3D mesh, double b_radius, double t_radius, double height, 
            int steps_per_quarter) {
        cylinder(mesh, b_radius, t_radius, height, steps_per_quarter, false, new Point_3D(0,0,0));
    }
    
    public static void cylinder(Mesh_3D mesh, double b_radius, double t_radius, double height, 
            int steps_per_quarter, boolean center) {
        cylinder(mesh, b_radius, t_radius, height, steps_per_quarter, center, new Point_3D(0,0,0));
    }
    
    public static void cylinder(Mesh_3D mesh, double b_radius, double t_radius, double height, 
            int steps_per_quarter, boolean center, Point_3D origin) {
        
        double two_pi = Math.PI * 2; // 6.283185307179586
        double step = two_pi / (4 * steps_per_quarter);
        double orig_x = origin.get_x();
        double orig_y = origin.get_y();
        double orig_z = origin.get_z();
        double b_height = center ? -height / 2 : 0;
        double t_height = center ? height / 2 : height;
        double angle = step;
        
        // generate bottom circle
        if (Math.abs(b_radius) < mesh.get_precision()) // bottom single point
        {
            // single point
            Point_3D bottom = new Point_3D(orig_x,orig_y,orig_z + b_height);

            // Make top and side facets together
            Point_3D begin = new Point_3D(orig_x + t_radius, orig_y, orig_z + t_height);
            Point_3D middle = Point_3D.cylindrical_point(t_radius, angle, t_height, origin);
            mesh.add(new Facet_3D(bottom, middle, begin)); // add side facet
            angle += step;
            while (angle < two_pi)
            {
                Point_3D last = Point_3D.cylindrical_point(t_radius, angle, t_height, origin);

                // create top facet
                mesh.add(new Facet_3D(begin, middle, last)); 
                // create side facet
                mesh.add(new Facet_3D(bottom, last, middle));

                middle = last;
                angle += step;
            }
            mesh.add(new Facet_3D(bottom, begin, middle)); // add last side facet
        }
        else if (Math.abs(t_radius) < mesh.get_precision()) // top single point
        {
            // single point
            Point_3D top = new Point_3D(orig_x,orig_y,orig_z + t_height);

            // Make top and side facets together
            Point_3D begin = new Point_3D(orig_x + b_radius, orig_y, orig_z + b_height);
            Point_3D middle = Point_3D.cylindrical_point(b_radius, angle, b_height, origin);
            mesh.add(new Facet_3D(top, begin, middle)); // add side facet
            angle += step;
            while (angle < two_pi)
            {
                Point_3D last = Point_3D.cylindrical_point(b_radius, angle, b_height, origin);

                // create bottom facet
                mesh.add(new Facet_3D(begin, last, middle)); 
                // create side facet
                mesh.add(new Facet_3D(top, middle, last));

                middle = last;
                angle += step;
            }
            mesh.add(new Facet_3D(top, middle, begin)); // add last side facet
        }
        else
        {
            Point_3D b_begin = new Point_3D(orig_x + b_radius, orig_y, orig_z + b_height);
            Point_3D t_begin = new Point_3D(orig_x + t_radius, orig_y, orig_z + t_height);
            Point_3D b_middle = Point_3D.cylindrical_point(b_radius, angle, b_height, origin);
            Point_3D t_middle = Point_3D.cylindrical_point(t_radius, angle, t_height, origin);
            mesh.add(new Facet_3D(b_begin, t_middle, t_begin));  // add side facet
            mesh.add(new Facet_3D(b_begin, b_middle, t_middle)); // add side facet
            angle += step;
            while (angle < two_pi)
            {
                Point_3D b_last = Point_3D.cylindrical_point(b_radius, angle, b_height, origin);
                Point_3D t_last = Point_3D.cylindrical_point(t_radius, angle, t_height, origin);

                mesh.add(new Facet_3D(b_begin, b_last, b_middle));  // add bottom facet
                mesh.add(new Facet_3D(t_begin, t_middle, t_last));  // add top facet
                mesh.add(new Facet_3D(b_middle, t_last, t_middle)); // add side facet
                mesh.add(new Facet_3D(b_middle, b_last, t_last));   // add side facet
                b_middle = b_last;
                t_middle = t_last;
                angle += step;
            }
            mesh.add(new Facet_3D(b_begin, t_begin, t_middle));  // add last side facets
            mesh.add(new Facet_3D(b_begin, t_middle, b_middle)); // add last side facets
        }
    }

    public static void e_cylinder(Mesh_3D mesh, double b_x_radius, double b_y_radius, double t_x_radius, 
            double t_y_radius, double height, int steps_per_quarter) {
        e_cylinder(mesh, b_x_radius, b_y_radius, t_x_radius, t_y_radius, height, steps_per_quarter, false, new Point_3D(0,0,0));
    }
    
    public static void e_cylinder(Mesh_3D mesh, double b_x_radius, double b_y_radius, double t_x_radius, 
            double t_y_radius, double height, int steps_per_quarter, boolean center) {
        e_cylinder(mesh, b_x_radius, b_y_radius, t_x_radius, t_y_radius, height, steps_per_quarter, center, new Point_3D(0,0,0));
    }
    
    public static void e_cylinder(Mesh_3D mesh, double b_x_radius, double b_y_radius, double t_x_radius, 
            double t_y_radius, double height, int steps_per_quarter, boolean center, 
            Point_3D origin) {
        
        // bottom ellipse parameters
        double b_y_mult = Math.pow(b_x_radius,2);
        double b_x_mult = Math.pow(b_y_radius,2);
        double b_result = b_x_mult * b_y_mult;

        // top ellipse parameters
        double t_y_mult = Math.pow(t_x_radius,2);
        double t_x_mult = Math.pow(t_y_radius,2);
        double t_result = t_x_mult * t_y_mult;
        
        // cylinder parameters
        double orig_x = origin.get_x();
        double orig_y = origin.get_y();
        double orig_z = origin.get_z();
        double b_height = center ? -height / 2 : 0;
        double t_height = center ? height / 2 : height;
        // generate bottom circle
        if (Math.abs(b_x_radius) < mesh.get_precision() && 
                Math.abs(b_y_radius) < mesh.get_precision()) { // bottom single point

            // single point
            Point_3D bottom = new Point_3D(orig_x,orig_y,orig_z + b_height);

            // Make top and side facets together
            double x_max = Math.sqrt(t_result / t_x_mult);
            double step = x_max / steps_per_quarter;
            double x_pos = -x_max;
            Point_3D begin = new Point_3D(orig_x + x_pos,orig_y,orig_z + t_height);
            x_pos += step;
            double y_val = Math.sqrt((t_result - t_x_mult * Math.pow(x_pos, 2)) / t_y_mult);
            Point_3D t_middle = new Point_3D(orig_x + x_pos,orig_y + y_val, orig_z + t_height);
            Point_3D b_middle = new Point_3D(orig_x + x_pos,orig_y - y_val, orig_z + t_height);
            // create side facets
            mesh.add(new Facet_3D(bottom,begin,t_middle));
            mesh.add(new Facet_3D(bottom,b_middle,begin));
            x_pos += step;
            while (x_pos < x_max)
            {
                y_val = Math.sqrt((t_result - t_x_mult * Math.pow(x_pos, 2)) / t_y_mult);
                Point_3D t_last = new Point_3D(orig_x + x_pos,orig_y + y_val, orig_z + t_height);
                Point_3D b_last = new Point_3D(orig_x + x_pos,orig_y - y_val, orig_z + t_height);
                // create top facets
                mesh.add(new Facet_3D(begin,t_last,t_middle));
                mesh.add(new Facet_3D(begin,b_middle,b_last));
                // create side facets
                mesh.add(new Facet_3D(bottom,t_middle,t_last));
                mesh.add(new Facet_3D(bottom,b_last,b_middle));
                // move last to middle
                t_middle = t_last;
                b_middle = b_last;
                // update x_pos
                x_pos += step;
            }

            // create last facets
            Point_3D end = new Point_3D(orig_x + x_max,orig_y,orig_z + t_height);
            mesh.add(new Facet_3D(begin,end,t_middle));
            mesh.add(new Facet_3D(begin,b_middle,end));
            mesh.add(new Facet_3D(bottom,t_middle,end));
            mesh.add(new Facet_3D(bottom,end,b_middle));
            
        } else if (Math.abs(t_x_radius) < mesh.get_precision() && 
                Math.abs(t_y_radius) < mesh.get_precision()) { // top single point

            // single point
            Point_3D top = new Point_3D(orig_x,orig_y,orig_z + t_height);

            // Make top and side facets together
            double x_max = Math.sqrt(b_result / b_x_mult);
            double step = x_max / steps_per_quarter;
            double x_pos = -x_max;
            Point_3D begin = new Point_3D(orig_x + x_pos,orig_y,orig_z + b_height);
            x_pos += step;
            double y_val = Math.sqrt((b_result - b_x_mult * Math.pow(x_pos, 2)) / b_y_mult);
            Point_3D t_middle = new Point_3D(orig_x + x_pos,orig_y + y_val, orig_z + b_height);
            Point_3D b_middle = new Point_3D(orig_x + x_pos,orig_y - y_val, orig_z + b_height);
            // create side facets
            mesh.add(new Facet_3D(top,t_middle,begin));
            mesh.add(new Facet_3D(top,begin,b_middle));
            x_pos += step;
            while (x_pos < x_max)
            {
                y_val = Math.sqrt((b_result - b_x_mult * Math.pow(x_pos, 2)) / b_y_mult);
                Point_3D t_last = new Point_3D(orig_x + x_pos,orig_y + y_val, orig_z + b_height);
                Point_3D b_last = new Point_3D(orig_x + x_pos,orig_y - y_val, orig_z + b_height);
                // create bottom facets
                mesh.add(new Facet_3D(begin,t_middle,t_last));
                mesh.add(new Facet_3D(begin,b_last,b_middle));
                // create side facets
                mesh.add(new Facet_3D(top,t_last,t_middle));
                mesh.add(new Facet_3D(top,b_middle,b_last));
                // move last to middle
                t_middle = t_last;
                b_middle = b_last;
                // update x_pos
                x_pos += step;
            }

            // create last facets
            Point_3D end = new Point_3D(orig_x + x_max,orig_y,orig_z + b_height);
            mesh.add(new Facet_3D(begin,t_middle,end));
            mesh.add(new Facet_3D(begin,end,b_middle));
            mesh.add(new Facet_3D(top,end,t_middle));
            mesh.add(new Facet_3D(top,b_middle,end));
            
        } else {
            
            double b_x_max = Math.sqrt(b_result / b_x_mult);
            double t_x_max = Math.sqrt(t_result / t_x_mult);
            double b_step = b_x_max / steps_per_quarter;
            double t_step = t_x_max / steps_per_quarter;
            double b_x_pos = -b_x_max;
            double t_x_pos = -t_x_max;
            Point_3D b_begin = new Point_3D(orig_x + b_x_pos,orig_y,orig_z + b_height);
            Point_3D t_begin = new Point_3D(orig_x + t_x_pos,orig_y,orig_z + t_height);
            b_x_pos += b_step;
            t_x_pos += t_step;
            double b_y_val = Math.sqrt((b_result - b_x_mult * Math.pow(b_x_pos, 2)) / b_y_mult);
            double t_y_val = Math.sqrt((t_result - t_x_mult * Math.pow(t_x_pos, 2)) / t_y_mult);
            // TODO
            Point_3D b_p_middle = new Point_3D(orig_x + b_x_pos,orig_y + b_y_val, orig_z + b_height);
            Point_3D b_n_middle = new Point_3D(orig_x + b_x_pos,orig_y - b_y_val, orig_z + b_height);
            Point_3D t_p_middle = new Point_3D(orig_x + t_x_pos,orig_y + t_y_val, orig_z + t_height);
            Point_3D t_n_middle = new Point_3D(orig_x + t_x_pos,orig_y - t_y_val, orig_z + t_height);
            // create side facets
            mesh.add(new Facet_3D(b_begin,t_begin,t_p_middle));
            mesh.add(new Facet_3D(b_begin,t_p_middle,b_p_middle));
            mesh.add(new Facet_3D(b_begin,t_n_middle,t_begin));
            mesh.add(new Facet_3D(b_begin,b_n_middle,t_n_middle));
            b_x_pos += b_step;
            t_x_pos += t_step;
            while (b_x_pos < b_x_max)
            {
                b_y_val = Math.sqrt((b_result - b_x_mult * Math.pow(b_x_pos, 2)) / b_y_mult);
                t_y_val = Math.sqrt((t_result - t_x_mult * Math.pow(t_x_pos, 2)) / t_y_mult);
                Point_3D b_p_last = new Point_3D(orig_x + b_x_pos,orig_y + b_y_val, orig_z + b_height);
                Point_3D b_n_last = new Point_3D(orig_x + b_x_pos,orig_y - b_y_val, orig_z + b_height);
                Point_3D t_p_last = new Point_3D(orig_x + t_x_pos,orig_y + t_y_val, orig_z + t_height);
                Point_3D t_n_last = new Point_3D(orig_x + t_x_pos,orig_y - t_y_val, orig_z + t_height);
                // create bottom facets
                mesh.add(new Facet_3D(b_begin,b_p_middle,b_p_last));
                mesh.add(new Facet_3D(b_begin,b_n_last,b_n_middle));
                // create top facets
                mesh.add(new Facet_3D(t_begin,t_p_last,t_p_middle));
                mesh.add(new Facet_3D(t_begin,t_n_middle,t_n_last));
                // create side facets
                mesh.add(new Facet_3D(b_p_middle,t_p_middle,t_p_last));
                mesh.add(new Facet_3D(b_p_middle,t_p_last,b_p_last));
                mesh.add(new Facet_3D(b_n_middle,t_n_last,t_n_middle));
                mesh.add(new Facet_3D(b_n_middle,b_n_last,t_n_last));
                // move last to middle
                b_p_middle = b_p_last;
                b_n_middle = b_n_last;
                t_p_middle = t_p_last;
                t_n_middle = t_n_last;
                // update x_pos
                b_x_pos += b_step;
                t_x_pos += t_step;
            }

            // create last facets
            Point_3D b_end = new Point_3D(orig_x + b_x_max,orig_y,orig_z + b_height);
            Point_3D t_end = new Point_3D(orig_x + t_x_max,orig_y,orig_z + t_height);
            mesh.add(new Facet_3D(b_begin,b_p_middle,b_end));
            mesh.add(new Facet_3D(b_begin,b_end,b_n_middle));
            mesh.add(new Facet_3D(t_begin,t_end,t_p_middle));
            mesh.add(new Facet_3D(t_begin,t_n_middle,t_end));
            mesh.add(new Facet_3D(b_end,t_p_middle,t_end));
            mesh.add(new Facet_3D(b_end,b_p_middle,t_p_middle));
            mesh.add(new Facet_3D(b_end,t_end,t_n_middle));
            mesh.add(new Facet_3D(b_end,t_n_middle,b_n_middle));
        }
    }

    public static void sphere(Mesh_3D mesh, double radius, int steps_per_quarter) {
        sphere(mesh, radius, steps_per_quarter, new Point_3D(0,0,0));
    }
    
    public static void sphere(Mesh_3D mesh, double radius, int steps_per_quarter, Point_3D origin) {
        
        // pi: 3.1415926535897932384
//        double two_pi = Math.PI * 2;
        double step = Math.PI / (2 * steps_per_quarter);
        double orig_x = origin.get_x();
        double orig_y = origin.get_y();
        double orig_z = origin.get_z();

        Point_3D top = new Point_3D(orig_x, orig_y, orig_z + radius);
        Point_3D bottom = new Point_3D(orig_x, orig_y, orig_z - radius);
        ArrayDeque<Point_3D> init_vert_pts = new ArrayDeque<>();
        ArrayDeque<Point_3D> vert_pts = new ArrayDeque<>(); // vertical points
        // angular measuements
        double a_theta = 0;
        double a_phi = step;
        // fill queue with initial vertical set of points
        int num_phi_pts = (2 * steps_per_quarter) - 1;
        
        for (int count = 0; count < num_phi_pts; ++count, a_phi += step) {
            vert_pts.addLast(Point_3D.spherical_point(radius, a_theta, a_phi, origin));
            init_vert_pts.addLast(vert_pts.peekLast());
            Point_3D p = vert_pts.peekLast();
        }
        // now go through and generate facets
        int num_theta_pts = (4 * steps_per_quarter) - 1;
        a_theta += step;
        for (int count = 0; count < num_theta_pts; ++count, a_theta += step) {
            a_phi = step;
            Point_3D p = Point_3D.spherical_point(radius, a_theta, a_phi, origin);
            vert_pts.addLast(p);
            mesh.add(new Facet_3D(top, vert_pts.peekFirst(), p)); // add top level facet
            a_phi += step;
            // start count at 1 because created the initial point just above (p)
            for (int phi_count = 1; phi_count < num_phi_pts; ++phi_count, a_phi += step) {
                vert_pts.addLast(Point_3D.spherical_point(radius, a_theta, a_phi, origin));
                Point_3D p_p = vert_pts.peekFirst(); // previous p
                vert_pts.removeFirst();
                mesh.add(new Facet_3D(p, p_p, vert_pts.peekFirst()));
                mesh.add(new Facet_3D(p, vert_pts.peekFirst(), vert_pts.peekLast()));
                p = vert_pts.peekLast();
            }
            mesh.add(new Facet_3D(bottom, vert_pts.peekLast(), vert_pts.peekFirst())); // add bottom level facet
            vert_pts.removeFirst();
        }
        // add last remaining facets
        mesh.add(new Facet_3D(top, vert_pts.peekFirst(), init_vert_pts.peekFirst())); // top level facet
        while (vert_pts.size() > 1)
        {
            Point_3D p = vert_pts.peekFirst();
            vert_pts.removeFirst();
            Point_3D i_p = init_vert_pts.peekFirst();
            init_vert_pts.removeFirst();
            mesh.add(new Facet_3D(i_p, p, vert_pts.peekFirst()));
            mesh.add(new Facet_3D(i_p, vert_pts.peekFirst(), init_vert_pts.peekFirst()));
        }
        mesh.add(new Facet_3D(bottom, init_vert_pts.peekFirst(), vert_pts.peekFirst())); // bottom level facet
    }
    
    public static void ellipsoid(Mesh_3D mesh, double x_radius, double y_radius, double z_radius,
            int steps_per_quarter) {
        ellipsoid(mesh, x_radius, y_radius, z_radius, steps_per_quarter, new Point_3D(0,0,0));
    }
    
    public static void ellipsoid(Mesh_3D mesh, double x_radius, double y_radius, double z_radius,
            int steps_per_quarter, Point_3D origin) {
        // Ax^2 + By^2 + Cz^2 = D
        // x_radius = sqrt(D / A)
        // c_radius = sqrt(D / B)
        // z_radius = sqrt(D / C)
        // choose D to be A*B*C
        // x_radius = sqrt(B*C)
        // B*C = x_radius^2
        // y_radius = sqrt(A*C)
        // A*C = y_radius^2
        // z_radius = sqrt(A*B)
        // A*B = z_radius^2
        // solve for A,B, and C
        // A = z_radius^2 / B
        // substitute
        // (z_radius^2 / B) * C = y_radius^2
        // C = x_radius^2 / B
        // substitute
        // (z_radius^2 / B) * (x_radius^2 / B) = y_radius^2
        // B^2 = (z_radius^2 * x_radius^2) / y_radius^2
        // B = sqrt((z_radius^2 * x_radius^2) / y_radius^2)
        // C = x_radius^2 / B
        // A = z_radius^2 / B
        double y_mult = Math.sqrt((Math.pow(z_radius,2) * Math.pow(x_radius,2)) / Math.pow(y_radius,2)); // B
        double z_mult = Math.pow(x_radius,2) / y_mult; // C
        double x_mult = Math.pow(z_radius,2) / y_mult; // A
        double result = x_mult * y_mult * z_mult; // D
        // Ax^2 + By^2 + Cz^2 = D
        // find max z by setting x and y to zero
        // z = sqrt(D / C)
        double z_max = Math.sqrt(result / z_mult);
        
        double z_step = z_max / steps_per_quarter;
        double orig_x = origin.get_x();
        double orig_y = origin.get_y();
        double orig_z = origin.get_z();
        
        // then starting at z=0, work to max z
        // for each level, start at -max_x and work to max x
        // Ax^2 + By^2 = D - z_step
        // y = +/- sqrt((D - z_step - Ax^2) / B)
        
        // keep track of previous layer points to create next layer of facets
        ArrayDeque<Point_3D> pos_prev_layer = new ArrayDeque<>(); // positive side points
        ArrayDeque<Point_3D> neg_prev_layer = new ArrayDeque<>(); // negative side points
        // start at bottom
        Point_3D z_max_pt = new Point_3D(orig_x,orig_y,orig_z - z_max); // bottom most point
        double z_pos = z_step - z_max; // z position of layer
        // find max x by setting y to zero and z to z_pos
        // Ax^2 + By^2 + Cz^2 = D
        // Ax^2 + Cz^2 = D
        // x_mult * x^2 + z_mult * z_pos^2 = result
        // x_mult * x^2 = result - z_mult * z_pos^2
        // x = sqrt((result - z_mult * z_pos^2) / x_mult)
//        Point_3D::Measurement x_max = sqrt((result - fabs(z_pos)) / x_mult);
        double x_max = Math.sqrt((result - z_mult * Math.pow(z_pos, 2)) / x_mult);
        double x_step = x_max / steps_per_quarter;
        double x_pos = x_step - x_max; // x position
        Point_3D p_begin = new Point_3D(orig_x - x_max,orig_y,orig_z + z_pos);
        Point_3D p_pos_pt = p_begin;
        Point_3D p_neg_pt = p_begin;
        // create bottom most facets
        int num_layer_points = (2 * steps_per_quarter) - 1;
        for (int count=0; count < num_layer_points; x_pos += x_step, ++count) {
            // determine y value
            // Ax^2 + By^2 + Cz^2 = D
            // y_mult * y^2 = result - x_mult * x_pos^2 - z_mult * z_pos^2
            // y = sqrt((result - x_mult * x_pos^2 - z_mult * z_pos^2) / y_mult)
//            Point_3D::Measurement y = sqrt((result - fabs(z_pos) - x_mult * pow(x_pos, 2)) / y_mult);
            double y = Math.sqrt((result - x_mult * Math.pow(x_pos, 2) - z_mult * Math.pow(z_pos, 2)) / y_mult);
            pos_prev_layer.add(new Point_3D(orig_x + x_pos, orig_y + y, orig_z + z_pos));
            neg_prev_layer.add(new Point_3D(orig_x + x_pos, orig_y - y, orig_z + z_pos));
            mesh.add(new Facet_3D(z_max_pt, p_pos_pt, pos_prev_layer.peekLast()));
            mesh.add(new Facet_3D(z_max_pt, neg_prev_layer.peekLast(), p_neg_pt));
            p_pos_pt = pos_prev_layer.peekLast();
            p_neg_pt = neg_prev_layer.peekLast();
        }
        Point_3D p_end = new Point_3D(orig_x + x_max,orig_y,orig_z + z_pos);
        mesh.add(new Facet_3D(z_max_pt, p_pos_pt, p_end));
        mesh.add(new Facet_3D(z_max_pt, p_end, p_neg_pt));
        z_pos += z_step; // go to next level
        // now generate facets in the middle between the z top and z bottom
        for (int count=1;count < num_layer_points;z_pos += z_step,++count) {
            x_max = Math.sqrt((result - z_mult * Math.pow(z_pos, 2)) / x_mult); //sqrt((result - fabs(z_pos)) / x_mult);
            x_step = x_max / steps_per_quarter;
            x_pos = x_step - x_max;
            // generate first points, pop prev layer first points
            Point_3D begin = new Point_3D(orig_x - x_max, orig_y, orig_z + z_pos);
            Point_3D pos_pt = begin;
            Point_3D neg_pt = begin;
            p_pos_pt = p_begin;
            p_neg_pt = p_begin;
            for (int layer_count=0;layer_count < num_layer_points; x_pos += x_step,++layer_count) {
                // generate new points and facets
                double y = Math.sqrt((result - x_mult * Math.pow(x_pos, 2) - z_mult * Math.pow(z_pos, 2)) / y_mult); //sqrt((result - fabs(z_pos) - x_mult * pow(x_pos, 2)) / y_mult);
                pos_prev_layer.add(new Point_3D(orig_x + x_pos, orig_y + y, orig_z + z_pos));
                neg_prev_layer.add(new Point_3D(orig_x + x_pos, orig_y - y, orig_z + z_pos));
                // generate positive side facets
                mesh.add(new Facet_3D(pos_prev_layer.peekLast(),p_pos_pt,pos_pt));
                mesh.add(new Facet_3D(pos_prev_layer.peekLast(),pos_prev_layer.peekFirst(),p_pos_pt));
                // generate negative side facets
                mesh.add(new Facet_3D(neg_prev_layer.peekLast(),neg_pt,p_neg_pt));
                mesh.add(new Facet_3D(neg_prev_layer.peekLast(),p_neg_pt,neg_prev_layer.peekFirst()));
                // update point values for next cycle
                p_pos_pt = pos_prev_layer.peekFirst();
                pos_prev_layer.pop();
                pos_pt = pos_prev_layer.peekLast();
                p_neg_pt = neg_prev_layer.peekFirst();
                neg_prev_layer.pop();
                neg_pt = neg_prev_layer.peekLast();
            }
            // end point
            Point_3D end = new Point_3D(orig_x + x_max, orig_y, orig_z + z_pos);
            // generate end facets
            mesh.add(new Facet_3D(end, p_pos_pt, pos_pt));
            mesh.add(new Facet_3D(end, p_end, p_pos_pt));
            mesh.add(new Facet_3D(end, neg_pt, p_neg_pt));
            mesh.add(new Facet_3D(end, p_neg_pt, p_end));
            // prepare for next cycle through
            p_begin = begin;
            p_end = end; 
        }
        // final top most z point
        z_max_pt = new Point_3D(orig_x,orig_y,orig_z + z_max);
        // first points
        p_pos_pt = p_begin;
        p_neg_pt = p_begin;
        while (!pos_prev_layer.isEmpty())
        {
            mesh.add(new Facet_3D(z_max_pt,pos_prev_layer.peekFirst(),p_pos_pt));
            mesh.add(new Facet_3D(z_max_pt,p_neg_pt,neg_prev_layer.peekFirst()));
            p_pos_pt = pos_prev_layer.peekFirst();
            pos_prev_layer.pop();
            p_neg_pt = neg_prev_layer.peekFirst();
            neg_prev_layer.pop();
        }
        // last facets
        mesh.add(new Facet_3D(z_max_pt,p_end,p_pos_pt));
        mesh.add(new Facet_3D(z_max_pt,p_neg_pt,p_end));
    }
}
