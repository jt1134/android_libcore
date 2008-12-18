/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package tests.api.java.io;

import dalvik.annotation.TestInfo;
import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTarget;
import dalvik.annotation.TestTargetClass; 
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Locale;

import tests.support.Support_StringReader;
import tests.support.Support_StringWriter;

@TestTargetClass(PrintWriter.class) 
public class PrintWriterTest extends junit.framework.TestCase {

    static class Bogus {
        public String toString() {
            return "Bogus";
        }
    }

    PrintWriter pw;

    ByteArrayOutputStream bao;

    ByteArrayInputStream bai;

    BufferedReader br;

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.io.OutputStream)
     */
    @TestInfo(
      level = TestLevel.TODO,
      purpose = "Test does not checks specified constructor. In setUp checked PrintWriter(OutputStream, boolean)",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.io.OutputStream.class}
        )
    })
    public void test_ConstructorLjava_io_OutputStream() {
        // Test for method java.io.PrintWriter(java.io.OutputStream)
        String s;
        pw.println("Random Chars");
        pw.write("Hello World");
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            s = br.readLine();
            assertTrue("Incorrect string written/read: " + s, s
                    .equals("Random Chars"));
            s = br.readLine();
            assertTrue("Incorrect string written/read: " + s, s
                    .equals("Hello World"));
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
    }

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.io.OutputStream, boolean)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.io.OutputStream.class, boolean.class}
        )
    })
    public void test_ConstructorLjava_io_OutputStreamZ() {
        // Test for method java.io.PrintWriter(java.io.OutputStream, boolean)
        String s;
        pw = new PrintWriter(bao, true);
        pw.println("Random Chars");
        pw.write("Hello World");
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            s = br.readLine();
            assertTrue("Incorrect string written/read: " + s, s
                    .equals("Random Chars"));
            pw.flush();
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            s = br.readLine();
            assertTrue("Incorrect string written/read: " + s, s
                    .equals("Random Chars"));
            s = br.readLine();
            assertTrue("Incorrect string written/read: " + s, s
                    .equals("Hello World"));
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
    }

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.io.Writer)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.io.Writer.class}
        )
    })
    public void test_ConstructorLjava_io_Writer() {
        // Test for method java.io.PrintWriter(java.io.Writer)
        Support_StringWriter sw;
        pw = new PrintWriter(sw = new Support_StringWriter());
        pw.print("Hello");
        pw.flush();
        assertEquals("Failed to construct proper writer", 
                "Hello", sw.toString());
    }

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.io.Writer, boolean)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.io.Writer.class, boolean.class}
        )
    })
    public void test_ConstructorLjava_io_WriterZ() {
        // Test for method java.io.PrintWriter(java.io.Writer, boolean)
        Support_StringWriter sw;
        pw = new PrintWriter(sw = new Support_StringWriter(), true);
        pw.print("Hello");
        // Auto-flush should have happened
        assertEquals("Failed to construct proper writer", 
                "Hello", sw.toString());
    }

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.io.File)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.io.File.class}
        )
    })
    public void test_ConstructorLjava_io_File() throws Exception {
        File file = File.createTempFile(getClass().getName(), null);
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.close();
        } finally {
            file.delete();
        }
    }

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.io.File, java.lang.String)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.io.File.class, java.lang.String.class}
        )
    })
    public void test_ConstructorLjava_io_File_Ljava_lang_String() throws Exception {
        File file = File.createTempFile(getClass().getName(), null);
        try {
            PrintWriter writer = new PrintWriter(file, 
                    Charset.defaultCharset().name());
            writer.close();
        } finally {
            file.delete();
        }
    }

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.lang.String)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_ConstructorLjava_lang_String() throws Exception {
        File file = File.createTempFile(getClass().getName(), null);
        try {
            PrintWriter writer = new PrintWriter(file.getPath());
            writer.close();
        } finally {
            file.delete();
        }
    }

    /**
     * @tests java.io.PrintWriter#PrintWriter(java.lang.String, java.lang.String)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "PrintWriter",
          methodArgs = {java.lang.String.class, java.lang.String.class}
        )
    })
    public void test_ConstructorLjava_lang_String_Ljava_lang_String() throws Exception {
        File file = File.createTempFile(getClass().getName(), null);
        try {
            PrintWriter writer = new PrintWriter(file.getPath(), 
                    Charset.defaultCharset().name());
            writer.close();
        } finally {
            file.delete();
        }
    }

    /**
     * @tests java.io.PrintWriter#checkError()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "checkError",
          methodArgs = {}
        )
    })
    public void test_checkError() {
        // Test for method boolean java.io.PrintWriter.checkError()
        pw.close();
        pw.print(490000000000.08765);
        assertTrue("Failed to return error", pw.checkError());
    }

    /**
     * @tests java.io.PrintWriter#close()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "close",
          methodArgs = {}
        )
    })
    public void test_close() {
        // Test for method void java.io.PrintWriter.close()
        pw.close();
        pw.println("l");
        assertTrue("Write on closed stream failed to generate error", pw
                .checkError());
    }

    /**
     * @tests java.io.PrintWriter#flush()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "flush",
          methodArgs = {}
        )
    })
    public void test_flush() {
        // Test for method void java.io.PrintWriter.flush()
        final double dub = 490000000000.08765;
        pw.print(dub);
        pw.flush();
        assertTrue("Failed to flush", new String(bao.toByteArray())
                .equals(String.valueOf(dub)));
    }

    /**
     * @tests java.io.PrintWriter#print(char[])
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {char[].class}
        )
    })
    public void test_print$C() {
        // Test for method void java.io.PrintWriter.print(char [])
        String s = null;
        char[] schars = new char[11];
        "Hello World".getChars(0, 11, schars, 0);
        pw.print(schars);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect char[] string: " + s, s
                .equals("Hello World"));
        int r = 0;
        try {
            pw.print((char[]) null);
        } catch (NullPointerException e) {
            r = 1;
        }
        assertEquals("null pointer exception for printing null char[] is not caught",
                1, r);
    }

    /**
     * @tests java.io.PrintWriter#print(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {char.class}
        )
    })
    public void test_printC() {
        // Test for method void java.io.PrintWriter.print(char)
        pw.print('c');
        pw.flush();
        assertEquals("Wrote incorrect char string", "c", new String(bao.toByteArray())
                );
    }

    /**
     * @tests java.io.PrintWriter#print(double)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {double.class}
        )
    })
    public void test_printD() {
        // Test for method void java.io.PrintWriter.print(double)
        final double dub = 490000000000.08765;
        pw.print(dub);
        pw.flush();
        assertTrue("Wrote incorrect double string", new String(bao
                .toByteArray()).equals(String.valueOf(dub)));
    }

    /**
     * @tests java.io.PrintWriter#print(float)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {float.class}
        )
    })
    public void test_printF() {
        // Test for method void java.io.PrintWriter.print(float)
        final float flo = 49.08765f;
        pw.print(flo);
        pw.flush();
        assertTrue("Wrote incorrect float string",
                new String(bao.toByteArray()).equals(String.valueOf(flo)));
    }

    /**
     * @tests java.io.PrintWriter#print(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {int.class}
        )
    })
    public void test_printI() {
        // Test for method void java.io.PrintWriter.print(int)
        pw.print(4908765);
        pw.flush();
        assertEquals("Wrote incorrect int string", "4908765", new String(bao.toByteArray())
                );
    }

    /**
     * @tests java.io.PrintWriter#print(long)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {long.class}
        )
    })
    public void test_printJ() {
        // Test for method void java.io.PrintWriter.print(long)
        pw.print(49087650000L);
        pw.flush();
        assertEquals("Wrote incorrect long string", "49087650000", new String(bao.toByteArray())
                );
    }

    /**
     * @tests java.io.PrintWriter#print(java.lang.Object)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {java.lang.Object.class}
        )
    })
    public void test_printLjava_lang_Object() {
        // Test for method void java.io.PrintWriter.print(java.lang.Object)
        pw.print((Object) null);
        pw.flush();
        assertEquals("Did not write null", "null", new String(bao.toByteArray()));
        bao.reset();

        pw.print(new Bogus());
        pw.flush();
        assertEquals("Wrote in incorrect Object string", "Bogus", new String(bao
                .toByteArray()));
    }

    /**
     * @tests java.io.PrintWriter#print(java.lang.String)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_printLjava_lang_String() {
        // Test for method void java.io.PrintWriter.print(java.lang.String)
        pw.print((String) null);
        pw.flush();
        assertEquals("did not write null", "null", new String(bao.toByteArray()));
        bao.reset();

        pw.print("Hello World");
        pw.flush();
        assertEquals("Wrote incorrect  string", "Hello World", new String(bao.toByteArray()));
    }

    /**
     * @tests java.io.PrintWriter#print(boolean)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "print",
          methodArgs = {boolean.class}
        )
    })
    public void test_printZ() {
        // Test for method void java.io.PrintWriter.print(boolean)
        pw.print(true);
        pw.flush();
        assertEquals("Wrote in incorrect boolean string", "true", new String(bao
                .toByteArray()));
    }

    /**
     * @tests java.io.PrintWriter#println()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {}
        )
    })
    public void test_println() {
        // Test for method void java.io.PrintWriter.println()
        String s;
        pw.println("Blarg");
        pw.println();
        pw.println("Bleep");
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            s = br.readLine();
            assertTrue("Wrote incorrect line: " + s, s.equals("Blarg"));
            s = br.readLine();
            assertTrue("Wrote incorrect line: " + s, s.equals(""));
            s = br.readLine();
            assertTrue("Wrote incorrect line: " + s, s.equals("Bleep"));
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
    }

    /**
     * @tests java.io.PrintWriter#println(char[])
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {char[].class}
        )
    })
    public void test_println$C() {
        // Test for method void java.io.PrintWriter.println(char [])
        String s = null;
        char[] schars = new char[11];
        "Hello World".getChars(0, 11, schars, 0);
        pw.println("Random Chars");
        pw.println(schars);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            s = br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect char[] string: " + s, s
                .equals("Hello World"));
    }

    /**
     * @tests java.io.PrintWriter#println(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {char.class}
        )
    })
    public void test_printlnC() {
        // Test for method void java.io.PrintWriter.println(char)
        String s = null;
        pw.println("Random Chars");
        pw.println('c');
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            s = br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect char string: " + s, s.equals("c"));
    }

    /**
     * @tests java.io.PrintWriter#println(double)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {double.class}
        )
    })
    public void test_printlnD() {
        // Test for method void java.io.PrintWriter.println(double)
        String s = null;
        final double dub = 4000000000000000.657483;
        pw.println("Random Chars");
        pw.println(dub);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect double string: " + s, s.equals(String
                .valueOf(dub)));
    }

    /**
     * @tests java.io.PrintWriter#println(float)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {float.class}
        )
    })
    public void test_printlnF() {
        // Test for method void java.io.PrintWriter.println(float)
        String s;
        final float flo = 40.4646464f;
        pw.println("Random Chars");
        pw.println(flo);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
            assertTrue("Wrote incorrect float string: " + s + " wanted: "
                    + String.valueOf(flo), s.equals(String.valueOf(flo)));
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }

    }

    /**
     * @tests java.io.PrintWriter#println(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {int.class}
        )
    })
    public void test_printlnI() {
        // Test for method void java.io.PrintWriter.println(int)
        String s = null;
        pw.println("Random Chars");
        pw.println(400000);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect int string: " + s, s.equals("400000"));
    }

    /**
     * @tests java.io.PrintWriter#println(long)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {long.class}
        )
    })
    public void test_printlnJ() {
        // Test for method void java.io.PrintWriter.println(long)
        String s = null;
        pw.println("Random Chars");
        pw.println(4000000000000L);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect long string: " + s, s
                .equals("4000000000000"));
    }

    /**
     * @tests java.io.PrintWriter#println(java.lang.Object)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {java.lang.Object.class}
        )
    })
    public void test_printlnLjava_lang_Object() {
        // Test for method void java.io.PrintWriter.println(java.lang.Object)
        String s = null;
        pw.println("Random Chars");
        pw.println(new Bogus());
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect Object string: " + s, s.equals("Bogus"));
    }

    /**
     * @tests java.io.PrintWriter#println(java.lang.String)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_printlnLjava_lang_String() {
        // Test for method void java.io.PrintWriter.println(java.lang.String)
        String s = null;
        pw.println("Random Chars");
        pw.println("Hello World");
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect string: " + s, s.equals("Hello World"));
    }

    /**
     * @tests java.io.PrintWriter#println(boolean)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "println",
          methodArgs = {boolean.class}
        )
    })
    public void test_printlnZ() {
        // Test for method void java.io.PrintWriter.println(boolean)
        String s = null;
        pw.println("Random Chars");
        pw.println(false);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect boolean string: " + s, s.equals("false"));
    }

    /**
     * @tests java.io.PrintWriter#write(char[])
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "write",
          methodArgs = {char[].class}
        )
    })
    public void test_write$C() {
        // Test for method void java.io.PrintWriter.write(char [])
        String s = null;
        char[] schars = new char[11];
        "Hello World".getChars(0, 11, schars, 0);
        pw.println("Random Chars");
        pw.write(schars);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
        assertTrue("Wrote incorrect char[] string: " + s, s
                .equals("Hello World"));
    }

    /**
     * @tests java.io.PrintWriter#write(char[], int, int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "write",
          methodArgs = {char[].class, int.class, int.class}
        )
    })
    public void test_write$CII() {
        // Test for method void java.io.PrintWriter.write(char [], int, int)
        String s = null;
        char[] schars = new char[11];
        "Hello World".getChars(0, 11, schars, 0);
        pw.println("Random Chars");
        pw.write(schars, 6, 5);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect char[] string: " + s, s.equals("World"));
    }

    /**
     * @tests java.io.PrintWriter#write(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "write",
          methodArgs = {int.class}
        )
    })
    public void test_writeI() {
        // Test for method void java.io.PrintWriter.write(int)
        char[] cab = new char[3];
        pw.write('a');
        pw.write('b');
        pw.write('c');
        pw.flush();
        bai = new ByteArrayInputStream(bao.toByteArray());
        cab[0] = (char) bai.read();
        cab[1] = (char) bai.read();
        cab[2] = (char) bai.read();
        assertTrue("Wrote incorrect ints", cab[0] == 'a' && cab[1] == 'b'
                && cab[2] == 'c');

    }

    /**
     * @tests java.io.PrintWriter#write(java.lang.String)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "write",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_writeLjava_lang_String() {
        // Test for method void java.io.PrintWriter.write(java.lang.String)
        String s = null;
        pw.println("Random Chars");
        pw.write("Hello World");
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect char[] string: " + s, s
                .equals("Hello World"));
    }

    /**
     * @tests java.io.PrintWriter#write(java.lang.String, int, int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "write",
          methodArgs = {java.lang.String.class, int.class, int.class}
        )
    })
    public void test_writeLjava_lang_StringII() {
        // Test for method void java.io.PrintWriter.write(java.lang.String, int,
        // int)
        String s = null;
        pw.println("Random Chars");
        pw.write("Hello World", 6, 5);
        pw.flush();
        try {
            br = new BufferedReader(new Support_StringReader(bao.toString()));
            br.readLine();
            s = br.readLine();
        } catch (IOException e) {
            fail("IOException during test : " + e.getMessage());
        }
        assertTrue("Wrote incorrect char[] string: " + s, s.equals("World"));
    }
    
    /**
     * @tests java.io.PrintWriter#append(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "append",
          methodArgs = {char.class}
        )
    })
    public void test_appendChar() {
    char testChar = ' ';
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintWriter printWriter = new PrintWriter(out);
    printWriter.append(testChar);
    printWriter.flush();
    assertEquals(String.valueOf(testChar),out.toString());
    printWriter.close();
    }
    /**
     * @tests java.io.PrintWriter#append(CharSequence)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "append",
          methodArgs = {java.lang.CharSequence.class}
        )
    })
    public void test_appendCharSequence() {
        
        String testString = "My Test String";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.append(testString);
        printWriter.flush();
        assertEquals(testString, out.toString());
        printWriter.close();    

    }

    /**
     *  @tests java.io.PrintWriter#append(CharSequence, int, int)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "IndexOutOfBoundsException checking missed.",
      targets = {
        @TestTarget(
          methodName = "append",
          methodArgs = {java.lang.CharSequence.class, int.class, int.class}
        )
    })
    public void test_appendCharSequenceIntInt() {
        String testString = "My Test String";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.append(testString, 1, 3);
        printWriter.flush();
        assertEquals(testString.substring(1, 3), out.toString());
        printWriter.close();

    }

    /**
     * @tests java.io.PrintWriter#format(java.lang.String, java.lang.Object...)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "format",
          methodArgs = {java.lang.String.class, java.lang.Object[].class}
        )
    })
    public void test_formatLjava_lang_String$Ljava_lang_Object() {
        pw.format("%s %s", "Hello", "World");
        pw.flush();
        assertEquals("Wrote incorrect string", "Hello World", 
                new String(bao.toByteArray()));
    }

    /**
     * @tests java.io.PrintWriter#format(java.util.Locale, java.lang.String, java.lang.Object...)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "format",
          methodArgs = {java.util.Locale.class, java.lang.String.class, java.lang.Object[].class}
        )
    })
    public void test_formatLjava_util_Locale_Ljava_lang_String_$Ljava_lang_Object() {
        pw.format(Locale.US, "%s %s", "Hello", "World");
        pw.flush();
        assertEquals("Wrote incorrect string", "Hello World", 
                new String(bao.toByteArray()));
    }

    /**
     * @tests java.io.PrintWriter#printf(java.lang.String, java.lang.Object...)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "printf",
          methodArgs = {java.lang.String.class, java.lang.Object[].class}
        )
    })
    public void test_printfLjava_lang_String$Ljava_lang_Object() {
        pw.printf("%s %s", "Hello", "World");
        pw.flush();
        assertEquals("Wrote incorrect string", "Hello World", 
                new String(bao.toByteArray()));
    }

    /**
     * @tests java.io.PrintWriter#printf(java.util.Locale, java.lang.String, java.lang.Object...)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Exceptions checking missed.",
      targets = {
        @TestTarget(
          methodName = "printf",
          methodArgs = {java.util.Locale.class, java.lang.String.class, java.lang.Object[].class}
        )
    })
    public void test_printfLjava_util_Locale_Ljava_lang_String_$Ljava_lang_Object() {
        pw.printf(Locale.US, "%s %s", "Hello", "World");
        pw.flush();
        assertEquals("Wrote incorrect string", "Hello World", 
                new String(bao.toByteArray()));
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
        bao = new ByteArrayOutputStream();
        pw = new PrintWriter(bao, false);

    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
        try {
            pw.close();
        } catch (Exception e) {
        }
    }
}
