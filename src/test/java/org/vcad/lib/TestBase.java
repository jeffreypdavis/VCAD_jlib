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
 * File: TestBase.java
 */
package org.vcad.lib;

import java.util.Iterator;

/**
 *
 * @author Jeffrey Davis
 */
public class TestBase {
    
    
    protected static final double precision = 0.0001;
    protected static final double DBL_EPSILON = 2.2204460492503131e-16d;

    protected boolean within_round(double val1, double val2, double precision) {
        return (Math.abs(val1 - val2) < precision);
    }
    
    protected boolean mesh_contains(Facet_2D facet, Mesh_2D mesh, double precision) {
        Iterator<Facet_2D> it = mesh.iterator();
        while (it.hasNext()) {
            Facet_2D facet2 = it.next();
            
            // try to locate the first point
            if (within_round(facet2.get_point1().get_x(), facet.get_point1().get_x(), precision) && 
                within_round(facet2.get_point1().get_y(), facet.get_point1().get_y(), precision)) {
                // try for point2
                if (within_round(facet2.get_point2().get_x(), facet.get_point2().get_x(), precision) && 
                    within_round(facet2.get_point2().get_y(), facet.get_point2().get_y(), precision)) {
                    // try point3
                    if (within_round(facet2.get_point3().get_x(), facet.get_point3().get_x(), precision) && 
                        within_round(facet2.get_point3().get_y(), facet.get_point3().get_y(), precision))
                        return true;
                }
            } else if (within_round(facet2.get_point2().get_x(), facet.get_point1().get_x(), precision) && 
                       within_round(facet2.get_point2().get_y(), facet.get_point1().get_y(), precision)) {
                // try for point2
                if (within_round(facet2.get_point3().get_x(), facet.get_point2().get_x(), precision) && 
                    within_round(facet2.get_point3().get_y(), facet.get_point2().get_y(), precision)) {
                    // try point3
                    if (within_round(facet2.get_point1().get_x(), facet.get_point3().get_x(), precision) && 
                        within_round(facet2.get_point1().get_y(), facet.get_point3().get_y(), precision))
                        return true;
                }
            } else if (within_round(facet2.get_point3().get_x(), facet.get_point1().get_x(), precision) && 
                       within_round(facet2.get_point3().get_y(), facet.get_point1().get_y(), precision)) {
                // try for point2
                if (within_round(facet2.get_point1().get_x(), facet.get_point2().get_x(), precision) && 
                    within_round(facet2.get_point1().get_y(), facet.get_point2().get_y(), precision)) {
                    // try point3
                    if (within_round(facet2.get_point2().get_x(), facet.get_point3().get_x(), precision) && 
                        within_round(facet2.get_point2().get_y(), facet.get_point3().get_y(), precision))
                        return true;
                }
            }
        }

        return false;
    }
    
    protected boolean mesh_contains(Facet_3D facet, Mesh_3D mesh, double precision) {

        Iterator<Facet_3D> it = mesh.iterator();
        while (it.hasNext()) {
            Facet_3D currentFacet = it.next();
            // try to locate the first point
            if (within_round(currentFacet.get_point1().get_x(), facet.get_point1().get_x(), precision) && 
                    within_round(currentFacet.get_point1().get_y(), facet.get_point1().get_y(), precision) &&
                    within_round(currentFacet.get_point1().get_z(), facet.get_point1().get_z(), precision)) {
                // try for point2
                if (within_round(currentFacet.get_point2().get_x(), facet.get_point2().get_x(), precision) && 
                        within_round(currentFacet.get_point2().get_y(), facet.get_point2().get_y(), precision) &&
                        within_round(currentFacet.get_point2().get_z(), facet.get_point2().get_z(), precision)) {
                    // try point3
                    if (within_round(currentFacet.get_point3().get_x(), facet.get_point3().get_x(), precision) && 
                            within_round(currentFacet.get_point3().get_y(), facet.get_point3().get_y(), precision) &&
                            within_round(currentFacet.get_point3().get_z(), facet.get_point3().get_z(), precision))
                        return true;
                }
            } else if (within_round(currentFacet.get_point2().get_x(), facet.get_point1().get_x(), precision) && 
                    within_round(currentFacet.get_point2().get_y(), facet.get_point1().get_y(), precision) &&
                    within_round(currentFacet.get_point2().get_z(), facet.get_point1().get_z(), precision)) {
                // try for point2
                if (within_round(currentFacet.get_point3().get_x(), facet.get_point2().get_x(), precision) && 
                        within_round(currentFacet.get_point3().get_y(), facet.get_point2().get_y(), precision) &&
                        within_round(currentFacet.get_point3().get_z(), facet.get_point2().get_z(), precision)) {
                    // try point3
                    if (within_round(currentFacet.get_point1().get_x(), facet.get_point3().get_x(), precision) && 
                            within_round(currentFacet.get_point1().get_y(), facet.get_point3().get_y(), precision) &&
                            within_round(currentFacet.get_point1().get_z(), facet.get_point3().get_z(), precision))
                        return true;
                }
            } else if (within_round(currentFacet.get_point3().get_x(), facet.get_point1().get_x(), precision) && 
                    within_round(currentFacet.get_point3().get_y(), facet.get_point1().get_y(), precision) &&
                    within_round(currentFacet.get_point3().get_z(), facet.get_point1().get_z(), precision)) {
                // try for point2
                if (within_round(currentFacet.get_point1().get_x(), facet.get_point2().get_x(), precision) && 
                        within_round(currentFacet.get_point1().get_y(), facet.get_point2().get_y(), precision) &&
                        within_round(currentFacet.get_point1().get_z(), facet.get_point2().get_z(), precision)) {
                    // try point3
                    if (within_round(currentFacet.get_point2().get_x(), facet.get_point3().get_x(), precision) && 
                            within_round(currentFacet.get_point2().get_y(), facet.get_point3().get_y(), precision) &&
                            within_round(currentFacet.get_point2().get_z(), facet.get_point3().get_z(), precision))
                        return true;
                }
            }
        }

        return false;
    }
    
}
