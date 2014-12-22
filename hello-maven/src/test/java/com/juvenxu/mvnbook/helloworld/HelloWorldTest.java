/**
* Created by tom on 2014/12/17.
*/
package com.juvenxu.mvnbook.helloworld;



import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HelloWorldTest
{
   @Test
   public void testSayHello()
   {
        HelloWorld helloWorld = new HelloWorld();

        String result = helloWorld.sayHello();

        assertEquals( "Hello Maven", result );
    }
}

