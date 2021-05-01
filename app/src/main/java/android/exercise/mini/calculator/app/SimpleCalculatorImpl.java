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
    if (isSign(res.toString())) {
      return ZERO_STR + res.toString();
    }
    while (res.indexOf(PLUS_SIGN) == 0) {
      res.deleteCharAt(0);
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
      /*if (calcHistory.isEmpty()) {
        calcHistory.add(String.valueOf(digit));
      }
      else {
        calcHistory.set(calcHistory.size() - 1, getLastInput() + digit);
      }*/
      calcHistory.add(String.valueOf(digit));
    }

  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
    if (isSign(getLastInput())) {
      return;
    }
    calcHistory.add(PLUS_SIGN);
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
    if (isSign(getLastInput())) {
      return;
    }
    calcHistory.add(MINUS_SIGN);
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    lastResult = 0;
    StringBuilder number = new StringBuilder();
    number.append(ZERO_STR);
    for (int i = 0; i < calcHistory.size(); i++) {
      if (isSign(calcHistory.get(i))) {
        int intNumber = Integer.parseInt(number.toString());
        if (lastSign.equals(PLUS_SIGN)) {
          lastResult += intNumber;
        }
        else {
          lastResult -= intNumber;
        }
        number.setLength(0);
        lastSign = calcHistory.get(i);
      }
      else {
        number.append(calcHistory.get(i));
      }
    }
    if (number.length() != 0) {
      int intNumber = Integer.parseInt(number.toString());
      if (lastSign.equals(PLUS_SIGN)) {
        lastResult += intNumber;
      }
      else {
        lastResult -= intNumber;
      }
      number.setLength(0);
    }
    lastSign = PLUS_SIGN;
    calcHistory.clear();
    String res = String.valueOf(lastResult);
    if (lastResult < 0) {
      calcHistory.add(MINUS_SIGN);
      calcHistory.add(res.substring(1));
    }
    else {
      calcHistory.add(res);
    }

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
