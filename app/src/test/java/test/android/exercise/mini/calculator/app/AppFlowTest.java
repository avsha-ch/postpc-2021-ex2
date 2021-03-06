package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class AppFlowTest {

  private ActivityController<MainActivity> activityController;
  private MainActivity activityUnderTest;
  private View button0;
  private View button1;
  private View button2;
  private View button3;
  private View button4;
  private View button5;
  private View button6;
  private View button7;
  private View button8;
  private View button9;
  private View buttonBackspace;
  private View buttonClear;
  private View buttonPlus;
  private View buttonMinus;
  private View buttonEquals;
  private TextView textViewOutput;

  /**
   * initialize main activity with a real calculator
   */
  @Before
  public void setup() {
    activityController = Robolectric.buildActivity(MainActivity.class);
    activityUnderTest = activityController.get();
    activityController.create().start().resume();
    button0 = activityUnderTest.findViewById(R.id.button0);
    button1 = activityUnderTest.findViewById(R.id.button1);
    button2 = activityUnderTest.findViewById(R.id.button2);
    button3 = activityUnderTest.findViewById(R.id.button3);
    button4 = activityUnderTest.findViewById(R.id.button4);
    button5 = activityUnderTest.findViewById(R.id.button5);
    button6 = activityUnderTest.findViewById(R.id.button6);
    button7 = activityUnderTest.findViewById(R.id.button7);
    button8 = activityUnderTest.findViewById(R.id.button8);
    button9 = activityUnderTest.findViewById(R.id.button9);
    buttonBackspace = activityUnderTest.findViewById(R.id.buttonBackSpace);
    buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
    buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);
    buttonMinus = activityUnderTest.findViewById(R.id.buttonMinus);
    buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);
    textViewOutput = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
  }

  @Test
  public void flowTest1() {
    // run clicks on "13+5"
    for (View button : Arrays.asList(
            button1, button3, buttonPlus, button5
    )) {
      button.performClick();
    }

    assertEquals("13+5", textViewOutput.getText().toString());
  }


  @Test
  public void flowTest2() {
    // run clicks on "7+5<backspace>4="
    for (View button : Arrays.asList(
            button7, buttonPlus, button5, buttonBackspace, button4, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("11", textViewOutput.getText().toString());
  }

  // TODO: add at last 10 more flow tests

  @Test
  public void flowTest3() {
    // run clicks on "9<Clear>12<Clear>8-7="
    for (View button : Arrays.asList(
            button9, buttonClear, button1, button2, buttonClear,
            button8, buttonMinus, button7, buttonEquals
    )) {
      button.performClick();
    }

    assertEquals("1", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest4() {
    // run clicks on "5+7-13<DeleteLast>25"
    for (View button : Arrays.asList(
            button5, buttonPlus, button1, button7, buttonMinus, button1, button3,
            buttonBackspace, button2, button5
    )) {
      button.performClick();
    }
    assertEquals("5+17-125", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest5() {
    // run clicks on "8-7=+4=-1="
    for (View button : Arrays.asList(
            button8, buttonMinus, button7, buttonEquals, buttonPlus, button4,
            buttonEquals, buttonMinus, button1, buttonEquals
    )) {
      button.performClick();
    }
    assertEquals("4", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest6() {
    // run clicks on "999-888-222<clear>=-1" expected output is "0-1"
    for (View button : Arrays.asList(
            button9, button9, button9, buttonMinus,
            button8, button8, button8, buttonMinus,
            button2, button2, button2, buttonClear,
            buttonEquals, buttonMinus, button1
    )) {
      button.performClick();
    }
    assertEquals("0-1", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest7() {
    // run clicks on "999-888-222=-333"
    for (View button : Arrays.asList(
            button9, button9, button9, buttonMinus,
            button8, button8, button8, buttonMinus,
            button2, button2, button2, buttonEquals,
            buttonMinus, button3, button3, button3
    )) {
      button.performClick();
    }
    assertEquals("-111-333", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest8() {
    // given input "1-800-400-400=", expected output is "-1599"
    for (View button : Arrays.asList(
            button1, buttonMinus, button8, button0, button0,
            buttonMinus, button4, button0, button0, buttonMinus,
            button4, button0, button0, buttonEquals
    )) {
      button.performClick();
    }
    assertEquals("-1599", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest9() {
    // given many sign inputs, all are ignored but the first one
    // if insertEquals, trim the signs and return "0"
    for (View button : Arrays.asList(
            buttonEquals, buttonEquals, buttonEquals, buttonEquals,
            buttonMinus, buttonMinus, buttonMinus, buttonMinus,
            buttonPlus, buttonPlus, buttonPlus, buttonPlus,
            buttonBackspace, buttonPlus, buttonMinus, buttonEquals
    )) {
      button.performClick();
    }
    assertEquals("0", textViewOutput.getText().toString());
    for (View button : Arrays.asList(
            buttonPlus, buttonMinus, buttonPlus, buttonMinus
    )) {
      button.performClick();
    }
    assertEquals("0+", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest10() {
    // given operators between digits and then CLEAR,
    // make sure that there are no traces of previous input
    for (View button : Arrays.asList(
            button1, buttonPlus, button1, buttonEquals, buttonClear,
            button3, buttonMinus, button1, buttonEquals, buttonClear,
            button9, button9, buttonPlus, button1, buttonEquals
    )) {
      button.performClick();
    }
    assertEquals("100", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest11() {
    // given operators between digits and then CLEAR,
    // make sure that there are no traces of previous input
    for (View button : Arrays.asList(
            button1, buttonPlus, button1, buttonEquals, buttonClear,
            button3, buttonMinus, button1, buttonEquals, buttonClear,
            button9, button9, buttonPlus, button1, buttonEquals
    )) {
      button.performClick();
    }
    assertEquals("100", textViewOutput.getText().toString());
  }

  @Test
  public void flowTest12_dinaBarzilai() {
    // given input "<BackSpace>496351" , expected output is "496351"
    for (View button : Arrays.asList(
            buttonBackspace, button4, button9, button6, button3, button5, button1
    )) {
      button.performClick();
    }
    assertEquals("496351", textViewOutput.getText().toString());
  }

}