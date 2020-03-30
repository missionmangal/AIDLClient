// RemoteAidlInterface.aidl
package com.dots.aidlserver;

// Declare any non-default types here with import statements

interface RemoteAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    String getName(String name);
    String setName(String name);
}
