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
 * File: Bool.java
 */
package org.vcad.lib;

/**
 *
 * @author Jeffrey Davis
 */
public class Bool {
    private boolean val;
    
    public Bool()
    {
        super();
        val = false;
    }
    
    public Bool(boolean value)
    {
        super();
        val = value;
    }

    public void set_val(boolean value)
    {
        val = value;
    }
    
    public boolean get_val()
    {
        return val;
    }
}
