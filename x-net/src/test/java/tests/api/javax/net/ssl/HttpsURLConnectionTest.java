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


package tests.api.javax.net.ssl;

import dalvik.annotation.TestTargetClass;
import dalvik.annotation.TestInfo;
import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTarget;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

import org.apache.harmony.security.tests.support.cert.TestUtils;

import junit.framework.TestCase;



/**
 * Tests for <code>HttpsURLConnection</code> class constructors and methods.
 * 
 */
@TestTargetClass(HttpsURLConnection.class) 
public class HttpsURLConnectionTest extends TestCase {
    
    /**
     * @tests javax.net.ssl.HttpsURLConnection#HttpsURLConnection(java_net_URL) 
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "HttpsURLConnection",
          methodArgs = {URL.class}
        )
    })
    public final void test_Constructor() {
        try {
            new MyHttpsURLConnection(new URL("https://www.fortify.net"));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
        try {
            new MyHttpsURLConnection(null);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#getDefaultHostnameVerifier() 
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getDefaultHostnameVerifier",
          methodArgs = {}
        )
    })
    public final void test_getDefaultHostnameVerifier() {
        HostnameVerifier verifyer =
            HttpsURLConnection.getDefaultHostnameVerifier();
        assertNotNull("Default hostname verifyer is null", verifyer);
    }
    
    /**
     * @tests javax.net.ssl.HttpsURLConnection#getDefaultSSLSocketFactory() 
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getDefaultSSLSocketFactory",
          methodArgs = {}
        )
    })
    public final void test_getDefaultSSLSocketFactory() {
        SSLSocketFactory sf = HttpsURLConnection.getDefaultSSLSocketFactory();
        if (!sf.equals(SSLSocketFactory.getDefault())) {
            fail("incorrect DefaultSSLSocketFactory");
        }
    }
    
    /**
     * @tests javax.net.ssl.HttpsURLConnection#getHostnameVerifier()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getHostnameVerifier",
          methodArgs = {}
        )
    })
    public final void test_getHostnameVerifier()
        throws Exception {
        HttpsURLConnection con = new MyHttpsURLConnection(
                new URL("https://www.fortify.net"));
        HostnameVerifier verifyer = con.getHostnameVerifier();
        assertNotNull("Hostname verifyer is null", verifyer);
        assertEquals("Incorrect value of hostname verirfyer", 
                HttpsURLConnection.getDefaultHostnameVerifier(), verifyer);
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#getLocalPrincipal()
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "IllegalStateException checking missed",
      targets = {
        @TestTarget(
          methodName = "getLocalPrincipal",
          methodArgs = {}
        )
    })
    public final void test_getLocalPrincipal() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        assertNotNull("Local principal is null", con.getLocalPrincipal());
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#getPeerPrincipal()
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "IllegalStateException checking missed",
      targets = {
        @TestTarget(
          methodName = "getPeerPrincipal",
          methodArgs = {}
        )
    })
    public final void test_getPeerPrincipal() throws Exception {
        HttpsURLConnection con = new MyHttpsURLConnection(
                new URL("https://www.fortify.net"));
        try {
            Principal p = con.getPeerPrincipal();
            assertNotNull("Principal is null", p);
        } catch (SSLPeerUnverifiedException e) {
            fail("Unexpected SSLPeerUnverifiedException " + e.toString());
        }
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#getSSLSocketFactory()
     */
    @TestInfo(
      level = TestLevel.COMPLETE,
      purpose = "",
      targets = {
        @TestTarget(
          methodName = "getSSLSocketFactory",
          methodArgs = {}
        )
    })
    public final void test_getSSLSocketFactory() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        SSLSocketFactory sf = con.getSSLSocketFactory();
        if (!sf.equals(SSLSocketFactory.getDefault())) {
            fail("incorrect DefaultSSLSocketFactory");
        }
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#setDefaultHostnameVerifier()
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Not null parameter checking missed",
      targets = {
        @TestTarget(
          methodName = "setDefaultHostnameVerifier",
          methodArgs = {HostnameVerifier.class}
        )
    })
    public final void test_setDefaultHostnameVerifier() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#setHostnameVerifier()
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Not null parameter checking missed",
      targets = {
        @TestTarget(
          methodName = "setHostnameVerifier",
          methodArgs = {HostnameVerifier.class}
        )
    })
    public final void test_setHostnameVerifier() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        try {
            con.setHostnameVerifier(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#setDefaultSSLSocketFactory()
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Not null checking missed",
      targets = {
        @TestTarget(
          methodName = "setDefaultSSLSocketFactory",
          methodArgs = {SSLSocketFactory.class}
        )
    })
    public final void test_setDefaultSSLSocketFactory() {
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.net.ssl.HttpsURLConnection#setSSLSocketFactory()
     */
    @TestInfo(
      level = TestLevel.PARTIAL,
      purpose = "Not null checking missed",
      targets = {
        @TestTarget(
          methodName = "setSSLSocketFactory",
          methodArgs = {SSLSocketFactory.class}
        )
    })
    public final void test_setSSLSocketFactory() {
        HttpsURLConnection con = new MyHttpsURLConnection(null);
        try {
            con.setSSLSocketFactory(null);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
}

class MyHttpsURLConnection extends HttpsURLConnection {

    public MyHttpsURLConnection(URL url) {
        super(url);
    }

    /*
     * @see javax.net.ssl.HttpsURLConnection#getCipherSuite()
     */
    public String getCipherSuite() {
        return null;
    }

    /*
     * @see javax.net.ssl.HttpsURLConnection#getLocalCertificates()
     */
    public Certificate[] getLocalCertificates() {
        Certificate cert = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            byte[] barr = TestUtils.getX509Certificate_v1();
            ByteArrayInputStream bis = new ByteArrayInputStream(barr);
            cert = cf.generateCertificate(bis);
        } catch (CertificateException se) {
            cert = null;
        }
        return cert == null ? null : new Certificate[]{cert};
    }

    /*
     * @see javax.net.ssl.HttpsURLConnection#getServerCertificates()
     */
    public Certificate[] getServerCertificates()
            throws SSLPeerUnverifiedException {
        Certificate cert = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            byte[] barr = TestUtils.getX509Certificate_v3();
            ByteArrayInputStream bis = new ByteArrayInputStream(barr);
            cert = cf.generateCertificate(bis);
        } catch (CertificateException se) {
            cert = null;
        }
        return cert == null ? null : new Certificate[]{cert};
    }

    /*
     * @see java.net.HttpURLConnection#disconnect()
     */
    public void disconnect() {
    }

    /*
     * @see java.net.HttpURLConnection#usingProxy()
     */
    public boolean usingProxy() {
        return false;
    }

    /*
     * @see java.net.URLConnection#connect()
     */
    public void connect() throws IOException {
    }

}

