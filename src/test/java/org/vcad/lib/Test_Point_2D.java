/*
 * Test_Point_2D.java
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
public class Test_Point_2D extends TestBase {
    
    public Test_Point_2D() {
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
    public void test_Point_2D() {
        // test Point_2D object
        Point_2D p1 = new Point_2D(5,6);
        // test get
        Assert.assertTrue(p1.get_x() == 5);
        Assert.assertTrue(p1.get_y() == 6);
        // test r and theta
        Assert.assertTrue(within_round(p1.get_r(), 7.8102, precision));
        Assert.assertTrue(within_round(p1.get_theta(), 0.8761, precision));
        // test r and theta from another point
        Point_2D p2 = new Point_2D(10,0);
        Assert.assertTrue(within_round(p1.get_r(p2), 7.81025, precision));
        Assert.assertTrue(within_round(p1.get_theta(p2), 2.26553, precision));
        // test rotate
        p1 = new Point_2D(8,8);
        p1.rotate(Math.PI / 4);
        Assert.assertTrue(within_round(p1.get_x(), 0, precision));
        Assert.assertTrue(within_round(p1.get_y(), 11.3137, precision));
        p1.rotate(-Math.PI / 4);
        Assert.assertTrue(within_round(p1.get_x(), 8, precision));
        Assert.assertTrue(within_round(p1.get_y(), 8, precision));
        // test rotate about an alternate origin
        p2 = new Point_2D(8,0);
        p1.rotate(Math.PI / 2, p2);
        Assert.assertTrue(within_round(p1.get_x(), 0, precision));
        Assert.assertTrue(within_round(p1.get_y(), 0, precision));
        p1.rotate(-Math.PI / 2, p2);
        Assert.assertTrue(within_round(p1.get_x(), 8, precision));
        Assert.assertTrue(within_round(p1.get_y(), 8, precision));
        // test scale by doubles
        p1 = new Point_2D(8,8);
        p1.scale(2, 1.5);
        Assert.assertTrue(within_round(p1.get_x(), 16, precision));
        Assert.assertTrue(within_round(p1.get_y(), 12, precision));
        p1.scale(0.5, 0.5);
        Assert.assertTrue(within_round(p1.get_x(), 8, precision));
        Assert.assertTrue(within_round(p1.get_y(), 6, precision));
        // test translate by doubles
        p1.translate(4,2);
        Assert.assertTrue(within_round(p1.get_x(), 12, precision));
        Assert.assertTrue(within_round(p1.get_y(), 8, precision));
        p1.translate(-2,0);
        Assert.assertTrue(within_round(p1.get_x(), 10, precision));
        Assert.assertTrue(within_round(p1.get_y(), 8, precision));
        // test translate by vector
        Vector_2D v = new Vector_2D(0.5, 0.5);
        p1.translate(v);
        Assert.assertTrue(within_round(p1.get_x(), 10.5, precision));
        Assert.assertTrue(within_round(p1.get_y(), 8.5, precision));
        v = new Vector_2D(-0.5, 0.5);
        p1.translate(v);
        Assert.assertTrue(within_round(p1.get_x(), 10, precision));
        Assert.assertTrue(within_round(p1.get_y(), 9, precision));
        // test +
        p2 = Point_2D.translate(p1, v);
        Assert.assertTrue(within_round(p2.get_x(), 9.5, precision));
        Assert.assertTrue(within_round(p2.get_y(), 9.5, precision));
        v = new Vector_2D(2,2);
        p2 = Point_2D.translate(p1, v);
        Assert.assertTrue(within_round(p2.get_x(), 12, precision));
        Assert.assertTrue(within_round(p2.get_y(), 11, precision));
        // test *
        p2 = Point_2D.scale(p1, 0.5);
        Assert.assertTrue(within_round(p2.get_x(), 5, precision));
        Assert.assertTrue(within_round(p2.get_y(), 4.5, precision));
        p2 = Point_2D.scale(p1, 2);
        Assert.assertTrue(within_round(p2.get_x(), 20, precision));
        Assert.assertTrue(within_round(p2.get_y(), 18, precision));
        // test chaining ops
        p1 = new Point_2D(8,8);
        v = new Vector_2D(-2, -2);
        p1.translate(v).rotate(Math.PI / 4);
        Assert.assertTrue(within_round(p1.get_x(), 0, precision));
        Assert.assertTrue(within_round(p1.get_y(), 8.48528, precision));
        // test polar points
        p1 = Point_2D.polar_point(5, Math.PI / 6);
        Assert.assertTrue(within_round(p1.get_x(), 4.3301, precision));
        Assert.assertTrue(within_round(p1.get_y(), 2.5, precision));
        p1 = Point_2D.polar_point(5, -Math.PI / 6);
        Assert.assertTrue(within_round(p1.get_x(), 4.3301, precision));
        Assert.assertTrue(within_round(p1.get_y(), -2.5, precision));
        // test move
        p1 = new Point_2D(5,4);
        p1.move(new Point_2D(0,0), new Vector_2D(0,-1), true);
        Assert.assertTrue(within_round(p1.get_x(), 4, precision));
        Assert.assertTrue(within_round(p1.get_y(), -5, precision));
        p1 = new Point_2D(5,4);
        p1.move(new Point_2D(0,0), new Vector_2D(0,-1), false);
        Assert.assertTrue(within_round(p1.get_x(), -5, precision));
        Assert.assertTrue(within_round(p1.get_y(), -4, precision));
        p1 = new Point_2D(5,4);
        p1.move(new Point_2D(3,3), new Vector_2D(0,-1), true, new Point_2D(4,3));
        Assert.assertTrue(within_round(p1.get_x(), 4, precision));
        Assert.assertTrue(within_round(p1.get_y(), 2, precision));
//        System.out.println("p1 x: " + p1.get_x() + " y: " + p1.get_y() + "\n");
    }
}
