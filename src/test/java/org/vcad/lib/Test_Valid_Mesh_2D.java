/*
 * Test_Valid_Mesh_2D.java
 */
package org.vcad.lib;

import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author Jeffrey Davis
 */
public class Test_Valid_Mesh_2D extends TestBase {
    
    public Test_Valid_Mesh_2D() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    @Test
    public void test_valid_mesh_1() {
        Mesh_2D mesh = new Mesh_2D();

        // create an unaligned point 3,3
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(3,3), new Point_2D(0,3)));
        mesh.add(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(0,3)));
        mesh.add(new Facet_2D(new Point_2D(0,3), new Point_2D(5,5), new Point_2D(0,10)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(5,5), new Point_2D(10,10)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,-5), new Point_2D(10,0)));

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        assert(!valid.validate());
        assert(!valid.pts_on_side_empty());
        assert(valid.pts_on_side_size() == 1);
        Iterator<Point_2D> it = valid.pts_on_side_iterator();
        assert(it.hasNext());
        assert(Point_2D.is_equal(it.next(), new Point_2D(3,3), precision));
        assert(valid.too_many_share_side_empty());
        assert(valid.too_many_share_side_size() == 0);
        assert(valid.facets_inside_facet_empty());
        assert(valid.facets_inside_facet_size() == 0);
    }

    @Test
    public void test_valid_mesh_2() {
        Mesh_2D mesh = new Mesh_2D();

        // create a facet within a facet
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,10)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(5,5), new Point_2D(10,10)));

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        assert(!valid.validate());
        assert(!valid.pts_on_side_empty());
        assert(valid.pts_on_side_size() == 1);
        Iterator<Point_2D> it = valid.pts_on_side_iterator();
        assert(it.hasNext());
        assert(Point_2D.is_equal(it.next(), new Point_2D(3,3), precision));
        assert(!valid.too_many_share_side_empty());
//        cout << "\ntoo many share side size: " << valid.too_many_share_side_size() << "\n";
//        for (Valid_Mesh_2D::too_many_share_side_iterator iter = valid.too_many_share_side_begin(); iter != valid.too_many_share_side_end(); ++iter)
//        {
//            cout << "Group of Facets\n";
//            for (vector<Facet_2D>::const_iterator iter2 = iter->begin(); iter2 != iter->end(); ++iter2)
//            {
//                cout << "    Facet_2D p1 x: " << iter2->get_point1()->get_x() << " y: " << iter2->get_point1()->get_y() <<
//                        " p2 x: " << iter2->get_point2()->get_x() << " y: " << iter2->get_point2()->get_y() << " p3 x: " << 
//                        iter2->get_point3()->get_x() << " y: " << iter2->get_point3()->get_y() << "\n";
//            }
//        }
        assert(valid.too_many_share_side_size() == 1);
        Iterator<List<Facet_2D>> it2 = valid.too_many_share_side_iterator();
        assert(it2.hasNext());
        List<Facet_2D> list = it2.next();
        assert(list.size() == 3);
        assert(Facet_2D.is_equal(list.get(0), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        assert(Facet_2D.is_equal(list.get(1), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)), precision));
        assert(Facet_2D.is_equal(list.get(2), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)), precision));
        assert(!valid.facets_inside_facet_empty());
//        System.out.println("\nfacets_inside_facets size: " + valid.facets_inside_facet_size());
//        for (Iterator<Valid_Mesh_2D.FacetsInsideFacet> iter = valid.facets_inside_facet_iterator(); iter.hasNext();) {
//            Valid_Mesh_2D.FacetsInsideFacet fif = iter.next();
//            System.out.println("Large facet p1 x: " + 
//                    fif.getFacet().get_point1().get_x() + " y: " + 
//                    fif.getFacet().get_point1().get_y() + " p2 x: " + 
//                    fif.getFacet().get_point2().get_x() + " y: " + 
//                    fif.getFacet().get_point2().get_y() + " p3 x: " + 
//                    fif.getFacet().get_point3().get_x() + " y: " + 
//                    fif.getFacet().get_point3().get_y());
//            for (Iterator<Facet_2D> iter2 = fif.getInsideFacets(); iter2.hasNext();) {
//                Facet_2D f = iter2.next();
//                System.out.println("    Facet_2D p1 x: " + f.get_point1().get_x() + 
//                        " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
//                        " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
//                        " y: " + f.get_point3().get_y());
//            }
//        }
        assert(valid.facets_inside_facet_size() == 2);
        Iterator<Valid_Mesh_2D.FacetsInsideFacet> it3 = valid.facets_inside_facet_iterator();
        assert(it3.hasNext());
        Valid_Mesh_2D.FacetsInsideFacet fif = it3.next();
        assert(Facet_2D.is_equal(fif.getFacet(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        assert(fif.insideFacetsSize() == 2);
        Iterator<Facet_2D> it4 = fif.getInsideFacets();
        assert(it4.hasNext());
        assert(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)), precision));
        assert(it4.hasNext());
        assert(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)), precision));
        assert(it3.hasNext());
        fif = it3.next();
        assert(Facet_2D.is_equal(fif.getFacet(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)), precision));
        assert(fif.insideFacetsSize() == 1);
        it4 = fif.getInsideFacets();
        assert(it4.hasNext());
        assert(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)), precision));
    }

    @Test
    public void test_valid_mesh_3() {
        Mesh_2D mesh = new Mesh_2D();

        // create invalid facets
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5.00001,4.99999), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(0,0), new Point_2D(5,5)));

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        assert(!valid.validate());
        assert(!valid.pts_on_side_empty());
        assert(valid.pts_on_side_size() == 1);
        Iterator<Point_2D> it = valid.pts_on_side_iterator();
        assert(Point_2D.is_equal(it.next(), new Point_2D(5,5), precision));
//        cout << "\ntoo many share side size: " << valid.too_many_share_side_size() << "\n";
//        for (Valid_Mesh_2D::too_many_share_side_iterator iter = valid.too_many_share_side_begin(); iter != valid.too_many_share_side_end(); ++iter)
//        {
//            cout << "Group of Facets\n";
//            for (vector<Facet_2D>::const_iterator iter2 = iter->begin(); iter2 != iter->end(); ++iter2)
//            {
//                cout << "    Facet_2D p1 x: " << iter2->get_point1()->get_x() << " y: " << iter2->get_point1()->get_y() <<
//                        " p2 x: " << iter2->get_point2()->get_x() << " y: " << iter2->get_point2()->get_y() << " p3 x: " << 
//                        iter2->get_point3()->get_x() << " y: " << iter2->get_point3()->get_y() << "\n";
//            }
//        }
        assert(!valid.too_many_share_side_empty());
        assert(valid.too_many_share_side_size() == 1);
        Iterator<List<Facet_2D>> it2 = valid.too_many_share_side_iterator();
        assert(it2.hasNext());
        List<Facet_2D> list = it2.next();
        assert(list.size() == 3);
        Iterator<Facet_2D> iter = list.iterator();
        assert(iter.hasNext());
        assert(Facet_2D.is_equal(iter.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        assert(iter.hasNext());
        assert(Facet_2D.is_equal(iter.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5.00001,4.99999), new Point_2D(5,5)), precision));
        assert(iter.hasNext());
        assert(Facet_2D.is_equal(iter.next(), new Facet_2D(new Point_2D(0,10), new Point_2D(0,0), new Point_2D(5,5)), precision));
//        System.out.println("\nfacets_inside_facets size: " + valid.facets_inside_facet_size());
//        for (Iterator<Valid_Mesh_2D.FacetsInsideFacet> iter3 = valid.facets_inside_facet_iterator(); iter3.hasNext();) {
//            Valid_Mesh_2D.FacetsInsideFacet fif = iter3.next();
//            System.out.println("Large facet p1 x: " + 
//                    fif.getFacet().get_point1().get_x() + " y: " + 
//                    fif.getFacet().get_point1().get_y() + " p2 x: " + 
//                    fif.getFacet().get_point2().get_x() + " y: " + 
//                    fif.getFacet().get_point2().get_y() + " p3 x: " + 
//                    fif.getFacet().get_point3().get_x() + " y: " + 
//                    fif.getFacet().get_point3().get_y());
//            for (Iterator<Facet_2D> iter2 = fif.getInsideFacets(); iter2.hasNext();) {
//                Facet_2D f = iter2.next();
//                System.out.println("    Facet_2D p1 x: " + f.get_point1().get_x() + 
//                        " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
//                        " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
//                        " y: " + f.get_point3().get_y());
//            }
//        }
        assert(!valid.facets_inside_facet_empty());
        assert(valid.facets_inside_facet_size() == 1);
        Iterator<Valid_Mesh_2D.FacetsInsideFacet> it3 = valid.facets_inside_facet_iterator();
        assert(it3.hasNext());
        Valid_Mesh_2D.FacetsInsideFacet fif = it3.next();
        assert(Facet_2D.is_equal(fif.getFacet(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        assert(fif.insideFacetsSize() == 1);
        Iterator<Facet_2D> it4 = fif.getInsideFacets();
        assert(it4.hasNext());
        assert(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5.00001,4.99999), new Point_2D(5,5)), precision));
    }

    @Test
    public void test_valid_mesh_4() {
        Mesh_2D mesh = new Mesh_2D();

        // create valid facets
        Shapes.ellipse(mesh, 10, 20, 4);

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        assert(valid.validate());
//        for (int i = 0; i < valid.unaligned_points_size(); ++i)
//        {
//            Point_2D p(valid.unaligned_point_at(i));
//            cout << "unaligned_points[" << i << "]: x: " << p.get_x() << " y: " << p.get_y() << "\n";
//        }
        assert(valid.pts_on_side_empty());
        assert(valid.pts_on_side_size() == 0);
//        assert(within_round(valid.unaligned_point_at(0), Point_2D(5,5), precision));
//        for (int i = 0; i < valid.facets_with_facets_size(); ++i)
//        {
//            Facet_2D f(valid.facets_with_facets_at(i));
//            cout << "facets_with_facets[" << i << "]: p1 x: " << f.get_point1().get_x() <<
//                    " y: " << f.get_point1().get_y() << " p2 x: " << f.get_point2().get_x() <<
//                    " y: " << f.get_point2().get_y() << " p3 x: " << f.get_point3().get_x() <<
//                    " y: " << f.get_point3().get_y() << "\n";
//        }
        assert(valid.facets_inside_facet_empty());
        assert(valid.facets_inside_facet_size() == 0);
//        for (int i = 0; i < valid.too_many_facets_size(); ++i)
//        {
//            Facet_2D f(valid.too_many_facets_at(i));
//            cout << "too_many_facets[" << i << "]: p1 x: " << f.get_point1().get_x() <<
//                    " y: " << f.get_point1().get_y() << " p2 x: " << f.get_point2().get_x() <<
//                    " y: " << f.get_point2().get_y() << " p3 x: " << f.get_point3().get_x() <<
//                    " y: " << f.get_point3().get_y() << "\n";
//        }
        assert(valid.too_many_share_side_empty());
        assert(valid.too_many_share_side_size() == 0);
//        assert(is_equal(valid.too_many_facets_at(0), Facet_2D(Point_2D(0,0), Point_2D(10,0), Point_2D(5,5)), precision));
//        assert(is_equal(valid.too_many_facets_at(1), Facet_2D(Point_2D(0,0), Point_2D(5.00001,4.99999), Point_2D(5,5)), precision));
    }
}
