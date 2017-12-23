/*
 * Valid_Mesh_2D.java
 */
package org.vcad.lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jeffrey Davis
 */
public class Valid_Mesh_2D {
    
    private final double precision;
    private final List<Point_2D> all_points; // stores all points from mesh
    private final List<Point_2D> pts_on_facet_sides; // points that are in the middle of a facet side
    private final List<Facet> all_facets; // stores all facets from mesh
    private final List<List<Facet_2D>> too_many_share_side; // more than two facets that share the same side
    // key is the facet that contains the facets in the vector value
    private final Map<Facet_2D_Wrapper,List<Facet_2D>> facets_inside_facets; 

    public Valid_Mesh_2D(Mesh_2D mesh) {
        super();
        precision = mesh.get_precision();
        all_points = new ArrayList<>();
        pts_on_facet_sides = new ArrayList<>();
        all_facets = new ArrayList<>();
        too_many_share_side = new ArrayList<>();
        facets_inside_facets = new HashMap<>();
        
        for (Iterator<Point_2D> it = mesh.point_iterator(); it.hasNext();)
            all_points.add(it.next());
        for (Iterator<Facet> it = mesh.facet_iterator(); it.hasNext();)
            all_facets.add(it.next());
    }
    
    public boolean validate() {
        Map<Facet_Side,Integer> sides = new HashMap<>();
        Set<Integer> points = new HashSet<>();
        
        // go through facets and get unique points and count the number of side occurrences
        for (Facet facet : all_facets) {
            points.add(facet.get_p1_index());
            points.add(facet.get_p2_index());
            points.add(facet.get_p3_index());
            
            // take each side of each facet and insert into the map of sides
            // if the side already exists, update the side count
            // p1p2
            Facet_Side side = new Facet_Side(facet.get_p1_index(), facet.get_p2_index());
            Integer val = sides.get(side);
            if (val == null)
                sides.put(side,1);
            else
                sides.put(side, val + 1);
            // p1p3
            side = new Facet_Side(facet.get_p1_index(), facet.get_p3_index());
            val = sides.get(side);
            if (val == null)
                sides.put(side,1);
            else
                sides.put(side, val + 1);
            
            // p2p3
            side = new Facet_Side(facet.get_p2_index(), facet.get_p3_index());
            val = sides.get(side);
            if (val == null)
                sides.put(side,1);
            else
                sides.put(side, val + 1);
        }
        
        // now take each unique side and look for points that are on the side
        // but are not end points
        for (Facet_Side side : sides.keySet()) {
            Integer value = sides.get(side);
            if (value > 2) {
                List<Facet_2D> facets = new ArrayList<>();
                for (Facet facet : all_facets) {
                    int count = 0;
                    if (facet.get_p1_index() == side.point1 || facet.get_p1_index() == side.point2)
                        ++count;
                    if (facet.get_p2_index() == side.point1 || facet.get_p2_index() == side.point2)
                        ++count;
                    if (facet.get_p3_index() == side.point1 || facet.get_p3_index() == side.point2)
                        ++count;
                    if (count == 2) {
                        facets.add(new Facet_2D(all_points.get(facet.get_p1_index()), 
                                all_points.get(facet.get_p2_index()), 
                                all_points.get(facet.get_p3_index())));
                    }
                }
                
                too_many_share_side.add(facets);
            }
            
            Point_2D p1 = all_points.get(side.point1);
            Point_2D p2 = all_points.get(side.point2);
            
            for (Integer it : points) {
                // go to next side if point is an end point
                if (side.point1 == it || side.point2 == it)
                    continue;
                
                Point_2D pt = all_points.get(it);
                
                boolean found = false;
                for (Point_2D fs_pt : pts_on_facet_sides) {
                    if (fs_pt == pt) {
                        found = true;
                        break;
                    }
                }
                if (Vector_2D.is_pt_on_vector(pt, p1, p2, precision) && !found)
                    pts_on_facet_sides.add(pt);
            }
        }
        
        // create a vector of facets then sort them largest to smallest
        List<Facet_2D> sorted_facets = new ArrayList<>();
        for (Facet facet : all_facets) {
            sorted_facets.add(new Facet_2D(all_points.get(facet.get_p1_index()), 
                    all_points.get(facet.get_p2_index()), 
                    all_points.get(facet.get_p3_index())));
        }
        sorted_facets.sort(new Comparator<Facet_2D>() {
            
            private double area(Facet_2D facet) {
                
                Vector_2D p1p2 = new Vector_2D(facet.get_point1(), facet.get_point2());
                Vector_2D p1p3 = new Vector_2D(facet.get_point1(), facet.get_point3());
                Vector_2D p2p3 = new Vector_2D(facet.get_point2(), facet.get_point3());

                if (p1p2.length() >= p1p3.length() && p1p2.length() > p2p3.length()) {
                    // p1p2 is the largest side
                    if (p1p3.length() > p2p3.length()) {
                        // p1p3 is the next largest
                        return Math.abs(0.5 * Vector_2D.cross_product(p1p2, p1p3));
                    } else {
                        // p2p3 is the next largest
                        return Math.abs(0.5 * Vector_2D.cross_product(p1p2.minus(), p2p3));
                    }
                } else if (p1p3.length() >= p1p2.length() && p1p3.length() >= p2p3.length()) {
                    // p1p3 is the largest side
                    if (p1p2.length() > p2p3.length()) {
                        // p1p2 is the next largest
                        return Math.abs(0.5 * Vector_2D.cross_product(p1p3, p1p2));
                    } else {
                        // p2p3 is the next largest
                        return Math.abs(0.5 * Vector_2D.cross_product(p1p3.minus(), p2p3.minus()));
                    }
                } else {
                    // p2p3 is the largest side
                    if (p1p2.length() > p1p3.length()) {
                        // p1p2 is the next largest
                        return Math.abs(0.5 * Vector_2D.cross_product(p2p3, p1p2.minus()));
                    } else {
                        // p1p3 is the next largest
                        return Math.abs(0.5 * Vector_2D.cross_product(p2p3.minus(), p1p3.minus()));
                    }
                }
            }
            
            @Override
            public int compare(Facet_2D f1, Facet_2D f2) {
                // put larger facets before smaller facets
                double areaF1 = area(f1);
                double areaF2 = area(f2);
                if (areaF1 > areaF2) {
                    return -1;
                } else if (areaF2 > areaF1) {
                    return 1;
                } else { // 
                    return 0;
                }
            }
        });

        // now go through sorted facets and find all smaller facets that are 
        // inside the larger facet
        for (int i = 0; i < sorted_facets.size(); ++i) {
            Facet_2D larger_facet = sorted_facets.get(i);
            
            List<Facet_2D> f_list = new ArrayList<>();
            for (int j = i + 1; j < sorted_facets.size(); ++j) {
                Facet_2D smaller_facet = sorted_facets.get(j);
                Bool pt_on_side = new Bool(false);
                if (larger_facet.contains_point(smaller_facet.get_inside_point(), pt_on_side, precision)) {
                    // found facet inside facet
                    f_list.add(smaller_facet);
                }
            }
            
            if (!f_list.isEmpty())
                facets_inside_facets.put(new Facet_2D_Wrapper(larger_facet),f_list);
        }
        
        return pts_on_facet_sides.isEmpty() && too_many_share_side.isEmpty() && facets_inside_facets.isEmpty();
    }
    
    public Iterator<Point_2D> pts_on_side_iterator() { 
        return pts_on_facet_sides.iterator(); 
    }
    
    public boolean pts_on_side_empty() { 
        return pts_on_facet_sides.isEmpty(); 
    }
    
    public int pts_on_side_size() { 
        return pts_on_facet_sides.size(); 
    }
    
    public Iterator<List<Facet_2D>> too_many_share_side_iterator() { 
        return too_many_share_side.iterator(); 
    }
    
    public boolean too_many_share_side_empty() { 
        return too_many_share_side.isEmpty(); 
    }
    
    public int too_many_share_side_size() { 
        return too_many_share_side.size(); 
    }
    
    public Iterator<FacetsInsideFacet> facets_inside_facet_iterator() { 
        return new FacetsInsideFacetIterator(facets_inside_facets);
    }

    public boolean facets_inside_facet_empty() { 
        return facets_inside_facets.isEmpty(); 
    }
    
    public int facets_inside_facet_size() { 
        return facets_inside_facets.size(); 
    }
    
    private class Facet_Side {
        public int point1;
        public int point2;
        public Facet_Side(int p1, int p2) {
            super();
            point1 = p1;
            point2 = p2;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (obj instanceof Facet_Side) {
                Facet_Side side = (Facet_Side) obj;
                return (point1 == side.point1 || point1 == side.point2) && 
                        (point2 == side.point2 || point2 == side.point1);
            } else
                return false;
        }

        @Override
        public int hashCode() {
            return point1 ^ point2;        
        }
    }

    public class FacetsInsideFacet {
        private Facet_2D facet; // facet that contains the other facets
        private List<Facet_2D> facets; // facets that are inside facet
        
        protected FacetsInsideFacet(Facet_2D facet, List<Facet_2D> facets) {
            super();
            
            if (facet == null)
                throw new NullPointerException("facet is null");
            
            if (facets == null)
                throw new NullPointerException("facets is null");
            
            if (facets.isEmpty())
                throw new IllegalArgumentException("facets is empty");
            
            this.facet = facet;
            this.facets = new ArrayList<>();
            this.facets.addAll(facets);
        }
        
        public Facet_2D getFacet() {
            return facet;
        }
        
        public Iterator<Facet_2D> getInsideFacets() {
            return facets.iterator();
        }
        
        public int insideFacetsSize() {
            return facets.size();
        }
    }
    
    private class Facet_2D_Wrapper {
        private Facet_2D facet;
        
        public Facet_2D_Wrapper(Facet_2D facet) {
            super();
            this.facet = facet;
        }
        
        public Facet_2D getFacet() {
            return facet;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (obj instanceof Facet_2D_Wrapper) {
                Facet_2D oFacet = ((Facet_2D_Wrapper) obj).getFacet();
                return (oFacet.get_point1() == facet.get_point1() && oFacet.get_point2() == facet.get_point2() && oFacet.get_point3() == facet.get_point3()) ||
                        (oFacet.get_point2() == facet.get_point1() && oFacet.get_point3() == facet.get_point2() && oFacet.get_point1() == facet.get_point3()) ||
                        (oFacet.get_point3() == facet.get_point1() && oFacet.get_point1() == facet.get_point2() && oFacet.get_point2() == facet.get_point3());
            }
            else
                return false;
        }

        @Override
        public int hashCode() {
            Point_2D pt = facet.get_point1();
            int hash = (31 * Double.hashCode(pt.get_x())) ^ (43 * Double.hashCode(pt.get_y()));
            pt = facet.get_point2();
            hash = hash ^ (31 * Double.hashCode(pt.get_x())) ^ (43 * Double.hashCode(pt.get_y()));
            pt = facet.get_point3();
            hash = hash ^ (31 * Double.hashCode(pt.get_x())) ^ (43 * Double.hashCode(pt.get_y()));
            return hash;
        }
    }
    
    private class FacetsInsideFacetIterator implements Iterator<FacetsInsideFacet> {

        private final Map<Facet_2D_Wrapper,List<Facet_2D>> facetsInsideFacet;
        private final Iterator<Facet_2D_Wrapper> it;
        
        public FacetsInsideFacetIterator(Map<Facet_2D_Wrapper,List<Facet_2D>> facetsInsideFacet) {
            super();
            this.facetsInsideFacet = facetsInsideFacet;
            it = this.facetsInsideFacet.keySet().iterator();
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public FacetsInsideFacet next() {
            Facet_2D_Wrapper fWrapper = it.next();
            List<Facet_2D> facets = facetsInsideFacet.get(fWrapper);
            return new FacetsInsideFacet(fWrapper.getFacet(), facets);
        }
    }
}
