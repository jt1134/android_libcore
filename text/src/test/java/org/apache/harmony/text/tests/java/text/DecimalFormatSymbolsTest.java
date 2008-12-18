/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

@TestTargetClass(DecimalFormatSymbols.class) 
public class DecimalFormatSymbolsTest extends TestCase {

    DecimalFormatSymbols dfs;

    DecimalFormatSymbols dfsUS;

    /**
     * @tests java.text.DecimalFormatSymbols#DecimalFormatSymbols() Test of
     *        method java.text.DecimalFormatSymbols#DecimalFormatSymbols().
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "DecimalFormatSymbols",
          methodArgs = {}
        )
    })
    public void test_Constructor() {
        // Test for method java.text.DecimalFormatSymbols()
        try {
            new DecimalFormatSymbols();
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @tests java.text.DecimalFormatSymbols#DecimalFormatSymbols(java.util.Locale)
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "NullPointerException is not verified.",
      targets = {
        @TestTarget(
          methodName = "DecimalFormatSymbols",
          methodArgs = {java.util.Locale.class}
        )
    })
    public void test_ConstructorLjava_util_Locale() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("en",
                "us"));
        assertEquals("Returned incorrect symbols", '%', dfs.getPercent());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#clone() Test of method
     *        java.text.DecimalFormatSymbols#clone(). Case 1: Compare of
     *        internal variables of cloned objects. Case 2: Compare of clones.
     *        Case 3: Change the content of the clone.
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "clone",
          methodArgs = {}
        )
    })
    public void test_clone() {
        try {
            // case 1: Compare of internal variables of cloned objects
            DecimalFormatSymbols fs = new DecimalFormatSymbols(Locale.US);
            DecimalFormatSymbols fsc = (DecimalFormatSymbols) fs.clone();
            assertEquals(fs.getCurrency(), fsc.getCurrency());

            // case 2: Compare of clones
            fs = new DecimalFormatSymbols();
            DecimalFormatSymbols fsc2 = (DecimalFormatSymbols) (fs.clone());
            // make sure the objects are equal
            assertTrue("Object's clone isn't equal!", fs.equals(fsc2));

            // case 3:
            // change the content of the clone and make sure it's not equal
            // anymore
            // verifies that it's data is now distinct from the original
            fs.setNaN("not-a-number");
            assertTrue("Object's changed clone should not be equal!", !fs
                    .equals(fsc2));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @tests java.text.DecimalFormatSymbols#equals(java.lang.Object)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "equals",
          methodArgs = {java.lang.Object.class}
        )
    })
    public void test_equalsLjava_lang_Object() {
        assertTrue("Equal objects returned false", dfs.equals(dfs.clone()));
        dfs.setDigit('B');
        assertTrue("Un-Equal objects returned true", !dfs
                .equals(new DecimalFormatSymbols()));

    }

    /**
     * @tests java.text.DecimalFormatSymbols#getCurrency()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getCurrency",
          methodArgs = {}
        )
    })
    public void test_getCurrency() {
        Currency currency = Currency.getInstance("USD");
        assertTrue("Returned incorrect currency",
                dfsUS.getCurrency() == currency);

        // BEGIN android-changed
        // use cs_CZ instead
        //Currency currK = Currency.getInstance("KRW");
        Currency currC = Currency.getInstance("CZK");
        Currency currX = Currency.getInstance("XXX");
        Currency currE = Currency.getInstance("EUR");
        // Currency currF = Currency.getInstance("FRF");

        DecimalFormatSymbols dfs1 = new DecimalFormatSymbols(new Locale("cs",
                "CZ"));
        assertTrue("Test1: Returned incorrect currency",
                dfs1.getCurrency() == currC);
        assertEquals("Test1: Returned incorrect currencySymbol", "K\u010d", dfs1
                .getCurrencySymbol());
        assertEquals("Test1: Returned incorrect intlCurrencySymbol", "CZK",
                dfs1.getInternationalCurrencySymbol());

        dfs1 = new DecimalFormatSymbols(new Locale("", "CZ"));
        assertTrue("Test2: Returned incorrect currency",
                dfs1.getCurrency() == currC);
        assertEquals("Test2: Returned incorrect currencySymbol", "CZK", dfs1
                .getCurrencySymbol());
        assertEquals("Test2: Returned incorrect intlCurrencySymbol", "CZK",
                dfs1.getInternationalCurrencySymbol());

        dfs1 = new DecimalFormatSymbols(new Locale("cs", ""));
        assertEquals("Test3: Returned incorrect currency",
                currX, dfs1.getCurrency());
        assertEquals("Test3: Returned incorrect currencySymbol", "\u00a4", dfs1
                .getCurrencySymbol());
        assertEquals("Test3: Returned incorrect intlCurrencySymbol", "XXX",
                dfs1.getInternationalCurrencySymbol());

        dfs1 = new DecimalFormatSymbols(new Locale("de", "AT"));
        // END android-changed
        assertTrue("Test4: Returned incorrect currency",
                dfs1.getCurrency() == currE);
        assertEquals("Test4: Returned incorrect currencySymbol", "\u20ac", dfs1
                .getCurrencySymbol());
        assertEquals("Test4: Returned incorrect intlCurrencySymbol", "EUR",
                dfs1.getInternationalCurrencySymbol());

        // RI fails these tests since it doesn't have the PREEURO variant
        // dfs1 = new DecimalFormatSymbols(new Locale("fr", "FR","PREEURO"));
        // assertTrue("Test5: Returned incorrect currency", dfs1.getCurrency()
        // == currF);
        // assertTrue("Test5: Returned incorrect currencySymbol",
        // dfs1.getCurrencySymbol().equals("F"));
        // assertTrue("Test5: Returned incorrect intlCurrencySymbol",
        // dfs1.getInternationalCurrencySymbol().equals("FRF"));
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getCurrencySymbol()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getCurrencySymbol",
          methodArgs = {}
        )
    })
    public void test_getCurrencySymbol() {
        assertEquals("Returned incorrect currencySymbol", "$", dfsUS
                .getCurrencySymbol());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getDecimalSeparator()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getDecimalSeparator",
          methodArgs = {}
        )
    })
    public void test_getDecimalSeparator() {
        dfs.setDecimalSeparator('*');
        assertEquals("Returned incorrect DecimalSeparator symbol", '*', dfs
                .getDecimalSeparator());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getDigit()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getDigit",
          methodArgs = {}
        )
    })
    public void test_getDigit() {
        dfs.setDigit('*');
        assertEquals("Returned incorrect Digit symbol", '*', dfs.getDigit());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getGroupingSeparator()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getGroupingSeparator",
          methodArgs = {}
        )
    })
    public void test_getGroupingSeparator() {
        dfs.setGroupingSeparator('*');
        assertEquals("Returned incorrect GroupingSeparator symbol", '*', dfs
                .getGroupingSeparator());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getInfinity()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getInfinity",
          methodArgs = {}
        )
    })
    public void test_getInfinity() {
        dfs.setInfinity("&");
        assertTrue("Returned incorrect Infinity symbol",
                dfs.getInfinity() == "&");
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getInternationalCurrencySymbol()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getInternationalCurrencySymbol",
          methodArgs = {}
        )
    })
    public void test_getInternationalCurrencySymbol() {
        assertEquals("Returned incorrect InternationalCurrencySymbol", "USD",
                dfsUS.getInternationalCurrencySymbol());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getMinusSign()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getMinusSign",
          methodArgs = {}
        )
    })
    public void test_getMinusSign() {
        dfs.setMinusSign('&');
        assertEquals("Returned incorrect MinusSign symbol", '&', dfs
                .getMinusSign());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getMonetaryDecimalSeparator() Test
     *        of method
     *        java.text.DecimalFormatSymbols#getMonetaryDecimalSeparator().
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getMonetaryDecimalSeparator",
          methodArgs = {}
        )
    })
    public void test_getMonetaryDecimalSeparator() {
        try {
            dfs.setMonetaryDecimalSeparator(',');
            assertEquals("Returned incorrect MonetaryDecimalSeparator symbol",
                    ',', dfs.getMonetaryDecimalSeparator());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getNaN()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getNaN",
          methodArgs = {}
        )
    })
    public void test_getNaN() {
        dfs.setNaN("NAN!!");
        assertEquals("Returned incorrect nan symbol", "NAN!!", dfs.getNaN());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getPatternSeparator()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPatternSeparator",
          methodArgs = {}
        )
    })
    public void test_getPatternSeparator() {
        dfs.setPatternSeparator('X');
        assertEquals("Returned incorrect PatternSeparator symbol", 'X', dfs
                .getPatternSeparator());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getPercent()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPercent",
          methodArgs = {}
        )
    })
    public void test_getPercent() {
        dfs.setPercent('*');
        assertEquals("Returned incorrect Percent symbol", '*', dfs.getPercent());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getPerMill()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getPerMill",
          methodArgs = {}
        )
    })
    public void test_getPerMill() {
        dfs.setPerMill('#');
        assertEquals("Returned incorrect PerMill symbol", '#', dfs.getPerMill());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#getZeroDigit()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getZeroDigit",
          methodArgs = {}
        )
    })
    public void test_getZeroDigit() {
        dfs.setZeroDigit('*');
        assertEquals("Returned incorrect ZeroDigit symbol", '*', dfs
                .getZeroDigit());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#hashCode() Test of method
     *        java.text.DecimalFormatSymbols#hashCode().
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
    public void test_hashCode() {
        try {
            DecimalFormatSymbols dfs1 = new DecimalFormatSymbols();
            DecimalFormatSymbols dfs2 = (DecimalFormatSymbols) dfs1.clone();
            assertTrue("Hash codes of equal object are equal", dfs2
                    .hashCode() == dfs1.hashCode());
            dfs1.setInfinity("infinity_infinity");
            // BEGIN android-changed
            assertTrue("Hash codes of non-equal objects are equal", dfs2
                    .hashCode() != dfs1.hashCode());
            // END android-changed
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setCurrency(java.util.Currency)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setCurrency",
          methodArgs = {java.util.Currency.class}
        )
    })
    public void test_setCurrencyLjava_util_Currency() {
        Locale locale = Locale.CANADA;
        DecimalFormatSymbols dfs = ((DecimalFormat) NumberFormat
                .getCurrencyInstance(locale)).getDecimalFormatSymbols();

        try {
            dfs.setCurrency(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
        }

        Currency currency = Currency.getInstance("JPY");
        dfs.setCurrency(currency);

        assertTrue("Returned incorrect currency", currency == dfs.getCurrency());
        assertTrue("Returned incorrect currency symbol", currency.getSymbol(
                locale).equals(dfs.getCurrencySymbol()));
        assertTrue("Returned incorrect international currency symbol", currency
                .getCurrencyCode().equals(dfs.getInternationalCurrencySymbol()));
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setCurrencySymbol(java.lang.String)
     *        Test of method
     *        java.text.DecimalFormatSymbols#setCurrencySymbol(java.lang.String).
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setCurrencySymbol",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_setCurrencySymbolLjava_lang_String() {
        try {
            dfs.setCurrencySymbol("$");
            assertEquals("Returned incorrect CurrencySymbol symbol", "$", dfs
                    .getCurrencySymbol());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setDecimalSeparator(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setDecimalSeparator",
          methodArgs = {char.class}
        )
    })
    public void test_setDecimalSeparatorC() {
        dfs.setDecimalSeparator('*');
        assertEquals("Returned incorrect DecimalSeparator symbol", '*', dfs
                .getDecimalSeparator());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setDigit(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setDigit",
          methodArgs = {char.class}
        )
    })
    public void test_setDigitC() {
        dfs.setDigit('*');
        assertEquals("Returned incorrect Digit symbol", '*', dfs.getDigit());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setGroupingSeparator(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setGroupingSeparator",
          methodArgs = {char.class}
        )
    })
    public void test_setGroupingSeparatorC() {
        dfs.setGroupingSeparator('*');
        assertEquals("Returned incorrect GroupingSeparator symbol", '*', dfs
                .getGroupingSeparator());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setInfinity(java.lang.String)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setInfinity",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_setInfinityLjava_lang_String() {
        dfs.setInfinity("&");
        assertTrue("Returned incorrect Infinity symbol",
                dfs.getInfinity() == "&");
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setInternationalCurrencySymbol(java.lang.String)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setInternationalCurrencySymbol",
          methodArgs = {java.lang.String.class}
        )
    })
    public void _test_setInternationalCurrencySymbolLjava_lang_String() {
        Locale locale = Locale.CANADA;
        DecimalFormatSymbols dfs = ((DecimalFormat) NumberFormat
                .getCurrencyInstance(locale)).getDecimalFormatSymbols();
        Currency currency = Currency.getInstance("JPY");
        dfs.setInternationalCurrencySymbol(currency.getCurrencyCode());

        assertTrue("Test1: Returned incorrect currency", currency == dfs
                .getCurrency());
        assertTrue("Test1: Returned incorrect currency symbol", currency
                .getSymbol(locale).equals(dfs.getCurrencySymbol()));
        assertTrue("Test1: Returned incorrect international currency symbol",
                currency.getCurrencyCode().equals(
                        dfs.getInternationalCurrencySymbol()));

        String symbol = dfs.getCurrencySymbol();
        dfs.setInternationalCurrencySymbol("bogus");
        assertNull("Test2: Returned incorrect currency", dfs.getCurrency());
        assertTrue("Test2: Returned incorrect currency symbol", dfs
                .getCurrencySymbol().equals(symbol));
        assertEquals("Test2: Returned incorrect international currency symbol",
                "bogus", dfs.getInternationalCurrencySymbol());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setMinusSign(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setMinusSign",
          methodArgs = {char.class}
        )
    })
    public void test_setMinusSignC() {
        dfs.setMinusSign('&');
        assertEquals("Returned incorrect MinusSign symbol", '&', dfs
                .getMinusSign());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setMonetaryDecimalSeparator(char)
     *        Test of method
     *        java.text.DecimalFormatSymbols#setMonetaryDecimalSeparator(char).
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setMonetaryDecimalSeparator",
          methodArgs = {char.class}
        )
    })
    public void test_setMonetaryDecimalSeparatorC() {
        try {
            dfs.setMonetaryDecimalSeparator('#');
            assertEquals("Returned incorrect MonetaryDecimalSeparator symbol",
                    '#', dfs.getMonetaryDecimalSeparator());
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setNaN(java.lang.String)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setNaN",
          methodArgs = {java.lang.String.class}
        )
    })
    public void test_setNaNLjava_lang_String() {
        dfs.setNaN("NAN!!");
        assertEquals("Returned incorrect nan symbol", "NAN!!", dfs.getNaN());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setPatternSeparator(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setPatternSeparator",
          methodArgs = {char.class}
        )
    })
    public void test_setPatternSeparatorC() {
        dfs.setPatternSeparator('X');
        assertEquals("Returned incorrect PatternSeparator symbol", 'X', dfs
                .getPatternSeparator());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setPercent(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setPercent",
          methodArgs = {char.class}
        )
    })
    public void test_setPercentC() {
        dfs.setPercent('*');
        assertEquals("Returned incorrect Percent symbol", '*', dfs.getPercent());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setPerMill(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setPerMill",
          methodArgs = {char.class}
        )
    })
    public void test_setPerMillC() {
        dfs.setPerMill('#');
        assertEquals("Returned incorrect PerMill symbol", '#', dfs.getPerMill());
    }

    /**
     * @tests java.text.DecimalFormatSymbols#setZeroDigit(char)
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "setZeroDigit",
          methodArgs = {char.class}
        )
    })
    public void test_setZeroDigitC() {
        dfs.setZeroDigit('*');
        assertEquals("Set incorrect ZeroDigit symbol", '*', dfs.getZeroDigit());
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
        dfs = new DecimalFormatSymbols();
        dfsUS = new DecimalFormatSymbols(new Locale("en", "us"));
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
    }

    // Test serialization mechanism of DecimalFormatSymbols
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "Checks serialization mechanism.",
      targets = {
        @TestTarget(
          methodName = "!SerializationSelf",
          methodArgs = {}
        )
    })
    public void test_serialization() throws Exception {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.FRANCE);
        Currency currency = symbols.getCurrency();
        assertNotNull(currency);

        // serialize
        ByteArrayOutputStream byteOStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOStream = new ObjectOutputStream(byteOStream);
        objectOStream.writeObject(symbols);

        // and deserialize
        ObjectInputStream objectIStream = new ObjectInputStream(
                new ByteArrayInputStream(byteOStream.toByteArray()));
        DecimalFormatSymbols symbolsD = (DecimalFormatSymbols) objectIStream
                .readObject();

        // The associated currency will not persist
        currency = symbolsD.getCurrency();
        assertNotNull(currency);
    }

    // Use RI to write DecimalFormatSymbols out, use Harmony to read
    // DecimalFormatSymbols in. The read symbol will be equal with those
    // instantiated inside Harmony.

    // This assertion will not come into existence the other way around. This is
    // probably caused by different serialization mechanism used by RI and
    // Harmony.
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Make sure all fields have non default values.",
      targets = {
        @TestTarget(
          methodName = "!SerializationGolden",
          methodArgs = {}
        )
    })
    public void _test_RIHarmony_compatible() throws Exception {
        ObjectInputStream i = null;
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(
                    Locale.FRANCE);
            i = new ObjectInputStream(
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream(
                    "/serialization/java/text/DecimalFormatSymbols.ser"));
            DecimalFormatSymbols symbolsD = (DecimalFormatSymbols) i
                    .readObject();
            assertEquals(symbols, symbolsD);
        } catch(NullPointerException e) {
            assertNotNull("Failed to load /serialization/java/text/DecimalFormatSymbols.ser", i);
        } finally {
            try {
                if (i != null) {
                    i.close();
                }
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
