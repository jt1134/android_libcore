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

package tests.api.java.lang.reflect;

import dalvik.annotation.TestInfo;
import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTarget;
import dalvik.annotation.TestTargetClass;

import java.lang.reflect.Modifier;

@TestTargetClass(Modifier.class) 
public class ModifierTest extends junit.framework.TestCase {

    private static final int ALL_FLAGS = 0x7FF;

    /**
     * @tests java.lang.reflect.Modifier#Modifier()
     */
    @TestInfo(
      level = TestLevel.TODO,
      purpose = "Empty test.",
      targets = {
        @TestTarget(
          methodName = "Modifier",
          methodArgs = {}
        )
    })
    public void test_Constructor() {
        // Test for method java.lang.reflect.Modifier()
        // Does nothing
    }

    /**
     * @tests java.lang.reflect.Modifier#isAbstract(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isAbstract",
          methodArgs = {int.class}
        )
    })
    public void test_isAbstractI() {
        // Test for method boolean java.lang.reflect.Modifier.isAbstract(int)
        assertTrue("ABSTRACT returned false", Modifier.isAbstract(ALL_FLAGS));
        assertTrue("ABSTRACT returned false", Modifier
                .isAbstract(Modifier.ABSTRACT));
        assertTrue("Non-ABSTRACT returned true", !Modifier
                .isAbstract(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isFinal(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isFinal",
          methodArgs = {int.class}
        )
    })
    public void test_isFinalI() {
        // Test for method boolean java.lang.reflect.Modifier.isFinal(int)
        assertTrue("FINAL returned false", Modifier.isFinal(ALL_FLAGS));
        assertTrue("FINAL returned false", Modifier.isFinal(Modifier.FINAL));
        assertTrue("Non-FINAL returned true", !Modifier
                .isFinal(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isInterface(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isInterface",
          methodArgs = {int.class}
        )
    })
    public void test_isInterfaceI() {
        // Test for method boolean java.lang.reflect.Modifier.isInterface(int)
        assertTrue("INTERFACE returned false", Modifier.isInterface(ALL_FLAGS));
        assertTrue("INTERFACE returned false", Modifier
                .isInterface(Modifier.INTERFACE));
        assertTrue("Non-INTERFACE returned true", !Modifier
                .isInterface(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isNative(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isNative",
          methodArgs = {int.class}
        )
    })
    public void test_isNativeI() {
        // Test for method boolean java.lang.reflect.Modifier.isNative(int)
        assertTrue("NATIVE returned false", Modifier.isNative(ALL_FLAGS));
        assertTrue("NATIVE returned false", Modifier.isNative(Modifier.NATIVE));
        assertTrue("Non-NATIVE returned true", !Modifier
                .isNative(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isPrivate(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isPrivate",
          methodArgs = {int.class}
        )
    })
    public void test_isPrivateI() {
        // Test for method boolean java.lang.reflect.Modifier.isPrivate(int)
        assertTrue("PRIVATE returned false", Modifier.isPrivate(ALL_FLAGS));
        assertTrue("PRIVATE returned false", Modifier
                .isPrivate(Modifier.PRIVATE));
        assertTrue("Non-PRIVATE returned true", !Modifier
                .isPrivate(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isProtected(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isProtected",
          methodArgs = {int.class}
        )
    })
    public void test_isProtectedI() {
        // Test for method boolean java.lang.reflect.Modifier.isProtected(int)
        assertTrue("PROTECTED returned false", Modifier.isProtected(ALL_FLAGS));
        assertTrue("PROTECTED returned false", Modifier
                .isProtected(Modifier.PROTECTED));
        assertTrue("Non-PROTECTED returned true", !Modifier
                .isProtected(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isPublic(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isPublic",
          methodArgs = {int.class}
        )
    })
    public void test_isPublicI() {
        // Test for method boolean java.lang.reflect.Modifier.isPublic(int)
        assertTrue("PUBLIC returned false", Modifier.isPublic(ALL_FLAGS));
        assertTrue("PUBLIC returned false", Modifier.isPublic(Modifier.PUBLIC));
        assertTrue("Non-PUBLIC returned true", !Modifier
                .isPublic(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isStatic(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isStatic",
          methodArgs = {int.class}
        )
    })
    public void test_isStaticI() {
        // Test for method boolean java.lang.reflect.Modifier.isStatic(int)
        assertTrue("STATIC returned false", Modifier.isStatic(ALL_FLAGS));
        assertTrue("STATIC returned false", Modifier.isStatic(Modifier.STATIC));
        assertTrue("Non-STATIC returned true", !Modifier
                .isStatic(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isStrict(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isStrict",
          methodArgs = {int.class}
        )
    })
    public void test_isStrictI() {
        // Test for method boolean java.lang.reflect.Modifier.isStrict(int)
        assertTrue("STRICT returned false", Modifier.isStrict(Modifier.STRICT));
        assertTrue("Non-STRICT returned true", !Modifier
                .isStrict(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#isSynchronized(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isSynchronized",
          methodArgs = {int.class}
        )
    })
    public void test_isSynchronizedI() {
        // Test for method boolean
        // java.lang.reflect.Modifier.isSynchronized(int)
        assertTrue("Synchronized returned false", Modifier
                .isSynchronized(ALL_FLAGS));
        assertTrue("Non-Synchronized returned true", !Modifier
                .isSynchronized(Modifier.VOLATILE));
    }

    /**
     * @tests java.lang.reflect.Modifier#isTransient(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isTransient",
          methodArgs = {int.class}
        )
    })
    public void test_isTransientI() {
        // Test for method boolean java.lang.reflect.Modifier.isTransient(int)
        assertTrue("Transient returned false", Modifier.isTransient(ALL_FLAGS));
        assertTrue("Transient returned false", Modifier
                .isTransient(Modifier.TRANSIENT));
        assertTrue("Non-Transient returned true", !Modifier
                .isTransient(Modifier.VOLATILE));
    }

    /**
     * @tests java.lang.reflect.Modifier#isVolatile(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "isVolatile",
          methodArgs = {int.class}
        )
    })
    public void test_isVolatileI() {
        // Test for method boolean java.lang.reflect.Modifier.isVolatile(int)
        assertTrue("Volatile returned false", Modifier.isVolatile(ALL_FLAGS));
        assertTrue("Volatile returned false", Modifier
                .isVolatile(Modifier.VOLATILE));
        assertTrue("Non-Volatile returned true", !Modifier
                .isVolatile(Modifier.TRANSIENT));
    }

    /**
     * @tests java.lang.reflect.Modifier#toString(int)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "toString",
          methodArgs = {int.class}
        )
    })
    public void test_toStringI() {
        // Test for method java.lang.String
        // java.lang.reflect.Modifier.toString(int)
        assertTrue("Returned incorrect string value: "
                + Modifier.toString(java.lang.reflect.Modifier.PUBLIC
                        + java.lang.reflect.Modifier.ABSTRACT), Modifier
                .toString(
                        java.lang.reflect.Modifier.PUBLIC
                                + java.lang.reflect.Modifier.ABSTRACT).equals(
                        "public abstract"));
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
    }
}
