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
 * File: Test_Vector_3D.java
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
public class Test_Vector_3D extends TestBase {
    
    public Test_Vector_3D() {
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
        Vector_3D v = new Vector_3D(5,6,7);
        Assert.assertTrue(v.get_x() == 5);
        Assert.assertTrue(v.get_y() == 6);
        Assert.assertTrue(v.get_z() == 7);
        v = new Vector_3D(5,5,5);
        Assert.assertTrue(within_round(v.length(), 8.660254, 0.0001));
        v.normalize();
        Assert.assertTrue(within_round(v.length(), 1, 0.0001));
        Assert.assertTrue(within_round(v.get_x(), 0.577350, 0.0001));
        Assert.assertTrue(within_round(v.get_y(), 0.577350, 0.0001));
        Assert.assertTrue(within_round(v.get_z(), 0.577350, 0.0001));
        // cross product
        v = new Vector_3D(5,5,5);
        Vector_3D v2 = new Vector_3D(-1, 1, 1);
        Vector_3D v3 = Vector_3D.cross_product(v, v2);
        Assert.assertTrue(within_round(v3.get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), -10, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 10, 0.0001));
        v = new Vector_3D(3, -1, 1);
        v2 = new Vector_3D(1, 2, -1);
        v3 = Vector_3D.cross_product(v, v2);
        Assert.assertTrue(within_round(v3.get_x(), -1, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), 4, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 7, 0.0001));
        // dot product
        v = new Vector_3D(3, 1, -2);
        v2 = new Vector_3D(1, -1, 1);
        Assert.assertTrue(Vector_3D.dot_product(v, v2) == 0);
        v = new Vector_3D(2, 1, -1);
        v2 = new Vector_3D(0, -2, 3);
        Assert.assertTrue(Vector_3D.dot_product(v, v2) == -5);
        // angle between
        v = new Vector_3D(5, 5, 0);
        v2 = new Vector_3D(1, 0, 0);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), Math.PI / 4, 0.0001));
        v2 = new Vector_3D(0, 1, 0);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), Math.PI / 4, 0.0001));
        v2 = new Vector_3D(1, 0, 0);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), Math.PI / 4, 0.0001));
        v2 = new Vector_3D(0, -1, 0);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), 3 * Math.PI / 4, 0.0001));
        v2 = new Vector_3D(-1, 0, 0);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), 3 * Math.PI / 4, 0.0001));
        v2 = new Vector_3D(1, -1, 0);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), Math.PI / 2, 0.0001));
        v = new Vector_3D(5, 5, 5);
        v2 = new Vector_3D(1, 1, 1);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), 0, 0.0001));
        v2 = new Vector_3D(-1, -1, -1);
        Assert.assertTrue(within_round(Vector_3D.angle_between(v, v2), Math.PI, 0.0001));
        // orthogonal projection
        v = new Vector_3D(5, 5, 5);
        v2 = new Vector_3D(1, 0, 0);
        v3 = Vector_3D.orthogonal_projection(v, v2);
        Assert.assertTrue(within_round(v3.get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 0, 0.0001));
        v2 = new Vector_3D(0, 1, 0);
        v3 = Vector_3D.orthogonal_projection(v, v2);
        Assert.assertTrue(within_round(v3.get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 0, 0.0001));
        v2 = new Vector_3D(0, 0, 1);
        v3 = Vector_3D.orthogonal_projection(v, v2);
        Assert.assertTrue(within_round(v3.get_x(), 0, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), 0, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 5, 0.0001));
        // add
        v2 = new Vector_3D(1,2,3);
        v.plus(v2);
        Assert.assertTrue(within_round(v.get_x(), 6, 0.0001));
        Assert.assertTrue(within_round(v.get_y(), 7, 0.0001));
        Assert.assertTrue(within_round(v.get_z(), 8, 0.0001));
        v2 = new Vector_3D(-1, -2, -3);
        v3 = Vector_3D.plus(v, v2);
        Assert.assertTrue(within_round(v3.get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 5, 0.0001));
        // subtract
        v2 = new Vector_3D(1,2,3);
        v.minus(v2);
        Assert.assertTrue(within_round(v.get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(v.get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(v.get_z(), 5, 0.0001));
        v2 = new Vector_3D(-1, -2, -3);
        v3 = Vector_3D.minus(v, v2);
        Assert.assertTrue(within_round(v3.get_x(), 6, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), 7, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 8, 0.0001));
        // multiply
        v.scale(2);
        Assert.assertTrue(within_round(v.get_x(), 10, 0.0001));
        Assert.assertTrue(within_round(v.get_y(), 10, 0.0001));
        Assert.assertTrue(within_round(v.get_z(), 10, 0.0001));
        v3 = Vector_3D.scale(v, 0.5);
        Assert.assertTrue(within_round(v3.get_x(), 5, 0.0001));
        Assert.assertTrue(within_round(v3.get_y(), 5, 0.0001));
        Assert.assertTrue(within_round(v3.get_z(), 5, 0.0001));
//        System.out.println("x: " + v.get_x() + " y: " + v.get_y() + " z: " + v.get_z());
    }
    
    @Test
    public void test_is_pt_on_vector() {
        Point_3D o = new Point_3D(2, 1, 0);
        Point_3D ep = new Point_3D(7, 6, 5);
        // test origin point
        Point_3D p = new Point_3D(2, 1, 0);
        Assert.assertTrue(Vector_3D.is_pt_on_vector(p, o, ep, precision));
        // test end point
        p = new Point_3D(7, 6, 5);
        Assert.assertTrue(Vector_3D.is_pt_on_vector(p, o, ep, precision));
        // test middle point
        p = new Point_3D(4.5, 3.5, 2.5);
        Assert.assertTrue(Vector_3D.is_pt_on_vector(p, o, ep, precision));
        // test points not on vector
        p = new Point_3D(1, 0, 0);
        Assert.assertTrue(!Vector_3D.is_pt_on_vector(p, o, ep, precision));
        p = new Point_3D(8, 7, 6);
        Assert.assertTrue(!Vector_3D.is_pt_on_vector(p, o, ep, precision));
        p = new Point_3D(5, 5, 5);
        Assert.assertTrue(!Vector_3D.is_pt_on_vector(p, o, ep, precision));
        p = new Point_3D(5, 9, -3);
        Assert.assertTrue(!Vector_3D.is_pt_on_vector(p, o, ep, precision));
    }
    
    @Test
    public void test_intersect_vectors_3d() {
        // test vector
        Point_3D tv_start = new Point_3D(2, 1, 1);
        Point_3D tv_end = new Point_3D(7, 6, 6);
//        Vector_3D tv_end(5, 5, 5);
        // vector that crosses in the middle
        Point_3D v_start = new Point_3D(6, 1, 1);
        Point_3D v_end = new Point_3D(1, 6, 6);
        Intersection_Data_3D idata = new Intersection_Data_3D();
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), new Point_3D(4,3,3), precision));
        // vector crosses at origin
        v_start = new Point_3D(4, -1, -1);
        v_end = new Point_3D(-1,4,4);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_start, precision));
        // vector crosses at tip of tv
        v_start = new Point_3D(9, 4, 4);
        v_end = new Point_3D(4,9,9);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), new Point_3D(7,6,6), precision));
        // both vectors start at origin
        v_end = new Point_3D(-3, 6, 6);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, tv_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_start, precision));
        // vector starts at end point
        v_start = new Point_3D(7, 6, 6);
        v_end = new Point_3D(4, 9, 9);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_start, precision));
        // test vector
//        tv = Vector_3D(0, 5, 0);
        tv_start = new Point_3D(0, 1, 0);
        tv_end = new Point_3D(0, 6, 0);
        // vector in same line does not meet
        v_start = new Point_3D(0, 0, 0);
        v_end = new Point_3D(0, -5, 0);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        // vector in same line does not meet
        v_start = new Point_3D(0, 12, 0);
        v_end = new Point_3D(0,7,0);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        // vector in same line meets at endpoint of v1
        v_start = new Point_3D(0, 11, 0);
        v_end = new Point_3D(0, 6, 0);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_end, precision));
        // vector in same line meets at origin of v1
        v_start = new Point_3D(0, -2, 0);
        v_end = new Point_3D(0, 1, 0);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_start, precision));
        // vector meets at tip of v2 and middle v1
        v_start = new Point_3D(-3, 1, 0);
        v_end = new Point_3D(0, 4, 0);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), new Point_3D(0,4,0), precision));
        // vectors do not meet
        v_start = new Point_3D(-3.1, 1, 0);
        v_end = new Point_3D(-0.1, 4, 0);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        // reverse vector could intersect
        tv_start = new Point_3D(0.25, 0.75, 0.5);
        tv_end = new Point_3D(0, 0, 0);
        v_start = new Point_3D(0, 10, 4);
        v_end = new Point_3D(5, 0, 0);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        // result is not quite on original vector
        tv_start = new Point_3D(4, 3, 0);
        tv_end = new Point_3D(10, 0, 0);
        v_start = new Point_3D(7, 1, 0);
        v_end = new Point_3D(12, 2, 0);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), new Point_3D(7.71429,1.14286,0), 0.0001));
        tv_start = new Point_3D(5, 5, 5);
        tv_end = new Point_3D(1, 1, 1);
        v_start = new Point_3D(3, 3, 3);
        v_end = new Point_3D(0, 0, 0);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_start, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), tv_end, precision));
//        tv = Vector_3D(4, 4, 4);
        tv_start = new Point_3D(1, 1, 1);
        tv_end = new Point_3D(5, 5, 5);
        v_start = new Point_3D(0, 0, 0);
        v_end = new Point_3D(3, 3, 3);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), new Point_3D(1,1,1), precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), v_end, precision));
        v_start = new Point_3D(5.1, 5.1, 5.1);
        v_end = new Point_3D(8.1, 8.1, 8.1);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        v_start = new Point_3D(0.5, 0.5, 0.5);
        v_end = new Point_3D(7.5, 7.5, 7.5);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_start, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), new Point_3D(5,5,5), precision));
        v_start = new Point_3D(1.1, 1.1, 1.1);
        v_end = new Point_3D(3.1, 3.1, 3.1);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_start, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), v_end, precision));
        v_start = new Point_3D(-1, -1, -1);
        v_end = new Point_3D(1,1,1);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_end, precision));
        v_start = new Point_3D(5, 5, 5);
        v_end = new Point_3D(7,7,7);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_start, precision));
        v_start = new Point_3D(5.1, 5.1, 5.1);
        v_end = new Point_3D(7.1, 7.1, 7.1);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        v_start = new Point_3D(-1.1, -1.1, -1.1);
        v_end = new Point_3D(0.9, 0.9, 0.9);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        v_start = new Point_3D(0, 0, 0);
        v_end = new Point_3D(2,2,2);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_start, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), v_end, precision));
        v_start = new Point_3D(3, 3, 3);
        v_end = new Point_3D(5,5,5);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_start, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), v_end, precision));
        v_start = new Point_3D(0.9, 0.9, 0.9);
        v_end = new Point_3D(-1.1, -1.1, -1.1);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        v_start = new Point_3D(7.1, 7.1, 7.1);
        v_end = new Point_3D(5.1, 5.1, 5.1);
        Assert.assertTrue(!Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        v_start = new Point_3D(7, 7, 7);
        v_end = new Point_3D(5,5,5);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_end, precision));
        v_start = new Point_3D(1, 1, 1);
        v_end = new Point_3D(-1,-1,-1);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_start, precision));
        v_start = new Point_3D(2, 2, 2);
        v_end = new Point_3D(0,0,0);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_start, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), v_start, precision));
        v_start = new Point_3D(6, 6, 6);
        v_end = new Point_3D(4,4,4);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_end, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), tv_end, precision));
        v_start = new Point_3D(0, 0, 0);
        v_end = new Point_3D(7, 7, 7);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 2);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), tv_start, precision));
        Assert.assertTrue(Point_3D.is_equal(idata.get_p2(), tv_end, precision));
        tv_start = new Point_3D(0,1,1);
        tv_end = new Point_3D(0,-1,-1);
        v_start = new Point_3D(0,0,0);
        v_end = new Point_3D(0,10,0);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), v_start, precision));
        tv_start = new Point_3D(-1, 0, 3);
        tv_end = new Point_3D(1, 0, 3);
        v_start = new Point_3D(0, 0, 0);
        v_end = new Point_3D(0, 0, 5);
        Assert.assertTrue(Vector_3D.intersect_vectors(tv_start, tv_end, v_start, v_end, idata, precision));
        Assert.assertTrue(idata.get_num() == 1);
        Assert.assertTrue(Point_3D.is_equal(idata.get_p1(), new Point_3D(0, 0, 3), precision));
//        cout << "tv x: " << tv.get_x() << " y: " << tv.get_y() << " z: " << tv.get_z() << "\n";
//        cout << "tvo x: " << tvO.get_x() << " y: " << tvO.get_y() << " z: " << tvO.get_z() << "\n";
//        cout << "v x: " << v.get_x() << " y: " << v.get_y() << " z: " << v.get_z() << "\n";
//        cout << "o x: " << o.get_x() << " y: " << o.get_y() << " z: " << o.get_z() << "\n";
//        cout << "idata num: " << idata.num << "\n";
//        if (idata.num > 0)
//            cout << "p1 x: " << idata.p1.get_x() << " y: " << idata.p1.get_y() << " z: " << idata.p1.get_z() << "\n";
//        if (idata.num == 2)
//            cout << "p2 x: " << idata.p2.get_x() << " y: " << idata.p2.get_y() << " z: " << idata.p2.get_z() << "\n";
    }
    
}
