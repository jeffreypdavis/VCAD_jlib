/*
 * Vector_3D.java
 */
package org.vcad;

/**
 *
 * @author Jeffrey Davis
 */
public class Vector_3D {
    
    private double x;
    private double y;
    private double z;
    
    public Vector_3D(double x_val, double y_val, double z_val)
    {
        super();
        x = x_val;
        y = y_val;
        z = z_val;
    }
    
    public Vector_3D(Point_3D end_point)
    {
        super();
        x = end_point.get_x();
        y = end_point.get_y();
        z = end_point.get_z();
    }
    
    public Vector_3D(Point_3D start_point, Point_3D end_point)
    {
        x = end_point.get_x() - start_point.get_x();
        y = end_point.get_y() - start_point.get_y();
        z = end_point.get_z() - start_point.get_z();
    }
    
    public double get_x() 
    { 
        return x; 
    }
    
    public double get_y()
    { 
        return y; 
    }
    
    public double get_z()
    { 
        return z; 
    }
    
    public double length()
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Vector_3D normalize()
    {
        double length = this.length();
        if (length == 0)
            throw new ArithmeticException("Cannot normalize a zero length vector");
        x /= length;
        y /= length;
        z /= length;
        return this;
    }
    
    public Vector_3D plus(Vector_3D vector)
    {
        x += vector.get_x();
        y += vector.get_y();
        z += vector.get_z();
        return this;
    }
    
    public Vector_3D minus(Vector_3D vector)
    {
        x -= vector.get_x();
        y -= vector.get_y();
        z -= vector.get_z();
        return this;
    }
    
    public Vector_3D minus()
    {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }
    
    public Vector_3D scale(double scalar)
    {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        return this;
    }
    
    @Override
    public String toString()
    {
        return new StringBuilder().append("Vector_3D x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString();
    }
    
    @Override
    public int hashCode()
    {
        return new Double(x).hashCode() ^ new Double(y).hashCode() ^ new Double(z).hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Vector_3D))
            return false;
        
        Vector_3D v = (Vector_3D) obj;
        if (v.get_x() != x)
            return false;
        if (v.get_y() != y)
            return false;
        
        return v.get_z() == z;
    }
    
    public static Vector_3D cross_product(Vector_3D vector1, Vector_3D vector2)
    {
        return new Vector_3D(
                vector1.get_y() * vector2.get_z() - vector1.get_z() * vector2.get_y(),
                vector1.get_z() * vector2.get_x() - vector1.get_x() * vector2.get_z(),
                vector1.get_x() * vector2.get_y() - vector1.get_y() * vector2.get_x());
    }
    
    public static double dot_product(Vector_3D vector1, Vector_3D vector2)
    {
        return vector1.get_x() * vector2.get_x() + vector1.get_y() * vector2.get_y() + vector1.get_z() * vector2.get_z();
    }
    
    public static double angle_between(Vector_3D vector1, Vector_3D vector2)
    {
        return Math.acos(dot_product(vector1, vector2) / (vector1.length() * vector2.length()));
    }

    public static Vector_3D plus(Vector_3D vector1, Vector_3D vector2)
    {
        return new Vector_3D(vector1.get_x() + vector2.get_x(), vector1.get_y() + vector2.get_y(), vector1.get_z() + vector2.get_z());
    }
    
    public static Vector_3D minus(Vector_3D vector1, Vector_3D vector2)
    {
        return new Vector_3D(vector1.get_x() - vector2.get_x(), vector1.get_y() - vector2.get_y(), vector1.get_z() - vector2.get_z());
    }
    
    public static Vector_3D scale(Vector_3D vector, double scalar)
    {
        return new Vector_3D(vector.get_x() * scalar, vector.get_y() * scalar, vector.get_z() * scalar);
    }
    
    // orthogonal projection of a onto b
    public static Vector_3D orthogonal_projection(Vector_3D a, Vector_3D b)
    {
        double length = b.length();
        if (length == 0)
            throw new ArithmeticException("Cannot orthogonal project a zero length vector");
        return Vector_3D.scale(b, (dot_product(a, b) / Math.pow(length, 2)));
    }
    
    public static boolean is_equal(Vector_3D vector1, Vector_3D vector2, double precision)
    {
//#ifdef DEBUG_VECTOR_3D_IS_EQUAL
//        cout << "Vector_3D is_equal begin\n";
//        cout << "Vector_3D is_equal vector1 x: " << vector1.get_x() << " y: " << vector1.get_y() << " z: " << vector1.get_z() << "\n";
//        cout << "vector_3D is_equal vector2 x: " << vector2.get_x() << " y: " << vector2.get_y() << " z: " << vector2.get_z() << "\n";
//#endif
        double largest = Math.abs(vector1.get_x());
        double error_bound = Math.abs(vector2.get_x());
        if (error_bound > largest)
            largest = error_bound;
        
        error_bound = largest > 1.0 ? (largest * precision) : precision;
        
        if (Math.abs(vector1.get_x() - vector2.get_x()) <= error_bound)
        {
            largest = Math.abs(vector1.get_y());
            error_bound = Math.abs(vector2.get_y());
            if (error_bound > largest)
                largest = error_bound;

            error_bound = largest > 1.0 ? (largest * precision) : precision;
            
            if (Math.abs(vector1.get_y() - vector2.get_y()) <= error_bound)
            {
                largest = Math.abs(vector1.get_z());
                error_bound = Math.abs(vector2.get_z());
                if (error_bound > largest)
                    largest = error_bound;

                error_bound = largest > 1.0 ? (largest * precision) : precision;

//#ifdef DEBUG_VECTOR_3D_IS_EQUAL
//                if (fabs(vector1.get_z() - vector2.get_z()) > error_bound)
//                {
//                    // determine what multiple of DBL_EPSILON is needed to succeed
//                    Vector_3D::Measurement val(fabs(vector1.get_z() - vector2.get_z()));
//                    Vector_3D::Measurement divisor(DBL_EPSILON);
//                    
//                    if (largest > 1.0)
//                        divisor *= largest;
//                    cout << "Vector_3D is_equal vector z (" << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//                }
//#endif                
                
                return Math.abs(vector1.get_z() - vector2.get_z()) <= error_bound;
            }
//#ifdef DEBUG_VECTOR_3D_IS_EQUAL
//            else
//            {
//                // determine what multiple of DBL_EPSILON is needed to succeed
//                Vector_3D::Measurement val(fabs(vector1.get_y() - vector2.get_y()));
//                Vector_3D::Measurement divisor(DBL_EPSILON);
//                if (largest > 1.0)
//                    divisor *= largest;
//                cout << "Vector_3D is_equal vector y (" << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//            }
//#endif
        }
//#ifdef DEBUG_VECTOR_3D_IS_EQUAL
//        else
//        {
//            // determine what multiple of DBL_EPSILON is needed to succeed
//            Vector_3D::Measurement val(fabs(vector1.get_x() - vector2.get_x()));
//            Vector_3D::Measurement divisor(DBL_EPSILON);
//            if (largest > 1.0)
//                divisor *= largest;
//            cout << "Vector_3D is_equal vector x (" << val << ") FALSE needs a precision of at least: " << (val / divisor) << " * DBL_EPSILON\n";
//        }
//#endif
        return false;
    }
    
    public static boolean is_cp_zero(Vector_3D vector1, Vector_3D vector2, double precision)
    {
        double one = vector1.get_y() * vector2.get_z();
        double two = vector1.get_z() * vector2.get_y();
        
        double largest = Math.abs(one);
        double error_bound = Math.abs(two);
        if (error_bound > largest)
            largest = error_bound;
        
        error_bound = largest > 1.0 ? (largest * precision) : precision;
        
        if (Math.abs(one - two) <= error_bound) // zero
        {
            one = vector1.get_x() * vector2.get_z();
            two = vector1.get_z() * vector2.get_x();

            largest = Math.abs(one);
            error_bound = Math.abs(two);
            if (error_bound > largest)
                largest = error_bound;

            error_bound = largest > 1.0 ? (largest * precision) : precision;

            if (Math.abs(two - one) <= error_bound) // zero
            {
                one = vector1.get_x() * vector2.get_y();
                two = vector1.get_y() * vector2.get_x();

                largest = Math.abs(one);
                error_bound = Math.abs(two);
                if (error_bound > largest)
                    largest = error_bound;

                error_bound = largest > 1.0 ? (largest * precision) : precision;

//#ifdef DEBUG_IS_CP_3D_ZERO
//                if (fabs(one - two) > error_bound)
//                {
//                    Vector_3D::Measurement val(fabs(one - two));
//                    Vector_3D::Measurement divisor(DBL_EPSILON);
//                    if (largest > 1.0)
//                        divisor *= largest;
//                    cout << "is_cp_zero result z(" << val << ") compared to (" << error_bound << ") one (" << one << ") two (" << two << ") needs to have a precision of " << (val / divisor) << " * DBL_EPSILON\n";
//                }
//#endif
                return Math.abs(one - two) <= error_bound;
            }
//#ifdef DEBUG_IS_CP_3D_ZERO
//            else
//            {
//                Vector_3D::Measurement val(fabs(two - one));
//                Vector_3D::Measurement divisor(DBL_EPSILON);
//                if (largest > 1.0)
//                    divisor *= largest;
//                cout << "is_cp_zero result y(" << val << ") compared to (" << error_bound << ") one (" << one << ") two (" << two << ") needs to have a precision of " << (val / divisor) << " * DBL_EPSILON\n";
//            }
//#endif
        }
//#ifdef DEBUG_IS_CP_3D_ZERO
//        else
//        {
//            Vector_3D::Measurement val(fabs(one - two));
//            Vector_3D::Measurement divisor(DBL_EPSILON);
//            if (largest > 1.0)
//                divisor *= largest;
//            cout << "is_cp_zero result x(" << val << ") compared to (" << error_bound << ") one (" << one << ") two (" << two << ") needs to have a precision of " << (val / divisor) << " * DBL_EPSILON\n";
//        }
//        cout << "is_cp_zero returning false\n";
//#endif        
        return false;
    }
    
    public static boolean is_same_line(Point_3D v1_start, Point_3D v1_end, 
            Point_3D v2_start, Point_3D v2_end, Bool same_direction,
            double precision)
    {
        if (Point_3D.is_equal(v1_start, v1_end, precision)) // zero length vector1
        {
            if (Point_3D.is_equal(v2_start, v2_end, precision)) // zero length vector2
            {
                if (Point_3D.is_equal(v1_start, v2_start, precision))
                {
                    same_direction.set_val(true);
                    return true;
                } // else return false
                same_direction.set_val(false);
                return false;
            } // else
            
            if (Point_3D.is_equal(v1_start, v2_start, precision))
            {
                same_direction.set_val(true);
                return true;
            } // else return if v1_start is on v2 vector
            
            if (Vector_3D.is_cp_zero(new Vector_3D(v2_start, v1_start), new Vector_3D(v2_start, v2_end), precision))
            {
                same_direction.set_val(true);
                return true;
            } // else return false
            same_direction.set_val(false);
            return false;
        }
        
        if (Point_3D.is_equal(v2_start, v2_end, precision)) // zero length vector2
        {
            if (Point_3D.is_equal(v1_start, v2_start, precision))
            {
                same_direction.set_val(true);
                return true;
            } // else return if v2_start is on v1 vector
            
            if (Vector_3D.is_cp_zero(new Vector_3D(v1_start, v2_start), new Vector_3D(v1_start, v1_end), precision))
            {
                same_direction.set_val(true);
                return true;
            } // else return false
            same_direction.set_val(false);
            return false;
        }
        
        Vector_3D v1 = new Vector_3D(v1_start, v1_end);
        Vector_3D v2 = new Vector_3D(v2_start, v2_end);
        if (Vector_3D.is_cp_zero(v1, v2, precision))
        {
            // same general line check if they start at the same origin
            if (Point_3D.is_equal(v1_start, v2_start, precision))
            {
                // start at same origin
                same_direction.set_val(dot_product(v1, v2) > 0);
                return true;
            } // else
            if (Vector_3D.is_cp_zero(new Vector_3D(v1_start, v2_start), v1, precision))
            {
                same_direction.set_val(dot_product(v1, v2) > 0);
                return true;
            } // else return false
        }
        
        same_direction.set_val(false);
        return false;
    }
    
    public static boolean is_pt_on_vector(Point_3D pt, Point_3D v_start, Point_3D v_end, double precision)
    {
//#ifdef DEBUG_IS_PT_ON_VECTOR
//        cout << "    is_pt_on_vector beginning\n";
//        cout << "    is_pt_on_vector checking if v has zero length\n";
//#endif
        if (Point_3D.is_equal(v_start, v_end, precision)) // v is zero length
            return Point_3D.is_equal(pt, v_start, precision);
            
//#ifdef DEBUG_IS_PT_ON_VECTOR
//        cout << "    is_pt_on_vector checking if pt is equal to v_start\n";
//#endif
        if (Point_3D.is_equal(pt, v_start, precision))
            return true;
        
//#ifdef DEBUG_IS_PT_ON_VECTOR
//        cout << "    is_pt_on_vector checking if pt is equal to v_end\n";
//#endif
        if (Point_3D.is_equal(pt, v_end, precision))
            return true;
        
        Vector_3D v_pt_f_start = new Vector_3D(v_start, pt);
        Vector_3D v_pt_f_end = new Vector_3D(v_end, pt);
        
//#ifdef DEBUG_IS_PT_ON_VECTOR
//        cout << "    is_pt_on_vector Testing from one end\n";
//#endif
        Vector_3D v = new Vector_3D(v_start, v_end);

        double largest = Math.abs(v_start.get_x());
        double error_bound = Math.abs(v_end.get_x());
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (largest * precision) : precision;
        if (Math.abs(v.get_x()) > error_bound) // if x is not zero
        {
            // ptX = oX + tX*vX
            // tX = (ptX - oX) / vX
            double tX = (pt.get_x() - v_start.get_x()) / v.get_x();
//#ifdef DEBUG_IS_PT_ON_VECTOR
//            cout << "    is_pt_on_vector tX: " << tX << " " << (tX > 0 && tX < 1) << " ip x: " << ip.get_x() << " y: " << ip.get_y() << " z: " << ip.get_z() << " pt x: " << pt.get_x() << " y: " << pt.get_y() << " z: " << pt.get_z() << "\n";
//#endif
            if (tX > 0 && tX < 1 && Point_3D.is_equal(Point_3D.translate(v_start, Vector_3D.scale(v,tX)), pt, precision))
                return true;
        }

        largest = Math.abs(v_start.get_y());
        error_bound = Math.abs(v_end.get_y());
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (largest * precision) : precision;
        if (Math.abs(v.get_y()) > error_bound) // if y is not zero
        {
            // ptY = oY + tY*vY
            // tY = (ptY - oY) / vY
            double tY = (pt.get_y() - v_start.get_y()) / v.get_y();
//            Point_3D ip = new Point_3D(v_start + v*tY);
//#ifdef DEBUG_IS_PT_ON_VECTOR
//            cout << "    is_pt_on_vector tY: " << tY << " " << (tY > 0 && tY < 1) << " ip x: " << ip.get_x() << " y: " << ip.get_y() << " z: " << ip.get_z() << " pt x: " << pt.get_x() << " y: " << pt.get_y() << " z: " << pt.get_z() << "\n";
//#endif
            if (tY > 0 && tY < 1 && Point_3D.is_equal(Point_3D.translate(v_start, Vector_3D.scale(v,tY)), pt, precision))
                return true;
        }

        largest = Math.abs(v_start.get_z());
        error_bound = Math.abs(v_end.get_z());
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (largest * precision) : precision;
        if (Math.abs(v.get_z()) > error_bound) // if z is not zero
        {
            // ptZ = oZ + tZ*vZ
            // tZ = (ptZ - oZ) / vZ
            double tZ = (pt.get_z() - v_start.get_z()) / v.get_z();
//            Point_3D ip = new Point_3D(v_start + v*tZ);
//#ifdef DEBUG_IS_PT_ON_VECTOR
//            cout << "    is_pt_on_vector tZ: " << tZ << " " << (tZ > 0 && tZ < 1) << " ip x: " << ip.get_x() << " y: " << ip.get_y() << " z: " << ip.get_z() << " pt x: " << pt.get_x() << " y: " << pt.get_y() << " z: " << pt.get_z() << "\n";
//#endif
            if (tZ > 0 && tZ < 1 && Point_3D.is_equal(Point_3D.translate(v_start, Vector_3D.scale(v,tZ)), pt, precision))
                return true;
        }

//#ifdef DEBUG_IS_PT_ON_VECTOR
//        cout << "    is_pt_on_vector Testing from other end\n";
//#endif
        v = new Vector_3D(v_end, v_start);

        largest = Math.abs(v_start.get_x());
        error_bound = Math.abs(v_end.get_x());
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (largest * precision) : precision;
        if (Math.abs(v.get_x()) > error_bound) // if x is not zero
        {
            // ptX = oX + tX*vX
            // tX = (ptX - oX) / vX
            double tX = (pt.get_x() - v_end.get_x()) / v.get_x();
//            Point_3D ip = new Point_3D(v_end + v*tX);
//#ifdef DEBUG_IS_PT_ON_VECTOR
//            cout << "    is_pt_on_vector tX: " << tX << " " << (tX > 0 && tX < 1) << " ip x: " << ip.get_x() << " y: " << ip.get_y() << " z: " << ip.get_z() << " pt x: " << pt.get_x() << " y: " << pt.get_y() << " z: " << pt.get_z() << "\n";
//#endif
            if (tX > 0 && tX < 1 && Point_3D.is_equal(Point_3D.translate(v_end, Vector_3D.scale(v,tX)), pt, precision))
                return true;
        }

        largest = Math.abs(v_start.get_y());
        error_bound = Math.abs(v_end.get_y());
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (largest * precision) : precision;
        if (Math.abs(v.get_y()) > error_bound) // if y is not zero
        {
            // ptY = oY + tY*vY
            // tY = (ptY - oY) / vY
            double tY = (pt.get_y() - v_end.get_y()) / v.get_y();
//            Point_3D ip = new Point_3D(v_end + v*tY);
//#ifdef DEBUG_IS_PT_ON_VECTOR
//            cout << "    is_pt_on_vector tY: " << tY << " " << (tY > 0 && tY < 1) << " ip x: " << ip.get_x() << " y: " << ip.get_y() << " z: " << ip.get_z() << " pt x: " << pt.get_x() << " y: " << pt.get_y() << " z: " << pt.get_z() << "\n";
//#endif
            if (tY > 0 && tY < 1 && Point_3D.is_equal(Point_3D.translate(v_end, Vector_3D.scale(v,tY)), pt, precision))
                return true;
        }

        largest = Math.abs(v_start.get_z());
        error_bound = Math.abs(v_end.get_z());
        if (error_bound > largest)
            largest = error_bound;

        error_bound = largest > 1.0 ? (largest * precision) : precision;
        if (Math.abs(v.get_z()) > error_bound) // if z is not zero
        {
            // ptZ = oZ + tZ*vZ
            // tZ = (ptZ - oZ) / vZ
            double tZ = (pt.get_z() - v_end.get_z()) / v.get_z();
//            Point_3D ip = new Point_3D(v_end + v*tZ);
//#ifdef DEBUG_IS_PT_ON_VECTOR
//            cout << "    is_pt_on_vector tZ: " << tZ << " " << (tZ > 0 && tZ < 1) << " ip x: " << ip.get_x() << " y: " << ip.get_y() << " z: " << ip.get_z() << " pt x: " << pt.get_x() << " y: " << pt.get_y() << " z: " << pt.get_z() << "\n";
//#endif
            if (tZ > 0 && tZ < 1 && Point_3D.is_equal(Point_3D.translate(v_end, Vector_3D.scale(v,tZ)), pt, precision))
                return true;
        }
        
//#ifdef DEBUG_IS_PT_ON_VECTOR
//        cout << "    is_pt_on_vector returning false\n";
//#endif
        return false;
    }
    
    private static boolean intersect_vectors_sl(Point_3D v1_start, Point_3D v1_end, 
            Point_3D v2_start, Point_3D v2_end, Bool same_direction,
            Intersection_Data_3D result, double precision)
    {
//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_sl same_line\n";
//#endif
        if (same_direction.get_val())
        {
            if (Point_3D.is_equal(v1_start, v2_start, precision)) // vector from v1_start to v2_start has zero length
            {
                if (is_pt_on_vector(v2_end, v1_start, v1_end, precision))
                {
                    result.set_p2(v2_end);
                    result.set_num(2);
                }
                else if (is_pt_on_vector(v1_end, v2_start, v2_end, precision))
                {
                    result.set_p2(v1_end);
                    result.set_num(2);
                }
                else
                    result.set_num(1);

                result.set_p1(v1_start);
                return true;
            }

            // if v1 comes before v2
            if (dot_product(new Vector_3D(v1_start, v1_end), new Vector_3D(v1_start, v2_start)) > 0)
            {
                if (is_pt_on_vector(v2_start, v1_start, v1_end, precision))
                {
                    if (is_pt_on_vector(v2_end, v1_start, v1_end, precision))
                    {
                        result.set_p2(v2_end);
                        result.set_num(2);
                    }
                    else if (is_pt_on_vector(v1_end, v2_start, v2_end, precision) && !Point_3D.is_equal(v1_end, v2_start, precision))
                    {
                        result.set_p2(v1_end);
                        result.set_num(2);
                    }
                    else
                        result.set_num(1);

                    result.set_p1(v2_start);
                    return true;
                }

                return false;
            }
            else // v2 comes before v1
            {
                if (is_pt_on_vector(v1_start, v2_start, v2_end, precision))
                {
                    if (is_pt_on_vector(v1_end, v2_start, v2_end, precision))
                    {
                        result.set_p2(v1_end);
                        result.set_num(2);
                    }
                    else if (is_pt_on_vector(v2_end, v1_start, v1_end, precision) && !Point_3D.is_equal(v2_end, v1_start, precision))
                    {
                        result.set_p2(v2_end);
                        result.set_num(2);
                    }
                    else
                        result.set_num(1);

                    result.set_p1(v1_start);
                    return true;
                }

                return false;
            }
        }
        else
        {
            if (Point_3D.is_equal(v1_start, v2_end, precision)) // vector from v1_start to v2_end has zero length
            {
                if (is_pt_on_vector(v2_start, v1_start, v1_end, precision))
                {
                    result.set_p2(v2_start);
                    result.set_num(2);
                }
                else if (is_pt_on_vector(v1_end, v2_start, v2_end, precision))
                {
                    result.set_p2(v1_end);
                    result.set_num(2);
                }
                else
                    result.set_num(1);

                result.set_p1(v1_start);
                return true;
            }

            // if v1 comes before v2
            if (dot_product(new Vector_3D(v1_start, v1_end), new Vector_3D(v1_start, v2_end)) > 0)
            {
                if (is_pt_on_vector(v2_end, v1_start, v1_end, precision))
                {
                    if (is_pt_on_vector(v2_start, v1_start, v1_end, precision))
                    {
                        result.set_p2(v2_start);
                        result.set_num(2);
                    }
                    else if (is_pt_on_vector(v1_end, v2_start, v2_end, precision) && !Point_3D.is_equal(v1_end, v2_end, precision))
                    {
                       result.set_p2(v1_end);
                        result.set_num(2);
                    }
                    else
                        result.set_num(1);

                    result.set_p1(v2_end);
                    return true;
                }

                return false;
            }
            else // v2 comes before v1
            {
                if (is_pt_on_vector(v1_start, v2_start, v2_end, precision))
                {
                    if (is_pt_on_vector(v1_end, v2_start, v2_end, precision))
                    {
                        result.set_p2(v1_end);
                        result.set_num(2);
                    }
                    else if (is_pt_on_vector(v2_start, v1_start, v1_end, precision) && !Point_3D.is_equal(v2_start, v1_start, precision))
                    {
                        result.set_p2(v2_start);
                        result.set_num(2);
                    }
                    else
                        result.set_num(1);

                    result.set_p1(v1_start);
                    return true;
                }

                return false;
            }
        }
    }

    private static boolean intersect_vectors_dl_cpz(Point_3D v1_start, Point_3D v1_end, 
            Point_3D v2_start, Point_3D v2_end, Intersection_Data_3D result, double precision)
    {
//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl_cpz begin\n";
//#endif
        // vectors cross, but may not actually intersect
        // find the point on v2 that is also on v1
        // point on v2 is defined as
        // v1 = o1 + t*v1
        // so
        // pX = o1X + t*v1X
        // pY = o1Y + t*v1Y
        // pZ = o1Z + t*v1Z
        // where t is from 0 to 1
        //
        // cross product will be of V3 and v2 which should equal zero
        // v3 is defined as
        // v3 = p - o2
        // three equations: x, y, and z
        // each must be zero
        // cpX = (v2Y * v3Z) - (v2Z * v3Y) = 0
        // cpY = (v2X * v3Z) - (v2Z * v3X) = 0
        // cpZ = (v2X * v3Y) - (v2Y * v3X) = 0
        //
        // cpx
        // (v2Y * v3Z) - (v2Z * v3Y) = 0
        // (V2Y * (o1Z + t*v1Z - o2Z)) - (v2Z * (o1Y + t*v1Y - o2Y)) = 0
        // v2Y*o1Z + t*v1Z*v2Y - o2Z*v2Y - v2Z*o1Y - t*v1Y*v2Z + o2Y*v2Z = 0
        // t(v1Z*v2Y - v1Y*v2Z) = o2Z*v2Y + v2Z*o1Y - v2Y*o1Z - o2Y*v2Z
        // t = (o2Z*v2Y + v2Z*o1Y - v2Y*o1Z - o2Y*v2Z) / (v1Z*v2Y - v1Y*v2Z)
        // t = (v2Y*(o2Z - o1Z) + v2Z*(o1Y - o2Y)) / (v1Z*v2Y - v1Y*v2Z)
        //
        // cpy
        // (v2X * v3Z) - (v2Z * v3X) = 0
        // (v2X * (o1Z + t*v1Z - o2Z)) - (v2Z * (o1X + t*v1X - o2X)) = 0
        // v2X*o1Z + t*v1Z*v2X - o2Z*v2X - v2Z*o1X - t*v1X*v2Z + o2X*v2Z = 0
        // t(v1Z*v2X - v1X*v2Z) = o2Z*v2X + v2Z*o1X - v2X*o1Z - o2X*v2Z
        // t = (o2Z*v2X + v2Z*o1X - v2X*o1Z - o2X*v2Z) / (v1Z*v2X - v1X*v2Z)
        // t = (v2X*(o2Z - o1Z) + v2Z*(o1X - o2X)) / (v1Z*v2X - v1X*v2Z)
        //
        // cpz
        // (v2X * v3Y) - (v2Y * v3X) = 0
        // (v2X * (o1Y + t*v1Y - o2Y)) - (v2Y * (o1X + t*v1X - o2X)) = 0
        // v2X*o1Y + t*v1Y*v2X - o2Y*v2X - v2Y*o1X - t*v1X*v2Y + o2X*v2Y = 0
        // t(v1Y*v2X - v1X*v2Y) = o2Y*v2X + v2Y*o1X - v2X*o1Y - o2X*v2Y
        // t = (o2Y*v2X + v2Y*o1X - v2X*o1Y - o2X*v2Y) / (v1Y*v2X - v1X*v2Y)
        // t = (v2X*(o2Y - o1Y) + v2Y*(o1X - o2X)) / (v1Y*v2X - v1X*v2Y)
        //
        // try first equation
        // check for zero division
        // t = (v2Y*(o2Z - o1Z) + v2Z*(o1Y - o2Y)) / (v1Z*v2Y - v1Y*v2Z)
        Vector_3D v1 = new Vector_3D(v1_start, v1_end);
        Vector_3D v2 = new Vector_3D(v2_start, v2_end);
//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl_cpz v1 x: " << v1.get_x() << " y: " << v1.get_y() << " z: " << v1.get_z() << "\n";
//        cout << "intersect_vectors_dl_cpz v2 x: " << v2.get_x() << " y: " << v2.get_y() << " z: " << v2.get_z() << "\n";
//#endif
        double bottom = v1.get_z() * v2.get_y();
        double largest = Math.abs(bottom);
        double next = v1.get_y() * v2.get_z();
        bottom -= next;
        double error_bound = Math.abs(next);
        if (error_bound > largest)
            largest = error_bound;
        error_bound = largest > 1.0 ? (largest * precision) : precision;
//        cout << "intersect_vectors_dl_cpz fabs(bottom): " << fabs(bottom) << " error_bound: " << error_bound << "\n";
        if (Math.abs(bottom) > error_bound)
        {
            double t = v2.get_y() * (v2_start.get_z() - v1_start.get_z()) + v2.get_z() * (v1_start.get_y() - v2_start.get_y());
            t /= bottom;
//#ifdef DEBUG_INTERSECT_VECTORS
//            cout << "intersect_vectors_dl_cpz first equation bottom: " << bottom << " t: " << t << "\n";
//#endif
            if (t >= 0 && t <= 1)
            {
                // point is on v1, now check if it is in v2
                Point_3D i_point = Point_3D.translate(v1_start, Vector_3D.scale(v1, t));
//#ifdef DEBUG_INTERSECT_VECTORS
//                cout << "intersect_vectors_dl_cpz i_point_cpz_x x: " << i_point.get_x() << " y: " << i_point.get_y() << " z: " << i_point.get_z() << "\n";
//                cout << "intersect_vectors_dl_cpz is_pt_on_vector1: " << is_pt_on_vector(i_point, v1_start, v1_end, precision) << "\n";
//                cout << "intersect_vectors_dl_cpz is_pt_on_vector2: " << is_pt_on_vector(i_point, v2_start, v2_end, precision) << "\n";
//#endif
                
                if (Point_3D.is_equal(i_point, v2_start, precision))
                {
                    if (is_pt_on_vector(v2_start, v1_start, v1_end, precision))
                    {
                        result.set_p1(v2_start);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v2_end, precision))
                {
                    if (is_pt_on_vector(v2_end, v1_start, v1_end, precision))
                    {
                        result.set_p1(v2_end);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v1_start, precision))
                {
                    if (is_pt_on_vector(v1_start, v2_start, v2_end, precision))
                    {
                        result.set_p1(v1_start);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v1_end, precision))
                {
                    if (is_pt_on_vector(v1_end, v2_start, v2_end, precision))
                    {
                        result.set_p1(v1_end);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (is_pt_on_vector(i_point, v1_start, v1_end, precision) && is_pt_on_vector(i_point, v2_start, v2_end, precision))
                {
                    result.set_p1(i_point);
                    result.set_num(1);
                    return true;
                }
            }
        }
        
        // try second equation
        // t = (v2X*(o2Z - o1Z) + v2Z*(o1X - o2X)) / (v1Z*v2X - v1X*v2Z)
        bottom = v1.get_z() * v2.get_x();
        largest = Math.abs(bottom);
        next = v1.get_x() * v2.get_z();
        bottom -= next;
        error_bound = Math.abs(next);
        if (error_bound > largest)
            largest = error_bound;
        error_bound = largest > 1.0 ? (largest * precision) : precision;
//        cout << "intersect_vectors_dl_cpz fabs(bottom): " << fabs(bottom) << " error_bound: " << error_bound << "\n";
        // check for zero division
        if (Math.abs(bottom) > error_bound)
        {
            double t = v2.get_x() * (v2_start.get_z() - v1_start.get_z()) + v2.get_z() * (v1_start.get_x() - v2_start.get_x());
            t /= bottom;
//#ifdef DEBUG_INTERSECT_VECTORS
//            cout << "intersect_vectors_dl_cpz second equation bottom: " << bottom << " t: " << t << "\n";
//#endif
            if (t >= 0 && t <= 1)
            {
                // point is on v1, now check if it is in v2
                Point_3D i_point = Point_3D.translate(v1_start, Vector_3D.scale(v1, t));
//#ifdef DEBUG_INTERSECT_VECTORS
//                cout << "intersect_vectors_dl_cpz i_point_cpz_y x: " << i_point.get_x() << " y: " << i_point.get_y() << " z: " << i_point.get_z() << "\n";
//                cout << "intersect_vectors_dl_cpz is_pt_on_vector1: " << is_pt_on_vector(i_point, v1_start, v1_end, precision) << "\n";
//                cout << "intersect_vectors_dl_cpz is_pt_on_vector2: " << is_pt_on_vector(i_point, v2_start, v2_end, precision) << "\n";
//#endif
                if (Point_3D.is_equal(i_point, v2_start, precision))
                {
                    if (is_pt_on_vector(v2_start, v1_start, v1_end, precision))
                    {
                        result.set_p1(v2_start);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v2_end, precision))
                {
                    if (is_pt_on_vector(v2_end, v1_start, v1_end, precision))
                    {
                        result.set_p1(v2_end);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v1_start, precision))
                {
                    if (is_pt_on_vector(v1_start, v2_start, v2_end, precision))
                    {
                        result.set_p1(v1_start);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v1_end, precision))
                {
                    if (is_pt_on_vector(v1_end, v2_start, v2_end, precision))
                    {
                        result.set_p1(v1_end);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (is_pt_on_vector(i_point, v1_start, v1_end, precision) && is_pt_on_vector(i_point, v2_start, v2_end, precision))
                {
                    result.set_p1(i_point);
                    result.set_num(1);
                    return true;
                }
            }
        }
        
        // try third equation
        // t = (v2X*(o2Y - o1Y) + v2Y*(o1X - o2X)) / (v1Y*v2X - v1X*v2Y)
        // check for zero division
        bottom = v1.get_y() * v2.get_x();
        largest = Math.abs(bottom);
        next = v1.get_x() * v2.get_y();
        bottom -= next;
        error_bound = Math.abs(next);
        if (error_bound > largest)
            largest = error_bound;
        error_bound = largest > 1.0 ? (largest * precision) : precision;
//        cout << "intersect_vectors_dl_cpz fabs(bottom): " << fabs(bottom) << " error_bound: " << error_bound << "\n";
        if (Math.abs(bottom) > error_bound)
        {
            double t = v2.get_x() * (v2_start.get_y() - v1_start.get_y()) + v2.get_y() * (v1_start.get_x() - v2_start.get_x());
            t /= bottom;
//#ifdef DEBUG_INTERSECT_VECTORS
//            cout << "intersect_vectors_dl_cpz third equation bottom: " << bottom << " t: " << t << "\n";
//#endif
            if (t >= 0 && t <= 1)
            {
                // point is on v1, now check if it is in v2
                Point_3D i_point = Point_3D.translate(v1_start, Vector_3D.scale(v1, t));
//#ifdef DEBUG_INTERSECT_VECTORS
//                cout << "intersect_vectors_dl_cpz i_point_cpz_z x: " << i_point.get_x() << " y: " << i_point.get_y() << " z: " << i_point.get_z() << "\n";
//                cout << "intersect_vectors_dl_cpz is_pt_on_vector1: " << is_pt_on_vector(i_point, v1_start, v1_end, precision) << "\n";
//                cout << "intersect_vectors_dl_cpz is_pt_on_vector2: " << is_pt_on_vector(i_point, v2_start, v2_end, precision) << "\n";
//#endif
                if (Point_3D.is_equal(i_point, v2_start, precision))
                {
                    if (is_pt_on_vector(v2_start, v1_start, v1_end, precision))
                    {
                        result.set_p1(v2_start);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v2_end, precision))
                {
                    if (is_pt_on_vector(v2_end, v1_start, v1_end, precision))
                    {
                        result.set_p1(v2_end);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v1_start, precision))
                {
                    if (is_pt_on_vector(v1_start, v2_start, v2_end, precision))
                    {
                        result.set_p1(v1_start);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (Point_3D.is_equal(i_point, v1_end, precision))
                {
                    if (is_pt_on_vector(v1_end, v2_start, v2_end, precision))
                    {
                        result.set_p1(v1_end);
                        result.set_num(1);
                        return true;
                    }
                }
                else if (is_pt_on_vector(i_point, v1_start, v1_end, precision) && is_pt_on_vector(i_point, v2_start, v2_end, precision))
                {
                    result.set_p1(i_point);
                    result.set_num(1);
                    return true;
                }
            }
        }
        
//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl_cpz returning false\n";
//#endif
        return false;
    }
    
    private static boolean intersect_vectors_dl(Point_3D v1_start, Point_3D v1_end, 
            Point_3D v2_start, Point_3D v2_end, Intersection_Data_3D result, 
            double precision)
    {
//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl begin\n";
//        cout << "intersect_vectors_dl testing if end points of vectors are on the other vector\n";
//#endif
        if (Vector_3D.is_pt_on_vector(v1_start, v2_start, v2_end, precision))
        {
            result.set_p1(v1_start);
            result.set_num(1);
            return true;
        }
        else if (Vector_3D.is_pt_on_vector(v1_end, v2_start, v2_end, precision))
        {
            result.set_p1(v1_end);
            result.set_num(1);
            return true;
        }
        else if (Vector_3D.is_pt_on_vector(v2_start, v1_start, v1_end, precision))
        {
            result.set_p1(v2_start);
            result.set_num(1);
            return true;
        }
        else if (Vector_3D.is_pt_on_vector(v2_end, v1_start, v1_end, precision))
        {
            result.set_p1(v2_end);
            result.set_num(1);
            return true;
        }
        
//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl Testing cross product zero method\n";
//#endif
        // try cross product equal to zero method
        if (intersect_vectors_dl_cpz(v1_start, v1_end, v2_start, v2_end, result, precision))
            return true;

//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl Testing cross product zero method v1 negative\n";
//#endif
        if (intersect_vectors_dl_cpz(v1_end, v1_start, v2_start, v2_end, result, precision))
            return true;

//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl Testing cross product zero method v2 negative\n";
//#endif
        if (intersect_vectors_dl_cpz(v1_start, v1_end, v2_end, v2_start, result, precision))
            return true;

//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vectors_dl Testing cross product zero method v1 & v2 negative\n";
//#endif
        return intersect_vectors_dl_cpz(v1_end, v1_start, v2_end, v2_start, result, precision);
    }
    
    public static boolean intersect_vectors(Point_3D v1_start, Point_3D v1_end,
            Point_3D v2_start, Point_3D v2_end, Intersection_Data_3D result, double precision)
    {
//#ifdef DEBUG_INTERSECT_VECTORS
//        cout << "intersect_vector_vector begin\n";
//        cout << "intersect_vector_vector v1_start x: " << v1_start.get_x() << " y: " << v1_start.get_y() << " z: " << v1_start.get_z() << "\n";
//        cout << "intersect_vector_vector v1_end x: " << v1_end.get_x() << " y: " << v1_end.get_y() << " z: " << v1_end.get_z() << "\n";
//        cout << "intersect_vector_vector v2_start x: " << v2_start.get_x() << " y: " << v2_start.get_y() << " z: " << v2_start.get_z() << "\n";
//        cout << "intersect_vector_vector v2_end x: " << v2_end.get_x() << " y: " << v2_end.get_y() << " z: " << v2_end.get_z() << "\n";
//#endif

        if (Point_3D.is_equal(v1_start, v1_end, precision)) // v1 has zero length
        {
            if (Point_3D.is_equal(v2_start, v2_end, precision)) // v2 has zero length
            {
                if (Point_3D.is_equal(v1_start, v2_start, precision))
                {
                    result.set_p1(v1_start);
                    result.set_num(1);
                    return true;
                }
                
                return false;
            } // else v2 has length
            
            if (is_pt_on_vector(v1_start, v2_start, v2_end, precision))
            {
                result.set_p1(v1_start);
                result.set_num(1);
                return true;
            }
            
            return false;
        }
        
        if (Point_3D.is_equal(v2_start, v2_end, precision)) // v2 has zero length
        {
            if (is_pt_on_vector(v2_start, v1_start, v1_end, precision))
            {
                result.set_p1(v2_start);
                result.set_num(1);
                return true;
            }
            
            return false;
        }
        
        Bool same_direction = new Bool(false);
        if (Vector_3D.is_same_line(v1_start, v1_end, v2_start, v2_end, same_direction, precision))
            return Vector_3D.intersect_vectors_sl(v1_start, v1_end, v2_start, v2_end, same_direction, result, precision);
        else // different lines
            return Vector_3D.intersect_vectors_dl(v1_start, v1_end, v2_start, v2_end, result, precision);
    }
    
}
