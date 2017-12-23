/*
 * Test_Shapes.java
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
 * @author jeff
 */
public class Test_Shapes extends TestBase {
    
    public Test_Shapes() {
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
    public void test_m_rectangle_1() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,20,40);
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(20,40), new Point_2D(0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(20,0), new Point_2D(20,40)), shape, precision));
    }

    @Test
    public void test_m_rectangle_2() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,20,40,true);
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10,-20), new Point_2D(10,20), new Point_2D(-10,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10,-20), new Point_2D(10,-20), new Point_2D(10,20)), shape, precision));
    }

    @Test
    public void test_m_rectangle_3() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,20,40,false,new Point_2D(5,-5));
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,-5), new Point_2D(25,35), new Point_2D(5,35)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,-5), new Point_2D(25,-5), new Point_2D(25,35)), shape, precision));
    }

    @Test
    public void test_m_rectangle_4() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,20,40,true,new Point_2D(5,-5));
//        cout << "\n";
//        int count = 0;
//        for (Mesh_2D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "shape[" << count++ << "] facet p1 x: " << (*it)->get_point1().get_x() << " y: " <<
//                    (*it)->get_point1().get_y() << " p2 x: " << (*it)->get_point2().get_x() << 
//                    " y: " << (*it)->get_point2().get_y() << " p3 x: " << 
//                    (*it)->get_point3().get_x() << " y: " << (*it)->get_point3().get_y() << "\n";
//        }
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-5,-25), new Point_2D(15,15), new Point_2D(-5,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-5,-25), new Point_2D(15,-25), new Point_2D(15,15)), shape, precision));
    }

    @Test
    public void test_m_circle_1() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,3);
        Assert.assertTrue(shape.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(17.3205,10), new Point_2D(10,17.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(10,17.3205), new Point_2D(0,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(0,20), new Point_2D(-10,17.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(-10,17.3205), new Point_2D(-17.3205,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(-17.3205,10), new Point_2D(-20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(-20,0), new Point_2D(-17.3205,-10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(-17.3205,-10), new Point_2D(-10,-17.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(-10,-17.3205), new Point_2D(0,-20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(0,-20), new Point_2D(10,-17.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(20,0), new Point_2D(10,-17.3205), new Point_2D(17.3205,-10)), shape, precision));
    }

    @Test
    public void test_m_circle_2() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.circle(shape,20,3,new Point_2D(5,-5));
//        cout << "\n";
//        int count = 0;
//        for (Mesh_2D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "shape[" << count++ << "] facet p1 x: " << (*it)->get_point1().get_x() << " y: " <<
//                    (*it)->get_point1().get_y() << " p2 x: " << (*it)->get_point2().get_x() << 
//                    " y: " << (*it)->get_point2().get_y() << " p3 x: " << 
//                    (*it)->get_point3().get_x() << " y: " << (*it)->get_point3().get_y() << "\n";
//        }
        Assert.assertTrue(shape.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(22.3205,5), new Point_2D(15,12.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(15,12.3205), new Point_2D(5,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(5,15), new Point_2D(-5,12.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(-5,12.3205), new Point_2D(-12.3205,5)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(-12.3205,5), new Point_2D(-15,-5)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(-15,-5), new Point_2D(-12.3205,-15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(-12.3205,-15), new Point_2D(-5,-22.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(-5,-22.3205), new Point_2D(5,-25)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(5,-25), new Point_2D(15,-22.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(25,-5), new Point_2D(15,-22.3205), new Point_2D(22.3205,-15)), shape, precision));
    }

    @Test
    public void test_m_ellipse_1() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.ellipse(shape,20,20,3);
        Assert.assertTrue(shape.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(-6.66667,18.8562), new Point_2D(-13.3333,14.9071)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(-13.3333,-14.9071), new Point_2D(-6.66667,-18.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(0,20), new Point_2D(-6.66667,18.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(-6.66667,-18.8562), new Point_2D(0,-20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(6.66667,18.8562), new Point_2D(0,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(0,-20), new Point_2D(6.66667,-18.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(13.3333,14.9071), new Point_2D(6.66667,18.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(6.66667,-18.8562), new Point_2D(13.3333,-14.9071)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(20,0), new Point_2D(13.3333,14.9071)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(13.3333,-14.9071), new Point_2D(20,0)), shape, precision));
    }

    @Test
    public void test_m_ellipse_2() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.ellipse(shape,20,20,3,new Point_2D(5,-5));
        Assert.assertTrue(shape.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(-1.66667,13.8562), new Point_2D(-8.3333,9.9071)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(-8.3333,-19.9071), new Point_2D(-1.66667,-23.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(5,15), new Point_2D(-1.66667,13.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(-1.66667,-23.8562), new Point_2D(5,-25)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(11.66667,13.8562), new Point_2D(5,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(5,-25), new Point_2D(11.66667,-23.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(18.3333,9.9071), new Point_2D(11.66667,13.8562)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(11.66667,-23.8562), new Point_2D(18.3333,-19.9071)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(25,-5), new Point_2D(18.3333,9.9071)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(18.3333,-19.9071), new Point_2D(25,-5)), shape, precision));
    }

    @Test
    public void test_m_ellipse_3() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.ellipse(shape,20,5,3);
        Assert.assertTrue(shape.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(-6.66667,4.71405), new Point_2D(-13.3333,3.72678)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(-13.3333,-3.72678), new Point_2D(-6.66667,-4.71405)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(0,5), new Point_2D(-6.66667,4.71405)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(-6.66667,-4.71405), new Point_2D(0,-5)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(6.66667,4.71405), new Point_2D(0,5)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(0,-5), new Point_2D(6.66667,-4.71405)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(13.3333,3.72678), new Point_2D(6.66667,4.71405)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(6.66667,-4.71405), new Point_2D(13.3333,-3.72678)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(20,0), new Point_2D(13.3333,3.72678)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,0), new Point_2D(13.3333,-3.72678), new Point_2D(20,0)), shape, precision));
    }

    @Test
    public void test_m_ellipse_4() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.ellipse(shape,20,5,3,new Point_2D(5,-5));
//        cout << "\n";
//        int count = 0;
//        for (Mesh_2D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "shape[" << count++ << "] facet p1 x: " << (*it)->get_point1().get_x() << " y: " <<
//                    (*it)->get_point1().get_y() << " p2 x: " << (*it)->get_point2().get_x() << 
//                    " y: " << (*it)->get_point2().get_y() << " p3 x: " << 
//                    (*it)->get_point3().get_x() << " y: " << (*it)->get_point3().get_y() << "\n";
//        }
        Assert.assertTrue(shape.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(-1.66667,-0.285955), new Point_2D(-8.3333,-1.27322)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(-8.3333,-8.72678), new Point_2D(-1.66667,-9.71405)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(5,0), new Point_2D(-1.66667,-0.285955)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(-1.66667,-9.71405), new Point_2D(5,-10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(11.66667,-0.285955), new Point_2D(5,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(5,-10), new Point_2D(11.66667,-9.71405)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(18.3333,-1.27322), new Point_2D(11.66667,-0.285955)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(11.66667,-9.71405), new Point_2D(18.3333,-8.72678)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(25,-5), new Point_2D(18.3333,-1.27322)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-15,-5), new Point_2D(18.3333,-8.72678), new Point_2D(25,-5)), shape, precision));
    }
    
    @Test
    public void test_m_cuboid_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,30);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(10,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,20,0), new Point_3D(10,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30), new Point_3D(10,20,30), new Point_3D(0,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30), new Point_3D(10,0,30), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,30), new Point_3D(0,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,30), new Point_3D(0,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,30), new Point_3D(10,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,0), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,30), new Point_3D(0,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(10,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(0,20,30), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(10,20,30), new Point_3D(10,20,0)), shape, precision));
    }

    @Test
    public void test_m_cuboid_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,30,true);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,-15), new Point_3D(-5,10,-15), new Point_3D(5,10,-15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,-15), new Point_3D(5,10,-15), new Point_3D(5,-10,-15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,15), new Point_3D(5,10,15), new Point_3D(-5,10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,15), new Point_3D(5,-10,15), new Point_3D(5,10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,-15), new Point_3D(-5,-10,15), new Point_3D(-5,10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,-15), new Point_3D(-5,10,15), new Point_3D(-5,10,-15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-10,-15), new Point_3D(5,10,15), new Point_3D(5,-10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-10,-15), new Point_3D(5,10,-15), new Point_3D(5,10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,-15), new Point_3D(5,-10,15), new Point_3D(-5,-10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-10,-15), new Point_3D(5,-10,-15), new Point_3D(5,-10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,10,-15), new Point_3D(-5,10,15), new Point_3D(5,10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,10,-15), new Point_3D(5,10,15), new Point_3D(5,10,-15)), shape, precision));
    }

    @Test
    public void test_m_cuboid_3() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,30,false,new Point_3D(5,10,15));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,15), new Point_3D(5,30,15), new Point_3D(15,30,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,15), new Point_3D(15,30,15), new Point_3D(15,10,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,45), new Point_3D(15,30,45), new Point_3D(5,30,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,45), new Point_3D(15,10,45), new Point_3D(15,30,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,15), new Point_3D(5,10,45), new Point_3D(5,30,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,15), new Point_3D(5,30,45), new Point_3D(5,30,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,10,15), new Point_3D(15,30,45), new Point_3D(15,10,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,10,15), new Point_3D(15,30,15), new Point_3D(15,30,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,15), new Point_3D(15,10,45), new Point_3D(5,10,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,15), new Point_3D(15,10,15), new Point_3D(15,10,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,30,15), new Point_3D(5,30,45), new Point_3D(15,30,45)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,30,15), new Point_3D(15,30,45), new Point_3D(15,30,15)), shape, precision));
    }

    @Test
    public void test_m_cuboid_4() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,30,true,new Point_3D(5,10,15));
//        cout << "\n";
//        int count = 0;
//        for (Mesh_3D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "shape[" << count++ << "] facet p1 x: " << (*it)->get_point1().get_x() << " y: " <<
//                    (*it)->get_point1().get_y() << " z: " << (*it)->get_point1().get_z() << " p2 x: " << 
//                    (*it)->get_point2().get_x() << " y: " << (*it)->get_point2().get_y() << " z: " << 
//                    (*it)->get_point2().get_z() << " p3 x: " << (*it)->get_point3().get_x() << " y: " << 
//                    (*it)->get_point3().get_y() << " z: " << (*it)->get_point3().get_z() << "\n";
//        }
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(10,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,20,0), new Point_3D(10,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30), new Point_3D(10,20,30), new Point_3D(0,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30), new Point_3D(10,0,30), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,30), new Point_3D(0,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,30), new Point_3D(0,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,30), new Point_3D(10,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,0), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,30), new Point_3D(0,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(10,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(0,20,30), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(10,20,30), new Point_3D(10,20,0)), shape, precision));
    }

    @Test
    public void test_m_cylinder_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,10,20,30,3);
        Assert.assertTrue(shape.size() == 44);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(17.3205,10,30),new Point_3D(20,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(8.66025,5,0),new Point_3D(17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(5,8.66025,0),new Point_3D(8.66025,5,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(17.3205,10,30),new Point_3D(10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,0),new Point_3D(10,17.3205,30),new Point_3D(17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,0),new Point_3D(5,8.66025,0),new Point_3D(10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(6.12323e-16,10,0),new Point_3D(5,8.66025,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(10,17.3205,30),new Point_3D(1.22465e-15,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,8.66025,0),new Point_3D(1.22465e-15,20,30),new Point_3D(10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,8.66025,0),new Point_3D(6.12323e-16,10,0),new Point_3D(1.22465e-15,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(-5,8.66025,0),new Point_3D(6.12323e-16,10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(1.22465e-15,20,30),new Point_3D(-10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.12323e-16,10,0),new Point_3D(-10,17.3205,30),new Point_3D(1.22465e-15,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.12323e-16,10,0),new Point_3D(-5,8.66025,0),new Point_3D(-10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(-8.66025,5,0),new Point_3D(-5,8.66025,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-10,17.3205,30),new Point_3D(-17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,8.66025,0),new Point_3D(-17.3205,10,30),new Point_3D(-10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,8.66025,0),new Point_3D(-8.66025,5,0),new Point_3D(-17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(-10,5.66554e-15,0),new Point_3D(-8.66025,5,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-17.3205,10,30),new Point_3D(-20,1.13311e-14,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,5,0),new Point_3D(-20,1.13311e-14,30),new Point_3D(-17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,5,0),new Point_3D(-10,5.66554e-15,0),new Point_3D(-20,1.13311e-14,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(-8.66025,-5,0),new Point_3D(-10,5.66554e-15,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-20,1.13311e-14,30),new Point_3D(-17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,5.66554e-15,0),new Point_3D(-17.3205,-10,30),new Point_3D(-20,1.13311e-14,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,5.66554e-15,0),new Point_3D(-8.66025,-5,0),new Point_3D(-17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(-5,-8.66025,0),new Point_3D(-8.66025,-5,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-17.3205,-10,30),new Point_3D(-10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,-5,0),new Point_3D(-10,-17.3205,30),new Point_3D(-17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,-5,0),new Point_3D(-5,-8.66025,0),new Point_3D(-10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(-1.83697e-15,-10,0),new Point_3D(-5,-8.66025,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-10,-17.3205,30),new Point_3D(-3.67394e-15,-20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-8.66025,0),new Point_3D(-3.67394e-15,-20,30),new Point_3D(-10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-8.66025,0),new Point_3D(-1.83697e-15,-10,0),new Point_3D(-3.67394e-15,-20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(5,-8.66025,0),new Point_3D(-1.83697e-15,-10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-3.67394e-15,-20,30),new Point_3D(10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.83697e-15,-10,0),new Point_3D(10,-17.3205,30),new Point_3D(-3.67394e-15,-20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.83697e-15,-10,0),new Point_3D(5,-8.66025,0),new Point_3D(10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(8.66025,-5,0),new Point_3D(5,-8.66025,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(10,-17.3205,30),new Point_3D(17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-8.66025,0),new Point_3D(17.3205,-10,30),new Point_3D(10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-8.66025,0),new Point_3D(8.66025,-5,0),new Point_3D(17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(20,0,30),new Point_3D(17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(17.3205,-10,30),new Point_3D(8.66025,-5,0)), shape, 0.0001));
    }

    @Test
    public void test_m_cylinder_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,10,20,30,3,false,new Point_3D(10,20,30));
        Assert.assertTrue(shape.size() == 44);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(27.3205,30,60),new Point_3D(30,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(18.6603,25,30),new Point_3D(27.3205,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(15,28.6603,30),new Point_3D(18.6603,25,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(27.3205,30,60),new Point_3D(20,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,25,30),new Point_3D(20,37.3205,60),new Point_3D(27.3205,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,25,30),new Point_3D(15,28.6603,30),new Point_3D(20,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(10,30,30),new Point_3D(15,28.6603,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(20,37.3205,60),new Point_3D(10,40,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,28.6603,30),new Point_3D(10,40,60),new Point_3D(20,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,28.6603,30),new Point_3D(10,30,30),new Point_3D(10,40,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(5,28.6603,30),new Point_3D(10,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(10,40,60),new Point_3D(3.55271e-15,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,30,30),new Point_3D(3.55271e-15,37.3205,60),new Point_3D(10,40,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,30,30),new Point_3D(5,28.6603,30),new Point_3D(3.55271e-15,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(1.33975,25,30),new Point_3D(5,28.6603,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(3.55271e-15,37.3205,60),new Point_3D(-7.32051,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,28.6603,30),new Point_3D(-7.32051,30,60),new Point_3D(3.55271e-15,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,28.6603,30),new Point_3D(1.33975,25,30),new Point_3D(-7.32051,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(0,20,30),new Point_3D(1.33975,25,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-7.32051,30,60),new Point_3D(-10,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,25,30),new Point_3D(-10,20,60),new Point_3D(-7.32051,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,25,30),new Point_3D(0,20,30),new Point_3D(-10,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(1.33975,15,30),new Point_3D(0,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-10,20,60),new Point_3D(-7.32051,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(-7.32051,10,60),new Point_3D(-10,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(1.33975,15,30),new Point_3D(-7.32051,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(5,11.3397,30),new Point_3D(1.33975,15,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-7.32051,10,60),new Point_3D(-8.88178e-15,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,15,30),new Point_3D(-8.88178e-15,2.67949,60),new Point_3D(-7.32051,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,15,30),new Point_3D(5,11.3397,30),new Point_3D(-8.88178e-15,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(10,10,30),new Point_3D(5,11.3397,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-8.88178e-15,2.67949,60),new Point_3D(10,0,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,11.3397,30),new Point_3D(10,0,60),new Point_3D(-8.88178e-15,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,11.3397,30),new Point_3D(10,10,30),new Point_3D(10,0,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(15,11.3397,30),new Point_3D(10,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(10,0,60),new Point_3D(20,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,10,30),new Point_3D(20,2.67949,60),new Point_3D(10,0,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,10,30),new Point_3D(15,11.3397,30),new Point_3D(20,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(18.6603,15,30),new Point_3D(15,11.3397,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(20,2.67949,60),new Point_3D(27.3205,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,11.3397,30),new Point_3D(27.3205,10,60),new Point_3D(20,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,11.3397,30),new Point_3D(18.6603,15,30),new Point_3D(27.3205,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(30,20,60),new Point_3D(27.3205,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(27.3205,10,60),new Point_3D(18.6603,15,30)), shape, 0.0001));
    }

    @Test
    public void test_m_cylinder_3() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,10,20,30,3,true);
        Assert.assertTrue(shape.size() == 44);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(17.3205,10,15),new Point_3D(20,0,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(8.66025,5,-15),new Point_3D(17.3205,10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(5,8.66025,-15),new Point_3D(8.66025,5,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(17.3205,10,15),new Point_3D(10,17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,-15),new Point_3D(10,17.3205,15),new Point_3D(17.3205,10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,-15),new Point_3D(5,8.66025,-15),new Point_3D(10,17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(6.12323e-16,10,-15),new Point_3D(5,8.66025,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(10,17.3205,15),new Point_3D(1.22465e-15,20,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,8.66025,-15),new Point_3D(1.22465e-15,20,15),new Point_3D(10,17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,8.66025,-15),new Point_3D(6.12323e-16,10,-15),new Point_3D(1.22465e-15,20,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(-5,8.66025,-15),new Point_3D(6.12323e-16,10,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(1.22465e-15,20,15),new Point_3D(-10,17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.12323e-16,10,-15),new Point_3D(-10,17.3205,15),new Point_3D(1.22465e-15,20,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.12323e-16,10,-15),new Point_3D(-5,8.66025,-15),new Point_3D(-10,17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(-8.66025,5,-15),new Point_3D(-5,8.66025,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(-10,17.3205,15),new Point_3D(-17.3205,10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,8.66025,-15),new Point_3D(-17.3205,10,15),new Point_3D(-10,17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,8.66025,-15),new Point_3D(-8.66025,5,-15),new Point_3D(-17.3205,10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(-10,5.66554e-15,-15),new Point_3D(-8.66025,5,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(-17.3205,10,15),new Point_3D(-20,1.13311e-14,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,5,-15),new Point_3D(-20,1.13311e-14,15),new Point_3D(-17.3205,10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,5,-15),new Point_3D(-10,5.66554e-15,-15),new Point_3D(-20,1.13311e-14,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(-8.66025,-5,-15),new Point_3D(-10,5.66554e-15,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(-20,1.13311e-14,15),new Point_3D(-17.3205,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,5.66554e-15,-15),new Point_3D(-17.3205,-10,15),new Point_3D(-20,1.13311e-14,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,5.66554e-15,-15),new Point_3D(-8.66025,-5,-15),new Point_3D(-17.3205,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(-5,-8.66025,-15),new Point_3D(-8.66025,-5,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(-17.3205,-10,15),new Point_3D(-10,-17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,-5,-15),new Point_3D(-10,-17.3205,15),new Point_3D(-17.3205,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,-5,-15),new Point_3D(-5,-8.66025,-15),new Point_3D(-10,-17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(-1.83697e-15,-10,-15),new Point_3D(-5,-8.66025,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(-10,-17.3205,15),new Point_3D(-3.67394e-15,-20,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-8.66025,-15),new Point_3D(-3.67394e-15,-20,15),new Point_3D(-10,-17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-8.66025,-15),new Point_3D(-1.83697e-15,-10,-15),new Point_3D(-3.67394e-15,-20,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(5,-8.66025,-15),new Point_3D(-1.83697e-15,-10,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(-3.67394e-15,-20,15),new Point_3D(10,-17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.83697e-15,-10,-15),new Point_3D(10,-17.3205,15),new Point_3D(-3.67394e-15,-20,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.83697e-15,-10,-15),new Point_3D(5,-8.66025,-15),new Point_3D(10,-17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(8.66025,-5,-15),new Point_3D(5,-8.66025,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,15),new Point_3D(10,-17.3205,15),new Point_3D(17.3205,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-8.66025,-15),new Point_3D(17.3205,-10,15),new Point_3D(10,-17.3205,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-8.66025,-15),new Point_3D(8.66025,-5,-15),new Point_3D(17.3205,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(20,0,15),new Point_3D(17.3205,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,-15),new Point_3D(17.3205,-10,15),new Point_3D(8.66025,-5,-15)), shape, 0.0001));
    }

    @Test
    public void test_m_cylinder_4() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,30,3);
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(17.3205,10,30),new Point_3D(20,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(17.3205,10,30),new Point_3D(10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,17.3205,30),new Point_3D(17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(10,17.3205,30),new Point_3D(1.22465e-15,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(1.22465e-15,20,30),new Point_3D(10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(1.22465e-15,20,30),new Point_3D(-10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-10,17.3205,30),new Point_3D(1.22465e-15,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-10,17.3205,30),new Point_3D(-17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-17.3205,10,30),new Point_3D(-10,17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-17.3205,10,30),new Point_3D(-20,1.13311e-14,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-20,1.13311e-14,30),new Point_3D(-17.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-20,1.13311e-14,30),new Point_3D(-17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-17.3205,-10,30),new Point_3D(-20,1.13311e-14,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-17.3205,-10,30),new Point_3D(-10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-10,-17.3205,30),new Point_3D(-17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-10,-17.3205,30),new Point_3D(-3.67394e-15,-20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-3.67394e-15,-20,30),new Point_3D(-10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(-3.67394e-15,-20,30),new Point_3D(10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,-17.3205,30),new Point_3D(-3.67394e-15,-20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,30),new Point_3D(10,-17.3205,30),new Point_3D(17.3205,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(17.3205,-10,30),new Point_3D(10,-17.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(20,0,30),new Point_3D(17.3205,-10,30)), shape, 0.0001));
    }

    @Test
    public void test_m_cylinder_5() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,30,3,false,new Point_3D(10,20,30));
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(27.3205,30,60),new Point_3D(30,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(27.3205,30,60),new Point_3D(20,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(20,37.3205,60),new Point_3D(27.3205,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(20,37.3205,60),new Point_3D(10,40,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(10,40,60),new Point_3D(20,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(10,40,60),new Point_3D(3.55271e-15,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(3.55271e-15,37.3205,60),new Point_3D(10,40,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(3.55271e-15,37.3205,60),new Point_3D(-7.32051,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(-7.32051,30,60),new Point_3D(3.55271e-15,37.3205,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-7.32051,30,60),new Point_3D(-10,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(-10,20,60),new Point_3D(-7.32051,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-10,20,60),new Point_3D(-7.32051,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(-7.32051,10,60),new Point_3D(-10,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-7.32051,10,60),new Point_3D(-8.88178e-15,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(-8.88178e-15,2.67949,60),new Point_3D(-7.32051,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(-8.88178e-15,2.67949,60),new Point_3D(10,0,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(10,0,60),new Point_3D(-8.88178e-15,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(10,0,60),new Point_3D(20,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(20,2.67949,60),new Point_3D(10,0,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,60),new Point_3D(20,2.67949,60),new Point_3D(27.3205,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(27.3205,10,60),new Point_3D(20,2.67949,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(30,20,60),new Point_3D(27.3205,10,60)), shape, 0.0001));
    }

    @Test
    public void test_m_cylinder_6() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,20,0,30,3);
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(20,0,0),new Point_3D(17.3205,10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(10,17.3205,0),new Point_3D(17.3205,10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(17.3205,10,0),new Point_3D(10,17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(1.22465e-15,20,0),new Point_3D(10,17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(10,17.3205,0),new Point_3D(1.22465e-15,20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(-10,17.3205,0),new Point_3D(1.22465e-15,20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(1.22465e-15,20,0),new Point_3D(-10,17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(-17.3205,10,0),new Point_3D(-10,17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-10,17.3205,0),new Point_3D(-17.3205,10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(-20,1.13311e-14,0),new Point_3D(-17.3205,10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-17.3205,10,0),new Point_3D(-20,1.13311e-14,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(-17.3205,-10,0),new Point_3D(-20,1.13311e-14,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-20,1.13311e-14,0),new Point_3D(-17.3205,-10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(-10,-17.3205,0),new Point_3D(-17.3205,-10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-17.3205,-10,0),new Point_3D(-10,-17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(-3.67394e-15,-20,0),new Point_3D(-10,-17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-10,-17.3205,0),new Point_3D(-3.67394e-15,-20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(10,-17.3205,0),new Point_3D(-3.67394e-15,-20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-3.67394e-15,-20,0),new Point_3D(10,-17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(17.3205,-10,0),new Point_3D(10,-17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(10,-17.3205,0),new Point_3D(17.3205,-10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(17.3205,-10,0),new Point_3D(20,0,0)), shape, 0.0001));
    }

    @Test
    public void test_m_cylinder_7() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,20,0,30,3,false,new Point_3D(10,20,30));
//        cout << "\n";
//        cout << "polyhedron(points=[";
//        bool write_comma(false);
//        for (Mesh_3D::const_point_iterator it = shape.point_begin(); it != shape.point_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        write_comma = false;
//        for (Mesh_3D::const_facet_iterator it = shape.facet_begin(); it != shape.facet_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << it->get_p1_index() << ',' << it->get_p2_index() << ',' << it->get_p3_index() << ']';
//        }
//        cout << "], convexity = 4);\n";
//        Mesh_3D::const_iterator iter(shape.begin());
//        while (iter != shape.end())
//        {
//            cout << "    Assert.assertTrue(mesh_contains(Facet_3D(shared_ptr<Point_3D>(new Point_3D(" << 
//                    iter->get_point1()->get_x() << "," << iter->get_point1()->get_y() << "," <<
//                    iter->get_point1()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point2()->get_x() << "," << iter->get_point2()->get_y() << "," <<
//                    iter->get_point2()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point3()->get_x() << "," << iter->get_point3()->get_y() << "," <<
//                    iter->get_point3()->get_z() << "))), shape, 0.0001));\n";
//            ++iter;
//        }
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(30,20,30),new Point_3D(27.3205,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(20,37.3205,30),new Point_3D(27.3205,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(27.3205,30,30),new Point_3D(20,37.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(10,40,30),new Point_3D(20,37.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(20,37.3205,30),new Point_3D(10,40,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(3.55271e-15,37.3205,30),new Point_3D(10,40,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(10,40,30),new Point_3D(3.55271e-15,37.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(-7.32051,30,30),new Point_3D(3.55271e-15,37.3205,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(3.55271e-15,37.3205,30),new Point_3D(-7.32051,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(-10,20,30),new Point_3D(-7.32051,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(-7.32051,30,30),new Point_3D(-10,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(-7.32051,10,30),new Point_3D(-10,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(-10,20,30),new Point_3D(-7.32051,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(-8.88178e-15,2.67949,30),new Point_3D(-7.32051,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(-7.32051,10,30),new Point_3D(-8.88178e-15,2.67949,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(10,0,30),new Point_3D(-8.88178e-15,2.67949,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(-8.88178e-15,2.67949,30),new Point_3D(10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(20,2.67949,30),new Point_3D(10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(10,0,30),new Point_3D(20,2.67949,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(27.3205,10,30),new Point_3D(20,2.67949,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(20,2.67949,30),new Point_3D(27.3205,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(27.3205,10,30),new Point_3D(30,20,30)), shape, 0.0001));
    }

    @Test
    public void test_m_e_cylinder_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.e_cylinder(shape,20,20,10,10,30,3);
        Assert.assertTrue(shape.size() == 44);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(-10,0,30),new Point_3D(-6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(-6.66667,7.45356,30),new Point_3D(-13.3333,14.9071,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(-6.66667,-7.45356,30),new Point_3D(-10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(-13.3333,-14.9071,0),new Point_3D(-6.66667,-7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(-13.3333,14.9071,0),new Point_3D(-6.66667,18.8562,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(-6.66667,-18.8562,0),new Point_3D(-13.3333,-14.9071,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(-3.33333,9.42809,30),new Point_3D(-6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(-6.66667,-7.45356,30),new Point_3D(-3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,14.9071,0),new Point_3D(-6.66667,7.45356,30),new Point_3D(-3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,14.9071,0),new Point_3D(-3.33333,9.42809,30),new Point_3D(-6.66667,18.8562,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,-14.9071,0),new Point_3D(-3.33333,-9.42809,30),new Point_3D(-6.66667,-7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,-14.9071,0),new Point_3D(-6.66667,-18.8562,0),new Point_3D(-3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(-6.66667,18.8562,0),new Point_3D(1.77636e-15,20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(1.77636e-15,-20,0),new Point_3D(-6.66667,-18.8562,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(8.88178e-16,10,30),new Point_3D(-3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(-3.33333,-9.42809,30),new Point_3D(8.88178e-16,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,18.8562,0),new Point_3D(-3.33333,9.42809,30),new Point_3D(8.88178e-16,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,18.8562,0),new Point_3D(8.88178e-16,10,30),new Point_3D(1.77636e-15,20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,-18.8562,0),new Point_3D(8.88178e-16,-10,30),new Point_3D(-3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,-18.8562,0),new Point_3D(1.77636e-15,-20,0),new Point_3D(8.88178e-16,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(1.77636e-15,20,0),new Point_3D(6.66667,18.8562,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(6.66667,-18.8562,0),new Point_3D(1.77636e-15,-20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(3.33333,9.42809,30),new Point_3D(8.88178e-16,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(8.88178e-16,-10,30),new Point_3D(3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,20,0),new Point_3D(8.88178e-16,10,30),new Point_3D(3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,20,0),new Point_3D(3.33333,9.42809,30),new Point_3D(6.66667,18.8562,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,-20,0),new Point_3D(3.33333,-9.42809,30),new Point_3D(8.88178e-16,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,-20,0),new Point_3D(6.66667,-18.8562,0),new Point_3D(3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(6.66667,18.8562,0),new Point_3D(13.3333,14.9071,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(13.3333,-14.9071,0),new Point_3D(6.66667,-18.8562,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(6.66667,7.45356,30),new Point_3D(3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(3.33333,-9.42809,30),new Point_3D(6.66667,-7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,18.8562,0),new Point_3D(3.33333,9.42809,30),new Point_3D(6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,18.8562,0),new Point_3D(6.66667,7.45356,30),new Point_3D(13.3333,14.9071,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,-18.8562,0),new Point_3D(6.66667,-7.45356,30),new Point_3D(3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,-18.8562,0),new Point_3D(13.3333,-14.9071,0),new Point_3D(6.66667,-7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(13.3333,14.9071,0),new Point_3D(20,0,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0),new Point_3D(20,0,0),new Point_3D(13.3333,-14.9071,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(10,0,30),new Point_3D(6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(6.66667,-7.45356,30),new Point_3D(10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(6.66667,7.45356,30),new Point_3D(10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(13.3333,14.9071,0),new Point_3D(6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(10,0,30),new Point_3D(6.66667,-7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0),new Point_3D(6.66667,-7.45356,30),new Point_3D(13.3333,-14.9071,0)), shape, 0.0001));
    }

    @Test
    public void test_m_e_cylinder_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.e_cylinder(shape,20,20,10,10,30,3,false,new Point_3D(10,20,30));
        Assert.assertTrue(shape.size() == 44);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(0,20,60),new Point_3D(3.33333,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(3.33333,27.4536,60),new Point_3D(-3.33333,34.9071,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(3.33333,12.5464,60),new Point_3D(0,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(-3.33333,5.09288,30),new Point_3D(3.33333,12.5464,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(-3.33333,34.9071,30),new Point_3D(3.33333,38.8562,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(3.33333,1.14382,30),new Point_3D(-3.33333,5.09288,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(6.66667,29.4281,60),new Point_3D(3.33333,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(3.33333,12.5464,60),new Point_3D(6.66667,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,34.9071,30),new Point_3D(3.33333,27.4536,60),new Point_3D(6.66667,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,34.9071,30),new Point_3D(6.66667,29.4281,60),new Point_3D(3.33333,38.8562,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,5.09288,30),new Point_3D(6.66667,10.5719,60),new Point_3D(3.33333,12.5464,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,5.09288,30),new Point_3D(3.33333,1.14382,30),new Point_3D(6.66667,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(3.33333,38.8562,30),new Point_3D(10,40,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(10,0,30),new Point_3D(3.33333,1.14382,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(10,30,60),new Point_3D(6.66667,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(6.66667,10.5719,60),new Point_3D(10,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,38.8562,30),new Point_3D(6.66667,29.4281,60),new Point_3D(10,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,38.8562,30),new Point_3D(10,30,60),new Point_3D(10,40,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,1.14382,30),new Point_3D(10,10,60),new Point_3D(6.66667,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,1.14382,30),new Point_3D(10,0,30),new Point_3D(10,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(10,40,30),new Point_3D(16.6667,38.8562,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(16.6667,1.14382,30),new Point_3D(10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(13.3333,29.4281,60),new Point_3D(10,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(10,10,60),new Point_3D(13.3333,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,40,30),new Point_3D(10,30,60),new Point_3D(13.3333,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,40,30),new Point_3D(13.3333,29.4281,60),new Point_3D(16.6667,38.8562,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,30),new Point_3D(13.3333,10.5719,60),new Point_3D(10,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,30),new Point_3D(16.6667,1.14382,30),new Point_3D(13.3333,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(16.6667,38.8562,30),new Point_3D(23.3333,34.9071,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(23.3333,5.09288,30),new Point_3D(16.6667,1.14382,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(16.6667,27.4536,60),new Point_3D(13.3333,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(13.3333,10.5719,60),new Point_3D(16.6667,12.5464,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6667,38.8562,30),new Point_3D(13.3333,29.4281,60),new Point_3D(16.6667,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6667,38.8562,30),new Point_3D(16.6667,27.4536,60),new Point_3D(23.3333,34.9071,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6667,1.14382,30),new Point_3D(16.6667,12.5464,60),new Point_3D(13.3333,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6667,1.14382,30),new Point_3D(23.3333,5.09288,30),new Point_3D(16.6667,12.5464,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(23.3333,34.9071,30),new Point_3D(30,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,20,30),new Point_3D(30,20,30),new Point_3D(23.3333,5.09288,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(20,20,60),new Point_3D(16.6667,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(16.6667,12.5464,60),new Point_3D(20,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(16.6667,27.4536,60),new Point_3D(20,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(23.3333,34.9071,30),new Point_3D(16.6667,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(20,20,60),new Point_3D(16.6667,12.5464,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(30,20,30),new Point_3D(16.6667,12.5464,60),new Point_3D(23.3333,5.09288,30)), shape, 0.0001));
    }

    @Test
    public void test_m_e_cylinder_3() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.e_cylinder(shape,20,20,10,10,30,3,true);
        Assert.assertTrue(shape.size() == 44);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(-10,0,15),new Point_3D(-6.66667,7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(-6.66667,7.45356,15),new Point_3D(-13.3333,14.9071,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(-6.66667,-7.45356,15),new Point_3D(-10,0,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(-13.3333,-14.9071,-15),new Point_3D(-6.66667,-7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(-13.3333,14.9071,-15),new Point_3D(-6.66667,18.8562,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(-6.66667,-18.8562,-15),new Point_3D(-13.3333,-14.9071,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(-3.33333,9.42809,15),new Point_3D(-6.66667,7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(-6.66667,-7.45356,15),new Point_3D(-3.33333,-9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,14.9071,-15),new Point_3D(-6.66667,7.45356,15),new Point_3D(-3.33333,9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,14.9071,-15),new Point_3D(-3.33333,9.42809,15),new Point_3D(-6.66667,18.8562,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,-14.9071,-15),new Point_3D(-3.33333,-9.42809,15),new Point_3D(-6.66667,-7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-13.3333,-14.9071,-15),new Point_3D(-6.66667,-18.8562,-15),new Point_3D(-3.33333,-9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(-6.66667,18.8562,-15),new Point_3D(1.77636e-15,20,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(1.77636e-15,-20,-15),new Point_3D(-6.66667,-18.8562,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(8.88178e-16,10,15),new Point_3D(-3.33333,9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(-3.33333,-9.42809,15),new Point_3D(8.88178e-16,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,18.8562,-15),new Point_3D(-3.33333,9.42809,15),new Point_3D(8.88178e-16,10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,18.8562,-15),new Point_3D(8.88178e-16,10,15),new Point_3D(1.77636e-15,20,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,-18.8562,-15),new Point_3D(8.88178e-16,-10,15),new Point_3D(-3.33333,-9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,-18.8562,-15),new Point_3D(1.77636e-15,-20,-15),new Point_3D(8.88178e-16,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(1.77636e-15,20,-15),new Point_3D(6.66667,18.8562,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(6.66667,-18.8562,-15),new Point_3D(1.77636e-15,-20,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(3.33333,9.42809,15),new Point_3D(8.88178e-16,10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(8.88178e-16,-10,15),new Point_3D(3.33333,-9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,20,-15),new Point_3D(8.88178e-16,10,15),new Point_3D(3.33333,9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,20,-15),new Point_3D(3.33333,9.42809,15),new Point_3D(6.66667,18.8562,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,-20,-15),new Point_3D(3.33333,-9.42809,15),new Point_3D(8.88178e-16,-10,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.77636e-15,-20,-15),new Point_3D(6.66667,-18.8562,-15),new Point_3D(3.33333,-9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(6.66667,18.8562,-15),new Point_3D(13.3333,14.9071,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(13.3333,-14.9071,-15),new Point_3D(6.66667,-18.8562,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(6.66667,7.45356,15),new Point_3D(3.33333,9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(3.33333,-9.42809,15),new Point_3D(6.66667,-7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,18.8562,-15),new Point_3D(3.33333,9.42809,15),new Point_3D(6.66667,7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,18.8562,-15),new Point_3D(6.66667,7.45356,15),new Point_3D(13.3333,14.9071,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,-18.8562,-15),new Point_3D(6.66667,-7.45356,15),new Point_3D(3.33333,-9.42809,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,-18.8562,-15),new Point_3D(13.3333,-14.9071,-15),new Point_3D(6.66667,-7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(13.3333,14.9071,-15),new Point_3D(20,0,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,-15),new Point_3D(20,0,-15),new Point_3D(13.3333,-14.9071,-15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(10,0,15),new Point_3D(6.66667,7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,15),new Point_3D(6.66667,-7.45356,15),new Point_3D(10,0,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,-15),new Point_3D(6.66667,7.45356,15),new Point_3D(10,0,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,-15),new Point_3D(13.3333,14.9071,-15),new Point_3D(6.66667,7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,-15),new Point_3D(10,0,15),new Point_3D(6.66667,-7.45356,15)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,-15),new Point_3D(6.66667,-7.45356,15),new Point_3D(13.3333,-14.9071,-15)), shape, 0.0001));
    }

    @Test
    public void test_m_e_cylinder_4() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.e_cylinder(shape,0,0,10,10,30,3);
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-10,0,30),new Point_3D(-6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-6.66667,-7.45356,30),new Point_3D(-10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(-3.33333,9.42809,30),new Point_3D(-6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(-6.66667,-7.45356,30),new Point_3D(-3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-6.66667,7.45356,30),new Point_3D(-3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-3.33333,-9.42809,30),new Point_3D(-6.66667,-7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(8.88178e-16,10,30),new Point_3D(-3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(-3.33333,-9.42809,30),new Point_3D(8.88178e-16,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(-3.33333,9.42809,30),new Point_3D(8.88178e-16,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(8.88178e-16,-10,30),new Point_3D(-3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(3.33333,9.42809,30),new Point_3D(8.88178e-16,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(8.88178e-16,-10,30),new Point_3D(3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(8.88178e-16,10,30),new Point_3D(3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(3.33333,-9.42809,30),new Point_3D(8.88178e-16,-10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(6.66667,7.45356,30),new Point_3D(3.33333,9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(3.33333,-9.42809,30),new Point_3D(6.66667,-7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(3.33333,9.42809,30),new Point_3D(6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(6.66667,-7.45356,30),new Point_3D(3.33333,-9.42809,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(10,0,30),new Point_3D(6.66667,7.45356,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,30),new Point_3D(6.66667,-7.45356,30),new Point_3D(10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(6.66667,7.45356,30),new Point_3D(10,0,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,0,30),new Point_3D(6.66667,-7.45356,30)), shape, 0.0001));
    }

    @Test
    public void test_m_e_cylinder_5() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.e_cylinder(shape,0,0,10,10,30,3,false,new Point_3D(10,20,30));
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(0,20,60),new Point_3D(3.33333,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(3.33333,12.5464,60),new Point_3D(0,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(6.66667,29.4281,60),new Point_3D(3.33333,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(3.33333,12.5464,60),new Point_3D(6.66667,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(3.33333,27.4536,60),new Point_3D(6.66667,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(6.66667,10.5719,60),new Point_3D(3.33333,12.5464,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(10,30,60),new Point_3D(6.66667,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(6.66667,10.5719,60),new Point_3D(10,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(6.66667,29.4281,60),new Point_3D(10,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(10,10,60),new Point_3D(6.66667,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(13.3333,29.4281,60),new Point_3D(10,30,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(10,10,60),new Point_3D(13.3333,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(10,30,60),new Point_3D(13.3333,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(13.3333,10.5719,60),new Point_3D(10,10,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(16.6667,27.4536,60),new Point_3D(13.3333,29.4281,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(13.3333,10.5719,60),new Point_3D(16.6667,12.5464,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(13.3333,29.4281,60),new Point_3D(16.6667,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(16.6667,12.5464,60),new Point_3D(13.3333,10.5719,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(20,20,60),new Point_3D(16.6667,27.4536,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,60),new Point_3D(16.6667,12.5464,60),new Point_3D(20,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(16.6667,27.4536,60),new Point_3D(20,20,60)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,30),new Point_3D(20,20,60),new Point_3D(16.6667,12.5464,60)), shape, 0.0001));
    }

    @Test
    public void test_m_e_cylinder_6() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.e_cylinder(shape,10,10,0,0,30,3);
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-6.66667,7.45356,0),new Point_3D(-10,0,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-10,0,0),new Point_3D(-6.66667,-7.45356,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(-6.66667,7.45356,0),new Point_3D(-3.33333,9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(-3.33333,-9.42809,0),new Point_3D(-6.66667,-7.45356,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-3.33333,9.42809,0),new Point_3D(-6.66667,7.45356,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-6.66667,-7.45356,0),new Point_3D(-3.33333,-9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(-3.33333,9.42809,0),new Point_3D(8.88178e-16,10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(8.88178e-16,-10,0),new Point_3D(-3.33333,-9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(8.88178e-16,10,0),new Point_3D(-3.33333,9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(-3.33333,-9.42809,0),new Point_3D(8.88178e-16,-10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(8.88178e-16,10,0),new Point_3D(3.33333,9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(3.33333,-9.42809,0),new Point_3D(8.88178e-16,-10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(3.33333,9.42809,0),new Point_3D(8.88178e-16,10,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(8.88178e-16,-10,0),new Point_3D(3.33333,-9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(3.33333,9.42809,0),new Point_3D(6.66667,7.45356,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(6.66667,-7.45356,0),new Point_3D(3.33333,-9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(6.66667,7.45356,0),new Point_3D(3.33333,9.42809,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(3.33333,-9.42809,0),new Point_3D(6.66667,-7.45356,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(6.66667,7.45356,0),new Point_3D(10,0,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,0,0),new Point_3D(10,0,0),new Point_3D(6.66667,-7.45356,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(10,0,0),new Point_3D(6.66667,7.45356,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30),new Point_3D(6.66667,-7.45356,0),new Point_3D(10,0,0)), shape, 0.0001));
    }

    @Test
    public void test_m_e_cylinder_7() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.e_cylinder(shape,10,10,0,0,30,3,false,new Point_3D(10,20,30));
//        cout << "\n";
//        cout << "polyhedron(points=[";
//        bool write_comma(false);
//        for (Mesh_3D::const_point_iterator it = shape.point_begin(); it != shape.point_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        write_comma = false;
//        for (Mesh_3D::const_facet_iterator it = shape.facet_begin(); it != shape.facet_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << it->get_p1_index() << ',' << it->get_p2_index() << ',' << it->get_p3_index() << ']';
//        }
//        cout << "], convexity = 4);\n";
//        Mesh_3D::const_iterator iter(shape.begin());
//        while (iter != shape.end())
//        {
//            cout << "    Assert.assertTrue(mesh_contains(Facet_3D(shared_ptr<Point_3D>(new Point_3D(" << 
//                    iter->get_point1()->get_x() << "," << iter->get_point1()->get_y() << "," <<
//                    iter->get_point1()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point2()->get_x() << "," << iter->get_point2()->get_y() << "," <<
//                    iter->get_point2()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point3()->get_x() << "," << iter->get_point3()->get_y() << "," <<
//                    iter->get_point3()->get_z() << "))), shape, 0.0001));\n";
//            ++iter;
//        }
        Assert.assertTrue(shape.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(3.33333,27.4536,30),new Point_3D(0,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(0,20,30),new Point_3D(3.33333,12.5464,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(3.33333,27.4536,30),new Point_3D(6.66667,29.4281,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(6.66667,10.5719,30),new Point_3D(3.33333,12.5464,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(6.66667,29.4281,30),new Point_3D(3.33333,27.4536,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(3.33333,12.5464,30),new Point_3D(6.66667,10.5719,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(6.66667,29.4281,30),new Point_3D(10,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(10,10,30),new Point_3D(6.66667,10.5719,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(10,30,30),new Point_3D(6.66667,29.4281,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(6.66667,10.5719,30),new Point_3D(10,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(10,30,30),new Point_3D(13.3333,29.4281,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(13.3333,10.5719,30),new Point_3D(10,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(13.3333,29.4281,30),new Point_3D(10,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(10,10,30),new Point_3D(13.3333,10.5719,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(13.3333,29.4281,30),new Point_3D(16.6667,27.4536,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(16.6667,12.5464,30),new Point_3D(13.3333,10.5719,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(16.6667,27.4536,30),new Point_3D(13.3333,29.4281,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(13.3333,10.5719,30),new Point_3D(16.6667,12.5464,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(16.6667,27.4536,30),new Point_3D(20,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(20,20,30),new Point_3D(16.6667,12.5464,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(20,20,30),new Point_3D(16.6667,27.4536,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,60),new Point_3D(16.6667,12.5464,30),new Point_3D(20,20,30)), shape, 0.0001));
    }

    @Test
    public void test_m_sphere_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.sphere(shape,10,3);
        Assert.assertTrue(shape.size() == 120);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(5,0,8.66025),new Point_3D(4.33013,2.5,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,2.5,8.66025),new Point_3D(5,0,8.66025),new Point_3D(8.66025,0,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,2.5,8.66025),new Point_3D(8.66025,0,5),new Point_3D(7.5,4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,4.33013,5),new Point_3D(8.66025,0,5),new Point_3D(10,0,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,4.33013,5),new Point_3D(10,0,6.12323e-16),new Point_3D(8.66025,5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,6.12323e-16),new Point_3D(10,0,6.12323e-16),new Point_3D(8.66025,0,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,6.12323e-16),new Point_3D(8.66025,0,-5),new Point_3D(7.5,4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,4.33013,-5),new Point_3D(8.66025,0,-5),new Point_3D(5,0,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,4.33013,-5),new Point_3D(5,0,-8.66025),new Point_3D(4.33013,2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(4.33013,2.5,-8.66025),new Point_3D(5,0,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(4.33013,2.5,8.66025),new Point_3D(2.5,4.33013,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,4.33013,8.66025),new Point_3D(4.33013,2.5,8.66025),new Point_3D(7.5,4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,4.33013,8.66025),new Point_3D(7.5,4.33013,5),new Point_3D(4.33013,7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,7.5,5),new Point_3D(7.5,4.33013,5),new Point_3D(8.66025,5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,7.5,5),new Point_3D(8.66025,5,6.12323e-16),new Point_3D(5,8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,8.66025,6.12323e-16),new Point_3D(8.66025,5,6.12323e-16),new Point_3D(7.5,4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,8.66025,6.12323e-16),new Point_3D(7.5,4.33013,-5),new Point_3D(4.33013,7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,7.5,-5),new Point_3D(7.5,4.33013,-5),new Point_3D(4.33013,2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,7.5,-5),new Point_3D(4.33013,2.5,-8.66025),new Point_3D(2.5,4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(2.5,4.33013,-8.66025),new Point_3D(4.33013,2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(2.5,4.33013,8.66025),new Point_3D(3.06162e-16,5,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.06162e-16,5,8.66025),new Point_3D(2.5,4.33013,8.66025),new Point_3D(4.33013,7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.06162e-16,5,8.66025),new Point_3D(4.33013,7.5,5),new Point_3D(5.30288e-16,8.66025,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30288e-16,8.66025,5),new Point_3D(4.33013,7.5,5),new Point_3D(5,8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30288e-16,8.66025,5),new Point_3D(5,8.66025,6.12323e-16),new Point_3D(6.12323e-16,10,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.12323e-16,10,6.12323e-16),new Point_3D(5,8.66025,6.12323e-16),new Point_3D(4.33013,7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.12323e-16,10,6.12323e-16),new Point_3D(4.33013,7.5,-5),new Point_3D(5.30288e-16,8.66025,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30288e-16,8.66025,-5),new Point_3D(4.33013,7.5,-5),new Point_3D(2.5,4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30288e-16,8.66025,-5),new Point_3D(2.5,4.33013,-8.66025),new Point_3D(3.06162e-16,5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(3.06162e-16,5,-8.66025),new Point_3D(2.5,4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(3.06162e-16,5,8.66025),new Point_3D(-2.5,4.33013,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.5,4.33013,8.66025),new Point_3D(3.06162e-16,5,8.66025),new Point_3D(5.30288e-16,8.66025,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.5,4.33013,8.66025),new Point_3D(5.30288e-16,8.66025,5),new Point_3D(-4.33013,7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,7.5,5),new Point_3D(5.30288e-16,8.66025,5),new Point_3D(6.12323e-16,10,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,7.5,5),new Point_3D(6.12323e-16,10,6.12323e-16),new Point_3D(-5,8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,8.66025,6.12323e-16),new Point_3D(6.12323e-16,10,6.12323e-16),new Point_3D(5.30288e-16,8.66025,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,8.66025,6.12323e-16),new Point_3D(5.30288e-16,8.66025,-5),new Point_3D(-4.33013,7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,7.5,-5),new Point_3D(5.30288e-16,8.66025,-5),new Point_3D(3.06162e-16,5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,7.5,-5),new Point_3D(3.06162e-16,5,-8.66025),new Point_3D(-2.5,4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-2.5,4.33013,-8.66025),new Point_3D(3.06162e-16,5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-2.5,4.33013,8.66025),new Point_3D(-4.33013,2.5,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,2.5,8.66025),new Point_3D(-2.5,4.33013,8.66025),new Point_3D(-4.33013,7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,2.5,8.66025),new Point_3D(-4.33013,7.5,5),new Point_3D(-7.5,4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,4.33013,5),new Point_3D(-4.33013,7.5,5),new Point_3D(-5,8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,4.33013,5),new Point_3D(-5,8.66025,6.12323e-16),new Point_3D(-8.66025,5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,5,6.12323e-16),new Point_3D(-5,8.66025,6.12323e-16),new Point_3D(-4.33013,7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,5,6.12323e-16),new Point_3D(-4.33013,7.5,-5),new Point_3D(-7.5,4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,4.33013,-5),new Point_3D(-4.33013,7.5,-5),new Point_3D(-2.5,4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,4.33013,-5),new Point_3D(-2.5,4.33013,-8.66025),new Point_3D(-4.33013,2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-4.33013,2.5,-8.66025),new Point_3D(-2.5,4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-4.33013,2.5,8.66025),new Point_3D(-5,2.83277e-15,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,2.83277e-15,8.66025),new Point_3D(-4.33013,2.5,8.66025),new Point_3D(-7.5,4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,2.83277e-15,8.66025),new Point_3D(-7.5,4.33013,5),new Point_3D(-8.66025,4.9065e-15,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,4.9065e-15,5),new Point_3D(-7.5,4.33013,5),new Point_3D(-8.66025,5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,4.9065e-15,5),new Point_3D(-8.66025,5,6.12323e-16),new Point_3D(-10,5.66554e-15,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,5.66554e-15,6.12323e-16),new Point_3D(-8.66025,5,6.12323e-16),new Point_3D(-7.5,4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,5.66554e-15,6.12323e-16),new Point_3D(-7.5,4.33013,-5),new Point_3D(-8.66025,4.9065e-15,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,4.9065e-15,-5),new Point_3D(-7.5,4.33013,-5),new Point_3D(-4.33013,2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,4.9065e-15,-5),new Point_3D(-4.33013,2.5,-8.66025),new Point_3D(-5,2.83277e-15,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-5,2.83277e-15,-8.66025),new Point_3D(-4.33013,2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-5,2.83277e-15,8.66025),new Point_3D(-4.33013,-2.5,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,-2.5,8.66025),new Point_3D(-5,2.83277e-15,8.66025),new Point_3D(-8.66025,4.9065e-15,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,-2.5,8.66025),new Point_3D(-8.66025,4.9065e-15,5),new Point_3D(-7.5,-4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,-4.33013,5),new Point_3D(-8.66025,4.9065e-15,5),new Point_3D(-10,5.66554e-15,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,-4.33013,5),new Point_3D(-10,5.66554e-15,6.12323e-16),new Point_3D(-8.66025,-5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,-5,6.12323e-16),new Point_3D(-10,5.66554e-15,6.12323e-16),new Point_3D(-8.66025,4.9065e-15,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.66025,-5,6.12323e-16),new Point_3D(-8.66025,4.9065e-15,-5),new Point_3D(-7.5,-4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,-4.33013,-5),new Point_3D(-8.66025,4.9065e-15,-5),new Point_3D(-5,2.83277e-15,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.5,-4.33013,-5),new Point_3D(-5,2.83277e-15,-8.66025),new Point_3D(-4.33013,-2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-4.33013,-2.5,-8.66025),new Point_3D(-5,2.83277e-15,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-4.33013,-2.5,8.66025),new Point_3D(-2.5,-4.33013,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.5,-4.33013,8.66025),new Point_3D(-4.33013,-2.5,8.66025),new Point_3D(-7.5,-4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.5,-4.33013,8.66025),new Point_3D(-7.5,-4.33013,5),new Point_3D(-4.33013,-7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,-7.5,5),new Point_3D(-7.5,-4.33013,5),new Point_3D(-8.66025,-5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,-7.5,5),new Point_3D(-8.66025,-5,6.12323e-16),new Point_3D(-5,-8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-8.66025,6.12323e-16),new Point_3D(-8.66025,-5,6.12323e-16),new Point_3D(-7.5,-4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,-8.66025,6.12323e-16),new Point_3D(-7.5,-4.33013,-5),new Point_3D(-4.33013,-7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,-7.5,-5),new Point_3D(-7.5,-4.33013,-5),new Point_3D(-4.33013,-2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.33013,-7.5,-5),new Point_3D(-4.33013,-2.5,-8.66025),new Point_3D(-2.5,-4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-2.5,-4.33013,-8.66025),new Point_3D(-4.33013,-2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-2.5,-4.33013,8.66025),new Point_3D(-9.18485e-16,-5,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-9.18485e-16,-5,8.66025),new Point_3D(-2.5,-4.33013,8.66025),new Point_3D(-4.33013,-7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-9.18485e-16,-5,8.66025),new Point_3D(-4.33013,-7.5,5),new Point_3D(-1.59086e-15,-8.66025,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.59086e-15,-8.66025,5),new Point_3D(-4.33013,-7.5,5),new Point_3D(-5,-8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.59086e-15,-8.66025,5),new Point_3D(-5,-8.66025,6.12323e-16),new Point_3D(-1.83697e-15,-10,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.83697e-15,-10,6.12323e-16),new Point_3D(-5,-8.66025,6.12323e-16),new Point_3D(-4.33013,-7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.83697e-15,-10,6.12323e-16),new Point_3D(-4.33013,-7.5,-5),new Point_3D(-1.59086e-15,-8.66025,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.59086e-15,-8.66025,-5),new Point_3D(-4.33013,-7.5,-5),new Point_3D(-2.5,-4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.59086e-15,-8.66025,-5),new Point_3D(-2.5,-4.33013,-8.66025),new Point_3D(-9.18485e-16,-5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-9.18485e-16,-5,-8.66025),new Point_3D(-2.5,-4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-9.18485e-16,-5,8.66025),new Point_3D(2.5,-4.33013,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,-4.33013,8.66025),new Point_3D(-9.18485e-16,-5,8.66025),new Point_3D(-1.59086e-15,-8.66025,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,-4.33013,8.66025),new Point_3D(-1.59086e-15,-8.66025,5),new Point_3D(4.33013,-7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,-7.5,5),new Point_3D(-1.59086e-15,-8.66025,5),new Point_3D(-1.83697e-15,-10,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,-7.5,5),new Point_3D(-1.83697e-15,-10,6.12323e-16),new Point_3D(5,-8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-8.66025,6.12323e-16),new Point_3D(-1.83697e-15,-10,6.12323e-16),new Point_3D(-1.59086e-15,-8.66025,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,-8.66025,6.12323e-16),new Point_3D(-1.59086e-15,-8.66025,-5),new Point_3D(4.33013,-7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,-7.5,-5),new Point_3D(-1.59086e-15,-8.66025,-5),new Point_3D(-9.18485e-16,-5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,-7.5,-5),new Point_3D(-9.18485e-16,-5,-8.66025),new Point_3D(2.5,-4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(2.5,-4.33013,-8.66025),new Point_3D(-9.18485e-16,-5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(2.5,-4.33013,8.66025),new Point_3D(4.33013,-2.5,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,-2.5,8.66025),new Point_3D(2.5,-4.33013,8.66025),new Point_3D(4.33013,-7.5,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33013,-2.5,8.66025),new Point_3D(4.33013,-7.5,5),new Point_3D(7.5,-4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,-4.33013,5),new Point_3D(4.33013,-7.5,5),new Point_3D(5,-8.66025,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,-4.33013,5),new Point_3D(5,-8.66025,6.12323e-16),new Point_3D(8.66025,-5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,-5,6.12323e-16),new Point_3D(5,-8.66025,6.12323e-16),new Point_3D(4.33013,-7.5,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,-5,6.12323e-16),new Point_3D(4.33013,-7.5,-5),new Point_3D(7.5,-4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,-4.33013,-5),new Point_3D(4.33013,-7.5,-5),new Point_3D(2.5,-4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,-4.33013,-5),new Point_3D(2.5,-4.33013,-8.66025),new Point_3D(4.33013,-2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(4.33013,-2.5,-8.66025),new Point_3D(2.5,-4.33013,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(4.33013,-2.5,8.66025),new Point_3D(5,0,8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,8.66025),new Point_3D(4.33013,-2.5,8.66025),new Point_3D(7.5,-4.33013,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,8.66025),new Point_3D(7.5,-4.33013,5),new Point_3D(8.66025,0,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,5),new Point_3D(7.5,-4.33013,5),new Point_3D(8.66025,-5,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,5),new Point_3D(8.66025,-5,6.12323e-16),new Point_3D(10,0,6.12323e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,6.12323e-16),new Point_3D(8.66025,-5,6.12323e-16),new Point_3D(7.5,-4.33013,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,6.12323e-16),new Point_3D(7.5,-4.33013,-5),new Point_3D(8.66025,0,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,-5),new Point_3D(7.5,-4.33013,-5),new Point_3D(4.33013,-2.5,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,-5),new Point_3D(4.33013,-2.5,-8.66025),new Point_3D(5,0,-8.66025)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(5,0,-8.66025),new Point_3D(4.33013,-2.5,-8.66025)), shape, 0.0001));
    }

    @Test
    public void test_m_sphere_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.sphere(shape,10,3,new Point_3D(10,20,30));
//        cout << "\n";
//        cout << "polyhedron(points=[";
//        bool write_comma(false);
//        for (Mesh_3D::const_point_iterator it = shape.point_begin(); it != shape.point_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        write_comma = false;
//        for (Mesh_3D::const_facet_iterator it = shape.facet_begin(); it != shape.facet_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << it->get_p1_index() << ',' << it->get_p2_index() << ',' << it->get_p3_index() << ']';
//        }
//        cout << "], convexity = 4);\n";
//        Mesh_3D::const_iterator iter(shape.begin());
//        while (iter != shape.end())
//        {
//            cout << "    Assert.assertTrue(mesh_contains(Facet_3D(shared_ptr<Point_3D>(new Point_3D(" << 
//                    iter->get_point1()->get_x() << "," << iter->get_point1()->get_y() << "," <<
//                    iter->get_point1()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point2()->get_x() << "," << iter->get_point2()->get_y() << "," <<
//                    iter->get_point2()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point3()->get_x() << "," << iter->get_point3()->get_y() << "," <<
//                    iter->get_point3()->get_z() << "))), shape, 0.0001));\n";
//            ++iter;
//        }
        Assert.assertTrue(shape.size() == 120);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(15,20,38.6603),new Point_3D(14.3301,22.5,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,22.5,38.6603),new Point_3D(15,20,38.6603),new Point_3D(18.6603,20,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,22.5,38.6603),new Point_3D(18.6603,20,35),new Point_3D(17.5,24.3301,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,24.3301,35),new Point_3D(18.6603,20,35),new Point_3D(20,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,24.3301,35),new Point_3D(20,20,30),new Point_3D(18.6603,25,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,25,30),new Point_3D(20,20,30),new Point_3D(18.6603,20,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,25,30),new Point_3D(18.6603,20,25),new Point_3D(17.5,24.3301,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,24.3301,25),new Point_3D(18.6603,20,25),new Point_3D(15,20,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,24.3301,25),new Point_3D(15,20,21.3397),new Point_3D(14.3301,22.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(14.3301,22.5,21.3397),new Point_3D(15,20,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(14.3301,22.5,38.6603),new Point_3D(12.5,24.3301,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(12.5,24.3301,38.6603),new Point_3D(14.3301,22.5,38.6603),new Point_3D(17.5,24.3301,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(12.5,24.3301,38.6603),new Point_3D(17.5,24.3301,35),new Point_3D(14.3301,27.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,27.5,35),new Point_3D(17.5,24.3301,35),new Point_3D(18.6603,25,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,27.5,35),new Point_3D(18.6603,25,30),new Point_3D(15,28.6603,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,28.6603,30),new Point_3D(18.6603,25,30),new Point_3D(17.5,24.3301,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,28.6603,30),new Point_3D(17.5,24.3301,25),new Point_3D(14.3301,27.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,27.5,25),new Point_3D(17.5,24.3301,25),new Point_3D(14.3301,22.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,27.5,25),new Point_3D(14.3301,22.5,21.3397),new Point_3D(12.5,24.3301,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(12.5,24.3301,21.3397),new Point_3D(14.3301,22.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(12.5,24.3301,38.6603),new Point_3D(10,25,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,25,38.6603),new Point_3D(12.5,24.3301,38.6603),new Point_3D(14.3301,27.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,25,38.6603),new Point_3D(14.3301,27.5,35),new Point_3D(10,28.6603,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,28.6603,35),new Point_3D(14.3301,27.5,35),new Point_3D(15,28.6603,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,28.6603,35),new Point_3D(15,28.6603,30),new Point_3D(10,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,30,30),new Point_3D(15,28.6603,30),new Point_3D(14.3301,27.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,30,30),new Point_3D(14.3301,27.5,25),new Point_3D(10,28.6603,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,28.6603,25),new Point_3D(14.3301,27.5,25),new Point_3D(12.5,24.3301,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,28.6603,25),new Point_3D(12.5,24.3301,21.3397),new Point_3D(10,25,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(10,25,21.3397),new Point_3D(12.5,24.3301,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(10,25,38.6603),new Point_3D(7.5,24.3301,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,24.3301,38.6603),new Point_3D(10,25,38.6603),new Point_3D(10,28.6603,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,24.3301,38.6603),new Point_3D(10,28.6603,35),new Point_3D(5.66987,27.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,27.5,35),new Point_3D(10,28.6603,35),new Point_3D(10,30,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,27.5,35),new Point_3D(10,30,30),new Point_3D(5,28.6603,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,28.6603,30),new Point_3D(10,30,30),new Point_3D(10,28.6603,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,28.6603,30),new Point_3D(10,28.6603,25),new Point_3D(5.66987,27.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,27.5,25),new Point_3D(10,28.6603,25),new Point_3D(10,25,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,27.5,25),new Point_3D(10,25,21.3397),new Point_3D(7.5,24.3301,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(7.5,24.3301,21.3397),new Point_3D(10,25,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(7.5,24.3301,38.6603),new Point_3D(5.66987,22.5,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,22.5,38.6603),new Point_3D(7.5,24.3301,38.6603),new Point_3D(5.66987,27.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,22.5,38.6603),new Point_3D(5.66987,27.5,35),new Point_3D(2.5,24.3301,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,24.3301,35),new Point_3D(5.66987,27.5,35),new Point_3D(5,28.6603,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,24.3301,35),new Point_3D(5,28.6603,30),new Point_3D(1.33975,25,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,25,30),new Point_3D(5,28.6603,30),new Point_3D(5.66987,27.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,25,30),new Point_3D(5.66987,27.5,25),new Point_3D(2.5,24.3301,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,24.3301,25),new Point_3D(5.66987,27.5,25),new Point_3D(7.5,24.3301,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,24.3301,25),new Point_3D(7.5,24.3301,21.3397),new Point_3D(5.66987,22.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(5.66987,22.5,21.3397),new Point_3D(7.5,24.3301,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(5.66987,22.5,38.6603),new Point_3D(5,20,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,20,38.6603),new Point_3D(5.66987,22.5,38.6603),new Point_3D(2.5,24.3301,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,20,38.6603),new Point_3D(2.5,24.3301,35),new Point_3D(1.33975,20,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,20,35),new Point_3D(2.5,24.3301,35),new Point_3D(1.33975,25,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,20,35),new Point_3D(1.33975,25,30),new Point_3D(0,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(1.33975,25,30),new Point_3D(2.5,24.3301,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,30),new Point_3D(2.5,24.3301,25),new Point_3D(1.33975,20,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,20,25),new Point_3D(2.5,24.3301,25),new Point_3D(5.66987,22.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,20,25),new Point_3D(5.66987,22.5,21.3397),new Point_3D(5,20,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(5,20,21.3397),new Point_3D(5.66987,22.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(5,20,38.6603),new Point_3D(5.66987,17.5,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,17.5,38.6603),new Point_3D(5,20,38.6603),new Point_3D(1.33975,20,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,17.5,38.6603),new Point_3D(1.33975,20,35),new Point_3D(2.5,15.6699,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,15.6699,35),new Point_3D(1.33975,20,35),new Point_3D(0,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,15.6699,35),new Point_3D(0,20,30),new Point_3D(1.33975,15,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,15,30),new Point_3D(0,20,30),new Point_3D(1.33975,20,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33975,15,30),new Point_3D(1.33975,20,25),new Point_3D(2.5,15.6699,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,15.6699,25),new Point_3D(1.33975,20,25),new Point_3D(5,20,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,15.6699,25),new Point_3D(5,20,21.3397),new Point_3D(5.66987,17.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(5.66987,17.5,21.3397),new Point_3D(5,20,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(5.66987,17.5,38.6603),new Point_3D(7.5,15.6699,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.6699,38.6603),new Point_3D(5.66987,17.5,38.6603),new Point_3D(2.5,15.6699,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.6699,38.6603),new Point_3D(2.5,15.6699,35),new Point_3D(5.66987,12.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,12.5,35),new Point_3D(2.5,15.6699,35),new Point_3D(1.33975,15,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,12.5,35),new Point_3D(1.33975,15,30),new Point_3D(5,11.3397,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,11.3397,30),new Point_3D(1.33975,15,30),new Point_3D(2.5,15.6699,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,11.3397,30),new Point_3D(2.5,15.6699,25),new Point_3D(5.66987,12.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,12.5,25),new Point_3D(2.5,15.6699,25),new Point_3D(5.66987,17.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.66987,12.5,25),new Point_3D(5.66987,17.5,21.3397),new Point_3D(7.5,15.6699,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(7.5,15.6699,21.3397),new Point_3D(5.66987,17.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(7.5,15.6699,38.6603),new Point_3D(10,15,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,15,38.6603),new Point_3D(7.5,15.6699,38.6603),new Point_3D(5.66987,12.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,15,38.6603),new Point_3D(5.66987,12.5,35),new Point_3D(10,11.3397,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,11.3397,35),new Point_3D(5.66987,12.5,35),new Point_3D(5,11.3397,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,11.3397,35),new Point_3D(5,11.3397,30),new Point_3D(10,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,10,30),new Point_3D(5,11.3397,30),new Point_3D(5.66987,12.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,10,30),new Point_3D(5.66987,12.5,25),new Point_3D(10,11.3397,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,11.3397,25),new Point_3D(5.66987,12.5,25),new Point_3D(7.5,15.6699,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,11.3397,25),new Point_3D(7.5,15.6699,21.3397),new Point_3D(10,15,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(10,15,21.3397),new Point_3D(7.5,15.6699,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(10,15,38.6603),new Point_3D(12.5,15.6699,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(12.5,15.6699,38.6603),new Point_3D(10,15,38.6603),new Point_3D(10,11.3397,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(12.5,15.6699,38.6603),new Point_3D(10,11.3397,35),new Point_3D(14.3301,12.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,12.5,35),new Point_3D(10,11.3397,35),new Point_3D(10,10,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,12.5,35),new Point_3D(10,10,30),new Point_3D(15,11.3397,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,11.3397,30),new Point_3D(10,10,30),new Point_3D(10,11.3397,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,11.3397,30),new Point_3D(10,11.3397,25),new Point_3D(14.3301,12.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,12.5,25),new Point_3D(10,11.3397,25),new Point_3D(10,15,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,12.5,25),new Point_3D(10,15,21.3397),new Point_3D(12.5,15.6699,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(12.5,15.6699,21.3397),new Point_3D(10,15,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(12.5,15.6699,38.6603),new Point_3D(14.3301,17.5,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,17.5,38.6603),new Point_3D(12.5,15.6699,38.6603),new Point_3D(14.3301,12.5,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.3301,17.5,38.6603),new Point_3D(14.3301,12.5,35),new Point_3D(17.5,15.6699,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,15.6699,35),new Point_3D(14.3301,12.5,35),new Point_3D(15,11.3397,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,15.6699,35),new Point_3D(15,11.3397,30),new Point_3D(18.6603,15,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,15,30),new Point_3D(15,11.3397,30),new Point_3D(14.3301,12.5,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,15,30),new Point_3D(14.3301,12.5,25),new Point_3D(17.5,15.6699,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,15.6699,25),new Point_3D(14.3301,12.5,25),new Point_3D(12.5,15.6699,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(17.5,15.6699,25),new Point_3D(12.5,15.6699,21.3397),new Point_3D(14.3301,17.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(14.3301,17.5,21.3397),new Point_3D(12.5,15.6699,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,40),new Point_3D(14.3301,17.5,38.6603),new Point_3D(15,20,38.6603)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,20,38.6603),new Point_3D(14.3301,17.5,38.6603),new Point_3D(17.5,15.6699,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,20,38.6603),new Point_3D(17.5,15.6699,35),new Point_3D(18.6603,20,35)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,20,35),new Point_3D(17.5,15.6699,35),new Point_3D(18.6603,15,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,20,35),new Point_3D(18.6603,15,30),new Point_3D(20,20,30)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(18.6603,15,30),new Point_3D(17.5,15.6699,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,20,30),new Point_3D(17.5,15.6699,25),new Point_3D(18.6603,20,25)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,20,25),new Point_3D(17.5,15.6699,25),new Point_3D(14.3301,17.5,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(18.6603,20,25),new Point_3D(14.3301,17.5,21.3397),new Point_3D(15,20,21.3397)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,20),new Point_3D(15,20,21.3397),new Point_3D(14.3301,17.5,21.3397)), shape, 0.0001));
    }

    @Test
    public void test_m_ellipsoid_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.ellipsoid(shape,10,10,10,3);
        Assert.assertTrue(shape.size() == 120);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-7.45356,0,-6.66667),new Point_3D(-4.96904,5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-4.96904,-5.55556,-6.66667),new Point_3D(-7.45356,0,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-4.96904,5.55556,-6.66667),new Point_3D(-2.48452,7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-2.48452,-7.02728,-6.66667),new Point_3D(-4.96904,-5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-2.48452,7.02728,-6.66667),new Point_3D(-8.88178e-16,7.45356,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-8.88178e-16,-7.45356,-6.66667),new Point_3D(-2.48452,-7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(-8.88178e-16,7.45356,-6.66667),new Point_3D(2.48452,7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(2.48452,-7.02728,-6.66667),new Point_3D(-8.88178e-16,-7.45356,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(2.48452,7.02728,-6.66667),new Point_3D(4.96904,5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(4.96904,-5.55556,-6.66667),new Point_3D(2.48452,-7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(4.96904,5.55556,-6.66667),new Point_3D(7.45356,0,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-10),new Point_3D(7.45356,0,-6.66667),new Point_3D(4.96904,-5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,7.02728,-3.33333),new Point_3D(-7.45356,0,-6.66667),new Point_3D(-9.42809,0,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,7.02728,-3.33333),new Point_3D(-4.96904,5.55556,-6.66667),new Point_3D(-7.45356,0,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,-7.02728,-3.33333),new Point_3D(-9.42809,0,-3.33333),new Point_3D(-7.45356,0,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,-7.02728,-3.33333),new Point_3D(-7.45356,0,-6.66667),new Point_3D(-4.96904,-5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,8.88889,-3.33333),new Point_3D(-4.96904,5.55556,-6.66667),new Point_3D(-6.28539,7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,8.88889,-3.33333),new Point_3D(-2.48452,7.02728,-6.66667),new Point_3D(-4.96904,5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,-8.88889,-3.33333),new Point_3D(-6.28539,-7.02728,-3.33333),new Point_3D(-4.96904,-5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,-8.88889,-3.33333),new Point_3D(-4.96904,-5.55556,-6.66667),new Point_3D(-2.48452,-7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,9.42809,-3.33333),new Point_3D(-2.48452,7.02728,-6.66667),new Point_3D(-3.1427,8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,9.42809,-3.33333),new Point_3D(-8.88178e-16,7.45356,-6.66667),new Point_3D(-2.48452,7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-9.42809,-3.33333),new Point_3D(-3.1427,-8.88889,-3.33333),new Point_3D(-2.48452,-7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-9.42809,-3.33333),new Point_3D(-2.48452,-7.02728,-6.66667),new Point_3D(-8.88178e-16,-7.45356,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,8.88889,-3.33333),new Point_3D(-8.88178e-16,7.45356,-6.66667),new Point_3D(0,9.42809,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,8.88889,-3.33333),new Point_3D(2.48452,7.02728,-6.66667),new Point_3D(-8.88178e-16,7.45356,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,-8.88889,-3.33333),new Point_3D(0,-9.42809,-3.33333),new Point_3D(-8.88178e-16,-7.45356,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,-8.88889,-3.33333),new Point_3D(-8.88178e-16,-7.45356,-6.66667),new Point_3D(2.48452,-7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,7.02728,-3.33333),new Point_3D(2.48452,7.02728,-6.66667),new Point_3D(3.1427,8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,7.02728,-3.33333),new Point_3D(4.96904,5.55556,-6.66667),new Point_3D(2.48452,7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,-7.02728,-3.33333),new Point_3D(3.1427,-8.88889,-3.33333),new Point_3D(2.48452,-7.02728,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,-7.02728,-3.33333),new Point_3D(2.48452,-7.02728,-6.66667),new Point_3D(4.96904,-5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,-3.33333),new Point_3D(4.96904,5.55556,-6.66667),new Point_3D(6.28539,7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,-3.33333),new Point_3D(7.45356,0,-6.66667),new Point_3D(4.96904,5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,-3.33333),new Point_3D(6.28539,-7.02728,-3.33333),new Point_3D(4.96904,-5.55556,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,-3.33333),new Point_3D(4.96904,-5.55556,-6.66667),new Point_3D(7.45356,0,-6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,7.45356,8.88178e-16),new Point_3D(-9.42809,0,-3.33333),new Point_3D(-10,0,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,7.45356,8.88178e-16),new Point_3D(-6.28539,7.02728,-3.33333),new Point_3D(-9.42809,0,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,-7.45356,8.88178e-16),new Point_3D(-10,0,8.88178e-16),new Point_3D(-9.42809,0,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667,-7.45356,8.88178e-16),new Point_3D(-9.42809,0,-3.33333),new Point_3D(-6.28539,-7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,9.42809,8.88178e-16),new Point_3D(-6.28539,7.02728,-3.33333),new Point_3D(-6.66667,7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,9.42809,8.88178e-16),new Point_3D(-3.1427,8.88889,-3.33333),new Point_3D(-6.28539,7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,-9.42809,8.88178e-16),new Point_3D(-6.66667,-7.45356,8.88178e-16),new Point_3D(-6.28539,-7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333,-9.42809,8.88178e-16),new Point_3D(-6.28539,-7.02728,-3.33333),new Point_3D(-3.1427,-8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178e-16,10,8.88178e-16),new Point_3D(-3.1427,8.88889,-3.33333),new Point_3D(-3.33333,9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178e-16,10,8.88178e-16),new Point_3D(0,9.42809,-3.33333),new Point_3D(-3.1427,8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178e-16,-10,8.88178e-16),new Point_3D(-3.33333,-9.42809,8.88178e-16),new Point_3D(-3.1427,-8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178e-16,-10,8.88178e-16),new Point_3D(-3.1427,-8.88889,-3.33333),new Point_3D(0,-9.42809,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,9.42809,8.88178e-16),new Point_3D(0,9.42809,-3.33333),new Point_3D(8.88178e-16,10,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,9.42809,8.88178e-16),new Point_3D(3.1427,8.88889,-3.33333),new Point_3D(0,9.42809,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,-9.42809,8.88178e-16),new Point_3D(8.88178e-16,-10,8.88178e-16),new Point_3D(0,-9.42809,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333,-9.42809,8.88178e-16),new Point_3D(0,-9.42809,-3.33333),new Point_3D(3.1427,-8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,7.45356,8.88178e-16),new Point_3D(3.1427,8.88889,-3.33333),new Point_3D(3.33333,9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,7.45356,8.88178e-16),new Point_3D(6.28539,7.02728,-3.33333),new Point_3D(3.1427,8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,-7.45356,8.88178e-16),new Point_3D(3.33333,-9.42809,8.88178e-16),new Point_3D(3.1427,-8.88889,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,-7.45356,8.88178e-16),new Point_3D(3.1427,-8.88889,-3.33333),new Point_3D(6.28539,-7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,8.88178e-16),new Point_3D(6.28539,7.02728,-3.33333),new Point_3D(6.66667,7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,8.88178e-16),new Point_3D(9.42809,0,-3.33333),new Point_3D(6.28539,7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,8.88178e-16),new Point_3D(6.66667,-7.45356,8.88178e-16),new Point_3D(6.28539,-7.02728,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,8.88178e-16),new Point_3D(6.28539,-7.02728,-3.33333),new Point_3D(9.42809,0,-3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,7.02728,3.33333),new Point_3D(-10,0,8.88178e-16),new Point_3D(-9.42809,0,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,7.02728,3.33333),new Point_3D(-6.66667,7.45356,8.88178e-16),new Point_3D(-10,0,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,-7.02728,3.33333),new Point_3D(-9.42809,0,3.33333),new Point_3D(-10,0,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539,-7.02728,3.33333),new Point_3D(-10,0,8.88178e-16),new Point_3D(-6.66667,-7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,8.88889,3.33333),new Point_3D(-6.66667,7.45356,8.88178e-16),new Point_3D(-6.28539,7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,8.88889,3.33333),new Point_3D(-3.33333,9.42809,8.88178e-16),new Point_3D(-6.66667,7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,-8.88889,3.33333),new Point_3D(-6.28539,-7.02728,3.33333),new Point_3D(-6.66667,-7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.1427,-8.88889,3.33333),new Point_3D(-6.66667,-7.45356,8.88178e-16),new Point_3D(-3.33333,-9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,9.42809,3.33333),new Point_3D(-3.33333,9.42809,8.88178e-16),new Point_3D(-3.1427,8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,9.42809,3.33333),new Point_3D(8.88178e-16,10,8.88178e-16),new Point_3D(-3.33333,9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-9.42809,3.33333),new Point_3D(-3.1427,-8.88889,3.33333),new Point_3D(-3.33333,-9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-9.42809,3.33333),new Point_3D(-3.33333,-9.42809,8.88178e-16),new Point_3D(8.88178e-16,-10,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,8.88889,3.33333),new Point_3D(8.88178e-16,10,8.88178e-16),new Point_3D(0,9.42809,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,8.88889,3.33333),new Point_3D(3.33333,9.42809,8.88178e-16),new Point_3D(8.88178e-16,10,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,-8.88889,3.33333),new Point_3D(0,-9.42809,3.33333),new Point_3D(8.88178e-16,-10,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.1427,-8.88889,3.33333),new Point_3D(8.88178e-16,-10,8.88178e-16),new Point_3D(3.33333,-9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,7.02728,3.33333),new Point_3D(3.33333,9.42809,8.88178e-16),new Point_3D(3.1427,8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,7.02728,3.33333),new Point_3D(6.66667,7.45356,8.88178e-16),new Point_3D(3.33333,9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,-7.02728,3.33333),new Point_3D(3.1427,-8.88889,3.33333),new Point_3D(3.33333,-9.42809,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539,-7.02728,3.33333),new Point_3D(3.33333,-9.42809,8.88178e-16),new Point_3D(6.66667,-7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,3.33333),new Point_3D(6.66667,7.45356,8.88178e-16),new Point_3D(6.28539,7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,3.33333),new Point_3D(10,0,8.88178e-16),new Point_3D(6.66667,7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,3.33333),new Point_3D(6.28539,-7.02728,3.33333),new Point_3D(6.66667,-7.45356,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809,0,3.33333),new Point_3D(6.66667,-7.45356,8.88178e-16),new Point_3D(10,0,8.88178e-16)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.96904,5.55556,6.66667),new Point_3D(-9.42809,0,3.33333),new Point_3D(-7.45356,0,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.96904,5.55556,6.66667),new Point_3D(-6.28539,7.02728,3.33333),new Point_3D(-9.42809,0,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.96904,-5.55556,6.66667),new Point_3D(-7.45356,0,6.66667),new Point_3D(-9.42809,0,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.96904,-5.55556,6.66667),new Point_3D(-9.42809,0,3.33333),new Point_3D(-6.28539,-7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452,7.02728,6.66667),new Point_3D(-6.28539,7.02728,3.33333),new Point_3D(-4.96904,5.55556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452,7.02728,6.66667),new Point_3D(-3.1427,8.88889,3.33333),new Point_3D(-6.28539,7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452,-7.02728,6.66667),new Point_3D(-4.96904,-5.55556,6.66667),new Point_3D(-6.28539,-7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452,-7.02728,6.66667),new Point_3D(-6.28539,-7.02728,3.33333),new Point_3D(-3.1427,-8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,7.45356,6.66667),new Point_3D(-3.1427,8.88889,3.33333),new Point_3D(-2.48452,7.02728,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,7.45356,6.66667),new Point_3D(0,9.42809,3.33333),new Point_3D(-3.1427,8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-7.45356,6.66667),new Point_3D(-2.48452,-7.02728,6.66667),new Point_3D(-3.1427,-8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-7.45356,6.66667),new Point_3D(-3.1427,-8.88889,3.33333),new Point_3D(0,-9.42809,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452,7.02728,6.66667),new Point_3D(0,9.42809,3.33333),new Point_3D(0,7.45356,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452,7.02728,6.66667),new Point_3D(3.1427,8.88889,3.33333),new Point_3D(0,9.42809,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452,-7.02728,6.66667),new Point_3D(0,-7.45356,6.66667),new Point_3D(0,-9.42809,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452,-7.02728,6.66667),new Point_3D(0,-9.42809,3.33333),new Point_3D(3.1427,-8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.96904,5.55556,6.66667),new Point_3D(3.1427,8.88889,3.33333),new Point_3D(2.48452,7.02728,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.96904,5.55556,6.66667),new Point_3D(6.28539,7.02728,3.33333),new Point_3D(3.1427,8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.96904,-5.55556,6.66667),new Point_3D(2.48452,-7.02728,6.66667),new Point_3D(3.1427,-8.88889,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.96904,-5.55556,6.66667),new Point_3D(3.1427,-8.88889,3.33333),new Point_3D(6.28539,-7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.45356,0,6.66667),new Point_3D(6.28539,7.02728,3.33333),new Point_3D(4.96904,5.55556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.45356,0,6.66667),new Point_3D(9.42809,0,3.33333),new Point_3D(6.28539,7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.45356,0,6.66667),new Point_3D(4.96904,-5.55556,6.66667),new Point_3D(6.28539,-7.02728,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.45356,0,6.66667),new Point_3D(6.28539,-7.02728,3.33333),new Point_3D(9.42809,0,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-4.96904,5.55556,6.66667),new Point_3D(-7.45356,0,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-7.45356,0,6.66667),new Point_3D(-4.96904,-5.55556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-2.48452,7.02728,6.66667),new Point_3D(-4.96904,5.55556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-4.96904,-5.55556,6.66667),new Point_3D(-2.48452,-7.02728,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(0,7.45356,6.66667),new Point_3D(-2.48452,7.02728,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(-2.48452,-7.02728,6.66667),new Point_3D(0,-7.45356,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(2.48452,7.02728,6.66667),new Point_3D(0,7.45356,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(0,-7.45356,6.66667),new Point_3D(2.48452,-7.02728,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(4.96904,5.55556,6.66667),new Point_3D(2.48452,7.02728,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(2.48452,-7.02728,6.66667),new Point_3D(4.96904,-5.55556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(7.45356,0,6.66667),new Point_3D(4.96904,5.55556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(4.96904,-5.55556,6.66667),new Point_3D(7.45356,0,6.66667)), shape, 0.0001));
    }

    @Test
    public void test_m_ellipsoid_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.ellipsoid(shape,20,40,10,3,new Point_3D(20,40,10));
//        cout << "\n";
//        cout << "polyhedron(points=[";
//        bool write_comma(false);
//        for (Mesh_3D::const_point_iterator it = shape.point_begin(); it != shape.point_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << (*it)->get_x() << ',' << (*it)->get_y() << ',' << (*it)->get_z() << ']';
//        }
//        cout << "], faces=[";
//        write_comma = false;
//        for (Mesh_3D::const_facet_iterator it = shape.facet_begin(); it != shape.facet_end(); ++it)
//        {
//            if (write_comma)
//                cout << ',';
//            else
//                write_comma = true;
//
//            cout << '[' << it->get_p1_index() << ',' << it->get_p2_index() << ',' << it->get_p3_index() << ']';
//        }
//        cout << "], convexity = 4);\n";
//        Mesh_3D::const_iterator iter(shape.begin());
//        while (iter != shape.end())
//        {
//            cout << "    Assert.assertTrue(mesh_contains(Facet_3D(shared_ptr<Point_3D>(new Point_3D(" << 
//                    iter->get_point1()->get_x() << "," << iter->get_point1()->get_y() << "," <<
//                    iter->get_point1()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point2()->get_x() << "," << iter->get_point2()->get_y() << "," <<
//                    iter->get_point2()->get_z() << ")),shared_ptr<Point_3D>(new Point_3D(" <<
//                    iter->get_point3()->get_x() << "," << iter->get_point3()->get_y() << "," <<
//                    iter->get_point3()->get_z() << "))), shape, 0.0001));\n";
//            ++iter;
//        }
        Assert.assertTrue(shape.size() == 120);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(5.09288,40,3.33333),new Point_3D(10.0619,62.2222,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(10.0619,17.7778,3.33333),new Point_3D(5.09288,40,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(10.0619,62.2222,3.33333),new Point_3D(15.031,68.1091,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(15.031,11.8909,3.33333),new Point_3D(10.0619,17.7778,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(15.031,68.1091,3.33333),new Point_3D(20,69.8142,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(20,10.1858,3.33333),new Point_3D(15.031,11.8909,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(20,69.8142,3.33333),new Point_3D(24.969,68.1091,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(24.969,11.8909,3.33333),new Point_3D(20,10.1858,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(24.969,68.1091,3.33333),new Point_3D(29.9381,62.2222,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(29.9381,17.7778,3.33333),new Point_3D(24.969,11.8909,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(29.9381,62.2222,3.33333),new Point_3D(34.9071,40,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,0),new Point_3D(34.9071,40,3.33333),new Point_3D(29.9381,17.7778,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,68.1091,6.66667),new Point_3D(5.09288,40,3.33333),new Point_3D(1.14382,40,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,68.1091,6.66667),new Point_3D(10.0619,62.2222,3.33333),new Point_3D(5.09288,40,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,11.8909,6.66667),new Point_3D(1.14382,40,6.66667),new Point_3D(5.09288,40,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,11.8909,6.66667),new Point_3D(5.09288,40,3.33333),new Point_3D(10.0619,17.7778,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,75.5556,6.66667),new Point_3D(10.0619,62.2222,3.33333),new Point_3D(7.42921,68.1091,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,75.5556,6.66667),new Point_3D(15.031,68.1091,3.33333),new Point_3D(10.0619,62.2222,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,4.44444,6.66667),new Point_3D(7.42921,11.8909,6.66667),new Point_3D(10.0619,17.7778,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,4.44444,6.66667),new Point_3D(10.0619,17.7778,3.33333),new Point_3D(15.031,11.8909,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,77.7124,6.66667),new Point_3D(15.031,68.1091,3.33333),new Point_3D(13.7146,75.5556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,77.7124,6.66667),new Point_3D(20,69.8142,3.33333),new Point_3D(15.031,68.1091,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,2.28764,6.66667),new Point_3D(13.7146,4.44444,6.66667),new Point_3D(15.031,11.8909,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,2.28764,6.66667),new Point_3D(15.031,11.8909,3.33333),new Point_3D(20,10.1858,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,75.5556,6.66667),new Point_3D(20,69.8142,3.33333),new Point_3D(20,77.7124,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,75.5556,6.66667),new Point_3D(24.969,68.1091,3.33333),new Point_3D(20,69.8142,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,4.44444,6.66667),new Point_3D(20,2.28764,6.66667),new Point_3D(20,10.1858,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,4.44444,6.66667),new Point_3D(20,10.1858,3.33333),new Point_3D(24.969,11.8909,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,68.1091,6.66667),new Point_3D(24.969,68.1091,3.33333),new Point_3D(26.2854,75.5556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,68.1091,6.66667),new Point_3D(29.9381,62.2222,3.33333),new Point_3D(24.969,68.1091,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,11.8909,6.66667),new Point_3D(26.2854,4.44444,6.66667),new Point_3D(24.969,11.8909,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,11.8909,6.66667),new Point_3D(24.969,11.8909,3.33333),new Point_3D(29.9381,17.7778,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,6.66667),new Point_3D(29.9381,62.2222,3.33333),new Point_3D(32.5708,68.1091,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,6.66667),new Point_3D(34.9071,40,3.33333),new Point_3D(29.9381,62.2222,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,6.66667),new Point_3D(32.5708,11.8909,6.66667),new Point_3D(29.9381,17.7778,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,6.66667),new Point_3D(29.9381,17.7778,3.33333),new Point_3D(34.9071,40,3.33333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,69.8142,10),new Point_3D(1.14382,40,6.66667),new Point_3D(0,40,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,69.8142,10),new Point_3D(7.42921,68.1091,6.66667),new Point_3D(1.14382,40,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,10.1858,10),new Point_3D(0,40,10),new Point_3D(1.14382,40,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667,10.1858,10),new Point_3D(1.14382,40,6.66667),new Point_3D(7.42921,11.8909,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.3333,77.7124,10),new Point_3D(7.42921,68.1091,6.66667),new Point_3D(6.66667,69.8142,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.3333,77.7124,10),new Point_3D(13.7146,75.5556,6.66667),new Point_3D(7.42921,68.1091,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.3333,2.28764,10),new Point_3D(6.66667,10.1858,10),new Point_3D(7.42921,11.8909,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.3333,2.28764,10),new Point_3D(7.42921,11.8909,6.66667),new Point_3D(13.7146,4.44444,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,80,10),new Point_3D(13.7146,75.5556,6.66667),new Point_3D(13.3333,77.7124,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,80,10),new Point_3D(20,77.7124,6.66667),new Point_3D(13.7146,75.5556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,10),new Point_3D(13.3333,2.28764,10),new Point_3D(13.7146,4.44444,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,10),new Point_3D(13.7146,4.44444,6.66667),new Point_3D(20,2.28764,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.6667,77.7124,10),new Point_3D(20,77.7124,6.66667),new Point_3D(20,80,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.6667,77.7124,10),new Point_3D(26.2854,75.5556,6.66667),new Point_3D(20,77.7124,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.6667,2.28764,10),new Point_3D(20,0,10),new Point_3D(20,2.28764,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.6667,2.28764,10),new Point_3D(20,2.28764,6.66667),new Point_3D(26.2854,4.44444,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(33.3333,69.8142,10),new Point_3D(26.2854,75.5556,6.66667),new Point_3D(26.6667,77.7124,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(33.3333,69.8142,10),new Point_3D(32.5708,68.1091,6.66667),new Point_3D(26.2854,75.5556,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(33.3333,10.1858,10),new Point_3D(26.6667,2.28764,10),new Point_3D(26.2854,4.44444,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(33.3333,10.1858,10),new Point_3D(26.2854,4.44444,6.66667),new Point_3D(32.5708,11.8909,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(40,40,10),new Point_3D(32.5708,68.1091,6.66667),new Point_3D(33.3333,69.8142,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(40,40,10),new Point_3D(38.8562,40,6.66667),new Point_3D(32.5708,68.1091,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(40,40,10),new Point_3D(33.3333,10.1858,10),new Point_3D(32.5708,11.8909,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(40,40,10),new Point_3D(32.5708,11.8909,6.66667),new Point_3D(38.8562,40,6.66667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,68.1091,13.3333),new Point_3D(0,40,10),new Point_3D(1.14382,40,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,68.1091,13.3333),new Point_3D(6.66667,69.8142,10),new Point_3D(0,40,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,11.8909,13.3333),new Point_3D(1.14382,40,13.3333),new Point_3D(0,40,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.42921,11.8909,13.3333),new Point_3D(0,40,10),new Point_3D(6.66667,10.1858,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,75.5556,13.3333),new Point_3D(6.66667,69.8142,10),new Point_3D(7.42921,68.1091,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,75.5556,13.3333),new Point_3D(13.3333,77.7124,10),new Point_3D(6.66667,69.8142,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,4.44444,13.3333),new Point_3D(7.42921,11.8909,13.3333),new Point_3D(6.66667,10.1858,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.7146,4.44444,13.3333),new Point_3D(6.66667,10.1858,10),new Point_3D(13.3333,2.28764,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,77.7124,13.3333),new Point_3D(13.3333,77.7124,10),new Point_3D(13.7146,75.5556,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,77.7124,13.3333),new Point_3D(20,80,10),new Point_3D(13.3333,77.7124,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,2.28764,13.3333),new Point_3D(13.7146,4.44444,13.3333),new Point_3D(13.3333,2.28764,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,2.28764,13.3333),new Point_3D(13.3333,2.28764,10),new Point_3D(20,0,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,75.5556,13.3333),new Point_3D(20,80,10),new Point_3D(20,77.7124,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,75.5556,13.3333),new Point_3D(26.6667,77.7124,10),new Point_3D(20,80,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,4.44444,13.3333),new Point_3D(20,2.28764,13.3333),new Point_3D(20,0,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(26.2854,4.44444,13.3333),new Point_3D(20,0,10),new Point_3D(26.6667,2.28764,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,68.1091,13.3333),new Point_3D(26.6667,77.7124,10),new Point_3D(26.2854,75.5556,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,68.1091,13.3333),new Point_3D(33.3333,69.8142,10),new Point_3D(26.6667,77.7124,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,11.8909,13.3333),new Point_3D(26.2854,4.44444,13.3333),new Point_3D(26.6667,2.28764,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(32.5708,11.8909,13.3333),new Point_3D(26.6667,2.28764,10),new Point_3D(33.3333,10.1858,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,13.3333),new Point_3D(33.3333,69.8142,10),new Point_3D(32.5708,68.1091,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,13.3333),new Point_3D(40,40,10),new Point_3D(33.3333,69.8142,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,13.3333),new Point_3D(32.5708,11.8909,13.3333),new Point_3D(33.3333,10.1858,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(38.8562,40,13.3333),new Point_3D(33.3333,10.1858,10),new Point_3D(40,40,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0619,62.2222,16.6667),new Point_3D(1.14382,40,13.3333),new Point_3D(5.09288,40,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0619,62.2222,16.6667),new Point_3D(7.42921,68.1091,13.3333),new Point_3D(1.14382,40,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0619,17.7778,16.6667),new Point_3D(5.09288,40,16.6667),new Point_3D(1.14382,40,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0619,17.7778,16.6667),new Point_3D(1.14382,40,13.3333),new Point_3D(7.42921,11.8909,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15.031,68.1091,16.6667),new Point_3D(7.42921,68.1091,13.3333),new Point_3D(10.0619,62.2222,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15.031,68.1091,16.6667),new Point_3D(13.7146,75.5556,13.3333),new Point_3D(7.42921,68.1091,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15.031,11.8909,16.6667),new Point_3D(10.0619,17.7778,16.6667),new Point_3D(7.42921,11.8909,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15.031,11.8909,16.6667),new Point_3D(7.42921,11.8909,13.3333),new Point_3D(13.7146,4.44444,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,69.8142,16.6667),new Point_3D(13.7146,75.5556,13.3333),new Point_3D(15.031,68.1091,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,69.8142,16.6667),new Point_3D(20,77.7124,13.3333),new Point_3D(13.7146,75.5556,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,10.1858,16.6667),new Point_3D(15.031,11.8909,16.6667),new Point_3D(13.7146,4.44444,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,10.1858,16.6667),new Point_3D(13.7146,4.44444,13.3333),new Point_3D(20,2.28764,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(24.969,68.1091,16.6667),new Point_3D(20,77.7124,13.3333),new Point_3D(20,69.8142,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(24.969,68.1091,16.6667),new Point_3D(26.2854,75.5556,13.3333),new Point_3D(20,77.7124,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(24.969,11.8909,16.6667),new Point_3D(20,10.1858,16.6667),new Point_3D(20,2.28764,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(24.969,11.8909,16.6667),new Point_3D(20,2.28764,13.3333),new Point_3D(26.2854,4.44444,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(29.9381,62.2222,16.6667),new Point_3D(26.2854,75.5556,13.3333),new Point_3D(24.969,68.1091,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(29.9381,62.2222,16.6667),new Point_3D(32.5708,68.1091,13.3333),new Point_3D(26.2854,75.5556,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(29.9381,17.7778,16.6667),new Point_3D(24.969,11.8909,16.6667),new Point_3D(26.2854,4.44444,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(29.9381,17.7778,16.6667),new Point_3D(26.2854,4.44444,13.3333),new Point_3D(32.5708,11.8909,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(34.9071,40,16.6667),new Point_3D(32.5708,68.1091,13.3333),new Point_3D(29.9381,62.2222,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(34.9071,40,16.6667),new Point_3D(38.8562,40,13.3333),new Point_3D(32.5708,68.1091,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(34.9071,40,16.6667),new Point_3D(29.9381,17.7778,16.6667),new Point_3D(32.5708,11.8909,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(34.9071,40,16.6667),new Point_3D(32.5708,11.8909,13.3333),new Point_3D(38.8562,40,13.3333)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(10.0619,62.2222,16.6667),new Point_3D(5.09288,40,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(5.09288,40,16.6667),new Point_3D(10.0619,17.7778,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(15.031,68.1091,16.6667),new Point_3D(10.0619,62.2222,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(10.0619,17.7778,16.6667),new Point_3D(15.031,11.8909,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(20,69.8142,16.6667),new Point_3D(15.031,68.1091,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(15.031,11.8909,16.6667),new Point_3D(20,10.1858,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(24.969,68.1091,16.6667),new Point_3D(20,69.8142,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(20,10.1858,16.6667),new Point_3D(24.969,11.8909,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(29.9381,62.2222,16.6667),new Point_3D(24.969,68.1091,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(24.969,11.8909,16.6667),new Point_3D(29.9381,17.7778,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(34.9071,40,16.6667),new Point_3D(29.9381,62.2222,16.6667)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,40,20),new Point_3D(29.9381,17.7778,16.6667),new Point_3D(34.9071,40,16.6667)), shape, 0.0001));
    }
}
