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
 * File: Test_Mesh_3D.java
 */
package org.vcad.lib;

import java.util.Iterator;
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
public class Test_Mesh_3D extends TestBase {
    
    public Test_Mesh_3D() {
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
    public void test_Mesh_3D() {
        // test constructors
        Mesh_3D mesh = new Mesh_3D();
        Assert.assertTrue(mesh.get_precision() == DBL_EPSILON * 21);
        Assert.assertTrue(mesh.size() == 0);
        mesh = new Mesh_3D(DBL_EPSILON * 6);
        Assert.assertTrue(mesh.get_precision() == DBL_EPSILON * 6);
        Assert.assertTrue(mesh.size() == 0);
        // test add facet, size
        Facet_3D facet = new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0));
        mesh.add(facet);
        Assert.assertTrue(mesh.size() == 1);
        Iterator<Facet_3D> it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), facet, precision));
        Facet_3D facet1 = new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0));
        mesh.add(facet1);
        Assert.assertTrue(mesh.size() == 2);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), facet, precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), facet1, precision));
        // test rotate x_axis
        Angle angle = new Angle(Math.PI/4,0,0);
        mesh.rotate(angle);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 3.53553, 0.0001));
        Assert.assertTrue(Point_3D.is_equal(facet.get_point3(), new Point_3D(10,0,0), precision));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 7.07107, 0.0001));
        angle.set_x(-Math.PI / 4);
        mesh.rotate(angle);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Facet_3D.is_equal(facet, new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Facet_3D.is_equal(facet, new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(0, 10, 0)), precision));
        // test rotate y_Axis
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        angle.set_x(0);
        angle.set_y(Math.PI / 4);
        mesh.rotate(angle);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), -3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), -7.07107, 0.0001));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), -3.53553, 0.0001));
        Assert.assertTrue(Point_3D.is_equal(facet.get_point3(), new Point_3D(0,10,0), precision));
        angle.set_y(-Math.PI / 4);
        mesh.rotate(angle);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Facet_3D.is_equal(facet, new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Facet_3D.is_equal(facet, new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(0, 10, 0)), precision));
        // test rotate z_axis
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        angle.set_y(0);
        angle.set_z(Math.PI / 4);
        mesh.rotate(angle);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 6.21725e-15, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 6.21725e-15, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), -7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        angle.set_z(-Math.PI / 4);
        mesh.rotate(angle);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Facet_3D.is_equal(facet, new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Facet_3D.is_equal(facet, new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(0, 10, 0)), precision));
        // test rotate x_axis about a different origin
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        angle.set_z(0);
        angle.set_x(Math.PI / 4);
        mesh.rotate(angle, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 3.53553, 0.0001));
        Assert.assertTrue(Point_3D.is_equal(facet.get_point3(), new Point_3D(10,0,0), precision));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(Point_3D.is_equal(facet.get_point1(), new Point_3D(0,0,0), precision));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 7.07107, 0.0001));
        angle.set_x(-Math.PI / 4);
        mesh.rotate(angle, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(0, 10, 0)), precision));
        // test rotate y_Axis about a different origin
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        angle.set_x(0);
        angle.set_y(Math.PI / 4);
        mesh.rotate(angle, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 2.92893, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 6.46447, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 3.53553, 0.0001));
        Assert.assertTrue(Point_3D.is_equal(facet.get_point3(), new Point_3D(10,0,0), precision));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 2.92893, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 6.46447, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 3.53553, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 2.92893, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 10, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 7.07107, 0.0001));
        angle.set_y(-Math.PI / 4);
        mesh.rotate(angle, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 10, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 10, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        // test rotate z_axis about a different origin
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        angle.set_y(0);
        angle.set_z(Math.PI / 4);
        mesh.rotate(angle, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 2.92893, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), -7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 2.92893, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 10, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 2.92893, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), -7.07107, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 2.92893, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), -4.14214, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        angle.set_z(-Math.PI / 4);
        mesh.rotate(angle, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 10, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        Assert.assertTrue(it.hasNext());
        facet = it.next();
        Assert.assertTrue(within_round(facet.get_point1().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point1().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(facet.get_point2().get_z(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_y(), 10, 0.0001));
        Assert.assertTrue(within_round(facet.get_point3().get_z(), 0, 0.0001));
        // test scale
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        mesh.scale(2,0.5,2);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(10, 2.5, 0), new Point_3D(20, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(10, 2.5, 0), new Point_3D(0, 5, 0)), precision));
        mesh.scale(0.5,2,0.5);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(0, 10, 0)), precision));
        // test scale about a different origin
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        mesh.scale(2,0.5,2, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(-10,0,0), new Point_3D(0, 2.5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(-10,0,0), new Point_3D(0, 2.5, 0), new Point_3D(-10, 5, 0)), precision));
        mesh.scale(0.5,2,0.5, new Point_3D(10,0,0));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(0, 10, 0)), precision));
        // test translate
        mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(10, 0, 0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,0), new Point_3D(0,10, 0)));
        mesh.translate(5,7,-3);
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(5,7,-3), new Point_3D(10, 12, -3), new Point_3D(15, 7, -3)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(5,7,-3), new Point_3D(10, 12, -3), new Point_3D(5, 17, -3)), precision));
        mesh.translate(new Vector_3D(-5,-7,3));
        it = mesh.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(10, 0, 0)), precision));
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(it.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5, 5, 0), new Point_3D(0, 10, 0)), precision));
//        int count = 0;
//        cout << "\n";
//        for (iter = mesh.begin(); iter != mesh.end(); ++iter)
//        {
//            cout << "mesh[" << count++ << "] p1(x: " << iter->get_point1()->get_x() <<
//                    " y: " << iter->get_point1()->get_y() << " z: " <<
//                    iter->get_point1()->get_z() << ") p2(x: " <<
//                    iter->get_point2()->get_x() << " y: " << iter->get_point2()->get_y() <<
//                    " z: " << iter->get_point2()->get_z() << ") p3(x: " << 
//                    iter->get_point3()->get_x() << " y: " << iter->get_point3()->get_y() << 
//                    " z: " << iter->get_point3()->get_z() << ")\n";
//        }
    }

    @Test
    public void test_mesh_contains_point() {
        // generate test mesh
        Mesh_3D mesh = new Mesh_3D();
        // end1
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,15), new Point_3D(0,10,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,15), new Point_3D(0,10,15), new Point_3D(0,10,0)));
        // end2
        mesh.add(new Facet_3D(new Point_3D(20,0,0), new Point_3D(20,10,0), new Point_3D(20,10,20)));
        mesh.add(new Facet_3D(new Point_3D(20,0,0), new Point_3D(20,10,20), new Point_3D(20,0,20)));
        // side1
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,0,15), new Point_3D(0,0,15)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,0,0), new Point_3D(5,0,15)));
        mesh.add(new Facet_3D(new Point_3D(5,0,0), new Point_3D(10,0,0), new Point_3D(10,0,15)));
        mesh.add(new Facet_3D(new Point_3D(5,0,0), new Point_3D(10,0,15), new Point_3D(5,0,15)));
        mesh.add(new Facet_3D(new Point_3D(5,0,15), new Point_3D(10,0,15), new Point_3D(10,0,20)));
        mesh.add(new Facet_3D(new Point_3D(5,0,15), new Point_3D(10,0,20), new Point_3D(5,0,20)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(15,0,0), new Point_3D(15,0,15)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(15,0,15), new Point_3D(10,0,15)));
        mesh.add(new Facet_3D(new Point_3D(15,0,0), new Point_3D(20,0,0), new Point_3D(20,0,20)));
        mesh.add(new Facet_3D(new Point_3D(15,0,0), new Point_3D(20,0,20), new Point_3D(15,0,15)));
        mesh.add(new Facet_3D(new Point_3D(15,0,15), new Point_3D(20,0,20), new Point_3D(15,0,20)));
        // side2
        mesh.add(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,10,15), new Point_3D(5,10,15)));
        mesh.add(new Facet_3D(new Point_3D(0,10,0), new Point_3D(5,10,15), new Point_3D(5,10,0)));
        mesh.add(new Facet_3D(new Point_3D(5,10,0), new Point_3D(10,10,15), new Point_3D(10,10,0)));
        mesh.add(new Facet_3D(new Point_3D(5,10,0), new Point_3D(5,10,15), new Point_3D(10,10,15)));
        mesh.add(new Facet_3D(new Point_3D(5,10,15), new Point_3D(10,10,20), new Point_3D(10,10,15)));
        mesh.add(new Facet_3D(new Point_3D(5,10,15), new Point_3D(5,10,20), new Point_3D(10,10,20)));
        mesh.add(new Facet_3D(new Point_3D(10,10,0), new Point_3D(15,10,15), new Point_3D(15,10,0)));
        mesh.add(new Facet_3D(new Point_3D(10,10,0), new Point_3D(10,10,15), new Point_3D(15,10,15)));
        mesh.add(new Facet_3D(new Point_3D(15,10,0), new Point_3D(20,10,20), new Point_3D(20,10,0)));
        mesh.add(new Facet_3D(new Point_3D(15,10,0), new Point_3D(15,10,15), new Point_3D(20,10,20)));
        mesh.add(new Facet_3D(new Point_3D(15,10,15), new Point_3D(15,10,20), new Point_3D(20,10,20)));
        // bottom
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(5,10,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,10,0), new Point_3D(5,0,0)));
        mesh.add(new Facet_3D(new Point_3D(5,0,0), new Point_3D(5,10,0), new Point_3D(10,10,0)));
        mesh.add(new Facet_3D(new Point_3D(5,0,0), new Point_3D(10,10,0), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,0), new Point_3D(15,10,0)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(15,10,0), new Point_3D(15,0,0)));
        mesh.add(new Facet_3D(new Point_3D(15,0,0), new Point_3D(15,10,0), new Point_3D(20,10,0)));
        mesh.add(new Facet_3D(new Point_3D(15,0,0), new Point_3D(20,10,0), new Point_3D(20,0,0)));
        // top
        mesh.add(new Facet_3D(new Point_3D(0,0,15), new Point_3D(5,0,15), new Point_3D(5,10,15)));
        mesh.add(new Facet_3D(new Point_3D(0,0,15), new Point_3D(5,10,15), new Point_3D(0,10,15)));
        mesh.add(new Facet_3D(new Point_3D(5,0,15), new Point_3D(5,0,20), new Point_3D(5,10,20)));
        mesh.add(new Facet_3D(new Point_3D(5,0,15), new Point_3D(5,10,20), new Point_3D(5,10,15)));
        mesh.add(new Facet_3D(new Point_3D(5,0,20), new Point_3D(10,0,20), new Point_3D(10,10,20)));
        mesh.add(new Facet_3D(new Point_3D(5,0,20), new Point_3D(10,10,20), new Point_3D(5,10,20)));
        mesh.add(new Facet_3D(new Point_3D(10,0,15), new Point_3D(10,10,15), new Point_3D(10,10,20)));
        mesh.add(new Facet_3D(new Point_3D(10,0,15), new Point_3D(10,10,20), new Point_3D(10,0,20)));
        mesh.add(new Facet_3D(new Point_3D(10,0,15), new Point_3D(15,0,15), new Point_3D(15,10,15)));
        mesh.add(new Facet_3D(new Point_3D(10,0,15), new Point_3D(15,10,15), new Point_3D(10,10,15)));
        mesh.add(new Facet_3D(new Point_3D(15,0,15), new Point_3D(15,0,20), new Point_3D(15,10,20)));
        mesh.add(new Facet_3D(new Point_3D(15,0,15), new Point_3D(15,10,20), new Point_3D(15,10,15)));
        mesh.add(new Facet_3D(new Point_3D(15,0,20), new Point_3D(20,0,20), new Point_3D(20,10,20)));
        mesh.add(new Facet_3D(new Point_3D(15,0,20), new Point_3D(20,10,20), new Point_3D(15,10,20)));

        // test points outside
        Bool pt_on_surface = new Bool(false);
        Point_3D p = new Point_3D(-1,4,4);
        Assert.assertTrue(!Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        p = new Point_3D(12.5,5,21);
        Assert.assertTrue(!Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        p = new Point_3D(2.5,5,16);
        Assert.assertTrue(!Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        p = new Point_3D(7.5,5,20.5);
        Assert.assertTrue(!Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        // corner points
        p = new Point_3D(0,0,0);
        Assert.assertTrue(Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        Assert.assertTrue(pt_on_surface.get_val());
        pt_on_surface.set_val(false);
        p = new Point_3D(15,10,20);
        Assert.assertTrue(Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        Assert.assertTrue(pt_on_surface.get_val());
        pt_on_surface.set_val(false);
        // points on sides
        p = new Point_3D(0, 5, 5);
        Assert.assertTrue(Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        Assert.assertTrue(pt_on_surface.get_val());
        pt_on_surface.set_val(false);
        p = new Point_3D(6, 5, 0);
        Assert.assertTrue(Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        Assert.assertTrue(pt_on_surface.get_val());
        pt_on_surface.set_val(false);
        // points inside
        p = new Point_3D(1, 1, 1);
        Assert.assertTrue(Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        Assert.assertTrue(!pt_on_surface.get_val());
        pt_on_surface.set_val(false);
        p = new Point_3D(6, 9, 16);
        Assert.assertTrue(Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        Assert.assertTrue(!pt_on_surface.get_val());
        pt_on_surface.set_val(false);
        p = new Point_3D(16, 2, 18);
        Assert.assertTrue(Mesh_3D.mesh_contains_point(mesh, p, pt_on_surface));
        Assert.assertTrue(!pt_on_surface.get_val());
    }
    
    @Test
    public void test_rotate_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(Math.PI / 6,0,0);
        shape.rotate(angle);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,17.3205,10), new Point_3D(10,17.3205,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,17.3205,10), new Point_3D(10,0,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,34.641), new Point_3D(10,-2.67949,44.641), new Point_3D(0,-2.67949,44.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,34.641), new Point_3D(10,-20,34.641), new Point_3D(10,-2.67949,44.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,-20,34.641), new Point_3D(0,-2.67949,44.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,-2.67949,44.641), new Point_3D(0,17.3205,10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,-2.67949,44.641), new Point_3D(10,-20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,17.3205,10), new Point_3D(10,-2.67949,44.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,-20,34.641), new Point_3D(0,-20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(10,-20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,17.3205,10), new Point_3D(0,-2.67949,44.641), new Point_3D(10,-2.67949,44.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,17.3205,10), new Point_3D(10,-2.67949,44.641), new Point_3D(10,17.3205,10)), shape, 0.0001));
    }

    @Test
    public void test_rotate_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(-Math.PI / 6,0,0);
        shape.rotate(angle);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,17.3205,-10), new Point_3D(10,17.3205,-10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,17.3205,-10), new Point_3D(10,0,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,34.641), new Point_3D(10,37.3205,24.641), new Point_3D(0,37.3205,24.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,34.641), new Point_3D(10,20,34.641), new Point_3D(10,37.3205,24.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,34.641), new Point_3D(0,37.3205,24.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,37.3205,24.641), new Point_3D(0,17.3205,-10)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,37.3205,24.641), new Point_3D(10,20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,17.3205,-10), new Point_3D(10,37.3205,24.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,20,34.641), new Point_3D(0,20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(10,20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,17.3205,-10), new Point_3D(0,37.3205,24.641), new Point_3D(10,37.3205,24.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,17.3205,-10), new Point_3D(10,37.3205,24.641), new Point_3D(10,17.3205,-10)), shape, 0.0001));
    }

    @Test
    public void test_rotate_3() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,Math.PI / 6,0);
        shape.rotate(angle);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(8.66025,20,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,20,-5), new Point_3D(8.66025,0,-5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,34.641), new Point_3D(28.6603,20,29.641), new Point_3D(20,20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,34.641), new Point_3D(28.6603,0,29.641), new Point_3D(28.6603,20,29.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(20,0,34.641), new Point_3D(20,20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(20,20,34.641), new Point_3D(0,20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,-5), new Point_3D(28.6603,20,29.641), new Point_3D(28.6603,0,29.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,-5), new Point_3D(8.66025,20,-5), new Point_3D(28.6603,20,29.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(28.6603,0,29.641), new Point_3D(20,0,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,0,-5), new Point_3D(28.6603,0,29.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(20,20,34.641), new Point_3D(28.6603,20,29.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(28.6603,20,29.641), new Point_3D(8.66025,20,-5)), shape, 0.0001));
    }

    @Test
    public void test_rotate_4() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,-Math.PI / 6,0);
        shape.rotate(angle);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(8.66025,20,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,20,5), new Point_3D(8.66025,0,5)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,34.641), new Point_3D(-11.3397,20,39.641), new Point_3D(-20,20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,34.641), new Point_3D(-11.3397,0,39.641), new Point_3D(-11.3397,20,39.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-20,0,34.641), new Point_3D(-20,20,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-20,20,34.641), new Point_3D(0,20,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,5), new Point_3D(-11.3397,20,39.641), new Point_3D(-11.3397,0,39.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,0,5), new Point_3D(8.66025,20,5), new Point_3D(-11.3397,20,39.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-11.3397,0,39.641), new Point_3D(-20,0,34.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,0,5), new Point_3D(-11.3397,0,39.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(-20,20,34.641), new Point_3D(-11.3397,20,39.641)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(-11.3397,20,39.641), new Point_3D(8.66025,20,5)), shape, 0.0001));
    }

    @Test
    public void test_rotate_5() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,0,Math.PI / 6);
        shape.rotate(angle);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-10,17.3205,0), new Point_3D(-1.33975,22.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-1.33975,22.3205,0), new Point_3D(8.66025,5,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(-1.33975,22.3205,40), new Point_3D(-10,17.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(8.66025,5,40), new Point_3D(-1.33975,22.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,40), new Point_3D(-10,17.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-10,17.3205,40), new Point_3D(-10,17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,0), new Point_3D(-1.33975,22.3205,40), new Point_3D(8.66025,5,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,5,0), new Point_3D(-1.33975,22.3205,0), new Point_3D(-1.33975,22.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,5,40), new Point_3D(0,0,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,5,0), new Point_3D(8.66025,5,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,17.3205,0), new Point_3D(-10,17.3205,40), new Point_3D(-1.33975,22.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,17.3205,0), new Point_3D(-1.33975,22.3205,40), new Point_3D(-1.33975,22.3205,0)), shape, 0.0001));
    }

    @Test
    public void test_rotate_6() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,0,-Math.PI / 6);
        shape.rotate(angle);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,17.3205,0), new Point_3D(18.6603,12.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(18.6603,12.3205,0), new Point_3D(8.66025,-5,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(18.6603,12.3205,40), new Point_3D(10,17.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(8.66025,-5,40), new Point_3D(18.6603,12.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,40), new Point_3D(10,17.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,17.3205,40), new Point_3D(10,17.3205,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,-5,0), new Point_3D(18.6603,12.3205,40), new Point_3D(8.66025,-5,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.66025,-5,0), new Point_3D(18.6603,12.3205,0), new Point_3D(18.6603,12.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,-5,40), new Point_3D(0,0,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(8.66025,-5,0), new Point_3D(8.66025,-5,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,17.3205,0), new Point_3D(10,17.3205,40), new Point_3D(18.6603,12.3205,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,17.3205,0), new Point_3D(18.6603,12.3205,40), new Point_3D(18.6603,12.3205,0)), shape, 0.0001));
    }

    @Test
    public void test_rotate_7() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(Math.PI / 6,0,0);
        shape.rotate(angle, new Point_3D(50,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,-2.32051), new Point_3D(0,28.6603,7.67949), new Point_3D(10,28.6603,7.67949)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,-2.32051), new Point_3D(10,28.6603,7.67949), new Point_3D(10,11.3397,-2.32051)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,32.3205), new Point_3D(10,8.66025,42.3205), new Point_3D(0,8.66025,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,32.3205), new Point_3D(10,-8.66025,32.3205), new Point_3D(10,8.66025,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,-2.32051), new Point_3D(0,-8.66025,32.3205), new Point_3D(0,8.66025,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,-2.32051), new Point_3D(0,8.66025,42.3205), new Point_3D(0,28.6603,7.67949)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,11.3397,-2.32051), new Point_3D(10,8.66025,42.3205), new Point_3D(10,-8.66025,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,11.3397,-2.32051), new Point_3D(10,28.6603,7.67949), new Point_3D(10,8.66025,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,-2.32051), new Point_3D(10,-8.66025,32.3205), new Point_3D(0,-8.66025,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,-2.32051), new Point_3D(10,11.3397,-2.32051), new Point_3D(10,-8.66025,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,28.6603,7.67949), new Point_3D(0,8.66025,42.3205), new Point_3D(10,8.66025,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,28.6603,7.67949), new Point_3D(10,8.66025,42.3205), new Point_3D(10,28.6603,7.67949)), shape, 0.0001));
    }

    @Test
    public void test_rotate_8() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(-Math.PI / 6,0,0);
        shape.rotate(angle, new Point_3D(50,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,7.67949), new Point_3D(0,8.66025,-2.32051), new Point_3D(10,8.66025,-2.32051)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,7.67949), new Point_3D(10,8.66025,-2.32051), new Point_3D(10,-8.66025,7.67949)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,42.3205), new Point_3D(10,28.6603,32.3205), new Point_3D(0,28.6603,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,11.3397,42.3205), new Point_3D(10,11.3397,42.3205), new Point_3D(10,28.6603,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,7.67949), new Point_3D(0,11.3397,42.3205), new Point_3D(0,28.6603,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,7.67949), new Point_3D(0,28.6603,32.3205), new Point_3D(0,8.66025,-2.32051)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,-8.66025,7.67949), new Point_3D(10,28.6603,32.3205), new Point_3D(10,11.3397,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,-8.66025,7.67949), new Point_3D(10,8.66025,-2.32051), new Point_3D(10,28.6603,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,7.67949), new Point_3D(10,11.3397,42.3205), new Point_3D(0,11.3397,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-8.66025,7.67949), new Point_3D(10,-8.66025,7.67949), new Point_3D(10,11.3397,42.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,8.66025,-2.32051), new Point_3D(0,28.6603,32.3205), new Point_3D(10,28.6603,32.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,8.66025,-2.32051), new Point_3D(10,28.6603,32.3205), new Point_3D(10,8.66025,-2.32051)), shape, 0.0001));
    }

    @Test
    public void test_rotate_9() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,Math.PI / 6,0);
        shape.rotate(angle, new Point_3D(50,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,27.6795), new Point_3D(-3.30127,20,27.6795), new Point_3D(5.35898,20,22.6795)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,27.6795), new Point_3D(5.35898,20,22.6795), new Point_3D(5.35898,0,22.6795)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,62.3205), new Point_3D(25.359,20,57.3205), new Point_3D(16.6987,20,62.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,62.3205), new Point_3D(25.359,0,57.3205), new Point_3D(25.359,20,57.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,27.6795), new Point_3D(16.6987,0,62.3205), new Point_3D(16.6987,20,62.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,27.6795), new Point_3D(16.6987,20,62.3205), new Point_3D(-3.30127,20,27.6795)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.35898,0,22.6795), new Point_3D(25.359,20,57.3205), new Point_3D(25.359,0,57.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.35898,0,22.6795), new Point_3D(5.35898,20,22.6795), new Point_3D(25.359,20,57.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,27.6795), new Point_3D(25.359,0,57.3205), new Point_3D(16.6987,0,62.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,27.6795), new Point_3D(5.35898,0,22.6795), new Point_3D(25.359,0,57.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,20,27.6795), new Point_3D(16.6987,20,62.3205), new Point_3D(25.359,20,57.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,20,27.6795), new Point_3D(25.359,20,57.3205), new Point_3D(5.35898,20,22.6795)), shape, 0.0001));
    }

    @Test
    public void test_rotate_10() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,-Math.PI / 6,0);
        shape.rotate(angle, new Point_3D(50,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,-22.3205), new Point_3D(16.6987,20,-22.3205), new Point_3D(25.359,20,-17.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,-22.3205), new Point_3D(25.359,20,-17.3205), new Point_3D(25.359,0,-17.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,12.3205), new Point_3D(5.35898,20,17.3205), new Point_3D(-3.30127,20,12.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.30127,0,12.3205), new Point_3D(5.35898,0,17.3205), new Point_3D(5.35898,20,17.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,-22.3205), new Point_3D(-3.30127,0,12.3205), new Point_3D(-3.30127,20,12.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,-22.3205), new Point_3D(-3.30127,20,12.3205), new Point_3D(16.6987,20,-22.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(25.359,0,-17.3205), new Point_3D(5.35898,20,17.3205), new Point_3D(5.35898,0,17.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(25.359,0,-17.3205), new Point_3D(25.359,20,-17.3205), new Point_3D(5.35898,20,17.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,-22.3205), new Point_3D(5.35898,0,17.3205), new Point_3D(-3.30127,0,12.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,0,-22.3205), new Point_3D(25.359,0,-17.3205), new Point_3D(5.35898,0,17.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,20,-22.3205), new Point_3D(-3.30127,20,12.3205), new Point_3D(5.35898,20,17.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(16.6987,20,-22.3205), new Point_3D(5.35898,20,17.3205), new Point_3D(25.359,20,-17.3205)), shape, 0.0001));
    }

    @Test
    public void test_rotate_11() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,0,Math.PI / 6);
        shape.rotate(angle, new Point_3D(50,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,0), new Point_3D(1.69873,-6.33975,0), new Point_3D(10.359,-1.33975,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,0), new Point_3D(10.359,-1.33975,0), new Point_3D(20.359,-18.6603,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,40), new Point_3D(10.359,-1.33975,40), new Point_3D(1.69873,-6.33975,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,40), new Point_3D(20.359,-18.6603,40), new Point_3D(10.359,-1.33975,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,0), new Point_3D(11.6987,-23.6603,40), new Point_3D(1.69873,-6.33975,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,0), new Point_3D(1.69873,-6.33975,40), new Point_3D(1.69873,-6.33975,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20.359,-18.6603,0), new Point_3D(10.359,-1.33975,40), new Point_3D(20.359,-18.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20.359,-18.6603,0), new Point_3D(10.359,-1.33975,0), new Point_3D(10.359,-1.33975,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,0), new Point_3D(20.359,-18.6603,40), new Point_3D(11.6987,-23.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,-23.6603,0), new Point_3D(20.359,-18.6603,0), new Point_3D(20.359,-18.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,-6.33975,0), new Point_3D(1.69873,-6.33975,40), new Point_3D(10.359,-1.33975,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,-6.33975,0), new Point_3D(10.359,-1.33975,40), new Point_3D(10.359,-1.33975,0)), shape, 0.0001));
    }

    @Test
    public void test_rotate_12() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        Angle angle = new Angle(0,0,-Math.PI / 6);
        shape.rotate(angle, new Point_3D(50,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,0), new Point_3D(11.6987,43.6603,0), new Point_3D(20.359,38.6603,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,0), new Point_3D(20.359,38.6603,0), new Point_3D(10.359,21.3397,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,40), new Point_3D(20.359,38.6603,40), new Point_3D(11.6987,43.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,40), new Point_3D(10.359,21.3397,40), new Point_3D(20.359,38.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,0), new Point_3D(1.69873,26.3397,40), new Point_3D(11.6987,43.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,0), new Point_3D(11.6987,43.6603,40), new Point_3D(11.6987,43.6603,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.359,21.3397,0), new Point_3D(20.359,38.6603,40), new Point_3D(10.359,21.3397,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10.359,21.3397,0), new Point_3D(20.359,38.6603,0), new Point_3D(20.359,38.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,0), new Point_3D(10.359,21.3397,40), new Point_3D(1.69873,26.3397,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.69873,26.3397,0), new Point_3D(10.359,21.3397,0), new Point_3D(10.359,21.3397,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,43.6603,0), new Point_3D(11.6987,43.6603,40), new Point_3D(20.359,38.6603,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.6987,43.6603,0), new Point_3D(20.359,38.6603,40), new Point_3D(20.359,38.6603,0)), shape, 0.0001));
    }

    @Test
    public void test_rotate_13() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.rotate(Math.PI / 6, new Vector_3D(1,0,1), new Point_3D(-10,0,-10));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-7.07107,17.3205,7.07107), new Point_3D(2.25906,20.856,7.74094)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(2.25906,20.856,7.74094), new Point_3D(9.33013,3.53553,0.669873)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,-14.1421,37.3205), new Point_3D(4.93855,6.71391,45.0614), new Point_3D(-4.39158,3.17837,44.3916)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,-14.1421,37.3205), new Point_3D(12.0096,-10.6066,37.9904), new Point_3D(4.93855,6.71391,45.0614)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(2.67949,-14.1421,37.3205), new Point_3D(-4.39158,3.17837,44.3916)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-4.39158,3.17837,44.3916), new Point_3D(-7.07107,17.3205,7.07107)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,3.53553,0.669873), new Point_3D(4.93855,6.71391,45.0614), new Point_3D(12.0096,-10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,3.53553,0.669873), new Point_3D(2.25906,20.856,7.74094), new Point_3D(4.93855,6.71391,45.0614)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(12.0096,-10.6066,37.9904), new Point_3D(2.67949,-14.1421,37.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(9.33013,3.53553,0.669873), new Point_3D(12.0096,-10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.07107,17.3205,7.07107), new Point_3D(-4.39158,3.17837,44.3916), new Point_3D(4.93855,6.71391,45.0614)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.07107,17.3205,7.07107), new Point_3D(4.93855,6.71391,45.0614), new Point_3D(2.25906,20.856,7.74094)), shape, 0.0001));
    }

    @Test
    public void test_rotate_14() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.rotate(-Math.PI / 6, new Vector_3D(1,0,1), new Point_3D(-10,0,-10));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(7.07107,17.3205,-7.07107), new Point_3D(16.4012,13.785,-6.40119)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(16.4012,13.785,-6.40119), new Point_3D(9.33013,-3.53553,0.669873)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,14.1421,37.3205), new Point_3D(19.0807,27.9271,30.9193), new Point_3D(9.75056,31.4626,30.2494)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,14.1421,37.3205), new Point_3D(12.0096,10.6066,37.9904), new Point_3D(19.0807,27.9271,30.9193)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(2.67949,14.1421,37.3205), new Point_3D(9.75056,31.4626,30.2494)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(9.75056,31.4626,30.2494), new Point_3D(7.07107,17.3205,-7.07107)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,-3.53553,0.669873), new Point_3D(19.0807,27.9271,30.9193), new Point_3D(12.0096,10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,-3.53553,0.669873), new Point_3D(16.4012,13.785,-6.40119), new Point_3D(19.0807,27.9271,30.9193)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(12.0096,10.6066,37.9904), new Point_3D(2.67949,14.1421,37.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(9.33013,-3.53553,0.669873), new Point_3D(12.0096,10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.07107,17.3205,-7.07107), new Point_3D(9.75056,31.4626,30.2494), new Point_3D(19.0807,27.9271,30.9193)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.07107,17.3205,-7.07107), new Point_3D(19.0807,27.9271,30.9193), new Point_3D(16.4012,13.785,-6.40119)), shape, 0.0001));
    }

    @Test
    public void test_rotate_15() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.rotate(Math.PI / 6, new Vector_3D(1,0,1), new Point_3D(10,0,10));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-7.07107,17.3205,7.07107), new Point_3D(2.25906,20.856,7.74094)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(2.25906,20.856,7.74094), new Point_3D(9.33013,3.53553,0.669873)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,-14.1421,37.3205), new Point_3D(4.93855,6.71391,45.0614), new Point_3D(-4.39158,3.17837,44.3916)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,-14.1421,37.3205), new Point_3D(12.0096,-10.6066,37.9904), new Point_3D(4.93855,6.71391,45.0614)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(2.67949,-14.1421,37.3205), new Point_3D(-4.39158,3.17837,44.3916)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(-4.39158,3.17837,44.3916), new Point_3D(-7.07107,17.3205,7.07107)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,3.53553,0.669873), new Point_3D(4.93855,6.71391,45.0614), new Point_3D(12.0096,-10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,3.53553,0.669873), new Point_3D(2.25906,20.856,7.74094), new Point_3D(4.93855,6.71391,45.0614)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(12.0096,-10.6066,37.9904), new Point_3D(2.67949,-14.1421,37.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(9.33013,3.53553,0.669873), new Point_3D(12.0096,-10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.07107,17.3205,7.07107), new Point_3D(-4.39158,3.17837,44.3916), new Point_3D(4.93855,6.71391,45.0614)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.07107,17.3205,7.07107), new Point_3D(4.93855,6.71391,45.0614), new Point_3D(2.25906,20.856,7.74094)), shape, 0.0001));
    }

    @Test
    public void test_rotate_16() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.rotate(-Math.PI / 6, new Vector_3D(1,0,1), new Point_3D(10,0,10));
//        cout << "\n";
//        int count = 0;
//        for (Mesh_3D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "shape[" << count++ << "] facet p1 x: " << it->get_point1()->get_x() << " y: " <<
//                    it->get_point1()->get_y() << " z: " << it->get_point1()->get_z() << " p2 x: " << 
//                    it->get_point2()->get_x() << " y: " << it->get_point2()->get_y() << " z: " << 
//                    it->get_point2()->get_z() << " p3 x: " << it->get_point3()->get_x() << " y: " << 
//                    it->get_point3()->get_y() << " z: " << it->get_point3()->get_z() << "\n";
//        }
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(7.07107,17.3205,-7.07107), new Point_3D(16.4012,13.785,-6.40119)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(16.4012,13.785,-6.40119), new Point_3D(9.33013,-3.53553,0.669873)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,14.1421,37.3205), new Point_3D(19.0807,27.9271,30.9193), new Point_3D(9.75056,31.4626,30.2494)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.67949,14.1421,37.3205), new Point_3D(12.0096,10.6066,37.9904), new Point_3D(19.0807,27.9271,30.9193)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(2.67949,14.1421,37.3205), new Point_3D(9.75056,31.4626,30.2494)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(9.75056,31.4626,30.2494), new Point_3D(7.07107,17.3205,-7.07107)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,-3.53553,0.669873), new Point_3D(19.0807,27.9271,30.9193), new Point_3D(12.0096,10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.33013,-3.53553,0.669873), new Point_3D(16.4012,13.785,-6.40119), new Point_3D(19.0807,27.9271,30.9193)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(12.0096,10.6066,37.9904), new Point_3D(2.67949,14.1421,37.3205)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(9.33013,-3.53553,0.669873), new Point_3D(12.0096,10.6066,37.9904)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.07107,17.3205,-7.07107), new Point_3D(9.75056,31.4626,30.2494), new Point_3D(19.0807,27.9271,30.9193)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.07107,17.3205,-7.07107), new Point_3D(19.0807,27.9271,30.9193), new Point_3D(16.4012,13.785,-6.40119)), shape, 0.0001));
    }

    @Test
    public void test_scale_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.scale(2,1,1);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(20,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(20,20,0), new Point_3D(20,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(20,20,40), new Point_3D(0,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(20,0,40), new Point_3D(20,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,40), new Point_3D(0,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,40), new Point_3D(0,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0), new Point_3D(20,20,40), new Point_3D(20,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(20,0,0), new Point_3D(20,20,0), new Point_3D(20,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(20,0,40), new Point_3D(0,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(20,0,0), new Point_3D(20,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(0,20,40), new Point_3D(20,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(20,20,40), new Point_3D(20,20,0)), shape, precision));
    }

    @Test
    public void test_scale_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.scale(1,3,1);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,60,0), new Point_3D(10,60,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,60,0), new Point_3D(10,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(10,60,40), new Point_3D(0,60,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,40), new Point_3D(10,0,40), new Point_3D(10,60,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,40), new Point_3D(0,60,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,60,40), new Point_3D(0,60,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,60,40), new Point_3D(10,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,60,0), new Point_3D(10,60,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,40), new Point_3D(0,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(10,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,60,0), new Point_3D(0,60,40), new Point_3D(10,60,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,60,0), new Point_3D(10,60,40), new Point_3D(10,60,0)), shape, precision));
    }

    @Test
    public void test_scale_3() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.scale(1,1,0.5);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,0), new Point_3D(10,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,20,0), new Point_3D(10,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,20), new Point_3D(10,20,20), new Point_3D(0,20,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,20), new Point_3D(10,0,20), new Point_3D(10,20,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,20), new Point_3D(0,20,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,20,20), new Point_3D(0,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,20), new Point_3D(10,0,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,20,0), new Point_3D(10,20,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,20), new Point_3D(0,0,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(10,0,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(0,20,20), new Point_3D(10,20,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,0), new Point_3D(10,20,20), new Point_3D(10,20,0)), shape, precision));
    }

    @Test
    public void test_scale_4() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.scale(2,1,1, new Point_3D(5,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,0), new Point_3D(-5,20,0), new Point_3D(15,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,0), new Point_3D(15,20,0), new Point_3D(15,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,40), new Point_3D(15,20,40), new Point_3D(-5,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,40), new Point_3D(15,0,40), new Point_3D(15,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,0), new Point_3D(-5,0,40), new Point_3D(-5,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,0), new Point_3D(-5,20,40), new Point_3D(-5,20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(15,20,40), new Point_3D(15,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(15,20,0), new Point_3D(15,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,0), new Point_3D(15,0,40), new Point_3D(-5,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,0,0), new Point_3D(15,0,0), new Point_3D(15,0,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,20,0), new Point_3D(-5,20,40), new Point_3D(15,20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5,20,0), new Point_3D(15,20,40), new Point_3D(15,20,0)), shape, precision));
    }

    @Test
    public void test_scale_5() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.scale(1,3,1, new Point_3D(5,10,20));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,0), new Point_3D(0,40,0), new Point_3D(10,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,0), new Point_3D(10,40,0), new Point_3D(10,-20,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,40), new Point_3D(10,40,40), new Point_3D(0,40,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,40), new Point_3D(10,-20,40), new Point_3D(10,40,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,0), new Point_3D(0,-20,40), new Point_3D(0,40,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,0), new Point_3D(0,40,40), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,-20,0), new Point_3D(10,40,40), new Point_3D(10,-20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,-20,0), new Point_3D(10,40,0), new Point_3D(10,40,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,0), new Point_3D(10,-20,40), new Point_3D(0,-20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-20,0), new Point_3D(10,-20,0), new Point_3D(10,-20,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,40,0), new Point_3D(0,40,40), new Point_3D(10,40,40)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,40,0), new Point_3D(10,40,40), new Point_3D(10,40,0)), shape, precision));
    }

    @Test
    public void test_scale_6() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.scale(1,1,0.5, new Point_3D(5,10,20));
//        cout << gen_openscad_polyhedron(shape,4) << '\n';
//        int count=0;
//        for (Mesh_3D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
////            cout << "Facet p1 x: " << (*it)->get_point1().get_x() << " y: " << (*it)->get_point1().get_y()
////                    << " z: " << (*it)->get_point1().get_z() << " p2 x: " << (*it)->get_point2().get_x() 
////                    << " y: " << (*it)->get_point2().get_y() << " z: " << (*it)->get_point2().get_z()
////                    << " p3 x: " << (*it)->get_point3().get_x() << " y: " << (*it)->get_point3().get_y()
////                    << " z: " << (*it)->get_point3().get_z() << "\n";
//            cout << "    Assert.assertTrue(is_equal(*shape.at(" << count++ << "), Facet_3D(Point_3D(" <<
//                    (*it)->get_point1().get_x() << ',' << (*it)->get_point1().get_y() << ',' <<
//                    (*it)->get_point1().get_z() << "), Point_3D(" << (*it)->get_point2().get_x() << ',' <<
//                    (*it)->get_point2().get_y() << ',' << (*it)->get_point2().get_z() << "), Point_3D(" <<
//                    (*it)->get_point3().get_x() << ',' << (*it)->get_point3().get_y() << ',' <<
//                    (*it)->get_point3().get_z() << ")), precision));\n";
//        }
//        cout << "shape size: " << shape.size() << "\n";
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(0,20,10), new Point_3D(10,20,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(10,20,10), new Point_3D(10,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30), new Point_3D(10,20,30), new Point_3D(0,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,30), new Point_3D(10,0,30), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(0,0,30), new Point_3D(0,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(0,20,30), new Point_3D(0,20,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,10), new Point_3D(10,20,30), new Point_3D(10,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,10), new Point_3D(10,20,10), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(10,0,30), new Point_3D(0,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(10,0,10), new Point_3D(10,0,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,10), new Point_3D(0,20,30), new Point_3D(10,20,30)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,20,10), new Point_3D(10,20,30), new Point_3D(10,20,10)), shape, precision));
    }

    @Test
    public void test_translate_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.translate(5,10,20);
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(5,30,20), new Point_3D(15,30,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(15,30,20), new Point_3D(15,10,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,60), new Point_3D(15,30,60), new Point_3D(5,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,60), new Point_3D(15,10,60), new Point_3D(15,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(5,10,60), new Point_3D(5,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(5,30,60), new Point_3D(5,30,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,10,20), new Point_3D(15,30,60), new Point_3D(15,10,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,10,20), new Point_3D(15,30,20), new Point_3D(15,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(15,10,60), new Point_3D(5,10,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(15,10,20), new Point_3D(15,10,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,30,20), new Point_3D(5,30,60), new Point_3D(15,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,30,20), new Point_3D(15,30,60), new Point_3D(15,30,20)), shape, precision));
    }

    @Test
    public void test_translate_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.translate(new Vector_3D(5,10,20));
//        cout << gen_openscad_polyhedron(shape,4) << '\n';
//        int count=0;
//        for (Mesh_3D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
////            cout << "Facet p1 x: " << (*it)->get_point1().get_x() << " y: " << (*it)->get_point1().get_y()
////                    << " z: " << (*it)->get_point1().get_z() << " p2 x: " << (*it)->get_point2().get_x() 
////                    << " y: " << (*it)->get_point2().get_y() << " z: " << (*it)->get_point2().get_z()
////                    << " p3 x: " << (*it)->get_point3().get_x() << " y: " << (*it)->get_point3().get_y()
////                    << " z: " << (*it)->get_point3().get_z() << "\n";
//            cout << "    Assert.assertTrue(is_equal(*shape.at(" << count++ << "), Facet_3D(Point_3D(" <<
//                    (*it)->get_point1().get_x() << ',' << (*it)->get_point1().get_y() << ',' <<
//                    (*it)->get_point1().get_z() << "), Point_3D(" << (*it)->get_point2().get_x() << ',' <<
//                    (*it)->get_point2().get_y() << ',' << (*it)->get_point2().get_z() << "), Point_3D(" <<
//                    (*it)->get_point3().get_x() << ',' << (*it)->get_point3().get_y() << ',' <<
//                    (*it)->get_point3().get_z() << ")), precision));\n";
//        }
//        cout << "shape size: " << shape.size() << "\n";
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(5,30,20), new Point_3D(15,30,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(15,30,20), new Point_3D(15,10,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,60), new Point_3D(15,30,60), new Point_3D(5,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,60), new Point_3D(15,10,60), new Point_3D(15,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(5,10,60), new Point_3D(5,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(5,30,60), new Point_3D(5,30,20)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,10,20), new Point_3D(15,30,60), new Point_3D(15,10,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,10,20), new Point_3D(15,30,20), new Point_3D(15,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(15,10,60), new Point_3D(5,10,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,10,20), new Point_3D(15,10,20), new Point_3D(15,10,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,30,20), new Point_3D(5,30,60), new Point_3D(15,30,60)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,30,20), new Point_3D(15,30,60), new Point_3D(15,30,20)), shape, precision));
    }

    @Test
    public void test_move_1() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.move_x_pxy(new Point_3D(-20,0,0), new Vector_3D(0,0,1), new Point_3D(-19,0,0));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,0), new Point_3D(0,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,10), new Point_3D(-20,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(0,40,10), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(-20,40,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,0), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,40,0), new Point_3D(0,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,40,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,0,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,10), new Point_3D(-20,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,0,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,0), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,10), new Point_3D(0,0,10)), shape, precision));
    }

    @Test
    public void test_move_2() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.move_x_pxz(new Point_3D(-20,0,0), new Vector_3D(0,0,1), new Point_3D(-20,1,0));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,0), new Point_3D(0,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,10), new Point_3D(-20,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(0,40,10), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(-20,40,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,0), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,40,0), new Point_3D(0,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,40,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,0,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,10), new Point_3D(-20,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,0,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,0), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,10), new Point_3D(0,0,10)), shape, precision));
    }

    @Test
    public void test_move_3() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.move_y_pxy(new Point_3D(-20,0,0), new Vector_3D(1,0,0), new Point_3D(-20,0,1));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,0), new Point_3D(0,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,10), new Point_3D(-20,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(0,40,10), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(-20,40,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,0), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,40,0), new Point_3D(0,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,40,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,0,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,10), new Point_3D(-20,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,0,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,0), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,10), new Point_3D(0,0,10)), shape, precision));
    }

    @Test
    public void test_move_4() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.move_y_pyz(new Point_3D(-20,0,0), new Vector_3D(1,0,0), new Point_3D(-20,1,0));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,0), new Point_3D(0,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,10), new Point_3D(-20,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(0,40,10), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(-20,40,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,0), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,40,0), new Point_3D(0,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,40,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,0,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,10), new Point_3D(-20,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,0,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,0), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,10), new Point_3D(0,0,10)), shape, precision));
    }

    @Test
    public void test_move_5() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.move_z_pxz(new Point_3D(-20,0,0), new Vector_3D(0,1,0), new Point_3D(-20,0,1));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,0), new Point_3D(0,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,10), new Point_3D(-20,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(0,40,10), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(-20,40,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,0), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,40,0), new Point_3D(0,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,40,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,0,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,10), new Point_3D(-20,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,0,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,0), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,10), new Point_3D(0,0,10)), shape, precision));
    }

    @Test
    public void test_move_6() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,20,40);
        shape.move_z_pyz(new Point_3D(-20,0,0), new Vector_3D(0,1,0), new Point_3D(-19,0,0));
        Assert.assertTrue(shape.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,0), new Point_3D(0,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,0,10), new Point_3D(-20,0,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(0,40,10), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,40,0), new Point_3D(-20,40,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,0), new Point_3D(0,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(0,40,0), new Point_3D(0,0,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,40,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,10), new Point_3D(0,0,10), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,40,10), new Point_3D(-20,40,0)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-20,0,0), new Point_3D(-20,0,10), new Point_3D(-20,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,0), new Point_3D(0,40,10)), shape, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,40,10), new Point_3D(0,0,10)), shape, precision));
    }

    @Test
    public void test_move_7() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,40,2);
        // x 1,-1,0
        // y 0,0,1
        // z -1,-1,0
        shape.move_x_pxy(new Point_3D(0,0,20), new Vector_3D(1,-1,0), new Point_3D(0,0,21), new Point_3D(0,0,20));
        Assert.assertTrue(shape.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(0,-28.2843,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-4.14214,-24.1421,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
    }

    @Test
    public void test_move_8() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,40,2);
        // x 1,-1,0
        // y 0,0,1
        // z -1,-1,0
        shape.move_x_pxz(new Point_3D(0,0,20), new Vector_3D(1,-1,0), new Point_3D(-1,-1,20), new Point_3D(0,0,20));
        Assert.assertTrue(shape.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(0,-28.2843,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-4.14214,-24.1421,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
    }

    @Test
    public void test_move_9() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,40,2);
        // x 1,-1,0
        // y 0,0,1
        // z -1,-1,0
        shape.move_y_pxy(new Point_3D(0,0,20), new Vector_3D(0,0,1), new Point_3D(1,-1,20), new Point_3D(0,0,20));
        Assert.assertTrue(shape.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(0,-28.2843,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-4.14214,-24.1421,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
    }

    @Test
    public void test_move_10() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,40,2);
        // x 1,-1,0
        // y 0,0,1
        // z -1,-1,0
        shape.move_y_pyz(new Point_3D(0,0,20), new Vector_3D(0,0,1), new Point_3D(-1,-1,20), new Point_3D(0,0,20));
        Assert.assertTrue(shape.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(0,-28.2843,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-4.14214,-24.1421,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
    }

    @Test
    public void test_move_11() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,40,2);
        // x 1,-1,0
        // y 0,0,1
        // z -1,-1,0
        shape.move_z_pxz(new Point_3D(0,0,20), new Vector_3D(-1,-1,0), new Point_3D(1,-1,20), new Point_3D(0,0,20));
        Assert.assertTrue(shape.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(0,-28.2843,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-4.14214,-24.1421,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
    }

    @Test
    public void test_move_12() {
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,0,20,40,2);
        // x 1,-1,0
        // y 0,0,1
        // z -1,-1,0
        shape.move_z_pyz(new Point_3D(0,0,20), new Vector_3D(-1,-1,0), new Point_3D(0,0,21), new Point_3D(0,0,20));
//        cout << "\n";
//        int count = 0;
//        for (Mesh_3D::const_iterator it = shape.begin(); it != shape.end(); ++it)
//        {
//            cout << "shape[" << count++ << "] facet p1 x: " << it->get_point1()->get_x() << " y: " <<
//                    it->get_point1()->get_y() << " z: " << it->get_point1()->get_z() << " p2 x: " << 
//                    it->get_point2()->get_x() << " y: " << it->get_point2()->get_y() << " z: " << 
//                    it->get_point2()->get_z() << " p3 x: " << it->get_point3()->get_x() << " y: " << 
//                    it->get_point3()->get_y() << " z: " << it->get_point3()->get_z() << "\n";
//        }
        Assert.assertTrue(shape.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(0,-28.2843,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-4.14214,-24.1421,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,40), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-14.1421,-14.1421,40)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,34.1421), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,34.1421)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-28.2843,0,20), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-28.2843,0,20)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-24.1421,-4.14214,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-24.1421,-4.14214,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-28.2843,20), new Point_3D(-14.1421,-14.1421,0), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(-4.14214,-24.1421,5.85786), new Point_3D(-14.1421,-14.1421,0)), shape, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(14.1421,14.1421,20), new Point_3D(0,-28.2843,20), new Point_3D(-4.14214,-24.1421,5.85786)), shape, 0.0001));
    }
}
