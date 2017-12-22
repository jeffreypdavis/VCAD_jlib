/*
 * Stl.java
 */
package org.vcad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jeffrey Davis
 */
public class Stl {
    
    /**
     * Read a text or binary STL file. This function attempts to dynamically 
     * determine if the file is a text or binary STL file. If it is a binary STL
     * file, it is read in little-endian byte order.
     * 
     * Facet unit normal vectors will not be ignored.
     * Facet points will be interpreted as being in counter-clockwise order
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl(Mesh_3D mesh, String filename, StringBuilder comment) 
            throws FileNotFoundException, STLException, IOException {
        return Stl.read_stl(mesh, filename, comment, false, false);
    }

    /**
     * Read a text or binary STL file. This function attempts to dynamically 
     * determine if the file is a text or binary STL file. If it is a binary STL
     * file, it is read in little-endian byte order.
     * 
     * Facet unit normal vectors will not be ignored.
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @param clockwise_order a boolean value to say if a facet's point order is
     * clockwise or counter-clockwise.
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl(Mesh_3D mesh, String filename, StringBuilder comment,
            boolean clockwise_order) throws FileNotFoundException, STLException, 
            IOException {
        return Stl.read_stl(mesh, filename, comment, clockwise_order, false);
    }

    /**
     * Read a text or binary STL file. This function attempts to dynamically 
     * determine if the file is a text or binary STL file. If it is a binary STL
     * file, it is read in little-endian byte order.
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @param clockwise_order a boolean value to say if a facet's point order is
     * clockwise or counter-clockwise.
     * @param ignore_unv a boolean value to say if a facet's unit normal vector 
     * should be ignored.
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl(Mesh_3D mesh, String filename, StringBuilder comment,
            boolean clockwise_order, boolean ignore_unv) throws FileNotFoundException, 
            STLException, IOException {
        
        byte[] test = new byte []{0,0,0,0,0,0};
        
        try (BufferedInputStream iStream = new BufferedInputStream(new FileInputStream(filename))) {
            iStream.read(test,0, 5);
        }
        
        String tmp = new String(test, "UTF-8").toLowerCase(Locale.getDefault());
        if ((tmp.charAt(0) == 's') && (tmp.charAt(1) == 'o') && (tmp.charAt(2) == 'l') && 
                (tmp.charAt(3) == 'i') && (tmp.charAt(4) == 'd'))
            // ascii stl file
            return Stl.read_stl_text(mesh, filename, comment, clockwise_order, ignore_unv);
        else
            // binary stl file as little endian which seams to be the usual byte ordering
            return Stl.read_stl_bin(mesh, filename, comment, clockwise_order, ignore_unv, true);
    }
    
//    /*
//     * Test if architecture is little endian.  returns true if little endian, 
//     * false if big endian
//     */
//    private static boolean is_little_endian() throws IOException {
//        // this would be little endian 1
//        ByteArrayOutputStream ss = new ByteArrayOutputStream();
//        ss.write(0x01);
//        ss.write(0x00);
//        ss.write(0x00);
//        ss.write(0x00);
//        try (DataInputStream dStream = new DataInputStream(new ByteArrayInputStream(ss.toByteArray()))) {
//            int temp = dStream.readInt();
//            return temp == 1;
//        }
//    }
    
    /**
     * Read a text STL file. 
     * 
     * Facet points are assumed to be in the typical counter-clockwise order
     * Facet unit normal vectors are not ignored.
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl_text(Mesh_3D mesh, String filename, StringBuilder comment) 
            throws FileNotFoundException, STLException, IOException {
        return Stl.read_stl_text(mesh, filename, comment, false, false);
    }
    
    /**
     * Read a text STL file. 
     * 
     * Facet unit normal vectors are not ignored.
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @param clockwise_order a boolean value to say if a facet's point order is
     * clockwise or counter-clockwise.
     * 
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl_text(Mesh_3D mesh, String filename, StringBuilder comment,
            boolean clockwise_order) throws FileNotFoundException, STLException, IOException {
        return Stl.read_stl_text(mesh, filename, comment, clockwise_order, false);
    }
    
    /**
     * Read a text STL file. 
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @param clockwise_order a boolean value to say if a facet's point order is
     * clockwise or counter-clockwise.
     * @param ignore_unv a boolean value to say if a facet's unit normal vector 
     * should be ignored.
     * 
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl_text(Mesh_3D mesh, String filename, StringBuilder comment,
            boolean clockwise_order, boolean ignore_unv) throws FileNotFoundException, 
            STLException, IOException {
        
        Pattern valPattern = Pattern.compile("\\s*(-?\\d*[.]{0,1}\\d*[eE]{0,1}(?:\\+{1}|-{1})?\\d*)\\s+(-?\\d*[.]{0,1}\\d*[eE]{0,1}(?:\\+{1}|-{1})?\\d*)\\s+(-?\\d*[.]{0,1}\\d*[eE]{0,1}(?:\\+{1}|-{1})?\\d*)\\s*");
        
        Mesh_3D temp = new Mesh_3D(mesh.get_precision()); // temporary mesh to store facets in case of error
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            String line = reader.readLine();
            if (line == null)
                throw new STLException(filename + " is an empty file");
            
            line = line.trim();
            while (line.isEmpty()) {
                line = reader.readLine();
                if (line == null)
                    throw new STLException(filename + " is an empty file");

                line = line.trim();
            } 
            
            if (line.length() < 5)
                throw new STLException("Invalid STL file: " + filename);

            // test if line starts with solid
            if (!line.substring(0,5).equalsIgnoreCase("solid"))
                throw new STLException("Invalid ascii STL file: " + filename);

            // set comment
            comment.append(line.substring(5,line.length()).trim());

            // read facets
            line = reader.readLine();
            boolean foundEndSolid = false;
            while (line != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    line = reader.readLine();
                    continue;
                }
                
                // line should start with facet normal
                if (line.length() < 12 || !line.substring(0,12).equalsIgnoreCase("facet normal"))
                {
                    // if line is end solid - it is the end of the file
                    if ((line.length() >= 8) && line.substring(0,8).equalsIgnoreCase("endsolid")) {
                        foundEndSolid = true;
                        break; // end of stl file
                    }
                    
                    // handle error
                    throw new STLException("Expected a facet normal or endsolid, found '" + line + "' on facet " + temp.size());
                }

                // the rest of the line should have three integers or doubles
                Matcher matcher = valPattern.matcher(line.substring(12));
                if (!matcher.matches()) 
                    throw new STLException("Expected three numbers for the unit normal, found '" + line.substring(12) + "' on facet: " + temp.size());
                
                // now go through each group and create the numbers
                if (matcher.group(1) == null || matcher.group(1) == null || matcher.group(1) == null)
                    throw new STLException("Unable to locate unit normal vector on facet " + temp.size());
                double unv_x = Double.parseDouble(matcher.group(1));
                double unv_y = Double.parseDouble(matcher.group(2));
                double unv_z = Double.parseDouble(matcher.group(3));
                
                // next line should have 'outer loop'
                do {
                    line = reader.readLine();
                    if (line == null)
                        throw new STLException("Expected facet normal, but reached the end of the file on facet " + temp.size());
                    line = line.trim();
                } while (line.isEmpty());
                
                if (!line.equalsIgnoreCase("outer loop"))
                    throw new STLException("Expected 'outer loop', found '" + line + "' on facet: " + temp.size());

                // next line should start with 'vertex'
                do {
                    line = reader.readLine();
                    if (line == null)
                        throw new STLException("Expected vertex, but reached the end of the file on facet " + temp.size());
                    line = line.trim();
                } while (line.isEmpty());
                
                if (!line.substring(0,6).equalsIgnoreCase("vertex"))
                    throw new STLException("Expected 'vertex', found '" + line.substring(0,6) + "' on facet: " + temp.size());
                
                matcher = valPattern.matcher(line.substring(6));
                if (!matcher.matches()) 
                    throw new STLException("Expected three numbers for the first vertex, found '" + line.substring(6) + "' on facet: " + temp.size());
                
                // now go through each group and create the numbers
                if (matcher.group(1) == null || matcher.group(1) == null || matcher.group(1) == null)
                    throw new STLException("Unable to read the first vertex on facet " + temp.size());
                double x = Double.parseDouble(matcher.group(1));
                double y = Double.parseDouble(matcher.group(2));
                double z = Double.parseDouble(matcher.group(3));
                Point_3D p1 = new Point_3D(x,y,z);

                // next line should start with 'vertex'
                do {
                    line = reader.readLine();
                    if (line == null)
                        throw new STLException("Expected vertex, but reached the end of the file on facet " + temp.size());
                    line = line.trim();
                } while (line.isEmpty());
                
                if (!line.substring(0,6).equalsIgnoreCase("vertex"))
                    throw new STLException("Expected 'vertex', found '" + line.substring(0,6) + "' on facet: " + temp.size());
                
                matcher = valPattern.matcher(line.substring(6));
                if (!matcher.matches()) 
                    throw new STLException("Expected three numbers for the second vertex, found '" + line.substring(6) + "' on facet: " + temp.size());
                
                // now go through each group and create the numbers
                if (matcher.group(1) == null || matcher.group(1) == null || matcher.group(1) == null)
                    throw new STLException("Unable to read the second vertex on facet " + temp.size());
                x = Double.parseDouble(matcher.group(1));
                y = Double.parseDouble(matcher.group(2));
                z = Double.parseDouble(matcher.group(3));
                Point_3D p2 = new Point_3D(x,y,z);

                // next line should start with 'vertex'
                do {
                    line = reader.readLine();
                    if (line == null)
                        throw new STLException("Expected vertex, but reached the end of the file on facet " + temp.size());
                    line = line.trim();
                } while (line.isEmpty());
                
                if (!line.substring(0,6).equalsIgnoreCase("vertex"))
                    throw new STLException("Expected 'vertex', found '" + line.substring(0,6) + "' on facet: " + temp.size());
                
                matcher = valPattern.matcher(line.substring(6));
                if (!matcher.matches()) 
                    throw new STLException("Expected three numbers for the third vertex, found '" + line.substring(6) + "' on facet: " + temp.size());
                
                // now go through each group and create the numbers
                if (matcher.group(1) == null || matcher.group(1) == null || matcher.group(1) == null)
                    throw new STLException("Unable to read the third vertex on facet " + temp.size());
                x = Double.parseDouble(matcher.group(1));
                y = Double.parseDouble(matcher.group(2));
                z = Double.parseDouble(matcher.group(3));
                Point_3D p3 = new Point_3D(x,y,z);

                // next line should start with 'endloop'
                do {
                    line = reader.readLine();
                    if (line == null)
                        throw new STLException("Expected endloop, but reached the end of the file on facet " + temp.size());
                    line = line.trim();
                } while (line.isEmpty());
                
                if (!line.equalsIgnoreCase("endloop"))
                    throw new STLException("Expected 'endloop', found '" + line + "' on facet: " + temp.size());

                // next line should start with 'endfacet'
                do {
                    line = reader.readLine();
                    if (line == null)
                        throw new STLException("Expected endfacet, but reached the end of the file on facet " + temp.size());
                    line = line.trim();
                } while (line.isEmpty());
                
                if (!line.equalsIgnoreCase("endfacet"))
                    throw new STLException("Expected 'endfacet', found '" + line + "' on facet: " + temp.size());
                
                // form facet
                if (ignore_unv)
                    temp.add(new Facet_3D(p1, p2, p3, clockwise_order));
                else 
                    temp.add(new Facet_3D(p1, p2, p3, new Vector_3D(unv_x,unv_y,unv_z),clockwise_order));
                
                // get the next line
                do {
                    line = reader.readLine();
                    if (line == null)
                        throw new STLException("Expected another line, but reached the end of the file on facet " + temp.size());
                    line = line.trim();
                } while (line.isEmpty());
            }
            
            // check for endsolid
            if (!foundEndSolid)
                throw new STLException("Did not find closing 'endsolid'");
        }
        
        mesh.clear();
        for (Iterator<Facet_3D> it = temp.iterator(); it.hasNext();)
            mesh.add(it.next());
        
        return temp.size();
    }
    
    private static short read_short(InputStream iStream, boolean littleEndian) 
            throws STLException, IOException {
        
        byte [] data = new byte [2];
        if (iStream.read(data) != 2)
            throw new STLException("Unable to read short value because the end of file has been reached");
        ByteBuffer buf = ByteBuffer.allocateDirect(2);
        buf.put(data);
        if (littleEndian)
            buf.order(ByteOrder.LITTLE_ENDIAN);
        else
            buf.order(ByteOrder.BIG_ENDIAN);
        buf.rewind();
        return buf.getShort();
    }

    private static void write_short(OutputStream oStream, short val, boolean littleEndian) 
            throws IOException {
        
        ByteBuffer buf = ByteBuffer.allocateDirect(2);
        if (littleEndian)
            buf.order(ByteOrder.LITTLE_ENDIAN);
        else
            buf.order(ByteOrder.BIG_ENDIAN);
        buf.putShort(val);
        
        buf.rewind();
        byte [] data = new byte [2];
        buf.get(data);
        oStream.write(data);
    }
    
    private static long read_uint(InputStream iStream, boolean littleEndian) 
            throws STLException, IOException {
        
        byte [] data = new byte [] {0,0,0,0,0,0,0,0};
        // little endian should be read into the beginning, big endian should be read at the end
        if (iStream.read(data, littleEndian ? 0 : 4, 4) != 4)
            throw new STLException("Unable to read int value because the end of file has been reached");
//        String hexChars = "0123456789ABCDEF";
//        System.out.print("Read uint: ");
//        for (int i = 0; i < data.length; ++i) {
//            System.out.print(hexChars.charAt((data[i] & 0xF0) >>> 4));
//            System.out.print(hexChars.charAt(data[i] & 0x0F));
//            System.out.print(' ');
//        }
//        System.out.println();
        ByteBuffer buf = ByteBuffer.allocateDirect(8);
        buf.put(data);
        buf.rewind();
        if (littleEndian)
            buf.order(ByteOrder.LITTLE_ENDIAN);
        else
            buf.order(ByteOrder.BIG_ENDIAN);
        return buf.getLong();
    }

    private static void write_uint(OutputStream oStream, long val, boolean littleEndian) 
            throws IOException {
        
        ByteBuffer buf = ByteBuffer.allocateDirect(8);
        if (littleEndian)
            buf.order(ByteOrder.LITTLE_ENDIAN);
        else
            buf.order(ByteOrder.BIG_ENDIAN);
        buf.putLong(val);
        buf.rewind();
        byte [] data = new byte [8];
        buf.get(data);
        // little endian would be at the beginning, big endian at the end
        oStream.write(data, littleEndian ? 0 : 4, 4);
    }
    
    private static float read_float(InputStream iStream, boolean littleEndian) 
            throws STLException, IOException {
        
        byte [] data = new byte [4];
        if (iStream.read(data) != 4)
            throw new STLException("Unable to read float value because the end of file has been reached");
        ByteBuffer buf = ByteBuffer.allocateDirect(4);
        buf.put(data);
        if (littleEndian)
            buf.order(ByteOrder.LITTLE_ENDIAN);
        else
            buf.order(ByteOrder.BIG_ENDIAN);
        buf.rewind();
        return Float.intBitsToFloat(buf.getInt());
    }
    
    private static void write_float(OutputStream oStream, float val, boolean littleEndian) 
            throws IOException {
        
        ByteBuffer buf = ByteBuffer.allocateDirect(4);
        if (littleEndian)
            buf.order(ByteOrder.LITTLE_ENDIAN);
        else
            buf.order(ByteOrder.BIG_ENDIAN);
        buf.putFloat(val);
        buf.rewind();
        byte [] data = new byte [4];
        buf.get(data);
        oStream.write(data);
    }
    
    /**
     * Read a binary STL file in little endian format. 
     * 
     * Facet points are assumed to be in the typical counter-clockwise order.
     * Facet unit normal vectors are not ignored. 
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * 
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl_bin(Mesh_3D mesh, String filename, StringBuilder comment) 
            throws FileNotFoundException, STLException, IOException{
        return Stl.read_stl_bin(mesh, filename, comment, false, false, true);
    }
    
    /**
     * Read a binary STL file in little endian format. 
     * 
     * Facet unit normal vectors are not ignored. 
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @param clockwise_order a boolean value to say if the points are in 
     * clockwise order or counter clockwise order
     * 
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl_bin(Mesh_3D mesh, String filename, StringBuilder comment,
            boolean clockwise_order) throws FileNotFoundException, STLException, IOException {
        return Stl.read_stl_bin(mesh, filename, comment, clockwise_order, false, true);
    }
    
    /**
     * Read a binary STL file in little endian format. 
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @param clockwise_order a boolean value to say if the points are in 
     * clockwise order or counter clockwise order
     * @param ignore_unv a boolean value to say if the facet unit normal values
     * should be ignored
     * 
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl_bin(Mesh_3D mesh, String filename, StringBuilder comment,
            boolean clockwise_order, boolean ignore_unv) throws FileNotFoundException, 
            STLException, IOException {
        
        return Stl.read_stl_bin(mesh, filename, comment, clockwise_order, ignore_unv, true);
    }

    /**
     * Read a binary STL file. 
     * 
     * @param mesh a mesh object to read the STL file into
     * @param filename the location of the STL file
     * @param comment a StringBuilder object to read the comment into
     * @param clockwise_order a boolean value to say if the points are in 
     * clockwise order or counter clockwise order
     * @param ignore_unv a boolean value to say if the facet unit normal values
     * should be ignored
     * @param littleEndian a boolean value to say if the binary STL file is in 
     * little endian format. little endian appears to be the predominant format.
     * 
     * @return the number of facets found in the STL file
     * @throws FileNotFoundException
     * @throws STLException
     * @throws IOException 
     */
    public static int read_stl_bin(Mesh_3D mesh, String filename, StringBuilder comment,
            boolean clockwise_order, boolean ignore_unv, boolean littleEndian) throws FileNotFoundException, 
            STLException, IOException {
        
        Mesh_3D temp = new Mesh_3D(mesh.get_precision()); // temporary mesh to hold facets
        long num_facets = -1;
        try (BufferedInputStream iStream = new BufferedInputStream(new FileInputStream(filename))) {
            // read 80 bytes for the comment
            byte[] buf = new byte[81];
            if (iStream.read(buf, 0, 80) != 80)
                throw new STLException("Unable to read STL starting comment because the end of the file was reached");
            buf[80] = 0;
            int nullLocation = 0;
            for (; nullLocation < buf.length; ++nullLocation) {
                if (buf[nullLocation] == 0)
                    break;
            }
            comment.append(new String(buf, 0, nullLocation, "UTF-8"));

            // read 4 byte int to find out how many facets there are
            num_facets = read_uint(iStream, littleEndian);
        
            iStream.mark(4);
            
            while (iStream.read(buf, 0, 4) == 4) {
                iStream.reset();
                // read unit normal vector
                float x = read_float(iStream, littleEndian);
                float y = read_float(iStream, littleEndian);
                float z = read_float(iStream, littleEndian);
                Vector_3D unv = new Vector_3D(x, y, z);
                
                // read point 1
                x = read_float(iStream, littleEndian);
                y = read_float(iStream, littleEndian);
                z = read_float(iStream, littleEndian);
                Point_3D p1 = new Point_3D(x,y,z);
                
                // read point 2
                x = read_float(iStream, littleEndian);
                y = read_float(iStream, littleEndian);
                z = read_float(iStream, littleEndian);
                Point_3D p2 = new Point_3D(x,y,z);
                
                // read point 3
                x = read_float(iStream, littleEndian);
                y = read_float(iStream, littleEndian);
                z = read_float(iStream, littleEndian);
                Point_3D p3 = new Point_3D(x,y,z);
    
                // read attribute
                short attribute = read_short(iStream, littleEndian);
    
                // form facet
                if (ignore_unv)
                    temp.add(new Facet_3D(p1, p2, p3, clockwise_order));
                else
                    temp.add(new Facet_3D(p1, p2, p3, unv, clockwise_order));
                iStream.mark(4); // for next iteration
            }
        }
        
        if (temp.size() != num_facets)
            throw new STLException("Expected " + num_facets + " facets and found " + temp.size());
        
        mesh.clear();
        for (Iterator<Facet_3D> it = temp.iterator(); it.hasNext();)
            mesh.add(it.next());
        return temp.size();
    }
    
    /**
     * Write stl as a text stl file. 
     * 
     * Facet points are written in counter-clockwise order.
     * Facet unit normal vectors are included.
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @throws IOException 
     */
    public static void write_stl(Mesh_3D mesh, String filename, String comment) 
            throws IOException {
        Stl.write_stl(mesh, filename, comment, false, false);
    }
    
    /**
     * Write stl as a text stl file. 
     * 
     * Facet unit normal vectors are included.
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @param clockwise_order a boolean value to determine if the points should
     * be written in clockwise or counter-clockwise order
     * @throws IOException 
     */
    public static void write_stl(Mesh_3D mesh, String filename, String comment, 
            boolean clockwise_order) throws IOException {
        Stl.write_stl(mesh, filename, comment, clockwise_order, false);
    }
    
    /**
     * Write stl as a text stl file. 
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @param clockwise_order a boolean value to determine if the points should
     * be written in clockwise or counter-clockwise order
     * @param zero_unv a boolean value to determine if the unit normal vector 
     * should be written as three zeros.
     * @throws IOException 
     */
    public static void write_stl(Mesh_3D mesh, String filename, String comment, 
            boolean clockwise_order, boolean zero_unv) throws IOException {
        
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
            // write comment
            String cmnt = null;
            if (comment != null && !comment.trim().isEmpty()) {
                // erase any newlines
                cmnt = comment;
                int pos = cmnt.indexOf('\r');
                while (pos != -1) {
                    cmnt = cmnt.substring(0,pos) + cmnt.substring(pos + 1);
                    pos = cmnt.indexOf('\r');
                }
                pos = cmnt.indexOf('\n');
                while (pos != -1) {
                    cmnt = cmnt.substring(0,pos) + cmnt.substring(pos + 1);
                    pos = cmnt.indexOf('\n');
                }

                writer.write("solid ");
                writer.write(cmnt);
                writer.newLine();
            } else {
                writer.write("solid");
                writer.newLine();
            }
        
            for (Iterator<Facet_3D> it = mesh.iterator(); it.hasNext();) {
                Facet_3D f = it.next();

                // write unit normal
                writer.write("  facet normal ");
                if (zero_unv)
                    writer.write("0 0 0");
                else {
                    writer.write(Double.toString(f.get_unv().get_x()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_unv().get_y()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_unv().get_z()));
                }
                writer.newLine();
                // write facet details
                writer.write("    outer loop");
                writer.newLine();
                writer.write("      vertex ");
                writer.write(Double.toString(f.get_point1().get_x()));
                writer.write(' ');
                writer.write(Double.toString(f.get_point1().get_y()));
                writer.write(' ');
                writer.write(Double.toString(f.get_point1().get_z()));
                writer.newLine();
                if (clockwise_order) { // swap points 2 and 3
                    writer.write("      vertex ");
                    writer.write(Double.toString(f.get_point3().get_x()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point3().get_y()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point3().get_z()));
                    writer.newLine();
                    writer.write("      vertex ");
                    writer.write(Double.toString(f.get_point2().get_x()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point2().get_y()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point2().get_z()));
                    writer.newLine();
                } else {
                    writer.write("      vertex ");
                    writer.write(Double.toString(f.get_point2().get_x()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point2().get_y()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point2().get_z()));
                    writer.newLine();
                    writer.write("      vertex ");
                    writer.write(Double.toString(f.get_point3().get_x()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point3().get_y()));
                    writer.write(' ');
                    writer.write(Double.toString(f.get_point3().get_z()));
                    writer.newLine();
                }
                writer.write("    endloop");
                writer.newLine();
                writer.write("  endfacet");
                writer.newLine();
            }
            writer.write("endsolid");
            if (cmnt != null) {
                writer.write(' ');
                writer.write(cmnt);
            }
            writer.newLine();
        
            writer.flush();
        }
    }
    
    /**
     * Write stl as a binary stl file. The file is written in little endian format.
     * 
     * Facet points are written counter-clockwise order.
     * Facet unit normal vectors are included.
     * The facet attribute is set to zero
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @throws IOException 
     */
    public static void write_stl_bin(Mesh_3D mesh, String filename, 
            String comment) throws IOException {
        Stl.write_stl_bin(mesh, filename, comment, (short) 0, false, false, true);
    }
    
    /**
     * Write stl as a binary stl file. The file is written in little endian format.
     * 
     * Facet points are written counter-clockwise order.
     * Facet unit normal vectors are included.
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @throws IOException 
     */
    public static void write_stl_bin(Mesh_3D mesh, String filename, 
            String comment, short attribute) throws IOException {
        Stl.write_stl_bin(mesh, filename, comment, attribute, false, false, true);
    }
    
    /**
     * Write stl as a binary stl file. The file is written in little endian format.
     * 
     * Facet unit normal vectors are included.
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @param attribute
     * @param clockwise_order a boolean value to determine if the points should
     * be written in clockwise or counter-clockwise order
     * @throws IOException 
     */
    public static void write_stl_bin(Mesh_3D mesh, String filename, 
            String comment, short attribute, boolean clockwise_order) 
            throws IOException {
        Stl.write_stl_bin(mesh, filename, comment, attribute, clockwise_order, false, true);
    }
    
    /**
     * Write stl as a binary stl file. The file is written in little endian format.
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @param attribute a facet attribute to include
     * @param clockwise_order a boolean value to determine if the points should
     * be written in clockwise or counter-clockwise order
     * @param zero_unv a boolean value to determine if the unit normal vector 
     * should be written as three zeros.
     * @throws IOException 
     */
    public static void write_stl_bin(Mesh_3D mesh, String filename, 
            String comment, short attribute, boolean clockwise_order, 
            boolean zero_unv) throws IOException {
        Stl.write_stl_bin(mesh, filename, comment, attribute, clockwise_order, zero_unv, true);
    }
        
    /**
     * Write stl as a binary stl file. 
     * 
     * @param mesh the mesh to write to file
     * @param filename the STL file location
     * @param comment the optional comment to include
     * @param attribute a facet attribute
     * @param clockwise_order a boolean value to determine if the points should
     * be written in clockwise or counter-clockwise order
     * @param zero_unv a boolean value to determine if the unit normal vector 
     * should be written as three zeros.
     * @param littleEndian a boolean value to determine if the binary STL file
     * should be written in little endian format.
     * @throws IOException 
     */
    public static void write_stl_bin(Mesh_3D mesh, String filename, 
            String comment, short attribute, boolean clockwise_order, 
            boolean zero_unv, boolean littleEndian) throws IOException {
        
        try (BufferedOutputStream oStream = new BufferedOutputStream(new FileOutputStream(filename))) {
        
            // write comment
            if (comment == null) {
                for (int i = 0; i < 80; ++i)
                    oStream.write(0);
            } else if (comment.length() > 80)
                oStream.write(comment.substring(0, 80).getBytes("UTF-8"));
            else {
                int length = comment.length();
                oStream.write(comment.getBytes("UTF-8"));
                length = 80 - length;
                for (int i = 0; i < length; ++i)
                    oStream.write(0);
            }
        
            // write number of facets
            write_uint(oStream, mesh.size(), littleEndian);
        
            for (Iterator<Facet_3D> it = mesh.iterator(); it.hasNext();) {
                Facet_3D f = it.next();
                // write unit normal vector
                if (zero_unv) {
                    write_float(oStream, 0, littleEndian);
                    write_float(oStream, 0, littleEndian);
                    write_float(oStream, 0, littleEndian);
                } else {
                    write_float(oStream, (float)f.get_unv().get_x(), littleEndian);
                    write_float(oStream, (float)f.get_unv().get_y(), littleEndian);
                    write_float(oStream, (float)f.get_unv().get_z(), littleEndian);
                }
                // write point 1
                write_float(oStream, (float)f.get_point1().get_x(), littleEndian);
                write_float(oStream, (float)f.get_point1().get_y(), littleEndian);
                write_float(oStream, (float)f.get_point1().get_z(), littleEndian);
                
                if (clockwise_order) { // swap points two and three
                    // write point 3
                    write_float(oStream, (float)f.get_point3().get_x(), littleEndian);
                    write_float(oStream, (float)f.get_point3().get_y(), littleEndian);
                    write_float(oStream, (float)f.get_point3().get_z(), littleEndian);
                    // write point 2
                    write_float(oStream, (float)f.get_point2().get_x(), littleEndian);
                    write_float(oStream, (float)f.get_point2().get_y(), littleEndian);
                    write_float(oStream, (float)f.get_point2().get_z(), littleEndian);
                } else {
                    // write point 2
                    write_float(oStream, (float)f.get_point2().get_x(), littleEndian);
                    write_float(oStream, (float)f.get_point2().get_y(), littleEndian);
                    write_float(oStream, (float)f.get_point2().get_z(), littleEndian);
                    // write point 3
                    write_float(oStream, (float)f.get_point3().get_x(), littleEndian);
                    write_float(oStream, (float)f.get_point3().get_y(), littleEndian);
                    write_float(oStream, (float)f.get_point3().get_z(), littleEndian);
                }
                // write attribute
                write_short(oStream, attribute, littleEndian);
            }
        
            oStream.flush();
        }
    }
}
