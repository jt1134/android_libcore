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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package tests.security.spec;

import dalvik.annotation.TestInfo;
import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTarget;
import dalvik.annotation.TestTargetClass;

import junit.framework.TestCase;

import java.math.BigInteger;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.KeySpec;

/**
 * Tests for <code>DSAPublicKeySpec</code>
 * 
 */
@TestTargetClass(DSAPublicKeySpec.class)
public class DSAPublicKeySpecTest extends TestCase {

    /**
     * Constructor for DSAPublicKeySpecTest.
     * @param name
     */
    public DSAPublicKeySpecTest(String name) {
        super(name);
    }

    /**
     * Test for <code>DSAPublicKeySpec</code> ctor
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "DSAPublicKeySpec",
          methodArgs = {java.math.BigInteger.class, java.math.BigInteger.class, java.math.BigInteger.class, java.math.BigInteger.class}
        )
    })
    public final void testDSAPublicKeySpec() {
        KeySpec ks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertTrue(ks instanceof DSAPublicKeySpec);
    }

    /**
     * Test for <code>getG</code> method 
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getG",
          methodArgs = {}
        )
    })
    public final void testGetG() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertEquals(4, dpks.getG().intValue());
    }

    /**
     * Test for <code>getP</code> method 
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getP",
          methodArgs = {}
        )
    })
    public final void testGetP() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertEquals(2, dpks.getP().intValue());
    }

    /**
     * Test for <code>getQ</code> method 
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getQ",
          methodArgs = {}
        )
    })
    public final void testGetQ() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertEquals(3, dpks.getQ().intValue());
    }

    /**
     * Test for <code>getY</code> method 
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getY",
          methodArgs = {}
        )
    })
    public final void testGetY() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertEquals(1, dpks.getY().intValue());
    }
}
