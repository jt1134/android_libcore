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

/**
 * @author Alexander V. Astapchuk
 * @version $Revision$
 */

package org.apache.harmony.security.tests.java.security;

import dalvik.annotation.TestTargetClass;
import dalvik.annotation.TestTarget;
import dalvik.annotation.TestInfo;
import dalvik.annotation.TestLevel;

import java.security.Timestamp;
import java.security.cert.CertPath;
import java.util.Date;

import org.apache.harmony.security.tests.support.cert.MyCertPath;

import junit.framework.TestCase;
@TestTargetClass(Timestamp.class)
/**
 * Tests for <code>Timestamp</code> class fields and methods
 * 
 */

public class TimestampTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TimestampTest.class);
    }

    private Date now = new Date();

    private static final byte[] encoding = { 1, 2, 3 };

    private CertPath cpath = new MyCertPath(encoding);

    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Non null parameters checking missed",
      targets = {
        @TestTarget(
          methodName = "Timestamp",
          methodArgs = {Date.class, CertPath.class}
        )
    })
    public void testTimestamp() {
        try {
            new Timestamp(null, cpath);
            fail("null was accepted");
        } catch (NullPointerException ex) { /* ok */
        }

        try {
            new Timestamp(now, null);
            fail("null was accepted");
            return;
        } catch (NullPointerException ex) { /* ok */
        }
    }

    /*
     * Class under test for boolean equals(Object)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "equals",
          methodArgs = {Object.class}
        )
    })
    public void testEqualsObject() {
        Timestamp one = new Timestamp(now, cpath);
        Timestamp two = new Timestamp(now, cpath);

        assertTrue(one.equals(one));
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
        assertFalse(one.equals(null));
        assertFalse(one.equals(new Object()));

        Timestamp two1 = new Timestamp(new Date(9999), cpath);
        assertFalse(one.equals(two1));
        assertTrue(two1.equals(two1));
    }

    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getSignerCertPath",
          methodArgs = {}
        )
    })
    public void testGetSignerCertPath() {
        assertSame(new Timestamp(now, cpath).getSignerCertPath(), cpath);
    }

    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getTimestamp",
          methodArgs = {}
        )
    })
    public void testGetTimestamp() {
        Timestamp t = new Timestamp(now, cpath);
        assertEquals(now, t.getTimestamp());
        assertNotSame(now, t.getTimestamp());
    }

    /*
     * Class under test for String toString()
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Test result is not verivied",
      targets = {
        @TestTarget(
          methodName = "toString",
          methodArgs = {}
        )
    })
    public void testToString() {
        new Timestamp(now, cpath).toString();
    }

    /*
     * Class under test for String hashCode()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "hashCode",
          methodArgs = {}
        )
    })
    public void testHashCode() {
        Timestamp one = new Timestamp(now, cpath);
        Timestamp two = new Timestamp(now, cpath);
        Timestamp three = new Timestamp(now, new MyCertPath(new byte[] { 10,
                20, 30 }));
        Timestamp four = null;
        
        assertTrue(one.hashCode() == two.hashCode());
        assertTrue(one.hashCode() != three.hashCode());
        assertTrue(two.hashCode() != three.hashCode());
        
        try {
            four.hashCode();
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }

    }
}
