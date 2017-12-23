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
 * File: Test_Valid_Mesh_2D.java
 */
package org.vcad.lib;

import java.util.Iterator;
import java.util.List;
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
public class Test_Valid_Mesh_2D extends TestBase {
    
    public Test_Valid_Mesh_2D() {
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
        Mesh_2D mesh = new Mesh_2D();

        // create an unaligned point 3,3
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(3,3), new Point_2D(0,3)));
        mesh.add(new Facet_2D(new Point_2D(3,3), new Point_2D(5,5), new Point_2D(0,3)));
        mesh.add(new Facet_2D(new Point_2D(0,3), new Point_2D(5,5), new Point_2D(0,10)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(5,5), new Point_2D(10,10)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,-5), new Point_2D(10,0)));

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        Assert.assertTrue(!valid.validate());
        Assert.assertTrue(!valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 1);
        Iterator<Point_2D> it = valid.pts_on_side_iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Point_2D.is_equal(it.next(), new Point_2D(3,3), precision));
        Assert.assertTrue(valid.too_many_share_side_empty());
        Assert.assertTrue(valid.too_many_share_side_size() == 0);
        Assert.assertTrue(valid.facets_inside_facet_empty());
        Assert.assertTrue(valid.facets_inside_facet_size() == 0);
    }

    @Test
    public void test_valid_mesh_2() {
        Mesh_2D mesh = new Mesh_2D();

        // create a facet within a facet
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5,5), new Point_2D(0,10)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(5,5), new Point_2D(10,10)));

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        Assert.assertTrue(!valid.validate());
        Assert.assertTrue(!valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 1);
        Iterator<Point_2D> it = valid.pts_on_side_iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Point_2D.is_equal(it.next(), new Point_2D(3,3), precision));
        Assert.assertTrue(!valid.too_many_share_side_empty());
//        cout << "\ntoo many share side size: " << valid.too_many_share_side_size() << "\n";
//        for (Valid_Mesh_2D::too_many_share_side_iterator iter = valid.too_many_share_side_begin(); iter != valid.too_many_share_side_end(); ++iter)
//        {
//            cout << "Group of Facets\n";
//            for (vector<Facet_2D>::const_iterator iter2 = iter->begin(); iter2 != iter->end(); ++iter2)
//            {
//                cout << "    Facet_2D p1 x: " << iter2->get_point1()->get_x() << " y: " << iter2->get_point1()->get_y() <<
//                        " p2 x: " << iter2->get_point2()->get_x() << " y: " << iter2->get_point2()->get_y() << " p3 x: " << 
//                        iter2->get_point3()->get_x() << " y: " << iter2->get_point3()->get_y() << "\n";
//            }
//        }
        Assert.assertTrue(valid.too_many_share_side_size() == 1);
        Iterator<List<Facet_2D>> it2 = valid.too_many_share_side_iterator();
        Assert.assertTrue(it2.hasNext());
        List<Facet_2D> list = it2.next();
        Assert.assertTrue(list.size() == 3);
        Assert.assertTrue(Facet_2D.is_equal(list.get(0), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        Assert.assertTrue(Facet_2D.is_equal(list.get(1), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)), precision));
        Assert.assertTrue(Facet_2D.is_equal(list.get(2), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)), precision));
        Assert.assertTrue(!valid.facets_inside_facet_empty());
//        System.out.println("\nfacets_inside_facets size: " + valid.facets_inside_facet_size());
//        for (Iterator<Valid_Mesh_2D.FacetsInsideFacet> iter = valid.facets_inside_facet_iterator(); iter.hasNext();) {
//            Valid_Mesh_2D.FacetsInsideFacet fif = iter.next();
//            System.out.println("Large facet p1 x: " + 
//                    fif.getFacet().get_point1().get_x() + " y: " + 
//                    fif.getFacet().get_point1().get_y() + " p2 x: " + 
//                    fif.getFacet().get_point2().get_x() + " y: " + 
//                    fif.getFacet().get_point2().get_y() + " p3 x: " + 
//                    fif.getFacet().get_point3().get_x() + " y: " + 
//                    fif.getFacet().get_point3().get_y());
//            for (Iterator<Facet_2D> iter2 = fif.getInsideFacets(); iter2.hasNext();) {
//                Facet_2D f = iter2.next();
//                System.out.println("    Facet_2D p1 x: " + f.get_point1().get_x() + 
//                        " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
//                        " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
//                        " y: " + f.get_point3().get_y());
//            }
//        }
        Assert.assertTrue(valid.facets_inside_facet_size() == 2);
        Iterator<Valid_Mesh_2D.FacetsInsideFacet> it3 = valid.facets_inside_facet_iterator();
        Assert.assertTrue(it3.hasNext());
        Valid_Mesh_2D.FacetsInsideFacet fif = it3.next();
        Assert.assertTrue(Facet_2D.is_equal(fif.getFacet(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        Assert.assertTrue(fif.insideFacetsSize() == 2);
        Iterator<Facet_2D> it4 = fif.getInsideFacets();
        Assert.assertTrue(it4.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)), precision));
        Assert.assertTrue(it4.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)), precision));
        Assert.assertTrue(it3.hasNext());
        fif = it3.next();
        Assert.assertTrue(Facet_2D.is_equal(fif.getFacet(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(3,3)), precision));
        Assert.assertTrue(fif.insideFacetsSize() == 1);
        it4 = fif.getInsideFacets();
        Assert.assertTrue(it4.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(2,1)), precision));
    }

    @Test
    public void test_valid_mesh_3() {
        Mesh_2D mesh = new Mesh_2D();

        // create invalid facets
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,0), new Point_2D(5.00001,4.99999), new Point_2D(5,5)));
        mesh.add(new Facet_2D(new Point_2D(0,10), new Point_2D(0,0), new Point_2D(5,5)));

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        Assert.assertTrue(!valid.validate());
        Assert.assertTrue(!valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 1);
        Iterator<Point_2D> it = valid.pts_on_side_iterator();
        Assert.assertTrue(Point_2D.is_equal(it.next(), new Point_2D(5,5), precision));
//        cout << "\ntoo many share side size: " << valid.too_many_share_side_size() << "\n";
//        for (Valid_Mesh_2D::too_many_share_side_iterator iter = valid.too_many_share_side_begin(); iter != valid.too_many_share_side_end(); ++iter)
//        {
//            cout << "Group of Facets\n";
//            for (vector<Facet_2D>::const_iterator iter2 = iter->begin(); iter2 != iter->end(); ++iter2)
//            {
//                cout << "    Facet_2D p1 x: " << iter2->get_point1()->get_x() << " y: " << iter2->get_point1()->get_y() <<
//                        " p2 x: " << iter2->get_point2()->get_x() << " y: " << iter2->get_point2()->get_y() << " p3 x: " << 
//                        iter2->get_point3()->get_x() << " y: " << iter2->get_point3()->get_y() << "\n";
//            }
//        }
        Assert.assertTrue(!valid.too_many_share_side_empty());
        Assert.assertTrue(valid.too_many_share_side_size() == 1);
        Iterator<List<Facet_2D>> it2 = valid.too_many_share_side_iterator();
        Assert.assertTrue(it2.hasNext());
        List<Facet_2D> list = it2.next();
        Assert.assertTrue(list.size() == 3);
        Iterator<Facet_2D> iter = list.iterator();
        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(iter.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(iter.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5.00001,4.99999), new Point_2D(5,5)), precision));
        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(iter.next(), new Facet_2D(new Point_2D(0,10), new Point_2D(0,0), new Point_2D(5,5)), precision));
//        System.out.println("\nfacets_inside_facets size: " + valid.facets_inside_facet_size());
//        for (Iterator<Valid_Mesh_2D.FacetsInsideFacet> iter3 = valid.facets_inside_facet_iterator(); iter3.hasNext();) {
//            Valid_Mesh_2D.FacetsInsideFacet fif = iter3.next();
//            System.out.println("Large facet p1 x: " + 
//                    fif.getFacet().get_point1().get_x() + " y: " + 
//                    fif.getFacet().get_point1().get_y() + " p2 x: " + 
//                    fif.getFacet().get_point2().get_x() + " y: " + 
//                    fif.getFacet().get_point2().get_y() + " p3 x: " + 
//                    fif.getFacet().get_point3().get_x() + " y: " + 
//                    fif.getFacet().get_point3().get_y());
//            for (Iterator<Facet_2D> iter2 = fif.getInsideFacets(); iter2.hasNext();) {
//                Facet_2D f = iter2.next();
//                System.out.println("    Facet_2D p1 x: " + f.get_point1().get_x() + 
//                        " y: " + f.get_point1().get_y() + " p2 x: " + f.get_point2().get_x() + 
//                        " y: " + f.get_point2().get_y() + " p3 x: " + f.get_point3().get_x() + 
//                        " y: " + f.get_point3().get_y());
//            }
//        }
        Assert.assertTrue(!valid.facets_inside_facet_empty());
        Assert.assertTrue(valid.facets_inside_facet_size() == 1);
        Iterator<Valid_Mesh_2D.FacetsInsideFacet> it3 = valid.facets_inside_facet_iterator();
        Assert.assertTrue(it3.hasNext());
        Valid_Mesh_2D.FacetsInsideFacet fif = it3.next();
        Assert.assertTrue(Facet_2D.is_equal(fif.getFacet(), new Facet_2D(new Point_2D(0,0), new Point_2D(10,0), new Point_2D(5,5)), precision));
        Assert.assertTrue(fif.insideFacetsSize() == 1);
        Iterator<Facet_2D> it4 = fif.getInsideFacets();
        Assert.assertTrue(it4.hasNext());
        Assert.assertTrue(Facet_2D.is_equal(it4.next(), new Facet_2D(new Point_2D(0,0), new Point_2D(5.00001,4.99999), new Point_2D(5,5)), precision));
    }

    @Test
    public void test_valid_mesh_4() {
        Mesh_2D mesh = new Mesh_2D();

        // create valid facets
        Shapes.ellipse(mesh, 10, 20, 4);

        Valid_Mesh_2D valid = new Valid_Mesh_2D(mesh);
        Assert.assertTrue(valid.validate());
//        for (int i = 0; i < valid.unaligned_points_size(); ++i)
//        {
//            Point_2D p(valid.unaligned_point_at(i));
//            cout << "unaligned_points[" << i << "]: x: " << p.get_x() << " y: " << p.get_y() << "\n";
//        }
        Assert.assertTrue(valid.pts_on_side_empty());
        Assert.assertTrue(valid.pts_on_side_size() == 0);
//        Assert.assertTrue(within_round(valid.unaligned_point_at(0), Point_2D(5,5), precision));
//        for (int i = 0; i < valid.facets_with_facets_size(); ++i)
//        {
//            Facet_2D f(valid.facets_with_facets_at(i));
//            cout << "facets_with_facets[" << i << "]: p1 x: " << f.get_point1().get_x() <<
//                    " y: " << f.get_point1().get_y() << " p2 x: " << f.get_point2().get_x() <<
//                    " y: " << f.get_point2().get_y() << " p3 x: " << f.get_point3().get_x() <<
//                    " y: " << f.get_point3().get_y() << "\n";
//        }
        Assert.assertTrue(valid.facets_inside_facet_empty());
        Assert.assertTrue(valid.facets_inside_facet_size() == 0);
//        for (int i = 0; i < valid.too_many_facets_size(); ++i)
//        {
//            Facet_2D f(valid.too_many_facets_at(i));
//            cout << "too_many_facets[" << i << "]: p1 x: " << f.get_point1().get_x() <<
//                    " y: " << f.get_point1().get_y() << " p2 x: " << f.get_point2().get_x() <<
//                    " y: " << f.get_point2().get_y() << " p3 x: " << f.get_point3().get_x() <<
//                    " y: " << f.get_point3().get_y() << "\n";
//        }
        Assert.assertTrue(valid.too_many_share_side_empty());
        Assert.assertTrue(valid.too_many_share_side_size() == 0);
//        Assert.assertTrue(is_equal(valid.too_many_facets_at(0), Facet_2D(Point_2D(0,0), Point_2D(10,0), Point_2D(5,5)), precision));
//        Assert.assertTrue(is_equal(valid.too_many_facets_at(1), Facet_2D(Point_2D(0,0), Point_2D(5.00001,4.99999), Point_2D(5,5)), precision));
    }
}
