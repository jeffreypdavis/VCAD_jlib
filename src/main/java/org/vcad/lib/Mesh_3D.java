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
 * File: Mesh_3D.java
 */
package org.vcad.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jeffrey Davis
 */
public class Mesh_3D implements Iterable<Facet_3D> {
    
    private final static double DBL_EPSILON = 2.2204460492503131e-16d;
    
    private final double precision;
    private final List<Point_3D> point_list;
    private final List<Facet> facet_list;
    
    public Mesh_3D() {
        super();
        precision = DBL_EPSILON * 21;
        point_list = new ArrayList<>();
        facet_list = new ArrayList<>();
    }
    
    public Mesh_3D(double precision) {
        super();
        this.precision = precision;
        point_list = new ArrayList<>();
        facet_list = new ArrayList<>();
    }

    public double get_precision() {
        return precision;
    }
    
    @Override
    public Iterator<Facet_3D> iterator() {
        return new Facet_3D_Iterator(point_list, facet_list);
    }
    
    public Iterator<Point_3D> point_iterator() {
        return point_list.iterator();
    }
    
    public Iterator<Facet> facet_iterator() {
        return facet_list.iterator();
    }
    
    public void add(Facet_3D facet) {
        // find points if mesh already contains them
        int p1_index = -1;
        int p2_index = -1;
        int p3_index = -1;
        int index = 0;
        
        for (Point_3D pt : point_list) {
            // test p1
            if (pt.get_x() == facet.get_point1().get_x() && pt.get_y() == facet.get_point1().get_y() && pt.get_z() == facet.get_point1().get_z())
                p1_index = index;
            else if (pt.get_x() == facet.get_point2().get_x() && pt.get_y() == facet.get_point2().get_y() && pt.get_z() == facet.get_point2().get_z())
                p2_index = index;
            else if (pt.get_x() == facet.get_point3().get_x() && pt.get_y() == facet.get_point3().get_y() && pt.get_z() == facet.get_point3().get_z())
                p3_index = index;
            ++index;
        }
        
        // add points if not found
        if (p1_index == -1)
        {
            point_list.add(new Point_3D(facet.get_point1().get_x(), facet.get_point1().get_y(), facet.get_point1().get_z()));
            p1_index = index;
            ++index;
        }
        if (p2_index == -1)
        {
            point_list.add(new Point_3D(facet.get_point2().get_x(), facet.get_point2().get_y(), facet.get_point2().get_z()));
            p2_index = index;
            ++index;
        }
        if (p3_index == -1)
        {
            point_list.add(new Point_3D(facet.get_point3().get_x(), facet.get_point3().get_y(), facet.get_point3().get_z()));
            p3_index = index;
            ++index;
        }
        
        // add facet
        facet_list.add(new Facet(p1_index, p2_index, p3_index));
    }
    
    public int size() {
        return facet_list.size();
    }
    
    public boolean isEmpty() {
        return facet_list.isEmpty();
    }
    
    public void clear() {
        facet_list.clear();
        point_list.clear();
    }
    
    public void remove(Facet_3D facet) {
        // first find point indices
        int p1_index = -1;
        Point_3D p1 = null;
        int p2_index = -1;
        Point_3D p2 = null;
        int p3_index = -1;
        Point_3D p3 = null;
        int index = 0;
        
        for (Point_3D pt : point_list) {
            // test p1
            if (pt.get_x() == facet.get_point1().get_x() && pt.get_y() == facet.get_point1().get_y() && pt.get_z() == facet.get_point1().get_z())
            {
                p1_index = index;
                p1 = pt;
            }
            else if (pt.get_x() == facet.get_point2().get_x() && pt.get_y() == facet.get_point2().get_y() && pt.get_z() == facet.get_point2().get_z())
            {
                p2_index = index;
                p2 = pt;
            }
            else if (pt.get_x() == facet.get_point3().get_x() && pt.get_y() == facet.get_point3().get_y() && pt.get_z() == facet.get_point3().get_z())
            {
                p3_index = index;
                p3 = pt;
            }
            ++index;
        }
        if (p1_index == -1 || p2_index == -1 || p3_index == -1)
            throw new IllegalArgumentException("unable to locate facet points");
        // find facet in facet list
        Facet f = null;
        for (Facet existingFacet : facet_list) {
            if ((existingFacet.get_p1_index() == p1_index && existingFacet.get_p2_index() == p2_index && existingFacet.get_p3_index() == p3_index) ||
                    (existingFacet.get_p1_index() == p2_index && existingFacet.get_p2_index() == p3_index && existingFacet.get_p3_index() == p1_index) ||
                    (existingFacet.get_p1_index() == p3_index && existingFacet.get_p2_index() == p1_index && existingFacet.get_p3_index() == p2_index)) {
                f = existingFacet;
                break;
            }
        }
        if (f == null)
            throw new IllegalArgumentException("unable to locate facet");
        
        // remove the facet
        facet_list.remove(f);
    }
    
    public Mesh_3D rotate(Angle angle) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().rotate(angle);
        }
        return this;
    }
    
    public Mesh_3D rotate(double angle, Vector_3D axis) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().rotate(angle, axis);
        }
        return this;
    }
    
    public Mesh_3D rotate(Angle angle, Point_3D origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().rotate(angle, origin);
        }
        return this;
    }
    
    public Mesh_3D rotate(double angle, Vector_3D axis, Point_3D origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().rotate(angle, axis, origin);
        }
        return this;
    }
    
    // scale
    public Mesh_3D scale(double x_scalar, double y_scalar, double z_scalar) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().scale(x_scalar, y_scalar, z_scalar);
        }
        
        int num_neg = 0;
        if (x_scalar < 0) { ++num_neg; }
        if (y_scalar < 0) { ++num_neg; }
        if (z_scalar < 0) { ++num_neg; }
        if (num_neg % 2 == 1) // odd number of negative multipliers
        {
            // invert the unit normal vector of each facet by swapping p2 and p3
            Iterator<Facet> facetIter = facet_list.iterator();
            while (facetIter.hasNext()) {
                facetIter.next().invert_unv();
            }
        }
        
        return this;
    }
    
    public Mesh_3D scale(double x_scalar, double y_scalar, double z_scalar, Point_3D origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().scale(x_scalar, y_scalar, z_scalar, origin);
        }
        
        int num_neg = 0;
        if (x_scalar < 0) { ++num_neg; }
        if (y_scalar < 0) { ++num_neg; }
        if (z_scalar < 0) { ++num_neg; }
        if (num_neg % 2 == 1) // odd number of negative multipliers
        {
            // invert the unit normal vector of each facet by swapping p2 and p3
            Iterator<Facet> facetIter = facet_list.iterator();
            while (facetIter.hasNext()) {
                facetIter.next().invert_unv();
            }
        }
        
        return this;
    }
    
    // translate
    public Mesh_3D translate(double x_val, double y_val, double z_val) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().translate(x_val, y_val, z_val);
        }
        return this;
    }
    
    public Mesh_3D translate(Vector_3D vector) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().translate(vector);
        }
        return this;
    }
    
    /*
     * move the facet to a different coordinate system and origin.
     * A new coordinate system must be supplied and this is done by 
     * specifying a new origin, a new axis, and one more point that is
     * not on the axis to define a plane.  ref_origin is an option to 
     * move based on a different origin than 0,0,0.
     * 
     * There are six methods to allow for specifying the new coordinate system
     * the names of the methods start with move.  Then next letter is the 
     * axis specified in the vector argument - x, y, or z axis.  The last part of the name
     * is identifying where the third point is located - xy plane, xz plane,
     * or yz plane.
     * 
     * exception safety: basic guarantee
     */
    public Mesh_3D move_x_pxy(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xy_plane) {
        return this.move_x_pxy(new_origin, x_axis, pt_xy_plane, new Point_3D(0,0,0));
    }
    
    public Mesh_3D move_x_pxy(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xy_plane, 
            Point_3D ref_origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().move_x_pxy(new_origin, x_axis, pt_xy_plane, ref_origin);
        }
        return this;
    }
    
    public Mesh_3D move_x_pxz(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xz_plane) {
        return this.move_x_pxz(new_origin, x_axis, pt_xz_plane, new Point_3D(0,0,0));
    }
    
    public Mesh_3D move_x_pxz(Point_3D new_origin, Vector_3D x_axis, Point_3D pt_xz_plane, 
            Point_3D ref_origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().move_x_pxz(new_origin, x_axis, pt_xz_plane, ref_origin);
        }
        return this;
    }
    
    public Mesh_3D move_y_pxy(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_xy_plane) {
        return this.move_y_pxy(new_origin, y_axis, pt_xy_plane, new Point_3D(0,0,0));
    }
    
    public Mesh_3D move_y_pxy(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_xy_plane, 
            Point_3D ref_origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().move_y_pxy(new_origin, y_axis, pt_xy_plane, ref_origin);
        }
        return this;
    }
    
    public Mesh_3D move_y_pyz(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_yz_plane) {
        return this.move_y_pyz(new_origin, y_axis, pt_yz_plane, new Point_3D(0,0,0));
    }
    
    public Mesh_3D move_y_pyz(Point_3D new_origin, Vector_3D y_axis, Point_3D pt_yz_plane, 
            Point_3D ref_origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().move_y_pyz(new_origin, y_axis, pt_yz_plane, ref_origin);
        }
        return this;
    }
    
    public Mesh_3D move_z_pxz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_xz_plane) {
        return this.move_z_pxz(new_origin, z_axis, pt_xz_plane, new Point_3D(0,0,0));
    }
    
    public Mesh_3D move_z_pxz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_xz_plane, 
            Point_3D ref_origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().move_z_pxz(new_origin, z_axis, pt_xz_plane, ref_origin);
        }
        return this;
    }
    
    public Mesh_3D move_z_pyz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_yz_plane) {
        return this.move_z_pyz(new_origin, z_axis, pt_yz_plane, new Point_3D(0,0,0));
    }
    
    public Mesh_3D move_z_pyz(Point_3D new_origin, Vector_3D z_axis, Point_3D pt_yz_plane, 
            Point_3D ref_origin) {
        Iterator<Point_3D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().move_z_pyz(new_origin, z_axis, pt_yz_plane, ref_origin);
        }
        return this;
    }
    
    private class Facet_3D_Iterator implements Iterator<Facet_3D> {
        private final List<Point_3D> point_list;
        private final List<Facet> facet_list;
        private final Iterator<Facet> facet_it;
        
        Facet_3D_Iterator(List<Point_3D> points, List<Facet> facets) {
            point_list = points;
            facet_list = facets;
            facet_it = facet_list.iterator();
        }
        
        @Override
        public boolean hasNext() {
            return facet_it.hasNext();
        }

        @Override
        public Facet_3D next() {
            Facet f = facet_it.next();
            return new Facet_3D(point_list.get(f.get_p1_index()), point_list.get(f.get_p2_index()), point_list.get(f.get_p3_index()));
        }
    }
    
    public static boolean mesh_contains_point(Mesh_3D mesh, Point_3D p, Bool pt_on_surface) {
        // determine if point is on or inside the mesh
        boolean outside_mesh = false;
        Iterator<Facet_3D> iter = mesh.iterator();
        while (iter.hasNext()) {
            Facet_3D f = iter.next();
            
//            System.out.println("Checking facet p1 x: " + f.get_point1().get_x() + " y: " + 
//                    f.get_point1().get_y() + " z: " + f.get_point1().get_z() + 
//                    " p2 x: " + f.get_point2().get_x() + " y: " + 
//                    f.get_point2().get_y() + " z: " + f.get_point2().get_z() + 
//                    " p3 x: " + f.get_point3().get_x() + " y: " + 
//                    f.get_point3().get_y() + " z: " + f.get_point3().get_z());
            // if point is on a facet, return true
            Bool pt_on_side = new Bool(false);
            if (f.contains_point(p, pt_on_side, mesh.get_precision())) {
//                System.out.println("    Point is on the surface");
                pt_on_surface.set_val(true);
                return true;
            } else if (outside_mesh) // if already found to be outside, 
                continue;          // see if point is on a facet - no need to look if outside anymore
//            (*iter)->set_debug(false);
//            if (debug)
//                cout << "    after facet contains point\n";
            // determine if point is inside mesh by checking if 
            // point is behind each closest facet
            
            // vector from point to facet
            Point_3D inside_pt = f.get_inside_point();
            Vector_3D v = new Vector_3D(p, inside_pt);
            boolean closer_facet = false; // if there is a facet that is closer
            
            // go through mesh again looking for possible facets that are closer
            Intersect_Point_3D i_point = new Intersect_Point_3D(); // initialize here so it will be only assigned in loop
//            int count = 0;
            Iterator<Facet_3D> it = mesh.iterator();
            while (it.hasNext()) {
                Facet_3D f2 = it.next();
//                System.out.println("    Checking if facet is closest facet p1 x: " + f2.get_point1().get_x() + 
//                        " y: " + f2.get_point1().get_y() + " z: " + f2.get_point1().get_z() + 
//                        " p2 x: " + f2.get_point2().get_x() + " y: " + f2.get_point2().get_y() + 
//                        " z: " + f2.get_point2().get_z() + " p3 x: " + f2.get_point3().get_x() + 
//                        " y: " + f2.get_point3().get_y() + " z: " + f2.get_point3().get_z());
//                if (debug)
//                    cout << "    closer facet loop " << count++ << " of " << facets.size() << "\n";
                if (f2.get_point1() == f.get_point1() && f2.get_point2() == f.get_point2() && 
                        f2.get_point3() == f.get_point3()) { // don't process the same facet
//                    System.out.println("        skipping same facet");
                    continue;
                }
                // check if facet is in the same general direction and closer 
                // than the *iter facet
//                Vector_3D v2(p, (*it)->get_inside_point());
//                if (dot_product(v2, v) >= 0 && v2.length() < v.length())
//                {
                    // see if line v from p intersects *it facet
//                    if (intersect_line_facet_plane(v, p, **it, i_point, precision) && 
//                            (*it)->contains_point(i_point, precision))
                    if (Facet_3D.intersect_line_facet_plane(v, p, f2, i_point, mesh.get_precision()) && 
                            Vector_3D.is_pt_on_vector(i_point.getPoint(), p, inside_pt, mesh.get_precision()) && 
                            f2.contains_point(i_point.getPoint(), pt_on_side, mesh.get_precision()))
                    {
//                        System.out.println("        Closer Facet: true");
                        // closer facet
                        closer_facet = true;
                        break;
                    }
//                }
            }
            
            if (!closer_facet) { // this is the closest facet, so test
//                System.out.println("    closest facet");
//                if (debug)
//                    cout << "closest facet v.length zero? " << within_round(v.length(), 0, precision) << "\n";
                // if point is in front of facet
                if (Vector_3D.dot_product(f.get_unv(), v.normalize()) < 0) {
//                    System.out.println("    Outside mesh");
                    outside_mesh = true;
//                } else {
//                    System.out.println("    Inside mesh");
                }
            }
        }
//        System.out.println("Finished loops");
        // if point is behind every closest facet, then the mesh contains the point
        if (!outside_mesh) {
            pt_on_surface.set_val(false);
//            System.out.println("returning true");
            return true;
        } else {
//            System.out.println("returning false");
            return false;
        }
    }
}
