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
 * File: Object_2D.java
 */
package org.vcad.lib;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeffrey Davis
 */
public class Object_2D {

    private List<Point_2D> points;
    private List<Path> paths;
    private List<Mesh_2D> meshes;
    
    public Object_2D() {
        super();
        
        points = new ArrayList<>();
        paths = new ArrayList<>();
        meshes = new ArrayList<>();
    }
    
    public void addPoint(Point_2D point) {
        // TODO make sure it isn't a duplicate point
        // TODO make sure it isn't part of a path
        // TODO make sure it isn't part of a mesh
    }
    
    public void addPath(Path path) {
        // TODO if it is already a point, remove the point
        // TODO make sure it isn't part of another path
        // TODO make sure it isn't part of a mesh
    }
}
