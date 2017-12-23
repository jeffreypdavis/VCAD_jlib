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
 * File: Test_Simplify_Mesh_2D.java
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
public class Test_Simplify_Mesh_2D extends TestBase {
    
    public Test_Simplify_Mesh_2D() {
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
        // mesh has three facets that should not be reduced
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,8)));
        mesh.add(new Facet_2D(new Point_2D(0,8), new Point_2D(5,5), new Point_2D(8,10)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(!simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,8)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,8), new Point_2D(5,5), new Point_2D(8,10)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_2() {
        // mesh has one internal point to remove
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,8)));
        mesh.add(new Facet_2D(new Point_2D(0,8), new Point_2D(5,5), new Point_2D(8,10)));
        mesh.add(new Facet_2D(new Point_2D(8,10), new Point_2D(5,5), new Point_2D(6,5)));
        mesh.add(new Facet_2D(new Point_2D(6,5), new Point_2D(5,5), new Point_2D(10,0)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,8), new Point_2D(10,0), new Point_2D(6,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8,10), new Point_2D(0,8), new Point_2D(6,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(0,8)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_3() {
        // mesh has one external point to remove
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(5,0)));
        mesh.add(new Facet_2D(new Point_2D(5,0), new Point_2D(5,5), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,8)));
        mesh.add(new Facet_2D(new Point_2D(0,8), new Point_2D(5,5), new Point_2D(8,10)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,8)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,8), new Point_2D(5,5), new Point_2D(8,10)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(5,5), new Point_2D(0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_4() {
        // mesh has three internal points
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(3,3), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(3,3), new Point_2D(0,8)));
        mesh.add(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(0,8)));
        mesh.add(new Facet_2D(new Point_2D(0,8), new Point_2D(5,5), new Point_2D(6.5,7.5)));
        mesh.add(new Facet_2D(new Point_2D(0,8), new Point_2D(6.5,7.5), new Point_2D(8,10)));
        mesh.add(new Facet_2D(new Point_2D(5,5), new Point_2D(6.5, 7.5), new Point_2D(10, 0)));
        mesh.add(new Facet_2D(new Point_2D(8,10), new Point_2D(6.5, 7.5), new Point_2D(10, 0)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(0,8), new Point_2D(0,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8,10), new Point_2D(0,8), new Point_2D(10,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_5() {
        // mesh has three internal points and 2 external points
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(3,3), new Point_2D(3,0)));
        mesh.add(new Facet_2D(new Point_2D(3,0), new Point_2D(3,3), new Point_2D(6,0)));
        mesh.add(new Facet_2D(new Point_2D(6,0), new Point_2D(3,3), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(3,3), new Point_2D(0,8)));
        mesh.add(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(0,8)));
        mesh.add(new Facet_2D(new Point_2D(0,8), new Point_2D(5,5), new Point_2D(6.5,7.5)));
        mesh.add(new Facet_2D(new Point_2D(0,8), new Point_2D(6.5,7.5), new Point_2D(8,10)));
        mesh.add(new Facet_2D(new Point_2D(5,5), new Point_2D(6.5, 7.5), new Point_2D(10, 0)));
        mesh.add(new Facet_2D(new Point_2D(8,10), new Point_2D(6.5, 7.5), new Point_2D(10, 0)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(8,10), new Point_2D(0,8), new Point_2D(10,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,8), new Point_2D(0,0), new Point_2D(10,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_6() {
        // reverse intersect_mesh_mesh_12
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(1,1), new Point_2D(4,1), new Point_2D(5.25,4.75)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(4,1), new Point_2D(1,1)));
        mesh.add(new Facet_2D(new Point_2D(10,0), new Point_2D(5.25,4.75), new Point_2D(4,1)));
        mesh.add(new Facet_2D(new Point_2D(5.25,4.75), new Point_2D(5,5), new Point_2D(1,1)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(4,1)));
        mesh.add(new Facet_2D(new Point_2D(4.56522,5.43478), new Point_2D(0.5,1), new Point_2D(1,1)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(1,1), new Point_2D(0.5,1)));
        mesh.add(new Facet_2D(new Point_2D(5,5), new Point_2D(4.56522,5.43478), new Point_2D(1,1)));
        mesh.add(new Facet_2D(new Point_2D(4.56522,5.43478), new Point_2D(0,10), new Point_2D(0.5,1)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(0.5,1), new Point_2D(0,10)));
        mesh.add(new Facet_2D(new Point_2D(4.56522,5.43478), new Point_2D(5.5,5.5), new Point_2D(6,7)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(4.56522,5.43478), new Point_2D(6,7)));
        mesh.add(new Facet_2D(new Point_2D(5,5), new Point_2D(5.5,5.5), new Point_2D(4.56522,5.43478)));
        mesh.add(new Facet_2D(new Point_2D(5.5,5.5), new Point_2D(10,10), new Point_2D(6,7)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(6,7), new Point_2D(10,10)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,10)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(0,10), new Point_2D(5,5), new Point_2D(10,10)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_7() {
        // reverse intersect_mesh_mesh_11
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(4.29412,4.05882), new Point_2D(4.11111,4.11111)));
        mesh.add(new Facet_2D(new Point_2D(4.11111,4.11111), new Point_2D(4.29412,4.05882), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(2,1), new Point_2D(4.29412,4.05882), new Point_2D(0,0)));
        mesh.add(new Facet_2D(new Point_2D(4.29412,4.05882), new Point_2D(4.5,4), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(4.29412,4.05882), new Point_2D(2,1), new Point_2D(4.5,4)));
        mesh.add(new Facet_2D(new Point_2D(5,5), new Point_2D(4.5,4), new Point_2D(5,4)));
        mesh.add(new Facet_2D(new Point_2D(2,1), new Point_2D(5,4), new Point_2D(4.5,4)));
        mesh.add(new Facet_2D(new Point_2D(5,4), new Point_2D(2,1), new Point_2D(6,0)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(5,4), new Point_2D(2,1), new Point_2D(6,0)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(5,4), new Point_2D(5,5)), mesh, precision));
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(2,1), new Point_2D(5,5), new Point_2D(0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_8() {
        // reverse intersect_mesh_mesh_7
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(3.75,3.75), new Point_2D(4.60526,1.18421), new Point_2D(5.49451,0.989011)));
        mesh.add(new Facet_2D(new Point_2D(6.15385,2.30769), new Point_2D(3.75,3.75), new Point_2D(5.49451,0.989011)));
        mesh.add(new Facet_2D(new Point_2D(10,0), new Point_2D(6.15385,2.30769), new Point_2D(5.49451,0.989011)));
        mesh.add(new Facet_2D(new Point_2D(4.60526,1.18421), new Point_2D(3.75,3.75), new Point_2D(1.8,1.8)));
        mesh.add(new Facet_2D(new Point_2D(3.75,3.75), new Point_2D(6.15385,2.30769), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(6.66667,3.33333), new Point_2D(6.15385,2.30769), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(5,5), new Point_2D(6.15385,2.30769), new Point_2D(6.66667,3.33333)));
        mesh.add(new Facet_2D(new Point_2D(1.28571,0), new Point_2D(5,0), new Point_2D(4.60526,1.18421)));
        mesh.add(new Facet_2D(new Point_2D(5,0), new Point_2D(5.49451,0.989011), new Point_2D(4.60526,1.18421)));
        mesh.add(new Facet_2D(new Point_2D(5,0), new Point_2D(10,0), new Point_2D(5.49451,0.989011)));
        mesh.add(new Facet_2D(new Point_2D(4.60526,1.18421), new Point_2D(1.8,1.8), new Point_2D(1.28571,0)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(1.28571,0), new Point_2D(1.8,1.8)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
        Assert.assertTrue(mesh.size() == 1);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(5,5), new Point_2D(0,0)), mesh, precision));
    }

    @Test
    public void test_simplify_mesh_9() {
        // reverse intersect_mesh_mesh_6
        Mesh_2D mesh = new Mesh_2D();
        mesh.add(new Facet_2D(new Point_2D(2,1), new Point_2D(4,3), new Point_2D(2,2)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(2,1), new Point_2D(2,2)));
        mesh.add(new Facet_2D(new Point_2D(2,2), new Point_2D(4,3), new Point_2D(3,3)));
        mesh.add(new Facet_2D(new Point_2D(3,3), new Point_2D(4,3), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)));
        mesh.add(new Facet_2D(new Point_2D(5,5), new Point_2D(4,3), new Point_2D(7.42857,2.57143)));
        mesh.add(new Facet_2D(new Point_2D(7.42857,2.57143), new Point_2D(4,3), new Point_2D(8.66667,1.33333)));
        mesh.add(new Facet_2D(new Point_2D(8.66667,1.33333), new Point_2D(7.71429,1.14286), new Point_2D(10,0)));
        mesh.add(new Facet_2D(new Point_2D(4,3), new Point_2D(7.71429,1.14286), new Point_2D(8.66667,1.33333)));
        mesh.add(new Facet_2D(new Point_2D(10,0), new Point_2D(7.71429,1.14286), new Point_2D(7,1)));
        mesh.add(new Facet_2D(new Point_2D(7.71429,1.14286), new Point_2D(4,3), new Point_2D(7,1)));
        mesh.add(new Facet_2D(new Point_2D(2,1), new Point_2D(10,0), new Point_2D(7,1)));
        mesh.add(new Facet_2D(new Point_2D(2,1), new Point_2D(7,1), new Point_2D(4,3)));

        Simplify_Mesh_2D simplify_mesh = new Simplify_Mesh_2D();

        Assert.assertTrue(simplify_mesh.simplify(mesh));
//        cout << "\n";
//        int count = 0;
//        for (Mesh_2D::const_iterator it = mesh.begin(); it != mesh.end(); ++it)
//        {
//            cout << "mesh[" << count++ << "] facet p1 x: " << (*it)->get_point1().get_x() << " y: " <<
//                    (*it)->get_point1().get_y() << " p2 x: " << (*it)->get_point2().get_x() << 
//                    " y: " << (*it)->get_point2().get_y() << " p3 x: " << 
//                    (*it)->get_point3().get_x() << " y: " << (*it)->get_point3().get_y() << "\n";
//        }
        Assert.assertTrue(mesh.size() == 1);
        Assert.assertTrue(mesh_contains(new Facet_2D(new Point_2D(10,0), new Point_2D(5,5), new Point_2D(0,0)), mesh, precision));
    }
}
