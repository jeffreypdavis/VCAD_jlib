/*
 * Object_2D.java
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
