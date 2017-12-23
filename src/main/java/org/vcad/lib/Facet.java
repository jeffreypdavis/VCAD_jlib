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
 * File: Facet.java
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
