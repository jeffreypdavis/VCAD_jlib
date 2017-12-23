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
 * File: Test_Mesh_2D.java
 */
package org.vcad.lib;

import java.util.Iterator;
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
public class Test_Mesh_2D extends TestBase {
    
    public Test_Mesh_2D() {
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
    public void test_Mesh_2D() {
        // test constructors
        Mesh_2D mesh = new Mesh_2D();
        Assert.assertTrue(mesh.get_precision() == DBL_EPSILON * 21);
        Assert.assertTrue(mesh.size() == 0);
        mesh = new Mesh_2D(DBL_EPSILON * 5);
        Assert.assertTrue(mesh.get_precision() == DBL_EPSILON * 5);
        Assert.assertTrue(mesh.size() == 0);
        // test add facet, size, and at
        Facet_2D facet = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10, 0));
        mesh.add(facet);
        Facet_2D facet1 = new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0, 10));
        mesh.add(facet1);
        Assert.assertTrue(mesh.size() == 2);
        Iterator<Facet_2D> it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), facet, precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), facet1, precision));
        // test rotate
        mesh.rotate(Math.PI / 4);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(7.07107, 7.07107), new Point_2D(0, 7.07107)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(0, 7.07107), new Point_2D(-7.07107, 7.07107)), precision));
        mesh.rotate(-Math.PI / 4);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10, 0), new Point_2D(5, 5)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5, 5), new Point_2D(0, 10)), precision));
        // test rotate about a different origin
        mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10, 0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0, 10)));
        mesh.rotate(Math.PI / 4, new Point_2D(10, 0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(2.92893,-7.07107), new Point_2D(10, 0), new Point_2D(2.92893, 0)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(2.92893,-7.07107), new Point_2D(2.92893, 0), new Point_2D(-4.14214, 0)), precision));
        mesh.rotate(-Math.PI / 4, new Point_2D(10, 0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10, 0), new Point_2D(5, 5)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5, 5), new Point_2D(0, 10)), precision));
        // test scale
        mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10, 0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0, 10)));
        mesh.scale(2, 0.5);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(20, 0), new Point_2D(10, 2.5)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10, 2.5), new Point_2D(0, 5)), precision));
        mesh.scale(0.5, 2);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10, 0), new Point_2D(5, 5)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5, 5), new Point_2D(0, 10)), precision));
        // test scale about a different origin
        mesh.scale(2, 0.5, new Point_2D(10, 0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(-10,0), new Point_2D(10, 0), new Point_2D(0, 2.5)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(-10,0), new Point_2D(0, 2.5), new Point_2D(-10, 5)), precision));
        mesh.scale(0.5, 2, new Point_2D(10, 0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10, 0), new Point_2D(5, 5)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5, 5), new Point_2D(0, 10)), precision));
        // test translate
        mesh.translate(5,10);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(5,10), new Point_2D(15, 10), new Point_2D(10, 15)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(5,10), new Point_2D(10, 15), new Point_2D(5, 20)), precision));
        mesh.translate(new Vector_2D(-5, -10));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10, 0), new Point_2D(5, 5)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5, 5), new Point_2D(0, 10)), precision));
//        int count = 0;
//        System.out.println();
//        for (Iterator<Facet_2D> iter = mesh.iterator(); iter.hasNext();) {
//            Facet_2D f  = iter.next();
//            System.out.println("mesh[" + count++ + "] p1(x: " + f.get_point1().get_x() +
//                    " y: " + f.get_point1().get_y() + ") p2(x: " + f.get_point2().get_x() + 
//                    " y: " + f.get_point2().get_y() + ") p3(x: " + f.get_point3().get_x() + 
//                    " y: " + f.get_point3().get_y() + ")");
//        }
//        count = 0;
//        for (iter = mesh.begin(); iter != mesh.end(); ++iter)
//        {
//            cout << "mesh[" << count++ << "] p1(x: " << (*iter)->get_point1().get_x() <<
//                    " y: " << (*iter)->get_point1().get_y() << ") p2(x: " <<
//                    (*iter)->get_point2().get_x() << " y: " << (*iter)->get_point2().get_y() <<
//                    ") p3(x: " << (*iter)->get_point3().get_x() << " y: " << 
//                    (*iter)->get_point3().get_y() << ")\n";
//        }
    }
    
    @Test
    public void test_rotate_1() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.rotate(Math.PI / 6);
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(-1.33975,22.3205), new Point_2D(-10,17.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(8.66025,5), new Point_2D(-1.33975,22.3205)), shape, precision));
    }

    @Test
    public void test_rotate_2() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.rotate(-Math.PI / 6);
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(18.6603,12.3205), new Point_2D(10,17.3205)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(8.66025,-5), new Point_2D(18.6603,12.3205)), shape, precision));
    }

    @Test
    public void test_rotate_3() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.rotate(Math.PI / 6, new Point_2D(50,10));
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.6987,-23.6603), new Point_2D(10.359,-1.33975), new Point_2D(1.69873,-6.33975)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(11.6987,-23.6603), new Point_2D(20.359,-18.6603), new Point_2D(10.359,-1.33975)), shape, precision));
    }

    @Test
    public void test_rotate_4() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.rotate(-Math.PI / 6, new Point_2D(50,10));
//        cout << gen_openscad_polygon(shape,2) << '\n';
//        int count=0;
//        for (Mesh_2D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "    Assert.assertTrue(is_equal(*shape.at(" << count++ << "), Facet_2D(Point_2D(" <<
//                    (*it)->get_point1().get_x() << ',' << (*it)->get_point1().get_y() << "), Point_2D(" << 
//                    (*it)->get_point2().get_x() << ',' << (*it)->get_point2().get_y() << "), Point_2D(" <<
//                    (*it)->get_point3().get_x() << ',' << (*it)->get_point3().get_y() << ")), precision));\n";
//        }
////        cout << "shape size: " << shape.size() << "\n";
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.69873,26.3397), new Point_2D(20.359,38.6603), new Point_2D(11.6987,43.6603)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(1.69873,26.3397), new Point_2D(10.359,21.3397), new Point_2D(20.359,38.6603)), shape, precision));
    }

    @Test
    public void test_scale_1() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.scale(2,1);
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(20,20), new Point_2D(0,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(20,0), new Point_2D(20,20)), shape, precision));
    }

    @Test
    public void test_scale_2() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.scale(1,0.5);
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(10,10), new Point_2D(0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(10,10)), shape, precision));
    }

    @Test
    public void test_scale_3() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.scale(2,1, new Point_2D(5,10));
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-5,0), new Point_2D(15,20), new Point_2D(-5,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-5,0), new Point_2D(15,0), new Point_2D(15,20)), shape, precision));
    }

    @Test
    public void test_scale_4() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.scale(1,0.5, new Point_2D(5,10));
//        cout << gen_openscad_polygon(shape,2) << '\n';
//        int count=0;
//        for (Mesh_2D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "    Assert.assertTrue(is_equal(*shape.at(" << count++ << "), Facet_2D(Point_2D(" <<
//                    (*it)->get_point1().get_x() << ',' << (*it)->get_point1().get_y() << "), Point_2D(" << 
//                    (*it)->get_point2().get_x() << ',' << (*it)->get_point2().get_y() << "), Point_2D(" <<
//                    (*it)->get_point3().get_x() << ',' << (*it)->get_point3().get_y() << ")), precision));\n";
//        }
////        cout << "shape size: " << shape.size() << "\n";
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,5), new Point_2D(10,15), new Point_2D(0,15)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,5), new Point_2D(10,5), new Point_2D(10,15)), shape, precision));
    }

    @Test
    public void test_translate_1() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.translate(-10,-10);
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10,-10), new Point_2D(0,10), new Point_2D(-10,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10,-10), new Point_2D(0,-10), new Point_2D(0,10)), shape, precision));
    }

    @Test
    public void test_translate_2() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.translate(new Vector_2D(-10,-10));
//        cout << gen_openscad_polygon(shape,2) << '\n';
//        int count=0;
//        for (Mesh_2D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "    Assert.assertTrue(is_equal(*shape.at(" << count++ << "), Facet_2D(Point_2D(" <<
//                    (*it)->get_point1().get_x() << ',' << (*it)->get_point1().get_y() << "), Point_2D(" << 
//                    (*it)->get_point2().get_x() << ',' << (*it)->get_point2().get_y() << "), Point_2D(" <<
//                    (*it)->get_point3().get_x() << ',' << (*it)->get_point3().get_y() << ")), precision));\n";
//        }
////        cout << "shape size: " << shape.size() << "\n";
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10,-10), new Point_2D(0,10), new Point_2D(-10,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-10,-10), new Point_2D(0,-10), new Point_2D(0,10)), shape, precision));
    }

    @Test
    public void test_move_1() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.move(new Point_2D(0,0), new Vector_2D(0,-1), true, new Point_2D(10,20));
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,10), new Point_2D(0,0), new Point_2D(0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-20,10), new Point_2D(-20,0), new Point_2D(0,0)), shape, precision));
    }

    @Test
    public void test_move_2() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
        shape.move(new Point_2D(0,10), new Vector_2D(1,1), false, new Point_2D(5,0));
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-3.53553,13.5355), new Point_2D(17.6777,20.6066), new Point_2D(10.6066,27.6777)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-3.53553,13.5355), new Point_2D(3.53553,6.46447), new Point_2D(17.6777,20.6066)), shape, precision));
    }

    @Test
    public void test_move_3() {
        Mesh_2D shape = new Mesh_2D();
        Shapes.rectangle(shape,10,20);
//        cout << '\n' << gen_openscad_polygon(shape,2) << '\n';
        shape.move(new Point_2D(0,10), new Vector_2D(1,-1), true, new Point_2D(5,20));
//        cout << gen_openscad_polygon(shape,2) << '\n';
//        int count=0;
//        for (Mesh_2D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "    Assert.assertTrue(is_equal(*shape.at(" << count++ << "), Facet_2D(Point_2D(" <<
//                    (*it)->get_point1().get_x() << ',' << (*it)->get_point1().get_y() << "), Point_2D(" << 
//                    (*it)->get_point2().get_x() << ',' << (*it)->get_point2().get_y() << "), Point_2D(" <<
//                    (*it)->get_point3().get_x() << ',' << (*it)->get_point3().get_y() << ")), precision));\n";
//        }
        Assert.assertTrue(shape.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-17.6777,-0.606602), new Point_2D(3.53553,6.46447), new Point_2D(-3.53553,13.5355)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(-17.6777,-0.606602), new Point_2D(-10.6066,-7.67767), new Point_2D(3.53553,6.46447)), shape, precision));
    }
    
    @Test
    public void test_linear_extrude_1() {
        Mesh_2D mesh_2d = new Mesh_2D();
        Shapes.rectangle(mesh_2d, 10,20);
        Mesh_3D result = new Mesh_3D();
        Mesh_2D.linear_extrude(mesh_2d, result, 15, false);
        Assert.assertTrue(result.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(10,20,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,15), new Point_3D(10,20,15), new Point_3D(0,20,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,20,0), new Point_3D(10,0,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,15), new Point_3D(10,0,15), new Point_3D(10,20,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,15), new Point_3D(10,0,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,0), new Point_3D(10,20,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,15), new Point_3D(0,0,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(0,20,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,0), new Point_3D(0,20,15), new Point_3D(10,20,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,20,0), new Point_3D(0,20,0), new Point_3D(0,20,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,15), new Point_3D(0,0,15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(10,0,15)), result, 0.0001));
    }

    @Test
    public void test_linear_extrude_2() {
        Mesh_2D mesh_2d = new Mesh_2D();
        Shapes.rectangle(mesh_2d, 10, 20);
        Mesh_2D m2d = new Mesh_2D();
        Shapes.rectangle(m2d, 5, 15);
        m2d.translate(2.5,2.5);
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        Mesh_2D mesh = new Mesh_2D();
        intersect_meshes.difference(mesh_2d, m2d, mesh);
        Mesh_3D result = new Mesh_3D();
        Mesh_2D.linear_extrude(mesh, result, 15, false);
        Assert.assertTrue(result.size() == 40);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,0.0),new Point_3D(7.5,17.5,0.0),new Point_3D(10.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,15.0),new Point_3D(10.0,20.0,15.0),new Point_3D(7.5,17.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,0.0),new Point_3D(2.5,17.5,0.0),new Point_3D(10.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,15.0),new Point_3D(10.0,20.0,15.0),new Point_3D(2.5,17.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,0.0),new Point_3D(2.5,17.5,0.0),new Point_3D(0.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,15.0),new Point_3D(0.0,20.0,15.0),new Point_3D(2.5,17.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,0.0),new Point_3D(2.5,5.0,0.0),new Point_3D(0.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,15.0),new Point_3D(0.0,20.0,15.0),new Point_3D(2.5,5.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,0.0),new Point_3D(0.0,0.0,0.0),new Point_3D(0.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,15.0),new Point_3D(0.0,20.0,15.0),new Point_3D(0.0,0.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,0.0),new Point_3D(2.5,2.5,0.0),new Point_3D(0.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,15.0),new Point_3D(0.0,0.0,15.0),new Point_3D(2.5,2.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,0.0),new Point_3D(7.5,2.5,0.0),new Point_3D(0.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,15.0),new Point_3D(0.0,0.0,15.0),new Point_3D(7.5,2.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,0.0),new Point_3D(7.5,2.5,0.0),new Point_3D(10.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,15.0),new Point_3D(10.0,0.0,15.0),new Point_3D(7.5,2.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,0.0),new Point_3D(7.5,15.0,0.0),new Point_3D(10.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,15.0),new Point_3D(10.0,0.0,15.0),new Point_3D(7.5,15.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,0.0),new Point_3D(10.0,20.0,0.0),new Point_3D(10.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,15.0),new Point_3D(10.0,0.0,15.0),new Point_3D(10.0,20.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,0.0),new Point_3D(2.5,17.5,15.0),new Point_3D(7.5,17.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,0.0),new Point_3D(2.5,17.5,0.0),new Point_3D(2.5,17.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,0.0),new Point_3D(7.5,17.5,15.0),new Point_3D(7.5,15.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,0.0),new Point_3D(7.5,17.5,0.0),new Point_3D(7.5,17.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,0.0),new Point_3D(0.0,0.0,15.0),new Point_3D(0.0,20.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,0.0),new Point_3D(0.0,0.0,0.0),new Point_3D(0.0,0.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,0.0),new Point_3D(2.5,2.5,15.0),new Point_3D(2.5,5.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,0.0),new Point_3D(2.5,2.5,0.0),new Point_3D(2.5,2.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,0.0),new Point_3D(0.0,20.0,15.0),new Point_3D(10.0,20.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,0.0),new Point_3D(0.0,20.0,0.0),new Point_3D(0.0,20.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,0.0),new Point_3D(2.5,5.0,15.0),new Point_3D(2.5,17.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,0.0),new Point_3D(2.5,5.0,0.0),new Point_3D(2.5,5.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,0.0),new Point_3D(7.5,15.0,15.0),new Point_3D(7.5,2.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,0.0),new Point_3D(7.5,15.0,0.0),new Point_3D(7.5,15.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,0.0),new Point_3D(10.0,20.0,15.0),new Point_3D(10.0,0.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,0.0),new Point_3D(10.0,20.0,0.0),new Point_3D(10.0,20.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,0.0),new Point_3D(7.5,2.5,15.0),new Point_3D(2.5,2.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,0.0),new Point_3D(7.5,2.5,0.0),new Point_3D(7.5,2.5,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,0.0),new Point_3D(10.0,0.0,15.0),new Point_3D(0.0,0.0,15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,0.0),new Point_3D(10.0,0.0,0.0),new Point_3D(10.0,0.0,15.0)), result, 0.0001));
    }

    @Test
    public void test_linear_extrude_3() {
        Mesh_2D mesh_2d = new Mesh_2D();
        Shapes.rectangle(mesh_2d, 10, 20);
        Mesh_2D m2d = new Mesh_2D();
        Shapes.rectangle(m2d, 5, 15);
        m2d.translate(2.5,2.5);
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        Mesh_2D mesh = new Mesh_2D();
        intersect_meshes.difference(mesh_2d, m2d, mesh);
        Mesh_3D result = new Mesh_3D();
        Mesh_2D.linear_extrude(mesh, result, 15, true);
        Assert.assertTrue(result.size() == 40);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,17.5,-7.5),new Point_3D(10.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,7.5),new Point_3D(10.0,20.0,7.5),new Point_3D(7.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-7.5),new Point_3D(2.5,17.5,-7.5),new Point_3D(10.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,7.5),new Point_3D(10.0,20.0,7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-7.5),new Point_3D(2.5,17.5,-7.5),new Point_3D(0.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,5.0,-7.5),new Point_3D(0.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(2.5,5.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(0.0,0.0,-7.5),new Point_3D(0.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(0.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,2.5,-7.5),new Point_3D(0.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,7.5),new Point_3D(0.0,0.0,7.5),new Point_3D(2.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-7.5),new Point_3D(7.5,2.5,-7.5),new Point_3D(0.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,7.5),new Point_3D(0.0,0.0,7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-7.5),new Point_3D(7.5,2.5,-7.5),new Point_3D(10.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,15.0,-7.5),new Point_3D(10.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(7.5,15.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(10.0,20.0,-7.5),new Point_3D(10.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(10.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-7.5),new Point_3D(2.5,17.5,7.5),new Point_3D(7.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-7.5),new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,17.5,7.5),new Point_3D(7.5,15.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,17.5,-7.5),new Point_3D(7.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,-7.5),new Point_3D(0.0,0.0,7.5),new Point_3D(0.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,-7.5),new Point_3D(0.0,0.0,-7.5),new Point_3D(0.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,2.5,7.5),new Point_3D(2.5,5.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,2.5,-7.5),new Point_3D(2.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(10.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-7.5),new Point_3D(0.0,20.0,-7.5),new Point_3D(0.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,5.0,7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,5.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,15.0,7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,15.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,-7.5),new Point_3D(10.0,20.0,7.5),new Point_3D(10.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,-7.5),new Point_3D(10.0,20.0,-7.5),new Point_3D(10.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-7.5),new Point_3D(7.5,2.5,7.5),new Point_3D(2.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-7.5),new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(0.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-7.5),new Point_3D(10.0,0.0,-7.5),new Point_3D(10.0,0.0,7.5)), result, 0.0001));
    }

    @Test
    public void test_linear_extrude_4() {
        Mesh_2D mesh_2d = new Mesh_2D();
        Shapes.rectangle(mesh_2d, 10, 20);
        Mesh_2D m2d = new Mesh_2D();
        Shapes.rectangle(m2d, 5, 15);
        m2d.translate(2.5,2.5);
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        Mesh_2D mesh = new Mesh_2D();
        intersect_meshes.difference(mesh_2d, m2d, mesh);
        Mesh_3D result = new Mesh_3D();
        Mesh_2D.linear_extrude(mesh, result, -15, false);
        Assert.assertTrue(result.size() == 40);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-15.0),new Point_3D(7.5,17.5,-15.0),new Point_3D(10.0,20.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,0.0),new Point_3D(10.0,20.0,0.0),new Point_3D(7.5,17.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-15.0),new Point_3D(2.5,17.5,-15.0),new Point_3D(10.0,20.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,0.0),new Point_3D(10.0,20.0,0.0),new Point_3D(2.5,17.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-15.0),new Point_3D(2.5,17.5,-15.0),new Point_3D(0.0,20.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,0.0),new Point_3D(0.0,20.0,0.0),new Point_3D(2.5,17.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-15.0),new Point_3D(2.5,5.0,-15.0),new Point_3D(0.0,20.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,0.0),new Point_3D(0.0,20.0,0.0),new Point_3D(2.5,5.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-15.0),new Point_3D(0.0,0.0,-15.0),new Point_3D(0.0,20.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,0.0),new Point_3D(0.0,20.0,0.0),new Point_3D(0.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-15.0),new Point_3D(2.5,2.5,-15.0),new Point_3D(0.0,0.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,0.0),new Point_3D(0.0,0.0,0.0),new Point_3D(2.5,2.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-15.0),new Point_3D(7.5,2.5,-15.0),new Point_3D(0.0,0.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,0.0),new Point_3D(0.0,0.0,0.0),new Point_3D(7.5,2.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-15.0),new Point_3D(7.5,2.5,-15.0),new Point_3D(10.0,0.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,0.0),new Point_3D(10.0,0.0,0.0),new Point_3D(7.5,2.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-15.0),new Point_3D(7.5,15.0,-15.0),new Point_3D(10.0,0.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,0.0),new Point_3D(10.0,0.0,0.0),new Point_3D(7.5,15.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-15.0),new Point_3D(10.0,20.0,-15.0),new Point_3D(10.0,0.0,-15.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,0.0),new Point_3D(10.0,0.0,0.0),new Point_3D(10.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-15.0),new Point_3D(2.5,17.5,0.0),new Point_3D(7.5,17.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-15.0),new Point_3D(2.5,17.5,-15.0),new Point_3D(2.5,17.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-15.0),new Point_3D(7.5,17.5,0.0),new Point_3D(7.5,15.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-15.0),new Point_3D(7.5,17.5,-15.0),new Point_3D(7.5,17.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,-15.0),new Point_3D(0.0,0.0,0.0),new Point_3D(0.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,-15.0),new Point_3D(0.0,0.0,-15.0),new Point_3D(0.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-15.0),new Point_3D(2.5,2.5,0.0),new Point_3D(2.5,5.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-15.0),new Point_3D(2.5,2.5,-15.0),new Point_3D(2.5,2.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-15.0),new Point_3D(0.0,20.0,0.0),new Point_3D(10.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-15.0),new Point_3D(0.0,20.0,-15.0),new Point_3D(0.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-15.0),new Point_3D(2.5,5.0,0.0),new Point_3D(2.5,17.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-15.0),new Point_3D(2.5,5.0,-15.0),new Point_3D(2.5,5.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-15.0),new Point_3D(7.5,15.0,0.0),new Point_3D(7.5,2.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-15.0),new Point_3D(7.5,15.0,-15.0),new Point_3D(7.5,15.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,-15.0),new Point_3D(10.0,20.0,0.0),new Point_3D(10.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,-15.0),new Point_3D(10.0,20.0,-15.0),new Point_3D(10.0,20.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-15.0),new Point_3D(7.5,2.5,0.0),new Point_3D(2.5,2.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-15.0),new Point_3D(7.5,2.5,-15.0),new Point_3D(7.5,2.5,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-15.0),new Point_3D(10.0,0.0,0.0),new Point_3D(0.0,0.0,0.0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-15.0),new Point_3D(10.0,0.0,-15.0),new Point_3D(10.0,0.0,0.0)), result, 0.0001));
    }

    @Test
    public void test_linear_extrude_5() {
        Mesh_2D mesh_2d = new Mesh_2D();
        Shapes.rectangle(mesh_2d, 10, 20);
        Mesh_2D m2d = new Mesh_2D();
        Shapes.rectangle(m2d, 5, 15);
        m2d.translate(2.5,2.5);
        Intersect_Meshes_2D intersect_meshes = new Intersect_Meshes_2D();
        Mesh_2D mesh = new Mesh_2D();
        intersect_meshes.difference(mesh_2d, m2d, mesh);
        Mesh_3D result = new Mesh_3D();
        Mesh_2D.linear_extrude(mesh, result, -15, true);
//        System.out.println();
//        System.out.print("polyhedron(points=[");
//        boolean write_comma = false;
//        for (Iterator<Point_3D> it = result.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (write_comma)
//                System.out.print(',');
//            else
//                write_comma = true;
//            
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        write_comma = false;
//        for (Iterator<Facet> it = result.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (write_comma)
//                System.out.print(',');
//            else
//                write_comma = true;
//            
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p2_index() + "," + f.get_p3_index() + "]");
//        }
//        System.out.println("], convexity = 4);");
//        System.out.println("        Assert.assertTrue(result.size() == " + result.size() + ");");
//        Iterator<Facet_3D> iter = result.iterator();
//        while (iter.hasNext()) {
//            Facet_3D f = iter.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(" + 
//                    f.get_point1().get_x() + "," + f.get_point1().get_y() + "," +
//                    f.get_point1().get_z() + "),new Point_3D(" + f.get_point2().get_x() + 
//                    "," + f.get_point2().get_y() + "," + f.get_point2().get_z() + 
//                    "),new Point_3D(" + f.get_point3().get_x() + "," + f.get_point3().get_y() + 
//                    "," + f.get_point3().get_z() + ")), result, 0.0001));");
//        }
        Assert.assertTrue(result.size() == 40);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,17.5,-7.5),new Point_3D(10.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,7.5),new Point_3D(10.0,20.0,7.5),new Point_3D(7.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-7.5),new Point_3D(2.5,17.5,-7.5),new Point_3D(10.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,7.5),new Point_3D(10.0,20.0,7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-7.5),new Point_3D(2.5,17.5,-7.5),new Point_3D(0.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,5.0,-7.5),new Point_3D(0.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(2.5,5.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(0.0,0.0,-7.5),new Point_3D(0.0,20.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(0.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,2.5,-7.5),new Point_3D(0.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,7.5),new Point_3D(0.0,0.0,7.5),new Point_3D(2.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-7.5),new Point_3D(7.5,2.5,-7.5),new Point_3D(0.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,7.5),new Point_3D(0.0,0.0,7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-7.5),new Point_3D(7.5,2.5,-7.5),new Point_3D(10.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,15.0,-7.5),new Point_3D(10.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(7.5,15.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(10.0,20.0,-7.5),new Point_3D(10.0,0.0,-7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(10.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-7.5),new Point_3D(2.5,17.5,7.5),new Point_3D(7.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,17.5,-7.5),new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,17.5,7.5),new Point_3D(7.5,15.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,17.5,-7.5),new Point_3D(7.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,-7.5),new Point_3D(0.0,0.0,7.5),new Point_3D(0.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,20.0,-7.5),new Point_3D(0.0,0.0,-7.5),new Point_3D(0.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,2.5,7.5),new Point_3D(2.5,5.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,2.5,-7.5),new Point_3D(2.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-7.5),new Point_3D(0.0,20.0,7.5),new Point_3D(10.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,20.0,-7.5),new Point_3D(0.0,20.0,-7.5),new Point_3D(0.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,5.0,7.5),new Point_3D(2.5,17.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,17.5,-7.5),new Point_3D(2.5,5.0,-7.5),new Point_3D(2.5,5.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,15.0,7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,15.0,-7.5),new Point_3D(7.5,15.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,-7.5),new Point_3D(10.0,20.0,7.5),new Point_3D(10.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.0,0.0,-7.5),new Point_3D(10.0,20.0,-7.5),new Point_3D(10.0,20.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-7.5),new Point_3D(7.5,2.5,7.5),new Point_3D(2.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.5,2.5,-7.5),new Point_3D(7.5,2.5,-7.5),new Point_3D(7.5,2.5,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-7.5),new Point_3D(10.0,0.0,7.5),new Point_3D(0.0,0.0,7.5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.0,0.0,-7.5),new Point_3D(10.0,0.0,-7.5),new Point_3D(10.0,0.0,7.5)), result, 0.0001));
    }
}
