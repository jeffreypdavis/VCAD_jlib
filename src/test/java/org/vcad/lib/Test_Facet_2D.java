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
 * File: Test_Facet_2D.java
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
public class Test_Facet_2D extends TestBase {
    
    public Test_Facet_2D() {
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
    public void test_Facet_2D() {
        Facet_2D f = new Facet_2D(new Point_2D(1,1), new Point_2D(5,5), new Point_2D(5,0));
        // test get methods
        Assert.assertTrue(Point_2D.is_equal(f.get_point1(), new Point_2D(1,1), precision));
        Assert.assertTrue(Point_2D.is_equal(f.get_point2(), new Point_2D(5,0), precision));
        Assert.assertTrue(Point_2D.is_equal(f.get_point3(), new Point_2D(5,5), precision));
        // test equals
        Facet_2D f1 = new Facet_2D(new Point_2D(5,5), new Point_2D(5,0), new Point_2D(1,1));
        Assert.assertTrue(Facet_2D.is_equal(f, f1, precision));
        f1 = new Facet_2D(new Point_2D(1,5), new Point_2D(5,1), new Point_2D(0,5));
        Assert.assertTrue(!Facet_2D.is_equal(f, f1, precision));
//        cout << "p1: x=" << f.get_point1().get_x() << " y=" << f.get_point1().get_y() << endl;
//        cout << "p2: x=" << f.get_point2().get_x() << " y=" << f.get_point2().get_y() << endl;
//        cout << "p3: x=" << f.get_point3().get_x() << " y=" << f.get_point3().get_y() << endl;
    }

    public void test_facet_contains_point() {
        Facet_2D f = new Facet_2D(new Point_2D(0,0), new Point_2D(3,3), new Point_2D(6,0));
        Bool pt_on_side = new Bool(false);
        // test corner points
        Assert.assertTrue(f.contains_point(new Point_2D(0,0), pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        Assert.assertTrue(f.contains_point(new Point_2D(3,3), pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        Assert.assertTrue(f.contains_point(new Point_2D(6,0), pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        // test outside points
        Assert.assertTrue(!f.contains_point(new Point_2D(0,1), pt_on_side, precision));
        Assert.assertTrue(!f.contains_point(new Point_2D(6,1), pt_on_side, precision));
        Assert.assertTrue(!f.contains_point(new Point_2D(3,-1), pt_on_side, precision));
        // test inside points
        Assert.assertTrue(f.contains_point(new Point_2D(2,1), pt_on_side, precision));
        Assert.assertTrue(!pt_on_side.get_val());
        Assert.assertTrue(f.contains_point(new Point_2D(4,1), pt_on_side, precision));
        Assert.assertTrue(!pt_on_side.get_val());
        Assert.assertTrue(f.contains_point(new Point_2D(3,2), pt_on_side, precision));
        Assert.assertTrue(!pt_on_side.get_val());
        // test side points
        Assert.assertTrue(f.contains_point(new Point_2D(3,0), pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        Assert.assertTrue(f.contains_point(new Point_2D(2,2), pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
        pt_on_side.set_val(false);
        Assert.assertTrue(f.contains_point(new Point_2D(4,2), pt_on_side, precision));
        Assert.assertTrue(pt_on_side.get_val());
    }
}
