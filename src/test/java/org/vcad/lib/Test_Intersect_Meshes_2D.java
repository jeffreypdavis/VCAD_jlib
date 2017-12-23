/*
 * Test_Intersect_Meshes_2D.java
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
public class Test_Intersect_Meshes_2D extends TestBase {
    
    public Test_Intersect_Meshes_2D() {
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
    public void test_intersect_meshes_1() {
        // facet and other_facet do not intersect
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(0, 10), new Point_2D(5, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(-0.25, 0.25), new Point_2D(-0.25, 0.75), new Point_2D(-0.75, 0.25));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_2() {
        // facet and other_facet do not intersect
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0.25, 0.5), new Point_2D(0.75, 0.75), new Point_2D(0.75, 0.25));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(0, 0), new Point_2D(0, 10), new Point_2D(5, 0));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.25, 0.5), new Point_2D(0.75, 0.25), new Point_2D(0.75, 0.75)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.25, 0.5), new Point_2D(0, 0), new Point_2D(0.75, 0.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0, 0), new Point_2D(0.25, 0.5), new Point_2D(0, 10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.25, 0.5), new Point_2D(0.75, 0.75), new Point_2D(0, 10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 0), new Point_2D(0.75, 0.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.75, 0.25), new Point_2D(5, 0), new Point_2D(0.75, 0.75)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.75, 0.75), new Point_2D(5, 0), new Point_2D(0, 10)), result1, precision));
        Assert.assertTrue(result2.size() == 1);
        Assert.assertTrue(mesh_contains(facet2, result2, precision));
    }

    @Test
    public void test_intersect_meshes_3() {
        // facet and other_facet do not intersect
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(0, 10), new Point_2D(5, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(0.25, 0.25), new Point_2D(0.25, 0.75), new Point_2D(0.75, 0.25));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.25,0.25), new Point_2D(0.75,0.25), new Point_2D(0.25,0.75)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.25,0.25), new Point_2D(0,0), new Point_2D(0.75,0.25)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,0), new Point_2D(0.75,0.25)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.25,0.25), new Point_2D(0.25,0.75), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.75,0.25), new Point_2D(5,0), new Point_2D(0,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(0.25,0.75), new Point_2D(0,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.25,0.75), new Point_2D(0.75,0.25), new Point_2D(0,10)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_4() {
        // two corners of otherFacet in facet. otherFacet sides intersect same facet side
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(0, 10), new Point_2D(5, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(-5, 3), new Point_2D(1, 2), new Point_2D(0.75, 1));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,1.26087), new Point_2D(0,2.16667), new Point_2D(-5,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,1.26087), new Point_2D(0.75,1), new Point_2D(0,2.16667)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,2.16667), new Point_2D(0.75,1), new Point_2D(1,2)), result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.75,1), new Point_2D(1,2), new Point_2D(0,1.26087)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,1.26087), new Point_2D(0,0), new Point_2D(0.75,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,0), new Point_2D(0.75,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,2), new Point_2D(0,2.16667), new Point_2D(0,1.26087)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,2.16667), new Point_2D(1,2), new Point_2D(0,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,2), new Point_2D(5,0), new Point_2D(0,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.75,1), new Point_2D(5,0), new Point_2D(1,2)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_5() {
        // corner of otherFacet on side of facet
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(0, 10), new Point_2D(5, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(-5, 3), new Point_2D(0, 5), new Point_2D(-3, -1));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,0), new Point_2D(0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,5), new Point_2D(5,0), new Point_2D(0,10)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_6() {
        // otherFacet inside facet with a corners crossing each side
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(0, 3), new Point_2D(5, -1), new Point_2D(10, 3));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7,3), new Point_2D(3,3), new Point_2D(8.33333,1.66667)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(1.66667,1.66667), new Point_2D(8.33333,1.66667)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.66667,1.66667), new Point_2D(3.75,0), new Point_2D(8.33333,1.66667)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.75,0), new Point_2D(6.25,0), new Point_2D(8.33333,1.66667)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.66667,1.66667), new Point_2D(3,3), new Point_2D(0,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.75,0), new Point_2D(5,-1), new Point_2D(6.25,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7,3), new Point_2D(8.33333,1.66667), new Point_2D(10,3)), result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(6.25,0), new Point_2D(8.33333,1.66667), new Point_2D(3.75,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.75,0), new Point_2D(8.33333,1.66667), new Point_2D(1.66667,1.66667)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.66667,1.66667), new Point_2D(8.33333,1.66667), new Point_2D(3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(8.33333,1.66667), new Point_2D(7,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(7,3), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.75,0), new Point_2D(1.66667,1.66667), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(6.25,0), new Point_2D(10,0), new Point_2D(8.33333,1.66667)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_7() {
        // otherFacet inside facet with a corner touching each side
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(3, 3), new Point_2D(5, 0), new Point_2D(7, 3));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(5,0), new Point_2D(7,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(3,3), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(7,3), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(10,0), new Point_2D(7,3)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_8() {
        // otherFacet has one corner inside
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(2, -3), new Point_2D(4, -3), new Point_2D(3, 1));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.75,0), new Point_2D(2,-3), new Point_2D(3.25,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.75,0), new Point_2D(3.25,0), new Point_2D(3,1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,-3), new Point_2D(4,-3), new Point_2D(3.25,0)), result1, precision));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,1), new Point_2D(2.75,0), new Point_2D(3.25,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.75,0), new Point_2D(3,1), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.25,0), new Point_2D(10,0), new Point_2D(3,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(3,1), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(5,5), new Point_2D(3,1)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_9() {
        // otherFacet has sides passing through facet corners
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(5, 7), new Point_2D(5, 3), new Point_2D(-5, -3));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(5,7), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(0,0), new Point_2D(5,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,7), new Point_2D(-5,-3), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,3), new Point_2D(5,5), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(5,3), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,3), new Point_2D(0,0), new Point_2D(10,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_10() {
        // otherFacet has one side passing through corner through opposite side
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(5, 7), new Point_2D(5, -3), new Point_2D(-5, -3));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,0), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(5,7), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,7), new Point_2D(-5,-3), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(-5,-3), new Point_2D(5,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(-5,-3), new Point_2D(5,-3)), result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(5,5), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(10,0), new Point_2D(5,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_11() {
        // otherFacet shares one side with facet
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(0, 0), new Point_2D(0, 5), new Point_2D(5, 5));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_12() {
        // otherFacet shares some of one side with facet
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(0, 0), new Point_2D(0, 5), new Point_2D(3, 3));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(10,0), new Point_2D(5,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_13() {
        // otherFacet has 2 corners inside facet, one side on each of facet corner
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5, 5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(2, 1), new Point_2D(8, 1), new Point_2D(5, 10));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(2.5,2.5), new Point_2D(7.5,2.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.5,2.5), new Point_2D(2,1), new Point_2D(7.5,2.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.5,2.5), new Point_2D(5,5), new Point_2D(5,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(8,1), new Point_2D(7.5,2.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(7.5,2.5), new Point_2D(5,10)), result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(8,1), new Point_2D(7.5,2.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(7.5,2.5), new Point_2D(2.5,2.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.5,2.5), new Point_2D(7.5,2.5), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.5,2.5), new Point_2D(8,1), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(10,0), new Point_2D(8,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(0,0), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.5,2.5), new Point_2D(0,0), new Point_2D(2,1)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_14() {
        // otherFacet shares corner, two sides pass over facet
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(5, 5), new Point_2D(10, 0), new Point_2D(4, 3));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(4, 3), new Point_2D(7, 1), new Point_2D(12, 2));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4,3), new Point_2D(7,1), new Point_2D(7.71429,1.14286)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4,3), new Point_2D(7.71429,1.14286), new Point_2D(7.42857,2.57143)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(7.71429,1.14286), new Point_2D(8.66667,1.33333)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(8.66667,1.33333), new Point_2D(12,2)), result1, precision));
        Assert.assertTrue(result2.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(7.71429,1.14286), new Point_2D(8.66667,1.33333)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(4,3), new Point_2D(7.71429,1.14286)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4,3), new Point_2D(7.42857,2.57143), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.66667,1.33333), new Point_2D(7.71429,1.14286), new Point_2D(10,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_15() {
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5,5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(2, 2), new Point_2D(4, 4), new Point_2D(2.5, 2));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.5,2), new Point_2D(4,4), new Point_2D(2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4,4), new Point_2D(2.5,2), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(2.5,2), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.5,2), new Point_2D(2,2), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(2,2), new Point_2D(0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_16() {
        // shares a corner, but no intersection
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5,5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(5, 5), new Point_2D(0, 10), new Point_2D(10, 10));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_17() {
        // facet shares part of other_facet side
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5,5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(0, 10), new Point_2D(11, -1), new Point_2D(20, 5));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,10), new Point_2D(5,5), new Point_2D(20,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(10,0), new Point_2D(20,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(11,-1), new Point_2D(20,5)), result1, precision));
        Assert.assertTrue(result2.size() == 1);
        Assert.assertTrue(mesh_contains(facet2, result2, precision));
    }

    @Test
    public void test_intersect_meshes_18() {
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0, 0), new Point_2D(5,5), new Point_2D(10, 0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        Facet_2D facet1 = new Facet_2D(new Point_2D(-2, 0), new Point_2D(3, 6), new Point_2D(1, -1));
        mesh1.add(facet1);

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(1.28571,0), new Point_2D(1.8,1.8)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.8,1.8), new Point_2D(3,6), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2,0), new Point_2D(3,6), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,-1), new Point_2D(0,0), new Point_2D(-2,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.28571,0), new Point_2D(0,0), new Point_2D(1,-1)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(1.28571,0), new Point_2D(1.8,1.8)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.28571,0), new Point_2D(10,0), new Point_2D(1.8,1.8)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(5,5), new Point_2D(1.8,1.8)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_19() {
        // intersect mesh with a mesh of one facet. other mesh does not touch facet
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(-1,-1), new Point_2D(-5,-5), new Point_2D(-10,-1)));
        mesh1.add(new Facet_2D(new Point_2D(-1,-1), new Point_2D(-10,-1), new Point_2D(-5,2)));
        mesh1.add(new Facet_2D(new Point_2D(-5,2), new Point_2D(-1,-1), new Point_2D(-3,6)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_20() {
        // intersect mesh with a mesh of one facet. other mesh corner touches facet side
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(3,3), new Point_2D(-5,-3), new Point_2D(-10,-1)));
        mesh1.add(new Facet_2D(new Point_2D(3,3), new Point_2D(-10,-1), new Point_2D(-5,1)));
        mesh1.add(new Facet_2D(new Point_2D(-5,1), new Point_2D(3,3), new Point_2D(-3,6)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(-10,-1), new Point_2D(-5,-3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(-5,1), new Point_2D(-10,-1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-5,1), new Point_2D(3,3), new Point_2D(-3,6)), result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(0,0), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(10,0), new Point_2D(5,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_21() {
        // intersect mesh with a mesh of one facet. other mesh shares a side
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(5,5), new Point_2D(0,0), new Point_2D(-5,-1)));
        mesh1.add(new Facet_2D(new Point_2D(5,5), new Point_2D(-5,-1), new Point_2D(-3,3)));
        mesh1.add(new Facet_2D(new Point_2D(0,0), new Point_2D(-5,-1), new Point_2D(-3,6)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_22() {
        // intersect mesh with a mesh of one facet. other mesh overlaps corner with one facet
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(2,3), new Point_2D(8,3), new Point_2D(5,7)));
        mesh1.add(new Facet_2D(new Point_2D(2,3), new Point_2D(5,7), new Point_2D(1,4)));
        mesh1.add(new Facet_2D(new Point_2D(8,3), new Point_2D(5,7), new Point_2D(7,10)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(3,3), new Point_2D(7,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7,3), new Point_2D(8,3), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8,3), new Point_2D(5,7), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(5,7)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(5,7), new Point_2D(2,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,3), new Point_2D(5,7), new Point_2D(1,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8,3), new Point_2D(7,10), new Point_2D(5,7)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(0,0), new Point_2D(7,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(7,3), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(7,3)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_23() {
        // intersect mesh with a mesh of one facet. other mesh 2 corners cover facet corner
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(2,3), new Point_2D(5,3), new Point_2D(5,7)));
        mesh1.add(new Facet_2D(new Point_2D(2,3), new Point_2D(5,7), new Point_2D(1,4)));
        mesh1.add(new Facet_2D(new Point_2D(5,3), new Point_2D(5,7), new Point_2D(12,1)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(2,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(5,3), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,3), new Point_2D(5,5), new Point_2D(5,7)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,3), new Point_2D(5,7), new Point_2D(1,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.8,2.2), new Point_2D(5,5), new Point_2D(5,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.8,2.2), new Point_2D(12,1), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(12,1), new Point_2D(5,7)), result1, precision));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,3), new Point_2D(5,5), new Point_2D(3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(7.8,2.2), new Point_2D(5,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(0,0), new Point_2D(7.8,2.2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.8,2.2), new Point_2D(0,0), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,3), new Point_2D(7.8,2.2), new Point_2D(5,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_24() {
        // intersect mesh with a mesh of one facet. other mesh has facet inside facet
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(2,3), new Point_2D(2,1), new Point_2D(4,3)));
        mesh1.add(new Facet_2D(new Point_2D(2,1), new Point_2D(4,3), new Point_2D(7,1)));
        mesh1.add(new Facet_2D(new Point_2D(4,3), new Point_2D(7,1), new Point_2D(12,2)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,2), new Point_2D(3,3), new Point_2D(2,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,2), new Point_2D(2,1), new Point_2D(3,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(2,1), new Point_2D(4,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(7,1), new Point_2D(4,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(4,3), new Point_2D(8.66667,1.33333)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7,1), new Point_2D(8.66667,1.33333), new Point_2D(4,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(12,2), new Point_2D(7.42857,2.57143), new Point_2D(8.66667,1.33333)), result1, precision));
        Assert.assertTrue(result2.size() == 11);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7,1), new Point_2D(8.66667,1.33333), new Point_2D(4,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(4,3), new Point_2D(8.66667,1.33333)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(5,5), new Point_2D(4,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(4,3), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.66667,1.33333), new Point_2D(7,1), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(10,0), new Point_2D(7,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(0,0), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(2,1), new Point_2D(2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7,1), new Point_2D(4,3), new Point_2D(2,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(2,1), new Point_2D(4,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,3), new Point_2D(2,2), new Point_2D(2,1)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_25() {
        // intersect mesh with a mesh of one facet. other mesh completely covers facet with several facets
        Mesh_2D mesh2 = new Mesh_2D();
        Facet_2D facet2 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0));
        mesh2.add(facet2);
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(-2,0), new Point_2D(3,6), new Point_2D(1,-1)));
        mesh1.add(new Facet_2D(new Point_2D(1,-1), new Point_2D(3,6), new Point_2D(5,0)));
        mesh1.add(new Facet_2D(new Point_2D(5,0), new Point_2D(3,6), new Point_2D(7,4)));
        mesh1.add(new Facet_2D(new Point_2D(7,4), new Point_2D(5,0), new Point_2D(12,-2)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 17);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(1.28571,0), new Point_2D(1.8,1.8)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.8,1.8), new Point_2D(3,6), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,6), new Point_2D(-2,0), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2,0), new Point_2D(1,-1), new Point_2D(0,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(1,-1), new Point_2D(1.28571,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.8,1.8), new Point_2D(1.28571,0), new Point_2D(3.75,3.75)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.28571,0), new Point_2D(5,0), new Point_2D(3.75,3.75)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(1.28571,0), new Point_2D(1,-1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.8,1.8), new Point_2D(3.75,3.75), new Point_2D(3,6)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(3.75,3.75), new Point_2D(6.66667,3.33333)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(6.66667,3.33333), new Point_2D(3.75,3.75)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.75,3.75), new Point_2D(5,5), new Point_2D(3,6)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(6.66667,3.33333), new Point_2D(7,4), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(6.66667,3.33333), new Point_2D(5,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(6.66667,3.33333), new Point_2D(10,0), new Point_2D(7,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(12,-2), new Point_2D(7,4), new Point_2D(10,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(12,-2), new Point_2D(10,0)), result1, precision));
        Assert.assertTrue(result2.size() == 6);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,0), new Point_2D(6.66667,3.33333), new Point_2D(3.75,3.75)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(3.75,3.75), new Point_2D(6.66667,3.33333)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.28571,0), new Point_2D(5,0), new Point_2D(3.75,3.75)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.75,3.75), new Point_2D(1.8,1.8), new Point_2D(1.28571,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(6.66667,3.33333), new Point_2D(5,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.28571,0), new Point_2D(1.8,1.8), new Point_2D(0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_26() {
        // intersect mesh does not intersect mesh
        Mesh_2D mesh2 = new Mesh_2D();
        mesh2.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(2,1)));
        mesh2.add(new Facet_2D(new Point_2D(5,5), new Point_2D(2,1), new Point_2D(4,4)));
        mesh2.add(new Facet_2D(new Point_2D(4,4), new Point_2D(2,1), new Point_2D(6,0)));
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(-2,6), new Point_2D(0,8), new Point_2D(1,5)));
        mesh1.add(new Facet_2D(new Point_2D(1,5), new Point_2D(0,8), new Point_2D(3,10)));
        mesh1.add(new Facet_2D(new Point_2D(3,10), new Point_2D(1,5), new Point_2D(5,6)));
        mesh1.add(new Facet_2D(new Point_2D(5,6), new Point_2D(3,10), new Point_2D(8,8)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_27() {
        // intersect mesh shares one side with mesh
        Mesh_2D mesh2 = new Mesh_2D();
        mesh2.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(2,1)));
        mesh2.add(new Facet_2D(new Point_2D(5,5), new Point_2D(2,1), new Point_2D(5,4)));
        mesh2.add(new Facet_2D(new Point_2D(5,4), new Point_2D(2,1), new Point_2D(6,0)));
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(-2,6), new Point_2D(0,8), new Point_2D(1,5)));
        mesh1.add(new Facet_2D(new Point_2D(1,5), new Point_2D(0,8), new Point_2D(3,10)));
        mesh1.add(new Facet_2D(new Point_2D(3,10), new Point_2D(1,5), new Point_2D(5,5)));
        mesh1.add(new Facet_2D(new Point_2D(5,5), new Point_2D(1,5), new Point_2D(0,0)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_28() {
        // intersect mesh overlaps part of one facet of mesh
        Mesh_2D mesh2 = new Mesh_2D();
        mesh2.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(2,1)));
        mesh2.add(new Facet_2D(new Point_2D(5,5), new Point_2D(2,1), new Point_2D(5,4)));
        mesh2.add(new Facet_2D(new Point_2D(5,4), new Point_2D(2,1), new Point_2D(6,0)));
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(-2,6), new Point_2D(0,8), new Point_2D(1,5)));
        mesh1.add(new Facet_2D(new Point_2D(1,5), new Point_2D(0,8), new Point_2D(3,10)));
        mesh1.add(new Facet_2D(new Point_2D(3,10), new Point_2D(1,5), new Point_2D(5,5)));
        mesh1.add(new Facet_2D(new Point_2D(5,5), new Point_2D(1,5), new Point_2D(2,1.5)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2,6), new Point_2D(1,5), new Point_2D(0,8)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,5), new Point_2D(3,10), new Point_2D(0,8)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,10), new Point_2D(1,5), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(1,5), new Point_2D(1.88889,1.88889)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(1.88889,1.88889), new Point_2D(2,1.5)), result1, precision));
        Assert.assertTrue(result2.size() == 6);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1.5), new Point_2D(5,5), new Point_2D(1.88889,1.88889)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.88889,1.88889), new Point_2D(0,0), new Point_2D(2,1.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1.5), new Point_2D(0,0), new Point_2D(2,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1.5), new Point_2D(2,1), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(2,1), new Point_2D(5,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,4), new Point_2D(2,1), new Point_2D(6,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_29() {
        // intersect mesh overlaps two mesh facets
        Mesh_2D mesh2 = new Mesh_2D();
        mesh2.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(2,1)));
        mesh2.add(new Facet_2D(new Point_2D(5,5), new Point_2D(2,1), new Point_2D(5,4)));
        mesh2.add(new Facet_2D(new Point_2D(5,4), new Point_2D(2,1), new Point_2D(6,0)));
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(-2,6), new Point_2D(0,8), new Point_2D(1,5)));
        mesh1.add(new Facet_2D(new Point_2D(1,5), new Point_2D(0,8), new Point_2D(3,10)));
        mesh1.add(new Facet_2D(new Point_2D(3,10), new Point_2D(1,5), new Point_2D(5,5)));
        mesh1.add(new Facet_2D(new Point_2D(5,5), new Point_2D(1,5), new Point_2D(4.5,4)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 6);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2,6), new Point_2D(1,5), new Point_2D(0,8)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,5), new Point_2D(3,10), new Point_2D(0,8)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3,10), new Point_2D(1,5), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(4.11111,4.11111), new Point_2D(4.29412,4.05882)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(1,5), new Point_2D(4.11111,4.11111)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(4.29412,4.05882), new Point_2D(4.5,4)), result1, precision));
        Assert.assertTrue(result2.size() == 8);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.11111,4.11111), new Point_2D(0,0), new Point_2D(4.29412,4.05882)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.11111,4.11111), new Point_2D(4.29412,4.05882), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(2,1), new Point_2D(4.29412,4.05882)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.29412,4.05882), new Point_2D(4.5,4), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(4.5,4), new Point_2D(5,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.29412,4.05882), new Point_2D(2,1), new Point_2D(4.5,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(5,4), new Point_2D(4.5,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,4), new Point_2D(2,1), new Point_2D(6,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_30() {
        // intersect mesh facet is in center of facets
        Mesh_2D mesh2 = new Mesh_2D();
        mesh2.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0)));
        mesh2.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,10)));
        mesh2.add(new Facet_2D(new Point_2D(0,10), new Point_2D(5,5), new Point_2D(10,10)));
        Mesh_2D mesh1 = new Mesh_2D();
        mesh1.add(new Facet_2D(new Point_2D(4,1), new Point_2D(0.5,1), new Point_2D(6,7)));

        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();

        Mesh_2D result1 = new Mesh_2D();
        Mesh_2D result2 = new Mesh_2D();
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
//        System.out.println();
//        int count = 0;
//        for (Iterator<Facet_2D> it = result1.iterator(); it.hasNext();) {
//            Facet_2D f = it.next();
//            System.out.println("result1[" + count++ + "] facet p1 x: " + f.get_point1().get_x() + 
//                    " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
//                    " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
//                    " y: " + f.get_point3().get_y());
//        }
//        count = 0;
//        for (Iterator<Facet_2D> it = result2.iterator(); it.hasNext();) {
//            Facet_2D f = it.next();
//            System.out.println("result2[" + count++ + "] facet p1 x: " + f.get_point1().get_x() + 
//                    " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
//                    " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
//                    " y: " + f.get_point3().get_y());
//        }
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5.25,4.75), new Point_2D(5.5,5.5), new Point_2D(5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(5.5,5.5), new Point_2D(4.56522,5.43478)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(6,7), new Point_2D(4.56522,5.43478), new Point_2D(5.5,5.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(4.56522,5.43478), new Point_2D(1,1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.5,1), new Point_2D(1,1), new Point_2D(4.56522,5.43478)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5.25,4.75), new Point_2D(5,5), new Point_2D(1,1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,1), new Point_2D(4,1), new Point_2D(5.25,4.75)), result1, precision));
        Assert.assertTrue(result2.size() == 15);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4,1), new Point_2D(5.25,4.75), new Point_2D(1,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(1,1), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,1), new Point_2D(5.25,4.75), new Point_2D(5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5.25,4.75), new Point_2D(4,1), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4,1), new Point_2D(1,1), new Point_2D(10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0.5,1), new Point_2D(1,1), new Point_2D(4.56522,5.43478)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,1), new Point_2D(0.5,1), new Point_2D(0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1,1), new Point_2D(5,5), new Point_2D(4.56522,5.43478)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(0.5,1), new Point_2D(0,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.56522,5.43478), new Point_2D(0,10), new Point_2D(0.5,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(6,7), new Point_2D(4.56522,5.43478), new Point_2D(5.5,5.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.56522,5.43478), new Point_2D(6,7), new Point_2D(0,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,5), new Point_2D(5.5,5.5), new Point_2D(4.56522,5.43478)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,10), new Point_2D(6,7), new Point_2D(10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5.5,5.5), new Point_2D(10,10), new Point_2D(6,7)), result2, precision));
    }
    
    @Test
    public void test_mesh_difference_1() {
        // shapes do not overlap
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10,false, new Point_2D(20,20));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.difference(shape, shape2, result);
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,10),new Point_2D(0,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,0),new Point_2D(10,10)),result,precision));
    }

    @Test
    public void test_mesh_difference_2() {
        // test shapes share side
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10,false, new Point_2D(10,0));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.difference(shape,shape2, result);
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,10),new Point_2D(0,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,0),new Point_2D(10,10)),result,precision));
    }

    @Test
    public void test_mesh_difference_3() {
        // test shape inside shape
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,2);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.circle(shape2,15,2);
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.difference(shape,shape2,result);
        Assert.assertTrue(result.size() == 24);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.2246467991473533E-15,20.0), new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(14.142135623730951,14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(14.142135623730951,14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(14.142135623730951,14.14213562373095), new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-8.106601717798211,11.642135623730951), new Point_2D(9.18485099360515E-16,15.0), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-14.14213562373095,14.142135623730951), new Point_2D(9.18485099360515E-16,15.0), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(9.18485099360515E-16,15.0), new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(13.964466094067262,2.5000000000000004), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-20.0,2.4492935982947065E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20.0,2.4492935982947065E-15), new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-8.106601717798211,11.642135623730951), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(13.964466094067262,2.5000000000000004), new Point_2D(15.0,0.0), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(13.964466094067262,-2.5), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(-20.0,2.4492935982947065E-15), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(-14.142135623730955,-14.14213562373095), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(13.964466094067262,-2.5), new Point_2D(11.46446609406726,-8.535533905932738), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(-3.673940397442059E-15,-20.0), new Point_2D(10.60660171779821,-10.606601717798215)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-3.673940397442059E-15,-20.0), new Point_2D(14.142135623730947,-14.142135623730955), new Point_2D(10.60660171779821,-10.606601717798215)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,-10.606601717798215), new Point_2D(14.142135623730947,-14.142135623730955), new Point_2D(11.46446609406726,-8.535533905932738)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.46446609406726,-8.535533905932738), new Point_2D(14.142135623730947,-14.142135623730955), new Point_2D(20.0,0.0)), result, precision));
    }

    @Test
    public void test_mesh_difference_4() {
        // test shape inside shape
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,2);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.circle(shape2,15,2,new Point_2D(0,20));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.difference(shape,shape2,result);
        Assert.assertTrue(result.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,9.393398282201785), new Point_2D(20.0,0.0), new Point_2D(12.803300858899105,14.696699141100893)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(14.142135623730951,14.14213562373095), new Point_2D(12.803300858899105,14.696699141100893)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,9.393398282201785), new Point_2D(3.9644660940672636,6.642135623730951), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-12.248737341529164,13.35786437626905), new Point_2D(-12.803300858899107,14.696699141100893), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.9644660940672636,6.642135623730951), new Point_2D(-2.7554552980815444E-15,5.0), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-2.7554552980815444E-15,5.0), new Point_2D(-20.0,2.4492935982947065E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,5.0), new Point_2D(-10.606601717798215,9.393398282201789), new Point_2D(-20.0,2.4492935982947065E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,9.393398282201789), new Point_2D(-12.248737341529164,13.35786437626905), new Point_2D(-20.0,2.4492935982947065E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-12.248737341529164,13.35786437626905), new Point_2D(-14.14213562373095,14.142135623730951), new Point_2D(-20.0,2.4492935982947065E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-20.0,2.4492935982947065E-15), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-14.142135623730955,-14.14213562373095), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-3.673940397442059E-15,-20.0), new Point_2D(14.142135623730947,-14.142135623730955)), result, precision));
    }

    @Test
    public void test_mesh_difference_5() {
        // shapes are the same
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10);
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.difference(shape,shape2,result);
//        System.out.println();
//        System.out.print("result: polygon(points=[");
//        int count = 0;
//        for (Iterator<Point_2D> p_it = result.point_iterator(); p_it.hasNext();++count) {
//            if (count > 0)
//                System.out.print(',');
//            Point_2D pt = p_it.next();
//            System.out.print("[" + pt.get_x() + "," + pt.get_y() + "]");
//        }
//        System.out.print("], paths=[");
//        count = 0;
//        for (Iterator<Facet> f_it = result.facet_iterator(); f_it.hasNext(); ++count) {
//            if (count > 0)
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            Facet f = f_it.next();
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
////        count = 0;
////        for (Iterator<Facet_2D> it = result.iterator(); it.hasNext(); ++count) {
////            Facet_2D f = it.next();
////            System.out.println("result[" + count + "] facet p1 x: " + f.get_point1().get_x() + 
////                    " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
////                    " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
////                    " y: " + f.get_point3().get_y());
////        }
//        System.out.println("        Assert.assertTrue(result.size() == " + result.size() + ");");
//        for (Iterator<Facet_2D> it = result.iterator(); it.hasNext(); ++count) {
//            Facet_2D f = it.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(" + 
//                    f.get_point1().get_x() + "," + f.get_point1().get_y() + 
//                    "), new Point_2D(" + f.get_point2().get_x() + "," + 
//                    f.get_point2().get_y() + "), new Point_2D(" + f.get_point3().get_x() + 
//                    "," + f.get_point3().get_y() + ")), result, precision));");
//        }
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_mesh_intersection_1() {
        // shapes completely overlap
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10);
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.intersection(shape,shape2,result);
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,10),new Point_2D(0,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,0),new Point_2D(10,10)),result,precision));
    }

    @Test
    public void test_mesh_intersection_2() {
        // test shapes share side
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10,false, new Point_2D(10,0));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.intersection(shape,shape2,result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_mesh_intersection_3() {
        // test shape inside shape
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,2);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.circle(shape2,15,2);
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.intersection(shape,shape2,result);
        Assert.assertTrue(result.size() == 18);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(11.464466094067262,8.535533905932736)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(9.18485099360515E-16,15.0), new Point_2D(-8.106601717798211,11.642135623730951), new Point_2D(11.464466094067262,3.5355339059327378)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(9.18485099360515E-16,15.0), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(8.535533905932738,11.46446609406726)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(11.464466094067262,8.535533905932736)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(13.964466094067262,2.5000000000000004)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(15.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(-10.606601717798211,10.606601717798213)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(-8.106601717798211,11.642135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(13.964466094067262,2.5000000000000004), new Point_2D(11.464466094067262,3.5355339059327378)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(11.464466094067262,-3.5355339059327373), new Point_2D(13.964466094067262,-2.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(11.464466094067262,-3.5355339059327373)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(11.464466094067262,-3.5355339059327373)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(15.0,0.0), new Point_2D(-15.0,1.83697019872103E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(11.464466094067262,-3.5355339059327373)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.464466094067262,-3.5355339059327373), new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(13.964466094067262,-2.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(13.964466094067262,-2.5), new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(11.46446609406726,-8.535533905932738)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(11.464466094067262,-3.5355339059327373), new Point_2D(-8.106601717798215,-11.64213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,-10.606601717798215), new Point_2D(11.46446609406726,-8.535533905932738), new Point_2D(8.535533905932732,-11.464466094067266)), result, precision));
    }

    @Test
    public void test_mesh_intersection_4() {
        // test shape inside shape
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,2);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.circle(shape2,15,2,new Point_2D(0,20));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.intersection(shape,shape2,result);
        Assert.assertTrue(result.size() == 16);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.499999999999998,12.5), new Point_2D(10.60660171779821,9.393398282201785), new Point_2D(10.606601717798211,15.606601717798213)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,9.393398282201785), new Point_2D(12.803300858899105,14.696699141100893), new Point_2D(10.606601717798211,15.606601717798213)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.393398282201787,15.606601717798213), new Point_2D(7.499999999999998,12.5), new Point_2D(7.5,16.893398282201787)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.499999999999998,12.5), new Point_2D(10.606601717798211,15.606601717798213), new Point_2D(7.5,16.893398282201787)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.393398282201787,15.606601717798213), new Point_2D(7.5,16.893398282201787), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,9.393398282201785), new Point_2D(7.499999999999998,12.5), new Point_2D(3.9644660940672636,6.642135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.499999999999998,12.5), new Point_2D(2.3223304703363095,7.322330470336311), new Point_2D(3.9644660940672636,6.642135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(7.499999999999998,12.5), new Point_2D(4.393398282201787,15.606601717798213), new Point_2D(2.3223304703363095,7.322330470336311)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.393398282201787,15.606601717798213), new Point_2D(-6.642135623730951,11.03553390593274), new Point_2D(2.3223304703363095,7.322330470336311)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-12.248737341529164,13.35786437626905), new Point_2D(-6.642135623730951,11.03553390593274), new Point_2D(-12.803300858899107,14.696699141100893)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-6.642135623730951,11.03553390593274), new Point_2D(4.393398282201787,15.606601717798213), new Point_2D(-12.803300858899107,14.696699141100893)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(4.393398282201787,15.606601717798213), new Point_2D(1.2246467991473533E-15,20.0), new Point_2D(-12.803300858899107,14.696699141100893)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,5.0), new Point_2D(3.9644660940672636,6.642135623730951), new Point_2D(2.3223304703363095,7.322330470336311)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,5.0), new Point_2D(2.3223304703363095,7.322330470336311), new Point_2D(-10.606601717798215,9.393398282201789)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,9.393398282201789), new Point_2D(2.3223304703363095,7.322330470336311), new Point_2D(-6.642135623730951,11.03553390593274)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,9.393398282201789), new Point_2D(-6.642135623730951,11.03553390593274), new Point_2D(-12.248737341529164,13.35786437626905)), result, precision));
    }

    @Test
    public void test_mesh_intersection_5() {
        // shapes are the same
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10);
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.intersection(shape,shape2,result);
//        System.out.println();
//        System.out.print("result: polygon(points=[");
//        int count = 0;
//        for (Iterator<Point_2D> p_it = result.point_iterator(); p_it.hasNext();++count) {
//            if (count > 0)
//                System.out.print(',');
//            Point_2D pt = p_it.next();
//            System.out.print("[" + pt.get_x() + "," + pt.get_y() + "]");
//        }
//        System.out.print("], paths=[");
//        count = 0;
//        for (Iterator<Facet> f_it = result.facet_iterator(); f_it.hasNext(); ++count) {
//            if (count > 0)
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            Facet f = f_it.next();
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
////        count = 0;
////        for (Iterator<Facet_2D> it = result.iterator(); it.hasNext(); ++count) {
////            Facet_2D f = it.next();
////            System.out.println("result[" + count + "] facet p1 x: " + f.get_point1().get_x() + 
////                    " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
////                    " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
////                    " y: " + f.get_point3().get_y());
////        }
//        System.out.println("        Assert.assertTrue(result.size() == " + result.size() + ");");
//        for (Iterator<Facet_2D> it = result.iterator(); it.hasNext(); ++count) {
//            Facet_2D f = it.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(" + 
//                    f.get_point1().get_x() + "," + f.get_point1().get_y() + 
//                    "), new Point_2D(" + f.get_point2().get_x() + "," + 
//                    f.get_point2().get_y() + "), new Point_2D(" + f.get_point3().get_x() + 
//                    "," + f.get_point3().get_y() + ")), result, precision));");
//        }
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,10),new Point_2D(0,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,0),new Point_2D(10,10)),result,precision));
    }

    @Test
    public void test_mesh_merge_1() {
        // shapes do not touch
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10, false, new Point_2D(0,15));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.merge(shape,shape2,result);
        Assert.assertTrue(result.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,10),new Point_2D(0,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,0),new Point_2D(10,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,15),new Point_2D(10,25),new Point_2D(0,25)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,15),new Point_2D(10,15),new Point_2D(10,25)),result,precision));
    }

    @Test
    public void test_mesh_merge_2() {
        // test shapes share side
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10,false, new Point_2D(10,5));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.merge(shape,shape2,result);
        Assert.assertTrue(result.size() == 6);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,10),new Point_2D(0,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0),new Point_2D(10,5),new Point_2D(0,0)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,5),new Point_2D(10,10),new Point_2D(0,0)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,5),new Point_2D(20,15),new Point_2D(10,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,15),new Point_2D(10,15),new Point_2D(10,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,5),new Point_2D(20,5),new Point_2D(20,15)),result,precision));
    }

    @Test
    public void test_mesh_merge_3() {
        // test shape inside shape
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,2);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.circle(shape2,15,2);
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.merge(shape,shape2,result);
        Assert.assertTrue(result.size() == 42);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(11.464466094067262,8.535533905932736)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.2246467991473533E-15,20.0), new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(14.142135623730951,14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.606601717798213,10.606601717798211), new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(14.142135623730951,14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(14.142135623730951,14.14213562373095), new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(9.18485099360515E-16,15.0), new Point_2D(-8.106601717798211,11.642135623730951), new Point_2D(11.464466094067262,3.5355339059327378)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-8.106601717798211,11.642135623730951), new Point_2D(9.18485099360515E-16,15.0), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-14.14213562373095,14.142135623730951), new Point_2D(9.18485099360515E-16,15.0), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(9.18485099360515E-16,15.0), new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(9.18485099360515E-16,15.0), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(8.535533905932738,11.46446609406726)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932738,11.46446609406726), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(11.464466094067262,8.535533905932736)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(13.964466094067262,2.5000000000000004)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.464466094067262,8.535533905932736), new Point_2D(13.964466094067262,2.5000000000000004), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(15.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-20.0,2.4492935982947065E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20.0,2.4492935982947065E-15), new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(-8.106601717798211,11.642135623730951), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(-10.606601717798211,10.606601717798213)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798211,10.606601717798213), new Point_2D(11.464466094067262,3.5355339059327378), new Point_2D(-8.106601717798211,11.642135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(13.964466094067262,2.5000000000000004), new Point_2D(11.464466094067262,3.5355339059327378)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(13.964466094067262,2.5000000000000004), new Point_2D(15.0,0.0), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(11.464466094067262,-3.5355339059327373), new Point_2D(13.964466094067262,-2.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(13.964466094067262,-2.5), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,0.0), new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(11.464466094067262,-3.5355339059327373)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(11.464466094067262,-3.5355339059327373)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15.0,1.83697019872103E-15), new Point_2D(-20.0,2.4492935982947065E-15), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10.606601717798215,-10.606601717798211), new Point_2D(15.0,0.0), new Point_2D(-15.0,1.83697019872103E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(11.464466094067262,-3.5355339059327373)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.464466094067262,-3.5355339059327373), new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(13.964466094067262,-2.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(13.964466094067262,-2.5), new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(11.46446609406726,-8.535533905932738)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-8.106601717798215,-11.64213562373095), new Point_2D(-14.142135623730955,-14.14213562373095), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,-15.0), new Point_2D(11.464466094067262,-3.5355339059327373), new Point_2D(-8.106601717798215,-11.64213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(13.964466094067262,-2.5), new Point_2D(11.46446609406726,-8.535533905932738), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,-10.606601717798215), new Point_2D(11.46446609406726,-8.535533905932738), new Point_2D(8.535533905932732,-11.464466094067266)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8.535533905932732,-11.464466094067266), new Point_2D(-3.673940397442059E-15,-20.0), new Point_2D(10.60660171779821,-10.606601717798215)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-3.673940397442059E-15,-20.0), new Point_2D(14.142135623730947,-14.142135623730955), new Point_2D(10.60660171779821,-10.606601717798215)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10.60660171779821,-10.606601717798215), new Point_2D(14.142135623730947,-14.142135623730955), new Point_2D(11.46446609406726,-8.535533905932738)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.46446609406726,-8.535533905932738), new Point_2D(14.142135623730947,-14.142135623730955), new Point_2D(20.0,0.0)), result, precision));
    }

    @Test
    public void test_mesh_merge_4() {
        // test shape inside shape
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,2);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.circle(shape2,15,2,new Point_2D(0,30));
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.merge(shape,shape2,result);
        Assert.assertTrue(result.size() == 24);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.5355339059327373,16.464466094067262), new Point_2D(20.0,0.0), new Point_2D(6.035533905932736,17.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(14.142135623730951,14.14213562373095), new Point_2D(6.035533905932736,17.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.4999999999999964,17.5), new Point_2D(3.5355339059327373,16.464466094067262), new Point_2D(3.535533905932736,18.535533905932738)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.5355339059327373,16.464466094067262), new Point_2D(6.035533905932736,17.5), new Point_2D(3.535533905932736,18.535533905932738)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.4999999999999964,17.5), new Point_2D(3.535533905932736,18.535533905932738), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,15.0), new Point_2D(3.5355339059327373,16.464466094067262), new Point_2D(2.4999999999999964,17.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.5355339059327373,16.464466094067262), new Point_2D(-2.7554552980815444E-15,15.0), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,15.0), new Point_2D(-6.035533905932739,17.5), new Point_2D(20.0,0.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-6.035533905932739,17.5), new Point_2D(-14.14213562373095,14.142135623730951)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-2.7554552980815444E-15,15.0), new Point_2D(2.4999999999999964,17.5), new Point_2D(-6.035533905932739,17.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2.4999999999999964,17.5), new Point_2D(1.2246467991473533E-15,20.0), new Point_2D(-6.035533905932739,17.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-14.14213562373095,14.142135623730951), new Point_2D(-20.0,2.4492935982947065E-15)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-20.0,2.4492935982947065E-15), new Point_2D(-14.142135623730955,-14.14213562373095)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-14.142135623730955,-14.14213562373095), new Point_2D(-3.673940397442059E-15,-20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20.0,0.0), new Point_2D(-3.673940397442059E-15,-20.0), new Point_2D(14.142135623730947,-14.142135623730955)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,30.0), new Point_2D(10.606601717798213,40.60660171779821), new Point_2D(9.18485099360515E-16,45.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,30.0), new Point_2D(9.18485099360515E-16,45.0), new Point_2D(-10.606601717798211,40.60660171779821)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,30.0), new Point_2D(-10.606601717798211,40.60660171779821), new Point_2D(-15.0,30.000000000000004)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,30.0), new Point_2D(-15.0,30.000000000000004), new Point_2D(-10.606601717798215,19.393398282201787)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.535533905932736,18.535533905932738), new Point_2D(15.0,30.0), new Point_2D(1.2246467991473533E-15,20.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.2246467991473533E-15,20.0), new Point_2D(15.0,30.0), new Point_2D(-6.035533905932739,17.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,30.0), new Point_2D(-10.606601717798215,19.393398282201787), new Point_2D(-6.035533905932739,17.5)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(3.535533905932736,18.535533905932738), new Point_2D(6.035533905932736,17.5), new Point_2D(15.0,30.0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(15.0,30.0), new Point_2D(6.035533905932736,17.5), new Point_2D(10.60660171779821,19.393398282201787)), result, precision));
    }

    @Test
    public void test_mesh_merge_5() {
        // shapes are the same
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,10);
        Mesh_2D shape2 = new Mesh_2D();
        Shapes.rectangle(shape2,10,10);
        Mesh_2D result = new Mesh_2D();
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        intersect_meshes.merge(shape,shape2,result);
//        System.out.println();
//        System.out.print("result: polygon(points=[");
//        int count = 0;
//        for (Iterator<Point_2D> p_it = result.point_iterator(); p_it.hasNext();++count) {
//            if (count > 0)
//                System.out.print(',');
//            Point_2D pt = p_it.next();
//            System.out.print("[" + pt.get_x() + "," + pt.get_y() + "]");
//        }
//        System.out.print("], paths=[");
//        count = 0;
//        for (Iterator<Facet> f_it = result.facet_iterator(); f_it.hasNext(); ++count) {
//            if (count > 0)
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            Facet f = f_it.next();
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
////        count = 0;
////        for (Iterator<Facet_2D> it = result.iterator(); it.hasNext(); ++count) {
////            Facet_2D f = it.next();
////            System.out.println("result[" + count + "] facet p1 x: " + f.get_point1().get_x() + 
////                    " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
////                    " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
////                    " y: " + f.get_point3().get_y());
////        }
//        System.out.println("        Assert.assertTrue(result.size() == " + result.size() + ");");
//        for (Iterator<Facet_2D> it = result.iterator(); it.hasNext(); ++count) {
//            Facet_2D f = it.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(" + 
//                    f.get_point1().get_x() + "," + f.get_point1().get_y() + 
//                    "), new Point_2D(" + f.get_point2().get_x() + "," + 
//                    f.get_point2().get_y() + "), new Point_2D(" + f.get_point3().get_x() + 
//                    "," + f.get_point3().get_y() + ")), result, precision));");
//        }
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,10),new Point_2D(0,10)),result,precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0),new Point_2D(10,0),new Point_2D(10,10)),result,precision));
    }
}
