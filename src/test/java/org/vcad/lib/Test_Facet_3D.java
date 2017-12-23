/*
 * Test_Facet_3D.java
 */
package org.vcad.lib;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jeffrey Davis
 */
public class Test_Facet_3D extends TestBase {
    
    public Test_Facet_3D() {
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
    public void test_Facet_3D() {
        Facet_3D f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,1,1), new Point_3D(1,1,5));
        Assert.assertTrue(Point_3D.is_equal(f.get_point1(), new Point_3D(1,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point2(), new Point_3D(5,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point3(), new Point_3D(1,1,5), precision));
        Vector_3D unv = f.get_unv();
        Assert.assertTrue(unv.get_x() == 0);
        Assert.assertTrue(unv.get_y() == -1);
        Assert.assertTrue(unv.get_z() == 0);
        f.invert_unv();
        unv = f.get_unv();
        Assert.assertTrue(unv.get_x() == 0);
        Assert.assertTrue(unv.get_y() == 1);
        Assert.assertTrue(unv.get_z() == 0);
        // test swapping points constructor
        f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,1,1), new Point_3D(1,1,5), true);
        unv = f.get_unv();
        Assert.assertTrue(Point_3D.is_equal(f.get_point1(), new Point_3D(1,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point2(), new Point_3D(1,1,5), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point3(), new Point_3D(5,1,1), precision));
        Assert.assertTrue(unv.get_x() == 0);
        Assert.assertTrue(unv.get_y() == 1);
        Assert.assertTrue(unv.get_z() == 0);
        // test constructor with unit normal vector set to zero
        f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,1,1), new Point_3D(1,1,5), new Vector_3D(0,0,0));
        unv = f.get_unv();
        Assert.assertTrue(Point_3D.is_equal(f.get_point1(), new Point_3D(1,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point2(), new Point_3D(5,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point3(), new Point_3D(1,1,5), precision));
        Assert.assertTrue(unv.get_x() == 0);
        Assert.assertTrue(unv.get_y() == -1);
        Assert.assertTrue(unv.get_z() == 0);
        f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,1,1), new Point_3D(1,1,5), new Vector_3D(0,0,0), true);
        unv = f.get_unv();
        Assert.assertTrue(Point_3D.is_equal(f.get_point1(), new Point_3D(1,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point2(), new Point_3D(1,1,5), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point3(), new Point_3D(5,1,1), precision));
        Assert.assertTrue(Vector_3D.is_equal(unv, new Vector_3D(0,1,0), precision));
        f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,1,1), new Point_3D(1,1,5), new Vector_3D(0,-1,0));
        unv = f.get_unv();
        Assert.assertTrue(Point_3D.is_equal(f.get_point1(), new Point_3D(1,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point2(), new Point_3D(5,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point3(), new Point_3D(1,1,5), precision));
        Assert.assertTrue(unv.get_x() == 0);
        Assert.assertTrue(unv.get_y() == -1);
        Assert.assertTrue(unv.get_z() == 0);
        f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,1,1), new Point_3D(1,1,5), new Vector_3D(0,-1,0));
        unv = f.get_unv();
        Assert.assertTrue(Point_3D.is_equal(f.get_point1(), new Point_3D(1,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point2(), new Point_3D(5,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(f.get_point3(), new Point_3D(1,1,5), precision));
        Assert.assertTrue(unv.get_x() == 0);
        Assert.assertTrue(unv.get_y() == -1);
        Assert.assertTrue(unv.get_z() == 0);
//        cout << "fp1: x: " << f.get_point1().get_x() << " y: " << f.get_point1().get_y() << " z: " << f.get_point1().get_z() << "\n";
//        cout << "fp2: x: " << f.get_point2().get_x() << " y: " << f.get_point2().get_y() << " z: " << f.get_point2().get_z() << "\n";
//        cout << "fp3: x: " << f.get_point3().get_x() << " y: " << f.get_point3().get_y() << " z: " << f.get_point3().get_z() << "\n";
//        cout << "unv: x: " << unv.get_x() << " y: " << unv.get_y() << " z: " << unv.get_z() << "\n";
    }

    @Test
    public void test_point_on_facet_plane() {
        Facet_3D f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(1,1,5), new Point_3D(5,1,1));
        Point_3D p = new Point_3D(0,1,0);
        Assert.assertTrue(Facet_3D.is_pt_on_facet_plane(p, f, precision));
        p = new Point_3D(0,0,0);
        Assert.assertTrue(!Facet_3D.is_pt_on_facet_plane(p, f, precision));
        p = new Point_3D(2,0,2);
        Assert.assertTrue(!Facet_3D.is_pt_on_facet_plane(p, f, precision));
        p = new Point_3D(2,1,2);
        Assert.assertTrue(Facet_3D.is_pt_on_facet_plane(p, f, precision));
    }

    @Test
    public void test_intersect_line_facet_plane() {
        Facet_3D f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(1,1,5), new Point_3D(5,1,1));
        Point_3D p = new Point_3D(0,1,0);
        Vector_3D v = new Vector_3D(1,0,0);
        Intersect_Point_3D result = new Intersect_Point_3D(); 
        Assert.assertTrue(Facet_3D.intersect_line_facet_plane(v, p, f, result, precision));
        Assert.assertTrue(Point_3D.is_equal(result.getPoint(), p, precision));
        p = new Point_3D(2,4,3);
        Assert.assertTrue(!Facet_3D.intersect_line_facet_plane(v, p, f, result, precision));
        v = new Vector_3D(0, -2, -1);
        Assert.assertTrue(Facet_3D.intersect_line_facet_plane(v, p, f, result, precision));
        Assert.assertTrue(Point_3D.is_equal(result.getPoint(), new Point_3D(2, 1, 1.5), precision));
        p = new Point_3D(-4, -5, -6);
        Assert.assertTrue(Facet_3D.intersect_line_facet_plane(v, p, f, result, precision));
        Assert.assertTrue(Point_3D.is_equal(result.getPoint(), new Point_3D(-4, 1, -3), precision));
        v = new Vector_3D(0, 0, 0);
        Assert.assertTrue(!Facet_3D.intersect_line_facet_plane(v, p, f, result, precision));
//        cout << "result x: " << result->get_x() << " y: " << result->get_y() << " z: " << result->get_z() << "\n";
    }

    @Test
    public void test_facet_contains_point() {
        Facet_3D f = new Facet_3D(new Point_3D(1,1,1), new Point_3D(1,1,5), new Point_3D(5,1,1));
        Point_3D p = new Point_3D(0,1,0);
        Bool pt_on_side = new Bool(false);
        Assert.assertTrue(!f.contains_point(p, pt_on_side, precision));
        // corner points
        p = new Point_3D(1,1,1);
        Assert.assertTrue(f.contains_point(p, pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        p = new Point_3D(1,1,5);
        Assert.assertTrue(f.contains_point(p, pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        p = new Point_3D(5,1,1);
        Assert.assertTrue(f.contains_point(p, pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        // side points
        p = new Point_3D(2,1,1);
        Assert.assertTrue(f.contains_point(p, pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        p = new Point_3D(1,1,3);
        Assert.assertTrue(f.contains_point(p, pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        p = new Point_3D(3,1,3);
        Assert.assertTrue(f.contains_point(p, pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        // inside point
        p = new Point_3D(3,2,3);
        Assert.assertTrue(!f.contains_point(p, pt_on_side, precision));
        Assert.assertTrue(!pt_on_side.get_val());
//        f = Facet_3D(Point_3D(-3.33333,37.7124,1.77636e-15), Point_3D(-6.28539,28.1091,-6.66667), Point_3D(-6.66667,29.8142,1.77636e-15));
//        Facet_3D f2(Point_3D(6.66667,29.8142,1.77636e-15), Point_3D(3.1427,35.5556,-6.66667), Point_3D(3.33333,37.7124,1.77636e-15));
//        Assert.assertTrue(!f.contains_point(f2.get_inside_point(), precision));
//        Assert.assertTrue(!f.contains_point(Point_3D(4.95234,33.2241,-1.66667), precision));
//        f = Facet_3D(Point_3D(4, 1, 1), Point_3D(0.5, 1, 1), Point_3D(6, 7, 7));
//        f2 = Facet_3D(Point_3D(4.56522,5.43478,5.43478), Point_3D(5,5,5), Point_3D(1,1,1));
//        Assert.assertTrue(f.contains_point(f2.get_inside_point(), precision));
//        f = Facet_3D(Point_3D(0, 0, 0), Point_3D(5, 5, 5), Point_3D(0, 10, 10));
//        f2 = Facet_3D(Point_3D(0.5, 1, 1), Point_3D(4.56522, 5.43478, 5.43478), Point_3D(1, 1, 1));
//        Assert.assertTrue(f.contains_point(f2.get_inside_point(), precision));
    }
}
