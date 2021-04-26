package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
  private ArrayList<String> calcHistory = new ArrayList<String>();
  private int lastResult = 0;
  private static final String PLUS_SIGN = "+";
  private static final String MINUS_SIGN = "-";
  private static final String ZERO_STR = "0";
  private String lastSign = PLUS_SIGN;

  private String getLastInput() {
    if (calcHistory.isEmpty()) {
      return ZERO_STR;
    }
    return calcHistory.get(calcHistory.size() - 1);
  }

  private static boolean isSign(String input) {
    return input.equals(PLUS_SIGN) || input.equals(MINUS_SIGN);
  }

  @Override
  public String output() {
    if (calcHistory.isEmpty()) {
      return ZERO_STR;
    }
    StringBuilder res = new StringBuilder();
    for (String s : calcHistory)
    {
      res.append(s);
    }
    if (isSign(calcHistory.get(0))) {
      return ZERO_STR + res.toString();
    }
    return res.toString();
  }

  @Override
  public void insertDigit(int digit) {
    // todo: insert a digit
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException();
    }
    if (isSign(getLastInput())) {
      calcHistory.add(String.valueOf(digit));
    }
    else {
      if (calcHistory.isEmpty()) {
        calcHistory.add(String.valueOf(digit));
      }
      else {
        calcHistory.set(calcHistory.size() - 1, getLastInput() + digit);
      }
    }

  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
    calcHistory.add(PLUS_SIGN);
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
    calcHistory.add(MINUS_SIGN);
    lastSign = MINUS_SIGN;
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    for (String input: calcHistory) {
      if (!isSign(input)) {
        int number = Integer.parseInt(input);
        if (lastSign.equals(MINUS_SIGN)) {
          lastResult -= number;
          lastSign = PLUS_SIGN;
        }
        else {
          lastResult += number;
        }
      }
      else {
        lastSign = input;
      }
    }
    lastSign = PLUS_SIGN;
    calcHistory.clear();
    calcHistory.add(String.valueOf(lastResult));

  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if (calcHistory.isEmpty()) {
      return;
    }
    calcHistory.remove(calcHistory.size() - 1);
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    calcHistory.clear();
    lastSign = PLUS_SIGN;
    lastResult = 0;
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    state.calcHistory = new ArrayList<String>(this.calcHistory);
    state.lastResult = this.lastResult;
    state.lastSign = this.lastSign;
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    this.calcHistory = casted.calcHistory;
    this.lastResult = casted.lastResult;
    this.lastSign = casted.lastSign;
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    private ArrayList<String> calcHistory = new ArrayList<String>();
    private int lastResult = 0;
    private String lastSign = PLUS_SIGN;
  }
}
