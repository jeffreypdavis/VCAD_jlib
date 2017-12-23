/*
 * Facet.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Facet {
    private final int p1_index;
    private int p2_index;
    private int p3_index;
    
    public Facet(int point1_index, int point2_index, int point3_index) {
        super();
        p1_index = point1_index;
        p2_index = point2_index;
        p3_index = point3_index;
    }
    
    public int get_p1_index() { 
        return p1_index; 
    }
    
    public int get_p2_index() { 
        return p2_index; 
    }
    
    public int get_p3_index() { 
        return p3_index; 
    }
    
    public void invert_unv() {
        // swap p2 and p3
        int temp = p2_index;
        p2_index = p3_index;
        p3_index = temp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.p1_index;
        hash = 43 * hash + this.p2_index;
        hash = 43 * hash + this.p3_index;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Facet) {
            
            Facet facet = (Facet) obj;
            
            return (facet.get_p1_index() == p1_index && facet.get_p2_index() == p2_index && facet.get_p3_index() == p3_index) || 
                    (facet.get_p2_index() == p1_index && facet.get_p3_index() == p2_index && facet.get_p1_index() == p3_index) || 
                    (facet.get_p3_index() == p1_index && facet.get_p1_index() == p2_index && facet.get_p2_index() == p3_index);
        } else
            return false;
    }
}
