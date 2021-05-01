package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-"; // TODO: decide the expected output when having a single minus
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // checking on empty input
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
    // checking on sign input
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
    // checking delete after output
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.output();
    calculatorUnderTest.deleteLast();
    assertEquals("5-", calculatorUnderTest.output());
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    // todo: implement test
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertEquals();
    assertEquals("2", calculatorUnderTest.output());
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // TODO: implement the test based on this method's name.
    //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
    // creating a state on 1st calc
    firstCalculator.insertDigit(1);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(1);
    Serializable savedState = firstCalculator.saveState();
    secondCalculator.loadState(savedState);
    assertEquals("1+1", secondCalculator.output());
  }

  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!

  @Test
  public void when_savingStateFromFirstCalculator_should_loadAndReplaceStateCorrectlyFromSecond(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    // creating a state on 1st calc
    firstCalculator.insertDigit(1);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(1);
    Serializable savedState = firstCalculator.saveState();
    // inserting "1+" to second calc
    secondCalculator.insertDigit(1);
    secondCalculator.insertPlus();
    secondCalculator.loadState(savedState);
    secondCalculator.insertEquals();
    assertEquals("2", secondCalculator.output());
  }

  @Test
  public void when_insertTwoSigns_shouldKeepOnlyOne(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // check minus
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    assertEquals("-1", calculatorUnderTest.output());
    calculatorUnderTest.clear();
    // check plus
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    assertEquals("1", calculatorUnderTest.output());
  }

  @Test
  public void when_insertingARowOfDigits_shouldAddDigitsToNumber(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("100", calculatorUnderTest.output());
  }

  @Test
  public void when_insertingARowOfZerosAndEquals_shouldIgnoreZeros(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.output();
    assertEquals("-1",calculatorUnderTest.output());
  }

  @Test
  public void when_receivesComplicatedInput_shouldCalculateCorrectly1(){
    // given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);
    assertEquals("5+17-125", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("-103", calculatorUnderTest.output());
  }

  @Test
  public void when_receivesComplicatedInput_shouldCalculateCorrectly2(){
    // given input "9<Clear>12<Clear>8-7=", expected output is "1"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    assertEquals("8-7", calculatorUnderTest.output());
    calculatorUnderTest.insertEquals();
    assertEquals("1", calculatorUnderTest.output());

  }

  @Test
  public void when_receivesComplicatedInput_shouldCalculateCorrectly3(){
    // given input "8-7=+4=-1=", expected output is "4"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("4",calculatorUnderTest.output());
  }

  @Test
  public void when_receivesComplicatedInput_shouldCalculateCorrectly4(){
    // given input "999-888-222=-333", expected output is "-111-333"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    assertEquals("-111-333",calculatorUnderTest.output());
  }

  @Test
  public void when_receivesComplicatedInput_shouldCalculateCorrectly5() {
    // given input "1-800-400-400", expected output is "-1599"
    // behaviour when result is a negative 4 digit number
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertEquals();
    // AIG
    assertEquals("-1599",calculatorUnderTest.output());
  }

  @Test
  public void when_receivesMultipleSign_shouldIgnoreRedundancies() {
    // given many sign inputs, all are ignored but the first one
    // if insertEquals, trim the signs and return "0"
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertEquals();
    assertEquals("0",calculatorUnderTest.output());
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    assertEquals("0+",calculatorUnderTest.output());
  }

}