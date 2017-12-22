/*
 * Simplify_Mesh_3D.java
 */
package org.vcad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Jeffrey Davis
 */
public class Simplify_Mesh_3D {
    
    public Simplify_Mesh_3D() {
        super();
    }
    
    public boolean simplify(Mesh_3D mesh) {
        if (mesh.isEmpty())
            return false;
        
//        System.out.println("Simplify_Mesh_3D::simplify");
//        int count = 0;
//        for (Iterator<Point_3D> pt_iter = mesh.point_iterator(); pt_iter.hasNext();) {
//            Point_3D pt = pt_iter.next();
//            System.out.println("Simplify_Mesh_3D::simplify mesh point #" + count++ + " x: " + pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//        }

        // make a copy of mesh and do all operations on it
        Facet_Datas facet_datas = new Facet_Datas();
        
        facet_datas.process_mesh(mesh);
        
//        System.out.println("Simplify_Mesh_3D::simplify removing internal points");

        // remove all internal points found
        for (Iterator<Pt_Remover> it = facet_datas.iterator(); it.hasNext();) {
            Pt_Remover pt_remover = it.next();
            pt_remover.rem_internal_pts();
        }
        
//        System.out.println("Simplify_Mesh_3D::simplify removing perimeter points");

        // check and remove external points if possible
        List<Integer> perimeter_pts_removed = new ArrayList<>();
        for (Iterator<Pt_Remover> it = facet_datas.iterator(); it.hasNext();) {
//            System.out.println("Simplify_Mesh_3D::simplify processing facet_data");

            Pt_Remover pt_Remover = it.next();
            for (Iterator<Integer> pp_it = pt_Remover.pt_iterator(); pp_it.hasNext();) {
                int ppt = pp_it.next();
                
//                System.out.println("Simplify_Mesh_3D::simplify testing if perimeter point can be removed: " + ppt);

                if (!perimeter_pts_removed.contains(ppt)) {
//                    System.out.println("Simplify_Mesh_3D::simplify perimeter point has not been processed previously");

                    // perimeter point has not been processed
                    List<Pt_Remover> pt_removers = new ArrayList<>();
                    boolean found_npp = false; // found, but not a perimeter point
                    pt_removers.add(pt_Remover);
                    
                    for (Iterator<Pt_Remover> it2 = facet_datas.iterator(); it2.hasNext();) {
                        Pt_Remover pt_Remover2 = it2.next();
                        if (pt_Remover2 == pt_Remover) // don't process the same facet_data
                            continue;
                        
                        boolean found = false;
                        for (Iterator<Integer> pp_it2 = pt_Remover2.pt_iterator(); pp_it2.hasNext();) {
                            int ppt2 = pp_it2.next();
                            if (ppt2 == ppt) {
                                found = true;
                                break;
                            }
                        }
                        if (found) {
//                            System.out.println("Simplify_Mesh_3D::simplify found another pt_remover with the same perimeter point");

                            pt_removers.add(pt_Remover2);
                        } else {
                            for (Iterator<Facet> facet_it = pt_Remover2.iterator(); facet_it.hasNext();) {
                                Facet f = facet_it.next();
                                if (f.get_p1_index() == ppt) {
                                    found_npp = true;
                                    break;
                                }
                                if (f.get_p2_index() == ppt) {
                                    found_npp = true;
                                    break;
                                }
                                if (f.get_p3_index() == ppt) {
                                    found_npp = true;
                                    break;
                                }
                            }
                        }
                    }
                    
                    if (found_npp) { // cannot remove point because there are multiple planes
//                        System.out.println("Simplify_Mesh_3D::simplify another Pt_Remover contains the perimeter point, but not as a perimeter point that can be removed");
                        continue;
                    }
                    if (pt_removers.size() <= 2) { // if it is two planes remove, if it is on one, also remove.  There are no connecting facets
//                        System.out.println("Simplify_Mesh_3D::simplify removing perimeter point");

                        // can remove point
                        for (Iterator<Pt_Remover> pt_rem_it = pt_removers.iterator(); pt_rem_it.hasNext();)
                            pt_rem_it.next().rem_perimeter_pt(ppt);
                        perimeter_pts_removed.add(ppt);
                    }
//                    else
//                        System.out.println("Simplify_Mesh_3D::simplify cannot remove perimeter point because it is common to more than two planes: " + pt_removers.size());
                }
//                else
//                    System.out.println("Simplify_Mesh_3D::operator() perimeter point has already been processed");
            }
        }
        
//        System.out.println("Simplify_Mesh_3D::simplify forming simplified mesh");

        Mesh_3D temp_mesh = new Mesh_3D(mesh.get_precision());
        for (Iterator<Pt_Remover> it = facet_datas.iterator(); it.hasNext();) {
//            System.out.println("Simplify_Mesh_3D::simplify processing pt_remover");
            Pt_Remover pt_remover = it.next();
            for (Iterator<Facet> pr_it = pt_remover.iterator(); pr_it.hasNext();) {
                Facet facet = pr_it.next();

//                System.out.println("Simplify_Mesh_3D::simplify adding facet p1: " + 
//                        facet.get_p1_index() + " p2: " + facet.get_p2_index() + " p3: " + 
//                        facet.get_p3_index());

                Iterator<Point_3D> pt_it = mesh.point_iterator();
                for (int i = 0; i < facet.get_p1_index(); ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                Point_3D p1 = pt_it.next();
                pt_it = mesh.point_iterator();
                for (int i = 0; i < facet.get_p2_index(); ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                Point_3D p2 = pt_it.next();
                pt_it = mesh.point_iterator();
                for (int i = 0; i < facet.get_p3_index(); ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                Point_3D p3 = pt_it.next();
                temp_mesh.add(new Facet_3D(p1, p2, p3));
            }
        }
        
        if (temp_mesh.size() < mesh.size()) {
//            System.out.println("Simplify_Mesh_3D::simplify mesh was simplified. updating mesh and returning true " + temp_mesh.size() + " " + mesh.size());

            mesh.clear();
            for (Iterator<Facet_3D> it = temp_mesh.iterator(); it.hasNext();)
                mesh.add(it.next());
            return true;
        } else {
//            System.out.println("Simplify_Mesh_3D::operator() mesh was not simplified. returning false");

            return false;
        }
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
        private final Mesh_3D orig_mesh;
        private final List<Integer> internal_pts; // internal points that can be removed
        private final List<Integer> perimeter_pts; // perimeter points that may or may not be removed
        private final List<Facet> same_plane_facets;
        
        public Pt_Remover(List<Facet> f_data, Mesh_3D mesh) {
            super();    
            orig_mesh = mesh;
            internal_pts = new ArrayList<>();
            perimeter_pts = new ArrayList<>();
            same_plane_facets = new ArrayList<>();
            same_plane_facets.addAll(f_data);
            
            // determine internal and perimeter points that can be removed
            // Note: some perimeter points found may not be able to be removed
            //       a perimeter point can only be removed if it is found on the edge
            //       of two planes.  If more than two planes, then the point should
            //       remain in the mesh.
            Segments segments = new Segments(mesh);
            for (Iterator<Facet> it = same_plane_facets.iterator(); it.hasNext();) {
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
                int internalPt = it.next();

//                System.out.println("Simplify_Mesh_3D::Pt_Remover::rem_internal_pts removing internal point #" + internalPt);

                // form original facets surrounding internal point and the
                // perimeter segments surrounding the internal point
                List<Facet> orig_facets = new ArrayList<>();
                List<Facet_3D> orig_facet_3ds = new ArrayList<>();
                Segments perimeter_segs = new Segments(orig_mesh);
                Iterator<Facet> f_it = same_plane_facets.iterator();
                while (f_it.hasNext()) {
                    Facet facet = f_it.next();
                    boolean add = false;
                    if (facet.get_p1_index() == internalPt) {
                        perimeter_segs.add(facet.get_p2_index(), facet.get_p3_index(), false);
                        add = true;
                    } else if (facet.get_p2_index() == internalPt) {
                        perimeter_segs.add(facet.get_p1_index(), facet.get_p3_index(), false);
                        add = true;
                    } else if (facet.get_p3_index() == internalPt) {
                        perimeter_segs.add(facet.get_p1_index(), facet.get_p2_index(), false);
                        add = true;
                    }
                    if (add) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//                        cout << "Simplify_Mesh_3D::Pt_Remover::rem_internal_pts adding facet p1: " << f_it->get_p1_index() << " p2: " << f_it->get_p2_index() << " p3: " << f_it->get_p3_index() << "\n";
//#endif
                        orig_facets.add(facet);
                        Iterator<Point_3D> pt_it = orig_mesh.point_iterator();
                        for (int i = 0; i < facet.get_p1_index(); ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p1 = pt_it.next();
                        pt_it = orig_mesh.point_iterator();
                        for (int i = 0; i < facet.get_p2_index(); ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p2 = pt_it.next();
                        pt_it = orig_mesh.point_iterator();
                        for (int i = 0; i < facet.get_p3_index(); ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p3 = pt_it.next();
                        orig_facet_3ds.add(new Facet_3D(p1, p2, p3));
                        f_it.remove();
                    }
                }
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//                cout << "Simplify_Mesh_3D::Pt_Remover::rem_internal_pts forming new facets\n";
//#endif            
            
                // remove internal point by forming new facets and replacing the original ones in same_plane_facets
                List<Facet> new_facets = new ArrayList<>();
                form_new_facets(orig_facet_3ds, perimeter_segs, new_facets);

                // add newly created facets back to the list of same plane facets
                same_plane_facets.addAll(new_facets);
            }
        }

        public void rem_perimeter_pt(int pt) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//            Mesh_3D::const_point_iterator temp_pt_it = orig_mesh->point_begin();
//            advance(temp_pt_it, pt);
//            cout << "Simplify_Mesh_3D::Pt_Remover::rem_perimeter_pt removing perimeter point #" << pt << " x: " << (*temp_pt_it)->get_x() << " y: " << (*temp_pt_it)->get_y() << "\n";
//#endif
            // form original facets surrounding internal point and the
            // perimeter segments surrounding the perimeter point
            List<Facet> orig_facets = new ArrayList<>();
            List<Facet_3D> orig_facet_3ds = new ArrayList<>();
            Segments perimeter_segs = new Segments(orig_mesh);

            Iterator<Facet> f_it = same_plane_facets.iterator();
            while (f_it.hasNext()) {
                Facet facet = f_it.next();
                boolean add = false;
                if (facet.get_p1_index() == pt) {
                    perimeter_segs.add(facet.get_p2_index(), facet.get_p3_index(), false);
                    add = true;
                } else if (facet.get_p2_index() == pt) {
                    perimeter_segs.add(facet.get_p1_index(), facet.get_p3_index(), false);
                    add = true;
                } else if (facet.get_p3_index() == pt) {
                    perimeter_segs.add(facet.get_p1_index(), facet.get_p2_index(), false);
                    add = true;
                }
                if (add) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//                    cout << "Simplify_Mesh_3D::Pt_Remover::rem_perimeter_pt adding facet p1: " << f_it->get_p1_index() << " p2: " << f_it->get_p2_index() << " p3: " << f_it->get_p3_index() << "\n";
//#endif
                    orig_facets.add(facet);
                    Iterator<Point_3D> it = orig_mesh.point_iterator();
                    for (int i = 0; i < facet.get_p1_index(); ++i) {
                        if (it.hasNext())
                            it.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!it.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D p1 = it.next();
                    it = orig_mesh.point_iterator();
                    for (int i = 0; i < facet.get_p2_index(); ++i) {
                        if (it.hasNext())
                            it.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!it.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D p2 = it.next();
                    it = orig_mesh.point_iterator();
                    for (int i = 0; i < facet.get_p3_index(); ++i) {
                        if (it.hasNext())
                            it.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!it.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D p3 = it.next();
                    orig_facet_3ds.add(new Facet_3D(p1, p2, p3));
                    f_it.remove();
                }
            }

            // locate segment end points and then form a new segment that connects them
            // this would remove that perimeter point
            List<Integer> seg_pts = new ArrayList<>();
            Iterator<Segment> seg_it = perimeter_segs.iterator();
            while (seg_it.hasNext()) {
                Segment seg = seg_it.next();
                seg_pts.add(seg.point1);
                seg_pts.add(seg.point2);
            }
            int index = 0;
            while (index < seg_pts.size()) {
                int current_pt = seg_pts.get(index);
                int index2 = seg_pts.lastIndexOf(current_pt);
                if (index2 == index) {
                    // only one point instance, so leave and move to the next
                    ++index;
                } else {
                    // two indexes, so remove both
                    seg_pts.remove(index2);
                    seg_pts.remove(index);
                }
            }
            // there should be two points left
            if (seg_pts.size() != 2)
                throw new IllegalStateException("Invalid number of segment end points found: " + seg_pts.size());
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//            cout << "Simplify_Mesh_3D::Pt_Remover::rem_perimeter_ps discovered end points #" << *pt_it << " and #" << *(pt_it + 1) << "\n";
//#endif
            perimeter_segs.add(seg_pts.get(0), seg_pts.get(1), false);
        
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//            cout << "Simplify_Mesh_3D::Pt_Remover::rem_perimeter_pt forming new facets\n";
//#endif
        
            // remove perimeter point by forming new facets and replacing the original ones in same_plane_facets
            List<Facet> new_facets = new ArrayList<>();
            form_new_facets(orig_facet_3ds, perimeter_segs, new_facets);

            // add newly created facets back to the list of same plane facets
            same_plane_facets.addAll(new_facets);
        }

        /*
         * form new facets
         */
        private void form_new_facets(List<Facet_3D> orig_facets, Segments segments, 
                List<Facet> new_facets) {
            
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//            cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets begin\n";
//            cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets Segments:\n";
//            for (Segments::const_iterator it = segments.begin(); it != segments.end(); ++it)
//            {
//                Mesh_3D::const_point_iterator pt_it = orig_mesh->point_begin();
//                advance(pt_it, it->point1);
//                shared_ptr<Point_3D> p1(*pt_it);
//                pt_it = orig_mesh->point_begin();
//                advance(pt_it, it->point2);
//                shared_ptr<Point_3D> p2(*pt_it);
//                cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets (" << it->point1 << 
//                        ", " << it->point2 << ") p1 x: " << p1->get_x() << " y: " << p1->get_y() << 
//                        " z: " << p1->get_z() << " p2 x: " << p2->get_x() << " y: " << p2->get_y() << 
//                        " z: " << p2->get_z() << "\n";
//            }
//#endif
            Vector_3D plane_unv = orig_facets.get(0).get_unv();

            // form new facets
            Segment segment1 = segments.get_next_segment(null);
            while (segment1 != null) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets segment1 p1: " << segment1.point1 << " p2: " << segment1.point2 << "\n";
//#endif
                // get connecting segment
                SharedPoint shared_pt = new SharedPoint();
                Segment segment2 = segments.find_connecting_segment(segment1, null, shared_pt, orig_mesh.get_precision());
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets segment2 p1: " << segment2->point1 << " p2: " << segment2->point2 << "\n";
//#endif
            
                while (segment2 != null) {
                    int seg3_p1 = segment1.point1 == shared_pt.pt ? segment1.point2 : segment1.point1;
                    int seg3_p2 = segment2.point1 == shared_pt.pt ? segment2.point2 : segment2.point1;
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets segment3 p1: " << seg3_p1 << " p2: " << seg3_p2 << "\n";
//#endif
                    if (segments.is_seg_valid(seg3_p1, seg3_p2, orig_facets, orig_mesh.get_precision())) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets seg3 is valid\n";
//#endif
                        Facet facet = new Facet(seg3_p1, shared_pt.pt, seg3_p2);
                        // verify if facet unit normal is pointing in the right direction
                        Iterator<Point_3D> pt_it = orig_mesh.point_iterator();
                        for (int i = 0; i < seg3_p1; ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p1 = pt_it.next();
                        pt_it = orig_mesh.point_iterator();
                        for (int i = 0; i < shared_pt.pt; ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p2 = pt_it.next();
                        pt_it = orig_mesh.point_iterator();
                        for (int i = 0; i < seg3_p2; ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p3 = pt_it.next();
                        Vector_3D unv = Vector_3D.cross_product(new Vector_3D(p1, p2), new Vector_3D(p1, p3));
                        if (Vector_3D.dot_product(unv, plane_unv) < 0) // change facet point order
                            facet.invert_unv();
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets Created new facet p1: " << facet.get_p1_index() << " p2: " << facet.get_p2_index() << " p3: " << facet.get_p3_index() << "\n";
//#endif
                        new_facets.add(facet);
                        // process segments used
                        segments.process_segs(segment1, segment2, seg3_p1, seg3_p2);
                        break;
                    } else { // try to find another connecting segment
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::form_new_facets seg3 is not valid\n";
//#endif
                        segment2 = segments.find_connecting_segment(segment1, segment2, shared_pt, orig_mesh.get_precision());
                    }
                }

                // get the next segment1
                boolean seg1_exists = false;
                Iterator<Segment> it = segments.iterator();
                while (it.hasNext()) {
                    if (it.next() == segment1) {
                        seg1_exists = true;
                        break;
                    }
                }
                if (seg1_exists)
                    segment1 = segments.get_next_segment(segment1);
                else
                    segment1 = segments.get_next_segment(null);
            }
        }
        
        private class Segment {
            public boolean is_internal;
            public boolean used;
            public int point1;
            public int point2;

            public Segment(int p1, int p2, boolean internal) {
                super();
                point1 = p1;
                point2 = p2;
                is_internal = internal;
                used = false;
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
                int hash = 7;
                hash = 67 * hash + this.point1;
                hash = 67 * hash + this.point2;
                return hash;
            }

            public int shares_pt(Segment seg) {
                if (point1 == seg.point1 || point1 == seg.point2)
                    return point1;
                if (point2 == seg.point1 || point2 == seg.point2)
                    return point2;
                return -1;
            }
        };

        private class SharedPoint {
            public int pt;
            public SharedPoint() {
                super();
                pt = -1;
            }
        }
        
        private class Segments implements Iterable<Segment> {
            
            private final Mesh_3D mesh;
            private final List<Segment> segments;
            private final List<Segment> removed_segs;

            public Segments(Mesh_3D mesh) {
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
                    Iterator<Segment> it2 = segments.iterator();
                    while (it2.hasNext()) {
                        if (it2.next() == seg)
                            break;
                    }
                    boolean found = false;
                    while (it2.hasNext()) {
                        if (seg.equals(it2.next())) {
                            found = true;
                            break;
                        }
                    }
                    if (found) // internal segment because it appears twice
                        internal_segs.add(seg);
                    else {
                        // external segment because it is only found once
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
                    if ((!internal_pts.contains(seg.point1)) && 
                            (!all_perimeter_pts.contains(seg.point1)))
                        internal_pts.add(seg.point1); // point is internal and can be removed
                    if ((!internal_pts.contains(seg.point2)) && 
                            (!all_perimeter_pts.contains(seg.point2)))
                        internal_pts.add(seg.point2); // point is internal and can be removed
                }

                // First, check if the point is in the middle of a straight segment
                // second, check if a continuous path around the perimeter pt can be formed
                // if so, then it is a valid point that might be removed
                // if not, then don't add it
                // locate external points that might be able to be removed
                Iterator<Integer> pt_it = all_perimeter_pts.iterator();
                while (pt_it.hasNext()) {
                    int p_pt = pt_it.next();
                    // find two perimeter segments that share the same point
                    Segment segment1 = null;
                    Segment segment2 = null;
                    it = perimeter_segs.iterator();
                    while (it.hasNext()) {
                        Segment seg = it.next();
                        if (seg.point1 == p_pt || seg.point2 == p_pt) {
                            if (segment1 == null)
                                segment1 = seg;
                            else {
                                segment2 = seg;
                                break;
                            }
                        }
                    }
                    if (segment1 == null || segment2 == null)
                        continue;

                    // test if segments are in a straight line

                    int common_index = segment1.shares_pt(segment2);
                    if (common_index == -1)
                        continue;
                    
                    Iterator<Point_3D> pt_iter = mesh.point_iterator();
                    for (int i = 0; i < common_index; ++i) {
                        if (pt_iter.hasNext())
                            pt_iter.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!pt_iter.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D p2 = pt_iter.next();
                    pt_iter = mesh.point_iterator();
                    int index = (common_index == segment1.point1) ? segment1.point2 : segment1.point1;
                    for (int i = 0; i < index; ++i) {
                        if (pt_iter.hasNext())
                            pt_iter.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!pt_iter.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D p1 = pt_iter.next();
                    pt_iter = mesh.point_iterator();
                    index = (common_index == segment2.point1) ? segment2.point2 : segment2.point1;
                    for (int i = 0; i < index; ++i) {
                        if (pt_iter.hasNext())
                            pt_iter.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!pt_iter.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D p3 = pt_iter.next();

                    Bool same_direction = new Bool(false);
                    if (Vector_3D.is_same_line(p1, p2, p2, p3, same_direction, mesh.get_precision())) {
                        // check if the segments surrounding this point are on the same plane
                        List<Segment> segs = new ArrayList<>();
                        int starting_pt = -1;
                        int ending_pt = -1;
                        Iterator<Facet> sp_it = same_plane_facets.iterator();
                        while (sp_it.hasNext()) {
                            Facet facet = sp_it.next();
                            if (facet.get_p1_index() == common_index) {
                                Segment seg1 = new Segment(facet.get_p1_index(), facet.get_p2_index(), false);
                                Segment seg2 = new Segment(facet.get_p1_index(), facet.get_p3_index(), false);
                                if (seg1.equals(segment1))
                                    starting_pt = facet.get_p3_index();
                                else if (seg1.equals(segment2))
                                    ending_pt = facet.get_p3_index();
                                else if (seg2.equals(segment1))
                                    starting_pt = facet.get_p2_index();
                                else if (seg2.equals(segment2))
                                    ending_pt = facet.get_p2_index();
                                else
                                    segs.add(new Segment(facet.get_p2_index(), facet.get_p3_index(), false));
                            } else if (facet.get_p2_index() == common_index) {
                                Segment seg1 = new Segment(facet.get_p2_index(), facet.get_p1_index(), false);
                                Segment seg2 = new Segment(facet.get_p2_index(), facet.get_p3_index(), false);
                                if (seg1.equals(segment1))
                                    starting_pt = facet.get_p3_index();
                                else if (seg1.equals(segment2))
                                    ending_pt = facet.get_p3_index();
                                else if (seg2.equals(segment1))
                                    starting_pt = facet.get_p1_index();
                                else if (seg2.equals(segment2))
                                    ending_pt = facet.get_p1_index();
                                else
                                    segs.add(new Segment(facet.get_p1_index(), facet.get_p3_index(), false));
                            } else if (facet.get_p3_index() == common_index) {
                                Segment seg1 = new Segment(facet.get_p3_index(), facet.get_p1_index(), false);
                                Segment seg2 = new Segment(facet.get_p3_index(), facet.get_p2_index(), false);
                                if (seg1.equals(segment1))
                                    starting_pt = facet.get_p2_index();
                                else if (seg1.equals(segment2))
                                    ending_pt = facet.get_p2_index();
                                else if (seg2.equals(segment1))
                                    starting_pt = facet.get_p1_index();
                                else if (seg2.equals(segment2))
                                    ending_pt = facet.get_p1_index();
                                else
                                    segs.add(new Segment(facet.get_p1_index(), facet.get_p2_index(), false));
                            }
                        }

                        // start with starting point and go around
                        boolean found;
                        do {
                            found = false;
                            ListIterator<Segment> pp_iter = segs.listIterator();
                            while (pp_iter.hasNext()) {
                                Segment seg = pp_iter.next();
                                if (starting_pt == seg.point1) {
                                    starting_pt = seg.point2;
                                    pp_iter.remove();
                                    found = true;
                                    break;
                                } else if (starting_pt == seg.point2) { 
                                    starting_pt = seg.point1;
                                    pp_iter.remove();
                                    found = true;
                                    break;
                                }
                            }
                        } while (found);

                        if (starting_pt == ending_pt) // found path.  this point might be able to be removed
                            perimeter_pts.add(p_pt);
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
                    Segment prev_connecting_seg, SharedPoint shared_pt, double precision) {
            
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment begin\n";
//#endif
                int shared_point;
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                for (vector<Line_Segment>::const_iterator t_it = segments.begin(); t_it != segments.end(); ++t_it)
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment segments p1 x: " << (*t_it).point1->get_x() << " y: " <<
//                            (*t_it).point1->get_y() << " z: " << (*t_it).point1->get_z() << " p2 x: " << (*t_it).point2->get_x() <<
//                            " y: " << (*t_it).point2->get_y() << " z: " << (*t_it).point2->get_z() << "\n";
//#endif
                Iterator<Segment> it = segments.iterator();
                if (prev_connecting_seg != null) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_conneting_segment locating last connecting segment in segments p1 x: " << 
//                            prev_connecting_seg->point1->get_x() << " y: " << prev_connecting_seg->point1->get_y() << 
//                            " z: " << prev_connecting_seg->point1->get_z() << " p2 x: " << prev_connecting_seg->point2->get_x() << 
//                            " y: " << prev_connecting_seg->point2->get_y() << " z: " << prev_connecting_seg->point2->get_z() << "\n";
//#endif
                    while (it.hasNext()) {
                        if (it.next() == prev_connecting_seg) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment found segment\n";
//#endif
                            break;
                        }
                    }
                }
                while (it.hasNext()) {
                    Segment seg = it.next();
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment checking segment p1 x: " << 
//                            it->point1->get_x() << " y: " << it->point1->get_y() << " z: " << it->point1->get_z() << " p2 x: " << 
//                            it->point2->get_x() << " y: " << it->point2->get_y() << " z: " << it->point2->get_z() << "\n";
//#endif
                    if (segment == seg) // do not return the same segment
                        continue;

                    shared_point = segment.shares_pt(seg);
                    if (shared_point != -1) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment segment shared pt x: " << shared_point->get_x() << " y: " << 
//                                shared_point->get_y() << " z: " << shared_point->get_z() << "\n";
//#endif
                        int index = (segment.point1 == shared_point) ? segment.point2 : segment.point1;
                        Iterator<Point_3D> pt_it = mesh.point_iterator();
                        for (int i = 0; i < index; ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p1 = pt_it.next();
                        pt_it = mesh.point_iterator();
                        for (int i = 0; i < shared_point; ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p2 = pt_it.next();
                        index = (seg.point1 == shared_point) ? seg.point2 : seg.point1;
                        pt_it = mesh.point_iterator();
                        for (int i = 0; i < index; ++i) {
                            if (pt_it.hasNext())
                                pt_it.next();
                            else
                                throw new IllegalStateException("Invalid point location");
                        }
                        if (!pt_it.hasNext())
                            throw new IllegalStateException("Invalid point location");
                        Point_3D p3 = pt_it.next();

                        Bool same_direction = new Bool(false);
                        if (Vector_3D.is_same_line(p1, p2, p2, p3, same_direction, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment segments are in the same line\n";
//#endif
                            continue;
                        }

//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment end returning segment\n";
//#endif
                        // return segment
                        shared_pt.pt = shared_point;
                        return seg;
                    }
                }
        
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                cout << "Simplify_Mesh_3D::Pt_Remover::Segments::find_connecting_segment end returning 0\n";
//#endif
                return null;
            }
                    
            /*
             * is_segment_valid
             */
            public boolean is_seg_valid(int p1, int p2, List<Facet_3D> orig_facets, 
                    double precision) {
                
                Segment seg = new Segment(p1,p2,true);
                // 1. is it an existing segment? is it removed already?
                if (segments.contains(seg)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::is_seg_valid segment is an existing segment: returning true\n";
//#endif
                    return true;
                }
                if (removed_segs.contains(seg)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::is_seg_valid segment is an already removed segment: returning false\n";
//#endif
                    return false; // segment has already been removed so it shouldn't be used again
                }
                // 2. does it intersect any existing or removed segments?
                // get segment p1 point value
                Iterator<Point_3D> pt_it = mesh.point_iterator();
                for (int i = 0; i < p1; ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                Point_3D p1_pt = pt_it.next();

                // get segment p2 point value
                pt_it = mesh.point_iterator();
                for (int i = 0; i < p2; ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                Point_3D p2_pt = pt_it.next();
        
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                cout << "Simplify_Mesh_3D::Pt_Remover::Segments::is_seg_valid checking segment (" << 
//                        p1 << ", " << p2 << ") p1 x: " << p1_pt->get_x() << " y: " << p1_pt->get_y() << 
//                        " z: " << p1_pt->get_z() << " p2 x: " << p2_pt->get_x() << " y: " << p2_pt->get_y() << 
//                        " z: " << p2_pt->get_z() << "\n";
//#endif
                Iterator<Segment> it = segments.iterator();
                while (it.hasNext()) {
                    Segment segment = it.next();
                    // get segment points
                    pt_it = mesh.point_iterator();
                    for (int i = 0; i < segment.point1; ++i) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            throw new IllegalStateException("Invalid point index");
                    }
                    if (!pt_it.hasNext())
                        throw new IllegalStateException("Invalid point index");
                    Point_3D seg_p1 = pt_it.next();
                    pt_it = mesh.point_iterator();
                    for (int i = 0; i < segment.point2; ++i) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            throw new IllegalStateException("Invalid point index");
                    }
                    if (!pt_it.hasNext())
                        throw new IllegalStateException("Invalid point index");
                    Point_3D seg_p2 = pt_it.next();
            
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::is_seg_valid testing against existing segment (" << 
//                            it->point1 << ", " << it->point2 << ") p1 x: " << seg_p1->get_x() << " y: " << seg_p1->get_y() << 
//                            " z: " << seg_p1->get_z() << " p2 x: " << seg_p2->get_x() << " y: " << seg_p2->get_y() << 
//                            " z: " << seg_p2->get_z() << "\n";
//#endif
            
                    // does the segment share a point
                    int shared_pt = segment.shares_pt(seg);
                    if (shared_pt != -1) { // shares a common point
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_mesh_3D::Pt_Remover::is_seg_valid shares common point: " << shared_pt << "\n";
//#endif
                        if (segment.point1 == shared_pt) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing seg point1 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_3D.is_pt_on_vector(seg_p2, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                                cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing segment point2 is on the main segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        } else {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing seg point2 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_3D.is_pt_on_vector(seg_p1, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                                cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing segment point1 is on the main segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        }
                    } else { // no common point, test if segments intersect
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid no shared points\n";
//#endif
                        Intersection_Data_3D idata = new Intersection_Data_3D();
                        if (Vector_3D.intersect_vectors(p1_pt, p2_pt, seg_p1, seg_p2, idata, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid segments intersect with no shared points - returning false\n";
//#endif
                            return false; // segments intersect and do not share a point.  
                        }
                    }
                }
                it = removed_segs.iterator();
                while (it.hasNext()) {
                    Segment segment = it.next();
                    // get segment points
                    pt_it = mesh.point_iterator();
                    for (int i = 0; i < segment.point1; ++i) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!pt_it.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D seg_p1 = pt_it.next();
                    pt_it = mesh.point_iterator();
                    for (int i = 0; i < segment.point2; ++i) {
                        if (pt_it.hasNext())
                            pt_it.next();
                        else
                            throw new IllegalStateException("Invalid point location");
                    }
                    if (!pt_it.hasNext())
                        throw new IllegalStateException("Invalid point location");
                    Point_3D seg_p2 = pt_it.next();
            
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::is_seg_valid testing against removed segment (" << 
//                            it->point1 << ", " << it->point2 << ") p1 x: " << seg_p1->get_x() << " y: " << seg_p1->get_y() << 
//                            " z: " << seg_p1->get_z() << " p2 x: " << seg_p2->get_x() << " y: " << seg_p2->get_y() << 
//                            " z: " << seg_p2->get_z() << "\n";
//#endif
            
                    // does the segment share a point
                    int shared_pt = segment.shares_pt(seg);
                    if (shared_pt != -1) { // shares a common point
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid shares point: " << shared_pt << "\n";
//#endif
                        if (segment.point1 == shared_pt) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing segment point1 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_3D.is_pt_on_vector(seg_p2, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                                cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing segment point2 is on the original segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        } else {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing segment point2 is the shared point\n";
//#endif
                            // test if non-common point is on vector
                            if (Vector_3D.is_pt_on_vector(seg_p1, p1_pt, p2_pt, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                                cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid testing segment point1 is on the original segment - returning false\n";
//#endif
                                return false; // segment contains a smaller segment
                            }
                        }
                    } else { // no common point, test if segments intersect
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid no shared points\n";
//#endif
                        Intersection_Data_3D idata = new Intersection_Data_3D();
                        if (Vector_3D.intersect_vectors(p1_pt, p2_pt, seg_p1, seg_p2, idata, precision)) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_Valid no shared points, but segments intersect - returning false\n";
//#endif
                            return false; // segments intersect and do not share a point.  
                        }
                    }
                }

                // 3. is a point halfway up segment inside the original facets?
                Vector_3D v = new Vector_3D(p1_pt, p2_pt);
                v.scale(0.5);
                Point_3D half_way_pt = Point_3D.translate(p1_pt, v);
        
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid half way pt x: " << half_way_pt.get_x() << " y: " << half_way_pt.get_y() << " z: " << half_way_pt.get_z() << "\n";
//#endif

                boolean hwp_found = false; // half way point found
                Iterator<Facet_3D> f_it = orig_facets.iterator();
                while (f_it.hasNext()) {
                    Bool pt_is_on_side = new Bool(false);
                    if (f_it.next().contains_point(half_way_pt, pt_is_on_side, precision)) {
                        hwp_found = true;
                        break;
                    }
                }
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                if (hwp_found)
//                    cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid half way point is inside original facets\n";
//                else
//                    cout << "Simplify_Mesh_3D::Pt_Remover::is_seg_valid half way point is outside original facets\n";
//#endif
                return hwp_found; // segment is valid
            }
            
            /*
             * process segments that have been used
             */
            public void process_segs(Segment seg1, Segment seg2, int seg3_p1, int seg3_p2) {
                
                Segment segment3 = new Segment(seg3_p1, seg3_p2, true);

                if (!segments.contains(seg1))
                    throw new IllegalStateException("Unable to locate seg1");
                if (seg1.is_internal) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs seg1 is an internal segment\n";
//#endif
                    if (seg1.used) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs seg1 has already been used - removing\n";
//#endif
                        removed_segs.add(seg1);
                        segments.remove(seg1);
                    } else
                        seg1.used = true;
                } else { // seg1 is a perimeter segment
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs seg1 is a perimeter segment\n";
//#endif
                    removed_segs.add(seg1);
                    segments.remove(seg1);
                }

                if (!segments.contains(seg2))
                    throw new IllegalStateException("Unable to locate segment2");
                if (seg2.is_internal) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs segment2 is an internal segment\n";
//#endif
                    if (seg2.used) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs segment2 has already been used - removing\n";
//#endif
                        removed_segs.add(seg2);
                        segments.remove(seg2);
                    } else
                        seg2.used = true;
                } else { // segment2 is a perimeter segment
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs segment2 is a perimeter segment\n";
//#endif
                    removed_segs.add(seg2);
                    segments.remove(seg2);
                }

                boolean found = false;
                Iterator<Segment> seg_it = segments.iterator();
                while (seg_it.hasNext()) {
                    Segment seg = seg_it.next();
                    if (seg.equals(segment3)) {
                        found = true;
                        segment3 = seg;
                        break;
                    }
                }
                if (found) { // segment3 already exists
                    if (segment3.is_internal) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs segment3 is an internal segment\n";
//#endif
                        if (segment3.used) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                            cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs segment3 has already been used - removing\n";
//#endif
                            removed_segs.add(segment3);
                            segments.remove(segment3);
                        } else
                            segment3.used = true;
                    } else { // segment3 is a perimeter segment
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                        cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs segment3 is a perimeter segment\n";
//#endif
                        removed_segs.add(segment3);
                        segments.remove(segment3);
                    }
                } else { // segment3 does not exist, so it must be internal
//#ifdef DEBUG_SIMPLIFY_MESH_3D_NEW_FACETS
//                    cout << "Simplify_Mesh_3D::Pt_Remover::Segments::process_segs segment3 is an internal segment\n";
//#endif
                    // set used to true and add internal segment
                    segment3.used = true;
                    segments.add(segment3);
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
            }
        }
    }
    
    /*
     * List of same plane facets within Mesh
     */
    private class Facet_Datas implements Iterable<Pt_Remover> {
        
        private List<Pt_Remover> facet_datas;
//    private:
//        class Facet_Find {
//        public:
//            Facet_Find(const Facet& facet);
//            const bool operator()(const Facet& facet);
//        private:
//            Facet facet_to_find;
//        };

        /*
         * get the point associated with the index
         */
        public Facet_Datas() {
            super();
            
            facet_datas = new ArrayList<>();
        }
        
        public int size() { 
            return facet_datas.size(); 
        }
        
        @Override
        public Iterator<Pt_Remover> iterator() { 
            return facet_datas.iterator(); 
        }

        /*
         * find next same plane facets. 
         */
        private void get_next_sp_facets(List<Facet> same_plane_facets, 
                List<Facet> facet_list, Mesh_3D mesh) {
            
            Iterator<Facet> facet_it = facet_list.iterator();
            if (!facet_it.hasNext())
                return;
            
            Facet facet = facet_it.next();
            
            Iterator<Point_3D> pt_it = mesh.point_iterator();
            for (int i = 0; i < facet.get_p1_index(); ++i) {
                if (pt_it.hasNext())
                    pt_it.next();
                else
                    throw new IllegalStateException("Invalid point location");
            }
            if (!pt_it.hasNext())
                throw new IllegalStateException("Invalid point location");
            Point_3D fp1 = pt_it.next();
            pt_it = mesh.point_iterator();
            for (int i = 0; i < facet.get_p2_index(); ++i) {
                if (pt_it.hasNext())
                    pt_it.next();
                else
                    throw new IllegalStateException("Invalid point location");
            }
            if (!pt_it.hasNext())
                throw new IllegalStateException("Invalid point location");
            Point_3D p2 = pt_it.next();
            pt_it = mesh.point_iterator();
            for (int i = 0; i < facet.get_p3_index(); ++i) {
                if (pt_it.hasNext())
                    pt_it.next();
                else
                    throw new IllegalStateException("Invalid point location");
            }
            if (!pt_it.hasNext())
                throw new IllegalStateException("Invalid point location");
            Point_3D p3 = pt_it.next();
            
            // get unit normal vector
            Vector_3D unv = Vector_3D.cross_product(new Vector_3D(fp1, p2), new Vector_3D(fp1, p3));
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//            cout << "Simplify_Mesh_3D::Pt_Remover::get_next_sp_facets finding all facets with unv x: " << unv.get_x() << " y: " << unv.get_y() << " z: " << unv.get_z() << "\n";
//#endif
        
            // add to same plane facets
            same_plane_facets.add(facet); 
            facet_list.remove(facet);

            // now iterate through mesh and get all facets with the same unv
            facet_it = facet_list.iterator();
            while (facet_it.hasNext()) {
                Facet currentFacet = facet_it.next();
                
                pt_it = mesh.point_iterator();
                for (int i = 0; i < currentFacet.get_p1_index(); ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                Point_3D p1 = pt_it.next();
                pt_it = mesh.point_iterator();
                for (int i = 0; i < currentFacet.get_p2_index(); ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                p2 = pt_it.next();
                pt_it = mesh.point_iterator();
                for (int i = 0; i < currentFacet.get_p3_index(); ++i) {
                    if (pt_it.hasNext())
                        pt_it.next();
                    else
                        throw new IllegalStateException("Invalid point location");
                }
                if (!pt_it.hasNext())
                    throw new IllegalStateException("Invalid point location");
                p3 = pt_it.next();

                
                Vector_3D temp_unv = Vector_3D.cross_product(new Vector_3D(p1, p2), new Vector_3D(p1, p3));
                Bool same_direction = new Bool(false);
                if (Vector_3D.is_same_line(fp1, Point_3D.translate(fp1, unv), fp1, Point_3D.translate(fp1, temp_unv), same_direction, mesh.get_precision()) && 
                        same_direction.get_val()) {
//#ifdef DEBUG_SIMPLIFY_MESH_3D
//                    cout << "Simplify_Mesh_3D::Pt_Remover::get_next_sp_Facets found same plane facet p1: " << it->get_p1_index() << " p2: " << it->get_p2_index() << " p3: " << it->get_p3_index() << "\n";
//#endif
                    same_plane_facets.add(currentFacet); // add to facet list
                    facet_it.remove(); // remove from facets
                }
            }
        }

        /*
         * sort facets into planes and locate internal and perimeter points to remove
         */
        public void process_mesh(Mesh_3D mesh) {
//            System.out.println("Simplify_Mesh_3D::Facet_Datas::process_mesh begin");

            List<Facet> facet_list = new ArrayList<>();
            for (Iterator<Facet> it = mesh.facet_iterator(); it.hasNext();)
                facet_list.add(it.next());

            List<Facet> same_plane_facets = new ArrayList<>();
            while (!facet_list.isEmpty()) {
                same_plane_facets.clear();
                get_next_sp_facets(same_plane_facets, facet_list, mesh);
//                System.out.println("Simplify_Mesh_3D::process_mesh discovered " + same_plane_facets.size() + " facets in the same plane");
                facet_datas.add(new Pt_Remover(same_plane_facets, mesh));
            }

//            System.out.println("Simplify_Mesh_3D::Facet_Datas::process_mesh discovered " + facet_datas.size() + " groups of same plane facets");
        }
    }
}
