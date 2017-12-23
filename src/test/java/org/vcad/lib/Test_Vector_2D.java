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
 * File: Test_Vector_2D.java
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
public class Test_Vector_2D extends TestBase {
    
    public Test_Vector_2D() {
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
    public void test_Vector_2D() {
        // test Vector_2D object
        Vector_2D v1 = new Vector_2D(5,6);
        // test get
        Assert.assertTrue(v1.get_x() == 5);
        Assert.assertTrue(v1.get_y() == 6);
        // test length
        Assert.assertTrue(within_round(v1.length(), 7.8102, precision));
        // test normalize
        v1.normalize();
        Assert.assertTrue(within_round(v1.get_x(), 0.640184, precision));
        Assert.assertTrue(within_round(v1.get_y(), 0.768221, precision));
        // test vector constructors
        Point_2D p1 = new Point_2D(6,5);
        v1 = new Vector_2D(p1);
        Assert.assertTrue(v1.get_x() == 6);
        Assert.assertTrue(v1.get_y() == 5);
        Point_2D p2 = new Point_2D(5,6);
        v1 = new Vector_2D(p2, p1);
        Assert.assertTrue(v1.get_x() == 1);
        Assert.assertTrue(v1.get_y() == -1);
        // test +=
        Vector_2D v2 = new Vector_2D(1,0);
        v1 = new Vector_2D(3,4);
        v1.plus(v2);
        Assert.assertTrue(within_round(v1.get_x(), 4, precision));
        Assert.assertTrue(within_round(v1.get_y(), 4, precision));
        v2 = new Vector_2D(-3, 4);
        v1.plus(v2);
        Assert.assertTrue(within_round(v1.get_x(), 1, precision));
        Assert.assertTrue(within_round(v1.get_y(), 8, precision));
        // test -=
        v1.minus(v2);
        Assert.assertTrue(within_round(v1.get_x(), 4, precision));
        Assert.assertTrue(within_round(v1.get_y(), 4, precision));
        v2 = new Vector_2D(2,-1);
        v1.minus(v2);
        Assert.assertTrue(within_round(v1.get_x(), 2, precision));
        Assert.assertTrue(within_round(v1.get_y(), 5, precision));
        // test *=
        v1.scale(2);
        Assert.assertTrue(within_round(v1.get_x(), 4, precision));
        Assert.assertTrue(within_round(v1.get_y(), 10, precision));
        v1.scale(0.5);
        Assert.assertTrue(within_round(v1.get_x(), 2, precision));
        Assert.assertTrue(within_round(v1.get_y(), 5, precision));
        v1.scale(-2);
        Assert.assertTrue(within_round(v1.get_x(), -4, precision));
        Assert.assertTrue(within_round(v1.get_y(), -10, precision));
        // test cross product
        v1 = new Vector_2D(5,5);
        v2 = new Vector_2D(0,1);
        Assert.assertTrue(within_round(Vector_2D.cross_product(v1, v2), 5, precision));
        v2 = new Vector_2D(1,0);
        Assert.assertTrue(within_round(Vector_2D.cross_product(v1, v2), -5, precision));
        // test dot product
        Assert.assertTrue(within_round(Vector_2D.dot_product(v1, v2), 5, precision));
        v2 = new Vector_2D(0,1);
        Assert.assertTrue(within_round(Vector_2D.dot_product(v1, v2), 5, precision));
        v2 = new Vector_2D(-1,0);
        Assert.assertTrue(within_round(Vector_2D.dot_product(v1, v2), -5, precision));
        v2 = new Vector_2D(0,-1);
        Assert.assertTrue(within_round(Vector_2D.dot_product(v1, v2), -5, precision));
        v2 = new Vector_2D(1,1);
        Assert.assertTrue(within_round(Vector_2D.dot_product(v1, v2), 10, precision));
        v2 = new Vector_2D(-1,-1);
        Assert.assertTrue(within_round(Vector_2D.dot_product(v1, v2), -10, precision));
        // test angle between
        v2 = new Vector_2D(1,0);
        Assert.assertTrue(within_round(Vector_2D.angle_between(v1, v2), Math.PI / 4, precision));
        v2 = new Vector_2D(0,1);
        Assert.assertTrue(within_round(Vector_2D.angle_between(v1, v2), Math.PI / 4, precision));
        v2 = new Vector_2D(-1,0);
        Assert.assertTrue(within_round(Vector_2D.angle_between(v1, v2), 3 * Math.PI / 4, precision));
        v2 = new Vector_2D(0,-1);
        Assert.assertTrue(within_round(Vector_2D.angle_between(v1, v2), 3 * Math.PI / 4, precision));
        v2 = new Vector_2D(1,1);
        Assert.assertTrue(within_round(Vector_2D.angle_between(v1, v2), 0, precision));
        v2 = new Vector_2D(-1,-1);
        Assert.assertTrue(within_round(Vector_2D.angle_between(v1, v2), Math.PI, precision));
        // test +
        v2 = new Vector_2D(-1,1);
        Vector_2D v3 = Vector_2D.plus(v1, v2);
        Assert.assertTrue(within_round(v3.get_x(), 4, precision));
        Assert.assertTrue(within_round(v3.get_y(), 6, precision));
        v2 = new Vector_2D(1, -1);
        v3 = Vector_2D.plus(v1, v2);
        Assert.assertTrue(within_round(v3.get_x(), 6, precision));
        Assert.assertTrue(within_round(v3.get_y(), 4, precision));
        // test -
        v3 = Vector_2D.minus(v1, v2);
        Assert.assertTrue(within_round(v3.get_x(), 4, precision));
        Assert.assertTrue(within_round(v3.get_y(), 6, precision));
        v2 = new Vector_2D(-1, 1);
        v3 = Vector_2D.minus(v1, v2);
        Assert.assertTrue(within_round(v3.get_x(), 6, precision));
        Assert.assertTrue(within_round(v3.get_y(), 4, precision));
        // test *
        v3 = Vector_2D.scale(v1, 2);
        Assert.assertTrue(within_round(v3.get_x(), 10, precision));
        Assert.assertTrue(within_round(v3.get_y(), 10, precision));
        v3 = Vector_2D.scale(v1, -0.5);
        Assert.assertTrue(within_round(v3.get_x(), -2.5, precision));
        Assert.assertTrue(within_round(v3.get_y(), -2.5, precision));
    }

    @Test
    public void test_is_pt_on_vector() {
        Vector_2D v = new Vector_2D(5,5);
        Point_2D o = new Point_2D(2,1);
        // test origin
        Point_2D p = new Point_2D(2,1);
        Assert.assertTrue(Vector_2D.is_pt_on_vector(p,o,Point_2D.translate(o, v), precision));
        // test end point
        p = new Point_2D(7,6);
        Assert.assertTrue(Vector_2D.is_pt_on_vector(p,o,Point_2D.translate(o, v), precision));
        // test middle point
        p = new Point_2D(4.5, 3.5);
        Assert.assertTrue(Vector_2D.is_pt_on_vector(p,o,Point_2D.translate(o, v), precision));
        // test points not on vector
        p = new Point_2D(1,0);
        Assert.assertTrue(!Vector_2D.is_pt_on_vector(p,o,Point_2D.translate(o, v), precision));
        p = new Point_2D(8,7);
        Assert.assertTrue(!Vector_2D.is_pt_on_vector(p,o,Point_2D.translate(o, v), precision));
        p = new Point_2D(5,5);
        Assert.assertTrue(!Vector_2D.is_pt_on_vector(p,o,Point_2D.translate(o, v), precision));
        p = new Point_2D(5,9);
        Assert.assertTrue(!Vector_2D.is_pt_on_vector(p,o,Point_2D.translate(o, v), precision));
    }

    @Test
    public void test_intersect_vector_vector() {
        Vector_2D v2 = new Vector_2D(5,5);
        Point_2D o2 = new Point_2D(2,2);
        /*
         * test both vectors in the same line, same direction
         */
        // v1 before v2, no intersection
        Vector_2D v1 = new Vector_2D(3,3);
        Point_2D o1 = new Point_2D(-2,-2);
        Intersection_Data_2D vid = new Intersection_Data_2D(); 
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1, Point_2D.translate(o1,v1), o2,Point_2D.translate(o2,v2), vid, precision)); // zero points
        // v1 before v2, intersect at o2
        o1 = new Point_2D(-1,-1);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 1);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        // v1 before v2, v1 passes o2
        o1 = new Point_2D(0,0);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o1, v1), precision));
        // v1 before v2, v1 covers v2, same end points
        v1 = new Vector_2D(7,7);
        o1 = new Point_2D(0,0);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o1, v1), precision));
        // v1 before v2, v1 covers v2
        v1 = new Vector_2D(8,8);
        o1 = new Point_2D(0,0);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o2, v2), precision));
        // o1 same as o2, v1 inside v2
        v1 = new Vector_2D(3,3);
        Assert.assertTrue(Vector_2D.intersect_vectors(o2,Point_2D.translate(o2,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o2, v1), precision));
        // o1 same as o2, v1 same as v2
        v1 = new Vector_2D(5,5);
        Assert.assertTrue(Vector_2D.intersect_vectors(o2,Point_2D.translate(o2,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o2, v1), precision));
        // o1 same as o2, v1 longer than v2
        v1 = new Vector_2D(6,6);
        Assert.assertTrue(Vector_2D.intersect_vectors(o2,Point_2D.translate(o2,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o2, v2), precision));
        // o1 after o2, v1 inside v2
        o1 = new Point_2D(3,3);
        v1 = new Vector_2D(2,2);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o1, v1), precision));
        // o1 after o2, ep1 == ep2
        v1 = new Vector_2D(4,4);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o1, v1), precision));
        // o1 after o2, ep1 past ep2
        v1 = new Vector_2D(5,5);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o2, v2), precision));
        // o1 same as ep2
        o1 = new Point_2D(7,7);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 1);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        // o1 past v2
        o1 = new Point_2D(8,8);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision)); // no intersection
        /*
         * test both vectors in the same line, opposite direction
         */
        // v1 before v2, no intersection
        v1 = new Vector_2D(-3,-3);
        o1 = new Point_2D(1,1);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        // o1 == o2, 1 intersection
        Assert.assertTrue(Vector_2D.intersect_vectors(o2,Point_2D.translate(o2,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 1);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o2, precision));
        // o1 past o2, v1 before o2
        o1 = new Point_2D(3,3);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), o2, precision));
        // o1 past o2, ep1 == o2
        o1 = new Point_2D(5,5);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), o2, precision));
        // v1 inside v2
        o1 = new Point_2D(6,6);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o1, v1), precision));
        // o1 on ep2, v1 inside v2
        o1 = new Point_2D(7,7);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o1, v1), precision));
        // o1 on ep2, ep1 before o2
        v1 = new Vector_2D(-8,-8);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), Point_2D.translate(o2, v2), precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), o2, precision));
        // o1 after ep2, v1 before o2
        o1 = new Point_2D(8,8);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), Point_2D.translate(o2, v2), precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), o2, precision));
        // o1 after ep2, ep1 == o2
        v1 = new Vector_2D(-6,-6);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), Point_2D.translate(o2, v2), precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), o2, precision));
        // o1 after ep2, ep1 after o2
        v1 = new Vector_2D(-4,-4);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 2);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), Point_2D.translate(o2, v2), precision));
        Assert.assertTrue(Point_2D.is_equal(vid.get_p2(), Point_2D.translate(o1, v1), precision));
        // o1 after ep2, ep1 == ep2
        v1 = new Vector_2D(-1,-1);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 1);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), Point_2D.translate(o2, v2), precision));
        // v1 past v2, no intersections
        o1 = new Point_2D(10,10);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision)); // no intersections
        /*
         * test both vectors not in the same line
         */
        // no intersection
        o1 = new Point_2D(6,-1);
        v1 = new Vector_2D(-3,3);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        // ep1 on middle v2
        o1 = new Point_2D(5.5, -0.5);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 1);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), Point_2D.translate(o1, v1), precision));
        // v1 middle intersects v2 middle
        o1 = new Point_2D(5,0);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 1);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), new Point_2D(2.5,2.5), precision));
        // o1 intersects v2 middle
        o1 = new Point_2D(2.5,2.5);
        Assert.assertTrue(Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        Assert.assertTrue(vid.get_num() == 1);
        Assert.assertTrue(Point_2D.is_equal(vid.get_p1(), o1, precision));
        // no intersection
        o1 = new Point_2D(3,4);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        // no intersection
        o1 = new Point_2D(1,0);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        // vector parallel, no intersection
        v1 = new Vector_2D(3,3);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
        // vector parallel, no intersection
        v1 = new Vector_2D(-3,-3);
        Assert.assertTrue(!Vector_2D.intersect_vectors(o1,Point_2D.translate(o1,v1),o2,Point_2D.translate(o2,v2),vid, precision));
//        cout << "vid.num: " << vid.num << "\n";
//        cout << "vid.p1 x: " << vid.p1.get_x() << " y: " << vid.p1.get_y() << "\n";
//        cout << "vid.p2 x: " << vid.p2.get_x() << " y: " << vid.p2.get_y() << "\n";
    }
}
