/*
 * 
 * Copyright (c) 2004 The Apache Software Foundation. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *  
 */
package org.apache.commons.math.analysis;

// commons-math
import org.apache.commons.math.MathException;

// junit
import junit.framework.TestCase;

/**
 * Tests the PolynomialFunction implementation of a UnivariateRealFunction.
 *
 * @version $Revision: 1.3 $
 * @author Matt Cliff <matt@mattcliff.com>
 */
public final class PolynomialFunctionTest extends TestCase {

    // all values are known precisely accept 15 digit precision error 
    final double error = 1.0e-15;

    /**
     * tests the value of a constant polynomial.
     *
     * <p>value of this is 2.5 everywhere.</p>
     */
    public void testConstants() throws MathException {
        double[] c = { 2.5 };
        UnivariateRealFunction f = new PolynomialFunction( c );

        // verify that we are equal to c[0] at several (nonsymmetric) places
        assertEquals( f.value( 0.0), c[0], error );
        assertEquals( f.value( -1.0), c[0], error );
        assertEquals( f.value( -123.5), c[0], error );
        assertEquals( f.value( 3.0), c[0], error );
        assertEquals( f.value( 456.89), c[0], error );
    }



    /**
     * tests the value of a linear polynomial.
     *
     * <p>This will test the function f(x) = 3*x - 1.5</p>
     * <p>This will have the values 
     *  <tt>f(0.0) = -1.5, f(-1.0) = -4.5, f(-2.5) = -9.0,
     *      f(0.5) = 0.0, f(1.5) = 3.0</tt> and <tt>f(3.0) = 7.5</tt>
     * </p>
     */
    public void testLinear() throws MathException {
        double[] c = { -1.5, 3.0 };
        UnivariateRealFunction f = new PolynomialFunction( c );

        // verify that we are equal to c[0] when x=0
        assertEquals( f.value( 0.0), c[0], error );

        // now check a few other places
        assertEquals( -4.5, f.value( -1.0), error );
        assertEquals( -9.0, f.value( -2.5), error );
        assertEquals( 0.0, f.value( 0.5), error );
        assertEquals( 3.0, f.value( 1.5), error );
        assertEquals( 7.5, f.value( 3.0), error );
    
    }


    /**
     * Tests a second order polynomial.
     * <p> This will test the function f(x) = 2x^2 - 3x -2 = (2x+1)(x-2)</p>
     *
     */
    public void testQuadratic() throws MathException {
        double[] c = { -2.0, -3.0, 2.0 };
        UnivariateRealFunction f = new PolynomialFunction( c );

        // verify that we are equal to c[0] when x=0
        assertEquals( f.value( 0.0), c[0], error );

        // now check a few other places
        assertEquals( 0.0, f.value( -0.5), error );
        assertEquals( 0.0, f.value( 2.0), error );
        assertEquals( -2.0, f.value( 1.5), error );
        assertEquals( 7.0, f.value( -1.5), error );
        assertEquals( 265.5312, f.value( 12.34), error );
    
    }    


    /** 
     * This will test the quintic function 
     *   f(x) = x^2(x-5)(x+3)(x-1) = x^5 - 3x^4 -13x^3 + 15x^2</p>
     *
     */
    public void testQuintic() throws MathException {
        double[] c = { 0.0, 0.0, 15.0, -13.0, -3.0, 1.0 };
        UnivariateRealFunction f = new PolynomialFunction( c );

        // verify that we are equal to c[0] when x=0
        assertEquals( f.value( 0.0), c[0], error );

        // now check a few other places
        assertEquals( 0.0, f.value( 5.0), error );
        assertEquals( 0.0, f.value( 1.0), error );
        assertEquals( 0.0, f.value( -3.0), error );
        assertEquals( 54.84375, f.value( -1.5), error );
        assertEquals( -8.06637, f.value( 1.3), error );
    
    }    


    /**
     * tests the derivative function by comparision
     *
     * <p>This will test the functions 
     * <tt>f(x) = x^3 - 2x^2 + 6x + 3, g(x) = 3x^2 - 4x + 6</tt>
     * and <tt>h(x) = 6x - 4</tt>
     */
    public void testDerivativeComparision() throws MathException {
        double[] f_coeff = { 3.0, 6.0, -2.0, 1.0 };
        double[] g_coeff = { 6.0, -4.0, 3.0 };
        double[] h_coeff = { -4.0, 6.0 };

        PolynomialFunction f = new PolynomialFunction( f_coeff );
        PolynomialFunction g = new PolynomialFunction( g_coeff );
        PolynomialFunction h = new PolynomialFunction( h_coeff );

        // compare f' = g
        assertEquals( f.firstDerivative(0.0), g.value(0.0), error );
        assertEquals( f.firstDerivative(1.0), g.value(1.0), error );
        assertEquals( f.firstDerivative(100.0), g.value(100.0), error );
        assertEquals( f.firstDerivative(4.1), g.value(4.1), error );
        assertEquals( f.firstDerivative(-3.25), g.value(-3.25), error );

        // compare g' = h


        // compare f'' = h
    }





}
