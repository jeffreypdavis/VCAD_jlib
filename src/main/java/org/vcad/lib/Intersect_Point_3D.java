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
 * File: Intersect_Point_3D.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Intersect_Point_3D {
    private Point_3D pt;
    
    public Intersect_Point_3D() {
        super();
        pt = null;
    }
    
    public void setPoint(Point_3D point) {
        pt = point;
    }
    
    public Point_3D getPoint() {
        return pt;
    }
}
