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
 * File: Test_Point_3D.java
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
public class Test_Point_3D extends TestBase {
    
    public Test_Point_3D() {
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
    //
    // @Test
    // public void hello() {}
    @Test
    public void test() {
        Point_3D p = new Point_3D(5, 6, 7);
        // test get
        Assert.assertTrue(p.get_x() == 5);
        Assert.assertTrue(p.get_y() == 6);
        Assert.assertTrue(p.get_z() == 7);
        // test r, theta, p, and phi
        p = new Point_3D(4,4,4);
        Assert.assertTrue(within_round(p.get_r(), 5.65685, 0.0001));
        Assert.assertTrue(within_round(p.get_theta(), 0.785398, 0.0001));
        Assert.assertTrue(within_round(p.get_p(), 6.9282, 0.0001));
        Assert.assertTrue(within_round(p.get_phi(), 0.955317, 0.0001));
        p = new Point_3D(4,4,-4);
        Assert.assertTrue(within_round(p.get_r(), 5.65685, 0.0001));
        Assert.assertTrue(within_round(p.get_theta(), 0.785398, 0.0001));
        Assert.assertTrue(within_round(p.get_p(), 6.9282, 0.0001));
        Assert.assertTrue(within_round(p.get_phi(), 2.18628, 0.0001));
        // test r, theta, p, and phi with a different origin
        p = new Point_3D(0,0,4);
        Point_3D p2 = new Point_3D(4,4,0);
        Assert.assertTrue(within_round(p.get_r(p2), 5.65685, 0.0001));
        Assert.assertTrue(within_round(p.get_theta(p2), 3.92699, 0.0001));
        Assert.assertTrue(within_round(p.get_p(p2), 6.9282, 0.0001));
        Assert.assertTrue(within_round(p.get_phi(p2), 0.955317, 0.0001));
        p2 = new Point_3D(4,4,8);
        Assert.assertTrue(within_round(p.get_r(p2), 5.65685, 0.0001));
        Assert.assertTrue(within_round(p.get_theta(p2), 3.92699, 0.0001));
        Assert.assertTrue(within_round(p.get_p(p2), 6.9282, 0.0001));
        Assert.assertTrue(within_round(p.get_phi(p2), 2.18628, 0.0001));
        // test translate
        p.translate(5,6,3);
        Assert.assertTrue(p.get_x() == 5);
        Assert.assertTrue(p.get_y() == 6);
        Assert.assertTrue(p.get_z() == 7);
        Vector_3D v = new Vector_3D(-3, 2, -1);
        p.translate(v);
        Assert.assertTrue(p.get_x() == 2);
        Assert.assertTrue(p.get_y() == 8);
        Assert.assertTrue(p.get_z() == 6);
        // test scale by 2
        p = new Point_3D(2,8,6);
        p.scale(2,2,2);
        Assert.assertTrue(p.get_x() == 4);
        Assert.assertTrue(p.get_y() == 16);
        Assert.assertTrue(p.get_z() == 12);
        p.scale(0.5,0.5,0.5);
        Assert.assertTrue(p.get_x() == 2);
        Assert.assertTrue(p.get_y() == 8);
        Assert.assertTrue(p.get_z() == 6);
        p.scale(2, 0.5, 3);
        Assert.assertTrue(p.get_x() == 4);
        Assert.assertTrue(p.get_y() == 4);
        Assert.assertTrue(p.get_z() == 18);
        p.scale(2, 3, 0.5);
        Assert.assertTrue(p.get_x() == 8);
        Assert.assertTrue(p.get_y() == 12);
        Assert.assertTrue(p.get_z() == 9);
        // test rotate 45 degrees in x, y, and z separately
        Angle a = new Angle(Math.PI / 4, 0,0);
        p = new Point_3D(8,8,8);
        p.rotate(a);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 11.3137, 0.0001));
        a.set_x(-Math.PI / 4);
        p.rotate(a);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_x(0);
        a.set_y(Math.PI / 4);
        p.rotate(a);
        Assert.assertTrue(within_round(p.get_x(), 11.3137, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 0, 0.0001));
        a.set_y(-Math.PI / 4);
        p.rotate(a);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_y(0);
        a.set_z(Math.PI / 4);
        p.rotate(a);
        Assert.assertTrue(within_round(p.get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 11.3137, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_z(-Math.PI / 4);
        p.rotate(a);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_z(0);
        // test rotate 90 degrees about a different origin
        a.set_x(Math.PI / 2);
        Point_3D o = new Point_3D(-2,-2,-2);
        p.rotate(a, o);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -12, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_x(-Math.PI / 2);
        p.rotate(a, o);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_x(0);
        a.set_y(Math.PI / 2);
        p.rotate(a, o);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), -12, 0.0001));
        a.set_y(-Math.PI / 2);
        p.rotate(a, o);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_y(0);
        a.set_z(Math.PI / 2);
        p.rotate(a, o);
        Assert.assertTrue(within_round(p.get_x(), -12, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        a.set_z(-Math.PI / 2);
        p.rotate(a, o);
        Assert.assertTrue(within_round(p.get_x(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 8, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        // test multiplication
        p = new Point_3D(8,8,8);
        o = Point_3D.scale(p, 0.5);
        Assert.assertTrue(within_round(o.get_x(), 4, 0.0001));
        Assert.assertTrue(within_round(o.get_y(), 4, 0.0001));
        Assert.assertTrue(within_round(o.get_z(), 4, 0.0001));
        o = Point_3D.scale(p,2);
        Assert.assertTrue(within_round(o.get_x(), 16, 0.0001));
        Assert.assertTrue(within_round(o.get_y(), 16, 0.0001));
        Assert.assertTrue(within_round(o.get_z(), 16, 0.0001));
        // test addition
        v = new Vector_3D(1,2,-3);
        o = Point_3D.translate(p, v);
        Assert.assertTrue(within_round(o.get_x(), 9, 0.0001));
        Assert.assertTrue(within_round(o.get_y(), 10, 0.0001));
        Assert.assertTrue(within_round(o.get_z(), 5, 0.0001));
        // cylindrical point
        p = Point_3D.cylindrical_point(5, Math.PI / 6, 5);
        Assert.assertTrue(within_round(p.get_x(), 4.330127, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 2.5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
        p = Point_3D.cylindrical_point(5, -Math.PI / 6, 5);
        Assert.assertTrue(within_round(p.get_x(), 4.330127, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -2.5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
        // test cylindrical point from different origin
        p2 = new Point_3D(1,2,3);
        p = Point_3D.cylindrical_point(5, Math.PI / 6, 5, p2);
        Assert.assertTrue(within_round(p.get_x(), 5.330127, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 4.5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        p = Point_3D.cylindrical_point(5, -Math.PI / 6, 5, p2);
        Assert.assertTrue(within_round(p.get_x(), 5.330127, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -0.5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 8, 0.0001));
        // spherical point
        p = Point_3D.spherical_point(5, Math.PI / 6, Math.PI / 3);
        Assert.assertTrue(within_round(p.get_x(), 3.75, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 2.16506, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 2.5, 0.0001));
        p = Point_3D.spherical_point(5, -Math.PI / 6, 2*Math.PI / 3);
        Assert.assertTrue(within_round(p.get_x(), 3.75, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -2.16506, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), -2.5, 0.0001));
        // test spherical point from different origin
        p2 = new Point_3D(1,2,3);
        p = Point_3D.spherical_point(5, Math.PI / 6, Math.PI / 3, p2);
        Assert.assertTrue(within_round(p.get_x(), 4.75, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), 4.16506, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5.5, 0.0001));
        p = Point_3D.spherical_point(5, -Math.PI / 6, 2*Math.PI / 3, p2);
        Assert.assertTrue(within_round(p.get_x(), 4.75, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -0.16506, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 0.5, 0.0001));
        // test move
        p = new Point_3D(1,0,1);
        p.move_x_pxy(new Point_3D(-5,-5,5), new Vector_3D(0,1,0), new Point_3D(-4,-5,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -4, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 4, 0.0001));
        p = new Point_3D(1,0,1);
        p.move_x_pxz(new Point_3D(-5,-5,5), new Vector_3D(0,1,0), new Point_3D(-5,-5,4));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -4, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 4, 0.0001));
        p = new Point_3D(1,0,1);
        p.move_y_pxy(new Point_3D(-5,-5,5), new Vector_3D(1,0,0), new Point_3D(-5,-4,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -4, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 4, 0.0001));
        p = new Point_3D(1,0,1);
        p.move_y_pyz(new Point_3D(-5,-5,5), new Vector_3D(1,0,0), new Point_3D(-5,-5,4));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -4, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 4, 0.0001));
        p = new Point_3D(1,0,1);
        p.move_z_pxz(new Point_3D(-5,-5,5), new Vector_3D(0,0,-1), new Point_3D(-5,-4,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -4, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 4, 0.0001));
        p = new Point_3D(1,0,1);
        p.move_z_pyz(new Point_3D(-5,-5,5), new Vector_3D(0,0,-1), new Point_3D(-4,-5,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -4, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 4, 0.0001));
        p = new Point_3D(0,0,0);
        p.move_x_pxy(new Point_3D(-5,-5,5), new Vector_3D(0,1,0), new Point_3D(-4,-5,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
        p = new Point_3D(0,0,0);
        p.move_x_pxz(new Point_3D(-5,-5,5), new Vector_3D(0,1,0), new Point_3D(-5,-5,4));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
        p = new Point_3D(0,0,0);
        p.move_y_pxy(new Point_3D(-5,-5,5), new Vector_3D(1,0,0), new Point_3D(-5,-4,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
        p = new Point_3D(0,0,0);
        p.move_y_pyz(new Point_3D(-5,-5,5), new Vector_3D(1,0,0), new Point_3D(-5,-5,4));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
        p = new Point_3D(0,0,0);
        p.move_z_pxz(new Point_3D(-5,-5,5), new Vector_3D(0,0,-1), new Point_3D(-5,-4,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
        p = new Point_3D(0,0,0);
        p.move_z_pyz(new Point_3D(-5,-5,5), new Vector_3D(0,0,-1), new Point_3D(-4,-5,5));
        Assert.assertTrue(within_round(p.get_x(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_y(), -5, 0.0001));
        Assert.assertTrue(within_round(p.get_z(), 5, 0.0001));
//        System.out.println("p x: " + p.get_x() + " y: " + p.get_y() + " z: " + p.get_z());
    }
}
