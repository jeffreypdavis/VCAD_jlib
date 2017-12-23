/*
 * Test_STL.java
 */
package org.vcad.lib;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author Jeffrey Davis
 */
public class Test_STL extends TestBase {
    
    public Test_STL() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        // delete test STL if it exists
        File file = new File("test.stl");
        if (file.exists())
            file.delete();
    }

    private void write_short(OutputStream oStream, short val, boolean littleEndian) 
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
    
    private void write_uint(OutputStream oStream, long val, boolean littleEndian) 
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
        oStream.write(data, littleEndian ? 0 : 4, 4);
    }
    
    private void write_float(OutputStream oStream, float val, boolean littleEndian) 
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
    
    private void create_file(String filename, List<String> content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        }
    }

    private void create_bin_file(String filename, byte [] content) throws IOException {
        try (BufferedOutputStream oStream = new BufferedOutputStream(new FileOutputStream(filename))) {
            oStream.write(content);
            oStream.flush();
        }
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    @Test
    public void test_read_stl_1() {
        // try to read a non existing stl file
        Mesh_3D shape = new Mesh_3D();
        String filename = "123.stl";
        StringBuilder comment = new StringBuilder();
        boolean caught_ex = false;
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
        } catch (FileNotFoundException ex) {
            caught_ex = true;
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(caught_ex);
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_2() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<String>();
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_3() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("sol");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_4() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soli");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_5() {
        // try to read an empty stl file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("sOLId");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_6() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("sOLId incomplete");
        content.add("fAc8t normal 0 0 0");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_7() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet n0rmal 0 0 0");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should have thrown an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex){
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_8() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet noRMal a 0 0");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_9() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 t12 0");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_10() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 ~12");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_11() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("0ut3R loop");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_12() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR 3L00p");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_13() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("\tvert3x 1 2 3");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_14() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex !2 2 3");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_15() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 -=2 3");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_16() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 &3");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_17() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  verte& 1 1 1");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_18() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex $1 1 1");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_19() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 #1 1");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_20() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 *1");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_21() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 1");
        content.add("\t  vertex3 1 1 1");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_22() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 1");
        content.add("\t  vertex d$1 1 1");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_23() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 1");
        content.add("\t  vertex 1 ^#1 1");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex){
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_24() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 1");
        content.add("\t  vertex 1 1 Hi");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_25() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 1");
        content.add("\t  vertex 1 1 1");
        content.add("endloop@");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_26() {
        // try to read an incomplete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 1");
        content.add("\t  vertex 1 1 1");
        content.add("endloop");
        content.add("endf@cet");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(false); // it should throw an exception
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_27() {
        // try to read a complete file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        List<String> content = new ArrayList<>();
        content.add("soliD incomplete");
        content.add("facet normal 0 12.0 .12");
        content.add("OuTeR LOOp");
        content.add("    vertex 2 2 -3");
        content.add("\t  vertex 1 1 -2");
        content.add("\t  vertex 1 1 1");
        content.add("endloop");
        content.add("endfacet");
        content.add("endsolid");
        try {
            create_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(num_facets == 1);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("incomplete"));
        Assert.assertTrue(shape.size() == 1);
        Assert.assertTrue(mesh_contains(new Facet_3D(new Point_3D(2,2,-3),new Point_3D(1,1,-2),new Point_3D(1,1,1)), shape, precision));
        Iterator<Facet_3D> it = shape.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(Vector_3D.is_equal(it.next().get_unv(), new Vector_3D(-0.707107,0.707107,0), precision));
    }

    @Test
    public void test_read_stl_28() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try (ByteArrayOutputStream content = new ByteArrayOutputStream()) {
            content.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8"));
            for (int i = 0; i < 30; ++i)
                content.write(0);
            // write size
            this.write_uint(content, 2, true); // size
            this.write_float(content, 1, true); // unv_x
            this.write_float(content, 0, true); // unv_y
            this.write_float(content, -1, true); // unv_z
            this.write_float(content, 0, true); // p1 x
            this.write_float(content, 0, true); // p1 y
            this.write_float(content, 0, true); // p1 z
            this.write_float(content, 1, true); // p2 x
            this.write_float(content, 1, true); // p2 y
            this.write_float(content, 1, true); // p2 z
            this.write_float(content, 2, true); // p3 x
            this.write_float(content, 0, true); // p3 y
            this.write_float(content, 5, true); // p3 z
            this.write_short(content, (short) 0, true); // attribute
            this.write_float(content, 2, true); // unv x
            this.write_float(content, 5, true); // unv y
            this.write_float(content, -1, true); // unv z
            this.write_float(content, 0, true); // p1 x
            this.write_float(content, 0, true); // p1 y
            this.write_float(content, 0, true); // p1 z
            this.write_float(content, 1, true); // p2 x
            this.write_float(content, 1, true); // p2 y
            this.write_float(content, 1, true); // p2 z
            this.write_float(content, 2, true); // p3 x
            this.write_float(content, 0, true); // p3 y
            this.write_float(content, -5, true); // p3 z
            this.write_short(content, (short) 0, true); // attribute
            create_bin_file(filename, content.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl(shape, filename, comment);
            Assert.assertTrue(num_facets == 2);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.size() == 2);
        Iterator<Facet_3D> iter = shape.iterator();
        Assert.assertTrue(iter.hasNext());
        Facet_3D f = iter.next();
        Assert.assertTrue(Vector_3D.is_equal(f.get_unv(), new Vector_3D(0.811107, -0.486664, -0.32443), precision));
        Assert.assertTrue(Facet_3D.is_equal(f, new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(2,0,5)), precision));
        Assert.assertTrue(iter.hasNext());
        f = iter.next();
//        System.out.println("unv x: " + f.get_unv().get_x() + " y: " + f.get_unv().get_y() + " z: " + f.get_unv().get_z());
        Assert.assertTrue(Vector_3D.is_equal(f.get_unv(), new Vector_3D(-0.566139, 0.792594, -0.226455), precision));
        Assert.assertTrue(Facet_3D.is_equal(f, new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(2,0,-5)), precision));
    }

    @Test
    public void test_read_stl_bin_1() {
        // try to read a non existing stl file
        Mesh_3D shape = new Mesh_3D();
        String filename = "123.stl";
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            // do nothing - file does not exist
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_2() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            create_bin_file(filename, new byte [0]);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_3() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            byte [] content = "This is a comment".getBytes("UTF-8");
            create_bin_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_4() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            byte [] content = "MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8");
            create_bin_file(filename, content);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_5() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8"));
            write_uint(out, 1, true);
            create_bin_file(filename, out.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_6() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            content.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8"));
            write_uint(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 0, true);
            create_bin_file(filename, content.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_7() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            content.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8"));
            write_uint(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 0, true);
            write_float(content, -1, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 2, true);
            write_float(content, 0, true);
            write_float(content, 5, true);
            create_bin_file(filename, content.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_8() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            content.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8"));
            write_uint(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 0, true);
            write_float(content, -1, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 2, true);
            write_float(content, 0, true);
            write_float(content, 5, true);
            write_short(content, (short) 0, true);
            create_bin_file(filename, content.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(num_facets == 1);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.size() == 1);
        Iterator<Facet_3D> it = shape.iterator();
        Assert.assertTrue(it.hasNext());
        Facet_3D f = it.next();
    //    cout << "unv x: " << (*shape.at(0)).get_unv().get_x() << " y: " << (*shape.at(0)).get_unv().get_y() << " z: " << (*shape.at(0)).get_unv().get_z() << "\n";
        Assert.assertTrue(Vector_3D.is_equal(f.get_unv(), new Vector_3D(0.811107, -0.486664, -0.32443), precision));
        Assert.assertTrue(Facet_3D.is_equal(f, new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(2,0,5)), precision));
    }

    @Test
    public void test_read_stl_bin_9() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            content.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8"));
            for (int i = 0; i < 30; ++i)
                content.write(0);
            write_uint(content, 2, true);
            write_float(content, 1, true);
            write_float(content, 0, true);
            write_float(content, -1, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 2, true);
            write_float(content, 0, true);
            write_float(content, 5, true);
            write_short(content, (short) 0, true);
            write_float(content, 2, true);
            write_float(content, 5, true);
            write_float(content, -1, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 2, true);
            write_float(content, 0, true);
            write_float(content, -5, true);
            write_short(content, (short) 0, true);
            create_bin_file(filename, content.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(num_facets == 2);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equalsIgnoreCase("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.size() == 2);
        Iterator<Facet_3D> iter = shape.iterator();
        Assert.assertTrue(iter.hasNext());
        Facet_3D f = iter.next();
        Assert.assertTrue(Vector_3D.is_equal(f.get_unv(), new Vector_3D(0.811107, -0.486664, -0.32443), precision));
        Assert.assertTrue(Facet_3D.is_equal(f, new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(2,0,5)), precision));
        Assert.assertTrue(iter.hasNext());
        f = iter.next();
    //    cout << "unv x: " << (*shape.at(1)).get_unv().get_x() << " y: " << (*shape.at(1)).get_unv().get_y() << " z: " << (*shape.at(1)).get_unv().get_z() << "\n";
        Assert.assertTrue(Vector_3D.is_equal(f.get_unv(), new Vector_3D(-0.566139, 0.792594, -0.226455), precision));
        Assert.assertTrue(Facet_3D.is_equal(f, new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(2,0,-5)), precision));
    }

    @Test
    public void test_read_stl_bin_10() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            content.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes());
            for (int i = 0; i < 30; ++i)
                content.write(0);
            write_uint(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 0, true);
            write_float(content, -1, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 2, true);
            write_float(content, 0, true);
            write_float(content, 5, true);
            write_short(content, (short) 0, true);
            write_float(content, 2, true);
            write_float(content, 5, true);
            write_float(content, -1, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 0, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 1, true);
            write_float(content, 2, true);
            write_float(content, 0, true);
            write_float(content, -5, true);
            write_short(content, (short) 0, true);
            create_bin_file(filename, content.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment);
            Assert.assertTrue(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            // do nothing
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.isEmpty());
    }

    @Test
    public void test_read_stl_bin_11() {
        // try to read an empty file
        Mesh_3D shape = new Mesh_3D();
        String filename = "test.stl";
        try {
            ByteArrayOutputStream content = new ByteArrayOutputStream();
            content.write("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-".getBytes("UTF-8"));
            for (int i = 0; i < 30; ++i)
                content.write(0);
            content.write(0);
            content.write(0);
            content.write(0);
            content.write(1); // big endian size 1
            write_float(content, 1, false);
            write_float(content, 0, false);
            write_float(content, -1, false);
            write_float(content, 0, false);
            write_float(content, 0, false);
            write_float(content, 0, false);
            write_float(content, 1, false);
            write_float(content, 1, false);
            write_float(content, 1, false);
            write_float(content, 2, false);
            write_float(content, 0, false);
            write_float(content, 5, false);
            content.write(0);
            content.write(0);
            create_bin_file(filename, content.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        StringBuilder comment = new StringBuilder();
        try {
            int num_facets = Stl.read_stl_bin(shape, filename, comment, false, false, false);
            Assert.assertTrue(num_facets == 1);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(comment.toString().equals("MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-MESH-"));
        Assert.assertTrue(shape.size() == 1);
        Iterator<Facet_3D> it = shape.iterator();
        Assert.assertTrue(it.hasNext());
        Facet_3D f = it.next();
        Assert.assertTrue(Vector_3D.is_equal(f.get_unv(), new Vector_3D(0.811107, -0.486664, -0.32443), precision));
        Assert.assertTrue(Facet_3D.is_equal(f, new Facet_3D(new Point_3D(0,0,0), new Point_3D(1,1,1), new Point_3D(2,0,5)), precision));
    //    cout << "unv x: " << (*shape.at(1)).get_unv().get_x() << " y: " << (*shape.at(1)).get_unv().get_y() << " z: " << (*shape.at(1)).get_unv().get_z() << "\n";
    }

    @Test
    public void test_write_stl_1() {
        // create a cube, write it in ascii and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            Stl.write_stl(shape, "test.stl", "cuboid shape 10x10x10");
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            int num_facets = Stl.read_stl(result, "test.stl", comment);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
                Assert.assertTrue(mesh_contains(rF, shape, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_write_stl_2() {
        // create a cube, write it in binary and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            Stl.write_stl_bin(shape, "test.stl", "cuboid shape 10x10x10");
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            int num_facets = Stl.read_stl(result, "test.stl", comment);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
                Assert.assertTrue(mesh_contains(rF, shape, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_write_stl_3() {
        // create a cube, write it in binary and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            Stl.write_stl_bin(shape, "test.stl", "cuboid shape 10x10x10");
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            int num_facets = Stl.read_stl(result, "test.stl", comment);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
                Assert.assertTrue(mesh_contains(rF, shape, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_write_stl_4() {
        // create a cube, write it in ascii and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            Stl.write_stl(shape, "test.stl", "cuboid shape 10x10x10", false, true);
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            int num_facets = Stl.read_stl(result, "test.stl", comment);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
                Assert.assertTrue(mesh_contains(rF, shape, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_write_stl_5() {
        // create a cube, write it in binary and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            Stl.write_stl_bin(shape, "test.stl", "cuboid shape 10x10x10", (short)0, false, true);
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            int num_facets = Stl.read_stl(result, "test.stl", comment);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
                Assert.assertTrue(mesh_contains(rF, result, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_write_stl_6() {
        // create a cube, write it in binary and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            Stl.write_stl_bin(shape, "test.stl", "cuboid shape 10x10x10", (short)0, true, true);
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            int num_facets = Stl.read_stl(result, "test.stl", comment, true);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
                Assert.assertTrue(mesh_contains(rF, result, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_write_stl_7() {
        // create a cube, write it in ascii and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            // write clockwise order
            Stl.write_stl(shape, "test.stl", "cuboid shape 10x10x10", true);
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            // read clockwise order
            int num_facets = Stl.read_stl(result, "test.stl", comment, true);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
                Assert.assertTrue(mesh_contains(rF, shape, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_write_stl_8() {
        // create a cube, write it in ascii and test result
        Mesh_3D shape = new Mesh_3D();
        Shapes.cuboid(shape, 10, 10, 10);
        try {
            Stl.write_stl(shape, "test.stl", "cuboid shape 10x10x10", true, true);
            Mesh_3D result = new Mesh_3D();
            StringBuilder comment = new StringBuilder();
            int num_facets = Stl.read_stl(result, "test.stl", comment, false, true);
            Assert.assertTrue(num_facets == shape.size());
            Assert.assertTrue(comment.toString().equals("cuboid shape 10x10x10"));
            Assert.assertTrue(shape.size() == result.size());
            Iterator<Facet_3D> r_it = result.iterator();
            while (r_it.hasNext()) {
                Facet_3D rF = r_it.next();
        //        cout << "shape p1 x: " << (*it)->get_point1().get_x() << " y: " << (*it)->get_point1().get_y() << 
        //                " z: " << (*it)->get_point1().get_z() << " p2 x: " << (*it)->get_point2().get_x() << " y: " <<
        //                (*it)->get_point2().get_y() << " z: " << (*it)->get_point2().get_z() << " p3 x: " <<
        //                (*it)->get_point3().get_x() << " y: " << (*it)->get_point3().get_y() << " z: " <<
        //                (*it)->get_point3().get_z() << "\n";
        //        cout << "result p1 x: " << (*r_it)->get_point1().get_x() << " y: " << (*r_it)->get_point1().get_y() << 
        //                " z: " << (*r_it)->get_point1().get_z() << " p2 x: " << (*r_it)->get_point2().get_x() << " y: " <<
        //                (*r_it)->get_point2().get_y() << " z: " << (*r_it)->get_point2().get_z() << " p3 x: " <<
        //                (*r_it)->get_point3().get_x() << " y: " << (*r_it)->get_point3().get_y() << " z: " <<
        //                (*r_it)->get_point3().get_z() << "\n";
                Assert.assertTrue(mesh_contains(rF, result, precision));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (STLException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
