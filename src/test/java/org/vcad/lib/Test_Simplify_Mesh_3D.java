/*
 * Test_Simplify_Mesh_3D.java
 */
package org.vcad.lib;

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
public class Test_Simplify_Mesh_3D extends TestBase {
    
    public Test_Simplify_Mesh_3D() {
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
    public void test_simplify_mesh_1() {
        // no simplification
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(1,7,7)));
        mesh.add(new Facet_3D(new Point_3D(1,7,7), new Point_3D(5,5,5), new Point_3D(8,9,9)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(!simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(1,7,7)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,7,7), new Point_3D(5,5,5), new Point_3D(8,9,9)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_2() {
        // no simplification
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(!simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_3() {
        // reverse intersect_mesh_mesh_5
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(1.2,1,1), new Point_3D(1,1,1)));
        mesh.add(new Facet_3D(new Point_3D(1,1,1), new Point_3D(1.2,1,1), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(1.2,1,1)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(1.2,1,1), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(0.8,1,1)));
        mesh.add(new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,5,5), new Point_3D(0.8,1,1)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0.8,1,1), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(0,10,10), new Point_3D(0.8,1,1)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)),  mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(0,0,0), new Point_3D(10,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(0,10,10), new Point_3D(0,0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_4() {
        // reverse intersect_mesh_mesh_7
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(5.25,4.75,4.75), new Point_3D(1,1,1), new Point_3D(4,1,1)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(4,1,1), new Point_3D(1,1,1)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(5.25,4.75,4.75), new Point_3D(4,1,1)));
        mesh.add(new Facet_3D(new Point_3D(5.25,4.75,4.75), new Point_3D(5,5,5), new Point_3D(1,1,1)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(4,1,1)));
        mesh.add(new Facet_3D(new Point_3D(4.56522,5.43478,5.43478), new Point_3D(0.5,1,1), new Point_3D(1,1,1)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(0.5,1,1)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(4.56522,5.43478,5.43478), new Point_3D(1,1,1)));
        mesh.add(new Facet_3D(new Point_3D(4.56522,5.43478,5.43478), new Point_3D(0,10,10), new Point_3D(0.5,1,1)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0.5,1,1), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(4.56522,5.43478,5.43478), new Point_3D(5.5,5.5,5.5), new Point_3D(6,7,7)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(4.56522,5.43478,5.43478), new Point_3D(6,7,7)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(5.5,5.5,5.5), new Point_3D(4.56522,5.43478,5.43478)));
        mesh.add(new Facet_3D(new Point_3D(5.5,5.5,5.5), new Point_3D(10,10,10), new Point_3D(6,7,7)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(6,7,7), new Point_3D(10,10,10)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_5() {
        // reverse intersect_mesh_mesh_9
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,3.5,3.5), new Point_3D(4.5,4.5,4.5)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(5.75,4.25,4.25), new Point_3D(5.325,3.825,3.825)));
        mesh.add(new Facet_3D(new Point_3D(5.75,4.25,4.25), new Point_3D(5,5,5), new Point_3D(5.325,3.825,3.825)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(4.5,4.5,4.5), new Point_3D(5.325,3.825,3.825)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,3.5,3.5)));
        mesh.add(new Facet_3D(new Point_3D(4.5,4.5,4.5), new Point_3D(5,3.5,3.5), new Point_3D(5.325,3.825,3.825)));
        mesh.add(new Facet_3D(new Point_3D(5.325,3.825,3.825), new Point_3D(5,3.5,3.5), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(4.5,4.5,4.5), new Point_3D(3.66667,6.16667,6.16667)));
        mesh.add(new Facet_3D(new Point_3D(4.5,4.5,4.5), new Point_3D(5,5,5), new Point_3D(3.66667,6.16667,6.16667)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(3.66667,6.16667,6.16667), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(3.75,6.25,6.25), new Point_3D(0,10,10), new Point_3D(3.66667,6.16667,6.16667)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(3.75,6.25,6.25), new Point_3D(3.66667,6.16667,6.16667)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(10,10,10), new Point_3D(5,7.5,7.5)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,7.5,7.5), new Point_3D(10,10,10)));
        mesh.add(new Facet_3D(new Point_3D(3.75,6.25,6.25), new Point_3D(5,5,5), new Point_3D(5,7.5,7.5)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(3.75,6.25,6.25), new Point_3D(5,7.5,7.5)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(0,0,0), new Point_3D(5,5,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_6() {
        // reverse intersect_facet_facet_6
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,1,3), new Point_3D(0,0,0), new Point_3D(0,1.33333,3)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,3), new Point_3D(0,0,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,1.33333,3)));
        mesh.add(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,1,3), new Point_3D(0,1.33333,3)));
        mesh.add(new Facet_3D(new Point_3D(0,1.33333,3), new Point_3D(0,10,0), new Point_3D(0,0,5)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 1);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_7() {
        // reverse intersect_facet_facet_7
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,3), new Point_3D(0,0,3)));
        mesh.add(new Facet_3D(new Point_3D(0,0,3), new Point_3D(0,1,3), new Point_3D(0,0,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,1,3)));
        mesh.add(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,1,3), new Point_3D(0,10,0)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 1);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,0,5)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_8() {
        // reverse intersect_facet_facet_32
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,4), new Point_3D(0,1,2)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,2), new Point_3D(0,0,4)));
        mesh.add(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,2,4), new Point_3D(0,1,2)));
        mesh.add(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,5), new Point_3D(0,0,4)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,1,2)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 1);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,0,5)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_9() {
        // reverse intersect_mesh_mesh_12
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(1,2,2), new Point_3D(3,4,4), new Point_3D(1.5,4,4)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(3,4,4), new Point_3D(1,2,2)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,2,2), new Point_3D(1.5,4,4)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(3,4,4)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(1.5,4,4), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(4,5,5), new Point_3D(3.6,4.6,4.6)));
        mesh.add(new Facet_3D(new Point_3D(2.8125,4.375,4.375), new Point_3D(3.6,4.6,4.6), new Point_3D(4,5,5)));
        mesh.add(new Facet_3D(new Point_3D(5,5,5), new Point_3D(0,10,10), new Point_3D(4,5,5)));
        mesh.add(new Facet_3D(new Point_3D(2.8125,4.375,4.375), new Point_3D(4,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(3,4,4), new Point_3D(5,5,5), new Point_3D(3.6,4.6,4.6)));
        mesh.add(new Facet_3D(new Point_3D(3,4,4), new Point_3D(3.6,4.6,4.6), new Point_3D(2.8125,4.375,4.375)));
        mesh.add(new Facet_3D(new Point_3D(2.8125,4.375,4.375), new Point_3D(0,10,10), new Point_3D(1.5,4,4)));
        mesh.add(new Facet_3D(new Point_3D(3,4,4), new Point_3D(2.8125,4.375,4.375), new Point_3D(1.5,4,4)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(0,10,10), new Point_3D(0,0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_10() {
        // two planes with some extra points between them
        Mesh_3D mesh = new Mesh_3D();
        // plane 1
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,5,0), new Point_3D(1,0,0)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0), new Point_3D(0,5,0), new Point_3D(2,0,0)));
        mesh.add(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,5,0), new Point_3D(3,0,0)));
        mesh.add(new Facet_3D(new Point_3D(3,0,0), new Point_3D(0,5,0), new Point_3D(4,0,0)));
        mesh.add(new Facet_3D(new Point_3D(4,0,0), new Point_3D(0,5,0), new Point_3D(5,0,0)));
        // plane 2
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,5), new Point_3D(1,0,0)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0), new Point_3D(0,0,5), new Point_3D(2,0,0)));
        mesh.add(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,0,5), new Point_3D(3,0,0)));
        mesh.add(new Facet_3D(new Point_3D(3,0,0), new Point_3D(0,0,5), new Point_3D(4,0,0)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,0,0), new Point_3D(0,5,0), new Point_3D(5,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(4,0,0), new Point_3D(0,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(4,0,0), new Point_3D(0,0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_11() {
        // two planes with some extra points between them - 3 planes for part
        Mesh_3D mesh = new Mesh_3D();
        // plane 1
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,5,0), new Point_3D(1,0,0)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0), new Point_3D(0,5,0), new Point_3D(2,0,0)));
        mesh.add(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,5,0), new Point_3D(3,0,0)));
        mesh.add(new Facet_3D(new Point_3D(3,0,0), new Point_3D(0,5,0), new Point_3D(4,0,0)));
        mesh.add(new Facet_3D(new Point_3D(4,0,0), new Point_3D(0,5,0), new Point_3D(5,0,0)));
        // plane 2
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,5), new Point_3D(1,0,0)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0), new Point_3D(0,0,5), new Point_3D(2,0,0)));
        mesh.add(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,0,5), new Point_3D(3,0,0)));
        mesh.add(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,0,5), new Point_3D(2,0,5)));
        mesh.add(new Facet_3D(new Point_3D(2,0,5), new Point_3D(3,0,0), new Point_3D(3,3,5))); // different plane
        mesh.add(new Facet_3D(new Point_3D(4,0,5), new Point_3D(3,0,0), new Point_3D(3,3,5))); // different plane
        mesh.add(new Facet_3D(new Point_3D(3,0,0), new Point_3D(4,0,5), new Point_3D(4,0,0)));


        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,5,0), new Point_3D(3,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(2,0,0), new Point_3D(0,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,0,5), new Point_3D(3,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2,0,0), new Point_3D(0,0,5), new Point_3D(2,0,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(2,0,0), new Point_3D(0,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2,0,5), new Point_3D(3,0,0), new Point_3D(3,3,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,0,5), new Point_3D(3,0,0), new Point_3D(3,3,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,0,0), new Point_3D(0,5,0), new Point_3D(5,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,0,0), new Point_3D(0,5,0), new Point_3D(4,0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,0,0), new Point_3D(4,0,5), new Point_3D(4,0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_12() {
        // two planes with same unit normal vector - 1 internal point & external
        // point in each plane
        Mesh_3D mesh = new Mesh_3D();
        // plane 1
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,5,0), new Point_3D(1,0,0)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0), new Point_3D(0,5,0), new Point_3D(1.5,2.5,0)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0), new Point_3D(1.5,2.5,0), new Point_3D(2,0,0)));
        mesh.add(new Facet_3D(new Point_3D(2,0,0), new Point_3D(1.5,2.5,0), new Point_3D(3,1,0)));
        mesh.add(new Facet_3D(new Point_3D(3,1,0), new Point_3D(1.5,2.5,0), new Point_3D(4,4,0)));
        mesh.add(new Facet_3D(new Point_3D(4,4,0), new Point_3D(1.5,2.5,0), new Point_3D(0,5,0)));
        // plane 2
        mesh.add(new Facet_3D(new Point_3D(0,0,0.001), new Point_3D(0,5,0.001), new Point_3D(1,0,0.001)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0.001), new Point_3D(0,5,0.001), new Point_3D(1.5,2.5,0.001)));
        mesh.add(new Facet_3D(new Point_3D(1,0,0.001), new Point_3D(1.5,2.5,0.001), new Point_3D(2,0,0.001)));
        mesh.add(new Facet_3D(new Point_3D(2,0,0.001), new Point_3D(1.5,2.5,0.001), new Point_3D(3,1,0.001)));
        mesh.add(new Facet_3D(new Point_3D(3,1,0.001), new Point_3D(1.5,2.5,0.001), new Point_3D(4,4,0.001)));
        mesh.add(new Facet_3D(new Point_3D(4,4,0.001), new Point_3D(1.5,2.5,0.001), new Point_3D(0,5,0.001)));

        Simplify_Mesh_3D simplify_mesh = new Simplify_Mesh_3D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
//        System.out.println();
//        int count = 0;
//        for (Iterator<Facet_3D> it = mesh.iterator(); it.hasNext();) {
//            Facet_3D f = it.next();
//            System.out.println("mesh[" + count++ + "] facet p1 x: " + f.get_point1().get_x() + " y: " +
//                    f.get_point1().get_y() + " z: " + f.get_point1().get_z() + " p2 x: " + 
//                    f.get_point2().get_x() + " y: " + f.get_point2().get_y() + " z: " + 
//                    f.get_point2().get_z() + " p3 x: " + f.get_point3().get_x() + " y: " + 
//                    f.get_point3().get_y() + " z: " + f.get_point3().get_z());
//        }
        Assert.assertTrue(mesh.size() == 6);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,1,0), new Point_3D(2,0,0), new Point_3D(0,5,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,1,0), new Point_3D(0,5,0), new Point_3D(4,4,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,1,0.001), new Point_3D(2,0,0.001), new Point_3D(0,5,0.001)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,1,0.001), new Point_3D(0,5,0.001), new Point_3D(4,4,0.001)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0.001), new Point_3D(2,0,0.001), new Point_3D(0,0,0.001)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(2,0,0), new Point_3D(0,0,0)), mesh, precision));
    }
}
