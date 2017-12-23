/*
 * Valid_Mesh_3D.java
 */
package org.vcad.lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jeffrey Davis
 */
public class Valid_Mesh_3D {
    
    private final double precision;
    private final List<Point_3D> all_points; // stores all points from mesh
    private final List<Point_3D> pts_on_facet_sides; // points that are in the middle of a facet side
    private final List<Facet> all_facets; // stores all facets from mesh
    private final List<Facet_3D> edge_facets; // facets that form an edge
    private final List<List<Facet_3D>> too_many_share_side; // more than two facets that share the same side
    // key is the facet that contains the facets in the vector value
    private final Map<Facet_3D_Wrapper,List<Facet_3D>> facets_inside_facets; 
    
    public Valid_Mesh_3D(Mesh_3D mesh) {
        super();
        precision = mesh.get_precision();
        all_points = new ArrayList<>();
        pts_on_facet_sides = new ArrayList<>();
        all_facets = new ArrayList<>();
        edge_facets = new ArrayList<>();
        too_many_share_side = new ArrayList<>();
        facets_inside_facets = new HashMap<>();
        
        for (Iterator<Point_3D> it = mesh.point_iterator(); it.hasNext();)
            all_points.add(it.next());
        for (Iterator<Facet> it = mesh.facet_iterator(); it.hasNext();)
            all_facets.add(it.next());
    }
    
    public Iterator<Point_3D> pts_on_side_iterator() { 
        return pts_on_facet_sides.iterator(); 
    }
    
    public boolean pts_on_side_empty() { 
        return pts_on_facet_sides.isEmpty(); 
    }
    
    public int pts_on_side_size() { 
        return pts_on_facet_sides.size(); 
    }
    
    public Iterator<Facet_3D> edge_facets_iterator() {
        return edge_facets.iterator();
    }
    
    public boolean edge_facets_empty() { 
        return edge_facets.isEmpty(); 
    }
    
    public int edge_facets_size() { 
        return edge_facets.size(); 
    }
    
    public Iterator<List<Facet_3D>> too_many_share_side_iterator() { 
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
    
    private void find_same_plane_facets(List<List<Facet_3D>> sp_facets) {
        List<Facet_3D> facets = new ArrayList<>();
        for (Facet facet : all_facets) {
            facets.add(new Facet_3D(all_points.get(facet.get_p1_index()), all_points.get(facet.get_p2_index()), all_points.get(facet.get_p3_index())));
        }

        while (!facets.isEmpty()) {
            Facet_3D facet = facets.get(0);
            facets.remove(0);
            
            List<Facet_3D> same_plane = new ArrayList<>();
            same_plane.add(facet);
            ListIterator<Facet_3D> it2 = facets.listIterator();
            while (it2.hasNext()) {
                Facet_3D facet2 = it2.next();
                if (Vector_3D.is_equal(facet2.get_unv(), facet.get_unv(), precision)) {
                    same_plane.add(facet2);
                    it2.remove();
                }
            }

            sp_facets.add(same_plane);
        }
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
            if (sides.containsKey(side))
                sides.put(side, sides.get(side) + 1);
            else
                sides.put(side, 1);

            // p1p3
            side = new Facet_Side(facet.get_p1_index(), facet.get_p3_index());
            if (sides.containsKey(side))
                sides.put(side,sides.get(side) + 1);
            else
                sides.put(side, 1);
            
            // p2p3
            side = new Facet_Side(facet.get_p2_index(), facet.get_p3_index());
            if (sides.containsKey(side))
                sides.put(side,sides.get(side) + 1);
            else
                sides.put(side, 1);
        }
        
        // now take each unique side and look for points that are on the side
        // but are not end points
        for (Facet_Side side : sides.keySet()) {
            if (sides.get(side) == 1) {
                for (Facet facet : all_facets) {
                    int count = 0;
                    if (facet.get_p1_index() == side.point1 || facet.get_p1_index() == side.point2)
                        ++count;
                    if (facet.get_p2_index() == side.point1 || facet.get_p2_index() == side.point2)
                        ++count;
                    if (facet.get_p3_index() == side.point1 || facet.get_p3_index() == side.point2)
                        ++count;
                    if (count == 2) {
                        Facet_3D edge_facet = new Facet_3D(all_points.get(facet.get_p1_index()), all_points.get(facet.get_p2_index()), all_points.get(facet.get_p3_index()));
                        boolean found = false;
                        for (Facet_3D facet_3d : edge_facets) {
                            if (edge_facet.get_point1() == facet_3d.get_point1() && 
                                    edge_facet.get_point2() == facet_3d.get_point2() && 
                                    edge_facet.get_point3() == facet_3d.get_point3()) {
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            edge_facets.add(edge_facet);
                        break;
                    }
                }
            } else if (sides.get(side) > 2) {
                List<Facet_3D> facets = new ArrayList<>();
                for (Facet facet : all_facets) {
                    int count = 0;
                    if (facet.get_p1_index() == side.point1 || facet.get_p1_index() == side.point2)
                        ++count;
                    if (facet.get_p2_index() == side.point1 || facet.get_p2_index() == side.point2)
                        ++count;
                    if (facet.get_p3_index() == side.point1 || facet.get_p3_index() == side.point2)
                        ++count;
                    if (count == 2)
                        facets.add(new Facet_3D(all_points.get(facet.get_p1_index()), all_points.get(facet.get_p2_index()), all_points.get(facet.get_p3_index())));
                }
                
                too_many_share_side.add(facets);
            }
            
            Point_3D p1 = all_points.get(side.point1);
            Point_3D p2 = all_points.get(side.point2);
            
            for (Integer pt : points) {
                // go to next side if point is an end point
                if (side.point1 == pt || side.point2 == pt)
                    continue;

                Point_3D point = all_points.get(pt);
                
                boolean found = false;
                for (Point_3D fs_pt : pts_on_facet_sides) {
                    if (fs_pt == point) {
                        found = true;
                        break;
                    }
                }
                if (Vector_3D.is_pt_on_vector(point, p1, p2, precision) && !found)
                    pts_on_facet_sides.add(point);
            }
        }
        
        // create a vector of facets of the same plane and then sort them largest to smallest
        List<List<Facet_3D>> sp_facets = new ArrayList<>();
        find_same_plane_facets(sp_facets);
        for (List<Facet_3D> spFacets : sp_facets) {
            spFacets.sort(new Comparator<Facet_3D>() {
                private double area(Facet_3D facet) {

                    Vector_3D p1p2 = new Vector_3D(facet.get_point1(), facet.get_point2());
                    Vector_3D p1p3 = new Vector_3D(facet.get_point1(), facet.get_point3());
                    Vector_3D p2p3 = new Vector_3D(facet.get_point2(), facet.get_point3());

                    if (p1p2.length() >= p1p3.length() && p1p2.length() > p2p3.length()) {
                        // p1p2 is the largest side
                        if (p1p3.length() > p2p3.length()) {
                            // p1p3 is the next largest
                            return Math.abs(0.5 * Vector_3D.cross_product(p1p2, p1p3).length());
                        } else {
                            // p2p3 is the next largest
                            return Math.abs(0.5 * Vector_3D.cross_product(p1p2.minus(), p2p3).length());
                        }
                    } else if (p1p3.length() >= p1p2.length() && p1p3.length() >= p2p3.length()) {
                        // p1p3 is the largest side
                        if (p1p2.length() > p2p3.length()) {
                            // p1p2 is the next largest
                            return Math.abs(0.5 * Vector_3D.cross_product(p1p3, p1p2).length());
                        } else {
                            // p2p3 is the next largest
                            return Math.abs(0.5 * Vector_3D.cross_product(p1p3.minus(), p2p3.minus()).length());
                        }
                    } else {
                        // p2p3 is the largest side
                        if (p1p2.length() > p1p3.length()) {
                            // p1p2 is the next largest
                            return Math.abs(0.5 * Vector_3D.cross_product(p2p3, p1p2.minus()).length());
                        } else {
                            // p1p3 is the next largest
                            return Math.abs(0.5 * Vector_3D.cross_product(p2p3.minus(), p1p3.minus()).length());
                        }
                    }
                }
                
                @Override
                public int compare(Facet_3D f1, Facet_3D f2) {
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
            for (int i = 0; i < spFacets.size(); ++i) {
                Facet_3D larger_facet = spFacets.get(i);
                List<Facet_3D> f_list = new ArrayList<>();
                for (int j = i + 1; j < spFacets.size(); ++j) {
                    Facet_3D smaller_facet = spFacets.get(j);
                    Bool pt_on_side = new Bool(false);
                    if (larger_facet.contains_point(smaller_facet.get_inside_point(), pt_on_side, precision)) {
                        // found facet inside facet
                        f_list.add(smaller_facet);
                    }
                }

                if (!f_list.isEmpty())
                    facets_inside_facets.put(new Facet_3D_Wrapper(larger_facet),f_list);
            }
        }
        
        return pts_on_facet_sides.isEmpty() && edge_facets.isEmpty() && too_many_share_side.isEmpty() && facets_inside_facets.isEmpty();
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
        private Facet_3D facet; // facet that contains the other facets
        private List<Facet_3D> facets; // facets that are inside facet
        
        protected FacetsInsideFacet(Facet_3D facet, List<Facet_3D> facets) {
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
        
        public Facet_3D getFacet() {
            return facet;
        }
        
        public Iterator<Facet_3D> getInsideFacets() {
            return facets.iterator();
        }
        
        public int insideFacetsSize() {
            return facets.size();
        }
    }
    
    private class Facet_3D_Wrapper {
        private Facet_3D facet;
        
        public Facet_3D_Wrapper(Facet_3D facet) {
            super();
            this.facet = facet;
        }
        
        public Facet_3D getFacet() {
            return facet;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (obj instanceof Facet_3D_Wrapper) {
                Facet_3D oFacet = ((Facet_3D_Wrapper) obj).getFacet();
                return (oFacet.get_point1() == facet.get_point1() && oFacet.get_point2() == facet.get_point2() && oFacet.get_point3() == facet.get_point3()) ||
                        (oFacet.get_point2() == facet.get_point1() && oFacet.get_point3() == facet.get_point2() && oFacet.get_point1() == facet.get_point3()) ||
                        (oFacet.get_point3() == facet.get_point1() && oFacet.get_point1() == facet.get_point2() && oFacet.get_point2() == facet.get_point3());
            }
            else
                return false;
        }

        @Override
        public int hashCode() {
            Point_3D pt = facet.get_point1();
            int hash = (31 * Double.hashCode(pt.get_x())) ^ (43 * Double.hashCode(pt.get_y())) ^ (23 * Double.hashCode(pt.get_z()));
            pt = facet.get_point2();
            hash = hash ^ (31 * Double.hashCode(pt.get_x())) ^ (43 * Double.hashCode(pt.get_y())) ^ (23 * Double.hashCode(pt.get_z()));
            pt = facet.get_point3();
            hash = hash ^ (31 * Double.hashCode(pt.get_x())) ^ (43 * Double.hashCode(pt.get_y())) ^ (23 * Double.hashCode(pt.get_z()));
            return hash;
        }
    }
    
    private class FacetsInsideFacetIterator implements Iterator<FacetsInsideFacet> {

        private final Map<Facet_3D_Wrapper,List<Facet_3D>> facetsInsideFacet;
        private final Iterator<Facet_3D_Wrapper> it;
        
        public FacetsInsideFacetIterator(Map<Facet_3D_Wrapper,List<Facet_3D>> facetsInsideFacet) {
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
            Facet_3D_Wrapper fWrapper = it.next();
            List<Facet_3D> facets = facetsInsideFacet.get(fWrapper);
            return new FacetsInsideFacet(fWrapper.getFacet(), facets);
        }
    }
}
