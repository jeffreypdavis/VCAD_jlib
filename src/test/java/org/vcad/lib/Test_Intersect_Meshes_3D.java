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
 * File: Test_Intersect_Meshes_3D.java
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
public class Test_Intersect_Meshes_3D extends TestBase {
    
    public Test_Intersect_Meshes_3D() {
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
        // different planes: no intersection - parallel
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 1), new Point_3D(0, 10, 1), new Point_3D(5, 0, 1));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(-5, 3, 0), new Point_3D(1, 2, 0), new Point_3D(0.75, 1, 0));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_2() {
        // different planes: corner touches corner
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, 5), new Point_3D(1, 2, 5), new Point_3D(0.75, 1, 5));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_3() {
        // different planes: corner touches side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, 3), new Point_3D(1, 2, 3), new Point_3D(0.75, 1, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,0,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,0,3)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_4() {
        // different planes: side touches side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(-1, 0, 3), new Point_3D(1, 0, 3), new Point_3D(-0.75, -1, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1,0,3), new Point_3D(0,0,3), new Point_3D(-0.75,-1,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,0,3), new Point_3D(-0.75,-1,3), new Point_3D(0,0,3)), result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,0,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,0,3)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_5() {
        // different planes: corner touches inside of facet
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 1, 3), new Point_3D(1, 2, 3), new Point_3D(0.75, 1, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,1,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,3), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,1,3)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_6() {
        // different planes: corner pierces inside of facet
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(-0.5, 1, 3), new Point_3D(1, 1, 3), new Point_3D(1, 2, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,3), new Point_3D(0,1.33333,3), new Point_3D(-0.5,1,3)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,3), new Point_3D(1,1,3), new Point_3D(0,1.33333,3)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1.33333,3), new Point_3D(1,1,3), new Point_3D(1,2,3)), result1, 0.0001));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,3), new Point_3D(0,0,0), new Point_3D(0,1.33333,3)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,3), new Point_3D(0,0,5)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,3), new Point_3D(0,1.33333,3), new Point_3D(0,0,5)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,1.33333,3)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1.33333,3), new Point_3D(0,10,0), new Point_3D(0,0,5)), result2, 0.0001));
    }

    @Test
    public void test_intersect_meshes_7() {
        // different planes: 1 side inside, 1 side out
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(-1, 1, 3), new Point_3D(1, 1, 3), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,3), new Point_3D(0,0,3), new Point_3D(-1,1,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,3), new Point_3D(1,-10,3), new Point_3D(0,1,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1,1,3), new Point_3D(0,0,3), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,3), new Point_3D(1,-10,3), new Point_3D(0,0,3)), result1, precision));
        Assert.assertTrue(result2.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,3), new Point_3D(0,0,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,3), new Point_3D(0,1,3), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,1,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,1,3), new Point_3D(0,10,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_8() {
        // different planes: other facet outside, facet inside
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(1, -2, 4), new Point_3D(-2, -3, 4), new Point_3D(0, 10, 4));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,2,4), new Point_3D(1,-2,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,-2,4), new Point_3D(-2,-3,4), new Point_3D(0,0,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(-2,-3,4), new Point_3D(0,2,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,-2,4), new Point_3D(0,2,4), new Point_3D(0,10,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(-2,-3,4), new Point_3D(0,10,4)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,0,0), new Point_3D(0,2,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,2,4)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_9() {
        // one side on same plane: no intersection
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -1, 0), new Point_3D(0, -1, 5), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_10() {
        // one side on same plane: share same side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 0, 5), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_11() {
        // one side on same plane: share same side, but other_facet side is longer
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, -1), new Point_3D(0, 0, 6), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-1), new Point_3D(0,0,0), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,5), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,6), new Point_3D(1,-10,3), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(result2.size() == 1);
        Assert.assertTrue(mesh_contains(facet2, result2, precision));
    }

    @Test
    public void test_intersect_meshes_12() {
        // one side on same plane: touches corner
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, 5), new Point_3D(0, 0, 6), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_13() {
        // one side on same plane: side smaller than side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, 1), new Point_3D(0, 0, 4), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,0,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,1), new Point_3D(0,10,0), new Point_3D(0,0,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,10,0), new Point_3D(0,0,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_14() {
        // one side on same plane: side half in, half out
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, 7), new Point_3D(0, 0, 3), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,7), new Point_3D(0,0,5), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,3), new Point_3D(1,-10,3), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,0,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,0,3)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_15() {
        // one side on same plane: side half in, through a corner point
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 1, 1), new Point_3D(0, -1, -1), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,0,0), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-1,-1), new Point_3D(1,-10,3), new Point_3D(0,0,0)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,1,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,1), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,1,1)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_16() {
        // one side on same plane: side half in, half out
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 2, 1), new Point_3D(0, 0, -1), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,1), new Point_3D(0,1,0), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,-1), new Point_3D(1,-10,3), new Point_3D(0,1,0)), result1, precision));
        Assert.assertTrue(result2.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,0), new Point_3D(0,2,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,0), new Point_3D(0,10,0), new Point_3D(0,2,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,2,1), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,2,1)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_17() {
        // one side on same plane: cross facet
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 2, -1), new Point_3D(0, 2, 6), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,-1), new Point_3D(0,2,0), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,0), new Point_3D(0,2,4), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,2,6), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,0), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,0), new Point_3D(0,10,0), new Point_3D(0,2,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_18() {
        // one side on same plane: side half in, half out
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 2, 1), new Point_3D(0, 2, 6), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,1), new Point_3D(0,2,4), new Point_3D(1,-10,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,6), new Point_3D(1,-10,3), new Point_3D(0,2,4)), result1, precision));
        Assert.assertTrue(result2.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,2,1), new Point_3D(0,10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,2,1), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,5), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,1), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_19() {
        // one side on same plane: side inside
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 2, 1), new Point_3D(0, 2, 3), new Point_3D(1, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,1), new Point_3D(0,2,3), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,2,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,1), new Point_3D(0,10,0), new Point_3D(0,2,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,2,3), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,3), new Point_3D(0,10,0), new Point_3D(0,0,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_20() {
        // facets on same plane: no intersection
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -2, 1), new Point_3D(0, -2, 6), new Point_3D(0, -10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_21() {                  
        // facets on similar plane: no intersection
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0.1, 1, 1), new Point_3D(0.1, 1, 2), new Point_3D(0.1, 2, 1));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_22() {
        // facets on same plane: share corner
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 10, 0), new Point_3D(0, 12, 0), new Point_3D(0, 11, 2));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_23() {
        // facets on same plane: share side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 10, 0), new Point_3D(0, 0, 5), new Point_3D(0, 8, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_24() {
        // facets on same plane: share side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -2, 6), new Point_3D(0, 12, -1), new Point_3D(0, 12, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-2,6), new Point_3D(0,0,5), new Point_3D(0,12,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,12,10), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,12,-1), new Point_3D(0,12,10)), result1, precision));
        Assert.assertTrue(result2.size() == 1);
        Assert.assertTrue(mesh_contains(facet2, result2, precision));
    }

    @Test
    public void test_intersect_meshes_25() {
        // facets on same plane: share part of side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -2, 6), new Point_3D(0, 2, 4), new Point_3D(0, 10, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-2,6), new Point_3D(0,0,5), new Point_3D(0,10,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,10,10), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,5), new Point_3D(0,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_26() {
        // facets on same plane: partial side intersection
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 12, -1), new Point_3D(0, 2, 4), new Point_3D(0, 10, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,12,-1), new Point_3D(0,10,0), new Point_3D(0,10,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,10,10), new Point_3D(0,10,0)), result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,5), new Point_3D(0,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_27() {
        // facets on same plane: side inside side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 2, 4), new Point_3D(0, 8, 1), new Point_3D(0, 10, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,8,1), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,8,1), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_28() {
        // facets on same plane: two side through corners
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -5, -1), new Point_3D(0, 5, 1), new Point_3D(0, 15, -1));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-5,-1), new Point_3D(0,0,0), new Point_3D(0,10,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,5,1), new Point_3D(0,10,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,15,-1), new Point_3D(0,-5,-1)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,1), new Point_3D(0,0,0), new Point_3D(0,10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,5,1), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,5), new Point_3D(0,5,1)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_29() {
        // facets on same plane: one side through corner, one side through side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -5, -1), new Point_3D(0, 5, 1), new Point_3D(0, 5, -1));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-5,-1), new Point_3D(0,0,0), new Point_3D(0,5,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,5,1), new Point_3D(0,5,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(0,5,-1), new Point_3D(0,-5,-1)), result1, precision));
        Assert.assertTrue(result2.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,1), new Point_3D(0,0,0), new Point_3D(0,5,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,5,1), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(0,10,0), new Point_3D(0,5,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,1), new Point_3D(0,10,0), new Point_3D(0,0,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_30() {
        // facets on same plane: overlap one corner
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -2, 4), new Point_3D(0, 3, 4), new Point_3D(0, -2, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,5), new Point_3D(0,0,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,3,4), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,3,4), new Point_3D(0,-2,10), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,-2,10), new Point_3D(0,0,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,-2,10), new Point_3D(0,-2,4)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,2,4), new Point_3D(0,0,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_31() {
        // facets on same plane: overlap corner
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -2, 4), new Point_3D(0, 2, 4), new Point_3D(0, -1, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,5), new Point_3D(0,0,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,-1,10), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-1,10), new Point_3D(0,0,4), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-2,4), new Point_3D(0,0,4), new Point_3D(0,-1,10)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,2,4), new Point_3D(0,0,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_32() {
        // facets on same plane: corner overlap
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -1, 6), new Point_3D(0, 1, 2), new Point_3D(0, 3, 6));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,0,4), new Point_3D(0,2,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,0,5), new Point_3D(0,-1,6)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,2,4), new Point_3D(0,-1,6)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,1,2), new Point_3D(0,2,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-1,6), new Point_3D(0,2,4), new Point_3D(0,3,6)), result1, precision));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,2), new Point_3D(0,2,4), new Point_3D(0,0,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,0,0), new Point_3D(0,1,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,1,2), new Point_3D(0,10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,2), new Point_3D(0,0,0), new Point_3D(0,10,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_33() {
        // facets on same plane: corner on side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 5, 2.5), new Point_3D(0, 2, 10), new Point_3D(0, 15, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,5,2.5), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,2.5), new Point_3D(0,0,5), new Point_3D(0,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_34() {
        // facets on same plane: corner over side
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 2, 3), new Point_3D(0, 2, 5), new Point_3D(0, 10, 3));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,3), new Point_3D(0,2,4), new Point_3D(0,4,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,2,5), new Point_3D(0,4,3)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,5), new Point_3D(0,10,3), new Point_3D(0,4,3)), result1, precision));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,3), new Point_3D(0,4,3), new Point_3D(0,2,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,4,3), new Point_3D(0,2,3), new Point_3D(0,10,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,2,3), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,3), new Point_3D(0,2,4), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_35() {
        // facets on same plane: two corners inside facet
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 1, 1), new Point_3D(0, 4, 1), new Point_3D(0, 1, -2));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,3,0), new Point_3D(0,1,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,0), new Point_3D(0,3,0), new Point_3D(0,1,-2)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,4,1), new Point_3D(0,3,0), new Point_3D(0,1,1)), result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,3,0), new Point_3D(0,4,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,1,0), new Point_3D(0,3,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,0), new Point_3D(0,1,1), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,3,0), new Point_3D(0,10,0), new Point_3D(0,4,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,4,1), new Point_3D(0,10,0), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,4,1), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,1), new Point_3D(0,0,5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_36() {
        // facets on same plane: corners out, body in
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -2, 4), new Point_3D(0, 4, -2), new Point_3D(0, 10, 4));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,0,4), new Point_3D(0,7.33333,1.33333)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,0,2), new Point_3D(0,7.33333,1.33333)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,2), new Point_3D(0,2,0), new Point_3D(0,7.33333,1.33333)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,0), new Point_3D(0,6,0), new Point_3D(0,7.33333,1.33333)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,2), new Point_3D(0,0,4), new Point_3D(0,-2,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,0), new Point_3D(0,4,-2), new Point_3D(0,6,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,4), new Point_3D(0,7.33333,1.33333), new Point_3D(0,10,4)), result1, 0.0001));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,6,0), new Point_3D(0,7.33333,1.33333), new Point_3D(0,2,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,0), new Point_3D(0,7.33333,1.33333), new Point_3D(0,0,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,2), new Point_3D(0,7.33333,1.33333), new Point_3D(0,0,4)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,7.33333,1.33333), new Point_3D(0,2,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,4), new Point_3D(0,2,4), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,2,0), new Point_3D(0,0,2), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,6,0), new Point_3D(0,10,0), new Point_3D(0,7.33333,1.33333)), result2, 0.0001));
    }

    @Test
    public void test_intersect_meshes_37() {
        // facets on same plane: all inside
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 1, 1), new Point_3D(0, 4, 1), new Point_3D(0, 1, 4));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 1);
        Assert.assertTrue(mesh_contains(facet1, result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,0,0), new Point_3D(0,4,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,1,4), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,1,4), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,4), new Point_3D(0,10,0), new Point_3D(0,0,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,4,1), new Point_3D(0,10,0), new Point_3D(0,1,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,4,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,1,1), new Point_3D(0,4,1), new Point_3D(0,1,4)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_38() {
        // facets on same plane: same facets
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(!intersect_meshes.intersect(mesh1, mesh2, result1, result2));
    }

    @Test
    public void test_intersect_meshes_39() {
        // facets on same plane: facet2 inside facet1
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(0, 10, 0), new Point_3D(0, 0, 5));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(0, -1, -1), new Point_3D(0, 12, -1), new Point_3D(0, -1, 10));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,0,0), new Point_3D(0,-1,-1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,0,5), new Point_3D(0,-1,-1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-1,-1), new Point_3D(0,0,5), new Point_3D(0,-1,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,10,0), new Point_3D(0,-1,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,12,-1), new Point_3D(0,-1,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,-1,-1), new Point_3D(0,12,-1), new Point_3D(0,10,0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(0,10,0), new Point_3D(0,0,5)), result1, precision));
        Assert.assertTrue(result2.size() == 1);
        Assert.assertTrue(mesh_contains(facet2, result2, precision));
    }

    @Test
    public void test_intersect_meshes_40() {
        // facets on same plane.  corner of other_facet on facet
        Mesh_3D mesh2 = new Mesh_3D();
        Facet_3D facet2 = new Facet_3D(new Point_3D(0, 0, 0), new Point_3D(5, 5, 5), new Point_3D(0, 10, 10));
        mesh2.add(facet2);
        Mesh_3D mesh1 = new Mesh_3D();
        Facet_3D facet1 = new Facet_3D(new Point_3D(4, 1, 1), new Point_3D(0.5, 1, 1), new Point_3D(6, 7, 7));
        mesh1.add(facet1);

        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();

        // intersect facets and get segments. 
        Assert.assertTrue(intersect_meshes.intersect(mesh1, mesh2, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(1,1,1), new Point_3D(4.56522,5.43478,5.43478)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,5,5), new Point_3D(4,1,1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.56522,5.43478,5.43478), new Point_3D(6,7,7), new Point_3D(5,5,5)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(0.5,1,1), new Point_3D(4.56522,5.43478,5.43478)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(6,7,7), new Point_3D(4,1,1)), result1, precision));
        Assert.assertTrue(result2.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.5,1,1), new Point_3D(1,1,1), new Point_3D(4.56522,5.43478,5.43478)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(0.5,1,1), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.56522,5.43478,5.43478), new Point_3D(0,10,10), new Point_3D(0.5,1,1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,5,5), new Point_3D(4.56522,5.43478,5.43478)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.5,1,1), new Point_3D(0,10,10), new Point_3D(0,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_41() {
        // no intersection
        Facet_3D facet = new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0));
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(facet);

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(-1,-1,-1), new Point_3D(-5,-5,-5), new Point_3D(-10, -1, 0)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(-1,-1,-1), new Point_3D(-5,-5,-5), new Point_3D(-20, -2, 0)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(-10,-1,0), new Point_3D(-20,-2,0), new Point_3D(-3, -1.5, -2)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(!intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
    }

    @Test
    public void test_intersect_meshes_42() {
        // mesh shares one side with facet
        Facet_3D facet = new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0));
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(facet);

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(-10, -1, 0)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(-1,-1,-1), new Point_3D(-5,-5,-5), new Point_3D(-20, -2, 0)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(-10,-1,0), new Point_3D(-20,-2,0), new Point_3D(-3, -1.5, -2)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(!intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
    }

    @Test
    public void test_intersect_meshes_43() {
        // mesh shares part of a side with facet
        Facet_3D facet = new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0));
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(facet);

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(1,1,1), new Point_3D(4,4,4), new Point_3D(8, -5, 2)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(-1,-1,-1), new Point_3D(-5,-5,-5), new Point_3D(-20, -2, 0)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(-10,-1,0), new Point_3D(-20,-2,0), new Point_3D(-3, -1.5, -2)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(4,4,4), new Point_3D(8, -5, 2)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1,-1,-1), new Point_3D(-5,-5,-5), new Point_3D(-20, -2, 0)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-10,-1,0), new Point_3D(-20,-2,0), new Point_3D(-3, -1.5, -2)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(4,4,4), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,4,4), new Point_3D(5,5,5), new Point_3D(10,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_44() {
        // mesh pierces facet with two facets
        Facet_3D facet = new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0));
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(facet);

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(5,2,0), new Point_3D(5,2,5), new Point_3D(6, 2, 5)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(5,2,0), new Point_3D(5,2,5), new Point_3D(4, 2, 5)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(1,3,5), new Point_3D(5,2,5), new Point_3D(4,2,5)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,2,0), new Point_3D(5,2,2), new Point_3D(5.4,2,2)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,2,2), new Point_3D(5,2,5), new Point_3D(5.4,2,2)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,2,5), new Point_3D(6,2,5), new Point_3D(5.4,2,2)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.6,2,2), new Point_3D(5,2,0), new Point_3D(5,2,2)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,2,2), new Point_3D(5,2,5), new Point_3D(4.6,2,2)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.6,2,2), new Point_3D(5,2,5), new Point_3D(4,2,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,3,5), new Point_3D(5,2,5), new Point_3D(4,2,5)), result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,2,2), new Point_3D(5.4,2,2), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(4.6,2,2), new Point_3D(5,2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(5,2,2), new Point_3D(4.6,2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(4.6,2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,2,2), new Point_3D(5,5,5), new Point_3D(5.4,2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5.4,2,2), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.4,2,2), new Point_3D(5,5,5), new Point_3D(10,0,0)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_45() {
        // one mesh facet intersects two facets
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(1,1,0), new Point_3D(0,1,5), new Point_3D(2, 1, 5)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,0), new Point_3D(1,1,1), new Point_3D(1.2,1,1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.2,1,1), new Point_3D(1,1,1), new Point_3D(2,1,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,0), new Point_3D(0.8,1,1), new Point_3D(1,1,1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(0.8,1,1), new Point_3D(2,1,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.8,1,1), new Point_3D(0,1,5), new Point_3D(2,1,5)), result1, precision));
        Assert.assertTrue(result2.size() == 9);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(0,0,0), new Point_3D(1.2,1,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(1.2,1,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(1.2,1,1), new Point_3D(5,5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(1.2,1,1), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(0.8,1,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,1,1), new Point_3D(5,5,5), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(0,0,0), new Point_3D(0.8,1,1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.8,1,1), new Point_3D(1,1,1), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_46() {
        // intersect mesh intersects first and third facets
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(5,1,0), new Point_3D(5,1,5), new Point_3D(5, 2, 5)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(5,1,5), new Point_3D(5,2,5), new Point_3D(8,5,7)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(8,5,7), new Point_3D(5,2,5), new Point_3D(6, 7, 7)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,1,0), new Point_3D(5,1,1), new Point_3D(5,1.25,1.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,1,1), new Point_3D(5,1,5), new Point_3D(5,1.25,1.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,1,5), new Point_3D(5,2,5), new Point_3D(5,1.25,1.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,1,5), new Point_3D(5,2,5), new Point_3D(8,5,7)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8,5,7), new Point_3D(5,2,5), new Point_3D(6,7,7)), result1, precision));
        Assert.assertTrue(result2.size() == 9);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,1,1), new Point_3D(0,0,0), new Point_3D(5,1.25,1.25)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,1,1), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(5,1,1), new Point_3D(5,1.25,1.25)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(5,1.25,1.25)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,1.25,1.25), new Point_3D(5,5,5), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(6,7,7)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(6,7,7), new Point_3D(10,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(10,10,10), new Point_3D(6,7,7)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_47() {
        // intersect mesh facet is on the same plane crosses each mesh facet
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,1,1), new Point_3D(0.5,1,1), new Point_3D(6, 7, 7)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,1,1), new Point_3D(0.5,1,1), new Point_3D(1, 3, 7)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(0.5,1,1), new Point_3D(6,7,7), new Point_3D(3, 1.5, -2)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 11);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5, 5, 5), new Point_3D(1, 1, 1), new Point_3D(4.56522, 5.43478, 5.43478)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5, 5, 5), new Point_3D(5.25, 4.75, 4.75), new Point_3D(1, 1, 1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 1, 1), new Point_3D(0.5, 1, 1), new Point_3D(4.56522, 5.43478, 5.43478)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 1, 1), new Point_3D(5.25, 4.75, 4.75), new Point_3D(4, 1, 1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5, 5, 5), new Point_3D(5.5, 5.5, 5.5), new Point_3D(5.25, 4.75, 4.75)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.5, 5.5, 5.5), new Point_3D(4.56522, 5.43478, 5.43478), new Point_3D(6, 7, 7)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5, 5, 5), new Point_3D(4.56522, 5.43478, 5.43478), new Point_3D(5.5, 5.5, 5.5)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 3, 7), new Point_3D(4, 1, 1), new Point_3D(1, 1, 1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 3, 7), new Point_3D(1, 1, 1), new Point_3D(0.5, 1, 1)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3, 1.5, -2), new Point_3D(0.5, 1, 1), new Point_3D(4.56522, 5.43478, 5.43478)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3, 1.5, -2), new Point_3D(4.56522, 5.43478, 5.43478), new Point_3D(6, 7, 7)), result1, 0.0001));
        Assert.assertTrue(result2.size() == 15);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4, 1, 1), new Point_3D(5.25, 4.75, 4.75), new Point_3D(1, 1, 1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 1, 1), new Point_3D(0, 0, 0), new Point_3D(4, 1, 1)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 1, 1), new Point_3D(5.25, 4.75, 4.75), new Point_3D(5, 5, 5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.25, 4.75, 4.75), new Point_3D(4, 1, 1), new Point_3D(10, 0, 0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4, 1, 1), new Point_3D(0, 0, 0), new Point_3D(10, 0, 0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.5, 1, 1), new Point_3D(1, 1, 1), new Point_3D(4.56522, 5.43478, 5.43478)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 1, 1), new Point_3D(0.5, 1, 1), new Point_3D(0, 0, 0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1, 1, 1), new Point_3D(5, 5, 5), new Point_3D(4.56522, 5.43478, 5.43478)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.5,1,1), new Point_3D(0,10,10), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.56522, 5.43478, 5.43478), new Point_3D(0, 10, 10), new Point_3D(0.5, 1, 1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,7,7), new Point_3D(4.56522,5.43478,5.43478), new Point_3D(5.5,5.5,5.5)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.56522, 5.43478, 5.43478), new Point_3D(6, 7, 7), new Point_3D(0, 10, 10)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.5, 5.5, 5.5), new Point_3D(4.56522, 5.43478, 5.43478), new Point_3D(5, 5, 5)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,7,7), new Point_3D(10,10,10), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.5,5.5,5.5), new Point_3D(10,10,10), new Point_3D(6,7,7)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_48() {
        // mesh edge crosses corner with end points inside two facets
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7,7), new Point_3D(6,3,3), new Point_3D(3, 3, 6)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7,7), new Point_3D(3,3,6), new Point_3D(7, 7, 10)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(6,3,3), new Point_3D(3,3,6), new Point_3D(7, 7, 10)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,7,7), new Point_3D(5,5,5), new Point_3D(3,3,6)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,3,3), new Point_3D(3,3,6), new Point_3D(5,5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,7,7), new Point_3D(3,3,6), new Point_3D(7,7,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,3,3), new Point_3D(3,3,6), new Point_3D(7,7,10)), result1, precision));
        Assert.assertTrue(result2.size() == 7);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(6,3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(10,0,0), new Point_3D(6,3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(6,3,3), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(4,7,7)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(10,10,10), new Point_3D(4,7,7)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(4,7,7), new Point_3D(10,10,10)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_49() {
        // mesh sides intersect mesh
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(5,5,5), new Point_3D(4.5,4.5,4.5)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(7,7.5,9.5), new Point_3D(6,3.5,2.5), new Point_3D(5, 3.5, 3.5)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.75,4.25,4.25), new Point_3D(5.325,3.825,3.825), new Point_3D(7,7.5,9.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7,7.5,9.5), new Point_3D(6,3.5,2.5), new Point_3D(5.75,4.25,4.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,3.5,2.5), new Point_3D(5.325,3.825,3.825), new Point_3D(5.75,4.25,4.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7,7.5,9.5), new Point_3D(5.325,3.825,3.825), new Point_3D(5,3.5,3.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.325,3.825,3.825), new Point_3D(6,3.5,2.5), new Point_3D(5,3.5,3.5)), result1, precision));
        Assert.assertTrue(result2.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(5.75,4.25,4.25), new Point_3D(5.325,3.825,3.825)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.75,4.25,4.25), new Point_3D(5,5,5), new Point_3D(5.325,3.825,3.825)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(4.5,4.5,4.5), new Point_3D(5.325,3.825,3.825)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_50() {
        // mesh sides intersect mesh
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7.5,6.5), new Point_3D(6,3.5,2.5), new Point_3D(3, 3.5, 5.5)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7.5,6.5), new Point_3D(3,3.5,5.5), new Point_3D(7, 7.5, 9.5)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(6,3.5,2.5), new Point_3D(3,3.5,5.5), new Point_3D(7, 7.5, 9.5)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.5,4.5,4.5), new Point_3D(4,7.5,6.5), new Point_3D(5,3.5,3.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,3.5,2.5), new Point_3D(5,3.5,3.5), new Point_3D(4,7.5,6.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.66667,6.16667,6.16667), new Point_3D(4,7.5,6.5), new Point_3D(4.5,4.5,4.5)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.66667,6.16667,6.16667), new Point_3D(4.5,4.5,4.5), new Point_3D(3,3.5,5.5)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,3.5,3.5), new Point_3D(3,3.5,5.5), new Point_3D(4.5,4.5,4.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.75,6.25,6.25), new Point_3D(4,7.5,6.5), new Point_3D(3.66667,6.16667,6.16667)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.66667,6.16667,6.16667), new Point_3D(3,3.5,5.5), new Point_3D(3.75,6.25,6.25)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,7.5,7.5), new Point_3D(4,7.5,6.5), new Point_3D(3.75,6.25,6.25)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7,7.5,9.5), new Point_3D(5,7.5,7.5), new Point_3D(3,3.5,5.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.75,6.25,6.25), new Point_3D(3,3.5,5.5), new Point_3D(5,7.5,7.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,3.5,3.5), new Point_3D(5.75,4.25,4.25), new Point_3D(6,3.5,2.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,3.5,5.5), new Point_3D(7,7.5,9.5), new Point_3D(5,3.5,3.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,3.5,2.5), new Point_3D(5.75,4.25,4.25), new Point_3D(7,7.5,9.5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,3.5,3.5), new Point_3D(7,7.5,9.5), new Point_3D(5.75,4.25,4.25)), result1, precision));
        Assert.assertTrue(result2.size() == 14);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,3.5,3.5), new Point_3D(5.75,4.25,4.25), new Point_3D(4.5,4.5,4.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.5,4.5,4.5), new Point_3D(0,0,0), new Point_3D(5,3.5,3.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,3.5,3.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(4.5,4.5,4.5), new Point_3D(5.75,4.25,4.25)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.75,4.25,4.25), new Point_3D(5,3.5,3.5), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.66667,6.16667,6.16667), new Point_3D(4.5,4.5,4.5), new Point_3D(3.75,6.25,6.25)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(4.5,4.5,4.5), new Point_3D(3.66667,6.16667,6.16667)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(3.75,6.25,6.25), new Point_3D(4.5,4.5,4.5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.75,6.25,6.25), new Point_3D(0,10,10), new Point_3D(3.66667,6.16667,6.16667)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(3.66667,6.16667,6.16667), new Point_3D(0,10,10)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.75,6.25,6.25), new Point_3D(5,7.5,7.5), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(10,10,10), new Point_3D(3.75,6.25,6.25)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,7.5,7.5), new Point_3D(10,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.75,6.25,6.25), new Point_3D(10,10,10), new Point_3D(5,7.5,7.5)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_51() {
        // mesh intersects facet twice
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7,7), new Point_3D(6,3,3), new Point_3D(3, 3, 6)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7,7), new Point_3D(3,3,6), new Point_3D(7, 7, 10)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(6,3,3), new Point_3D(3,3,6), new Point_3D(7, 7, 10)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(3,2,2), new Point_3D(2,2,5), new Point_3D(3, 2, 5)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,7,7), new Point_3D(5,5,5), new Point_3D(3,3,6)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,3,3), new Point_3D(3,3,6), new Point_3D(5,5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,7,7), new Point_3D(3,3,6), new Point_3D(7,7,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6,3,3), new Point_3D(3,3,6), new Point_3D(7,7,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,2,2), new Point_3D(2,2,5), new Point_3D(3,2,5)), result1, precision));
        Assert.assertTrue(result2.size() == 9);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(10,0,0), new Point_3D(6,3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(3,2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(3,2,2), new Point_3D(6,3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(6,3,3), new Point_3D(3,2,2)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(6,3,3), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(4,7,7)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(4,7,7), new Point_3D(10,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,5), new Point_3D(10,10,10), new Point_3D(4,7,7)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_52() {
        // mesh intersects side with facets
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7,7), new Point_3D(5,3,3), new Point_3D(3, 3, 6)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(4,7,7), new Point_3D(3,3,6), new Point_3D(7, 7, 10)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(5,3,3), new Point_3D(3,3,6), new Point_3D(7, 7, 10)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 5);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,7,7), new Point_3D(4.33333,5.66667,5.66667), new Point_3D(3,3,6)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33333,5.66667,5.66667), new Point_3D(4.6,4.6,4.6), new Point_3D(3,3,6)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,3,3), new Point_3D(3,3,6), new Point_3D(4.6,4.6,4.6)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,7,7), new Point_3D(3,3,6), new Point_3D(7,7,10)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,3,3), new Point_3D(3,3,6), new Point_3D(7,7,10)), result1, precision));
        Assert.assertTrue(result2.size() == 11);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(4.6,4.6,4.6), new Point_3D(5,3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.6,4.6,4.6), new Point_3D(5,5,5), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,3,3), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.6,4.6,4.6), new Point_3D(10,0,0), new Point_3D(5,3,3)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33333,5.66667,5.66667), new Point_3D(0,0,0), new Point_3D(4.6,4.6,4.6)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33333,5.66667,5.66667), new Point_3D(4.6,4.6,4.6), new Point_3D(5,5,5)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(4.33333,5.66667,5.66667), new Point_3D(0,10,10)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33333,5.66667,5.66667), new Point_3D(4,7,7), new Point_3D(0,10,10)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.33333,5.66667,5.66667), new Point_3D(5,5,5), new Point_3D(10,10,10)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,7,7), new Point_3D(10,10,10), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,10,10), new Point_3D(4,7,7), new Point_3D(4.33333,5.66667,5.66667)), result2, 0.0001));
    }

    @Test
    public void test_intersect_meshes_53() {
        // intersecting mesh is on the same plane as mesh
        Mesh_3D mesh = new Mesh_3D();
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));

        Mesh_3D intersecting_mesh = new Mesh_3D();
        intersecting_mesh.add(new Facet_3D(new Point_3D(1,2,2), new Point_3D(3,4,4), new Point_3D(1.5, 4, 4)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(3,4,4), new Point_3D(1.5,4,4), new Point_3D(5, 5, 5)));
        intersecting_mesh.add(new Facet_3D(new Point_3D(1.5,4,4), new Point_3D(5,5,5), new Point_3D(4, 5, 5)));

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,2,2), new Point_3D(3,4,4), new Point_3D(1.5,4,4)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,4,4), new Point_3D(1.5,4,4), new Point_3D(5,5,5)), result1, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.5,4,4), new Point_3D(5,5,5), new Point_3D(4,5,5)), result1, precision));
        Assert.assertTrue(result2.size() == 11);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(10,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,2,2), new Point_3D(3,4,4), new Point_3D(1.5,4,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(3,4,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,2,2), new Point_3D(0,0,0), new Point_3D(3,4,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,2,2), new Point_3D(0,10,10), new Point_3D(0,0,0)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,5,5), new Point_3D(5,5,5), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1,2,2), new Point_3D(1.5,4,4), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3,4,4), new Point_3D(5,5,5), new Point_3D(1.5,4,4)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4,5,5), new Point_3D(1.5,4,4), new Point_3D(5,5,5)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.5,4,4), new Point_3D(4,5,5), new Point_3D(0,10,10)), result2, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), result2, precision));
    }

    @Test
    public void test_intersect_meshes_54() {
        // intersecting mesh is on the same plane as mesh
        Mesh_3D mesh = new Mesh_3D();
        // make a 3D circle with radius 20, but keep only the third facet
        final double two_pi = 6.283185307179586;
        final double step = two_pi / 8;
        // generate 3 dimensional circle
        double angle = step;
        Point_3D origin = new Point_3D(0,0,0);
        Point_3D begin = new Point_3D(20, 0, 0); // radius is 20
        Point_3D middle = Point_3D.cylindrical_point(20, angle, 0, origin);
        angle += step;
        int count = 0;
        while (angle < two_pi) {
            Point_3D last = Point_3D.cylindrical_point(20, angle, 0, origin);

            if (count++ == 2) {
                mesh.add(new Facet_3D(begin, middle, last));
                break;
            }
            middle = last;
            angle += step;
        }

        Mesh_3D intersecting_mesh = new Mesh_3D();
        // make a 3D circle with radius 15, but only keep the first facet
        begin = new Point_3D(15, 0, 0); // radius is 15
        angle = step;
        middle = Point_3D.cylindrical_point(15, angle, 0, origin);
        angle += step;
    //    while (angle < two_pi)
    //    {
            Point_3D last = Point_3D.cylindrical_point(15, angle, 0, origin);

            intersecting_mesh.add(new Facet_3D(begin, middle, last));
    //        middle = last;
    //        angle += step;
    //    }

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 3);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.9645,2.5,0), new Point_3D(11.4645,3.53553,0), new Point_3D(15,0,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.9645,2.5,0), new Point_3D(10.6066,10.6066,0), new Point_3D(11.4645,3.53553,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,3.53553,0), new Point_3D(10.6066,10.6066,0), new Point_3D(0,15,0)), result1, 0.0001));
        Assert.assertTrue(result2.size() == 4);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(13.9645,2.5,0), new Point_3D(11.4645,3.53553,0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.9645,2.5,0), new Point_3D(15,0,0), new Point_3D(20,0,0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,3.53553,0), new Point_3D(-14.1421,14.1421,0), new Point_3D(15,0,0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(-14.1421,14.1421,0), new Point_3D(-20,0,0)), result2, 0.0001));
    }

    @Test
    public void test_intersect_meshes_55() {
        // intersecting mesh is on the same plane as mesh
        Mesh_3D mesh = new Mesh_3D();
        // make a 3D circle with radius 20
        final double two_pi = 6.283185307179586;
        final double step = two_pi / 8;
        // generate 3 dimensional circle
        double angle = step;
        Point_3D origin = new Point_3D(0,0,0);
        Point_3D begin = new Point_3D(20, 0, 0); // radius is 20
        Point_3D middle = Point_3D.cylindrical_point(20, angle, 0, origin);
        angle += step;
        while (angle < two_pi) {
            Point_3D last = Point_3D.cylindrical_point(20, angle, 0, origin);

            mesh.add(new Facet_3D(begin, middle, last));
            middle = last;
            angle += step;
        }

        Mesh_3D intersecting_mesh = new Mesh_3D();
        // make a 3D circle with radius 15
        // generate 3 dimensional circle
        begin = new Point_3D(15, 0, 0); // radius is 15
        angle = step;
        middle = Point_3D.cylindrical_point(15, angle, 0, origin);
        angle += step;
        while (angle < two_pi) {
            Point_3D last = Point_3D.cylindrical_point(15, angle, 0, origin);

            intersecting_mesh.add(new Facet_3D(begin, middle, last));
            middle = last;
            angle += step;
        }

//        int count = 0;
//        for (Iterator<Facet_3D> iter = mesh.iterator(); iter.hasNext(); ) {
//            Facet_3D f = iter.next();
//            System.out.println("mesh[" + count++ + "]: p1 x: " + f.get_point1().get_x() + " y: " 
//                    + f.get_point1().get_y() + " z: " + f.get_point1().get_z() + " p2 x: "
//                    + f.get_point2().get_x() + " y: " + f.get_point2().get_y() + " z: "
//                    + f.get_point2().get_z() + " p3 x: " + f.get_point3().get_x() + " y: "
//                    + f.get_point3().get_y() + " z: " + f.get_point3().get_z()); 
//        }
//        count = 0;
//        for (Iterator<Facet_3D> iter = intersecting_mesh.iterator(); iter.hasNext(); ) {
//            Facet_3D f = iter.next();
//            System.out.println("intersecting_mesh[" + count++ + "]: p1 x: " + f.get_point1().get_x() + " y: " 
//                    + f.get_point1().get_y() + " z: " + f.get_point1().get_z() + " p2 x: "
//                    + f.get_point2().get_x() + " y: " + f.get_point2().get_y() + " z: "
//                    + f.get_point2().get_z() + " p3 x: " + f.get_point3().get_x() + " y: "
//                    + f.get_point3().get_y() + " z: " + f.get_point3().get_z()); 
//        }

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 18); 
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,8.53553,0), new Point_3D(8.53553,11.4645,0), new Point_3D(11.4645,3.53553,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,8.53553,0), new Point_3D(10.6066,10.6066,0), new Point_3D(8.53553,11.4645,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.9645,2.5,0), new Point_3D(11.4645,8.53553,0), new Point_3D(11.4645,3.53553,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.9645,2.5,0), new Point_3D(11.4645,3.53553,0), new Point_3D(15,0,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,3.53553,0), new Point_3D(8.53553,11.4645,0), new Point_3D(9.18485e-16,15,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,3.53553,0), new Point_3D(-8.1066,11.6421,0), new Point_3D(15,0,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,3.53553,0), new Point_3D(9.18485e-16,15,0), new Point_3D(-8.1066,11.6421,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(-8.1066,11.6421,0), new Point_3D(-10.6066,10.6066,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(-10.6066,10.6066,0), new Point_3D(-15,1.83697e-15,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(-15,1.83697e-15,0), new Point_3D(-10.6066,-10.6066,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,-3.53553,0), new Point_3D(15,0,0), new Point_3D(-8.1066,-11.6421,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,-3.53553,0), new Point_3D(-8.1066,-11.6421,0), new Point_3D(-2.75546e-15,-15,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(15,0,0), new Point_3D(-10.6066,-10.6066,0), new Point_3D(-8.1066,-11.6421,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,-3.53553,0), new Point_3D(13.9645,-2.5,0), new Point_3D(15,0,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,-3.53553,0), new Point_3D(-2.75546e-15,-15,0), new Point_3D(8.53553,-11.4645,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,-8.53553,0), new Point_3D(13.9645,-2.5,0), new Point_3D(8.53553,-11.4645,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(11.4645,-8.53553,0), new Point_3D(8.53553,-11.4645,0), new Point_3D(10.6066,-10.6066,0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(13.9645,-2.5,0), new Point_3D(11.4645,-3.53553,0), new Point_3D(8.53553,-11.4645,0)), result1, 0.0001));
        Assert.assertTrue(result2.size() == 42);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result2, 0.0001));
    }

    @Test
    public void test_intersect_meshes_56() {
        // intersecting mesh is on the same plane as mesh
        Mesh_3D mesh = new Mesh_3D();
        // make a 3D circle with radius 20
        double two_pi = 6.283185307179586;
        double step = two_pi / 8;
        // generate 3 dimensional circle
        double angle = step;
        Point_3D origin = new Point_3D(0,0,0);
        Point_3D begin = new Point_3D(20, 0, 10); // radius is 20
        Point_3D middle = Point_3D.cylindrical_point(20, angle, 10, origin);
        angle += step;
        while (angle < two_pi) {
            Point_3D last = Point_3D.cylindrical_point(20, angle, 10, origin);

            mesh.add(new Facet_3D(begin, middle, last));
            middle = last;
            angle += step;
        }

        Mesh_3D intersecting_mesh = new Mesh_3D();
        Shapes.cylinder(intersecting_mesh,15,15,10,2,false);

    //    int count = 0;
    //    for (Mesh_3D::const_iterator iter = mesh.begin(); iter != mesh.end(); ++iter)
    //    {
    //        cout << "mesh[" << count++ << "]: p1 x: " << (*iter)->get_point1().get_x() << " y: " 
    //                << (*iter)->get_point1().get_y() << " z: " << (*iter)->get_point1().get_z() << " p2 x: "
    //                << (*iter)->get_point2().get_x() << " y: " << (*iter)->get_point2().get_y() << " z: "
    //                << (*iter)->get_point2().get_z() << " p3 x: " << (*iter)->get_point3().get_x() << " y: "
    //                << (*iter)->get_point3().get_y() << " z: " << (*iter)->get_point3().get_z() << "\n"; 
    //    }
    //    count = 0;
    //    for (Mesh_3D::const_iterator iter = intersecting_mesh.begin(); iter != intersecting_mesh.end(); ++iter)
    //    {
    //        cout << "intersecting_mesh[" << count++ << "]: p1 x: " << (*iter)->get_point1().get_x() << " y: " 
    //                << (*iter)->get_point1().get_y() << " z: " << (*iter)->get_point1().get_z() << " p2 x: "
    //                << (*iter)->get_point2().get_x() << " y: " << (*iter)->get_point2().get_y() << " z: "
    //                << (*iter)->get_point2().get_z() << " p3 x: " << (*iter)->get_point3().get_x() << " y: "
    //                << (*iter)->get_point3().get_y() << " z: " << (*iter)->get_point3().get_z() << "\n"; 
    //    }

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
        Assert.assertTrue(result1.size() == 48);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(result2.size() == 42);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
    }

    @Test
    public void test_intersect_meshes_57() {
        // intersecting mesh is on the same plane as mesh
        Mesh_3D mesh = new Mesh_3D();
        // make a 3D circle with radius 20
        double two_pi = 6.283185307179586;
        double step = two_pi / 8;
        // generate 3 dimensional circle
        double angle = step;
        Point_3D origin = new Point_3D(0,0,0);
        Point_3D begin = new Point_3D(20, 0, 10); // radius is 20
        Point_3D middle = Point_3D.cylindrical_point(20, angle, 10, origin);
        angle += step;
        while (angle < two_pi) {
            Point_3D last = Point_3D.cylindrical_point(20, angle, 10, origin);

            mesh.add(new Facet_3D(begin, middle, last));
            middle = last;
            angle += step;
        }

        Mesh_3D intersecting_mesh = new Mesh_3D();
        Shapes.cylinder(intersecting_mesh,15,15,20,2);

    //    int count = 0;
    //    for (Mesh_3D::const_iterator iter = mesh.begin(); iter != mesh.end(); ++iter)
    //    {
    //        cout << "mesh[" << count++ << "]: p1 x: " << (*iter)->get_point1().get_x() << " y: " 
    //                << (*iter)->get_point1().get_y() << " z: " << (*iter)->get_point1().get_z() << " p2 x: "
    //                << (*iter)->get_point2().get_x() << " y: " << (*iter)->get_point2().get_y() << " z: "
    //                << (*iter)->get_point2().get_z() << " p3 x: " << (*iter)->get_point3().get_x() << " y: "
    //                << (*iter)->get_point3().get_y() << " z: " << (*iter)->get_point3().get_z() << "\n"; 
    //    }
    //    count = 0;
    //    for (Mesh_3D::const_iterator iter = intersecting_mesh.begin(); iter != intersecting_mesh.end(); ++iter)
    //    {
    //        cout << "intersecting_mesh[" << count++ << "]: p1 x: " << (*iter)->get_point1().get_x() << " y: " 
    //                << (*iter)->get_point1().get_y() << " z: " << (*iter)->get_point1().get_z() << " p2 x: "
    //                << (*iter)->get_point2().get_x() << " y: " << (*iter)->get_point2().get_y() << " z: "
    //                << (*iter)->get_point2().get_z() << " p3 x: " << (*iter)->get_point3().get_x() << " y: "
    //                << (*iter)->get_point3().get_y() << " z: " << (*iter)->get_point3().get_z() << "\n"; 
    //    }

        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        Mesh_3D result1 = new Mesh_3D();
        Mesh_3D result2 = new Mesh_3D();
        Assert.assertTrue(intersect_meshes.intersect(intersecting_mesh, mesh, result1, result2));
//        count = 0;
//        for (Iterator<Facet_3D> iter = result1.iterator(); iter.hasNext(); ) {
//            Facet_3D f = iter.next();
//            System.out.println("result1[" + count++ + "]: p1 x: " + f.get_point1().get_x() + " y: " 
//                    + f.get_point1().get_y() + " z: " + f.get_point1().get_z() + " p2 x: "
//                    + f.get_point2().get_x() + " y: " + f.get_point2().get_y() + " z: "
//                    + f.get_point2().get_z() + " p3 x: " + f.get_point3().get_x() + " y: "
//                    + f.get_point3().get_y() + " z: " + f.get_point3().get_z()); 
//        }
//        count = 0;
//        for (Iterator<Facet_3D> iter = result2.iterator(); iter.hasNext(); ) {
//            Facet_3D f = iter.next();
//            System.out.println("result2[" + count++ + "]: p1 x: " + f.get_point1().get_x() + " y: " 
//                    + f.get_point1().get_y() + " z: " + f.get_point1().get_z() + " p2 x: "
//                    + f.get_point2().get_x() + " y: " + f.get_point2().get_y() + " z: "
//                    + f.get_point2().get_z() + " p3 x: " + f.get_point3().get_x() + " y: "
//                    + f.get_point3().get_y() + " z: " + f.get_point3().get_z()); 
//        }
//        System.out.print("intersecting mesh: polyhedron(points=[");
//        boolean first = true;
//        for (Iterator<Point_3D> it = intersecting_mesh.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = intersecting_mesh.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.print("result1: polyhedron(points=[");
//        first = true;
//        for (Iterator<Point_3D> it = result1.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = result1.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.print("mesh: polyhedron(points=[");
//        first = true;
//        for (Iterator<Point_3D> it = mesh.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = mesh.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.print("result2: polyhedron(points=[");
//        first = true;
//        for (Iterator<Point_3D> it = result2.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = result2.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.println("        Assert.assertTrue(result1.size() == " + result1.size() + ");");
//        DecimalFormat decimalFormat = new DecimalFormat("0.00000E0");
//        for (Iterator<Facet_3D> iter = result1.iterator(); iter.hasNext();) {
//            Facet_3D f = iter.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(" + 
//                    decimalFormat.format(f.get_point1().get_x()) + "," + 
//                    decimalFormat.format(f.get_point1().get_y()) + "," + 
//                    decimalFormat.format(f.get_point1().get_z()) + "), new Point_3D(" +
//                    decimalFormat.format(f.get_point2().get_x()) + "," + 
//                    decimalFormat.format(f.get_point2().get_y()) + "," + 
//                    decimalFormat.format(f.get_point2().get_z()) + "), new Point_3D(" + 
//                    decimalFormat.format(f.get_point3().get_x()) + "," + 
//                    decimalFormat.format(f.get_point3().get_y()) + "," + 
//                    decimalFormat.format(f.get_point3().get_z()) + ")), result1, 0.0001));");
//        }
//        System.out.println("        Assert.assertTrue(result2.size() == " + result2.size() + ");");
//        for (Iterator<Facet_3D> iter = result2.iterator(); iter.hasNext();) {
//            Facet_3D f = iter.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(" +  
//                    decimalFormat.format(f.get_point1().get_x()) + "," + 
//                    decimalFormat.format(f.get_point1().get_y()) + "," + 
//                    decimalFormat.format(f.get_point1().get_z()) + "), new Point_3D(" +
//                    decimalFormat.format(f.get_point2().get_x()) + "," + 
//                    decimalFormat.format(f.get_point2().get_y()) + "," + 
//                    decimalFormat.format(f.get_point2().get_z()) + "), new Point_3D(" + 
//                    decimalFormat.format(f.get_point3().get_x()) + "," + 
//                    decimalFormat.format(f.get_point3().get_y()) + "," + 
//                    decimalFormat.format(f.get_point3().get_z()) + ")), result2, 0.0001));");
//        }
        Assert.assertTrue(result1.size() == 76);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.28033E1,5.30330E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(-5.30330E0,1.28033E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(5.30330E0,-1.28033E1,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result1, 0.0001));
        Assert.assertTrue(result2.size() == 54);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(-5.30330E0,1.28033E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(5.30330E0,1.28033E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(5.30330E0,1.28033E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-5.30330E0,1.28033E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.28033E1,5.30330E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.28033E1,5.30330E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.28033E1,5.30330E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result2, 0.0001));
    }

    @Test
    public void test_mesh_difference_1() {
        // shapes do not overlap
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,10,10,10,false, new Point_3D(20,20,20));
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.difference(shape, shape2, result);
        Assert.assertTrue(result.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(0,10,0),new Point_3D(10,10,0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,10,0),new Point_3D(10,0,0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(10,10,10),new Point_3D(0,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(10,0,10),new Point_3D(10,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(0,0,10),new Point_3D(0,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(0,10,10),new Point_3D(0,10,0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(10,10,10),new Point_3D(10,0,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(10,10,0),new Point_3D(10,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,0,10),new Point_3D(0,0,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,0,0),new Point_3D(10,0,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0),new Point_3D(0,10,10),new Point_3D(10,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0),new Point_3D(10,10,10),new Point_3D(10,10,0)), result, precision));
    }

    @Test
    public void test_mesh_difference_2() {
        // shapes do not overlap
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,10,10,10,false, new Point_3D(10,0,0));
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.difference(shape, shape2, result);
        Assert.assertTrue(result.size() == 10);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(0,10,0),new Point_3D(10,10,0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,10,0),new Point_3D(10,0,0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(10,10,10),new Point_3D(0,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10),new Point_3D(10,0,10),new Point_3D(10,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(0,0,10),new Point_3D(0,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(0,10,10),new Point_3D(0,10,0)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,0,10),new Point_3D(0,0,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,0),new Point_3D(10,0,0),new Point_3D(10,0,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0),new Point_3D(0,10,10),new Point_3D(10,10,10)), result, precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0),new Point_3D(10,10,10),new Point_3D(10,10,0)), result, precision));
    }

    @Test
    public void test_mesh_difference_3() {
        // shape2 is smaller than shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,5,5,5);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.difference(shape, shape2, result);
        Assert.assertTrue(result.size() == 24);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(0,10,0), new Point_3D(5,5,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,0), new Point_3D(0,10,0), new Point_3D(10,10,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,5,0), new Point_3D(10,10,0), new Point_3D(5,0,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,0), new Point_3D(10,10,0), new Point_3D(10,0,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(10,10,10), new Point_3D(0,10,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,10), new Point_3D(10,0,10), new Point_3D(10,10,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,0,10), new Point_3D(0,5,5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,5), new Point_3D(0,0,10), new Point_3D(0,10,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,5), new Point_3D(0,10,10), new Point_3D(0,5,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(0,10,10), new Point_3D(0,10,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,10), new Point_3D(10,0,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,0), new Point_3D(10,10,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,5), new Point_3D(10,0,10), new Point_3D(0,0,5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(10,0,10), new Point_3D(0,0,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,0), new Point_3D(10,0,0), new Point_3D(5,0,5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,5), new Point_3D(10,0,0), new Point_3D(10,0,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(0,10,10), new Point_3D(10,10,10)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,10,0), new Point_3D(10,10,10), new Point_3D(10,10,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(0,5,5), new Point_3D(5,5,5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,0,5), new Point_3D(5,5,5), new Point_3D(5,0,5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,0), new Point_3D(5,0,5), new Point_3D(5,5,5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5,0,0), new Point_3D(5,5,5), new Point_3D(5,5,0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(5,5,5), new Point_3D(0,5,5)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0,5,0), new Point_3D(5,5,0), new Point_3D(5,5,5)), result, 0.0001));
    }

    @Test
    public void test_mesh_difference_4() {
        // shape inside shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,20,20,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,15,15,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.difference(shape, shape2, result);
        Assert.assertTrue(result.size() == 96);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
    }

    @Test
    public void test_mesh_difference_5() {
        // shape inside shape, but initial shape is completely inside other shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,15,15,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,20,20,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.difference(shape, shape2, result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_mesh_difference_6() {
        // shapes are the same
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,15,15,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,15,15,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.difference(shape, shape2, result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_mesh_difference_7() {
        // shape2 doesn't pass all the way through shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.ellipsoid(shape,10,40,20,3);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.e_cylinder(shape2,5,20,15,30,20,3);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.difference(shape, shape2, result);
//        System.out.print("result: polyhedron(points=[");
//        boolean first = true;
//        for (Iterator<Point_3D> it = result.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = result.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.println("        Assert.assertTrue(result.size() == " + result.size() + ");");
//        DecimalFormat decimalFormat = new DecimalFormat("0.00000E0");
//        for (Iterator<Facet_3D> iter = result.iterator(); iter.hasNext();) {
//            Facet_3D f = iter.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(" + 
//                    decimalFormat.format(f.get_point1().get_x()) + "," + 
//                    decimalFormat.format(f.get_point1().get_y()) + "," + 
//                    decimalFormat.format(f.get_point1().get_z()) + "), new Point_3D(" +
//                    decimalFormat.format(f.get_point2().get_x()) + "," + 
//                    decimalFormat.format(f.get_point2().get_y()) + "," + 
//                    decimalFormat.format(f.get_point2().get_z()) + "), new Point_3D(" + 
//                    decimalFormat.format(f.get_point3().get_x()) + "," + 
//                    decimalFormat.format(f.get_point3().get_y()) + "," + 
//                    decimalFormat.format(f.get_point3().get_z()) + ")), result, 0.0001));");
//        }
        Assert.assertTrue(result.size() == 214);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1), new Point_3D(2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(2.48452E0,2.81091E1,-1.33333E1), new Point_3D(4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(4.96904E0,2.22222E1,-1.33333E1), new Point_3D(7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(7.45356E0,0.00000E0,-1.33333E1), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1), new Point_3D(0.00000E0,3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(2.48452E0,2.81091E1,-1.33333E1), new Point_3D(3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(4.96904E0,2.22222E1,-1.33333E1), new Point_3D(2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(4.96904E0,2.22222E1,-1.33333E1), new Point_3D(6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(7.45356E0,0.00000E0,-1.33333E1), new Point_3D(4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(0.00000E0,3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(0.00000E0,3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,2.26821E1,1.28125E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-7.38484E0,1.06653E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-7.69908E0,8.61671E0,9.25168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-4.43091E0,2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-5.07188E0,2.26821E1,1.28125E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(-3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(0.00000E0,-3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(0.00000E0,2.98142E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(0.00000E0,3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(0.00000E0,-3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(4.77743E0,2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(4.77743E0,-2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(5.15033E0,-2.30330E1,1.24152E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.00593E0,-2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.78876E0,-1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(-2.29442E0,2.59583E1,1.38434E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(-2.29442E0,2.59583E1,1.38434E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(-2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(0.00000E0,2.98142E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(2.30796E0,-2.61116E1,1.38071E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(2.30796E0,2.61116E1,1.38071E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-3.33333E0,1.49071E1,0.00000E0), new Point_3D(-6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-7.69908E0,-8.61671E0,9.25168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-1.66667E0,1.88562E1,0.00000E0), new Point_3D(-3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,2.26821E1,1.28125E1), new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-4.43091E0,2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-3.33333E0,1.49071E1,0.00000E0), new Point_3D(-4.43091E0,2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-3.33333E0,1.49071E1,0.00000E0), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,1.49071E1,0.00000E0), new Point_3D(-1.66667E0,1.88562E1,0.00000E0), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,-1.49071E1,0.00000E0), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(4.44089E-16,2.00000E1,0.00000E0), new Point_3D(-1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-5.02802E-1,2.66381E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-5.02802E-1,2.66381E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-1.66667E0,1.88562E1,0.00000E0), new Point_3D(-5.02802E-1,2.66381E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(-1.66667E0,1.88562E1,0.00000E0), new Point_3D(1.33973E-16,2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.66667E0,1.88562E1,0.00000E0), new Point_3D(4.44089E-16,2.00000E1,0.00000E0), new Point_3D(1.33973E-16,2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.66667E0,-1.88562E1,0.00000E0), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(1.66667E0,1.88562E1,0.00000E0), new Point_3D(4.44089E-16,2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0), new Point_3D(1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(4.44089E-16,2.00000E1,0.00000E0), new Point_3D(3.35688E0,2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(4.44089E-16,2.00000E1,0.00000E0), new Point_3D(3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.44089E-16,2.00000E1,0.00000E0), new Point_3D(1.66667E0,1.88562E1,0.00000E0), new Point_3D(3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(3.35688E0,-2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.44089E-16,-2.00000E1,0.00000E0), new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0), new Point_3D(1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(1.66667E0,-1.88562E1,0.00000E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(6.00593E0,2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(6.00593E0,2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(1.66667E0,1.88562E1,0.00000E0), new Point_3D(6.00593E0,2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(1.66667E0,1.88562E1,0.00000E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.66667E0,1.88562E1,0.00000E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(4.77743E0,-2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(1.66667E0,-1.88562E1,0.00000E0), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.66667E0,-1.88562E1,0.00000E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(7.38484E0,-1.06653E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(6.78876E0,-1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_intersection_1() {
        // shapes do not overlap
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,10,10,10,false, new Point_3D(20,20,20));
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.intersection(shape, shape2, result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_mesh_intersection_2() {
        // shapes do not overlap
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,10,10,10,false, new Point_3D(10,0,0));
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.intersection(shape, shape2, result);
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(10,10,10),new Point_3D(10,0,10)), result,precision));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(10,0,0),new Point_3D(10,10,0),new Point_3D(10,10,10)),result,precision));
    }

    @Test
    public void test_mesh_intersection_3() {
        // shape2 is smaller than shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,5,5,5);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.intersection(shape, shape2, result);
        Assert.assertTrue(result.size() == 12);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(5.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,5.00000E0,0.00000E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,5.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,5.00000E0), new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(5.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(5.00000E0,5.00000E0,5.00000E0), new Point_3D(0.00000E0,5.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(5.00000E0,0.00000E0,5.00000E0), new Point_3D(5.00000E0,5.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(5.00000E0,5.00000E0,5.00000E0), new Point_3D(5.00000E0,0.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(5.00000E0,5.00000E0,0.00000E0), new Point_3D(5.00000E0,5.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,5.00000E0,5.00000E0), new Point_3D(5.00000E0,5.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(5.00000E0,5.00000E0,5.00000E0), new Point_3D(5.00000E0,5.00000E0,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_intersection_4() {
        // shape inside shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,20,20,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,15,15,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.intersection(shape, shape2, result);
        Assert.assertTrue(result.size() == 68);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_intersection_5() {
        // shape inside shape, but initial shape is completely inside other shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,15,15,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,20,20,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.intersection(shape, shape2, result);
        Assert.assertTrue(result.size() == 68);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.14645E1,3.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_intersection_6() {
        // shapes are the same
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,15,15,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,15,15,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.intersection(shape, shape2, result);
        Assert.assertTrue(result.size() == 28);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_intersection_7() {
        // shape2 doesn't pass all the way through shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.ellipsoid(shape,10,40,20,3);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.e_cylinder(shape2,5,20,15,30,20,3);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.intersection(shape, shape2, result);
//        System.out.print("result: polyhedron(points=[");
//        boolean first = true;
//        for (Iterator<Point_3D> it = result.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = result.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.println("        Assert.assertTrue(result.size() == " + result.size() + ");");
//        DecimalFormat decimalFormat = new DecimalFormat("0.00000E0");
//        for (Iterator<Facet_3D> iter = result.iterator(); iter.hasNext();) {
//            Facet_3D f = iter.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(" + 
//                    decimalFormat.format(f.get_point1().get_x()) + "," + 
//                    decimalFormat.format(f.get_point1().get_y()) + "," + 
//                    decimalFormat.format(f.get_point1().get_z()) + "), new Point_3D(" +
//                    decimalFormat.format(f.get_point2().get_x()) + "," + 
//                    decimalFormat.format(f.get_point2().get_y()) + "," + 
//                    decimalFormat.format(f.get_point2().get_z()) + "), new Point_3D(" + 
//                    decimalFormat.format(f.get_point3().get_x()) + "," + 
//                    decimalFormat.format(f.get_point3().get_y()) + "," + 
//                    decimalFormat.format(f.get_point3().get_z()) + ")), result, 0.0001));");
//        }
        Assert.assertTrue(result.size() == 118);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.96904E0,2.22222E1,1.33333E1), new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-7.45356E0,0.00000E0,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-5.07188E0,2.26821E1,1.28125E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-5.07188E0,2.26821E1,1.28125E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,2.26821E1,1.28125E1), new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-4.96904E0,-2.22222E1,1.33333E1), new Point_3D(-9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.96904E0,-2.22222E1,1.33333E1), new Point_3D(-7.45356E0,0.00000E0,1.33333E1), new Point_3D(-9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-7.38484E0,-1.06653E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-7.69908E0,-8.61671E0,9.25168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-4.96904E0,-2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-5.07188E0,2.26821E1,1.28125E1), new Point_3D(-4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-4.43091E0,-2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-4.96904E0,-2.22222E1,1.33333E1), new Point_3D(-4.43091E0,-2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-4.96904E0,-2.22222E1,1.33333E1), new Point_3D(-5.07188E0,-2.26821E1,1.28125E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(4.77743E0,2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(3.67500E0,-2.52884E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(4.96904E0,-2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(4.96904E0,-2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(6.78876E0,1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(6.78876E0,1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(7.45356E0,0.00000E0,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.45356E0,0.00000E0,1.33333E1), new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(7.45356E0,0.00000E0,1.33333E1), new Point_3D(7.38484E0,1.06653E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(7.45356E0,0.00000E0,1.33333E1), new Point_3D(9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(6.00593E0,-2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(5.15033E0,-2.30330E1,1.24152E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(7.45356E0,0.00000E0,1.33333E1), new Point_3D(5.15033E0,-2.30330E1,1.24152E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.45356E0,0.00000E0,1.33333E1), new Point_3D(4.96904E0,-2.22222E1,1.33333E1), new Point_3D(5.15033E0,-2.30330E1,1.24152E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(7.45356E0,0.00000E0,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(7.45356E0,0.00000E0,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(-4.96904E0,2.22222E1,1.33333E1), new Point_3D(-7.45356E0,0.00000E0,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(-7.45356E0,0.00000E0,1.33333E1), new Point_3D(-4.96904E0,-2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(-4.96904E0,-2.22222E1,1.33333E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(-2.29442E0,-2.59583E1,1.38434E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(3.67500E0,2.52884E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(3.67500E0,2.52884E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(4.96904E0,2.22222E1,1.33333E1), new Point_3D(3.67500E0,2.52884E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(2.30796E0,-2.61116E1,1.38071E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(4.96904E0,-2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(7.45356E0,0.00000E0,1.33333E1), new Point_3D(4.96904E0,2.22222E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(4.96904E0,-2.22222E1,1.33333E1), new Point_3D(7.45356E0,0.00000E0,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-7.69908E0,8.61671E0,9.25168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-3.33333E0,1.49071E1,0.00000E0), new Point_3D(-1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,2.26821E1,1.28125E1), new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,1.49071E1,0.00000E0), new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-4.43091E0,-2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-3.33333E0,-1.49071E1,0.00000E0), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,-1.49071E1,0.00000E0), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(-1.66667E0,1.88562E1,0.00000E0), new Point_3D(4.44089E-16,2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(-1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(-1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.66667E0,1.88562E1,0.00000E0), new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(4.44089E-16,2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(-1.66667E0,-1.88562E1,0.00000E0), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.66667E0,-1.88562E1,0.00000E0), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(4.44089E-16,2.00000E1,0.00000E0), new Point_3D(1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(1.66667E0,-1.88562E1,0.00000E0), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(4.44089E-16,2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(4.44089E-16,2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.44089E-16,2.00000E1,0.00000E0), new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(3.35688E0,-2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(4.44089E-16,-2.00000E1,0.00000E0), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.44089E-16,-2.00000E1,0.00000E0), new Point_3D(1.66667E0,-1.88562E1,0.00000E0), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(1.66667E0,1.88562E1,0.00000E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0), new Point_3D(1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(4.77743E0,2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(1.66667E0,1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.66667E0,1.88562E1,0.00000E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(6.00593E0,-2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(6.00593E0,-2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(1.66667E0,-1.88562E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(1.66667E0,-1.88562E1,0.00000E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.66667E0,-1.88562E1,0.00000E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.00000E0,0.00000E0,0.00000E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(3.33333E0,1.49071E1,0.00000E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(3.33333E0,-1.49071E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_merge_1() {
        // shapes do not overlap
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,10,10,10,false, new Point_3D(20,20,20));
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.merge(shape, shape2, result);
        Assert.assertTrue(result.size() == 24);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(1.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(0.00000E0,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(0.00000E0,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,1.00000E1,1.00000E1), new Point_3D(0.00000E0,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(1.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(0.00000E0,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(0.00000E0,1.00000E1,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(1.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,2.00000E1), new Point_3D(2.00000E1,3.00000E1,2.00000E1), new Point_3D(3.00000E1,3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,2.00000E1), new Point_3D(3.00000E1,3.00000E1,2.00000E1), new Point_3D(3.00000E1,2.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,3.00000E1), new Point_3D(3.00000E1,3.00000E1,3.00000E1), new Point_3D(2.00000E1,3.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,3.00000E1), new Point_3D(3.00000E1,2.00000E1,3.00000E1), new Point_3D(3.00000E1,3.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,2.00000E1), new Point_3D(2.00000E1,2.00000E1,3.00000E1), new Point_3D(2.00000E1,3.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,2.00000E1), new Point_3D(2.00000E1,3.00000E1,3.00000E1), new Point_3D(2.00000E1,3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.00000E1,2.00000E1,2.00000E1), new Point_3D(3.00000E1,3.00000E1,3.00000E1), new Point_3D(3.00000E1,2.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.00000E1,2.00000E1,2.00000E1), new Point_3D(3.00000E1,3.00000E1,2.00000E1), new Point_3D(3.00000E1,3.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,2.00000E1), new Point_3D(3.00000E1,2.00000E1,3.00000E1), new Point_3D(2.00000E1,2.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,2.00000E1,2.00000E1), new Point_3D(3.00000E1,2.00000E1,2.00000E1), new Point_3D(3.00000E1,2.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,3.00000E1,2.00000E1), new Point_3D(2.00000E1,3.00000E1,3.00000E1), new Point_3D(3.00000E1,3.00000E1,3.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,3.00000E1,2.00000E1), new Point_3D(3.00000E1,3.00000E1,3.00000E1), new Point_3D(3.00000E1,3.00000E1,2.00000E1)), result, 0.0001));
    }

    @Test
    public void test_mesh_merge_2() {
        // shapes do not overlap
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,10,10,10,false, new Point_3D(10,0,0));
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.merge(shape, shape2, result);
        Assert.assertTrue(result.size() == 22);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(1.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(0.00000E0,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(0.00000E0,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,1.00000E1,1.00000E1), new Point_3D(0.00000E0,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(1.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(0.00000E0,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(0.00000E0,1.00000E1,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(1.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(2.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,1.00000E1,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(2.00000E1,1.00000E1,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(2.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,1.00000E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,1.00000E1,0.00000E0), new Point_3D(2.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(2.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(2.00000E1,1.00000E1,1.00000E1), new Point_3D(2.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_merge_3() {
        // shape2 is smaller than shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape,10,10,10);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cuboid(shape2,5,5,5);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.merge(shape, shape2, result);
        Assert.assertTrue(result.size() == 24);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(5.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(5.00000E0,5.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,5.00000E0,0.00000E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,5.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(5.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(1.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(0.00000E0,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,5.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(0.00000E0,5.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,1.00000E1), new Point_3D(0.00000E0,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,5.00000E0), new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,5.00000E0), new Point_3D(0.00000E0,1.00000E1,1.00000E1), new Point_3D(0.00000E0,5.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,5.00000E0,0.00000E0), new Point_3D(0.00000E0,1.00000E1,1.00000E1), new Point_3D(0.00000E0,1.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(1.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,5.00000E0), new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(0.00000E0,0.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,5.00000E0), new Point_3D(1.00000E1,0.00000E0,1.00000E1), new Point_3D(0.00000E0,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(5.00000E0,0.00000E0,5.00000E0), new Point_3D(0.00000E0,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(5.00000E0,0.00000E0,5.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.00000E0,0.00000E0,5.00000E0), new Point_3D(1.00000E1,0.00000E0,0.00000E0), new Point_3D(1.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(0.00000E0,1.00000E1,1.00000E1), new Point_3D(1.00000E1,1.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,1.00000E1,0.00000E0), new Point_3D(1.00000E1,1.00000E1,1.00000E1), new Point_3D(1.00000E1,1.00000E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_merge_4() {
        // shape inside shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,20,20,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,15,15,20,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.merge(shape, shape2, result);
        Assert.assertTrue(result.size() == 128);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(5.30330E0,1.28033E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-5.30330E0,1.28033E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.28033E1,5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.28033E1,5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
    }

    @Test
    public void test_mesh_merge_5() {
        // shape inside shape, but initial shape is completely inside other shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,15,15,20,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,20,20,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.merge(shape, shape2, result);
        Assert.assertTrue(result.size() == 128);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.39645E1,2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.50000E1,3.06162E-16,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,3.06162E-16,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1), new Point_3D(1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,3.53553E0,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(9.18485E-16,1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-1.50000E1,2.14313E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,2.14313E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-1.50000E1,1.83697E-15,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1), new Point_3D(-1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(8.53553E0,-1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.14645E1,-3.53553E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,3.06162E-16,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(8.53553E0,1.14645E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(1.14645E1,8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,1.14645E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(5.30330E0,1.28033E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-5.30330E0,1.28033E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,1.28033E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-8.10660E0,1.16421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.28033E1,5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,5.30330E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.14645E1,8.53553E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(1.22465E-15,2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.22465E-15,2.00000E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-8.10660E0,1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.39645E1,2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,2.50000E0,1.00000E1), new Point_3D(1.50000E1,3.06162E-16,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,1.16421E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.28033E1,5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,5.30330E0,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-1.50000E1,2.14313E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,2.14313E-15,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-2.00000E1,2.44929E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,3.06162E-16,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.28033E1,-5.30330E0,1.00000E1), new Point_3D(-1.50000E1,2.14313E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,2.14313E-15,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-2.00000E1,2.44929E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.00000E1,2.44929E-15,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-8.10660E0,-1.16421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.39645E1,-2.50000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.28033E1,-5.30330E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.28033E1,-5.30330E0,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.39645E1,-2.50000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.30330E0,-1.28033E1,1.00000E1), new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-8.10660E0,-1.16421E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(-1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.41421E1,-1.41421E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.14645E1,-8.53553E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.14645E1,-8.53553E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(2.00000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.53553E0,-1.14645E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(-3.67394E-15,-2.00000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.67394E-15,-2.00000E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(2.00000E1,0.00000E0,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.00000E1,0.00000E0,0.00000E0), new Point_3D(1.41421E1,-1.41421E1,1.00000E1), new Point_3D(1.41421E1,-1.41421E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_merge_6() {
        // shapes are the same
        Mesh_3D shape = new Mesh_3D();
        Shapes.cylinder(shape,15,15,10,2);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.cylinder(shape2,15,15,10,2);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.merge(shape, shape2, result);
        Assert.assertTrue(result.size() == 28);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(1.50000E1,0.00000E0,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(9.18485E-16,1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(9.18485E-16,1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.18485E-16,1.50000E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.50000E1,1.83697E-15,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-1.50000E1,1.83697E-15,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,1.83697E-15,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(-1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(-2.75546E-15,-1.50000E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.75546E-15,-1.50000E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.50000E1,0.00000E0,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,1.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.50000E1,0.00000E0,0.00000E0), new Point_3D(1.06066E1,-1.06066E1,1.00000E1), new Point_3D(1.06066E1,-1.06066E1,0.00000E0)), result, 0.0001));
    }

    @Test
    public void test_mesh_merge_7() {
        // shape2 doesn't pass all the way through shape
        Mesh_3D shape = new Mesh_3D();
        Shapes.ellipsoid(shape,10,40,20,3);
        Mesh_3D shape2 = new Mesh_3D();
        Shapes.e_cylinder(shape2,5,20,15,30,20,3);
        Mesh_3D result = new Mesh_3D();
        Intersect_Meshes_3D intersect_meshes = new Intersect_Meshes_3D();
        intersect_meshes.merge(shape, shape2, result);
//        System.out.print("result: polyhedron(points=[");
//        boolean first = true;
//        for (Iterator<Point_3D> it = result.point_iterator(); it.hasNext();) {
//            Point_3D p = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            System.out.print("[" + p.get_x() + "," + p.get_y() + "," + p.get_z() + "]");
//        }
//        System.out.print("], faces=[");
//        first = true;
//        for (Iterator<Facet> it = result.facet_iterator(); it.hasNext();) {
//            Facet f = it.next();
//            if (first)
//                first = false;
//            else
//                System.out.print(',');
//            // write points in clockwise order because openscad prefers it
//            System.out.print("[" + f.get_p1_index() + "," + f.get_p3_index() + "," + f.get_p2_index() + "]");
//        }
//        System.out.println("], convexity=4);");
//        System.out.println("        Assert.assertTrue(result.size() == " + result.size() + ");");
//        DecimalFormat decimalFormat = new DecimalFormat("0.00000E0");
//        for (Iterator<Facet_3D> iter = result.iterator(); iter.hasNext();) {
//            Facet_3D f = iter.next();
//            System.out.println("        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(" + 
//                    decimalFormat.format(f.get_point1().get_x()) + "," + 
//                    decimalFormat.format(f.get_point1().get_y()) + "," + 
//                    decimalFormat.format(f.get_point1().get_z()) + "), new Point_3D(" +
//                    decimalFormat.format(f.get_point2().get_x()) + "," + 
//                    decimalFormat.format(f.get_point2().get_y()) + "," + 
//                    decimalFormat.format(f.get_point2().get_z()) + "), new Point_3D(" + 
//                    decimalFormat.format(f.get_point3().get_x()) + "," + 
//                    decimalFormat.format(f.get_point3().get_y()) + "," + 
//                    decimalFormat.format(f.get_point3().get_z()) + ")), result, 0.0001));");
//        }
        Assert.assertTrue(result.size() == 216);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1), new Point_3D(2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(2.48452E0,2.81091E1,-1.33333E1), new Point_3D(4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(4.96904E0,2.22222E1,-1.33333E1), new Point_3D(7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,-2.00000E1), new Point_3D(7.45356E0,0.00000E0,-1.33333E1), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-7.45356E0,0.00000E0,-1.33333E1), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1), new Point_3D(-2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(-2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1), new Point_3D(0.00000E0,3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(2.48452E0,2.81091E1,-1.33333E1), new Point_3D(-8.88178E-16,2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(-8.88178E-16,-2.98142E1,-1.33333E1), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(2.48452E0,2.81091E1,-1.33333E1), new Point_3D(3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(4.96904E0,2.22222E1,-1.33333E1), new Point_3D(2.48452E0,2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(2.48452E0,-2.81091E1,-1.33333E1), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(4.96904E0,2.22222E1,-1.33333E1), new Point_3D(6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(7.45356E0,0.00000E0,-1.33333E1), new Point_3D(4.96904E0,2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(4.96904E0,-2.22222E1,-1.33333E1), new Point_3D(7.45356E0,0.00000E0,-1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,-6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(-6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(-3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(-3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(0.00000E0,3.77124E1,-6.66667E0), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(0.00000E0,3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(0.00000E0,-3.77124E1,-6.66667E0), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(3.14270E0,3.55556E1,-6.66667E0), new Point_3D(3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(3.14270E0,3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(3.14270E0,-3.55556E1,-6.66667E0), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.28539E0,2.81091E1,-6.66667E0), new Point_3D(6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(9.42809E0,0.00000E0,-6.66667E0), new Point_3D(6.28539E0,2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.28539E0,-2.81091E1,-6.66667E0), new Point_3D(9.42809E0,0.00000E0,-6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15), new Point_3D(-9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-1.00000E1,0.00000E0,1.77636E-15), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15), new Point_3D(-6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(-6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15), new Point_3D(-3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(-3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(-3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15), new Point_3D(0.00000E0,3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(8.88178E-16,4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(8.88178E-16,-4.00000E1,1.77636E-15), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(3.33333E0,3.77124E1,1.77636E-15), new Point_3D(3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(3.33333E0,3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(3.33333E0,-3.77124E1,1.77636E-15), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.66667E0,2.98142E1,1.77636E-15), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(1.00000E1,0.00000E0,1.77636E-15), new Point_3D(6.66667E0,2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.66667E0,-2.98142E1,1.77636E-15), new Point_3D(1.00000E1,0.00000E0,1.77636E-15)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,2.26821E1,1.28125E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-7.38484E0,1.06653E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-7.69908E0,8.61671E0,9.25168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-9.42809E0,0.00000E0,6.66667E0), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-4.43091E0,2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-6.28539E0,2.81091E1,6.66667E0), new Point_3D(-5.07188E0,2.26821E1,1.28125E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(-6.28539E0,-2.81091E1,6.66667E0), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(-3.14270E0,3.55556E1,6.66667E0), new Point_3D(-2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(-3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(-3.14270E0,-3.55556E1,6.66667E0), new Point_3D(0.00000E0,-3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(0.00000E0,3.77124E1,6.66667E0), new Point_3D(0.00000E0,2.98142E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(0.00000E0,3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(0.00000E0,-3.77124E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(0.00000E0,-3.77124E1,6.66667E0), new Point_3D(3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(3.14270E0,3.55556E1,6.66667E0), new Point_3D(2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(4.77743E0,2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(6.28539E0,2.81091E1,6.66667E0), new Point_3D(3.14270E0,3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(4.77743E0,-2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1), new Point_3D(3.14270E0,-3.55556E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(5.15033E0,-2.30330E1,1.24152E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(3.14270E0,-3.55556E1,6.66667E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(9.42809E0,0.00000E0,6.66667E0), new Point_3D(6.28539E0,2.81091E1,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.00593E0,-2.06810E1,1.04142E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(6.78876E0,-1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(6.28539E0,-2.81091E1,6.66667E0), new Point_3D(9.42809E0,0.00000E0,6.66667E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(-2.48452E0,2.81091E1,1.33333E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(-2.29442E0,2.59583E1,1.38434E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(-2.29442E0,2.59583E1,1.38434E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(0.00000E0,2.98142E1,1.33333E1), new Point_3D(-2.48452E0,2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(-2.48452E0,-2.81091E1,1.33333E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(0.00000E0,2.98142E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(2.30796E0,-2.61116E1,1.38071E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(0.00000E0,-2.98142E1,1.33333E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(2.48452E0,2.81091E1,1.33333E1), new Point_3D(2.30796E0,2.61116E1,1.38071E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(2.48452E0,-2.81091E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(-7.69908E0,8.61671E0,9.25168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,8.61671E0,9.25168E0), new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(-7.38484E0,1.06653E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,1.06653E1,9.53936E0), new Point_3D(-1.00000E1,2.23607E1,2.00000E1), new Point_3D(-6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-9.02084E0,0.00000E0,8.04168E0), new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-1.50000E1,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.69908E0,-8.61671E0,9.25168E0), new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-1.50000E1,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-1.00000E1,-2.23607E1,2.00000E1), new Point_3D(-1.50000E1,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-7.38484E0,-1.06653E1,9.53936E0), new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-1.00000E1,-2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(-5.00000E0,2.82843E1,2.00000E1), new Point_3D(-1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(-1.00000E1,-2.23607E1,2.00000E1), new Point_3D(-5.00000E0,-2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,1.84622E1,9.53936E0), new Point_3D(-1.00000E1,2.23607E1,2.00000E1), new Point_3D(-5.07188E0,2.26821E1,1.28125E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,2.26821E1,1.28125E1), new Point_3D(-1.00000E1,2.23607E1,2.00000E1), new Point_3D(-4.43091E0,2.37166E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-1.00000E1,2.23607E1,2.00000E1), new Point_3D(-5.00000E0,2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,2.37166E1,1.31710E1), new Point_3D(-5.00000E0,2.82843E1,2.00000E1), new Point_3D(-3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-6.51312E0,-1.84622E1,9.53936E0), new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-1.00000E1,-2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.07188E0,-2.26821E1,1.28125E1), new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-1.00000E1,-2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-5.00000E0,-2.82843E1,2.00000E1), new Point_3D(-1.00000E1,-2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-4.43091E0,-2.37166E1,1.31710E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(-5.00000E0,-2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1), new Point_3D(-5.00000E0,2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(-5.00000E0,-2.82843E1,2.00000E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,2.59583E1,1.38434E1), new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,2.52025E1,1.33333E1), new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,2.50650E1,1.31710E1), new Point_3D(-5.00000E0,2.82843E1,2.00000E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,2.66381E1,1.39664E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1), new Point_3D(1.33973E-16,2.69832E1,1.39664E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1), new Point_3D(-2.29442E0,-2.59583E1,1.38434E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-2.29442E0,-2.59583E1,1.38434E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1), new Point_3D(-3.71122E0,-2.52025E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.71122E0,-2.52025E1,1.33333E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1), new Point_3D(-3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-3.86183E0,-2.50650E1,1.31710E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1), new Point_3D(-5.00000E0,-2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-5.02802E-1,-2.66381E1,1.39664E1), new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(5.00000E0,2.82843E1,2.00000E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1), new Point_3D(5.00000E0,-2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,2.69832E1,1.39664E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1), new Point_3D(2.30796E0,2.61116E1,1.38071E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,2.61116E1,1.38071E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1), new Point_3D(3.35688E0,2.55619E1,1.34275E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(0.00000E0,3.00000E1,2.00000E1), new Point_3D(5.00000E0,2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,2.55619E1,1.34275E1), new Point_3D(5.00000E0,2.82843E1,2.00000E1), new Point_3D(3.67500E0,2.52884E1,1.33333E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,2.52884E1,1.33333E1), new Point_3D(5.00000E0,2.82843E1,2.00000E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(1.33973E-16,-2.69832E1,1.39664E1), new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2.30796E0,-2.61116E1,1.38071E1), new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(5.00000E0,-2.82843E1,2.00000E1), new Point_3D(0.00000E0,-3.00000E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.35688E0,-2.55619E1,1.34275E1), new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(5.00000E0,-2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.67500E0,-2.52884E1,1.33333E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(5.00000E0,-2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1), new Point_3D(5.00000E0,2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(5.00000E0,-2.82843E1,2.00000E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,2.30330E1,1.24152E1), new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,2.36211E1,1.26339E1), new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,2.50650E1,1.31710E1), new Point_3D(5.00000E0,2.82843E1,2.00000E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,2.06810E1,1.04142E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1), new Point_3D(6.51312E0,1.84622E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(5.15033E0,-2.30330E1,1.24152E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(5.15033E0,-2.30330E1,1.24152E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(4.77743E0,-2.36211E1,1.26339E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(4.77743E0,-2.36211E1,1.26339E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(3.86183E0,-2.50650E1,1.31710E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(3.86183E0,-2.50650E1,1.31710E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(5.00000E0,-2.82843E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.00593E0,-2.06810E1,1.04142E1), new Point_3D(6.51312E0,-1.84622E1,9.53936E0), new Point_3D(1.00000E1,-2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(-1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(0.00000E0,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(0.00000E0,0.00000E0,2.00000E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(1.00000E1,2.23607E1,2.00000E1), new Point_3D(9.02084E0,0.00000E0,8.04168E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(1.00000E1,2.23607E1,2.00000E1), new Point_3D(1.50000E1,0.00000E0,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,1.06653E1,9.53936E0), new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,1.59968E1,9.53936E0), new Point_3D(6.51312E0,1.84622E1,9.53936E0), new Point_3D(1.00000E1,2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(9.02084E0,0.00000E0,8.04168E0), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(7.38484E0,-1.06653E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(1.50000E1,0.00000E0,2.00000E1), new Point_3D(1.00000E1,-2.23607E1,2.00000E1)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(7.38484E0,-1.06653E1,9.53936E0), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(6.78876E0,-1.59968E1,9.53936E0)), result, 0.0001));
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(6.78876E0,-1.59968E1,9.53936E0), new Point_3D(1.00000E1,-2.23607E1,2.00000E1), new Point_3D(6.51312E0,-1.84622E1,9.53936E0)), result, 0.0001));
    }
}
