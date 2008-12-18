/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.text.tests.java.text;

import dalvik.annotation.TestInfo;
import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTarget;
import dalvik.annotation.TestTargetClass;

import junit.framework.TestCase;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Test for additional features introduced by icu. These tests fail on the RI
 * but succeed on Android 1.0. The features are only accessible through the
 * pattern. Api methods where not introduced to allow direct access of these
 * features. This would have changed the api too much.
 */
@TestTargetClass(DecimalFormat.class)
public class DecimalFormatTestICU extends TestCase {

    DecimalFormat format;

    protected void setUp() {
        format = (DecimalFormat) NumberFormat.getNumberInstance();
    }

    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "applyPattern",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_sigDigPattern() throws Exception {
        format.applyPattern("@@@");
        assertEquals("12300", format.format(12345));
        assertEquals("0.123", format.format(0.12345));

        format.applyPattern("@@##");
        assertEquals("3.142", format.format(3.14159));
        assertEquals("1.23", format.format(1.23004));

        try {
            format.applyPattern("@00");
            fail("expected IllegalArgumentException was not thrown for "
                    + "pattern \"@00\".");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            format.applyPattern("@.###");
            fail("expected IllegalArgumentException was not thrown for "
                    + "pattern \"@.###\".");
        } catch (IllegalArgumentException e) {
            // expected
        }

        format.applyPattern("@@###E0");
        assertEquals("1.23E1", format.format(12.3));
        assertEquals(12.3f, format.parse("1.23E1").floatValue());
        format.applyPattern("0.0###E0");
        assertEquals("1.23E1", format.format(12.3));
        assertEquals(12.3f, format.parse("1.23E1").floatValue());
    }

    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Regression test.",
      targets = {
        @TestTarget(
          methodName = "format",
          methodArgs = {Object.class}
        ),
        @TestTarget(
          methodName = "parse",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_paddingPattern() throws Exception {
        format.applyPattern("*x##,##,#,##0.0#");
        assertEquals("xxxxxxxxx123.0", format.format(123));
        assertEquals(123, format.parse("xxxxxxxxx123.0").intValue());

        format.applyPattern("*\u00e7#0 o''clock");
        assertEquals("\u00e72 o'clock", format.format(2));
        assertEquals("12 o'clock", format.format(12));
        assertEquals(2, format.parse("\u00e72 o'clock").intValue());
        assertEquals(12, format.parse("12 o'clock").intValue());

        try {
            format.applyPattern("#0.##*xE0");
            fail("expected IllegalArgumentException was not thrown for"
                    + "pattern \"#0.##*xE0\".");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            format.applyPattern("##0.## *");
            fail("expected IllegalArgumentException was not thrown for "
                    + "pattern \"##0.## *\".");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Regression test.",
      targets = {
        @TestTarget(
          methodName = "format",
          methodArgs = {Object.class}
        ),
        @TestTarget(
          methodName = "parse",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_positiveExponentSign() throws Exception {
        format.applyPattern("0.###E+0");
        assertEquals("1E+2", format.format(100));
        assertEquals("1E-2", format.format(0.01));
        assertEquals(100, format.parse("1E+2").intValue());
        assertEquals(0.01f, format.parse("1E-2").floatValue());

        format.applyPattern("0.###E0 m/s");
        assertEquals("1E2 m/s", format.format(100));
        assertEquals(100, format.parse("1E2 m/s").intValue());

        format.applyPattern("00.###E0");
        assertEquals("12.3E-4", format.format(0.00123));
        assertEquals(0.00123f, format.parse("12.3E-4").floatValue());

        format.applyPattern("##0.####E0");
        assertEquals("12.345E3", format.format(12345));
        assertEquals(12345, format.parse("12.345E3").intValue());

        try {
            format.applyPattern("#,##0.##E0");
            fail("expected IllegalArgumentException was not thrown for "
                    + "pattern \"#,##0.##E0\".");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Verifies the grouping size.",
      targets = {
        @TestTarget(
          methodName = "format",
          methodArgs = {Object.class}
        ),
        @TestTarget(
          methodName = "parse",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_secondaryGroupingSize() throws Exception {
        format.applyPattern("#,##,###,####");
        assertEquals("123,456,7890", format.format(1234567890));
        assertEquals(1234567890, format.parse("123,456,7890").intValue());
        format.applyPattern("##,#,###,####");
        assertEquals("123,456,7890", format.format(1234567890));
        assertEquals(1234567890, format.parse("123,456,7890").intValue());
        format.applyPattern("###,###,####");
        assertEquals("123,456,7890", format.format(1234567890));
        assertEquals(1234567890, format.parse("123,456,7890").intValue());
    }
}
