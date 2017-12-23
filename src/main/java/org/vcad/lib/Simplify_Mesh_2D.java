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
 * File: Simplify_Mesh_2D.java
 */
package org.vcad.lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author Jeffrey Davis
 */
public class Simplify_Mesh_2D {
    
    
    public Simplify_Mesh_2D() {
        super();
    }
    
    /*
     * Try to simplify mesh by removing unnecessary points
     */
    public boolean simplify(Mesh_2D mesh) {
        if (mesh.isEmpty())
            return false;
        
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//        cout << "Simplify_Mesh_2D::Operator() begin\n";
//#endif
        // make a copy of mesh and do all operations on it
        Pt_Remover pt_remover = new Pt_Remover(mesh);
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//        int count = 0;
//        for (Mesh_2D::const_point_iterator pt_iter = mesh.point_begin(); pt_iter != mesh.point_end(); ++pt_iter)
//        {
//            cout << "Simplify_Mesh_2D::operator() point #" << count++ << " x: " << (*pt_iter)->get_x() << " y: " << (*pt_iter)->get_y() << "\n";
//        }
//#endif        
        
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//        cout << "Simplify_Mesh_2D::operator() removing internal points\n";
//#endif
        // remove all internal points found
        pt_remover.rem_internal_pts();
        
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//        cout << "Simplify_Mesh_2D::operator() removing perimeter points\n";
//#endif
        pt_remover.rem_perimeter_pts();
        
        Mesh_2D temp_mesh = new Mesh_2D(mesh.get_precision());
        Iterator<Facet> pr_it = pt_remover.iterator();
        while (pr_it.hasNext()) {
            Facet facet = pr_it.next();
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//            cout << "Simplify_Mesh_2D::operator() adding facet p1: " << pr_it->get_p1_index() << " p2: " << pr_it->get_p2_index() << " p3: " << pr_it->get_p3_index() << "\n";
//#endif
            Iterator<Point_2D> pt_it = mesh.point_iterator();
            for (int i = 0; i < facet.get_p1_index(); ++i) {
                if (pt_it.hasNext())
                    pt_it.next();
                else
                    throw new IllegalStateException("Invalid Point Location");
            }
            if (!pt_it.hasNext())
                throw new IllegalStateException("Invalid Point Location");
            Point_2D p1 = pt_it.next();
            pt_it = mesh.point_iterator();
            for (int i = 0; i < facet.get_p2_index(); ++i) {
                if (pt_it.hasNext())
                    pt_it.next();
                else
                    throw new IllegalStateException("Invalid Point Location");
            }
            if (!pt_it.hasNext())
                throw new IllegalStateException("Invalid Point Location");
            Point_2D p2 = pt_it.next();
            pt_it = mesh.point_iterator();
            for (int i = 0; i < facet.get_p3_index(); ++i) {
                if (pt_it.hasNext())
                    pt_it.next();
                else
                    throw new IllegalStateException("Invalid Point Location");
            }
            if (!pt_it.hasNext())
                throw new IllegalStateException("Invalid Point Location");
            Point_2D p3 = pt_it.next();
            temp_mesh.add(new Facet_2D(p1, p2, p3));
        }
        
        if (temp_mesh.size() < mesh.size()) {
            mesh.clear();
            Iterator<Facet_2D> it = temp_mesh.iterator();
            while (it.hasNext())
                mesh.add(it.next());
            return true;
        } else
            return false;
    }
    
    /*
     * Can create new facets as long as new segments generated have a point half way
     * along segment that is inside the facets and does not intersect other internal
     * segments
     * 
     * A perimeter point can only be removed if all of the connecting facets
     * form two planes where the perimeter point is common to both planes.
     */
    private class Pt_Remover implements Iterable<Facet> {
        private final Mesh_2D orig_mesh;
        private final List<Integer> internal_pts; // internal points that can be removed
        private final List<Integer> perimeter_pts; // perimeter points that may or may not be removed
        private final List<Facet> same_plane_facets;

        public Pt_Remover(Mesh_2D mesh) {
            super();
            
            orig_mesh = mesh;
            internal_pts = new ArrayList<>();
            perimeter_pts = new ArrayList<>();
            same_plane_facets = new ArrayList<>();
            
            // make a copy of the facets in the mesh
            Iterator<Facet> it = mesh.facet_iterator();
            while (it.hasNext())
                same_plane_facets.add(it.next());
            // determine internal and perimeter points that can be removed
            Segments segments = new Segments(mesh);
            it = same_plane_facets.iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
                segments.add(facet.get_p1_index(), facet.get_p2_index(), false);
                segments.add(facet.get_p1_index(), facet.get_p3_index(), false);
                segments.add(facet.get_p2_index(), facet.get_p3_index(), false);
            }
            
            segments.find_pts_to_remove(internal_pts, perimeter_pts, same_plane_facets);
        }
        
        @Override
        public Iterator<Facet> iterator() {
            return same_plane_facets.iterator();
        }

        public Iterator<Integer> pt_iterator() {
            return perimeter_pts.iterator();
        }

        public void rem_internal_pts() {
            Iterator<Integer> it = internal_pts.iterator();
            while (it.hasNext()) {
                int internal_pt = it.next();
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                Mesh_2D::const_point_iterator temp_pt_it = orig_mesh->point_begin();
//                advance(temp_pt_it, *it);
//                cout << "Simplify_Mesh_2D::Pt_Remover::rem_internal_pts removing internal point #" << *it << " x: " << (*temp_pt_it)->get_x() << " y: " << (*temp_pt_it)->get_y() << "\n";
//#endif
                // form original facets surrounding internal point and the
                // perimeter segments surrounding the internal point
                List<Facet> orig_facets = new ArrayList<>();
                List<Facet_2D> orig_facet_3ds = new ArrayList<>();
                Segments perimeter_segs = new Segments(orig_mesh);
                Iterator<Facet> f_it = same_plane_facets.iterator();
                while (f_it.hasNext()) {
                    Facet facet = f_it.next();
                    boolean add = false;
                    if (facet.get_p1_index() == internal_pt) {
                        perimeter_segs.add(facet.get_p2_index(), facet.get_p3_index(), false);
                        add = true;
                    } else if (facet.get_p2_index() == internal_pt) {
                        perimeter_segs.add(facet.get_p1_index(), facet.get_p3_index(), false);
                        add = true;
                    } else if (facet.get_p3_index() == internal_pt) {
                        perimeter_segs.add(facet.get_p1_index(), facet.get_p2_index(), false);
                        add = true;
                    }
                    if (add) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                        cout << "Simplify_Mesh_2D::Pt_Remover::rem_internal_pts adding facet p1: " << f_it->get_p1_index() << " p2: " << f_it->get_p2_index() << " p3: " << f_it->get_p3_index() << "\n";
//#endif
                        orig_facets.add(facet);
                        Iterator<Point_2D> p_it = orig_mesh.point_iterator();
                        for (int index = 0; index < facet.get_p1_index(); ++index) {
                            if (p_it.hasNext())
                                p_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!p_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p1 = p_it.next();
                        p_it = orig_mesh.point_iterator();
                        for (int index = 0; index < facet.get_p2_index(); ++index) {
                            if (p_it.hasNext())
                                p_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!p_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p2 = p_it.next();
                        p_it = orig_mesh.point_iterator();
                        for (int index = 0; index < facet.get_p3_index(); ++index) {
                            if (p_it.hasNext())
                                p_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!p_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p3 = p_it.next();
                        orig_facet_3ds.add(new Facet_2D(p1, p2, p3));
                        f_it.remove();//f_it = same_plane_facets.erase(f_it);
                    }
                }
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                cout << "Simplify_Mesh_2D::Pt_Remover::rem_internal_pts forming new facets\n";
//#endif            
                // remove internal point by forming new facets and replacing the original ones in same_plane_facets
                List<Facet> new_facets = new ArrayList<>();
                form_new_facets(orig_facet_3ds, perimeter_segs, new_facets);

                // add newly created facets back to the list of same plane facets
                same_plane_facets.addAll(new_facets);
            }
        }
        
        public void rem_perimeter_pts() {
            Iterator<Integer> ppt_it = perimeter_pts.iterator();
            while (ppt_it.hasNext()) {
                int p_pt = ppt_it.next();
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                Mesh_2D::const_point_iterator temp_pt_it = orig_mesh->point_begin();
//                advance(temp_pt_it, *ppt_it);
//                cout << "Simplify_Mesh_2D::Pt_Remover::rem_perimeter_pts removing perimeter point #" << *ppt_it << " x: " << (*temp_pt_it)->get_x() << " y: " << (*temp_pt_it)->get_y() << "\n";
//#endif
                // form original facets surrounding internal point and the
                // perimeter segments surrounding the perimeter point
                List<Facet> orig_facets = new ArrayList<>();
                List<Facet_2D> orig_facet_2ds = new ArrayList<>();
                Segments perimeter_segs = new Segments(orig_mesh);

                Iterator<Facet> f_it = same_plane_facets.iterator();
                while (f_it.hasNext()) {
                    Facet facet = f_it.next();
                    boolean add = false;
                    if (facet.get_p1_index() == p_pt) {
                        perimeter_segs.add(facet.get_p2_index(), facet.get_p3_index(), false);
                        add = true;
                    } else if (facet.get_p2_index() == p_pt) {
                        perimeter_segs.add(facet.get_p1_index(), facet.get_p3_index(), false);
                        add = true;
                    } else if (facet.get_p3_index() == p_pt) {
                        perimeter_segs.add(facet.get_p1_index(), facet.get_p2_index(), false);
                        add = true;
                    }
                    if (add) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                        cout << "Simplify_Mesh_2D::Pt_Remover::rem_perimeter_pts adding facet p1: " << f_it->get_p1_index() << " p2: " << f_it->get_p2_index() << " p3: " << f_it->get_p3_index() << "\n";
//#endif
                        orig_facets.add(facet);
                        Iterator<Point_2D> p_it = orig_mesh.point_iterator();
                        for (int index = 0; index < facet.get_p1_index(); ++index) {
                            if (p_it.hasNext())
                                p_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!p_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p1 = p_it.next();
                        p_it = orig_mesh.point_iterator();
                        for (int index = 0; index < facet.get_p2_index(); ++index) {
                            if (p_it.hasNext())
                                p_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!p_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p2 = p_it.next();
                        p_it = orig_mesh.point_iterator();
                        for (int index = 0; index < facet.get_p3_index(); ++index) {
                            if (p_it.hasNext())
                                p_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!p_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p3 = p_it.next();
                        orig_facet_2ds.add(new Facet_2D(p1, p2, p3));
                        f_it.remove(); //f_it = same_plane_facets.erase(f_it);
                    }
                }

                // locate segment end points and then form a new segment that connects them
                // this would remove that perimeter point
                Map<Integer,Integer> seg_pts = new HashMap<>();
                for (Iterator<Segment> seg_it = perimeter_segs.iterator(); seg_it.hasNext();) {
                    Segment seg = seg_it.next();
                    if (seg_pts.containsKey(seg.point1))
                        seg_pts.put(seg.point1, seg_pts.get(seg.point1) + 1);
                    else 
                        seg_pts.put(seg.point1, 1);
                    if (seg_pts.containsKey(seg.point2))
                        seg_pts.put(seg.point2, seg_pts.get(seg.point2) + 1);
                    else 
                        seg_pts.put(seg.point2, 1);
                }
                Integer segp1 = null;
                Integer segp2 = null;
                for (Integer key : seg_pts.keySet()) {
                    if (seg_pts.get(key) == 1) {
                        if (segp1 == null)
                            segp1 = key;
                        else if (segp2 == null) {
                            segp2 = key;
                            break;
                        }
                    } 
                }
                // there should be two points left
                if (segp1 == null || segp2 == null)
                    throw new IllegalStateException("Unable to locate segments end points");
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                cout << "Simplify_Mesh_2D::Pt_Remover::rem_perimeter_pts discovered end points #" << *pt_it << " and #" << *(pt_it + 1) << "\n";
//#endif
                perimeter_segs.add(segp1, segp2, false);

//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                cout << "Simplify_Mesh_2D::Pt_Remover::rem_perimeter_pts forming new facets\n";
//#endif
                // remove perimeter point by forming new facets and replacing the original ones in same_plane_facets
                List<Facet> new_facets = new ArrayList<>();
                form_new_facets(orig_facet_2ds, perimeter_segs, new_facets);

                // add newly created facets back to the list of same plane facets
                same_plane_facets.addAll(new_facets);
//#ifdef DEBUG_SIMPLIFY_MESH_2D
//                    cout << "Simplify_Mesh_2D::Pt_Remover::rem_perimeter_pts adding new facet p1: " << nf_it->get_p1_index() << " p2: " << nf_it->get_p2_index() << " p3: " << nf_it->get_p3_index() << "\n";
//#endif
            }
        }

        /*
         * form new facets
         */
        private void form_new_facets(List<Facet_2D> orig_facets, Segments segments, 
                List<Facet> new_facets) {
            
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//            cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets begin\n";
//            cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets Segments:\n";
//            for (Segments::const_iterator it = segments.begin(); it != segments.end(); ++it)
//            {
//                Mesh_2D::const_point_iterator pt_it = orig_mesh->point_begin();
//                advance(pt_it, it->point1);
//                shared_ptr<Point_2D> p1(*pt_it);
//                pt_it = orig_mesh->point_begin();
//                advance(pt_it, it->point2);
//                shared_ptr<Point_2D> p2(*pt_it);
//                cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets (" << it->point1 << 
//                        ", " << it->point2 << ") p1 x: " << p1->get_x() << " y: " << p1->get_y() << 
//                        " p2 x: " << p2->get_x() << " y: " << p2->get_y() << "\n";
//            }
//#endif
            double plane_unv = Vector_2D.cross_product(new Vector_2D(orig_facets.get(0).get_point1(), orig_facets.get(0).get_point2()), 
                    new Vector_2D(orig_facets.get(0).get_point1(), orig_facets.get(0).get_point3()));

            // form new facets
            Segment seg1 = segments.get_next_segment(null);
            while (seg1 != null) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets segment1 p1: " << segment1.point1 << " p2: " << segment1.point2 << "\n";
//#endif
                // get connecting segment
                SharedPoint shared_pt = new SharedPoint();
                Segment seg2 = segments.find_connecting_segment(seg1, null, shared_pt);
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets segment2 p1: " << segment2->point1 << " p2: " << segment2->point2 << "\n";
//#endif
            
                while (seg2 != null) {
                    int seg3_p1 = seg1.point1 == shared_pt.pt ? seg1.point2 : seg1.point1;
                    int seg3_p2 = seg2.point1 == shared_pt.pt ? seg2.point2 : seg2.point1;
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                    cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets formed segment3: (" << seg3_p1 << ", " << seg3_p2 << ")\n";
//#endif
                    if (segments.is_seg_valid(seg3_p1, seg3_p2, orig_facets, orig_mesh.get_precision())) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets seg3 is valid\n";
//#endif
                        Facet facet = new Facet(seg3_p1, shared_pt.pt, seg3_p2);
                        // verify if facet unit normal is pointing in the right direction
                        Iterator<Point_2D> pt_it = orig_mesh.point_iterator();
                        int index = 0;
                        while (index < seg3_p1) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                break;
                            ++index;
                        }
                        if (index < seg3_p1 || !pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p1 = pt_it.next();
                        pt_it = orig_mesh.point_iterator();
                        index = 0;
                        while (index < shared_pt.pt) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                break;
                            ++index;
                        }
                        if (index < shared_pt.pt || !pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p2 = pt_it.next();
                        pt_it = orig_mesh.point_iterator();
                        index = 0;
                        while (index < seg3_p2) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                break;
                            ++index;
                        }
                        if (index < seg3_p2 || !pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_2D p3 = pt_it.next();
                        double unv = Vector_2D.cross_product(new Vector_2D(p1, p2), new Vector_2D(p1, p3));
                        if ((unv < 0 && plane_unv > 0) || (unv > 0 && plane_unv < 0)) // change facet point order
                            facet.invert_unv();
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets Created new facet p1: " << facet.get_p1_index() << " p2: " << facet.get_p2_index() << " p3: " << facet.get_p3_index() << "\n";
//#endif
                        new_facets.add(facet);
                        // process segments used
                        segments.process_segs(seg1, seg2, seg3_p1, seg3_p2);
                        break;
                    } else { // try to find another connecting segment
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::form_new_facets seg3 is not valid\n";
//#endif
                        seg2 = segments.find_connecting_segment(seg1, seg2, shared_pt);
                    }
                }

                // get the next segment1
                boolean found = false;
                Iterator<Segment> it = segments.iterator();
                while (it.hasNext()) {
                    if (it.next() == seg1) {
                        found = true;
                        break;
                    }
                }
                if (found)
                    seg1 = segments.get_next_segment(seg1);
                else
                    seg1 = segments.get_next_segment(null);
            }
        }
        
        private class Segment {
            public boolean is_internal;
            public boolean used;
            public int point1;
            public int point2;

            public Segment(int p1, int p2, boolean internal) {
                super();
                is_internal = internal;
                used = false;
                point1 = p1;
                point2 = p2;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null)
                    return false;
                if (obj instanceof Segment) {
                    Segment seg = (Segment) obj;
                    
                    return (point1 == seg.point1 || point1 == seg.point2) && (point2 == seg.point1 || point2 == seg.point2);
                } else
                    return false;
            }

            @Override
            public int hashCode() {
                int hash = 3;
                hash = 47 * hash + this.point1;
                hash = 47 * hash + this.point2;
                return hash;
            }
            
            public int shares_pt(Segment seg) {
                if (point1 == seg.point1 || point1 == seg.point2)
                    return point1;
                if (point2 == seg.point1 || point2 == seg.point2)
                    return point2;
                return -1;
            }
        }

        private class SharedPoint {
            public int pt;
            public SharedPoint() {
                super();
                pt = -1;
            }
        }
        
        private class Segments implements Iterable<Segment> {
            private Mesh_2D mesh;
            private final List<Segment> segments;
            private final List<Segment> removed_segs;

            public Segments(Mesh_2D mesh) {
                super();
                this.mesh = mesh;
                segments = new ArrayList<>();
                removed_segs = new ArrayList<>();
            }
            
            @Override
            public Iterator<Segment> iterator() {
                return segments.iterator();
            }
            
            /*
             * add a segment
             */
            public void add(int p1, int p2, boolean internal) {
                Segment seg = new Segment(p1,p2,internal);
                // there needs to be duplicates because that is how external segments
                // and internal segments are identified
                segments.add(seg);
                // sort segments so the internal segments move to the front
                segments.sort(new Comparator<Segment>() {
                    @Override
                    public int compare(Segment t, Segment t1) {
                        if (t.is_internal) {
                            if (t1.is_internal)
                                return 0;
                            else
                                return -1;
                        } else {
                            if (t1.is_internal)
                                return 1;
                            else
                                return 0;
                        }
                    }
                });
            }
            
            /*
             * sort segments to locate internal and perimeter points that might
             * be able to be removed
             */
            public void find_pts_to_remove(List<Integer> internal_pts, 
                    List<Integer> perimeter_pts, List<Facet> same_plane_facets) {
                
                List<Segment> internal_segs = new ArrayList<>();
                List<Segment> perimeter_segs = new ArrayList<>();
                List<Integer> all_perimeter_pts = new ArrayList<>();
                // locate perimeter segment points and internal segments
                Iterator<Segment> it = segments.iterator();
                while (it.hasNext()) {
                    Segment seg = it.next();
                    if (internal_segs.contains(seg))
                        continue; // already processed internal segment, so move to next segment
                    // TODO
                    Iterator<Segment> it2 = segments.iterator();
                    while (it2.hasNext()) {
                        if (it2.next() == seg)
                            break;
                    }
                    boolean found = false;
                    while (it2.hasNext()) {
                        if (it2.next().equals(seg)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) // internal segment because it appears twice
                        internal_segs.add(seg);
                    else { // external segment because it is only found once
                        if (!all_perimeter_pts.contains(seg.point1))
                            all_perimeter_pts.add(seg.point1);
                        if (!all_perimeter_pts.contains(seg.point2))
                            all_perimeter_pts.add(seg.point2);
                        perimeter_segs.add(seg);
                    }
                }

                // locate internal points that can be removed
                it = internal_segs.iterator();
                while (it.hasNext()) {
                    Segment seg = it.next();
                    if (!internal_pts.contains(seg.point1) && !all_perimeter_pts.contains(seg.point1))
                        internal_pts.add(seg.point1); // point is internal and can be removed
                    if (!internal_pts.contains(seg.point2) && !all_perimeter_pts.contains(seg.point2))
                        internal_pts.add(seg.point2); // point is internal and can be removed
                }

                // locate external points that might be able to be removed
                Iterator<Integer> pt_it = all_perimeter_pts.iterator();
                while (pt_it.hasNext()) {
                    int pt = pt_it.next();
                    it = perimeter_segs.iterator();
                    Segment seg1 = null;
                    while (it.hasNext()) {
                        Segment seg = it.next();
                        if (seg.point1 == pt || seg.point2 == pt) {
                            seg1 = seg;
                            break;
                        }
                    }
                    Segment seg2 = null;
                    while (it.hasNext()) {
                        Segment seg = it.next();
                        if (seg.point1 == pt || seg.point2 == pt) {
                            seg2 = seg;
                            break;
                        }
                    }
                    if (seg1 == null || seg2 == null)
                        continue;

                    // test if segments are in a straight line

                    int common_index = seg1.shares_pt(seg2);
                    if (common_index == -1)
                        continue;
                    // TODO get actual points
                    Iterator<Point_2D> pt_iter = mesh.point_iterator();
                    int index = 0;
                    while (index < common_index) {
                        if (pt_iter.hasNext())
                            pt_iter.next();
                        else 
                            break;
                        ++index;
                    }
                    if (index != common_index || !pt_iter.hasNext())
                        throw new IllegalStateException("Unable to locate point");
                    Point_2D p2 = pt_iter.next();
                    pt_iter = mesh.point_iterator();
                    index = 0;
                    int pt_val = (common_index == seg1.point1) ? seg1.point2 : seg1.point1;
                    while (index < pt_val) {
                        if (pt_iter.hasNext())
                            pt_iter.next();
                        else 
                            break;
                        ++index;
                    }
                    if (index != pt_val || !pt_iter.hasNext())
                        throw new IllegalStateException("Unable to locate point");
                    Point_2D p1 = pt_iter.next();
                    pt_iter = mesh.point_iterator();
                    index = 0;
                    pt_val = (common_index == seg2.point1) ? seg2.point2 : seg2.point1;
                    while (index < pt_val) {
                        if (pt_iter.hasNext())
                            pt_iter.next();
                        else 
                            break;
                        ++index;
                    }
                    if (index != pt_val || !pt_iter.hasNext())
                        throw new IllegalStateException("Unable to locate point");
                    Point_2D p3 = pt_iter.next();

                    Bool same_direction = new Bool(false);
                    if (Vector_2D.is_same_line(p1, p2, p2, p3, same_direction, mesh.get_precision())) {
                        perimeter_pts.add(pt);
                    }
                }
            }
                    
            /*
             * Get next segment
             */
            public Segment get_next_segment(Segment prev_segment) {
                Iterator<Segment> it = segments.iterator();
                if (prev_segment != null) {
                    while (it.hasNext()) {
                        if (it.next() == prev_segment)
                            break;
                    }
                }
                if (it.hasNext())
                    return it.next();

                return null;
            }
            
            /*
             * find the next connecting segment.  A connecting segment is one
             * which shares a point with the segment
             * 
             * Arguments:
             * segment: the segment to find a connecting segment for
             * prev_connecting_seg: the previous connecting segment found.  Can be zero
             * shared_pt: if a connecting segment is found, function sets this to the 
             *            common point found between the two segments
             * precision: the precision to find the connecting segment
             * 
             * returns a pointer to a connecting segment or zero if none were
             * found.
             */
            public Segment find_connecting_segment(Segment segment, 
                    Segment prev_connecting_seg, SharedPoint shared_pt) {
            
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::find_connecting_segment begin\n";
//#endif
                int shared_point;
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                for (vector<Line_Segment>::const_iterator t_it = segments.begin(); t_it != segments.end(); ++t_it)
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::find_connecting_segment segments p1 x: " << (*t_it).point1->get_x() << " y: " <<
//                            (*t_it).point1->get_y() << " p2 x: " << (*t_it).point2->get_x() << " y: " << (*t_it).point2->get_y() << "\n";
//#endif
                Iterator<Segment> it = segments.iterator();
                if (prev_connecting_seg != null) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::find_conneting_segment locating last connecting segment in segments p1 x: " << 
//                            prev_connecting_seg->point1->get_x() << " y: " << prev_connecting_seg->point1->get_y() << 
//                            " p2 x: " << prev_connecting_seg->point2->get_x() << " y: " << prev_connecting_seg->point2->get_y() << "\n";
//#endif
                    while (it.hasNext()) {
                        if (it.next() == prev_connecting_seg) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                            cout << "Simplify_Mesh_2D::Pt_Remover::Segments::find_connecting_segment found segment\n";
//#endif
                            break;
                        }
                    }
                }
                while (it.hasNext()) {
                    Segment seg = it.next();
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::find_connecting_segment checking segment p1 x: " << 
//                            it->point1->get_x() << " y: " << it->point1->get_y() << " p2 x: " << it->point2->get_x() << 
//                            " y: " << it->point2->get_y() << "\n";
//#endif
                    if (segment == seg) // do not return the same segment
                        continue;

                    shared_point = segment.shares_pt(seg);
                    if (shared_point != -1) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::Segments::find_connecting_segment segment shared pt x: " << 
//                                shared_point->get_x() << " y: " << shared_point->get_y() << "\n";
//#endif
                        int index = (segment.point1 == shared_point) ? segment.point2 : segment.point1;
                        // get points
                        Iterator<Point_2D> pt_it = mesh.point_iterator();
                        int ptIndex = 0;
                        while (ptIndex < index) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                break;
                            ++ptIndex;
                        }
                        if (ptIndex < index || !pt_it.hasNext())
                            throw new IllegalStateException("Invalid point index");
                        Point_2D p1 = pt_it.next();
                        pt_it = mesh.point_iterator();
                        ptIndex = 0;
                        while (ptIndex < shared_point) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                break;
                            ++ptIndex;
                        }
                        if (ptIndex < shared_point || !pt_it.hasNext())
                            throw new IllegalStateException("Invalid point index");
                        Point_2D p2 = pt_it.next();
                        index = (seg.point1 == shared_point) ? seg.point2 : seg.point1;
                        pt_it = mesh.point_iterator();
                        ptIndex = 0;
                        while (ptIndex < index) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                break;
                            ++ptIndex;
                        }
                        if (ptIndex < index || !pt_it.hasNext())
                            throw new IllegalStateException("Invalid point index");
                        Point_2D p3 = pt_it.next();

                        Bool same_direction = new Bool(false);
                        if (Vector_2D.is_same_line(p1, p2, p2, p3, same_direction, mesh.get_precision()))
                            continue;

                        // return segment
                        shared_pt.pt = shared_point;
                        return seg;
                    }
                }

//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::find_connecting_segment end returning 0\n";
//#endif
                return null;
            }
                    
            /*
             * is_segment_valid
             */
            public boolean is_seg_valid(int p1, int p2, List<Facet_2D> orig_facets, 
                    double precision) {
                
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid begin\n";
//#endif
                Segment seg = new Segment(p1,p2,true);

                // 1. is it an existing segment? is it removed already?
                Iterator<Segment> it = segments.iterator();
                while (it.hasNext()) {
                    if (it.next().equals(seg)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid segment is an existing segment: returning true\n";
//#endif
                        return true;
                    }
                }
                it = removed_segs.iterator();
                while (it.hasNext()) {
                    if (it.next().equals(seg)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid segment is an already removed segment: returning false\n";
//#endif
                        return false;
                    }
                }
                
                // 2. does it intersect any existing or removed segments?
                // get segment p1 point value
                Iterator<Point_2D> pt_it = mesh.point_iterator();
                int index = 0;
                while (index < p1) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        break;
                    ++index;
                }
                if (index < p1 || !pt_it.hasNext())
                    throw new IllegalStateException("invalid point index");
                Point_2D p1_pt = pt_it.next();

                // get segment p2 point value
                pt_it = mesh.point_iterator();
                index = 0;
                while (index < p2) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        break;
                    ++index;
                }
                if (index < p2 || !pt_it.hasNext())
                    throw new IllegalStateException("invalid point index");
                Point_2D p2_pt = pt_it.next();
        
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid checking segment (" << 
//                        p1 << ", " << p2 << ") p1 x: " << p1_pt->get_x() << " y: " << p1_pt->get_y() << 
//                        " p2 x: " << p2_pt->get_x() << " y: " << p2_pt->get_y() << "\n";
//#endif
                it = segments.iterator();
                while (it.hasNext()) {
                    Segment currentSeg = it.next();
                    
                    // get segment points
                    pt_it = mesh.point_iterator();
                    index = 0;
                    while (index < currentSeg.point1) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            break;
                        ++index;
                    }
                    if (index < currentSeg.point1 || !pt_it.hasNext())
                        throw new IllegalStateException("invalid point index");
                    Point_2D seg_p1 = pt_it.next();
                    pt_it = mesh.point_iterator();
                    index = 0;
                    while (index < currentSeg.point2) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            break;
                        ++index;
                    }
                    if (index < currentSeg.point2 || !pt_it.hasNext())
                        throw new IllegalStateException("invalid point index");
                    Point_2D seg_p2 = pt_it.next();

//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing against existing segment (" << 
//                            it->point1 << ", " << it->point2 << ") p1 x: " << seg_p1->get_x() << " y: " << seg_p1->get_y() << 
//                            " p2 x: " << seg_p2->get_x() << " y: " << seg_p2->get_y() << "\n";
//#endif
            
                    // does the segment share a point
                    int shared_pt = currentSeg.shares_pt(seg);
                    if (shared_pt != -1) { // shares a common point
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid shares common point: " << shared_pt << "\n";
//#endif
                        if (currentSeg.point1 == shared_pt) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                            cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing seg point1 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_2D.is_pt_on_vector(seg_p2, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing segment point2 is on the main segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        } else {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                            cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing seg point2 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_2D.is_pt_on_vector(seg_p1, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing segment point1 is on the main segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        }
                    } else { // no common point, test if segments intersect
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid no shared points\n";
//#endif
                        Intersection_Data_2D idata = new Intersection_Data_2D();
                        if (Vector_2D.intersect_vectors(p1_pt, p2_pt, seg_p1, seg_p2, idata, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                            cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid segments intersect with no shared points - returning false\n";
//#endif
                            return false; // segments intersect and do not share a point.  
                        }
                    }
                }
                it = removed_segs.iterator();
                while (it.hasNext()) {
                    Segment currentSeg = it.next();
                    // get segment points
                    pt_it = mesh.point_iterator();
                    index = 0;
                    while (index < currentSeg.point1) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            break;
                        ++index;
                    }
                    if (index < currentSeg.point1 || !pt_it.hasNext())
                        throw new IllegalStateException("invalid point index");
                    Point_2D seg_p1 = pt_it.next();
                    pt_it = mesh.point_iterator();
                    index = 0;
                    while (index < currentSeg.point2) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            break;
                        ++index;
                    }
                    if (index < currentSeg.point2 || !pt_it.hasNext())
                        throw new IllegalStateException("invalid point index");
                    Point_2D seg_p2 = pt_it.next();
            
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing against removed segment (" << 
//                            it->point1 << ", " << it->point2 << ") p1 x: " << seg_p1->get_x() << " y: " << seg_p1->get_y() << 
//                            " p2 x: " << seg_p2->get_x() << " y: " << seg_p2->get_y() << "\n";
//#endif
            
                    // does the segment share a point
                    int shared_pt = currentSeg.shares_pt(seg);
                    if (shared_pt != -1) { // shares a common point
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid shares point: " << shared_pt << "\n";
//#endif
                        if (currentSeg.point1 == shared_pt) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                            cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing segment point1 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_2D.is_pt_on_vector(seg_p2, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing segment point2 is on the original segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        } else {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                            cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing segment point2 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_2D.is_pt_on_vector(seg_p1, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid testing segment point1 is on the original segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        }
                    } else { // no common point, test if segments intersect
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                        cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid no shared points\n";
//#endif
                        Intersection_Data_2D idata = new Intersection_Data_2D();
                        if (Vector_2D.intersect_vectors(p1_pt, p2_pt, seg_p1, seg_p2, idata, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                            cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid no shared points, but segments intersect - returning false\n";
//#endif
                            return false; // segments intersect and do not share a point.  
                        }
                    }
                }

                // 3. is a point halfway up segment inside the original facets?
                Vector_2D v = new Vector_2D(p1_pt, p2_pt);
                v.scale(0.5);
                Point_2D half_way_pt = Point_2D.translate(p1_pt, v);
        
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid half way pt x: " << half_way_pt.get_x() << " y: " << half_way_pt.get_y() << "\n";
//#endif
        
                boolean hwp_found = false; // half way point found
                Iterator<Facet_2D> facet_it = orig_facets.iterator();
                while (facet_it.hasNext()) {
                    Bool pt_is_on_side = new Bool(false);
                    if (facet_it.next().contains_point(half_way_pt, pt_is_on_side, precision)) {
                        hwp_found = true;
                        break;
                    }
                }
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                if (hwp_found)
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid original facet does contain half way point\n";
//                else
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::is_seg_valid original facets does not contain half way point\n";
//#endif
                return hwp_found; // segment is valid or not
            }
                    
            /*
             * process segments that have been used
             */
            public void process_segs(Segment seg1, Segment seg2, int seg3_p1, int seg3_p2) {
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                cout << "Simplify_Mesh_2D::Pt_Remover::Segments::process_segs begin\n";
//#endif
                Segment segment3 = new Segment(seg3_p1, seg3_p2, true);

                boolean found = false;
                ListIterator<Segment> seg_it = segments.listIterator();
                while (seg_it.hasNext()) {
                    Segment seg = seg_it.next();
                    if (seg.equals(seg1)) {
                        found = true;
                        if (seg1.is_internal) {
                            if (seg1.used) {
                                removed_segs.add(seg);
                                seg_it.remove();
                            } else
                                seg.used = true;
                        } else { // seg1 is a perimeter segment
                            removed_segs.add(seg);
                            seg_it.remove();
                        }
                        
                        break;
                    }
                }
                if (!found)
                    throw new IllegalStateException("Unable to locate seg1");

                found = false;
                seg_it = segments.listIterator();
                while (seg_it.hasNext()) {
                    Segment seg = seg_it.next();
                    if (seg.equals(seg2)) {
                        found = true;
                        if (seg2.is_internal) {
                            if (seg2.used) {
                                removed_segs.add(seg);
                                seg_it.remove();
                            } else
                                seg.used = true;
                        } else { // seg1 is a perimeter segment
                            removed_segs.add(seg);
                            seg_it.remove();
                        }
                        
                        break;
                    }
                }

                if (!found)
                    throw new IllegalStateException("Unable to locate segment2");

                found = false;
                seg_it = segments.listIterator();
                while (seg_it.hasNext()) {
                    Segment seg = seg_it.next();
                    if (seg.equals(segment3)) {
                        found = true;
                        if (seg.is_internal) {
                            if (seg.used) {
                                removed_segs.add(seg);
                                seg_it.remove();
                            } else
                                seg.used = true;
                        } else {
                            removed_segs.add(seg);
                            seg_it.remove();
                        }
                        break;
                    }
                }
                if (!found) { // segment3 does not exist, so it must be internal
//#ifdef DEBUG_SIMPLIFY_MESH_2D_NEW_FACETS
//                    cout << "Simplify_Mesh_2D::Pt_Remover::Segments::process_segs segment3 is internal. setting used to true\n";
//#endif
                    // set used to true and add internal segment
                    segment3.used = true;
                    segments.add(segment3);
                    segments.sort(new Comparator<Segment>() {
                        @Override
                        public int compare(Segment t, Segment t1) {
                            if (t.is_internal) {
                                if (t1.is_internal)
                                    return 0;
                                else
                                    return -1;
                            } else {
                                if (t1.is_internal)
                                    return 1;
                                else
                                    return 0;
                            }
                        }
                    });
                }
            }
        }
    }
}
