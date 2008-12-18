/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.security.interfaces;
import dalvik.annotation.TestInfo;
import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTarget;
import dalvik.annotation.TestTargetClass;

import junit.framework.TestCase;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateCrtKey;

@TestTargetClass(RSAPrivateCrtKey.class)
public class RSAPrivateCrtKeyTest extends TestCase {
    
    RSAPrivateCrtKey key = null;
    
    protected void setUp() throws Exception {
        super.setUp();
        KeyFactory gen = KeyFactory.getInstance("RSA", Util.prov);
        key = (RSAPrivateCrtKey) gen.generatePrivate(Util.rsaCrtParam);
    }
    
    /**
     * @tests java.security.interfaces.RSAPrivateCrtKey
     * #getCrtCoefficient()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getCrtCoefficient",
          methodArgs = {}
        )
    })
    public void test_getCrtCoefficient() {
        assertEquals("invalid CRT coefficient",
                Util.rsaCrtParam.getCrtCoefficient(), key.getCrtCoefficient());
    }
    
    /**
     * @tests java.security.interfaces.RSAPrivateCrtKey
     * #getPrimeExponentP()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPrimeExponentP",
          methodArgs = {}
        )
    })
    public void test_getPrimeExponentP() {
        assertEquals("invalid prime exponent P",
                Util.rsaCrtParam.getPrimeExponentP(), key.getPrimeExponentP());
    }
    
    /**
     * @tests java.security.interfaces.RSAPrivateCrtKey
     * #getPrimeExponentQ()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPrimeExponentQ",
          methodArgs = {}
        )
    })
    public void test_getPrimeExponentQ() {
        assertEquals("invalid prime exponent Q",
                Util.rsaCrtParam.getPrimeExponentQ(), key.getPrimeExponentQ());
    }
    
    /**
     * @tests java.security.interfaces.RSAPrivateCrtKey
     * #getPrimeP()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPrimeP",
          methodArgs = {}
        )
    })
    public void test_getPrimeP() {
        assertEquals("invalid prime P",
                Util.rsaCrtParam.getPrimeP(), key.getPrimeP());
    }
    
    /**
     * @tests java.security.interfaces.RSAPrivateCrtKey
     * #getPrimeQ()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPrimeQ",
          methodArgs = {}
        )
    })
    public void test_getPrimeQ() {
        assertEquals("invalid prime Q",
                Util.rsaCrtParam.getPrimeQ(), key.getPrimeQ());
    }
    
    /**
     * @tests java.security.interfaces.RSAPrivateCrtKey
     * #getPublicExponent()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPublicExponent",
          methodArgs = {}
        )
    })
    public void test_getPublicExponent() {
        assertEquals("invalid public exponent",
                Util.rsaCrtParam.getPublicExponent(), key.getPublicExponent());
    }
    
    protected void tearDown() throws Exception {
        key = null;
        super.tearDown();
    }
}
