package com.verint.kata;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {
    private StringCalculator calculator;

    @Before
    public void setUp() {
        calculator = new StringCalculator();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void emptyStringShouldReturnZero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void oneNumberInStringShouldReturnItself() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void severalNumbersInStringSeperatedWithCommaShouldReturnTheirSum() {
        assertEquals(11, calculator.add("10,1"));
        assertEquals(212, calculator.add("199,1,1,11"));
    }
    
    @Test
    public void allowSpaceBetweenInput(){
        assertEquals(11, calculator.add(" 10 , 1 "));
        assertEquals(212, calculator.add(" 199 , 1 , 1, 11 "));
    }

    @Test
    public void allowMethodToHandleUnknownNumberOfNumbers() {
        Random randomGenerator = new Random();
        int inputSize = randomGenerator.nextInt(100);
        int sum = 0;
        int random;
        String inputNumber = "";
        for (int i = 0; i < inputSize; i++) {
            random = randomGenerator.nextInt(100);
            sum += random;
            inputNumber = inputNumber.concat(Integer.toString(random)).concat(",");
        }
        assertEquals(sum, calculator.add(inputNumber));
    }

    @Test
    public void allowTheAddMethodToHandleNewLineAsDelimeterInsteadOfComma() {
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(6, calculator.add("1\n2\n3"));
    }

    @Test
    public void allowCustomDelimeterCharacter() {
        assertEquals(11, calculator.add("//;\n1;2;3;5"));
    }
    
    @Test
    public void verifiesExceptionTypeAndMessage() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("negatives not allowed: [-1, -3]");
        calculator.add("-1,2,-3");
    }

    @Test
    public void numberBiggerThanThousandShouldBeIgnored() {
        assertEquals(1, calculator.add("1001,1"));
        assertEquals(1001, calculator.add("1000,1"));
    }
    
    @Test
    public void anyLengthOfDelimeterShouldBeSupported(){
        assertEquals(11, calculator.add("//[:)]\n 1 :) 2 :) 3 :) 5 "));
        assertEquals(11, calculator.add("//[;p]\n1;p2;p3;p5"));
    }
    
}
