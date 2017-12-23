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
 * File: Intersection_Data_3D.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Intersection_Data_3D {
    private int num; // number of intersection points found (0,1, or 2))
    private Point_3D p1;
    private Point_3D p2;
    
    public Intersection_Data_3D()
    {
        super();
        num = 0;
        p1 = new Point_3D(0,0,0);
        p2 = new Point_3D(0,0,0);
    }
    
    public void set_num(int val)
    {
        num = val;
    }
    
    public int get_num()
    {
        return num;
    }
    
    public void set_p1(Point_3D pt)
    {
        p1 = pt;
    }
    
    public Point_3D get_p1()
    {
        return p1;
    }
    
    public void set_p2(Point_3D pt)
    {
        p2 = pt;
    }
    
    public Point_3D get_p2()
    {
        return p2;
    }
}
