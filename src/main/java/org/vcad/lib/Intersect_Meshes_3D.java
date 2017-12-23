/*
 * Intersect_Meshes_3D.java
 */
package org.vcad.lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author Jeffrey Davis
 */
public class Intersect_Meshes_3D {
    
    /*
     * Intersect Point location
     * internal: inside facet
     * p1p2: on side from corner point 1 to corner point 2
     * p1p3: on side from corner point 1 to corner point 3
     * p2p3: on side from corner point 2 to corner point 3
     * p1: corner point 1
     * p2: corner point 2
     * p3: corner point 3
     */
    private enum Location { internal, p1p2, p1p3, p2p3, p1, p2, p3 };

    /*
     * Constructor.  Does not perform any actions because class does not 
     * contain any objects to initialize.
     */
    public Intersect_Meshes_3D() {
        super();
    }
    
    /*
     * Intersect mesh1 into mesh2.  returns true if new facets were generated
     * because of the intersection.  mesh1_result and mesh2_result are only
     * updated if function returns true;
     * 
     * Arguments:
     * mesh1: mesh to intersect into mesh2
     * mesh2: mesh to intersect into mesh1
     * mesh1_result: the intersected mesh1 result fractured with facets aligned to mesh2_result
     * mesh2_result: the intersected mesh2 result fractured with facets aligned to mesh1_result
     */
    public boolean intersect(Mesh_3D mesh1, Mesh_3D mesh2, Mesh_3D mesh1_result, Mesh_3D mesh2_result) {
//        System.out.println("Intersect_Meshes_3D::intersect begin");
        
        Facets facets1 = new Facets(mesh1);
        Facets facets2 = new Facets(mesh2);
        
//        System.out.println("Intersect_Meshes_3D::intersect calling intersect_facets");

        this.intersect_facets(facets1, facets2, mesh2_result.get_precision());

//        System.out.println("Intersect_Meshes_3D::intersect after intersect_facets");

        if (facets1.size() > mesh1.size() || facets2.size() > mesh2.size()) {
//            System.out.println("Intersect_Meshes_3D::intersect new facets were generated.  Updating mesh1_result");

            mesh1_result.clear();
            Iterator<Facet> it = facets1.iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
                mesh1_result.add(new Facet_3D(facets1.get_point(facet.get_p1_index()), 
                        facets1.get_point(facet.get_p2_index()), 
                        facets1.get_point(facet.get_p3_index())));
            }

//            System.out.println("Intersect_Meshes_3D::intersect Updating mesh2_result");

            mesh2_result.clear();
            it = facets2.iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
                mesh2_result.add(new Facet_3D(facets2.get_point(facet.get_p1_index()), 
                        facets2.get_point(facet.get_p2_index()), 
                        facets2.get_point(facet.get_p3_index())));
            }

//            System.out.println("Intersect_Meshes_3D::intersect returning true");

            return true;
        } else {
//            System.out.println("Intersect_Meshes_3D::intersect no new facets were generated.  Returning false");

            return false;
        }
    }
    
    /*
     * subtract mesh2 from mesh1 and store in result
     * 
     * Arguments:
     * mesh1: mesh to subtract mesh2 from
     * mesh2: mesh to subtract from mesh1
     * result: the result of mesh1 - mesh2
     */
    public void difference(Mesh_3D mesh1, Mesh_3D mesh2, Mesh_3D result) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_DIFFERENCE
//        cout << "Intersect_Meshes_3D::difference begin\n";
//#endif
        Facets facets1 = new Facets(mesh1);
        Facets facets2 = new Facets(mesh2);
        
        intersect_facets(facets2, facets1, result.get_precision());
//#ifdef DEBUG_INTERSECT_MESHES_3D_DIFFERENCE
//        cout << "Intersect_Meshes_3D::difference intersected meshes size facets1: " << facets1.size() << " facets2: " << facets2.size() << "\n";
//        cout << "Intersect_Meshes_3D::difference facets1: polyhedron(points=[";
//        for (Facets::pt_const_iterator it = facets1.pts_cbegin(); it != facets1.pts_cend(); ++it)
//        {
//            if (it != facets1.pts_cbegin())
//                cout << ',';
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        for (Facets::const_iterator it = facets1.cbegin(); it != facets1.cend(); ++it)
//        {
//            if (it != facets1.cbegin())
//                cout << ',';
//            // write points in clockwise order because openscad prefers it
//            cout << '[' << it->get_p1_index() << ',' << it->get_p3_index() << ',' << it->get_p2_index() << ']';
//        }
//        cout << "], convexity=4);\n";
//        cout << "Intersect_Meshes_3D::difference facets2: polyhedron(points=[";
//        for (Facets::pt_const_iterator it = facets2.pts_cbegin(); it != facets2.pts_cend(); ++it)
//        {
//            if (it != facets2.pts_cbegin())
//                cout << ',';
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        for (Facets::const_iterator it = facets2.cbegin(); it != facets2.cend(); ++it)
//        {
//            if (it != facets2.cbegin())
//                cout << ',';
//            // write points in clockwise order because openscad prefers it
//            cout << '[' << it->get_p1_index() << ',' << it->get_p3_index() << ',' << it->get_p2_index() << ']';
//        }
//        cout << "], convexity=4);\n";
//        cout << "Intersect_Meshes_3D::difference sorting facets\n";
//        cout.flush();
//#endif
        Facet_Sorter facet_sorter = new Facet_Sorter(result.get_precision());
        facet_sorter.sort(facets1, facets2);
        result.clear();
//        if (facets1.size() > mesh1.size() || facets2.size() > mesh2.size()) {
            // add any of facets1 facets that are not on or inside of facets2
            Iterator<Facet> it = facets1.iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
                boolean found = false;
                Iterator<Facet> f1_it = facet_sorter.f1_surface_iterator();
                while (f1_it.hasNext()) {
                    if (f1_it.next() == facet) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    f1_it = facet_sorter.f1_inside_iterator();
                    while (f1_it.hasNext()) {
                        if (f1_it.next() == facet) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        result.add(new Facet_3D(facets1.get_point(facet.get_p1_index()), 
                                facets1.get_point(facet.get_p2_index()), 
                                facets1.get_point(facet.get_p3_index())));
                    }
                }
            }

            // add any facets2 facets that are inside facets1 and invert the unit normal vector
            it = facet_sorter.f2_inside_iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_DIFFERENCE
//                    cout << "Intersect_Meshes_3D::difference adding facet2 facet (p1: " << it->get_p1_index() << " p2: " << 
//                            it->get_p2_index() << " p3: " << it->get_p3_index() << ") because it is inside facets1\n";
//#endif
                // invert unit normal vector by swapping p2 and p3
                result.add(new Facet_3D(facets2.get_point(facet.get_p1_index()), 
                        facets2.get_point(facet.get_p3_index()), 
                        facets2.get_point(facet.get_p2_index())));
            }
//        } else {
////#ifdef DEBUG_INTERSECT_MESHES_3D_DIFFERENCE
////            cout << "Intersect_Meshes_3D::difference No new facets created in the intersection of mesh1 and mesh2\n";
////            cout << "Intersect_Meshes_3D::difference adding facets from facets2 that are inside facets1\n";
////#endif
//            // add any facets from mesh that are inside this facet
//            Iterator<Facet> it = facet_sorter.f2_inside_iterator();
//            while (it.hasNext()) {
//                Facet facet = it.next();
//                // invert unit normal vector by swapping p2 and p3
//                result.add(new Facet_3D(facets2.get_point(facet.get_p1_index()), 
//                        facets2.get_point(facet.get_p3_index()), 
//                        facets2.get_point(facet.get_p2_index())));
//            }
//
////#ifdef DEBUG_INTERSECT_MESHES_3D_DIFFERENCE
////            cout << "Intersect_Meshes_3D::difference adding facets from facets1 that are outside of facets2\n";
////#endif
//            // remove any of this mesh facets that are inside or on the surface of the other mesh
//            it = facets1.iterator();
//            while (it.hasNext()) {
//                Facet facet = it.next();
//                boolean found = false;
//                Iterator<Facet> f1_it = facet_sorter.f1_surface_iterator();
//                while (f1_it.hasNext()) {
//                    if (f1_it.next() == facet) {
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    f1_it = facet_sorter.f1_inside_iterator();
//                    while (f1_it.hasNext()) {
//                        if (f1_it.next() == facet) {
//                            found = true;
//                            break;
//                        }
//                    }
//                    if (!found)
//                        result.add(new Facet_3D(facets1.get_point(facet.get_p1_index()), 
//                                facets1.get_point(facet.get_p2_index()), 
//                                facets1.get_point(facet.get_p3_index())));
//                }
//            }
//        }
//#ifdef DEBUG_INTERSECT_MESHES_3D_DIFFERENCE
//            cout << "Intersect_Meshes_3D::difference end\n";
//#endif
    }
    
    /* 
     * intersect mesh1 and mesh2 and keep only the facets that are in both.
     * 
     * Arguments:
     * mesh1: mesh to intersect into mesh2
     * mesh2: mesh to intersect into mesh1
     * result: the intersected mesh1 and mesh2 result
     */
    public void intersection(Mesh_3D mesh1, Mesh_3D mesh2, Mesh_3D result) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_INTERSECTION
//        cout << "Intersect_Meshes_3D::intersection begin\n";
//#endif
        Facets facets1 = new Facets(mesh1);
        Facets facets2 = new Facets(mesh2);
        
        intersect_facets(facets2, facets1, result.get_precision());

//        System.out.println("Intersect_Meshes_3D::intersection intersected meshes size facets1: " + facets1.size() + " facets2: " + facets2.size());
//        System.out.print("Intersect_Meshes_3D::intersection facets1: polyhedron(points=[");
//        boolean first = true;
//        for (Iterator<Point_3D> it = facets1.pts_iterator(); it.hasNext();) {
//            if (first)
//                first = false;
//            else
//                System.out.print(",");
//            Point_3D pt = it.next();
//            System.out.print("[" + pt.get_x() + "," + pt.get_y() + "," + pt.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = facets1.iterator(); it.hasNext();) {
//            if (first)
//                first = false;
//            else
//                System.out.print(",");
//            Facet f = it.next();
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.print("Intersect_Meshes_3D::intersection facets2: polyhedron(points=[");
//        first = true;
//        for (Iterator<Point_3D> it = facets2.pts_iterator(); it.hasNext();) {
//            if (first)
//                first = false;
//            else
//                System.out.print(",");
//            Point_3D pt = it.next();
//            System.out.print("[" + pt.get_x() + "," + pt.get_y() + "," + pt.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = facets2.iterator(); it.hasNext();) {
//            if (first)
//                first = false;
//            else
//                System.out.print(",");
//            Facet f = it.next();
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.println("Intersect_Meshes_3D::intersection sorting facets");

        Facet_Sorter facet_sorter = new Facet_Sorter(result.get_precision());
        facet_sorter.sort(facets1, facets2);
        result.clear();
//        if (facets1.size() > mesh1.size() || facets2.size() > mesh2.size()) {
            // add any t_result facets that are inside or on m_result
            Iterator<Facet> it = facets1.iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
                Iterator<Facet> f1_it = facet_sorter.f1_surface_iterator();
                boolean found = false;
                while (f1_it.hasNext()) {
                    if (f1_it.next() == facet) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    f1_it = facet_sorter.f1_inside_iterator();
                    while (f1_it.hasNext()) {
                        if (f1_it.next() == facet) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    result.add(new Facet_3D(facets1.get_point(facet.get_p1_index()), 
                            facets1.get_point(facet.get_p2_index()), 
                            facets1.get_point(facet.get_p3_index())));
                }
            }

            // add any facet2 facets that are inside facet1
            it = facet_sorter.f2_inside_iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_INTERSECTION
//                cout << "Intersect_Meshes_3D::intersection adding facet2 facet (p1: " << it->get_p1_index() << " p2: " << 
//                        it->get_p2_index() << " p3: " << it->get_p3_index() << ") because it is inside facets1\n";
//#endif
                result.add(new Facet_3D(facets2.get_point(facet.get_p1_index()), 
                        facets2.get_point(facet.get_p2_index()), 
                        facets2.get_point(facet.get_p3_index())));
            }
//        } else {
////#ifdef DEBUG_INTERSECT_MESHES_3D_INTERSECTION
////            cout << "Intersect_Meshes_3D::intersection No new facets created in the intersection of mesh1 and mesh2\n";
////            cout << "Intersect_Meshes_3D::intersection adding facets from facets1 that are inside or on the surface of facets2\n";
////#endif
//            // this mesh facet is inside or on mesh
//            Iterator<Facet> it = facets1.iterator();
//            while (it.hasNext()) {
//                Facet facet = it.next();
//                boolean found = false;
//                Iterator<Facet> f1_it = facet_sorter.f1_inside_iterator();
//                while (f1_it.hasNext()) {
//                    if (f1_it.next() == facet) {
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    f1_it = facet_sorter.f1_surface_iterator();
//                    while (f1_it.hasNext()) {
//                        if (f1_it.next() == facet) {
//                            found = true;
//                            break;
//                        }
//                    }
//                }
//                if (found) {
//                    result.add(new Facet_3D(facets1.get_point(facet.get_p1_index()), 
//                            facets1.get_point(facet.get_p2_index()), 
//                            facets1.get_point(facet.get_p3_index())));
//                }
//            }
//            
////#ifdef DEBUG_INTERSECT_MESHES_3D_INTERSECTION
////            cout << "Intersect_Meshes_3D::intersection adding facets from facets2 that are inside facets1\n";
////#endif
//            // mesh facet is inside this mesh
//            it = facet_sorter.f2_inside_iterator();
//            while (it.hasNext()) {
//                Facet facet = it.next();
//                // add facets that are inside mesh
//                result.add(new Facet_3D(facets2.get_point(facet.get_p1_index()), 
//                        facets2.get_point(facet.get_p2_index()), 
//                        facets2.get_point(facet.get_p3_index())));
//            }
//        }
//#ifdef DEBUG_INTERSECT_MESHES_3D_INTERSECTION
//        cout << "Intersect_Meshes_3D::intersection end\n";
//#endif
    }
    
    /*
     * merge mesh1 and mesh2 together and store in result.
     * 
     * Arguments:
     * mesh1: mesh to combine into mesh2
     * mesh2: mesh to combine into mesh1
     * result: the combined mesh1 and mesh2
     */
    public void merge(Mesh_3D mesh1, Mesh_3D mesh2, Mesh_3D result) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_MERGE
//        cout << "Intersect_Meshes_3D::merge begin\n";
//#endif
        Facets facets1 = new Facets(mesh1);
        Facets facets2 = new Facets(mesh2);
        
        intersect_facets(facets2, facets1, mesh1.get_precision());
//#ifdef DEBUG_INTERSECT_MESHES_3D_MERGE
//        cout << "Intersect_Meshes_3D::merge intersected meshes size facets1: " << facets1.size() << " facets2: " << facets2.size() << "\n";
//        cout << "Intersect_Meshes_3D::merge facets1: polyhedron(points=[";
//        for (Facets::pt_const_iterator it = facets1.pts_cbegin(); it != facets1.pts_cend(); ++it)
//        {
//            if (it != facets1.pts_cbegin())
//                cout << ',';
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        for (Facets::const_iterator it = facets1.cbegin(); it != facets1.cend(); ++it)
//        {
//            if (it != facets1.cbegin())
//                cout << ',';
//            // write points in clockwise order because openscad prefers it
//            cout << '[' << it->get_p1_index() << ',' << it->get_p3_index() << ',' << it->get_p2_index() << ']';
//        }
//        cout << "], convexity=4);\n";
//        cout << "Intersect_Meshes_3D::merge facets2: polyhedron(points=[";
//        for (Facets::pt_const_iterator it = facets2.pts_cbegin(); it != facets2.pts_cend(); ++it)
//        {
//            if (it != facets2.pts_cbegin())
//                cout << ',';
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        for (Facets::const_iterator it = facets2.cbegin(); it != facets2.cend(); ++it)
//        {
//            if (it != facets2.cbegin())
//                cout << ',';
//            // write points in clockwise order because openscad prefers it
//            cout << '[' << it->get_p1_index() << ',' << it->get_p3_index() << ',' << it->get_p2_index() << ']';
//        }
//        cout << "], convexity=4);\n";
//        cout << "Intersect_Meshes_3D::merge sorting facets\n";
//        cout.flush();
//#endif
        Facet_Sorter facet_sorter = new Facet_Sorter(result.get_precision());
        facet_sorter.sort(facets1, facets2);
        result.clear();
//        if (facets1.size() > mesh1.size() || facets2.size() > mesh2.size()) {
            // add any t_result facets that are not inside m_result
            Iterator<Facet> it = facets1.iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
                boolean found = false;
                Iterator<Facet> f1_it = facet_sorter.f1_inside_iterator();
                while (f1_it.hasNext()) {
                    if (f1_it.next() == facet) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_MERGE
//                    cout << "Intersect_Meshes_3D::merge adding facet1 facet (p1: " << it->get_p1_index() << " p2: " << 
//                            it->get_p2_index() << " p3: " << it->get_p3_index() << ") because it is not inside facets2\n";
//#endif
                    result.add(new Facet_3D(facets1.get_point(facet.get_p1_index()), 
                            facets1.get_point(facet.get_p2_index()), 
                            facets1.get_point(facet.get_p3_index())));
                }
            }

            // add any m_result facets that are not inside or on t_result
            it = facets2.iterator();
            while (it.hasNext()) {
                Facet facet = it.next();
                boolean found = false;
                Iterator<Facet> f2_it = facet_sorter.f2_inside_iterator();
                while (f2_it.hasNext()) {
                    if (f2_it.next() == facet) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    f2_it = facet_sorter.f2_surface_iterator();
                    while (f2_it.hasNext()) {
                        if (f2_it.next() == facet) {
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        result.add(new Facet_3D(facets2.get_point(facet.get_p1_index()), 
                                facets2.get_point(facet.get_p2_index()), 
                                facets2.get_point(facet.get_p3_index())));
                }
            }
//        } else {
////#ifdef DEBUG_INTERSECT_MESHES_3D_MERGE
////            cout << "Intersect_Meshes_3D::merge No new facets created in the intersection of mesh1 and mesh2\n";
////            cout << "Intersect_Meshes_3D::merge adding facets from facets1 that are not inside facets2\n";
////#endif
//            // add this mesh facets that are not inside mesh
//            Iterator<Facet> it = facets1.iterator();
//            while (it.hasNext()) {
//                Facet facet = it.next();
//                boolean found = false;
//                Iterator<Facet> f1_it = facet_sorter.f1_inside_iterator();
//                while (f1_it.hasNext()) {
//                    if (f1_it.next() == facet) {
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found)
//                    result.add(new Facet_3D(facets1.get_point(facet.get_p1_index()), 
//                            facets1.get_point(facet.get_p2_index()), 
//                            facets1.get_point(facet.get_p3_index())));
//            }
//            
////#ifdef DEBUG_INTERSECT_MESHES_3D_MERGE
////            cout << "Intersect_Meshes_3D::merge adding facets from facets2 that are outside facets1\n";
////#endif
//            // add mesh facet that is not inside or on this mesh
//            it = facets2.iterator();
//            while (it.hasNext()) {
//                Facet facet = it.next();
//                boolean found = false;
//                Iterator<Facet> f2_it = facet_sorter.f2_inside_iterator();
//                while (f2_it.hasNext()) {
//                    if (f2_it.next() == facet) {
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    f2_it = facet_sorter.f2_surface_iterator();
//                    while (f2_it.hasNext()) {
//                        if (f2_it.next() == facet) {
//                            found = true;
//                            break;
//                        }
//                    }
//                }
//                if (!found)
//                    result.add(new Facet_3D(facets2.get_point(facet.get_p1_index()), 
//                            facets2.get_point(facet.get_p2_index()), 
//                            facets2.get_point(facet.get_p3_index())));
//            }
//        }
//#ifdef DEBUG_INTERSECT_MESHES_3D_MERGE
//        cout << "Intersect_Meshes_3D::merge end\n";
//#endif
    }
        
    /*
     * Intersect two facets.  
     * 
     * Arguments:
     * facet1: a facet to intersect into facet2
     * facet2: a facet to intersect into facet1
     * precision: the precision to perform the intersection
     */
    private void intersect_facets(Facets facets1, Facets facets2, double precision) {
//        System.out.println("intersect_meshes_3D::intersect_facets begin");

        // create a list of facet builders for facets1
        List<Facet_Builder> f1_builders = new ArrayList<>();
        Iterator<Facet> it = facets1.iterator();
        while (it.hasNext()) {
            Facet facet = it.next();
            f1_builders.add(new Facet_Builder(true, facet,
                    new Facet_3D(facets1.get_point(facet.get_p1_index()), 
                            facets1.get_point(facet.get_p2_index()), 
                            facets1.get_point(facet.get_p3_index())), 
                    precision));
        }
        
        I_Pt_Locator i_pt_locator = new I_Pt_Locator(precision);
        
        // intersect all facets together
        int index = 0;
        while (index < facets2.size()) {
            Facet f2_facet = facets2.getFacet(index);

            Facet_Builder f2_builder = new Facet_Builder(false, f2_facet,
                    new Facet_3D(facets2.get_point(f2_facet.get_p1_index()), 
                            facets2.get_point(f2_facet.get_p2_index()),
                            facets2.get_point(f2_facet.get_p3_index())), 
                    precision);
            
            // intersect all of facets1 with facets2 facet
            for (Facet_Builder fb1 : f1_builders) {
//                System.out.println("intersect_meshes_3D::intersect_facets facet1 p1 x: " + fb1.get_facet_3d().get_point1().get_x() + 
//                        " y: " + fb1.get_facet_3d().get_point1().get_y() + " z: " + fb1.get_facet_3d().get_point1().get_z() + 
//                        " p2 x: " + fb1.get_facet_3d().get_point2().get_x() + " y: " + fb1.get_facet_3d().get_point2().get_y() + 
//                        " z: " + fb1.get_facet_3d().get_point2().get_z() + " p3 x: " + fb1.get_facet_3d().get_point3().get_x() + 
//                        " y: " + fb1.get_facet_3d().get_point3().get_y() + " z: " + fb1.get_facet_3d().get_point3().get_z());
//                System.out.println("intersect_meshes_3D::intersect_facets facet2 p1 x: " + f2_builder.get_facet_3d().get_point1().get_x() + " y: " + 
//                        f2_builder.get_facet_3d().get_point1().get_y() + " z: " + f2_builder.get_facet_3d().get_point1().get_z() + " p2 x: " + 
//                        f2_builder.get_facet_3d().get_point2().get_x() + " y: " + f2_builder.get_facet_3d().get_point2().get_y() + " z: " + 
//                        f2_builder.get_facet_3d().get_point2().get_z() + " p3 x: " + f2_builder.get_facet_3d().get_point3().get_x() + " y: " + 
//                        f2_builder.get_facet_3d().get_point3().get_y() + " z: " + f2_builder.get_facet_3d().get_point3().get_z());

                I_Pt_List intersect_pts = new I_Pt_List();
                if (i_pt_locator.locate(fb1.get_facet_3d(), f2_builder.get_facet_3d(), intersect_pts)) {
//                    System.out.println("intersect_meshes_3D::intersect_facets intersect_pts size: " + intersect_pts.size());
//                    for (Iterator<Intersect_Point> ip_it = intersect_pts.iterator(); ip_it.hasNext(); ) {
//                        Intersect_Point i_point = ip_it.next();
//                        System.out.println("intersect_meshes_3D::intersect_facets intersect point x: " + i_point.pt.get_x() + 
//                                " y: " + i_point.pt.get_y() + " z: " + i_point.pt.get_z() + " f1_loc: " + i_point.f1_loc + 
//                                " f2_loc: " + i_point.f2_loc);
//                    }
//                    System.out.println("intersect_meshes_3D::intersect_facets adding intersection to facets1 facet builder p1 x: " + 
//                            fb1.get_facet_3d().get_point1().get_x() + " y: " + fb1.get_facet_3d().get_point1().get_y() + " z: " + 
//                            fb1.get_facet_3d().get_point1().get_z() + " p2 x: " + fb1.get_facet_3d().get_point2().get_x() + " y: " + 
//                            fb1.get_facet_3d().get_point2().get_y() + " z: " + fb1.get_facet_3d().get_point2().get_z() + " p3 x: " + 
//                            fb1.get_facet_3d().get_point3().get_x() + " y: " + fb1.get_facet_3d().get_point3().get_y() + " z: " + 
//                            fb1.get_facet_3d().get_point3().get_z());

                    // add intersection to f1 facet
                    fb1.add_intersection(intersect_pts);

//                    System.out.println("intersect_meshes_3D::intersect_facets adding intersection to facets2 facet builder");

                    // add intersection to f2_facet
                    f2_builder.add_intersection(intersect_pts);
                }
            }
            
//            System.out.println("intersect_meshes_3D::intersect_facets forming new facets for facets2 facet");

            // build facets2 facet
            Facets new_facets = new Facets();
            if (f2_builder.form_new_facets(new_facets)) {
//                System.out.println("intersect_meshes_3D::intersect_facets formed new facets for facets2 facet p1 x: " + 
//                        f2_builder.get_facet_3d().get_point1().get_x() + " y: " + f2_builder.get_facet_3d().get_point1().get_y() + 
//                        " z: " + f2_builder.get_facet_3d().get_point1().get_z() + " p2 x: " + f2_builder.get_facet_3d().get_point2().get_x() + 
//                        " y: " + f2_builder.get_facet_3d().get_point2().get_y() + " z: " + f2_builder.get_facet_3d().get_point2().get_z() + 
//                        " p3 x: " + f2_builder.get_facet_3d().get_point3().get_x() + " y: " + f2_builder.get_facet_3d().get_point3().get_y() + 
//                        " z: " + f2_builder.get_facet_3d().get_point3().get_z());
//                for (Iterator<Facet> f_it = new_facets.iterator(); f_it.hasNext(); ) {
//                    Facet t_facet = f_it.next();
//                    Point_3D p1 = new_facets.get_point(t_facet.get_p1_index());
//                    Point_3D p2 = new_facets.get_point(t_facet.get_p2_index());
//                    Point_3D p3 = new_facets.get_point(t_facet.get_p3_index());
//                    System.out.println("        intersect_meshes_3D::intersect_facets new facet p1 x: " + 
//                            p1.get_x() + " y: " + p1.get_y() + " z: " + p1.get_z() + " p2 x: " + p2.get_x() +
//                            " y: " + p2.get_y() + " z: " + p2.get_z() + " p3 x: " + p3.get_x() + 
//                            " y: " + p3.get_y() + " z: " + p3.get_z());
//                }

                facets2.replace_facet(f2_builder.get_facet(), new_facets);
                index += new_facets.size();
            } else {
                ++index;
//                System.out.println("intersect_meshes_3D::intersect_facets no intersection");
            }
        }
        
        // form facets1 facets
        for (Facet_Builder fb1 : f1_builders) {
//            System.out.println("intersect_meshes_3D::intersect_facets forming new facets for facets1 facet p1 x: " + 
//                    fb1.get_facet_3d().get_point1().get_x() + " y: " + fb1.get_facet_3d().get_point1().get_y() + 
//                    " z: " + fb1.get_facet_3d().get_point1().get_z() + " p2 x: " + fb1.get_facet_3d().get_point2().get_x() + 
//                    " y: " + fb1.get_facet_3d().get_point2().get_y() + " z: " + fb1.get_facet_3d().get_point2().get_z() + 
//                    " p3 x: " + fb1.get_facet_3d().get_point3().get_x() + " y: " + fb1.get_facet_3d().get_point3().get_y() + 
//                    " z: " + fb1.get_facet_3d().get_point3().get_z());

            // build facets1 facet
            Facets new_facets = new Facets();
            if (fb1.form_new_facets(new_facets)) {
//                System.out.println("intersect_meshes_3D::intersect_facets formed new facets for facets1 facet");
//
//                for (Iterator<Facet> facet_it = new_facets.iterator(); facet_it.hasNext(); ) {
//                    Facet f = facet_it.next();
//                    Point_3D p1 = new_facets.get_point(f.get_p1_index());
//                    Point_3D p2 = new_facets.get_point(f.get_p2_index());
//                    Point_3D p3 = new_facets.get_point(f.get_p3_index());
//                    System.out.println("        intersect_meshes_3D::intersect_facets new facet p1 x: " + 
//                            p1.get_x() + " y: " + p1.get_y() + " z: " + p1.get_z() + " p2 x: " + p2.get_x() +
//                            " y: " + p2.get_y() + " z: " + p2.get_z() + " p3 x: " + p3.get_x() + " y: " + 
//                            p3.get_y() + " z: " + p3.get_z());
//                }
                
                facets1.replace_facet(fb1.get_facet(), new_facets);
//            } else {
//                System.out.println("intersect_meshes_3D::intersect_facets no intersection");
            }
        }
    }

    /*
     * An intersection point common to two facets
     */
    private class Intersect_Point {
        public Point_3D pt;        // the intersection point
        public Location f1_loc;    // the location of the intersection point on facet1
        public Location f2_loc;    // the location of the intersection point on facet2
        public Intersect_Point() { // null initialization
            super();
            pt = new Point_3D(0,0,0);
            f1_loc = Location.internal;
            f2_loc = Location.internal;
        }
        
        /*
         * Constructor
         * 
         * Arguments:
         * i_point: the intersect point
         * f1_location: the intersect point location on facet1
         * f2_location: the intersect point location on facet2
         */
        public Intersect_Point(Point_3D i_point, Location f1_location, Location f2_location) {
            super();
            pt = i_point;
            f1_loc = f1_location;
            f2_loc = f2_location;
        }
        
        /*
         * equality operator.  returns true if the points match as well as the locations.
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Intersect_Point))
                return false;
            
            Intersect_Point ipt = (Intersect_Point) obj;
            return (ipt.pt == pt || (ipt.pt.get_x() == pt.get_x() && ipt.pt.get_y() == pt.get_y() && ipt.pt.get_z() == pt.get_z())) && 
                    ipt.f1_loc == f1_loc && ipt.f2_loc == f2_loc;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 71 * hash + Objects.hashCode(this.pt);
            hash = 71 * hash + Objects.hashCode(this.f1_loc);
            hash = 71 * hash + Objects.hashCode(this.f2_loc);
            return hash;
        }
    }
    
    /*
     * A list of intersect points
     */
    private class I_Pt_List implements Iterable<Intersect_Point> {
        private final List<Intersect_Point> i_points; // vector to hold intersect points

        public I_Pt_List() {
            super();
            i_points = new ArrayList<>();
        }
        
        public boolean isEmpty() { 
            return i_points.isEmpty(); 
        }
        
        public int size() { 
            return i_points.size(); 
        }
        
        public void clear() { 
            i_points.clear(); 
        }
        
        /*
         * Intersect_Point iterators
         */
        @Override
        public Iterator<Intersect_Point> iterator() {
            return i_points.iterator();
        }
        
        // add an intersect point
        public void add(Intersect_Point ip) {
            if (i_points.contains(ip))
                return;

            i_points.add(ip);
        }
        
        // erase an intersect point
        public void erase(Intersect_Point ip) { 
            i_points.remove(ip); 
        }
        
        /*
         * Verify intersect points and try to correct any problems found
         * 
         * Arguments:
         * facet1: the facet1 used in the intersection
         * facet2: the facet2 used in the intersection
         */
        public void validate(Facet_3D facet1, Facet_3D facet2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//            cout << "Intersect_Meshes_3D::I_Pt_List::validate begin\n";
//#endif
            // check if the point matches or is considered is_equal with another intersect point
            // and remove duplicate if possible
            Iterator<Intersect_Point> it = i_points.iterator();
            while (it.hasNext()) {
                Intersect_Point ip = it.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                cout << "Intersect_Meshes_3D::I_Pt_List::validate checking intersect pt x: " << ip.pt->get_x() << " y: " << ip.pt->get_y() << " z: " << ip.pt->get_z() << " (" << ip.pt << ") f1_loc: " << ip.f1_loc << " f2_loc: " << ip.f2_loc << "\n";
//#endif
                // increment it in the for loop definition, but update it in methods if necessary
                Iterator<Intersect_Point> it2 = i_points.iterator();
                while (it2.hasNext()) {
                    if (it2.next() == ip)
                        break;
                }
                while (it2.hasNext()) {
                    Intersect_Point ip2 = it2.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                    cout << "Intersect_Meshes_3D::I_Pt_List::validate checking against i pt x: " << (*it2).pt->get_x() << " y: " << (*it2).pt->get_y() << " z: " << (*it2).pt->get_z() << " (" << (*it2).pt << ") f1_loc: " << (*it2).f1_loc << " f2_loc: " << (*it2).f2_loc << "\n";
//#endif
                    if (matches(ip.pt, ip2.pt)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                        cout << "Intersect_Meshes_3D::I_Pt_List::validate pts match\n";
//#endif
                        // points match
                        it = process_matching_i_pts(ip, ip2, facet1, facet2);
                        break;
                    }
                }
            }

//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//            cout << "Intersect_Meshes_3D::I_Pt_List::validate end\n";
//#endif
        }

        /*
         * returns true if the points match, but ignores f1_loc and f2_loc
         */
        private boolean matches(Point_3D p1, Point_3D p2) {
            return p1.get_x() == p2.get_x() && p1.get_y() == p2.get_y() && p1.get_z() == p2.get_z();
        }
        
        /*
         * If two intersect points have the same points, but different locations
         * then this method is called (from validate).  Determines which point 
         * to keep and which to remove.  In one case, both are points are
         * removed and a corner point is added instead.
         * 
         * Arguments:
         * matching_ip1: has the same intersect point as matching_ip2
         * matching_ip2: has the same intersect point as matching_ip1
         * facet1: the facet1 used in the intersection
         * facet2: the facet2 used in the intersection
         */
        private Iterator<Intersect_Point> process_matching_i_pts(Intersect_Point matching_ip1, 
                Intersect_Point matching_ip2, Facet_3D facet1, Facet_3D facet2) {
            
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//            cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts begin\n";
//            cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts ip1 pt x: " << matching_ip1.pt->get_x() << " y: " << matching_ip1.pt->get_y() << " z: " << matching_ip1.pt->get_z() << " f1_loc: " << matching_ip1.f1_loc << " f2_loc: " << matching_ip1.f2_loc << "\n";
//            cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts ip2 pt x: " << (*matching_ip2).pt->get_x() << " y: " << (*matching_ip2).pt->get_y() << " z: " << (*matching_ip2).pt->get_z() << " f1_loc: " << (*matching_ip2).f1_loc << " f2_loc: " << (*matching_ip2).f2_loc << "\n";
//#endif
            // only process if the side locations of either f1, or f2 are the same
            boolean rem_ip1 = false;
            boolean rem_ip2 = false;
            boolean add_ip = false;
            Point_3D pt = null;
            Location f1_loc = Location.internal;
            Location f2_loc = Location.internal;

            boolean f1_loc_same;
            Location ip1_loc;
            Location ip2_loc;
            Location common_loc;

            if (matching_ip1.f1_loc == matching_ip2.f1_loc) {
                f1_loc_same = true;
                ip1_loc = matching_ip1.f2_loc;
                ip2_loc = matching_ip2.f2_loc;
                common_loc = matching_ip1.f1_loc;
            } else if (matching_ip1.f2_loc == matching_ip2.f2_loc) {
                f1_loc_same = false;
                ip1_loc = matching_ip1.f1_loc;
                ip2_loc = matching_ip2.f1_loc;
                common_loc = matching_ip1.f2_loc;
            } else { // no common locations - return
                Iterator<Intersect_Point> it = i_points.iterator();
                while (it.hasNext()) {
                    if (matching_ip1 == it.next())
                        break;
                }
                return it;
            }

            // process locations and determine which intersect point to use
            if (ip1_loc == Location.p1 || ip1_loc == Location.p2 || ip1_loc == Location.p3) {
                // prefer corner points over side or internal points
                if (ip2_loc == Location.p1p2 || ip2_loc == Location.p1p3 || ip2_loc == Location.p2p3 || ip2_loc == Location.internal)
                    rem_ip2 = true;
            } else if (ip1_loc == Location.p1p2 || ip1_loc == Location.p1p3 || ip1_loc == Location.p2p3) {
                // prefer side points over internal points
                if (ip2_loc == Location.internal)
                    rem_ip2 = true;
                else if (ip2_loc == Location.p1 || ip2_loc == Location.p2 || ip2_loc == Location.p3)
                    rem_ip1 = true; // prefer corner points over side points
                else { // same pt is on two sides... determine which one makes sense
                    // determine which location is the correct location...
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                    cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts matching points are both side points\n";
//#endif
                    // iterate through other intersect points and remove the side that generates too many side points
                    List<Intersect_Point> p1p2_pts = new ArrayList<>();
                    List<Intersect_Point> p1p3_pts = new ArrayList<>();
                    List<Intersect_Point> p2p3_pts = new ArrayList<>();
                    Iterator<Intersect_Point> it = i_points.iterator();
                    while (it.hasNext()) {
                        Intersect_Point ip = it.next();
                        Location loc = f1_loc_same ? ip.f2_loc : ip.f1_loc;
                        switch (loc) {
                            case p1:
                                p1p2_pts.add(ip);
                                p1p3_pts.add(ip);
                                break;
                            case p2:
                                p1p2_pts.add(ip);
                                p2p3_pts.add(ip);
                                break;
                            case p3:
                                p1p3_pts.add(ip);
                                p2p3_pts.add(ip);
                                break;
                            case p1p2:
                                p1p2_pts.add(ip);
                                break;
                            case p1p3:
                                p1p3_pts.add(ip);
                                break;
                            case p2p3:
                                p2p3_pts.add(ip);
                                break;
                            default:
                                break;
                        }
                    }

                    if (p1p2_pts.size() > 2) {
                        if (p1p2_pts.contains(matching_ip1))
                            rem_ip1 = true;
                        else if (p1p2_pts.contains(matching_ip2))
                            rem_ip2 = true;
                    } else if (p1p3_pts.size() > 2) {
                        if (p1p3_pts.contains(matching_ip1))
                            rem_ip1 = true;
                        else if (p1p3_pts.contains(matching_ip2))
                            rem_ip2 = true;
                    } else if (p2p3_pts.size() > 2) {
                        if (p2p3_pts.contains(matching_ip1))
                            rem_ip1 = true;
                        else if (p2p3_pts.contains(matching_ip2))
                            rem_ip2 = true;
                    } else {
                        Point_3D common_corner;
                        Point_3D f_diff_ep1;
                        Point_3D f_diff_ep2;
                        Point_3D f_same_ep1;
                        Point_3D f_same_ep2;

                        if (ip1_loc == Location.p1p2) {
                            if (ip2_loc == Location.p1p3) {
                                common_corner = f1_loc_same ? facet2.get_point1() : facet1.get_point1();
                                f_diff_ep1 = f1_loc_same ? facet2.get_point2() : facet1.get_point2();
                                f_diff_ep2 = f1_loc_same ? facet2.get_point3() : facet1.get_point3();
                            } else { // ip2_loc == p2p3
                                common_corner = f1_loc_same ? facet2.get_point2() : facet1.get_point2();
                                f_diff_ep1 = f1_loc_same ? facet2.get_point1() : facet1.get_point1();
                                f_diff_ep2 = f1_loc_same ? facet2.get_point3() : facet1.get_point3();
                            }
                        } else if (ip1_loc == Location.p1p3) {
                            if (ip2_loc == Location.p1p2) {
                                common_corner = f1_loc_same ? facet2.get_point1() : facet1.get_point1();
                                f_diff_ep1 = f1_loc_same ? facet2.get_point3() : facet1.get_point3();
                                f_diff_ep2 = f1_loc_same ? facet2.get_point2() : facet1.get_point2();
                            } else { // ip2_loc == p2p3
                                common_corner = f1_loc_same ? facet2.get_point3() : facet1.get_point3();
                                f_diff_ep1 = f1_loc_same ? facet2.get_point1() : facet1.get_point1();
                                f_diff_ep2 = f1_loc_same ? facet2.get_point2() : facet1.get_point2();
                            }
                        } else { // ip1_loc == p2p3
                            if (ip2_loc == Location.p1p2) {
                                common_corner = f1_loc_same ? facet2.get_point2() : facet1.get_point2();
                                f_diff_ep1 = f1_loc_same ? facet2.get_point3() : facet1.get_point3();
                                f_diff_ep2 = f1_loc_same ? facet2.get_point1() : facet1.get_point1();
                            } else { // ip2_loc == p1p3
                                common_corner = f1_loc_same ? facet2.get_point3() : facet1.get_point3();
                                f_diff_ep1 = f1_loc_same ? facet2.get_point2() : facet1.get_point2();
                                f_diff_ep2 = f1_loc_same ? facet2.get_point1() : facet1.get_point1();
                            }
                        }

                        // determine f1_ep1 and f1_ep2
                        switch (common_loc) {
                            case p1:
                                f_same_ep1 = f1_loc_same ? facet1.get_point2() : facet2.get_point2();
                                f_same_ep2 = f1_loc_same ? facet1.get_point3() : facet2.get_point3();
                                break;
                            case p2:
                                f_same_ep1 = f1_loc_same ? facet1.get_point1() : facet2.get_point1();
                                f_same_ep2 = f1_loc_same ? facet1.get_point3() : facet2.get_point3();
                                break;
                            case p3:
                                f_same_ep1 = f1_loc_same ? facet1.get_point1() : facet2.get_point1();
                                f_same_ep2 = f1_loc_same ? facet1.get_point2() : facet2.get_point2();
                                break;
                            case p1p2:
                                f_same_ep1 = f1_loc_same ? facet1.get_point1() : facet2.get_point1();
                                f_same_ep2 = f1_loc_same ? facet1.get_point2() : facet2.get_point2();
                                break;
                            case p1p3:
                                f_same_ep1 = f1_loc_same ? facet1.get_point1() : facet2.get_point1();
                                f_same_ep2 = f1_loc_same ? facet1.get_point3() : facet2.get_point3();
                                break;
                            case p2p3:
                                f_same_ep1 = f1_loc_same ? facet1.get_point2() : facet2.get_point2();
                                f_same_ep2 = f1_loc_same ? facet1.get_point3() : facet2.get_point3();
                                break;
                            default: // Location::internal
                                throw new IllegalStateException("unable to process matching point because location is internal");
                        }

                        // find the side that is closer to the f2 point and use that
                        // use cross product and dot product together to determine closest side
                        Vector_3D f_diff_v1 = new Vector_3D(common_corner, f_diff_ep1);
                        Vector_3D f_diff_v2 = new Vector_3D(common_corner, f_diff_ep2);
                        Vector_3D f_same_v1 = new Vector_3D(common_corner, f_same_ep1);
                        Vector_3D f_same_v2 = new Vector_3D(common_corner, f_same_ep2);

                        Vector_3D cp_f_diff_v1v2 = Vector_3D.cross_product(f_diff_v1, f_diff_v2);
                        Vector_3D cp_f_diff_v1_f_same_v1 = Vector_3D.cross_product(f_diff_v1, f_same_v1); // could be a zero vector if end point is the same
                        Vector_3D cp_f_diff_v1_f_same_v2 = Vector_3D.cross_product(f_diff_v1, f_same_v2); // could be a zero vector if end point is the same
                        Vector_3D cp_f_diff_v2_f_same_v1 = Vector_3D.cross_product(f_diff_v2, f_same_v1); // could be a zero vector if end point is the same
                        Vector_3D cp_f_diff_v2_f_same_v2 = Vector_3D.cross_product(f_diff_v2, f_same_v2); // could be a zero vector if end point is the same

                        if (cp_f_diff_v1_f_same_v1.get_x() == 0 && cp_f_diff_v1_f_same_v1.get_y() == 0 && cp_f_diff_v1_f_same_v1.get_z() == 0) {
                            // cp_f_diff_v1_f_same_v1 is a zero vector
                            if (Vector_3D.dot_product(cp_f_diff_v1_f_same_v2, cp_f_diff_v2_f_same_v2) > 0) { // f_same_v2 is on the same side of both f_diff_v1 and f_diff_v2
                                if (Vector_3D.dot_product(cp_f_diff_v1v2, cp_f_diff_v1_f_same_v2) > 0) // f_diff_v2 is the closest side to f_same_v2, so choose matching_ip2
                                    rem_ip1 = true;
                                else // f_diff_v1 is the closest side to f_same_vectors, so choose matching_ip1
                                    rem_ip2 = true;
                            }
                            else // f_same_v2 is on different sides of f_diff_v1 and f_diff_v2, so choose matching_ip1
                                rem_ip2 = true;
                        } else if (cp_f_diff_v1_f_same_v2.get_x() == 0 && cp_f_diff_v1_f_same_v2.get_y() == 0 && cp_f_diff_v1_f_same_v2.get_z() == 0) {
                            // cp_f_diff_v1_f_same_v2 is a zero vector
                            if (Vector_3D.dot_product(cp_f_diff_v1_f_same_v1, cp_f_diff_v2_f_same_v1) > 0) { // f_same_v1 is on the same side of both f_diff_v1 and f_diff_v2
                                if (Vector_3D.dot_product(cp_f_diff_v1v2, cp_f_diff_v1_f_same_v1) > 0) // f_diff_v2 is the closest side to f_same_v1, so choose matching_ip2
                                    rem_ip1 = true;
                                else // f_diff_v1 is the closest side to f_same vectors, so choose matching_ip1
                                    rem_ip2 = true;
                            } else // f_same_v1 is on different sides of f_diff_v1 and f_diff_v2, so choose f_diff_v1
                                rem_ip2 = true;
                        } else if (cp_f_diff_v2_f_same_v1.get_x() == 0 && cp_f_diff_v2_f_same_v1.get_y() == 0 && cp_f_diff_v2_f_same_v1.get_z() == 0) {
                            // cp_f_diff_v2_f_same_v1 is a zero vector
                            if (Vector_3D.dot_product(cp_f_diff_v1_f_same_v2, cp_f_diff_v2_f_same_v2) > 0) { // f_same_v2 is on the same side of both f_diff_v1 and f_diff_v2
                                if (Vector_3D.dot_product(cp_f_diff_v1v2, cp_f_diff_v1_f_same_v2) > 0) // f_diff_v2 is the closest side to f_same_v2, so choose matching_ip2
                                    rem_ip1 = true;
                                else // f_diff_v1 is the closest side to f_same vectors, so choose matching_ip1
                                    rem_ip2 = true;
                            } else // f_same_v2 is on different sides of f_diff_v1 and f_diff_v2, so choose f_diff_v2
                                rem_ip1 = true;
                        } else if (cp_f_diff_v2_f_same_v2.get_x() == 0 && cp_f_diff_v2_f_same_v2.get_y() == 0 && cp_f_diff_v2_f_same_v2.get_z() == 0) {
                            // cp_f_diff_v2_f_same_v2 is a zero vector
                            if (Vector_3D.dot_product(cp_f_diff_v1_f_same_v1, cp_f_diff_v2_f_same_v1) > 0) { // f_same_v1 is on the same side of both f_diff_v1 and f_diff_v2
                                if (Vector_3D.dot_product(cp_f_diff_v1v2, cp_f_diff_v1_f_same_v1) > 0) // f_diff_v2 is the closest side to f_same_v1, so choose matching_ip2
                                    rem_ip1 = true;
                                else // f2_v1 is the closest side to f1_vectors, so choose matching_ip1
                                    rem_ip2 = true;
                            } else // f_same_v1 is on different sides of f_diff_v1 and f_diff_v2, so choose matching_ip2
                                rem_ip1 = true;
                        } else if (Vector_3D.dot_product(cp_f_diff_v1_f_same_v1, cp_f_diff_v1_f_same_v2) > 0) { // both f_same_v1 and f_same_v2 are on the same side of f_diff_v1
                            if (Vector_3D.dot_product(cp_f_diff_v2_f_same_v1, cp_f_diff_v2_f_same_v2) > 0) { // both f_same_v1 and f_same_v2 are on the same side of f_diff_v2
                                if (Vector_3D.dot_product(cp_f_diff_v1v2, cp_f_diff_v1_f_same_v1) > 0) // f_diff_v2 is the closest side to f_same vectors, so choose matching_ip2
                                    rem_ip1 = true;
                                else // f_diff_v1 is the closest side to f_same vectors, so choose matching_ip1
                                    rem_ip2 = true;
                            } else // f_same_v1 and f_same_v2 are on different sides of f_diff_v2, so choose matching_ip2
                                rem_ip1 = true;
                        } else { // f_same_v1 and f_same_v2 are on different sides of f_diff_v1
                            if (Vector_3D.dot_product(cp_f_diff_v2_f_same_v1, cp_f_diff_v2_f_same_v2) > 0) // both f_same_v1 and f_same_v2 are on the same side of f_diff_v2, so choose matching_ip1
                                rem_ip2 = true;
                            else { // f_same_v1 and f_same_v2 are on different sides of f_diff_v2
                                // unable to determine which side to choose, so remove both and use corner point
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                                cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts using corner point\n";
//#endif
                                rem_ip1 = true;
                                rem_ip2 = true;
                                add_ip = true;
                                pt = common_corner;
                                Location corner_loc;
                                if (ip1_loc == Location.p1p2)
                                    corner_loc = ip2_loc == Location.p1p3 ? Location.p1 : Location.p2;
                                else if (ip1_loc == Location.p1p3)
                                    corner_loc = ip2_loc == Location.p1p2 ? Location.p1 : Location.p3;
                                else // ip1_loc == Location.p2p3
                                    corner_loc = ip2_loc == Location.p1p2 ? Location.p2 : Location.p3;
                                f1_loc = f1_loc_same ? matching_ip1.f1_loc : corner_loc;
                                f2_loc = f1_loc_same ? corner_loc : matching_ip1.f2_loc;
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                                cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts add_ip: " << add_ip << " pt x: " << pt->get_x() << " y: " << pt->get_y() << " z: " << pt->get_z() << " f1_loc: " << f1_loc << " f2_loc: " << f2_loc << "\n";
//#endif
                            }
                        }
                    }
                }
            } else // ip1_loc == Location::internal
                rem_ip1 = true; // prefer side or corner points over internal points

            // remove the 'extra' intersect point if it was found
            if (rem_ip2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts removing ip2\n";
//#endif
                i_points.remove(matching_ip2);
            }

            if (add_ip) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts adding ip x: " << pt->get_x() << " y: " << pt->get_y() << " z: " << pt->get_z() << " f1_loc: " << f1_loc << " f2_loc: " << f2_loc << "\n";
//#endif
                Intersect_Point i_pt = new Intersect_Point(pt, f1_loc, f2_loc);
                this.add(i_pt);
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                else
//                    cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts i_pt already exists\n";
//#endif
            }

            if (rem_ip1) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LIST
//                cout << "Intersect_Meshes_3D::I_Pt_List::process_matching_i_pts removing ip1\n";
//#endif
                Iterator<Intersect_Point> it = i_points.iterator();
                while (it.hasNext()) {
                    if (it.next() == matching_ip1) {
                        it.remove();
                        break;
                    }
                }
                return it;
            }

            Iterator<Intersect_Point> it = i_points.iterator();
            while (it.hasNext()) {
                if (it.next() == matching_ip1)
                    break;
            }
            return it;
        }
    }
    
    private class Pt_Location {
        private Location loc;
        
        public Pt_Location() {
            super();
            loc = Location.internal;
        }
        
        public Pt_Location(Location location) {
            super();
            loc = location;
        }
        
        public void setLocation(Location location) {
            loc = location;
        }
        
        public Location getLocation() {
            return loc;
        }
    }
    
    /*
     * Determines the intersect points common to two facets.
     * 
     * 1. Intersect each facet side of facet1 with each facet side of facet2
     * 2. If no intersection of facet1 side, check if the side intersects facet2
     * 3. if there is one intersection of facet1 side, check if either corner
     *    is in facet2.
     * 4. If a facet2 side was not intersected, check if it intersects facet1
     * 5. If a facet2 side has one intersection, check if either corner is inside facet1
     */
    private class I_Pt_Locator {
        private final double precision;
        private Facet_3D facet1;
        private Facet_3D facet2;
        private final List<Point_3D> generated_pts;

        /*
         * Constructor
         * 
         * Arguments:
         * f1: facet1 to intersect into facet2
         * f2: facet2 to intersect into facet1
         * prec: the precision to intersect the two facets
         */
        public I_Pt_Locator(double prec) {
            super();
            precision = prec;
            generated_pts = new ArrayList<>();
        }
        
        /*
         * find all intersect points for the two facets
         * 
         * Arguments:
         * intersect_points: filled with the intersect points found
         */
        public boolean locate(Facet_3D f1, Facet_3D f2, I_Pt_List intersect_points) {
            // assign values
            facet1 = f1;
            facet2 = f2;

//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate facet1 p1 x: " + facet1.get_point1().get_x() + 
//                    " y: " + facet1.get_point1().get_y() + " z: " + facet1.get_point1().get_z() + 
//                    " p2 x: " + facet1.get_point2().get_x() + " y: " + facet1.get_point2().get_y() + 
//                    " z: " + facet1.get_point2().get_z() + " p3 x: " + facet1.get_point3().get_x() + 
//                    " y: " + facet1.get_point3().get_y() + " z: " + facet1.get_point3().get_z());
//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate facet2 p1 x: " + facet2.get_point1().get_x() + 
//                    " y: " + facet2.get_point1().get_y() + " z: " + facet2.get_point1().get_z() + 
//                    " p2 x: " + facet2.get_point2().get_x() + " y: " + facet2.get_point2().get_y() + 
//                    " z: " + facet2.get_point2().get_z() + " p3 x: " + facet2.get_point3().get_x() + 
//                    " y: " + facet2.get_point3().get_y() + " z: " + facet2.get_point3().get_z());

            I_Pt_List intersect_pts = new I_Pt_List();
            // intersect vector sides, form internal segments
            // intersect i_p1p2 to facet
//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate intersecting i_p1p2 with facet");

            intersect_f1_side_to_f2(Location.p1p2, intersect_pts);
            int count = 0;
            for (Iterator<Intersect_Point> it = intersect_pts.iterator(); it.hasNext(); ) {
                Intersect_Point ip = it.next();
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate intersect_pts[" + count++ + "] x: " + 
//                        ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z() + " f1_loc: " + ip.f1_loc + 
//                        " f2_loc: " + ip.f2_loc);
            }
        
            // intersect i_p1p3 to facet
//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate intersecting i_p1p3 with facet. intersect_points size: " + intersect_pts.size());

            intersect_f1_side_to_f2(Location.p1p3, intersect_pts);
            count = 0;
            for (Iterator<Intersect_Point> it = intersect_pts.iterator(); it.hasNext(); ) {
                Intersect_Point ip = it.next();
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate intersect_pts[" + count++ + "] x: " + 
//                        ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z() + " f1_loc: " + ip.f1_loc + 
//                        " f2_loc: " + ip.f2_loc);
            }
        
            // intersect i_p2p3 to facet
//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate intersecting i_p2p3 with facet. intersect_points size: " + intersect_pts.size());

            intersect_f1_side_to_f2(Location.p2p3, intersect_pts);
            count = 0;
            for (Iterator<Intersect_Point> it = intersect_pts.iterator(); it.hasNext(); ) {
                Intersect_Point ip = it.next();
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate intersect_pts[" + count++ + "] x: " + 
//                        ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z() + " f1_loc: " + ip.f1_loc + 
//                        " f2_loc: " + ip.f2_loc);
            }
        
            // if a facet side was not intersected, see if it intersects the intersecting_facet
            Bool corner1_found = new Bool(false);
            Bool corner2_found = new Bool(false);
            int intersection_ct = is_f2_side_intersected(Location.p1p2, intersect_pts, corner1_found, corner2_found);
            if (intersection_ct == 0) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate Intersecting f2 p1p2 side");

                intersect_f2_side_to_f1(Location.p1p2, intersect_pts);
            } else if (intersection_ct == 1) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate checking f2.p1 and f2.p2");

                Bool pt_on_side = new Bool(false);
                if (!corner1_found.get_val() && facet1.contains_point(facet2.get_point1(), pt_on_side, precision)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate adding f2.p1 pt x: " + 
//                            facet2.get_point1().get_x() + " y: " + facet2.get_point1().get_y() + " z: " + 
//                            facet2.get_point1().get_z() + " f1_loc: internal f2_loc: p1");

                    intersect_pts.add(new Intersect_Point(facet2.get_point1(), Location.internal, Location.p1));
                }

                if (!corner2_found.get_val() && facet1.contains_point(facet2.get_point2(), pt_on_side, precision)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate adding f2.p2 pt x: " + 
//                            facet2.get_point2().get_x() + " y: " + facet2.get_point2().get_y() + " z: " + 
//                            facet2.get_point2().get_z() + " f1_loc: internal f2_loc: p2");

                    intersect_pts.add(new Intersect_Point(facet2.get_point2(), Location.internal, Location.p2));
                }
            }

            corner1_found.set_val(false);
            corner2_found.set_val(false);
            intersection_ct = is_f2_side_intersected(Location.p1p3, intersect_pts, corner1_found, corner2_found);
            if (intersection_ct == 0) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate Intersecting f2 p1p3 side");

                intersect_f2_side_to_f1(Location.p1p3, intersect_pts);
            } else if (intersection_ct == 1) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate checking f2.p3");

                Bool pt_on_side = new Bool(false);
                if (!corner2_found.get_val() && facet1.contains_point(facet2.get_point3(), pt_on_side, precision)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate adding f2.p3 pt x: " + 
//                            facet2.get_point3().get_x() + " y: " + facet2.get_point3().get_y() + " z: " + 
//                            facet2.get_point3().get_z() + " f1_loc: internal f2_loc: p3");

                    intersect_pts.add(new Intersect_Point(facet2.get_point3(), Location.internal, Location.p3));
                }
            }

            corner1_found.set_val(false);
            corner2_found.set_val(false);
            intersection_ct = is_f2_side_intersected(Location.p2p3, intersect_pts, corner1_found, corner2_found);
            if (intersection_ct == 0) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate Intersecting f2 p2p3 side");

                intersect_f2_side_to_f1(Location.p2p3, intersect_pts);
            }
//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::locate after f2 to f1 intersect_pts size: " + intersect_pts.size());

            if (intersect_pts.isEmpty())
                return false;

            // validate intersect points
            intersect_pts.validate(facet1, facet2);

            Iterator<Intersect_Point> it = intersect_pts.iterator();
            while (it.hasNext())
                intersect_points.add(it.next());
            return true;
        }

        private boolean matches(Point_3D p1, Point_3D p2) {
            return p1.get_x() == p2.get_x() && p1.get_y() == p2.get_y() && p1.get_z() == p2.get_z();
        }

        /*
         * determines the location of the intersect point. On the side,
         * or is either corner.  If the location is a corner, i_pt is updated to
         * use the corner point location.
         * 
         * Arguments:
         * side_start: the facet side start point (p1 or p2)
         * side_end: the facet side end point (p2 or p3)
         * side_loc: the location of the side (p1p2, p1p3, or p2p3)
         * i_pt: the intersect point to check
         * loc: the determined i_pt location
         */
        private void side_i_pt_loc(Point_3D side_start, Point_3D side_end, 
                Location side_loc, Intersect_Point_3D i_pt, Pt_Location loc) {
            
            if (Point_3D.is_equal(i_pt.getPoint(), side_start, precision)) {
                i_pt.setPoint(side_start);
                loc.setLocation((side_loc == Location.p2p3) ? Location.p2 : Location.p1);
            } else if (Point_3D.is_equal(i_pt.getPoint(), side_end, precision)) {
                i_pt.setPoint(side_end);
                loc.setLocation((side_loc == Location.p1p2) ? Location.p2 : Location.p3);
            } else {
                loc.setLocation(side_loc);
                // check existing points for pt value
                Iterator<Point_3D> it = generated_pts.iterator();
                boolean found = false;
                while (it.hasNext()) {
                    Point_3D pt = it.next();
                    if (Point_3D.is_equal(i_pt.getPoint(), pt, precision)) {
                        found = true;
                        i_pt.setPoint(pt);
                        break;
                    }
                }
                if (!found) {
                    generated_pts.add(i_pt.getPoint());
                }
            }
        }

        /*
         * Intersects f1 side with the three sides of facet2.
         * Updates i_pt_data to have a maximum of two intersect points if
         * any are found.  Returns true if one or two intersect points were
         * found.
         * 
         * Arguments:
         * f1_side_start: the starting point of the f1 side (p1 or p2)
         * f1_Side_end: the ending point of the f1 side (p2 or p3)
         * f1_side: the location of the f1_side (p1p2, p1p3, or p2p3)
         * f2_side_start: the starting point of the f2 side (p1 or p2)
         * f2_side_end: the ending point of the f1_side (p2 or p3)
         * f2_side: the location of the f2 side (p1p2, p1p3, or p2p3)
         * i_pt_data: the intersect points found
         */
        private boolean intersect_sides(Point_3D f1_side_start, 
                Point_3D f1_side_end, Location f1_side_loc, Point_3D f2_side_start, 
                Point_3D f2_side_end, Location f2_side_loc, I_Pt_Data i_pt_data) {
            
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LOCATOR
//            cout << "Intersect_Meshes_3D::I_Pt_Locator::intersect_sides begin\n";
//#endif
            Intersection_Data_3D idata = new Intersection_Data_3D();
            if (Vector_3D.intersect_vectors(f1_side_start, f1_side_end, f2_side_start, f2_side_end, idata, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LOCATOR
//                cout << "Intersect_Meshes_3D::I_Pt_Locator::intersect_sides found " << idata.num << " intersect points\n";
//#endif
                Pt_Location pt_loc = new Pt_Location();
                Intersect_Point_3D i_pt = new Intersect_Point_3D();
                i_pt.setPoint(idata.get_p1());
                side_i_pt_loc(f1_side_start, f1_side_end, f1_side_loc, i_pt, pt_loc);
                i_pt_data.ip1.f1_loc = pt_loc.getLocation();
                side_i_pt_loc(f2_side_start, f2_side_end, f2_side_loc, i_pt, pt_loc);
                i_pt_data.ip1.f2_loc = pt_loc.getLocation();
                i_pt_data.ip1.pt = i_pt.getPoint();
                ++i_pt_data.num;
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LOCATOR
//                cout << "Intersect_Meshes_3D::I_Pt_Locator::intersect_sides ip1.pt x: " << i_pt_data.ip1.pt->get_x() << " y: " << i_pt_data.ip1.pt->get_y() << " z: " << i_pt_data.ip1.pt->get_z() << " f1_loc: " << i_pt_data.ip1.f1_loc << " f2_loc: " << i_pt_data.ip1.f2_loc << "\n";
//#endif
                if (idata.get_num() == 2) {
                    i_pt.setPoint(idata.get_p2());
                    side_i_pt_loc(f1_side_start, f1_side_end, f1_side_loc, i_pt, pt_loc);
                    i_pt_data.ip2.f1_loc = pt_loc.getLocation();
                    side_i_pt_loc(f2_side_start, f2_side_end, f2_side_loc, i_pt, pt_loc);
                    i_pt_data.ip2.f2_loc = pt_loc.getLocation();
                    i_pt_data.ip2.pt = i_pt.getPoint();
                    ++i_pt_data.num;
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LOCATOR
//                    cout << "Intersect_Meshes_3D::I_Pt_Locator::intersect_sides ip2.pt x: " << i_pt_data.ip2.pt->get_x() << " y: " << i_pt_data.ip2.pt->get_y() << " z: " << i_pt_data.ip2.pt->get_z() << " f1_loc: " << i_pt_data.ip2.f1_loc << " f2_loc: " << i_pt_data.ip2.f2_loc << "\n";
//#endif
                }
            }
            return idata.get_num() > 0;
        }

        /*
         * Intersects a vector with the three sides of a facet.  Fills p1 and p2
         * with intersect points and sets p1p2_intersected, p1p3_intersected, and/or 
         * p2p3_intersected to true if those facet sides were intersected by the 
         * vector.  Returns the number of intersect points found (0, 1, or 2)
         * 
         * Arguments:
         * f1_side: the side of facet1 to intersect into facet2
         * intersect_points: the list of intersect points to fill
         */
        private void intersect_f1_side_to_f2(Location f1_side, I_Pt_List intersect_points) {
            Point_3D v1_start = f1_side == Location.p2p3 ? facet1.get_point2() : facet1.get_point1();
            Point_3D v1_end = f1_side == Location.p1p2 ? facet1.get_point2() : facet1.get_point3();

//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 p1p2");
            I_Pt_Data i_pt_data = new I_Pt_Data();
            if (intersect_sides(v1_start, v1_end, f1_side, facet2.get_point1(), facet2.get_point2(), Location.p1p2, i_pt_data)) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 Discovered " + i_pt_data.num + " intersections");
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding intersect point x: " + i_pt_data.ip1.pt.get_x() + " y: " + i_pt_data.ip1.pt.get_y() + " z: " + i_pt_data.ip1.pt.get_z() + " intersect_points size: " + intersect_points.size());
                intersect_points.add(i_pt_data.ip1);
                if (i_pt_data.num == 2) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding intersect point x: " + i_pt_data.ip2.pt.get_x() + " y: " + i_pt_data.ip2.pt.get_y() + " z: " + i_pt_data.ip2.pt.get_z() + " intersect_points size: " + intersect_points.size());
                    intersect_points.add(i_pt_data.ip2);
                    return; // no need to process further, found maximum of two intersection points
                }
            }

//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 p1p3");
            I_Pt_Data t_i_pt_data = new I_Pt_Data();
            if (intersect_sides(v1_start, v1_end, f1_side, facet2.get_point1(), facet2.get_point3(), Location.p1p3, t_i_pt_data)) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 discovered " + t_i_pt_data.num + " intersections");
                if (i_pt_data.num == 0 || !matches(i_pt_data.ip1.pt, t_i_pt_data.ip1.pt)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding intersect point x: " + t_i_pt_data.ip1.pt.get_x() + " y: " + t_i_pt_data.ip1.pt.get_y() + " z: " + t_i_pt_data.ip1.pt.get_z() + " intersect_points size: " + intersect_points.size());
                    intersect_points.add(t_i_pt_data.ip1);
                    switch (i_pt_data.num) {
                        case (0):
                            i_pt_data.num = 1;
                            i_pt_data.ip1 = t_i_pt_data.ip1;
                            break;
                        case (1):
                            return; // found maximum of two intersect points, do not do any more processing
                    }
                }
                if (t_i_pt_data.num == 2) { // should not need to check point against i_pt_data.ip1
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding intersect point x: " + t_i_pt_data.ip2.pt.get_x() + " y: " + t_i_pt_data.ip2.pt.get_y() + " z: " + t_i_pt_data.ip2.pt.get_z() + " intersect_points size: " + intersect_points.size());
                    intersect_points.add(t_i_pt_data.ip2);
                    return; // found maximum of two intersect points, do not do any more processing
                }
            }
        
//            System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 p2p3");
            t_i_pt_data = new I_Pt_Data();
            if (intersect_sides(v1_start, v1_end, f1_side, facet2.get_point2(), facet2.get_point3(), Location.p2p3, t_i_pt_data)) {
//                System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 discovered " + t_i_pt_data.num + " intersections");
                if (i_pt_data.num == 0 || !matches(i_pt_data.ip1.pt, t_i_pt_data.ip1.pt)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding intersect point x: " + t_i_pt_data.ip1.pt.get_x() + " y: " + t_i_pt_data.ip1.pt.get_y() + " z: " + t_i_pt_data.ip1.pt.get_z() + " intersect_points size: " + intersect_points.size());
                    intersect_points.add(t_i_pt_data.ip1);
                    switch (i_pt_data.num) {
                        case (0):
                            i_pt_data.num = 1;
                            i_pt_data.ip1 = t_i_pt_data.ip1;
                            break;
                        case (1):
                            return; // found maximum of two intersect points, do not do any more processing
                    }
                }
                if (t_i_pt_data.num == 2) { // should not need to check point against i_pt_data.ip1
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding intersect point x: " + t_i_pt_data.ip2.pt.get_x() + " y: " + t_i_pt_data.ip2.pt.get_y() + " z: " + t_i_pt_data.ip2.pt.get_z() + " intersect_points size: " + intersect_points.size());
                    intersect_points.add(t_i_pt_data.ip2);
                    return; // found maximum of two intersect points, do not do any more processing
                }
            }

            if (i_pt_data.num == 0) {
                Bool pt_on_side = new Bool(false);
                if (facet2.contains_point(v1_start, pt_on_side, precision) && facet2.contains_point(v1_end, pt_on_side, precision)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 contains v1_start and v1_end");

                    intersect_points.add(new Intersect_Point(v1_start, f1_side == Location.p2p3 ? Location.p2 : Location.p1, Location.internal));
                    intersect_points.add(new Intersect_Point(v1_end, f1_side == Location.p1p2 ? Location.p2 : Location.p3, Location.internal));
                } else {
                    // try to intersect with facet plane
                    Vector_3D i_vector = new Vector_3D(v1_start, v1_end);
                    Intersect_Point_3D i_point = new Intersect_Point_3D();
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 intersect_line_facet_plane");

                    if (Facet_3D.intersect_line_facet_plane(i_vector, v1_start, facet2, i_point, precision) &&
                            Vector_3D.is_pt_on_vector(i_point.getPoint(), v1_start, v1_end, precision) && 
                            facet2.contains_point(i_point.getPoint(), pt_on_side, precision)) {
                        Pt_Location loc = new Pt_Location();
                        side_i_pt_loc(v1_start, v1_end, f1_side, i_point, loc);
//                        System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding intersection point from intersect_line_facet_plane ip x: " + 
//                                i_point.getPoint().get_x() + " y: " + i_point.getPoint().get_y() + " z: " + i_point.getPoint().get_z() + " f1_loc: " + loc.getLocation() + 
//                                " f2_loc: internal");

                        intersect_points.add(new Intersect_Point(i_point.getPoint(), loc.getLocation(), Location.internal));
                    }
                }
            } else { // one intersect point
                Bool pt_on_side = new Bool(false);
                // check if either end is inside the facet
                if (i_pt_data.ip1.f1_loc != (f1_side == Location.p2p3 ? Location.p2 : Location.p1) && facet2.contains_point(v1_start, pt_on_side, precision)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding v1_start intersection point");

                    intersect_points.add(new Intersect_Point(v1_start, f1_side == Location.p2p3 ? Location.p2 : Location.p1, Location.internal));
                } else if (i_pt_data.ip1.f1_loc != (f1_side == Location.p1p2 ? Location.p2 : Location.p3) && facet2.contains_point(v1_end, pt_on_side, precision)) {
//                    System.out.println("Intersect_Meshes_3D::I_Pt_Locator::intersect_f1_side_to_f2 adding v1_end intersection point");

                    intersect_points.add(new Intersect_Point(v1_end, f1_side == Location.p1p2 ? Location.p2 : Location.p3, Location.internal));
                }
            }
        }

        /*
         * Intersects a facet side into the intersecting_facet. Adds any internal
         * segments, updates side_points and single_i_points as needed.
         * 
         * Arguments:
         * f2_side: the side of facet2 to intersect into facet1
         * intersect_points: the list of intersect points to fill
         */
        private void intersect_f2_side_to_f1(Location f2_side, I_Pt_List intersect_points) {
            
            Point_3D v2_start = f2_side == Location.p2p3 ? facet2.get_point2() : facet2.get_point1();
            Point_3D v2_end = f2_side == Location.p1p2 ? facet2.get_point2() : facet2.get_point3();

            boolean found_i_pt = false;
            Bool pt_on_side = new Bool(false);
            if (facet1.contains_point(v2_start, pt_on_side, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LOCATOR
//                cout << "Intersect_Meshes_3D::I_Pt_Locator::intersect_f2_side_to_f1 adding v2_start pt x: " << v2_start->get_x() << " y: " << v2_start->get_y() << " z: " << v2_start->get_z() << " f1_loc: internal f2_loc: " << (f2_side == Location::p2p3 ? Location::p2 : Location::p1) << "\n";
//#endif
                intersect_points.add(new Intersect_Point(v2_start, Location.internal, f2_side == Location.p2p3 ? Location.p2 : Location.p1));
                found_i_pt = true;
            }

            if (facet1.contains_point(v2_end, pt_on_side, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LOCATOR
//                cout << "Intersect_Meshes_3D::I_Pt_Locator::intersect_f2_side_to_f1 adding v2_end pt x: " << v2_end->get_x() << " y: " << v2_end->get_y() << " z: " << v2_end->get_z() << " f1_loc: internal f2_loc: " << (f2_side == Location::p1p2 ? Location::p2 : Location::p3) << "\n";
//#endif
                intersect_points.add(new Intersect_Point(v2_end, Location.internal, f2_side == Location.p1p2 ? Location.p2 : Location.p3));
                found_i_pt = true;
            }

            if (found_i_pt)
                return;

            Intersect_Point_3D i_point = new Intersect_Point_3D();
            if (Facet_3D.intersect_line_facet_plane(new Vector_3D(v2_start, v2_end), v2_start, facet1, i_point, precision) &&
                    Vector_3D.is_pt_on_vector(i_point.getPoint(), v2_start, v2_end, precision) && 
                    facet1.contains_point(i_point.getPoint(), pt_on_side, precision)) {
                
                // f1_loc should be internal
                Pt_Location loc = new Pt_Location(f2_side);
                side_i_pt_loc(v2_start, v2_end, f2_side, i_point, loc);
//#ifdef DEBUG_INTERSECT_MESHES_3D_I_PT_LOCATOR
//                cout << "Intersect_Meshes_3D::I_Pt_Locator::intersect_f2_side_to_f1 adding i_point pt x: " << i_pt->get_x() << " y: " << i_pt->get_y() << " z: " << i_pt->get_z() << " f1_loc: internal f2_loc: " << loc << "\n";
//#endif
                intersect_points.add(new Intersect_Point(i_point.getPoint(), Location.internal, loc.getLocation()));
            }
        }

        /*
         * Determine if a facet was intersected.  Returns the number of 
         * intersections (0, 1 or 2)
         * 
         * Arguments:
         * f2_side: the side of facet2 to test if it was intersected
         * intersect_points: the list of intersect points
         * corner1_found: set to true or false depending if the side start
         *                corner is found as an intersect point
         * corner2_found: set to true or false depending if the side end
         *                corner is found as an intersect point
         */
        private int is_f2_side_intersected(Location f2_side, 
            I_Pt_List intersect_points, Bool corner1_found, Bool corner2_found) {
            
            Location start_corner = f2_side == Location.p2p3 ? Location.p2 : Location.p1;
            Location end_corner = f2_side == Location.p1p2 ? Location.p2 : Location.p3;

            int count = 0;
            Iterator<Intersect_Point> it = intersect_points.iterator();
            while (it.hasNext()) {
                Location loc = it.next().f2_loc;
                if (loc == f2_side)
                    ++count;
                else if (loc == start_corner) {
                    ++count;
                    corner1_found.set_val(true);
                } else if (loc == end_corner) {
                    ++count;
                    corner2_found.set_val(true);
                }
            }

            return count;
        }
        
        /*
         * An intersect point holder to facilitate processing
         */
        private class I_Pt_Data {
            public int num;
            public Intersect_Point ip1;
            public Intersect_Point ip2;
            public I_Pt_Data() { // sets num to zero and initializes ip1 and ip2 with null constructor
                super();
                num = 0;
                ip1 = new Intersect_Point();
                ip2 = new Intersect_Point();
            }
        };
    }
    
    /*
     * List of facets with functions to facilitate intersecting facets and 
     * maintain a consistent mesh
     */
    private class Facets implements Iterable<Facet> {
        private final List<Point_3D> point_list; // list of points
        private final List<Facet> facet_list; // list of Facet
        
        public Facets() {
            super();
            
            point_list = new ArrayList<>();
            facet_list = new ArrayList<>();
        }
        
        public Facets(Mesh_3D mesh) {
            super();
            
            point_list = new ArrayList<>();
            facet_list = new ArrayList<>();
            
            // import mesh
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//            cout << "Intersect_Meshes_3D::Facets begin\n";
//#endif
            Iterator<Facet_3D> it = mesh.iterator();
            while (it.hasNext()) {
                Facet_3D facet_3d = it.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//                cout << "Intersect_Meshes_3D::Facets adding facet p1 x: " << it->get_point1()->get_x() << 
//                        " y: " << it->get_point1()->get_y() << " z: " << it->get_point1()->get_z() << 
//                        " p2 x: " << it->get_point2()->get_x() << " y: " << it->get_point2()->get_y() << 
//                        " z: " << it->get_point2()->get_z() << " p3 x: " << it->get_point3()->get_x() << 
//                        " y: " << it->get_point3()->get_y() << " z: " << it->get_point3()->get_z() << "\n";
//#endif
                int p1_index = -1;
                int p2_index = -1;
                int p3_index = -1;
                int index = 0;
                Iterator<Point_3D> p_it = point_list.iterator();
                while (p_it.hasNext()) {
                    Point_3D pt = p_it.next();
                    if (matches(facet_3d.get_point1(), pt))
                        p1_index = index;
                    else if (matches(facet_3d.get_point2(), pt))
                        p2_index = index;
                    else if (matches(facet_3d.get_point3(), pt))
                        p3_index = index;
                    ++index;
                }
                if (p1_index == -1) {
                    point_list.add(facet_3d.get_point1());
                    p1_index = index;
                    ++index;
                }
                if (p2_index == -1) {
                    point_list.add(facet_3d.get_point2());
                    p2_index = index;
                    ++index;
                }
                if (p3_index == -1) {
                    point_list.add(facet_3d.get_point3());
                    p3_index = index;
                }
                facet_list.add(new Facet(p1_index, p2_index, p3_index));
            }
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//            cout << "Intersect_Meshes_3D::Facets end\n";
//#endif
        }
        
        public boolean isEmpty() { 
            return facet_list.isEmpty(); 
        }
        
        public int size() { 
            return facet_list.size(); 
        }
        
        public Facet getFacet(int index) {
            return facet_list.get(index);
        }
        
        @Override
        public Iterator<Facet> iterator() {
            return facet_list.iterator();
        }
        
        public Iterator<Point_3D> pts_iterator() {
            return point_list.iterator();
        }
        
        public void clear() {
            facet_list.clear();
            point_list.clear();
        }
        
        /*
         * get the point associated with the index
         */
        public Point_3D get_point(int index) {
            return point_list.get(index);
        }
        
        /*
         * Add a Facet
         */
        public void add(Facet_3D facet) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//            cout << "Intersect_Meshes_3D::Facets::push_back(facet) begin\n";
//            cout << "Intersect_Meshes_3D::Facets::push_back p1 x: " << facet.get_point1()->get_x() << 
//                    " y: " << facet.get_point1()->get_y() << " z: " << facet.get_point1()->get_z() << 
//                    " p2 x: " << facet.get_point2()->get_x() << " y: " << facet.get_point2()->get_y() << 
//                    " z: " << facet.get_point2()->get_z() << " p3 x: " << facet.get_point3()->get_x() << 
//                    " y: " << facet.get_point3()->get_y() << " z: " << facet.get_point3()->get_z() << "\n";
//#endif
            int p1_index = -1;
            int p2_index = -1;
            int p3_index = -1;
            int index = 0;
            Iterator<Point_3D> p_it = point_list.iterator();
            while (p_it.hasNext()) {
                Point_3D pt = p_it.next();
                if (matches(facet.get_point1(), pt))
                    p1_index = index;
                else if (matches(facet.get_point2(), pt))
                    p2_index = index;
                else if (matches(facet.get_point3(), pt))
                    p3_index = index;
                ++index;
            }
            if (p1_index == -1) {
                point_list.add(facet.get_point1());
                p1_index = index;
                ++index;
            }
            if (p2_index == -1) {
                point_list.add(facet.get_point2());
                p2_index = index;
                ++index;
            }
            if (p3_index == -1) {
                point_list.add(facet.get_point3());
                p3_index = index;
            }
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//            cout << "Intersect_Meshes_3D::Facets::push_back(facet) adding facet p1: " << p1_index << " p2: " << p2_index << " p3: " << p3_index << "\n";
//#endif
            facet_list.add(new Facet(p1_index, p2_index, p3_index));
        }
        
        /*
         * Get the last facet in the list and remove it
         */
        public Facet_3D pop() {
            Facet last_facet = facet_list.get(facet_list.size() - 1);
            // do not check for points to remove and update other facet point indices.  
            // Leave unused points in list to reduce processing time
            facet_list.remove(last_facet);

            return new Facet_3D(point_list.get(last_facet.get_p1_index()), 
                    point_list.get(last_facet.get_p2_index()),
                    point_list.get(last_facet.get_p3_index()));
        }
        
        /*
         * Clear the list and add new facets
         */
        public void replace_all(Facets facets) {
            facet_list.clear();
            point_list.clear();
            Iterator<Point_3D> p_it = facets.pts_iterator();
            while (p_it.hasNext())
                point_list.add(p_it.next());
            Iterator<Facet> it = facets.iterator();
            while (it.hasNext())
                facet_list.add(it.next());
        }
        
        /*
         * Checks if the facet list contains the facet
         */
        public boolean contains(Facet_3D facet) {
            int p1_index = -1;
            int p2_index = -1;
            int p3_index = -1;

            int index = 0;
            Iterator<Point_3D> it = point_list.iterator();
            while (it.hasNext()) {
                Point_3D pt = it.next();
                if (pt == facet.get_point1())
                    p1_index = index;
                else if (pt == facet.get_point2())
                    p2_index = index;
                else if (pt == facet.get_point3())
                    p3_index = index;

                ++index;
            }

            if (p1_index == -1 || p2_index == -1 || p3_index == -1)
                return false;
            Facet f = new Facet(p1_index, p2_index, p3_index);
            return facet_list.contains(f);
        }
        
        /*
         * Finds the facet in the list.  Throws runtime_error if it cannot 
         * find the facet.
         */
        public Facet find_facet(Facet_3D facet) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//            cout << "Intersect_Meshes_3D::Facets::find_facet begin\n";
//            cout << "Intersect_Meshes_3D::Facets::find_facet facet p1 x: " << facet.get_point1()->get_x() << 
//                    " y: " << facet.get_point1()->get_y() << " z: " << facet.get_point1()->get_z() << 
//                    " p2 x: " << facet.get_point2()->get_x() << " y: " << facet.get_point2()->get_y() << 
//                    " z: " << facet.get_point2()->get_z() << " p3 x: " << facet.get_point3()->get_x() << 
//                    " y: " << facet.get_point3()->get_y() << " z: " << facet.get_point3()->get_z() << "\n";
//#endif
            int p1_index = -1;
            int p2_index = -1;
            int p3_index = -1;

            int index = 0;
            Iterator<Point_3D> it = point_list.iterator();
            while (it.hasNext()) {
                Point_3D pt = it.next();
                if (matches(facet.get_point1(), pt))
                    p1_index = index;
                else if (matches(facet.get_point2(), pt))
                    p2_index = index;
                else if (matches(facet.get_point3(), pt))
                    p3_index = index;
                ++index;
            }

            if (p1_index == -1 || p2_index == -1 || p3_index == -1) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//                cout << "Intersect_Meshes_3D::Facets::find_facet unable to locate point index p1_index: " << 
//                        p1_index << " p2_index: " << p2_index << " p3_index: " << p3_index << "\n";
//#endif
                throw new IllegalStateException("Unable to locate facet");
            }

            Facet f = new Facet(p1_index, p2_index, p3_index);
            if (!facet_list.contains(f)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//                cout << "Intersect_Meshes_3D::Facets::find_facet unable to locate facet in facet list\n";
//#endif
                throw new IllegalStateException("Unable to locate facet");
            }
        
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACETS
//            cout << "Intersect_Meshes_3D::Facets::find_facet found facet p1: " << f.get_p1_index() << 
//                    " p2: " << f.get_p2_index() << " p3: " << f.get_p3_index() << "\n";
//#endif
            return f;
        }
        
        /*
         * Replaces facet with new_facets
         * 
         * Arguments:
         * facet: the facet to replace
         * new_facets: the facets to replace facet with
         */
        public void replace_facet(Facet facet, Facets new_facets) {
//            System.out.println("Intersect_Meshes_3D::Facets::replace_facet begin");
//            System.out.println("Intersect_Meshes_3D::Facets::replace_facet facet p1(" + facet.get_p1_index() + ") x: " + 
//                    this.get_point(facet.get_p1_index()).get_x() + " y: " + this.get_point(facet.get_p1_index()).get_y() + 
//                    " z: " + this.get_point(facet.get_p1_index()).get_z() + " p2(" + facet.get_p2_index() + 
//                    ") x: " + this.get_point(facet.get_p2_index()).get_x() + " y: " + this.get_point(facet.get_p2_index()).get_y() + 
//                    " z: " + this.get_point(facet.get_p2_index()).get_z() + " p3(" + facet.get_p3_index() + ") x: " + 
//                    this.get_point(facet.get_p3_index()).get_x() + " y: " + this.get_point(facet.get_p3_index()).get_y() + 
//                    " z: " + this.get_point(facet.get_p3_index()).get_z()); 
//            int count = 0;
//            for (Iterator<Point_3D> it = point_list.iterator(); it.hasNext();) {
//                Point_3D p = it.next();
//                System.out.println("Intersect_Meshes_3D::Facets::replace_facet before points[" + count + "] x: " + p.get_x() + " y: " + p.get_y() + " z: " + p.get_z());
//                ++count;
//            }
//            count = 0;
//            for (Iterator<Facet> it = facet_list.iterator(); it.hasNext();) {
//                Facet f = it.next();
//                System.out.println("Intersect_Meshes_3D::Facets::replace_facet before facets[" + count + "] p1: " + f.get_p1_index() + 
//                        " p2: " + f.get_p2_index() + " p3: " + f.get_p3_index());
//                ++count;
//            }
//
//            System.out.println("Intersect_Meshes_3D::Facets::replace_facet looking for facet: p1: " + facet.get_p1_index() + " p2: " + facet.get_p2_index() + " p3: " + facet.get_p3_index());
            ListIterator<Facet> f_loc = facet_list.listIterator();
            boolean found = false;
            while (f_loc.hasNext()) {
                Facet f = f_loc.next();
                if (f == facet) {
                    found = true;
//                    System.out.println("Intersect_Meshes_3D::Facets::replace_facet discovered facet: p1: " + f.get_p1_index() + " p2: " + f.get_p2_index() + " p3: " + f.get_p3_index());
                    break;
                }
            }

            if (!found)
                throw new IllegalStateException("Unable to locate facet");

            // remove Facet at f_loc
//            System.out.println("Intersect_Meshes_3D::Facets::replace_facet erasing f_loc facets size: " + facet_list.size());
            
            f_loc.remove(); // f_loc now points to the facet just after the one removed

//            System.out.println("Intersect_Meshes_3D::Facets::replace_facet erased f_loc facets size: " + facet_list.size());
//            count = 0;
//            for (Iterator<Facet> it = facet_list.iterator(); it.hasNext(); ++count) {
//                Facet f = it.next();
//                System.out.println("Intersect_Meshes_3D::Facets::replace_facet after erase facets[" + count + "] p1: " + f.get_p1_index() + 
//                        " p2: " + f.get_p2_index() + " p3: " + f.get_p3_index());
//            }

            Iterator<Facet> it = new_facets.iterator();
            boolean processedFirst = false;
            Facet firstFacet = null;
            while (it.hasNext()) {
                Facet nf = it.next();
                int p1_index = -1;
                int p2_index = -1;
                int p3_index = -1;

                int index = 0;

                Iterator<Point_3D> p_it = point_list.iterator();
                while (p_it.hasNext()) {
                    Point_3D pt = p_it.next();
                    if (matches(new_facets.get_point(nf.get_p1_index()), pt))
                        p1_index = index;
                    else if (matches(new_facets.get_point(nf.get_p2_index()), pt))
                        p2_index = index;
                    else if (matches(new_facets.get_point(nf.get_p3_index()), pt))
                        p3_index = index;
                    ++index;
                }

                if (p1_index == -1) {
                    point_list.add(new_facets.get_point(nf.get_p1_index()));
                    p1_index = index;
                    ++index;
                }
                if (p2_index == -1) {
                    point_list.add(new_facets.get_point(nf.get_p2_index()));
                    p2_index = index;
                    ++index;
                }
                if (p3_index == -1) {
                    point_list.add(new_facets.get_point(nf.get_p3_index()));
                    p3_index = index;
                }

//                System.out.println("Intersect_Meshes_3D::Facets::replace_facet inserting facet p1: " + p1_index + " p2: " + p2_index + " p3: " + p3_index);

                Facet f = new Facet(p1_index, p2_index, p3_index);
                if (!processedFirst) {
                    firstFacet = f;
                    processedFirst = true;
                }
                f_loc.add(f);
            }

            f_loc = facet_list.listIterator();
            found = false;
            while (f_loc.hasNext()) {
                if (f_loc.next() == firstFacet) {
                    found = true;
                    if (f_loc.hasPrevious())
                        f_loc.previous();
                    else
                        f_loc = facet_list.listIterator();

                    break;
                }
            }
            if (!found)
                throw new IllegalStateException("Unable to locate newly inserted facet");
        
//            count = 0;
//            for (Iterator<Point_3D> p_it = point_list.iterator(); p_it.hasNext();) {
//                Point_3D p = p_it.next();
//                System.out.println("Intersect_Meshes_3D::Facets::replace_facet after points[" + count + "] x: " + p.get_x() + " y: " + p.get_y() + " z: " + p.get_z());
//                ++count;
//            }
//            count = 0;
//            for (Iterator<Facet> f_it = facet_list.iterator(); f_it.hasNext(); ++count) {
//                Facet f = f_it.next();
//                System.out.println("Intersect_Meshes_3D::Facets::replace_facet after facets[" + 
//                        count + "] p1: " + f.get_p1_index() + " p2: " + f.get_p2_index() + 
//                        " p3: " + f.get_p3_index());
//            }
//            System.out.println("Intersect_Meshes_3D::Facets::replace_facet end. returning f_loc");
        }
        
        /*
         * Checks if the point values are the same
         */
        private boolean matches(Point_3D p1, Point_3D p2) {
            return p1.get_x() == p2.get_x() && p1.get_y() == p2.get_y() && p1.get_z() == p2.get_z();
        }
    }
    
    /*
     * A Class to fracture a facet into new facets based on the intersect points
     */
    private class Facet_Builder {
        
        private final boolean for_facet1;
        private final double precision;
        private final Facet orig_facet;
        private final Facet_3D orig_facet_3d;
        private final List<Point_3D> internal_pts;
        private final List<Point_3D> p1p2_pts;
        private final List<Point_3D> p1p3_pts;
        private final List<Point_3D> p2p3_pts;
        private final Segments segments;
        
        /*
         * Constructor
         * 
         * Arguments:
         * for_f1: for facet1
         * orig_Facet: the original facet that will be fractured
         * prec: the precision to form new facets to
         */
        public Facet_Builder(boolean for_f1, Facet orig_facet, Facet_3D orig_facet_3d, double prec) {
            super();
            for_facet1 = for_f1;
            this.orig_facet = orig_facet;
            this.orig_facet_3d = orig_facet_3d;
            precision = prec;
            internal_pts = new ArrayList<>();
            p1p2_pts = new ArrayList<>();
            p1p3_pts = new ArrayList<>();
            p2p3_pts = new ArrayList<>();
            segments = new Segments();
        }

        public Facet get_facet() {
            return orig_facet;
        }
        
        public Facet_3D get_facet_3d() { 
            return orig_facet_3d; 
        }

        /*
         * Add a new intersection.  Generates internal segments based on
         * intersection points.
         * 
         * Arguments
         * intersect_pts: all of the intersect points
         * side_points: the side intersect points for this facet
         * i_side_points: the side intersect points for the other facet
         * internal_points: the internal points found in the intersection
         */
        public void add_intersection(I_Pt_List intersect_pts) {

//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection Adding a new intersection");
//
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection Current internal_points:");
//            for (Iterator<Point_3D> it = internal_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection existing internal point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
//            
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection Current p1p2_pts:");
//            for (Iterator<Point_3D> it = p1p2_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection existing p1p2 point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
//            
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection Current p1p3_pts:");
//            for (Iterator<Point_3D> it = p1p3_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection existing p1p3 point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
//            
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection Current p2p3_pts:");
//            for (Iterator<Point_3D> it = p2p3_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection existing p2p3 point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
        
            Intersecting_Facet_Side_Pts i_side_pts = new Intersecting_Facet_Side_Pts();
            Iterator<Intersect_Point> ip_it = intersect_pts.iterator();
            while (ip_it.hasNext()) {
                Intersect_Point ip = ip_it.next();
                Location loc = for_facet1 ? ip.f1_loc : ip.f2_loc; // the location on this facet
                Location i_loc = for_facet1 ? ip.f2_loc : ip.f1_loc; // the location on the intersecting facet

                if (loc == Location.internal) {
                    Iterator<Point_3D> internal_it = internal_pts.iterator();
                    boolean found = false;
                    while (internal_it.hasNext()) {
                        Point_3D pt = internal_it.next();
                        if (pt.get_x() == ip.pt.get_x() && pt.get_y() == ip.pt.get_y() && pt.get_z() == ip.pt.get_z()) {
                            found = true;
                            // update intersect point to use this point instead
                            ip.pt = pt;
                            break;
                        }
                    }
                    if (!found) {
                        internal_pts.add(ip.pt);

//                        System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection adding new internal point x: " + 
//                                ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z());
                    }
                } else if (loc == Location.p1p2) {
                    Iterator<Point_3D> side_it = p1p2_pts.iterator();
                    boolean found = false;
                    while (side_it.hasNext()) {
                        Point_3D pt = side_it.next();
                        if (pt.get_x() == ip.pt.get_x() && pt.get_y() == ip.pt.get_y() && pt.get_z() == ip.pt.get_z()) {
                            found = true;
                            // update intersect point to use this point instead
                            ip.pt = pt;
                            break;
                        }
                    }
                    if (!found) {
                        p1p2_pts.add(ip.pt);

//                        System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection adding new p1p2 point x: " +
//                                ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z());
                    }
                } else if (loc == Location.p1p3) {
                    Iterator<Point_3D> side_it = p1p3_pts.iterator();
                    boolean found = false;
                    while (side_it.hasNext()) {
                        Point_3D pt = side_it.next();
                        if (pt.get_x() == ip.pt.get_x() && pt.get_y() == ip.pt.get_y() && pt.get_z() == ip.pt.get_z()) {
                            found = true;
                            // update intersect point to use this point instead
                            ip.pt = pt;
                            break;
                        }
                    }
                    if (!found) {
                        p1p3_pts.add(ip.pt);

//                        System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection adding new p1p3 point x: " + 
//                                ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z());
                    }
                } else if (loc == Location.p2p3) {
                    Iterator<Point_3D> side_it = p2p3_pts.iterator();
                    boolean found = false;
                    while (side_it.hasNext()) {
                        Point_3D pt = side_it.next();
                        if (pt.get_x() == ip.pt.get_x() && pt.get_y() == ip.pt.get_y() && pt.get_z() == ip.pt.get_z()) {
                            found = true;
                            // update intersect point to use this point instead
                            ip.pt = pt;
                            break;
                        }
                    }
                    if (!found) {
                        p2p3_pts.add(ip.pt);

//                        System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection adding new p2p3 point x: " + 
//                                ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z());
                    }
                } else if (loc == Location.p1)
                    ip.pt = orig_facet_3d.get_point1();
                else if (loc == Location.p2)
                    ip.pt = orig_facet_3d.get_point2();
                else // (loc == Location.p3)
                    ip.pt = orig_facet_3d.get_point3();

                switch (i_loc) {
                    case p1:
                        i_side_pts.p1p2_points.add(ip);
                        i_side_pts.p1p3_points.add(ip);
                        break;
                    case p2:
                        i_side_pts.p1p2_points.add(ip);
                        i_side_pts.p2p3_points.add(ip);
                        break;
                    case p3:
                        i_side_pts.p1p3_points.add(ip);
                        i_side_pts.p2p3_points.add(ip);
                        break;
                    case p1p2:
                        i_side_pts.p1p2_points.add(ip);
                        break;
                    case p1p3:
                        i_side_pts.p1p3_points.add(ip);
                        break;
                    case p2p3:
                        i_side_pts.p2p3_points.add(ip);
                        break;
                    default:
                        break;
                }

            }

//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection internal_points after processing:");
//            for (Iterator<Point_3D> it = internal_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection current internal point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
//
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection p1p2_pts after processing:");
//            for (Iterator<Point_3D> it = p1p2_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection current p1p2 point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
//
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection p1p3_pts after processing:");
//            for (Iterator<Point_3D> it = p1p3_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection current p1p3 point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
//
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection p2p3_pts after processing:");
//            for (Iterator<Point_3D> it = p2p3_pts.iterator(); it.hasNext(); ) {
//                Point_3D pt = it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection current p2p3 point x: " + 
//                        pt.get_x() + " y: " + pt.get_y() + " z: " + pt.get_z());
//            }
//
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::add_intersection generating internal segments");

            // now generate internal segments
            gen_internal_segs(intersect_pts, i_side_pts);
        }

        /*
         * creates new facets.  If no new facets are generated, new_facets
         * will be empty and function will return false.  If new facets were
         * generated, function will return true and the new facets will be in 
         * new_facets.
         * 
         * Arguments
         * new_facets: Facets list to store new facets in.
         */
        public boolean form_new_facets(Facets new_facets) {
            // if there are internal points
            if (!internal_pts.isEmpty()) {
                // check for any link segments that may need to be added for lone internal points
                check_internal_pts();

                // check if any link facets need to be added for internal segments
                // not connected to the facet perimeter
                verify_internal_paths();

                // check for any internal segments that form a straight line
                // and add a link segment to allow facets to be formed
                verify_internal_segs();
            }

            // generate perimeter line segments (external)
            Point_3D p1 = orig_facet_3d.get_point1();
            Point_3D p2 = orig_facet_3d.get_point2();
            Point_3D p3 = orig_facet_3d.get_point3();

            // generate side segments
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::form_new_facets creating side segments for p1p2\n";
//#endif
            gen_side_segs(p1p2_pts, Location.p1p2, p1, p2);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::form_new_facets creating side segments for p1p3\n";
//#endif
            gen_side_segs(p1p3_pts, Location.p1p3, p1, p3);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::form_new_facets creating side segments for p2p3\n";
//#endif
            gen_side_segs(p2p3_pts, Location.p2p3, p2, p3);

            if (segments.size() > 3) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::form_new_facets Forming Facets\n";
//#endif
                Facets temp = new Facets();
                build_facets(temp);
            
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::form_new_facets replacing Facet with new facets\n";
//                cout << "Intersect_Meshes_3D::Facet_Builder::form_new_facets original Facet p1 x: " << orig_facet.get_point1()->get_x() << 
//                        " y: " << orig_facet.get_point1()->get_y() << " z: " << orig_facet.get_point1()->get_z() <<
//                        " p2 x: " << orig_facet.get_point2()->get_x() << " y: " << orig_facet.get_point2()->get_y() << 
//                        " z: " << orig_facet.get_point2()->get_z() << " p3 x: " << orig_facet.get_point3()->get_x() << 
//                        " y: " << orig_facet.get_point3()->get_y() << " z: " << orig_facet.get_point3()->get_z() << "\n";
//                double orig_area(facet_area(orig_facet));
//                double sum(0);
//                for (Facets::const_iterator it = temp.begin(); it != temp.end(); ++it)
//                {
//                    shared_ptr<Point_3D> p1(temp.get_point(it->get_p1_index()));
//                    shared_ptr<Point_3D> p2(temp.get_point(it->get_p2_index()));
//                    shared_ptr<Point_3D> p3(temp.get_point(it->get_p3_index()));
//                    cout << "        Intersect_Meshes_3D::Facet_Builder::form_new_facets new Facet p1 x: " << p1->get_x() << 
//                            " y: " << p1->get_y() << " z: " << p1->get_z() << " p2 x: " << p2->get_x() << " y: " << 
//                            p2->get_y() << " z: " << p2->get_z() << " p3 x: " << p3->get_x() << " y: " << 
//                            p3->get_y() << " z: " << p3->get_z() << "\n";
//                    sum += facet_area(Facet_3D(p1,p2,p3));
//                }
//                if (orig_area - sum > 0.1)
//                    cout << "MISSING FACETS!!! area difference: " << (orig_area - sum) << "\n";
//#endif
                new_facets.replace_all(temp);
                return true;
            } else { // no intersection
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::form_new_facets no intersection\n";
//#endif
                return false;
            }
        }

        /*
         * Generate an internal segment for facet based on a list of two 
         * intersect points. For example, if an intersecting facet side 
         * intersects orig_facet twice, then this would generate a segment
         * between those two points.
         * 
         * Arguments:
         * i_pts: a list of intersect points that has two points.
         */
        private void process_two_i_pts(I_Pt_List i_pts) {
            Iterator<Intersect_Point> it = i_pts.iterator();
            Intersect_Point ip1;
            if (it.hasNext())
                ip1 = it.next();
            else
                throw new IllegalStateException("Expected two intersect points, found zero");
            Intersect_Point ip2;
            if (it.hasNext())
                ip2 = it.next();
            else
                throw new IllegalStateException("Expected two intersect points, found only one");
            
            Location ip1_loc = for_facet1 ? ip1.f1_loc : ip1.f2_loc;
            Location ip2_loc = for_facet1 ? ip2.f1_loc : ip2.f2_loc;
        
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::process_two_i_pts ip1 x: " + ip1.pt.get_x() + " y: " + ip1.pt.get_y() + " z: " + ip1.pt.get_z() + " loc " + ip1_loc);
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::process_two_i_pts ip2 x: " + ip2.pt.get_x() + " y: " + ip2.pt.get_y() + " z: " + ip2.pt.get_z() + " loc " + ip2_loc);
        
            switch (ip1_loc) {
                case internal:
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::process_two_i_pts adding segment p1 x: " << ip1.pt->get_x() << " y: " << ip1.pt->get_y() << " z: " << ip1.pt->get_z() << " p2 x: " << ip2.pt->get_x() << " y: " << ip2.pt->get_y() << " z: " << ip2.pt->get_z() << "\n";
//#endif
                    segments.add_internal_segment(ip1.pt, ip2.pt);
                    break;
                case p1p2:
                    if (ip2_loc != Location.p1p2 && ip2_loc != Location.p1 && 
                            ip2_loc != Location.p2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::process_two_i_pts adding segment p1 x: " << ip1.pt->get_x() << " y: " << ip1.pt->get_y() << " z: " << ip1.pt->get_z() << " p2 x: " << ip2.pt->get_x() << " y: " << ip2.pt->get_y() << " z: " << ip2.pt->get_z() << "\n";
//#endif
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                    }
                    break;
                case p1p3:
                    if (ip2_loc != Location.p1p3 && ip2_loc != Location.p1 && 
                            ip2_loc != Location.p3) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::process_two_i_pts adding segment p1 x: " << ip1.pt->get_x() << " y: " << ip1.pt->get_y() << " z: " << ip1.pt->get_z() << " p2 x: " << ip2.pt->get_x() << " y: " << ip2.pt->get_y() << " z: " << ip2.pt->get_z() << "\n";
//#endif
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                    }
                    break;
                case p2p3:
                    if (ip2_loc != Location.p2p3 && ip2_loc != Location.p2 && 
                            ip2_loc != Location.p3) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::process_two_i_pts adding segment p1 x: " << ip1.pt->get_x() << " y: " << ip1.pt->get_y() << " z: " << ip1.pt->get_z() << " p2 x: " << ip2.pt->get_x() << " y: " << ip2.pt->get_y() << " z: " << ip2.pt->get_z() << "\n";
//#endif
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                    }
                    break;
                case p1:
                    if (ip2_loc != Location.p1p2 && ip2_loc != Location.p1p3 && 
                            ip2_loc != Location.p2 && ip2_loc != Location.p3) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::process_two_i_pts adding segment p1 x: " << ip1.pt->get_x() << " y: " << ip1.pt->get_y() << " z: " << ip1.pt->get_z() << " p2 x: " << ip2.pt->get_x() << " y: " << ip2.pt->get_y() << " z: " << ip2.pt->get_z() << "\n";
//#endif
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                    }
                    break;
                case p2:
                    if (ip2_loc != Location.p1p2 && ip2_loc != Location.p2p3 && 
                            ip2_loc != Location.p1 && ip2_loc != Location.p3) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::process_two_i_pts adding segment p1 x: " << ip1.pt->get_x() << " y: " << ip1.pt->get_y() << " z: " << ip1.pt->get_z() << " p2 x: " << ip2.pt->get_x() << " y: " << ip2.pt->get_y() << " z: " << ip2.pt->get_z() << "\n";
//#endif
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                    }
                    break;
                case p3:
                    if (ip2_loc != Location.p1p3 && ip2_loc != Location.p2p3 && 
                            ip2_loc != Location.p1 && ip2_loc != Location.p2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::process_two_i_pts adding segment p1 x: " << ip1.pt->get_x() << " y: " << ip1.pt->get_y() << " z: " << ip1.pt->get_z() << " p2 x: " << ip2.pt->get_x() << " y: " << ip2.pt->get_y() << " z: " << ip2.pt->get_z() << "\n";
//#endif
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                    }
                    break;
                default:
                    throw new IllegalStateException("undefined point location");
            }
        }

        /*
         * Processes a list of intersecting facet side points.  If there is
         * only one point, it does nothing, if there are two, it calls
         * process_two_i_pts, and if there are more than two, it throws a
         * runtime_error because there should only be a maximum of two
         * side intersect points.  If process_two_pts was called, then the 
         * intersect points used are removed from t_intersect_pts
         * 
         * Arguments:
         * i_side_pts: the side intersect points of the intersecting facet side
         * t_intersect_pts: the temporary intersect point list
         */
        private void process_i_side_pts(I_Pt_List i_side_pts, I_Pt_List t_intersect_pts) {
            if (i_side_pts.size() == 2) {
                process_two_i_pts(i_side_pts);
                // remove from temp intersect pts
                Iterator<Intersect_Point> t_it = i_side_pts.iterator();
                Intersect_Point ip1;
                Intersect_Point ip2;
                if (t_it.hasNext())
                    ip1 = t_it.next();
                else
                    throw new IllegalStateException("Expected two intersect points, found zero");
                if (t_it.hasNext())
                    ip2 = t_it.next();
                else
                    throw new IllegalStateException("Expected two intersect points, found only one");
                
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::process_i_side_pts Erasing ip1 x: " + ip1.pt.get_x() + " y: " + ip1.pt.get_y() + " z: " + ip1.pt.get_z() + " f1_loc: " + ip1.f1_loc + " f2_loc: " + ip1.f2_loc);
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::process_i_side_pts Erasing ip2 x: " + ip2.pt.get_x() + " y: " + ip2.pt.get_y() + " z: " + ip2.pt.get_z() + " f1_loc: " + ip2.f1_loc + " f2_loc: " + ip2.f2_loc);
                t_intersect_pts.erase(ip1);
                t_intersect_pts.erase(ip2);
            } else if (i_side_pts.size() > 2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                int count = 0;
//                for (I_Pt_List::const_iterator it = i_side_pts.begin(); it != i_side_pts.end(); ++it)
//                {
//                    cout << "Intersect_Meshes_3D::Facet_Builder::process_i_side_pts: i_side_pts[" << count++ << "] pt x: " << (*it).pt->get_x() << " y: " << (*it).pt->get_y() << " z: " << (*it).pt->get_z() << " f1_loc: " << (*it).f1_loc << " f2_loc: " << (*it).f2_loc << "\n";
//                }
//#endif
                throw new IllegalStateException("invalid number of facet intersecting side points");
            }
        }

        /*
         * Generates internal segments based on the intersect points.
         * 
         * Arguments:
         * intersect_points: the total intersect points
         * intersecting_side_pts: the side intersect points
         * internal_pts: the internal intersect points
         */
        private void gen_internal_segs(I_Pt_List intersect_points, 
                Intersecting_Facet_Side_Pts intersecting_side_pts) {
            
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs begin for_facet1: " + for_facet1);

            I_Pt_List t_intersect_pts = new I_Pt_List();
            Iterator<Intersect_Point> it = intersect_points.iterator();
            while (it.hasNext())
                t_intersect_pts.add(it.next());
        
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs intersecting_side_pts.p1p2_points.size() is " + 
//                    intersecting_side_pts.p1p2_points.size() + " t_intersect_pts.size: " + t_intersect_pts.size());

            process_i_side_pts(intersecting_side_pts.p1p2_points, t_intersect_pts);

//            System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs intersecting_side_pts.p1p3_points.size() is " + 
//                    intersecting_side_pts.p1p3_points.size() + " t_intersect_pts.size: " + t_intersect_pts.size());

            process_i_side_pts(intersecting_side_pts.p1p3_points, t_intersect_pts);

//            System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs intersecting_side_pts.p2p3_points.size() is " + 
//                    intersecting_side_pts.p2p3_points.size() + " t_intersect_pts.size: " + t_intersect_pts.size());

            process_i_side_pts(intersecting_side_pts.p2p3_points, t_intersect_pts);

            // process remaining points
//            System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs facet leftover intersect points: " + 
//                    t_intersect_pts.size());
//            int count = 0;
//            for (Iterator<Intersect_Point> ip_it = t_intersect_pts.iterator(); ip_it.hasNext(); ) {
//                Intersect_Point ip = ip_it.next();
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs t_intersect_pts[" + count++ + "] x: " + 
//                        ip.pt.get_x() + " y: " + ip.pt.get_y() + " z: " + ip.pt.get_z() + " f1_loc: " + 
//                        ip.f1_loc + " f2_loc: " + ip.f2_loc);
//            }
        
        // process remaining single_i_points to form any further internal segments
//        if (t_intersect_pts.size() == 1)
//        {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            const Intersect_Point* ip1(&*t_intersect_pts.begin());
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_internal_segs t_intersect_pts ip1 x: " << 
//                    ip1->pt->get_x() << " y: " << ip1->pt->get_y() << " z: " << ip1->pt->get_z() << 
//                    " f1_loc: " << ip1->f1_loc << " f2_loc: " << ip1->f2_loc << "\n";
//#endif
//        }
            if (t_intersect_pts.size() == 2) {
                // process points
//                I_Pt_List leftover_pts;
//                I_Pt_List::const_iterator t_it = t_intersect_pts.begin();
//                leftover_pts.push_back(*t_it);
//                ++t_it;
//                leftover_pts.push_back(*t_it);
                process_two_i_pts(t_intersect_pts);
            } else if (t_intersect_pts.size() == 3) {
                it = t_intersect_pts.iterator();
                Intersect_Point ip1 = null;
                Intersect_Point ip2 = null;
                Intersect_Point ip3 = null;
                
                if (it.hasNext())
                    ip1 = it.next();
                else
                    throw new IllegalStateException("Expected three Intersect Points, found 0");

                if (it.hasNext())
                    ip2 = it.next();
                else
                    throw new IllegalStateException("Expected three Intersect Points, found 1");

                if (it.hasNext())
                    ip3 = it.next();
                else
                    throw new IllegalStateException("Expected three Intersect Points, found 2");
            
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs 3 intersect points remaining");
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs ip1 x: " + ip1.pt.get_x() + 
//                        " y: " + ip1.pt.get_y() + " z: " + ip1.pt.get_z() + " f1_loc: " + ip1.f1_loc + " f2_loc: " + 
//                        ip1.f2_loc);
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs ip2 x: " + ip2.pt.get_x() + 
//                        " y: " + ip2.pt.get_y() + " z: " + ip2.pt.get_z() + " f1_loc: " + ip2.f1_loc + " f2_loc: " + 
//                        ip2.f2_loc);
//                System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs ip3 x: " + ip3.pt.get_x() + 
//                        " y: " + ip3.pt.get_y() + " z: " + ip3.pt.get_z() + " f1_loc: " + ip3.f1_loc + " f2_loc: " + 
//                        ip3.f2_loc);
            
                // if all points are internal
                if (for_facet1) {
                    if (ip1.f1_loc == Location.internal && ip2.f1_loc == Location.internal && ip3.f1_loc == Location.internal) {
//                        System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs all three intersect points are internal points - adding segments");
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                        segments.add_internal_segment(ip2.pt, ip3.pt);
                        segments.add_internal_segment(ip3.pt, ip1.pt);
                    }
                    else if (!((ip1.f1_loc == Location.p1 || ip1.f1_loc == Location.p2 || ip1.f1_loc == Location.p3) &&
                            (ip2.f1_loc == Location.p1 || ip2.f1_loc == Location.p2 || ip2.f1_loc == Location.p3) &&
                            (ip3.f1_loc == Location.p1 || ip3.f1_loc == Location.p2 || ip3.f1_loc == Location.p3)))
                        throw new IllegalStateException("invalid number of single intersect points: 3");
                } else {
                    if (ip1.f2_loc == Location.internal && ip2.f2_loc == Location.internal && ip3.f2_loc == Location.internal) {
//                        System.out.println("Intersect_Meshes_3D::Facet_Builder::gen_internal_segs all three points are internal points - adding segments");
                        segments.add_internal_segment(ip1.pt, ip2.pt);
                        segments.add_internal_segment(ip2.pt, ip3.pt);
                        segments.add_internal_segment(ip3.pt, ip1.pt);
                    } else if (!((ip1.f2_loc == Location.p1 || ip1.f2_loc == Location.p2 || ip1.f2_loc == Location.p3) &&
                            (ip2.f2_loc == Location.p1 || ip2.f2_loc == Location.p2 || ip2.f2_loc == Location.p3) &&
                            (ip3.f2_loc == Location.p1 || ip3.f2_loc == Location.p2 || ip3.f2_loc == Location.p3)))
                        throw new IllegalStateException("invalid number of single intersect points: 3");
                }
            } else if (t_intersect_pts.size() > 3)
                throw new IllegalStateException("invalid number of facet intersect_points");

//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_internal_segs end. segments size: " << segments.size() << "\n";
//#endif
        }

        /*
         * Create a link segment
         */
        private Line_Segment create_link_segment(Point_3D pt) {
            Line_Segment seg = new Line_Segment(pt, orig_facet_3d.get_point1(), Location.internal);
            if (segments.does_seg_intersect(seg, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
                seg.point2 = orig_facet_3d.get_point2();
                if (segments.does_seg_intersect(seg, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
                    seg.point2 = orig_facet_3d.get_point3();
                    if (segments.does_seg_intersect(seg, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
                        // if none of the corner points can link to the point, then try for side points
                        for (Point_3D side_pt : p1p2_pts) {
                            seg.point2 = side_pt;
                            if (!segments.does_seg_intersect(seg, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
                                segments.add_internal_segment(seg.point1, seg.point2);
                                Iterator<Line_Segment> it = segments.iterator();
                                while (it.hasNext()) {
                                    Line_Segment existingSeg = it.next();
                                    if (existingSeg.equals(seg))
                                        return existingSeg;
                                }
                                throw new IllegalStateException("Unable to locate added line segment");
                            }
                        }
                        for (Point_3D side_pt : p1p3_pts) {
                            seg.point2 = side_pt;
                            if (!segments.does_seg_intersect(seg, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
                                segments.add_internal_segment(seg.point1, seg.point2);
                                Iterator<Line_Segment> it = segments.iterator();
                                while (it.hasNext()) {
                                    Line_Segment existingSeg = it.next();
                                    if (existingSeg.equals(seg))
                                        return existingSeg;
                                }
                                throw new IllegalStateException("Unable to locate added line segment");
                            }
                        }
                        for (Point_3D side_pt : p2p3_pts) {
                            seg.point2 = side_pt;
                            if (!segments.does_seg_intersect(seg, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
                                segments.add_internal_segment(seg.point1, seg.point2);
                                Iterator<Line_Segment> it = segments.iterator();
                                while (it.hasNext()) {
                                    Line_Segment existingSeg = it.next();
                                    if (existingSeg.equals(seg))
                                        return existingSeg;
                                }
                                throw new IllegalStateException("Unable to locate added line segment");
                            }
                        }
                        // try other internal points last chance
                        for (Point_3D internal_pt : internal_pts) {
                            if (internal_pt.get_x() == pt.get_x() && internal_pt.get_y() == pt.get_y() && internal_pt.get_z() == pt.get_z()) // don't process the same internal point
                                continue;
                            seg.point2 = internal_pt;
                            if (!segments.does_seg_intersect(seg, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
                                segments.add_internal_segment(seg.point1, seg.point2);
                                Iterator<Line_Segment> it = segments.iterator();
                                while (it.hasNext()) {
                                    Line_Segment existingSeg = it.next();
                                    if (existingSeg.equals(seg))
                                        return existingSeg;
                                }
                                throw new IllegalStateException("Unable to locate added line segment");
                            }
                        }
                        // else if no link segment could be created,
                        return null;
                    }
                    else
                        segments.add_internal_segment(seg.point1, seg.point2);
                }
                else
                    segments.add_internal_segment(seg.point1, seg.point2);
            }
            else
                segments.add_internal_segment(seg.point1, seg.point2);
            Iterator<Line_Segment> it = segments.iterator();
            while (it.hasNext()) {
                Line_Segment existingSeg = it.next();
                if (existingSeg.equals(seg))
                    return existingSeg;
            }
            throw new IllegalStateException("Unable to locate added line segment");
        }

        /*
         * Look for any internal points that are not part of an internal
         * segment.  Creates a link segment for these or throws a runtime_error
         * if no link segment could be created.
         */
        private void check_internal_pts() {
            // go through internal points and look for any not in a segment
            Iterator<Point_3D> it = internal_pts.iterator();
            while (it.hasNext()) {
                Point_3D pt = it.next();
                boolean found = false;
                Iterator<Line_Segment> seg_it = segments.iterator();
                while (seg_it.hasNext()) {
                    Line_Segment seg = seg_it.next();
                    if (seg.location != Location.internal)
                        break;
                    if (seg.point1 == pt || seg.point2 == pt) {
                        found = true;
                        break;
                    }
                }
                if (!found) { // create a link segment to the point
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::check_internal_pts found isolated intersect point. Creating link segment to internal point x: " << 
//                            (*it)->get_x() << " y: " << (*it)->get_y() << " z: " << (*it)->get_z() << "\n";
//#endif
                    Line_Segment link_seg = create_link_segment(pt);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    if (link_seg == 0)
//                        cout << "Intersect_Meshes_3D::Facet_Builder::check_internal_pts Could not create link segment to isolated internal point x: " << 
//                                (*it)->get_x() << " y: " << (*it)->get_y() << " z: " << (*it)->get_z() << "\n";
//                    else
//                        cout << "Intersect_Meshes_3D::Facet_Builder::check_internal_pts created link segment p1 x: " << 
//                                link_seg->point1->get_x() << " y: " << link_seg->point1->get_y() << " z: " << 
//                                link_seg->point1->get_z() << " p2 x: " << link_seg->point2->get_x() << " y: " << 
//                                link_seg->point2->get_y() << " z: " << link_seg->point2->get_z() << "\n";
//#endif
                    if (link_seg == null)
                        throw new IllegalStateException("Unable to generate link segment for internal point");
                }
            }
        }

        /*
         * Check if segment is connected to a facet corner or side point.
         * return true if it is, false if both end points are internal points
         */
        private boolean check_internal_seg(Line_Segment seg) {
            // look if points are internal points
            boolean p1_found = false; // found matching internal point for p1
            boolean p2_found = false; // found matching internal point for p2
            Iterator<Point_3D> internal_pt_it = internal_pts.iterator();
            while (internal_pt_it.hasNext()) {
                Point_3D pt = internal_pt_it.next();
                if (seg.point1 == pt)
                    p1_found = true;
                else if (seg.point2 == pt)
                    p2_found = true;
            }
            return !(p1_found && p2_found);
        }

        private Line_Segment findLinkSeg(Point_3D pt, Iterator<Line_Segment> segment_it) {
            
            Line_Segment seg = null;
            while (segment_it.hasNext()) {
                
                Line_Segment tmp = segment_it.next();
                if (pt == tmp.point1 || pt == tmp.point2) {
                    seg = tmp;
                    break;
                }
            }
            
            return seg;
        }
        
        private boolean find_path(Line_Segment currentSeg, Point_3D point, 
                List<Line_Segment> path, List<Line_Segment> checked_segs) {
            
            // create a stack to process all the connecting segments found on the path
            Stack<Point_3D> pt_stack = new Stack<>(); // non shared point
            // create path from point
            Iterator<Line_Segment> prev_seg_it = segments.iterator();
            while (prev_seg_it.hasNext()) {
                if (prev_seg_it.next() == currentSeg)
                    break;
            }
            // create path from point
            Point_3D pt = point;
            Line_Segment nextSeg = findLinkSeg(pt, prev_seg_it);
            while (nextSeg != null && nextSeg.location == Location.internal) {
                // check if segment is already in the path - forms a circle
                if (nextSeg == currentSeg || path.contains(nextSeg)) {
                    // move to next segment
                    // move to next segment
                    nextSeg = findLinkSeg(pt, prev_seg_it);
                    if (nextSeg == null || nextSeg.location != Location.internal) {
                        // look for a segment in the stack
                        if (!pt_stack.empty()) {
                            pt = pt_stack.pop();
                            nextSeg = findLinkSeg(pt, prev_seg_it);
                        }
                    }
                } else if (checked_segs.contains(nextSeg)) // check if segment is a checked segment
                    return true; // found segment path to corner or side of facet
                else {
                    // add line segment to path
                    path.add(nextSeg);
                    if (check_internal_seg(nextSeg)) // check if the segment does connect with the perimeter
                        return true;
                    else {
                        // add non shared point to stack
                        pt_stack.push((pt == nextSeg.point1) ? nextSeg.point2 : nextSeg.point1);
                        // get next connecting segment
                        nextSeg = findLinkSeg(pt, prev_seg_it);
                        if (nextSeg == null || nextSeg.location != Location.internal) { // no more connecting segments
                            // look for a segment in the stack
                            if (!pt_stack.empty()) {
                                pt = pt_stack.pop();
                                nextSeg = findLinkSeg(pt, prev_seg_it);
                            }
                        }
                    }
                }
            }
            return false;
        }

        private boolean complete_path(List<Line_Segment> path, 
                List<Line_Segment> checked_segs, Line_Segment segment) {
            
            boolean found = false;
            // create a link segment from point1
            Line_Segment link_seg = create_link_segment(segment.point1);
            // check link segment
            if (link_seg != null) {
                if (check_internal_seg(link_seg)) {
                    checked_segs.add(link_seg);
                    checked_segs.add(segment);
                    // add all path elements to checked_segs
                    Iterator<Line_Segment> path_it = path.iterator();
                    while (path_it.hasNext())
                        checked_segs.add(path_it.next());
                    return true;
                } else
                    path.add(link_seg);
            }
            // try from point2
            link_seg = create_link_segment(segment.point2);
            // check link segment
            if (link_seg != null) {
                if (check_internal_seg(link_seg)) {
                    checked_segs.add(link_seg);
                    checked_segs.add(segment);
                    // add all path elements to checked_segs
                    Iterator<Line_Segment> path_it = path.iterator();
                    while (path_it.hasNext())
                        checked_segs.add(path_it.next());
                    return true;
                } else
                    path.add(link_seg);
            }
            return false;
        }

        private boolean findLinkSeg(Line_Segment segment, Iterator<Line_Segment> segment_it) {
            
            boolean found = false;
            while (segment_it.hasNext()) {
                
                Line_Segment seg = segment_it.next();
                if (segment.point1 == seg.point1 || segment.point2 == seg.point1 ||
                        segment.point1 == seg.point2 || segment.point2 == seg.point2) {
                    found = true;
                    break;
                }
                
            }
            
            return found;
        }
        
        /*
         * Check for any internal points without segments or any internal
         * segments not connected to a corner or side point.
         */
        private void verify_internal_paths() {
            List<Line_Segment> checked_segs = new ArrayList<>();
            // Go through internal segments and verify that they are connected to
            // a side or corner point
            Iterator<Line_Segment> seg_it = segments.iterator();
            while (seg_it.hasNext()) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs checking internal segment p1 x: " << 
//                        seg_it->point1->get_x() << " y: " << seg_it->point1->get_y() << " z: " <<
//                        seg_it->point1->get_z() << " p2 x: " << seg_it->point2->get_x() << " y: " <<
//                        seg_it->point2->get_y() << " z: " << seg_it->point2->get_z() << "\n";
//#endif
                Line_Segment currentSeg = seg_it.next();
                if (checked_segs.contains(currentSeg)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs segment has already been verified\n";
//#endif
                    continue; // already checked segment, so go to next one
                }

                if (check_internal_seg(currentSeg)) { // if one of the points is not an internal point
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs segment contains a perimeter point\n";
//#endif
                    checked_segs.add(currentSeg); // segment connects to facet perimeter
                } else { // segment has both points inside the facet
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs segment point1 and point2 are internal points\n";
//#endif
                    // segment is fully inside facet, try locating linking segments on either side of the segment and follow
                    // the trail to a side or corner point

                    // check if there is an already processed segment that shares point1
                    if (findLinkSeg(currentSeg, checked_segs.iterator())) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs segment shares point1 with a segment connected to the perimeter\n";
//#endif
                        checked_segs.add(currentSeg);
                        continue;
                    }
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Finding path to perimeter\n";
//#endif
                    // no connecting segment linked to a side or corner point was found
                    // create a stack of connecting segments looking for a segment that is connected to 
                    List<Line_Segment> path = new ArrayList<>();
                    path.add(currentSeg);
                    if (find_path(currentSeg, currentSeg.point1, path, checked_segs)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Found path to perimeter from point1.  Adding path to checked_segs\n";
//#endif
                        // add all path elements to checked_segs
                        checked_segs.addAll(path);
                    } else if (find_path(currentSeg, currentSeg.point2, path, checked_segs)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Found path to perimeter from point2.  Adding path to checked_segs\n";
//#endif
                        // add all path elements to checked_segs
                        checked_segs.addAll(path);
                    } else { // create a link segment
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Could not find path to perimeter.  Generating a link segment\n";
//#endif
                        // try to link segment with perimeter of facet
                        if (complete_path(path, checked_segs, path.get(0))) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                            cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Generated a link segment to the perimeter\n";
//#endif
                            Iterator<Line_Segment> it = segments.iterator();
                            while (it.hasNext()) {
                                if (it.next() == path.get(0))
                                    break;
                            }
                            seg_it = it;
                            continue;
                        } else if (path.size() > 1) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                            cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Unable to generate a link segment to the perimeter\n";
//#endif
                            // try from other segments in the path
                            boolean found = false;
                            Iterator<Line_Segment> path_it = path.iterator();
                            if (path_it.hasNext())
                                path_it.next();
                            while (path_it.hasNext()) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                                cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Attempting to generate a link segment from segment p1 x: " << 
//                                        path_it->point1->get_x() << " y: " << path_it->point1->get_y() << " z: " << path_it->point1->get_z() << " p2 x: " << 
//                                        path_it->point2->get_x() << " y: " << path_it->point2->get_y() << " z: " << path_it->point2->get_z() << "\n";
//#endif
                                Line_Segment path_seg = path_it.next();
                                if (complete_path(path, checked_segs, path_seg)) {
                                    found = true;
                                    break;
                                } else {
                                    Iterator<Line_Segment> it = path.iterator();
                                    while (it.hasNext()) {
                                        if (it.next() == path_seg)
                                            break;
                                    }
                                    path_it = it;
                                }
                            }
                            if (found) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                                cout << "Intersect_Meshes_3D::Facet_Builder::verify_internal_segs Generated a link segment to the perimeter\n";
//#endif
                                Iterator<Line_Segment> it = segments.iterator();
                                while (it.hasNext()) {
                                    if (it.next() == path.get(0))
                                        break;
                                }
                                seg_it = it;
                                continue;
                            } else
                                throw new IllegalStateException("Unable to link segment with facet perimeter");
                        }
                    }
                }
            }
        }

        /*
         * Check for internal segments that are in a straight line without
         * another segment connecting to the shared point
         */
        private void verify_internal_segs() {
            // check each internal segment if it forms a straight line with 
            // another internal segment
            Iterator<Line_Segment> seg_it = segments.iterator();
            while (seg_it.hasNext()) {
                Line_Segment currentSeg = seg_it.next();
                if (currentSeg.location == Location.internal)
                    break;
                
                // check if segment point1 internal point
                if (internal_pts.contains(currentSeg.point1)) {
                    // try to find a connecting segment to the internal point
                    List<Line_Segment> connecting_segs = new ArrayList<>();
                    Iterator<Line_Segment> it = segments.iterator();
                    while (it.hasNext()) {
                        if (it.next() == currentSeg)
                            break;
                    }
                    Line_Segment foundSeg = findLinkSeg(currentSeg.point1, it);
                    while (foundSeg != null) {
                        connecting_segs.add(foundSeg);
                        foundSeg = findLinkSeg(currentSeg.point1, it);
                    }
                    if (connecting_segs.size() == 1) {
                        Bool same_direction = new Bool(false);
                        if (Vector_3D.is_same_line(currentSeg.point1, currentSeg.point2, 
                                connecting_segs.get(0).point1, connecting_segs.get(0).point2, 
                                same_direction, precision)) {
//                            Line_Segment seg(*seg_it);
                            // segments are in a straight line
                            // add a link segment to the common point
                            create_link_segment(currentSeg.point1);
                            seg_it = segments.iterator();
                            while (seg_it.hasNext()) {
                                if (seg_it.next() == currentSeg)
                                    break;
                            }
                        }
                    }
                }
                // check if segment point2 internal point
                if (internal_pts.contains(currentSeg.point2)) {
                    // try to find a connecting segment to the internal point
                    List<Line_Segment> connecting_segs = new ArrayList<>();
                    Iterator<Line_Segment> it = segments.iterator();
                    while (it.hasNext()) {
                        if (it.next() == currentSeg)
                            break;
                    }
                    Line_Segment foundSeg = findLinkSeg(currentSeg.point2, it);
                    while (foundSeg != null) {
                        connecting_segs.add(foundSeg);
                        foundSeg = findLinkSeg(currentSeg.point2, it);
                    }
                    if (connecting_segs.size() == 1) {
                        Bool same_direction = new Bool(false);
                        if (Vector_3D.is_same_line(currentSeg.point1, currentSeg.point2, 
                                connecting_segs.get(0).point1, connecting_segs.get(0).point2, 
                                same_direction, precision)) {
//                            Line_Segment seg(*seg_it);
                            // segments are in a straight line
                            // add a link segment to the common point
                            create_link_segment(currentSeg.point2);
                            seg_it = segments.iterator();
                            while (seg_it.hasNext()) {
                                if (seg_it.next() == currentSeg)
                                    break;
                            }
                        }
                    }
                }
            }
        }

        /*
         * form the outside segments of the facet using side_point data. 
         * p1 and p2 are the corner points of the facet side
         * 
         * Arguments:
         * side_points: the side intersect points found
         * side: the side to form facets for (p1p2, p1p3, or p2p3)
         * p1: the side corner point (p1 or p2)
         * p2: the side end point (p2 or p3)
         */
        private void gen_side_segs(List<Point_3D> side_points, Location side, 
                Point_3D p1, Point_3D p2) {
            
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs begin\n";
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs p1 x: " << p1->get_x() << " y: " << p1->get_y() << " z: " << p1->get_z() << "\n";
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs p2 x: " << p2->get_x() << " y: " << p2->get_y() << " z: " << p2->get_z() << "\n";
//            for (vector<shared_ptr<Point_3D>>::const_iterator it = side_points.begin(); it != side_points.end(); ++it)
//                cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs side_point x: " << (*it)->get_x() << 
//                        " y: " << (*it)->get_y() << " z: " << (*it)->get_z() << "\n";
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs removing corner points\n";
//#endif
        
        // remove any corner points
//        vector<shared_ptr<Point_3D>> side_pts;
//        for (I_Pt_List::const_iterator it = side_points.begin(); it != side_points.end(); ++it)
//        {
//            if ((for_facet1 && (*it).f1_loc == side) || 
//                    (!for_facet1 && (*it).f2_loc == side))
//                side_pts.push_back((*it).pt);
//        }
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs side_points size: " << side_points.size() << "\n";
//            for (vector<shared_ptr<Point_3D>>::const_iterator it = side_points.begin(); it != side_points.end(); ++it)
//                cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs side_points x: " << (*it)->get_x() << " y: " << 
//                        (*it)->get_y() << " z: " << (*it)->get_z() << "\n";
//#endif        
        
            if (side_points.isEmpty()) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs adding segment: p1 x: " << p1->get_x() << 
//                        " y: " << p1->get_y() << " z: " << p1->get_z() << " ptr: " << p1.get() << " p2 x: " << 
//                        p2->get_x() << " y: " << p2->get_y() << " z: " << p2->get_z() << " ptr: " << p2.get()  << "\n";
//                int size = segments.size();
//#endif
                segments.add_external_segment(p1, p2, side);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                if (segments.size() > size)
//                    cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs added segment\n";
//                else
//                    cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs did not add segment\n";
//#endif
            } else {
                class Point_Sort implements Comparator<Point_3D> {
                    private final Point_3D corner_pt;
            
                    public Point_Sort(Point_3D corner_point) {
                        super();
                        corner_pt = corner_point;
                    }
                    
                    @Override
                    public int compare(Point_3D pt1, Point_3D pt2) {
                        double l1 = new Vector_3D(corner_pt, pt1).length();
                        double l2 = new Vector_3D(corner_pt, pt2).length();
                        if (l1 < l2)
                            return  -1;
                        else if (l1 > l2)
                            return 1;
                        return 0;
                    }
                }
                
                side_points.sort(new Point_Sort(p1));

                Point_3D prev_pt = p1; // start with the corner point
                Iterator<Point_3D> it = side_points.iterator();
                while (it.hasNext()) {
                    Point_3D pt = it.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs adding segment: p1 x: " << prev_pt->get_x() << 
//                            " y: " << prev_pt->get_y() << " z: " << prev_pt->get_z() << " ptr: " << prev_pt << " p2 x: " << 
//                            (*it)->get_x() << " y: " << (*it)->get_y() << " z: " << (*it)->get_z() << " ptr: " << (*it) << "\n";
//                    int size = segments.size();
//#endif
                    segments.add_external_segment(prev_pt, pt, side);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    if (segments.size() > size)
//                        cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs added segment\n";
//                    else
//                        cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs did not add segment\n";
//#endif
                    prev_pt = pt;
                }
                // add last segment
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs add last segment: p1 x: " << prev_pt->get_x() << 
//                        " y: " << prev_pt->get_y() << " z: " << prev_pt->get_z() << " ptr: " << prev_pt.get() << " p2 x: " << 
//                        p2->get_x() << " y: " << p2->get_y() << " z: " << p2->get_z() << " ptr: " << p2.get() << "\n";
//                int size = segments.size();
//#endif
                segments.add_external_segment(prev_pt, p2, side);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                if (segments.size() > size)
//                    cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs added segment\n";
//                else
//                    cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segs did not add segment\n";
//#endif
            }
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::gen_side_segments end\n";
//#endif
        }

        /*
         * Determine if a facet contains an internal point
         * 
         * Arguments:
         * p1: point1 of the facet
         * p2: point2 of the facet
         * p3: point3 of the facet
         * facet: the facet made up of p1, p2, and p3
         */
        private boolean contains_internal_pt(Point_3D p1, Point_3D p2, Point_3D p3, Facet_3D facet) {
            Iterator<Point_3D> internal_pt_iter = internal_pts.iterator();
            while (internal_pt_iter.hasNext()) {
                Point_3D internal_pt = internal_pt_iter.next();

                Bool pt_on_side = new Bool(false);
                if (!Point_3D.is_equal(internal_pt, p1, precision) && 
                        !Point_3D.is_equal(internal_pt, p2, precision) && 
                        !Point_3D.is_equal(internal_pt, p3, precision) && 
                        facet.contains_point(internal_pt, pt_on_side, precision))
                    return true;
            }

            return false;
        }

        /*
         * Creates new facets, but keeps overlapping facets the same as other_new_facets
         * 
         * Arguments:
         * side_pts: the side intersect points found
         * internal_points: the internal intersect points found
         * new_facets: the function will put newly generated facets in this list
         */
        private void build_facets(Facets new_facets) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::build_facets orig_facet p1 x: " << 
//                    orig_facet.get_point1()->get_x() << " y: " << orig_facet.get_point1()->get_y() << " z: " << 
//                    orig_facet.get_point1()->get_z() << " ptr: " << (orig_facet.get_point1().get()) << " p2 x: " << 
//                    orig_facet.get_point2()->get_x() << " y: " << orig_facet.get_point2()->get_y() << " z: " << 
//                    orig_facet.get_point2()->get_z() << " ptr: " << (orig_facet.get_point2().get()) << " p3 x: " << 
//                    orig_facet.get_point3()->get_x() << " y: " << orig_facet.get_point3()->get_y() << " z: " << 
//                    orig_facet.get_point3()->get_z() << " ptr: " << (orig_facet.get_point3().get()) << "\n";
//            cout << "Intersect_Meshes_3D::Facet_Builder::build_facets: internal_segments:\n";
//            cout.flush();
//            Segments::const_iterator temp_it(segments.begin());
//            while (temp_it != segments.end() && temp_it->location == Location::internal)
//            {
//                cout << "Intersect_Meshes_3D::Facet_Builder::build_facets segment p1 x: " << temp_it->point1->get_x() << 
//                        " y: " << temp_it->point1->get_y() << " z: " << temp_it->point1->get_z() << " ptr: " << 
//                        temp_it->point1 << " p2 x: " << temp_it->point2->get_x() << " y: " << temp_it->point2->get_y() << 
//                        " z: " << temp_it->point2->get_z() << " ptr: " << temp_it->point2 << " used: " << temp_it->used << "\n";
//                ++temp_it;
//            }
//            cout << "Intersect_Meshes_3D::Facet_Builder::build_facets: external_segments:\n";
//            cout.flush();
//            while (temp_it != segments.end())
//            {
//                cout << "Intersect_Meshes_3D::Facet_Builder::build_facets segment p1 x: " << temp_it->point1->get_x() << 
//                        " y: " << temp_it->point1->get_y() << " z: " << temp_it->point1->get_z() << " p2 x: " << 
//                        temp_it->point2->get_x() << " y: " << temp_it->point2->get_y() << " z: " << temp_it->point2->get_z() << 
//                        " loc: " << temp_it->location << "\n";
//                ++temp_it;
//            }
//            cout.flush();
//            for (vector<shared_ptr<Point_3D>>::const_iterator it = internal_pts.begin(); it != internal_pts.end(); ++it)
//            {
//                cout << "Intersect_Meshes_3D::Facet_Builder::build_facets internal point x: " << (*it)->get_x() << 
//                        " y: " << (*it)->get_y() << " z: " << (*it)->get_z() << "\n";
//            }
//            cout.flush();
//            cout << "Intersect_Meshes_3D::Facet_Builder::build_facets begin\n";
//            cout.flush();
//#endif
            Line_Segment segment1 = segments.get_next_segment(null); // get initial segment
            while (segment1 != null) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::build_facets segment1 p1 x=" << 
//                        segment1.point1->get_x() << ", y=" << segment1.point1->get_y() << ", z=" << 
//                        segment1.point1->get_z() << " ptr: " << segment1.point1 << " p2(x=" << 
//                        segment1.point2->get_x() << ", y=" << segment1.point2->get_y() << ", z=" << 
//                        segment1.point2->get_z() << " ptr: " << segment1.point2 << "\n";
//                cout.flush();
//#endif
                Shared_Point shared_pt = new Shared_Point();
                Point_3D p2 = null;
                Point_3D p3 = null;
            
                Line_Segment segment2 = segments.find_connecting_segment(segment1, null, shared_pt, precision);
//                cout << "segp=" << segp << "\n";
//                cout.flush();
                while (segment2 != null) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::build_facets segment2 p1 x=" << 
//                            segment2.point1->get_x() << ", y=" << segment2.point1->get_y() << ", z=" << 
//                            segment2.point1->get_z() << " ptr: " << segment2.point2 << " p2 x=" << 
//                            segment2.point2->get_x() << ", y=" << segment2.point2->get_y() << ", z=" << 
//                            segment2.point2->get_z() << " ptr: " << segment2.point2 << "\n";
//                    cout.flush();
//#endif

                    // shared_pt is p1
                    p2 = segment1.point1 == shared_pt.pt ? segment1.point2 : segment1.point1;
                    p3 = segment2.point1 == shared_pt.pt ? segment2.point2 : segment2.point1;

                    // do not know location now, so use invalid segment location p1
                    Line_Segment seg3 = new Line_Segment(p2, p3, Location.p1);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::build_facets segment3 p1 x=" << 
//                            seg3.point1->get_x() << ", y=" << seg3.point1->get_y() << ", z=" << 
//                            seg3.point1->get_z() << " ptr: " << seg3.point1 << " p2 x=" << 
//                            seg3.point2->get_x() << ", y=" << seg3.point2->get_y() << ", z=" << 
//                            seg3.point2->get_z() << " ptr: " << seg3.point2 << "\n";
//                    cout << "Intersect_Meshes_3D::Facet_Builder::build_facets checking if segment3 intersects any other segments\n";
//                    cout.flush();
//#endif
                    if (segments.does_seg_intersect(seg3, orig_facet_3d, p1p2_pts, p1p3_pts, p2p3_pts, internal_pts, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::build_facets segment3 intersects another segment\n";
//                        cout.flush();
//#endif
                        segment2 = segments.find_connecting_segment(segment1, segment2, shared_pt, precision);
                        continue;
                    }

                    // create facet
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::build_facets forming facet p1(x=" << 
//                            shared_pt->get_x() << ", y=" << shared_pt->get_y() << ", z=" << shared_pt->get_z() << 
//                            "), p2(x=" << p2->get_x() << ", y=" << p2->get_y() << ", z=" << p2->get_z() << 
//                            "), p3(x=" << p3->get_x() << ", y=" << p3->get_y() << ", z=" << p3->get_z() << ")\n";
//                    cout.flush();
//#endif
                    Facet_3D facet = new Facet_3D(shared_pt.pt, p2, p3);

//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::build_facets checking if facet has already been created\n";
//                    cout.flush();
//#endif
                    // make sure facet unit normal is pointing in the same direction
                    // as orig_facet
                    if (Vector_3D.dot_product(facet.get_unv(), orig_facet_3d.get_unv()) < 0) {
                        facet.invert_unv();
                        // swap p2 and p3
                        Point_3D tmp = p2;
                        p2 = p3;
                        p3 = tmp;
                    }

                    // check if facet already exists
                    if (new_facets.contains(facet)) { // new_facets.end() != find_if(new_facets.begin(), new_facets.end(), Facet_find(facet, precision)))
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::build_facets facet has already been created\n";
//                        cout.flush();
//#endif
                        segment2 = segments.find_connecting_segment(segment1, segment2, shared_pt, precision);
                        continue;
                    }

//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                    cout << "Intersect_Meshes_3D::Facet_Builder::build_facets checking if facet contains an internal point\n";
//                    cout.flush();
//#endif
                    // check if facet contains an internal point
                    if (contains_internal_pt(shared_pt.pt, p2, p3, facet)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::build_facets facet contains an internal point\n";
//                        cout.flush();
//#endif
                        segment2 = segments.find_connecting_segment(segment1, segment2, shared_pt, precision);
                    } else {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                        cout << "Intersect_Meshes_3D::Facet_Builder::build_facets facet does not contain an internal point.  found facet to add\n";
//                        cout.flush();
//#endif
                        break;
                    }
                }

                if (segment2 == null) {
                    Line_Segment seg = segments.get_next_segment(segment1);
                    if (seg != null && seg == segment1)
                        throw new IllegalStateException("next segment is the same segment");
                    segment1 = seg;
                    continue;
                }

                // update / remove segments as necessary
                segments.process_used_segments(segment1, segment2);

//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::build_facets adding facet: " << new_facets.size() << "\n";
//                cout.flush();
//#endif
                new_facets.add(new Facet_3D(shared_pt.pt, p2, p3));
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//                cout << "Intersect_Meshes_3D::Facet_Builder::build_facets added facet: " << new_facets.size() << "\n";
//                cout.flush();
//#endif
                segment1 = segments.get_next_segment(null);
            }
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
//            cout << "Intersect_Meshes_3D::Facet_Builder::build_facets end\n";
//            cout.flush();
//#endif
        }
            
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER
        private double facet_area(Facet_3D facet) {
            Vector_3D p1p2 = new Vector_3D(facet.get_point1(), facet.get_point2());
            Vector_3D p1p3 = new Vector_3D(facet.get_point1(), facet.get_point3());
            Vector_3D p2p3 = new Vector_3D(facet.get_point2(), facet.get_point3());

            if (p1p2.length() >= p1p3.length() && p1p2.length() > p2p3.length()) {
                // p1p2 is the longest side
                if (p1p3.length() > p2p3.length()) {
                    // p1p3 is the next longest
                    return Math.abs(0.5 * Vector_3D.cross_product(p1p2, p1p3).length());
                } else {
                    // p2p3 is the next longest
                    return Math.abs(0.5 * Vector_3D.cross_product(p1p2.minus(), p2p3).length());
                }
            } else if (p1p3.length() >= p1p2.length() && p1p3.length() >= p2p3.length()) {
                // p1p3 is the longest
                if (p1p2.length() > p2p3.length()) {
                    // p1p2 is the next longest
                    return Math.abs(0.5 * Vector_3D.cross_product(p1p3, p1p2).length());
                } else {
                    // p2p3 is the next longest
                    return Math.abs(0.5 * Vector_3D.cross_product(p1p3.minus(), p2p3.minus()).length());
                }
            } else {
                // p2p3 is the longest
                if (p1p2.length() > p1p3.length()) {
                    // p1p2 is the next longest
                    return Math.abs(0.5 * Vector_3D.cross_product(p2p3, p1p2.minus()).length());
                } else {
                    // p1p3 is the next longest
                    return Math.abs(0.5 * Vector_3D.cross_product(p2p3.minus(), p1p3.minus()).length());
                }
            }
        }
//#endif

        private class Shared_Point {
            public Point_3D pt;
            
            public Shared_Point() {
                super();
                pt = null;
            }
        }
        
        /*
         * Facet is broken up into line segments.  The segments are used to form
         * new Facets.
         */
        private class Line_Segment {
            public boolean used; // internal line segments are used twice, external line segments only once
            public Location location; // the location of the segment (internal or one of p1p2, p1p3, or p2p3)
            public Point_3D point1; // the beginning of the line segment
            public Point_3D point2; // the end of the line segment
            
            /*
             * Constructor
             *
             * Arguments:
             * p1: the start of the line segment
             * p2: the end of the line segment
             * location: the location of the line segment (internal or one of the sides p1p2, p1p3, or p2p3)
             */
            public Line_Segment(Point_3D p1, Point_3D p2, Location location) {
                super();
                used = false;
                this.location = location;
                this.point1 = p1;
                this.point2 = p2;
            }
            
            /*
             * determines if the line segments are equal (share same points.  Location is not considered)
             */
            @Override
            public int hashCode() {
                int hash = 7;
                hash = 53 * hash + Objects.hashCode(this.location);
                hash = 53 * hash + Objects.hashCode(this.point1);
                hash = 53 * hash + Objects.hashCode(this.point2);
                return hash;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null)
                    return false;
                if (obj instanceof Line_Segment) {
                    Line_Segment seg = (Line_Segment) obj;
                    
                    return (point1 == seg.point1 || point1 == seg.point2) && 
                            (point2 == seg.point2 || point2 == seg.point1);
                } else
                    return false;
            }
            
            /*
             * checks if the other line segment shares a point with this segment
             * 
             * Arguments:
             * seg: the segment to check
             * shared_pt: function sets this to the shared point if one is found
             */
            public boolean shares_pt(Line_Segment seg, Shared_Point shared_pt) {
                if (point1 == seg.point1 || point1 == seg.point2) {
                    shared_pt.pt = point1;
                    return true;
                } else if (point2 == seg.point1 || point2 == seg.point2) {
                    shared_pt.pt = point2;
                    return true;
                }

                return false;
            }
        }

        /*
         * A grouping of segment objects.  Ensures that any segment added does 
         * not intersect an already existing segment
         */
        private class Segments implements Iterable<Line_Segment> {
            private final List<Line_Segment> segments;
            private final List<Line_Segment> removed_segs;
            
            public Segments() {
                super();
                segments = new ArrayList<>();
                removed_segs = new ArrayList<>();
            }
            
            @Override
            public Iterator<Line_Segment> iterator() {
                return segments.iterator();
            }

            public int size() { // overall size of internal and external segments
                return segments.size();
            }
            
            public void clear() { // clear segments
                segments.clear();
                removed_segs.clear();
            }
            
            /*
             * determines if the segment already exists in either external or internal segments
             * 
             * Arguments:
             * p1: the start of the segment
             * p2: the end of the segment
             * 
             * returns true if the segment was found, false otherwise
             */
            public boolean contains_segment(Point_3D p1, Point_3D p2) {
                // location does not matter, so use p1 since it isn't a valid segment location
                Line_Segment seg = new Line_Segment(p1, p2, Location.p1);
                return segments.contains(seg);
            }
            
            // add an internal segment
            public void add_internal_segment(Point_3D p1, Point_3D p2) {
                Line_Segment seg = new Line_Segment(p1, p2, Location.internal);
                if (!segments.contains(seg)) {
                    segments.add(seg);
                    segments.sort(new Comparator<Line_Segment>() {
                        @Override
                        public int compare(Line_Segment seg1, Line_Segment seg2) {
                            // move internal segments to the front
                            if (seg1.location == Location.internal) {
                                return -1;
                            } else if (seg2.location == Location.internal) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    });
                }
            }
            
            // add an external segment. side_loc specifies the side of the segment on the original facet
            public void add_external_segment(Point_3D p1, Point_3D p2, Location side_loc) {
                // don't check if it already exists because it shouldn't
                segments.add(new Line_Segment(p1, p2, side_loc));
                // don't sort because is is already in the back
            }
            
            /*
             * get the next segment
             * 
             * Arguments:
             * prev_Segment: the previous segment found.  Can be zero to indicate the first time used
             * 
             * returns a pointer to the next segment or zero if no segments are found
             */
            public Line_Segment get_next_segment(Line_Segment prev_segment) {
                if (!segments.isEmpty()) {
                    Iterator<Line_Segment> it = segments.iterator();
                    if (prev_segment != null) {
                        while (it.hasNext()) {
                            if (it.next() == prev_segment)
                                break;
                        }
                    }
                    if (it.hasNext())
                        return it.next();
                }

                return null;
            }
            
            /*
             * find the line segment and return a pointer to it
             * 
             * Arguments:
             * p1: the start of the segment
             * p2: the end of the segment
             * 
             * returns a pointer to the segment or throws runtime_error if 
             * it could not find the segment
             */
            public Line_Segment find_segment(Point_3D p1, Point_3D p2) {
                // location does not matter, so use p1 since it is not a valid segment location
                Line_Segment seg = new Line_Segment(p1, p2, Location.p1);
                Iterator<Line_Segment> it = segments.iterator();
                while (it.hasNext()) {
                    Line_Segment segment = it.next();
                    if (segment.equals(seg)) {
                        return segment;
                    }
                }
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
            public Line_Segment find_connecting_segment(Line_Segment segment, 
                    Line_Segment prev_connecting_seg, Shared_Point shared_pt, 
                    double precision) {
                
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment begin\n";
//#endif
                Shared_Point shared_point = new Shared_Point();
                if (!segments.isEmpty()) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    for (vector<Line_Segment>::const_iterator t_it = segments.begin(); t_it != segments.end(); ++t_it)
//                        cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment segments p1 x: " << (*t_it).point1->get_x() << " y: " <<
//                                (*t_it).point1->get_y() << " z: " << (*t_it).point1->get_z() << " p2 x: " << (*t_it).point2->get_x() <<
//                                " y: " << (*t_it).point2->get_y() << " z: " << (*t_it).point2->get_z() << "\n";
//#endif
                    Iterator<Line_Segment> it = segments.iterator();
                    if (prev_connecting_seg != null) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                        cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_conneting_segment locating last connecting segment in segments p1 x: " << 
//                                prev_connecting_seg->point1->get_x() << " y: " << prev_connecting_seg->point1->get_y() << 
//                                " z: " << prev_connecting_seg->point1->get_z() << " p2 x: " << prev_connecting_seg->point2->get_x() << 
//                                " y: " << prev_connecting_seg->point2->get_y() << " z: " << prev_connecting_seg->point2->get_z() << "\n";
//#endif
                        while (it.hasNext()) {
                            if (it.next() == prev_connecting_seg) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment found segment\n";
//#endif
                                break;
                            }
                        }
                    }
                    while (it.hasNext()) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                        cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment checking segment p1 x: " << 
//                                it->point1->get_x() << " y: " << it->point1->get_y() << " z: " << it->point1->get_z() << " p2 x: " << 
//                                it->point2->get_x() << " y: " << it->point2->get_y() << " z: " << it->point2->get_z() << "\n";
//#endif
                        Line_Segment seg = it.next();
                        if (segment == seg)  // do not return the same segment
                            continue;
 
                        if (segment.shares_pt(seg, shared_point)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment segment shared pt x: " << shared_point->get_x() << " y: " << 
//                                    shared_point->get_y() << " z: " << shared_point->get_z() << "\n";
//#endif
                            if (segment.location == Location.internal) {
                                if (seg.location == Location.internal) {
                                    // both segments are internal, so check if they form a straight line
                                    Bool same_direction = new Bool(false);
                                    Point_3D seg1_pt = segment.point1 == shared_point.pt ? segment.point2 : segment.point1;
                                    Point_3D seg2_pt = seg.point1 == shared_point.pt ? seg.point2 : seg.point1;
                                    if (Vector_3D.is_same_line(shared_point.pt, seg1_pt, shared_point.pt, seg2_pt, same_direction, precision) && !same_direction.get_val())
                                        continue;
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment found connecting segment\n";
//#endif
                                    shared_pt.pt = shared_point.pt;
                                    return seg;
                                }

                                // connecting segment is a perimeter segment
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment found connecting segment\n";
//#endif
                                // no need to check if it is on the same side
                                shared_pt.pt = shared_point.pt;
                                return seg;
                            }

                            // segment is a perimeter segment
                            if (seg.location == segment.location) // connecting segment is on the same side
                                continue; // do not return a segment from the same side
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment found connecting segment\n";
//#endif
                            // segment is external, so do not need to check if it is in a straight line
                            shared_pt.pt = shared_point.pt;
                            return seg;
                        }
                    }
                }

//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::find_connecting_segment end returning 0\n";
//#endif
                return null;
            }
                    
            /* 
             * determine if a segment is an external or internal segment
             * 
             * Arguments:
             * seg3: the segment to test if it is an internal or external segment
             * side_loc: if external, function sets this to the facet side it is on
             * original_facet: the original facet being fractured
             * p1p2_pts: p1p2 side intersect points
             * p1p3_pts: p1p3 side intersect points
             * p2p3_pts: p2p3 side intersect points
             * internal_pts: the internal points found inside the facet
             */
            public boolean is_segment_external(Line_Segment seg3, Pt_Location side_loc, 
                    Facet_3D original_facet, List<Point_3D> p1p2_pts, 
                    List<Point_3D> p1p3_pts, List<Point_3D> p2p3_pts, 
                    List<Point_3D> internal_pts) {

                for (Point_3D internalPt : internal_pts) {
                    if (internalPt == seg3.point1 || internalPt == seg3.point2)
                        return false; // if either end of seg3 is an internal point, then it is an internal segment
                }

                boolean found_p1 = false;
                boolean found_p2 = false;

                // check p1p2 side
                if (seg3.point1 == original_facet.get_point1() || seg3.point1 == original_facet.get_point2())
                    found_p1 = true;
                if (seg3.point2 == original_facet.get_point1() || seg3.point2 == original_facet.get_point2())
                    found_p2 = true;
                for (Point_3D sidePt : p1p2_pts) {
                    if (sidePt == seg3.point1)
                        found_p1 = true;
                    else if (sidePt == seg3.point2)
                        found_p2 = true;
                }

                if (found_p1 && found_p2) {
                    side_loc.setLocation(Location.p1p2);
                    return true;
                }

                found_p1 = false;
                found_p2 = false;

                // check p1p3 side
                if (seg3.point1 == original_facet.get_point1() || seg3.point1 == original_facet.get_point3())
                    found_p1 = true;
                if (seg3.point2 == original_facet.get_point1() || seg3.point2 == original_facet.get_point3())
                    found_p2 = true;
                for (Point_3D sidePt : p1p3_pts) {
                    if (sidePt == seg3.point1)
                        found_p1 = true;
                    else if (sidePt == seg3.point2)
                        found_p2 = true;
                }

                if (found_p1 && found_p2) {
                    side_loc.setLocation(Location.p1p3);
                    return true;
                }

                found_p1 = false;
                found_p2 = false;

                // check p2p3 side
                if (seg3.point1 == original_facet.get_point2() || seg3.point1 == original_facet.get_point3())
                    found_p1 = true;
                if (seg3.point2 == original_facet.get_point2() || seg3.point2 == original_facet.get_point3())
                    found_p2 = true;
                for (Point_3D sidePt : p2p3_pts) {
                    if (sidePt == seg3.point1)
                        found_p1 = true;
                    else if (sidePt == seg3.point2)
                        found_p2 = true;
                }

                if (found_p1 && found_p2) {
                    side_loc.setLocation(Location.p2p3);
                    return true;
                }

                return false;
            }
            
            /* 
             * check if the formed segment3 crosses other segments
             * 
             * Arguments:
             * seg3: the segment to check
             * orig_facet: the original facet that is being fractured
             * p1p2_pts: p1p2 side intersect points
             * p1p3_pts: p1p3 side intersect points
             * p2p3_pts: p2p3 side intersect points
             * internal_points: the original facet internal intersect points
             * precision: the precision to test for intersects with
             */
            public boolean does_seg_intersect(Line_Segment segment, Facet_3D orig_facet, 
                    List<Point_3D> p1p2_pts, List<Point_3D> p1p3_pts, 
                    List<Point_3D> p2p3_pts, List<Point_3D> internal_points, 
                    double precision) {
                
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect checking segment p1 x: " << 
//                        segment.point1->get_x() << " y: " << segment.point1->get_y() << " z: "  << segment.point1->get_z() << 
//                        " ptr: " << segment.point1.get() <<  " p2 x: " << segment.point2->get_x() << " y: " << segment.point2->get_y() << 
//                        " z: " << segment.point2->get_z() << " ptr: " << segment.point2.get() << " location: " << segment.location << "\n";
//#endif
                if (removed_segs.contains(segment)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment matches removed segment. returning true\n";
//#endif
                    return true; // segment has already been processed, return true
                }
                if (segments.contains(segment)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment matches segment. returning true\n";
//#endif
                    return false; // segment is still being processed, return false
                }

                Pt_Location side_loc = new Pt_Location(Location.internal);
                boolean segment_is_ext = is_segment_external(segment, side_loc, orig_facet, p1p2_pts, p1p3_pts, p2p3_pts, internal_points);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment_is_ext: " << (segment_is_ext ? "TRUE" : "FALSE") << " side_loc: " << side_loc << "\n";
//#endif
        
                // check if segment crosses any removed segments
                Iterator<Line_Segment> it = removed_segs.iterator();
                while (it.hasNext()) {
                    Line_Segment seg = it.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect testing removed segment p1 x: " << 
//                            it->point1->get_x() << " y: " << it->point1->get_y() << " z: "  << it->point1->get_z() << 
//                            " ptr: " << it->point1.get() <<  " p2 x: " << it->point2->get_x() << " y: " << it->point2->get_y() << 
//                            " z: " << it->point2->get_z() << " ptr: " << it->point2.get() << " location: " << it->location << "\n";
//#endif
                    if (segment_is_ext && seg.location == side_loc.getLocation()) { // if both are external and on the same side
                        Shared_Point shared_pt = new Shared_Point();
                        if (segment.shares_pt(seg, shared_pt)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect removed external segment shares point with segment\n";
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect testing segment end points\n";
//#endif
                            Point_3D non_common_pt = seg.point1 == shared_pt.pt ? seg.point2 : seg.point1;
                            if (Vector_3D.is_pt_on_vector(non_common_pt, segment.point1, segment.point2, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects removed external segment\n";
//#endif
                                return true;
                            }
                            non_common_pt = segment.point1 == shared_pt.pt ? segment.point2 : segment.point1;
                            if (Vector_3D.is_pt_on_vector(non_common_pt, seg.point1, seg.point2, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects removed external segment\n";
//#endif
                                return true;
                            }
                        }
                    } else if (!segment_is_ext && seg.location == Location.internal) { // both segments are internal
                        Shared_Point shared_pt = new Shared_Point();
                        if (!segment.shares_pt(seg, shared_pt)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect intersecting vectors\n";
//#endif
                            // intersect
                            Intersection_Data_3D idata = new Intersection_Data_3D();
                            if (Vector_3D.intersect_vectors(segment.point1, segment.point2, seg.point1, seg.point2, idata, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects a removed segment\n";
//#endif
                                return true; // vectors do not share a common point, but did intersect
                            }
                        } else { // segments share point
                            Intersection_Data_3D idata = new Intersection_Data_3D();
                            if (Vector_3D.intersect_vectors(segment.point1, segment.point2, seg.point1, seg.point2, idata, precision) && idata.get_num() == 2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects a removed segment\n";
//#endif
                                return true; // vectors shared a common point and an additional intersection
                            }
                        }
                    }
                }

                // check if segment crosses any segments
                it = segments.iterator();
                while (it.hasNext()) {
                    Line_Segment seg = it.next();
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect testing segment p1 x: " << 
//                            it->point1->get_x() << " y: " << it->point1->get_y() << " z: "  << it->point1->get_z() << 
//                            " ptr: " << it->point1.get() <<  " p2 x: " << it->point2->get_x() << " y: " << 
//                            it->point2->get_y() << " z: " << it->point2->get_z() << " ptr: " << it->point2.get() << "\n";
//#endif
                    if (segment_is_ext && side_loc.getLocation() == seg.location) { // both segments are on the same side
                        Shared_Point shared_pt = new Shared_Point();
                        if (segment.shares_pt(seg, shared_pt)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment shares point with existing segment\n";
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect testing segment end points\n";
//#endif
                            Point_3D non_common_pt = seg.point1 == shared_pt.pt ? seg.point2 : seg.point1;
                            if (Vector_3D.is_pt_on_vector(non_common_pt, segment.point1, segment.point2, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects external segment\n";
//#endif
                                return true;
                            }
                            non_common_pt = segment.point1 == shared_pt.pt ? segment.point2 : segment.point1;
                            if (Vector_3D.is_pt_on_vector(non_common_pt, seg.point1, seg.point2, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects external segment\n";
//#endif
                                return true;
                            }
                        } // don't test if they don't share a common point - shouldn't form an intersect pt because *it is external
                    } else if (!segment_is_ext && seg.location == Location.internal) { // both are internal
                        Shared_Point shared_pt = new Shared_Point();
                        if (!segment.shares_pt(seg, shared_pt)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                            cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect intersecting vectors\n";
//#endif
                            // intersect
                            Intersection_Data_3D idata = new Intersection_Data_3D();
                            if (Vector_3D.intersect_vectors(segment.point1, segment.point2, seg.point1, seg.point2, idata, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects an internal segment\n";
//#endif
                                return true; // vectors do not share a common point, but did intersect
                            }
                        } else { // segments share point
                            Intersection_Data_3D idata = new Intersection_Data_3D();
                            if (Vector_3D.intersect_vectors(segment.point1, segment.point2, seg.point1, seg.point2, idata, precision) && idata.get_num() == 2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::does_seg_intersect segment intersects an internal segment\n";
//#endif
                                return true; // vectors shared a common point and an additional intersection
                            }
                        }
                    }
                }

                return false;
            }

            /*
             * processes segment
             */
            private void process_used_segment(Line_Segment seg) {
                if (seg.location == Location.internal) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments segment is an internal segment\n";
//#endif
                    if (seg.used) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                        cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments erasing segment\n";
//#endif
                        removed_segs.add(seg);
                        segments.remove(seg);
                    } else { // update used to true
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                        cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments setting segment used to true\n";
//#endif
                        seg.used = true;
                    }
                } else { // external segment
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments segment is an external segment. erasing\n";
//#endif
                    removed_segs.add(seg);
                    segments.remove(seg);
                }
            }
            
            /* 
             * updates used property of segment or removes them from the list of
             * segments
             * 
             * Arguments:
             * seg1: segment1 used to form a new facet
             * seg2: segment2 used to form a new facet
             */
            public void process_used_segments(Line_Segment seg1, Line_Segment seg2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments begin\n";
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments seg1 p1 x: " << seg1.point1->get_x() << " y: " << seg1.point1->get_y() << 
//                        " z: " << seg1.point1->get_z() << " p2 x: " << seg1.point2->get_x() << " y: " << seg1.point2->get_y() <<
//                        " z: " << seg1.point2->get_z() << " location: " << seg1.location << " used: " << seg1.used << "\n";
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments seg2 p1 x: " << seg2.point1->get_x() << " y: " << seg2.point1->get_y() << 
//                        " z: " << seg2.point1->get_z() << " p2 x: " << seg2.point2->get_x() << " y: " << seg2.point2->get_y() <<
//                        " z: " << seg2.point2->get_z() << " location: " << seg2.location << " used: " << seg2.used << "\n";
//#endif
                // determine segment 3
                Shared_Point shared_pt = new Shared_Point();
                if (!seg1.shares_pt(seg2, shared_pt))
                    throw new IllegalStateException("segment1 and segment2 do not share a common point");
                // location should not matter: use location p1 since it is not a valid segment location
                Line_Segment seg3 = new Line_Segment(seg1.point1 == shared_pt.pt ? seg1.point2 : seg1.point1, seg2.point1 == shared_pt.pt ? seg2.point2 : seg2.point1, Location.p1);
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments seg3 p1 x: " << seg3.point1->get_x() << " y: " << seg3.point1->get_y() << 
//                        " z: " << seg3.point1->get_z() << " p2 x: " << seg3.point2->get_x() << " y: " << seg3.point2->get_y() <<
//                        " z: " << seg3.point2->get_z() << "\n";
//#endif

                if (!segments.contains(seg1))
                    throw new IllegalStateException("Unable to locate segment1");
                process_used_segment(seg1);

                if (!segments.contains(seg2))
                    throw new IllegalStateException("Unable to locate segment2");
                process_used_segment(seg2);

                Iterator<Line_Segment> it = segments.iterator(); 
                boolean found = false;
                while (it.hasNext()) {
                    Line_Segment seg = it.next();
                    if (seg.equals(seg3)) {
                        found = true;
                        seg3 = seg;
                        break;
                    }
                }
                if (!found) {
                    // segment3 does not exist
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                    cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments seg3 does not exist. adding\n";
//#endif
                    // add line segment 3 because it was formed in the build algorithm
                    add_internal_segment(seg3.point1, seg3.point2);
                    it = segments.iterator();
                    while (it.hasNext()) {
                        Line_Segment seg = it.next();
                        if (seg.equals(seg3)) {
                            seg3 = seg;
                        }
                    }
                }
                process_used_segment(seg3);
        
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_BUILDER_SEGMENTS
//                cout << "Intersect_Meshes_3D::Facet_Builder::Segments::process_used_segments end\n";
//#endif
            }
        }

        /*
         * Intersect points located on the sides of a facet
         */
        private class Intersecting_Facet_Side_Pts {
            public final I_Pt_List p1p2_points;
            public final I_Pt_List p1p3_points;
            public final I_Pt_List p2p3_points;
            
            public Intersecting_Facet_Side_Pts() {
                super();
                p1p2_points = new I_Pt_List();
                p1p3_points = new I_Pt_List();
                p2p3_points = new I_Pt_List();
            }
            
            public void clear() { 
                p1p2_points.clear(); 
                p1p3_points.clear(); 
                p2p3_points.clear(); 
            }
        }
    }
    
    /*
     * Sort intersected facets to know which facets are on the surface or 
     * inside the other mesh.
     */
    private class Facet_Sorter {
        private final double precision;
        private final List<Facet> f1_on_surface_f2;
        private final List<Facet> f1_inside_f2;
        private final List<Facet> f2_on_surface_f1;
        private final List<Facet> f2_inside_f1;
        
        public Facet_Sorter(double prec) {
            super();
            precision = prec;
            f1_inside_f2 = new ArrayList<>();
            f1_on_surface_f2 = new ArrayList<>();
            f2_inside_f1 = new ArrayList<>();
            f2_on_surface_f1 = new ArrayList<>();
        }
        
        public Iterator<Facet> f1_surface_iterator() { 
            return f1_on_surface_f2.iterator(); 
        }
        
        public Iterator<Facet> f1_inside_iterator() {
            return f1_inside_f2.iterator();
        }
        
        public Iterator<Facet> f2_surface_iterator() {
            return f2_on_surface_f1.iterator();
        }
        
        public Iterator<Facet> f2_inside_iterator() {
            return f2_inside_f1.iterator();
        }
        
        /*
         * determine the location of the facets in facets1 and facets2 in 
         * relation to each other.  each facet is determined if it is on the 
         * surface of or inside of the other mesh.
         * 
         * Arguments:
         * facets1: a mesh that was intersected by facets2
         * facets2: a mesh that was intersected by facets1
         */
        public void sort(Facets facets1, Facets facets2) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//            cout << "Intersect_Meshes_3D::Facet_Sorter::sort begin\n";
//#endif
            // consider all f1 facets inside f2 and remove them if they are found to be outside
            Iterator<Facet> it = facets1.iterator();
            while (it.hasNext()) {
                f1_inside_f2.add(it.next());
            }

            List<Facet> unchecked_f2_facets = new ArrayList<>();
            // consider all f2 facets inside f1 and remove them if they are found to be outside
            it = facets2.iterator();
            while (it.hasNext()) {
                Facet f = it.next();
                f2_inside_f1.add(f);
                unchecked_f2_facets.add(f);
            }
        
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//            cout << "Intersect_Meshes_3D::Facet_Sorter::sort beginning first round\n";
//#endif
            Iterator<Facet> f1_it = facets1.iterator();
            while (f1_it.hasNext()) {
                Facet f1_facet = f1_it.next();
                Facet_3D f1 = new Facet_3D(facets1.get_point(f1_facet.get_p1_index()), 
                        facets1.get_point(f1_facet.get_p2_index()), 
                        facets1.get_point(f1_facet.get_p3_index()));
                Point_3D f1_ip = f1.get_inside_point();
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//                cout << "Intersect_Meshes_3D::Facet_Sorter::sort sorting facets1 facet (p1: " << 
//                        f1_it->get_p1_index() << " p2: " << f1_it->get_p2_index() << " p3: " << 
//                        f1_it->get_p3_index() << ") p1 x: " << f1.get_point1()->get_x() << " y: " << 
//                        f1.get_point1()->get_y() << " z: " << f1.get_point1()->get_z() << " p2 x: " << 
//                        f1.get_point2()->get_x() << " y: " << f1.get_point2()->get_y() << " z: " << 
//                        f1.get_point2()->get_z() << " p3 x: " << f1.get_point3()->get_x() << " y: " << 
//                        f1.get_point3()->get_y() << " z: " << f1.get_point3()->get_z() << 
//                        " internal point is x: " << f1_ip.get_x() << " y: " << f1_ip.get_y() << " z: " << 
//                        f1_ip.get_z() << "\n";
//#endif
                // determine if point is on or inside the mesh
                boolean outside_mesh = false;
                Iterator<Facet> f2_it = facets2.iterator();
                while (f2_it.hasNext()) {
                    Facet f2_facet = f2_it.next();
                    Facet_3D f2 = new Facet_3D(facets2.get_point(f2_facet.get_p1_index()), 
                            facets2.get_point(f2_facet.get_p2_index()), 
                            facets2.get_point(f2_facet.get_p3_index()));
                    // if point is on a facet, return true
                    Bool pt_on_side = new Bool(false);
                    if (f2.contains_point(f1_ip, pt_on_side, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//                        cout << "Intersect_Meshes_3D::Facet_Sorter::sort sorting facets1 facet p1: " << 
//                                f1_it->get_p1_index() << " p2: " << f1_it->get_p2_index() << " p3: " << 
//                                f1_it->get_p3_index() << " internal point is on surface of f2 p1: " << 
//                                f2_it->get_p1_index() << " p2: " << f2_it->get_p2_index() << " p3: " << 
//                                f2_it->get_p3_index() << "\n";
//#endif
                        // both f1 and f2 are on the surface of each other
                        f1_on_surface_f2.add(f1_facet);
                        f2_on_surface_f1.add(f2_facet);
                        // remove f1 facet from inside
                        f1_inside_f2.remove(f1_facet);
                        // remove f2 facet from inside
                        f2_inside_f1.remove(f2_facet); // remove from list since it is on the f1 mesh
                        // remove f2 facet from unchecked
                        unchecked_f2_facets.remove(f2_facet); // remove from list since it is on the f1 mesh
                        break; // go to next f1
                    } else if (outside_mesh) // if already found to be outside, 
                        continue;          // see if point is on a facet - no need to look if outside anymore

                    // determine if point is inside mesh by checking if 
                    // point is behind each closest facet

                    // vector from point to facet
                    Point_3D f2_ip = f2.get_inside_point();
                    Vector_3D v = new Vector_3D(f1_ip, f2_ip);
                    boolean closer_facet = false; // if there is a facet that is closer

                    // go through mesh again looking for possible facets that are closer
                    Intersect_Point_3D i_point = new Intersect_Point_3D(); // initialize here so it will be only assigned in loop
                    it = facets2.iterator();
                    while (it.hasNext()) {
                        Facet currentFacet = it.next();
                        if (currentFacet == f2_facet) // don't process the same facet
                            continue;
                        // check if facet is in the same general direction and closer 
                        // than the *f2_it facet
                        // see if line v from p intersects *it facet
                        Facet_3D f = new Facet_3D(facets2.get_point(currentFacet.get_p1_index()), 
                                facets2.get_point(currentFacet.get_p2_index()), 
                                facets2.get_point(currentFacet.get_p3_index()));
                        if (Facet_3D.intersect_line_facet_plane(v, f1_ip, f, i_point, precision) && 
                                Vector_3D.is_pt_on_vector(i_point.getPoint(), f1_ip, f2_ip, precision) && 
                                f.contains_point(i_point.getPoint(), pt_on_side, precision)) {
                            // closer facet
                            closer_facet = true;
                            break;
                        }
                    }

                    if (!closer_facet) { // this is the closest facet, so test
                        // if point is in front of facet
                        if (Vector_3D.dot_product(f2.get_unv(), v.normalize()) < 0) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//                            cout << "Intersect_Meshes_3D::Facet_Sorter::sort facets1 facet p1: " << 
//                                    f1_it->get_p1_index() << " p2: " << f1_it->get_p2_index() << " p3: " << 
//                                    f1_it->get_p3_index() << " internal point is outside of f2 p1: " << 
//                                    f2_it->get_p1_index() << " p2: " << f2_it->get_p2_index() << " p3: " << 
//                                    f2_it->get_p3_index() << "\n";
//#endif
                            outside_mesh = true;
                            // f1 is outside of f2
                            f1_inside_f2.remove(f1_facet);
                        }
                        if (Vector_3D.dot_product(f1.get_unv(), v.minus()) < 0) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//                            cout << "Intersect_Meshes_3D::Facet_Sorter::sort facets2 facet p1: " << f2_it->get_p1_index() << 
//                                    " p2: " << f2_it->get_p2_index() << " p3: " << f2_it->get_p3_index() << 
//                                    " internal point is outside of f1 p1: " << f1_it->get_p1_index() << " p2: " << 
//                                    f1_it->get_p2_index() << " p3: " << f1_it->get_p3_index() << "\n";
//#endif
                            // f2 is outside of f1
                            f2_inside_f1.remove(f2_facet); // remove from list since it is outside of mesh
                            unchecked_f2_facets.remove(f2_facet); // remove from list since it is outside of mesh
                        }
                    }
                }
            }
        
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//            cout << "Intersect_Meshes_3D::Facet_Sorter::sort beginning second round with " << unchecked_f2_facets.size() << " facets2 facets to sort\n";
//#endif
            Iterator<Facet> f2_it = unchecked_f2_facets.iterator();
            while (f2_it.hasNext()) {
                Facet f2_facet = f2_it.next();
                Facet_3D f2 = new Facet_3D(facets2.get_point(f2_facet.get_p1_index()), 
                        facets2.get_point(f2_facet.get_p2_index()), 
                        facets2.get_point(f2_facet.get_p3_index()));
                Point_3D f2_ip = f2.get_inside_point();
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//                cout << "Intersect_Meshes_3D::Facet_Sorter::sort sorting facets2 facet (p1: " << 
//                        f2_it->get_p1_index() << " p2: " << f2_it->get_p2_index() << " p3: " << 
//                        f2_it->get_p3_index() << ") p1 x: " << f2.get_point1()->get_x() << " y: " << 
//                        f2.get_point1()->get_y() << " z: " << f2.get_point1()->get_z() << " p2 x: " << 
//                        f2.get_point2()->get_x() << " y: " << f2.get_point2()->get_y() << " z: " << 
//                        f2.get_point2()->get_z() << " p3 x: " << f2.get_point3()->get_x() << " y: " << 
//                        f2.get_point3()->get_y() << " z: " << f2.get_point3()->get_z() << 
//                        " internal point is x: " << f2_ip.get_x() << " y: " << f2_ip.get_y() << " z: " << 
//                        f2_ip.get_z() << "\n";
//#endif
                // determine if point is inside the mesh
                boolean outside_mesh = false;
                f1_it = facets1.iterator();
                while (f1_it.hasNext()) {
                    Facet f1_facet = f1_it.next();
                    Facet_3D f1 = new Facet_3D(facets1.get_point(f1_facet.get_p1_index()), 
                            facets1.get_point(f1_facet.get_p2_index()), 
                            facets1.get_point(f1_facet.get_p3_index()));
                    // if point is on a facet, return true
                    Bool pt_on_side = new Bool(false);
                    if (f1.contains_point(f2_ip, pt_on_side, precision)) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//                        cout << "Intersect_Meshes_3D::Facet_Sorter::sort sorting facets2 facet p1: " << 
//                                f2_it->get_p1_index() << " p2: " << f2_it->get_p2_index() << " p3: " << 
//                                f2_it->get_p3_index() << " internal point is on surface of f1 p1: " << 
//                                f1_it->get_p1_index() << " p2: " << f1_it->get_p2_index() << " p3: " << 
//                                f1_it->get_p3_index() << "\n";
//#endif
                        // both f1 and f2 are on the surface of each other
                        f2_on_surface_f1.add(f2_facet);
                        // remove f1 facet from inside
                        f1_inside_f2.remove(f1_facet); // remove from list since it is on the f1 mesh
                        // remove f2 facet from inside
                        f2_inside_f1.remove(f2_facet); // remove from list since it is on the f1 mesh
                        break; // go to next f1
                    } else if (outside_mesh) // if already found to be outside, 
                        continue;          // see if point is on a facet - no need to look if outside anymore

                    // determine if point is inside mesh by checking if 
                    // point is behind each closest facet

                    // vector from point to facet
                    Point_3D f1_ip = f1.get_inside_point();
                    Vector_3D v = new Vector_3D(f2_ip, f1_ip);
                    boolean closer_facet = false; // if there is a facet that is closer

                    // go through mesh again looking for possible facets that are closer
                    Intersect_Point_3D i_point = new Intersect_Point_3D(); // initialize here so it will be only assigned in loop
                    it = facets1.iterator();
                    while (it.hasNext()) {
                        Facet currentFacet = it.next();
                        if (currentFacet == f1_facet) // don't process the same facet
                            continue;
                        // check if facet is in the same general direction and closer 
                        // than the *f1_it facet
                        // see if line v from p intersects *it facet
                        pt_on_side.set_val(false);
                        Facet_3D f = new Facet_3D(facets1.get_point(currentFacet.get_p1_index()), 
                                facets1.get_point(currentFacet.get_p2_index()), 
                                facets1.get_point(currentFacet.get_p3_index()));
                        if (Facet_3D.intersect_line_facet_plane(v, f2_ip, f, i_point, precision) && 
                                Vector_3D.is_pt_on_vector(i_point.getPoint(), f2_ip, f1_ip, precision) && 
                                f.contains_point(i_point.getPoint(), pt_on_side, precision)) {
                            // closer facet
                            closer_facet = true;
                            break;
                        }
                    }

                    if (!closer_facet) { // this is the closest facet, so test
                        // if point is in front of facet
                        if (Vector_3D.dot_product(f1.get_unv(), v.normalize()) < 0) {
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//                            cout << "Intersect_Meshes_3D::Facet_Sorter::sort facets2 facet p1: " << 
//                                    f2_it->get_p1_index() << " p2: " << f2_it->get_p2_index() << " p3: " << 
//                                    f2_it->get_p3_index() << " internal point is outside of f1 p1: " << 
//                                    f1_it->get_p1_index() << " p2: " << f1_it->get_p2_index() << " p3: " << 
//                                    f1_it->get_p3_index() << "\n";
//#endif
                            outside_mesh = true;
                            // f2 is outside of f1
                            f2_inside_f1.remove(f2_facet);
                        }
                    }
                }
            }
//#ifdef DEBUG_INTERSECT_MESHES_3D_FACET_SORTER
//            cout << "Intersect_Meshes_3D::Facet_Sorter::sort end\n";
//#endif
        }
        
        public void clear() {
            f1_inside_f2.clear();
            f1_on_surface_f2.clear();
            f2_inside_f1.clear();
            f2_on_surface_f1.clear();
        }
    }
}
