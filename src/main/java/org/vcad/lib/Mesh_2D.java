/*
 * Mesh_2D.java
 */
package org.vcad.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author Jeffrey Davis
 */
public class Mesh_2D implements Iterable<Facet_2D> {
    
    private final static double DBL_EPSILON = 2.2204460492503131e-16d;
    
    private final double precision;
    private final List<Point_2D> point_list;
    private final List<Facet> facet_list;
    
    public Mesh_2D() {
        super();
        precision = DBL_EPSILON * 21;
        point_list = new ArrayList<>();
        facet_list = new ArrayList<>();
    }
    
    public Mesh_2D(double precision) {
        super();
        this.precision = precision;
        point_list = new ArrayList<>();
        facet_list = new ArrayList<>();
    }

    public Mesh_2D(Mesh_2D mesh) {
        super();
        
        this.precision = mesh.get_precision();
        point_list = new ArrayList<>();
        facet_list = new ArrayList<>();
        Iterator<Facet_2D> it = mesh.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }
    
    public double get_precision() {
        return precision;
    }
    
    @Override
    public Iterator<Facet_2D> iterator() {
        return new Facet_2D_Iterator(point_list, facet_list);
    }
    
    public Iterator<Point_2D> point_iterator() {
        return point_list.iterator();
    }
    
    public Iterator<Facet> facet_iterator() {
        return facet_list.iterator();
    }
    
    public void add(Facet_2D facet) {
        // find points if mesh already contains them
        int p1_index = -1;
        int p2_index = -1;
        int p3_index = -1;
        int index = 0;
        
        for (Point_2D pt : point_list) {
            // test p1
            if (pt.get_x() == facet.get_point1().get_x() && pt.get_y() == facet.get_point1().get_y())
                p1_index = index;
            else if (pt.get_x() == facet.get_point2().get_x() && pt.get_y() == facet.get_point2().get_y())
                p2_index = index;
            else if (pt.get_x() == facet.get_point3().get_x() && pt.get_y() == facet.get_point3().get_y())
                p3_index = index;
            ++index;
        }
        
        // add points if not found
        if (p1_index == -1) {
            point_list.add(new Point_2D(facet.get_point1().get_x(), facet.get_point1().get_y()));
            p1_index = index;
            ++index;
        }
        if (p2_index == -1) {
            point_list.add(new Point_2D(facet.get_point2().get_x(), facet.get_point2().get_y()));
            p2_index = index;
            ++index;
        }
        if (p3_index == -1) {
            point_list.add(new Point_2D(facet.get_point3().get_x(), facet.get_point3().get_y()));
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
    
    public void remove(Facet_2D facet) {
        // first find point indices
        int p1_index = -1;
        int p2_index = -1;
        int p3_index = -1;
        int index = 0;
        
        for (Point_2D pt : point_list) {
            // test p1
            if (pt.get_x() == facet.get_point1().get_x() && pt.get_y() == facet.get_point1().get_y()) {
                p1_index = index;
            } else if (pt.get_x() == facet.get_point2().get_x() && pt.get_y() == facet.get_point2().get_y()) {
                p2_index = index;
            } else if (pt.get_x() == facet.get_point3().get_x() && pt.get_y() == facet.get_point3().get_y()) {
                p3_index = index;
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
    
    public Mesh_2D rotate(double angle)
    {
        Iterator<Point_2D> it = point_list.iterator();
        while (it.hasNext())
            it.next().rotate(angle);
        return this;
    }
    
    public Mesh_2D rotate(double angle, Point_2D origin)
    {
        Iterator<Point_2D> it = point_list.iterator();
        while (it.hasNext())
            it.next().rotate(angle, origin);
        return this;
    }
    
    // scale
    public Mesh_2D scale(double x_scalar, double y_scalar) {
        Iterator<Point_2D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().scale(x_scalar, y_scalar);
        }
        
        int num_neg = 0;
        if (x_scalar < 0) { ++num_neg; }
        if (y_scalar < 0) { ++num_neg; }
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
    
    public Mesh_2D scale(double x_scalar, double y_scalar, Point_2D origin) {
        Iterator<Point_2D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().scale(x_scalar, y_scalar, origin);
        }
        
        int num_neg = 0;
        if (x_scalar < 0) { ++num_neg; }
        if (y_scalar < 0) { ++num_neg; }
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
    public Mesh_2D translate(double x_val, double y_val) {
        Iterator<Point_2D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().translate(x_val, y_val);
        }
        return this;
    }
    
    public Mesh_2D translate(Vector_2D vector) {
        Iterator<Point_2D> it = point_list.iterator();
        while (it.hasNext()) {
            it.next().translate(vector);
        }
        return this;
    }

    public Mesh_2D move(Point_2D new_origin, Vector_2D axis, boolean is_x_axis) {
        return this.move(new_origin, axis, is_x_axis, new Point_2D(0,0));
    }
    
    public Mesh_2D move(Point_2D new_origin, Vector_2D axis, boolean is_x_axis, 
            Point_2D ref_origin) {
        Iterator<Point_2D> it = point_list.iterator();
        while (it.hasNext())
            it.next().move(new_origin, axis, is_x_axis, ref_origin);
        return this;
    }
    
    
    public static Mesh_3D linear_extrude(Mesh_2D mesh_2d, Mesh_3D mesh, 
            double height, boolean center) {
        if (height == 0) {
            for (Iterator<Facet_2D> it = mesh_2d.iterator(); it.hasNext();) {
                Facet_2D f = it.next();
                mesh.add(new Facet_3D(new Point_3D(f.get_point1().get_x(), f.get_point1().get_y(), 0), 
                        new Point_3D(f.get_point2().get_x(), f.get_point2().get_y(), 0), 
                        new Point_3D(f.get_point3().get_x(), f.get_point3().get_y(), 0)));
            }
            
            return mesh;
        }
        
        // determine outside line segments
        class Facet_Side {
            public int point1;
            public int point2;
            public Facet_Side(int pt1, int pt2) {
                point1 = pt1; 
                point2 = pt2;
            }

            @Override
            public int hashCode() {
                return point1 ^ point2;
            }
            
            @Override
            public boolean equals(Object obj) {
                if (obj == null)
                    return false;
                if (!(obj instanceof Facet_Side))
                    return false;
                Facet_Side side = (Facet_Side) obj;
                
                return (point1 == side.point1 || point1 == side.point2) && 
                        (point2 == side.point2 || point2 == side.point1);
            }
        }
        
        Map<Facet_Side,Integer> facet_sides = new HashMap<>();
        
        for (Iterator<Facet> it = mesh_2d.facet_iterator(); it.hasNext();) {
            Facet f = it.next();
            // p1p2
            Facet_Side side = new Facet_Side(f.get_p1_index(), f.get_p2_index());
            if (facet_sides.containsKey(side)) {
                Integer val = facet_sides.get(side);
                facet_sides.put(side, val + 1);
            } else {
                facet_sides.put(side, 1);
            }
            
            // p1p3
            side = new Facet_Side(f.get_p1_index(), f.get_p3_index());
            if (facet_sides.containsKey(side)) {
                Integer val = facet_sides.get(side);
                facet_sides.put(side, val + 1);
            } else {
                facet_sides.put(side, 1);
            }
            
            // p2p3
            side = new Facet_Side(f.get_p2_index(), f.get_p3_index());
            if (facet_sides.containsKey(side)) {
                Integer val = facet_sides.get(side);
                facet_sides.put(side, val + 1);
            } else {
                facet_sides.put(side, 1);
            }
        }
        
        // find outside segments
        List<Facet_Side> outside_segments = new ArrayList<>();
        for (Facet_Side side : facet_sides.keySet()) {
            if (facet_sides.get(side) == 1)
                outside_segments.add(side);
        }
        facet_sides.clear();
        
        double lower_z = center ? -height / 2 : 0;
        double upper_z = center ? height / 2 : height;
        if (height < 0) {
            lower_z = center ? -lower_z : height;
            upper_z = center ? -upper_z : 0;
        }
        
        // generate mesh facets
        for (Iterator<Facet_2D> it = mesh_2d.iterator(); it.hasNext();) {
            Facet_2D f = it.next();
            // bottom facet
            mesh.add(new Facet_3D(new Point_3D(f.get_point1().get_x(), f.get_point1().get_y(), lower_z), 
                    new Point_3D(f.get_point3().get_x(), f.get_point3().get_y(), lower_z), 
                    new Point_3D(f.get_point2().get_x(), f.get_point2().get_y(), lower_z)));
            
            // top facet
            mesh.add(new Facet_3D(new Point_3D(f.get_point1().get_x(), f.get_point1().get_y(), upper_z), 
                    new Point_3D(f.get_point2().get_x(), f.get_point2().get_y(), upper_z), 
                    new Point_3D(f.get_point3().get_x(), f.get_point3().get_y(), upper_z)));
        }
        
        // generate side facets
        for (Facet_Side side : outside_segments) {
            
            Iterator<Point_2D> p1_it = mesh_2d.point_iterator();
            Iterator<Point_2D> p2_it = mesh_2d.point_iterator();
            int count = 0;
            while (count < side.point1) {
                if (p1_it.hasNext())
                    p1_it.next();
                else {
                    // TODO throw exception???
                }
                count++;
            }
            count = 0;
            while (count < side.point2) {
                if (p2_it.hasNext())
                    p2_it.next();
                else {
                    // TODO throw exception???
                }
                count++;
            }
            if (!p1_it.hasNext()) {
                // TODO throw exception???
            }
            Point_2D p1 = p1_it.next();
            if (!p2_it.hasNext()) {
                // TODO throw exception???
            }
            Point_2D p2 = p2_it.next();
                
            mesh.add(new Facet_3D(new Point_3D(p1.get_x(), p1.get_y(), lower_z), 
                    new Point_3D(p2.get_x(), p2.get_y(), upper_z), 
                    new Point_3D(p1.get_x(), p1.get_y(), upper_z)));
            mesh.add(new Facet_3D(new Point_3D(p1.get_x(), p1.get_y(), lower_z), 
                    new Point_3D(p2.get_x(), p2.get_y(), lower_z), 
                    new Point_3D(p2.get_x(), p2.get_y(), upper_z)));
        }
        
        return mesh;
    }
    
    private class Facet_2D_Iterator implements Iterator<Facet_2D> {
        private final List<Point_2D> point_list;
        private final List<Facet> facet_list;
        private final Iterator<Facet> current_facet;
        private Facet_2D facet;
        
        Facet_2D_Iterator(List<Point_2D> points, List<Facet> facets) {
            point_list = points;
            facet_list = facets;
            current_facet = facet_list.iterator();
//            update_facet();
        }

        private void update_facet() {
            if (current_facet.hasNext()) {
                Facet f = current_facet.next();
                facet = new Facet_2D(point_list.get(f.get_p1_index()), point_list.get(f.get_p2_index()), point_list.get(f.get_p3_index()));
            } else
                throw new NoSuchElementException("iterator is at end of mesh");
        }
        
        @Override
        public boolean hasNext() {
            if (current_facet.hasNext()) {
                update_facet();
                return true;
            } else
                return false;
        }

        @Override
        public Facet_2D next() {
            return facet;
        }
    }
}
