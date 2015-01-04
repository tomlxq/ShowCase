/**
* Created by tom on 2014/12/17.
*/
package com.photograph.Controller;



import com.photograph.web.controller.HelloWorld;
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

