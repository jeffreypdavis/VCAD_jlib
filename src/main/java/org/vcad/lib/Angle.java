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
 * File: Angle.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Angle {
    private double x;
    private double y;
    private double z;
    
    public Angle(double angle_x, double angle_y, double angle_z)
    {
        super();
        x = angle_x;
        y = angle_y;
        z = angle_z;
    }
    
    public void set_x(double angle_x)
    {
        x = angle_x;
    }
    
    public double get_x()
    {
        return x;
    }
    
    public void set_y(double angle_y)
    {
        y = angle_y;
    }
    
    public double get_y()
    {
        return y;
    }
    
    public void set_z(double angle_z)
    {
        z = angle_z;
    }
    
    public double get_z()
    {
        return z;
    }
    
    @Override
    public String toString()
    {
        return new StringBuilder().append("Angle x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(); 
    }
    
    @Override
    public int hashCode()
    {
        return new Double(x).hashCode() ^ new Double(y * 31).hashCode() ^ new Double(z * 57).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Angle)) {
            return false;
        }
        
        final Angle other = (Angle) obj;
        if (x != other.get_x()) {
            return false;
        }
        
        if (y != other.get_y()) {
            return false;
        }
        
        if (z != other.get_z()) {
            return false;
        }
        
        return true;
    }
}
