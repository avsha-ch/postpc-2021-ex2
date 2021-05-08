package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;
  // chose View instead of TextView because of AppFlowTest and buttonBackSpace
  private ArrayList<View> digitButtons = new ArrayList<>();
  private View buttonEquals;
  private View buttonPlus;
  private View buttonMinus;
  private View buttonClear;
  private View buttonBackSpace;
  private TextView textViewOutput;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    // find all views
    this.buttonEquals = findViewById(R.id.buttonEquals);
    this.buttonPlus = findViewById(R.id.buttonPlus);
    this.buttonMinus = findViewById(R.id.buttonMinus);
    this.buttonClear = findViewById(R.id.buttonClear);
    this.buttonBackSpace = findViewById(R.id.buttonBackSpace);
    this.textViewOutput = findViewById(R.id.textViewCalculatorOutput);
    this.button0 = findViewById(R.id.button0);
    digitButtons.add(this.button0);
    this.button1 = findViewById(R.id.button1);
    digitButtons.add(this.button1);
    this.button2 = findViewById(R.id.button2);
    digitButtons.add(this.button2);
    this.button3 = findViewById(R.id.button3);
    digitButtons.add(this.button3);
    this.button4 = findViewById(R.id.button4);
    digitButtons.add(this.button4);
    this.button5 = findViewById(R.id.button5);
    digitButtons.add(this.button5);
    this.button6 = findViewById(R.id.button6);
    digitButtons.add(this.button6);
    this.button7 = findViewById(R.id.button7);
    digitButtons.add(this.button7);
    this.button8 = findViewById(R.id.button8);
    digitButtons.add(this.button8);
    this.button9 = findViewById(R.id.button9);
    digitButtons.add(this.button9);

    // initial update main text-view based on calculator's output
    textViewOutput.setText(calculator.output());

    // set click listeners on all buttons to operate on the calculator and refresh main text-view
    buttonEquals.setOnClickListener(v -> {
      calculator.insertEquals();
      textViewOutput.setText(calculator.output());
    });

    buttonPlus.setOnClickListener(v -> {
      calculator.insertPlus();
      textViewOutput.setText(calculator.output());
    });

    buttonMinus.setOnClickListener(v -> {
      calculator.insertMinus();
      textViewOutput.setText(calculator.output());
    });

    buttonClear.setOnClickListener(v -> {
      calculator.clear();
      textViewOutput.setText(calculator.output());
    });

    buttonBackSpace.setOnClickListener(v -> {
      calculator.deleteLast();
      textViewOutput.setText(calculator.output());
    });
    // set all digit buttons listeners through loop
    for (int i = 0; i < digitButtons.size(); i++)
    {
      int finalI = i;
      digitButtons.get(finalI).setOnClickListener(v -> {
        calculator.insertDigit(finalI);
        textViewOutput.setText(calculator.output());
      });
    }


  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    // todo: save calculator state into the bundle
    outState.putSerializable("calculatorSavedState", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
    calculator.loadState(savedInstanceState.getSerializable("calculatorSavedState"));
    textViewOutput.setText(calculator.output());
  }
}