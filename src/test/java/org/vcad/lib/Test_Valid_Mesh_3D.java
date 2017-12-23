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
 * File: Test_Valid_Mesh_3D.java
 */
package org.vcad.lib;

import java.util.Iterator;
import java.util.List;
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
public class Test_Valid_Mesh_3D extends TestBase {
    
    public Test_Valid_Mesh_3D() {
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
    public void test_valid_mesh_1() {
        Mesh_3D mesh = new Mesh_3D();

        // invalid mesh
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(3,3,3), new Point_3D(0,3,3)));
        mesh.add(new Facet_3D(new Point_3D(0,3,3), new Point_3D(3,3,3), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,3,3), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,10), new Point_3D(5,5,5)));

        Valid_Mesh_3D valid = new Valid_Mesh_3D(mesh);
        Assert.assertTrue(!valid.validate());
        Assert.assertTrue(!valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 1);
        Iterator<Point_3D> pt_iter = valid.pts_on_side_iterator();
        Assert.assertTrue(pt_iter.hasNext());
        Assert.assertTrue(Point_3D.is_equal(pt_iter.next(), new Point_3D(3,3,3), precision));
        Assert.assertTrue(valid.facets_inside_facet_empty());
        Assert.assertTrue(valid.facets_inside_facet_size() == 0);
        Assert.assertTrue(!valid.edge_facets_empty());
        Assert.assertTrue(valid.edge_facets_size() == 6);
        Iterator<Facet_3D> facet_iter = valid.edge_facets_iterator();
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(3,3,3), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(5,5,5), new Point_3D(0,10,10)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(3,3,3), new Point_3D(0,3,3)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,10), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(valid.too_many_share_side_size() == 0);
        Assert.assertTrue(valid.too_many_share_side_empty());
    }

    @Test
    public void test_valid_mesh_2() {
        Mesh_3D mesh = new Mesh_3D();

        // invalid mesh
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,3,3)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(2,2,10)));
        mesh.add(new Facet_3D(new Point_3D(0,3,3), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,10), new Point_3D(5,5,5)));

        Valid_Mesh_3D valid = new Valid_Mesh_3D(mesh);
        Assert.assertTrue(!valid.validate());
        Assert.assertTrue(valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 0);
        Assert.assertTrue(valid.facets_inside_facet_empty());
        Assert.assertTrue(valid.facets_inside_facet_size() == 0);
        Assert.assertTrue(!valid.edge_facets_empty());
        Assert.assertTrue(valid.edge_facets_size() == 6);
        Iterator<Facet_3D> facet_iter = valid.edge_facets_iterator();
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,3,3)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(2,2,10)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(5,5,5), new Point_3D(0,10,10)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,10), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(!valid.too_many_share_side_empty());
        Assert.assertTrue(valid.too_many_share_side_size() == 1);
        Iterator<List<Facet_3D>> list_iter = valid.too_many_share_side_iterator();
        Assert.assertTrue(list_iter.hasNext());
        List<Facet_3D> v = list_iter.next();
        Assert.assertTrue(v.size() == 3);
        facet_iter = v.iterator();
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,3,3)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(2,2,10)), precision));
    }

    @Test
    public void test_valid_mesh_3() {
        Mesh_3D mesh = new Mesh_3D();

        // invalid mesh
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,3,3)));
        mesh.add(new Facet_3D(new Point_3D(0,0,0), new Point_3D(3,3,3), new Point_3D(0,3,3)));
        mesh.add(new Facet_3D(new Point_3D(0,3,3), new Point_3D(3,3,3), new Point_3D(5,5,5)));
        mesh.add(new Facet_3D(new Point_3D(0,3,3), new Point_3D(5,5,5), new Point_3D(0,10,10)));
        mesh.add(new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)));
        mesh.add(new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,10), new Point_3D(5,5,5)));

        Valid_Mesh_3D valid = new Valid_Mesh_3D(mesh);
        Assert.assertTrue(!valid.validate());
        Assert.assertTrue(!valid.pts_on_side_empty());
//        cout << "pts on side size: " << valid.pts_on_side_size() << "\n";
//        for (Valid_Mesh_3D::pt_on_side_iterator it = valid.pts_on_side_begin(); it != valid.pts_on_side_end(); ++it)
//        {
//            cout << "pt on side x: " << (*it)->get_x() << " y: " << (*it)->get_y() << " z: " << (*it)->get_z() << "\n";
//        }
        Assert.assertTrue(valid.pts_on_side_size() == 1);
        Iterator<Point_3D> pt_iter = valid.pts_on_side_iterator();
        Assert.assertTrue(pt_iter.hasNext());
        Assert.assertTrue(Point_3D.is_equal(pt_iter.next(), new Point_3D(3,3,3), precision));
        Assert.assertTrue(!valid.facets_inside_facet_empty());
//        cout << "Facets inside facet size: " << valid.facets_inside_facet_size() << "\n";
//        for (Valid_Mesh_3D::facets_inside_facet_iterator it = valid.facets_inside_facet_begin(); it != valid.facets_inside_facet_end(); ++it)
//        {
//            cout << "Larger facet p1 x: " << it->first.get_point1()->get_x() << " y: " << it->first.get_point1()->get_y() << 
//                    " z: " << it->first.get_point1()->get_z() << " p2 x: " << it->first.get_point2()->get_x() << " y: " << 
//                    it->first.get_point2()->get_y() << " z: " << it->first.get_point2()->get_z() << " p3 x: " << 
//                    it->first.get_point3()->get_x() << " y: " << it->first.get_point3()->get_y() << " z: " << 
//                    it->first.get_point3()->get_z() << "\n";
//            for (vector<Facet_3D>::const_iterator it2 = it->second.begin(); it2 != it->second.end(); ++it2)
//            {
//                cout << "    Facet p1 x: " << it2->get_point1()->get_x() << " y: " << it2->get_point1()->get_y() << 
//                        " z: " << it2->get_point1()->get_z() << " p2 x: " << it2->get_point2()->get_x() << " y: " << 
//                        it2->get_point2()->get_y() << " z: " << it2->get_point2()->get_z() << " p3 x: " << 
//                        it2->get_point3()->get_x() << " y: " << it2->get_point3()->get_y() << " z: " << 
//                        it2->get_point3()->get_z() << "\n";
//            }
//        }
        Assert.assertTrue(valid.facets_inside_facet_size() == 1);
        Iterator<Valid_Mesh_3D.FacetsInsideFacet> iter = valid.facets_inside_facet_iterator();
        Assert.assertTrue(iter.hasNext());
        Valid_Mesh_3D.FacetsInsideFacet p = iter.next();
        Assert.assertTrue(Facet_3D.is_equal(p.getFacet(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,3,3)), precision));
        Assert.assertTrue(p.insideFacetsSize() == 2);
        Iterator<Facet_3D> facet_iter = p.getInsideFacets();
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(3,3,3), new Point_3D(0,3,3)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(3,3,3), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(!valid.edge_facets_empty());
//        System.out.println("edge_facets_size: " + valid.edge_facets_size());
//        for (Iterator<Facet_3D> ef_iter = valid.edge_facets_iterator(); ef_iter.hasNext();) {
//            Facet_3D f = ef_iter.next();
//            System.out.println("edge facet p1 x: " + f.get_point1().get_x() + " y: " + f.get_point1().get_y() + " z: " + 
//                    f.get_point1().get_z() + " p2 x: " + f.get_point2().get_x() + " y: " + f.get_point2().get_y() + 
//                    " z: " + f.get_point2().get_z() + " p3 x: " + f.get_point3().get_x() + " y: " + f.get_point3().get_y() + 
//                    " z: " + f.get_point3().get_z());
//        }
        Assert.assertTrue(valid.edge_facets_size() == 6);
        facet_iter = valid.edge_facets_iterator();
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(10,0,0), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,10,10), new Point_3D(5,5,5), new Point_3D(10,10,10)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(3,3,3), new Point_3D(0,3,3)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(3,3,3), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(5,5,5), new Point_3D(0,10,10)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(10,0,0), new Point_3D(10,10,10), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(!valid.too_many_share_side_empty());
        Assert.assertTrue(valid.too_many_share_side_size() == 1);
        Iterator<List<Facet_3D>> list_iter = valid.too_many_share_side_iterator();
        Assert.assertTrue(list_iter.hasNext());
        List<Facet_3D> v = list_iter.next();
        Assert.assertTrue(v.size() == 3);
        facet_iter = v.iterator();
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,0,0), new Point_3D(5,5,5), new Point_3D(0,3,3)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(3,3,3), new Point_3D(5,5,5)), precision));
        Assert.assertTrue(facet_iter.hasNext());
        Assert.assertTrue(Facet_3D.is_equal(facet_iter.next(), new Facet_3D(new Point_3D(0,3,3), new Point_3D(5,5,5), new Point_3D(0,10,10)), precision));
    }

    @Test
    public void test_valid_mesh_4() {
        Mesh_3D mesh = new Mesh_3D();

        // valid mesh
        Shapes.ellipsoid(mesh, 10,15,17,4);

        Valid_Mesh_3D valid = new Valid_Mesh_3D(mesh);
        Assert.assertTrue(valid.validate());
        Assert.assertTrue(valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 0);
        Assert.assertTrue(valid.facets_inside_facet_empty());
        Assert.assertTrue(valid.facets_inside_facet_size() == 0);
        Assert.assertTrue(valid.edge_facets_empty());
        Assert.assertTrue(valid.edge_facets_size() == 0);
        Assert.assertTrue(valid.too_many_share_side_empty());
        Assert.assertTrue(valid.too_many_share_side_size() == 0);
    }

    @Test
    public void test_valid_mesh_5() {
        Mesh_3D mesh = new Mesh_3D();

        // valid mesh
        Shapes.e_cylinder(mesh, 10,15,15,10,17,4);

        Valid_Mesh_3D valid = new Valid_Mesh_3D(mesh);
        Assert.assertTrue(valid.validate());
//        for (int i = 0; i < valid.unaligned_points_size(); ++i)
//        {
//            Point_3D p(valid.unaligned_point_at(i));
//            cout << "unaligned_points[" << i << "]: x: " << p.get_x() << " y: " << p.get_y() << " z: " << p.get_z() << "\n";
//        }
        Assert.assertTrue(valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 0);
//        Assert.assertTrue(is_equal(valid.unaligned_point_at(0), Point_3D(3,3,3), precision));
//        Assert.assertTrue(is_equal(valid.unaligned_point_at(1), Point_3D(2,2,2), precision));
//        Assert.assertTrue(is_equal(valid.unaligned_point_at(2), Point_3D(4,0.000001,-0.000001), precision));
//        for (int i = 0; i < valid.facets_with_facets_size(); ++i)
//        {
//            Facet_3D f(valid.facets_with_facets_at(i));
//            cout << "facets_with_facets[" << i << "]: p1 x: " << f.get_point1().get_x() <<
//                    " y: " << f.get_point1().get_y() << " z: " << f.get_point1().get_z() << 
//                    " p2 x: " << f.get_point2().get_x() << " y: " << f.get_point2().get_y() << 
//                    " z: " << f.get_point2().get_z() << " p3 x: " << f.get_point3().get_x() <<
//                    " y: " << f.get_point3().get_y() << " z: " << f.get_point3().get_z() << "\n";
//        }
        Assert.assertTrue(valid.facets_inside_facet_empty());
        Assert.assertTrue(valid.facets_inside_facet_size() == 0);
//        Assert.assertTrue(is_equal(valid.facets_with_facets_at(0), Facet_3D(Point_3D(0,0,0), Point_3D(10,0,0), Point_3D(5,5,5)), precision));
//        Assert.assertTrue(is_equal(valid.facets_with_facets_at(1), Facet_3D(Point_3D(0,0,0), Point_3D(5,5,5), Point_3D(0,3,3)), precision));
//        Assert.assertTrue(is_equal(valid.facets_with_facets_at(2), Facet_3D(Point_3D(0,0,0), Point_3D(3,3,3), Point_3D(0,3,3)), precision));
//        for (int i = 0; i < valid.invalid_facets_size(); ++i)
//        {
//            Facet_3D f(valid.invalid_facets_at(i));
//            cout << "invalid_facets[" << i << "]: p1 x: " << f.get_point1().get_x() <<
//                    " y: " << f.get_point1().get_y() << " z: " << f.get_point1().get_z() << 
//                    " p2 x: " << f.get_point2().get_x() << " y: " << f.get_point2().get_y() << 
//                    " z: " << f.get_point2().get_z() << " p3 x: " << f.get_point3().get_x() <<
//                    " y: " << f.get_point3().get_y() << " z: " << f.get_point3().get_z() << "\n";
//        }
        Assert.assertTrue(valid.edge_facets_empty());
        Assert.assertTrue(valid.edge_facets_size() == 0);
//        Assert.assertTrue(is_equal(valid.edge_facets_at(0), Facet_3D(Point_3D(0,0,0), Point_3D(10,0,0), Point_3D(5,5,5)), precision));
//        Assert.assertTrue(is_equal(valid.edge_facets_at(1), Facet_3D(Point_3D(0,0,0), Point_3D(5,5,5), Point_3D(0,3,3)), precision));
//        Assert.assertTrue(is_equal(valid.edge_facets_at(2), Facet_3D(Point_3D(0,0,0), Point_3D(3,3,3), Point_3D(0,3,3)), precision));
//        Assert.assertTrue(is_equal(valid.edge_facets_at(3), Facet_3D(Point_3D(0,3,3), Point_3D(3,3,3), Point_3D(5,5,5)), precision));
//        Assert.assertTrue(is_equal(valid.edge_facets_at(4), Facet_3D(Point_3D(0,3,3), Point_3D(5,5,5), Point_3D(0,10,10)), precision));
//        Assert.assertTrue(is_equal(valid.edge_facets_at(5), Facet_3D(Point_3D(0,10,10), Point_3D(5,5,5), Point_3D(10,10,10)), precision));
//        Assert.assertTrue(is_equal(valid.edge_facets_at(6), Facet_3D(Point_3D(10,0,0), Point_3D(10,10,10), Point_3D(5,5,5)), precision));
//        for (int i = 0; i < valid.too_many_facets_size(); ++i)
//        {
//            Facet_3D f(valid.too_many_facets_at(i));
//            cout << "too_many_facets[" << i << "]: p1 x: " << f.get_point1().get_x() <<
//                    " y: " << f.get_point1().get_y() << " z: " << f.get_point1().get_z() << 
//                    " p2 x: " << f.get_point2().get_x() << " y: " << f.get_point2().get_y() << 
//                    " z: " << f.get_point2().get_z() << " p3 x: " << f.get_point3().get_x() <<
//                    " y: " << f.get_point3().get_y() << " z: " << f.get_point3().get_z() << "\n";
//        }
        Assert.assertTrue(valid.too_many_share_side_empty());
        Assert.assertTrue(valid.too_many_share_side_size() == 0);
//        Assert.assertTrue(is_equal(valid.too_many_facets_at(0), Facet_3D(Point_3D(0,0,0), Point_3D(5,5,5), Point_3D(0,3,3)), precision));
//        Assert.assertTrue(is_equal(valid.too_many_facets_at(1), Facet_3D(Point_3D(0,3,3), Point_3D(3,3,3), Point_3D(5,5,5)), precision));
//        Assert.assertTrue(is_equal(valid.too_many_facets_at(2), Facet_3D(Point_3D(0,3,3), Point_3D(5,5,5), Point_3D(0,10,10)), precision));
    }
}
