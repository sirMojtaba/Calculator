package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String INSTANCE_KEY_TEXT_VIEW = "textView";
    public static final String INSTANCE_KEY_TEXT_NUMBER = "textNumber";
    private TextView mTextViewResult;
    private Button mButtonZero, mButtonOne, mButtonTwo, mButtonThree, mButtonFour, mButtonFive, mButtonSix, mButtonSeven, mButtonEight, mButtonNine, mButtonDot, mButtonDelete, mButtonEqual, mButtonPlus, mButtonMinus, mButtonMulti, mButtonDivide, mButtonReset;
    private DecimalFormat mDFormat = new DecimalFormat();
    private double mNumber1, mNumber2, mMemory;
    private String mSign = "";
    private String mTextNumber = "";
    private boolean mBooleanReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onConfiguration(savedInstanceState);
        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_KEY_TEXT_VIEW, mTextViewResult.getText().toString());
        outState.putString(INSTANCE_KEY_TEXT_NUMBER, mTextNumber);
    }

    private void onConfiguration(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mTextNumber = savedInstanceState.getString(INSTANCE_KEY_TEXT_NUMBER);
            mTextViewResult.setText(mTextNumber);
        }
    }

    private void init() {
        mButtonZero = findViewById(R.id.btn_zero);
        mButtonOne = findViewById(R.id.btn_one);
        mButtonTwo = findViewById(R.id.btn_two);
        mButtonThree = findViewById(R.id.btn_three);
        mButtonFour = findViewById(R.id.btn_four);
        mButtonFive = findViewById(R.id.btn_five);
        mButtonSix = findViewById(R.id.btn_six);
        mButtonSeven = findViewById(R.id.btn_seven);
        mButtonEight = findViewById(R.id.btn_eight);
        mButtonNine = findViewById(R.id.btn_nine);
        mButtonDot = findViewById(R.id.btn_dot);
        mButtonDelete = findViewById(R.id.btn_delete);
        mButtonReset = findViewById(R.id.reset);
        mButtonEqual = findViewById(R.id.btn_equal);
        mButtonPlus = findViewById(R.id.btn_plus);
        mButtonMinus = findViewById(R.id.btn_minus);
        mButtonMulti = findViewById(R.id.btn_multiply);
        mButtonDivide = findViewById(R.id.btn_divide);
        mTextViewResult = findViewById(R.id.text_view_result);
        mDFormat.applyPattern(getString(R.string.patern));
    }

    private boolean hasSign() {

        return !mBooleanReset
                && (mTextNumber.contains(getString(R.string.plus)) || mTextNumber.contains(getString(R.string.minus))
                || mTextNumber.contains(getString(R.string.multi)) || mTextNumber.contains(getString(R.string.divide)));
    }

    private void setText(String input) {


        if (mBooleanReset) {

            if ((input.charAt(0) < 48 || input.charAt(0) > 57))
                input = mDFormat.format(mMemory).concat(input);
            mBooleanReset = false;
        }
        if (mTextNumber.equals("0") && (input.charAt(0) < 48 || input.charAt(0) > 57))
            mTextNumber = "0";
        else if (mTextNumber.equals("0") && (input.charAt(0) >= 48 && input.charAt(0) <= 57)) {
            reset();
            mTextNumber = "";
        }
        mTextNumber += input;
        mTextViewResult.setText(mTextNumber);
    }

    private void getNumbers() {

        for (int i = 1; i < mTextNumber.length(); i++) {
            if ((mTextNumber.charAt(i) < 48 || mTextNumber.charAt(i) > 57) && (mTextNumber.charAt(i) != 46)) {
                mSign = String.valueOf(mTextNumber.charAt(i));
                mNumber1 = Double.parseDouble(mTextNumber.substring(0, i));
                mNumber2 = Double.parseDouble(mTextNumber.substring(i + 1));
                break;
            }
        }
    }

    private String calculateResult(double number1, String sign, double number2) {
        double result = 0;

        if (sign.equals(getString(R.string.plus)))
            result = number1 + number2;
        else if (sign.equals(getString(R.string.minus)))
            result = number1 - number2;
        else if (sign.equals(getString(R.string.multi)))
            result = number1 * number2;
        else if (sign.equals(getString(R.string.divide)))
            result = number1 / number2;

        mTextNumber = "";
        mNumber1 = 0;
        mNumber2 = 0;
        mSign = "";
        mMemory = result;
        return mDFormat.format(result);
    }

    private void printResult() {

        getNumbers();

        if (mSign.equals(getString(R.string.divide)) && mNumber2 == 0)
            mTextViewResult.setText(R.string.divide_by_zero);
        else
            mTextViewResult.setText(calculateResult(mNumber1, mSign, mNumber2));

        mBooleanReset = true;

    }

    private void reset() {
        mSign = "";
        mNumber1 = 0;
        mNumber2 = 0;
        mMemory = 0;
        mBooleanReset = false;
        mTextNumber = "0";
        mTextViewResult.setText("0");
        Toast.makeText(this, "All data cleared...", Toast.LENGTH_SHORT).show();
    }

    private void setListeners() {

        mButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("1");
                showToast();
            }
        });

        mButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("2");
                showToast();
            }
        });

        mButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("3");
                showToast();
            }
        });

        mButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("4");
                showToast();
            }
        });

        mButtonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("5");
                showToast();
            }
        });

        mButtonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("6");
                showToast();
            }
        });

        mButtonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("7");
                showToast();
            }
        });

        mButtonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("8");
                showToast();
            }
        });

        mButtonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("9");
                showToast();
            }
        });

        mButtonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText("0");
                showToast();
            }
        });

        mButtonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    if (!mTextNumber.contains(getString(R.string.dot)))
                        if (mTextNumber.length() == 0 || mTextNumber.equals("0") || mTextNumber.equals(""))
                            setText("0" + getString(R.string.dot));
                        else
                            setText(getString(R.string.dot));
                    else {
                        int dotIndex = mTextNumber.indexOf(getString(R.string.dot));
                        String newTextNumber = mTextNumber.substring(dotIndex + 1);
                        if ((newTextNumber.contains(getString(R.string.plus)) || newTextNumber.contains(getString(R.string.minus))
                                || newTextNumber.contains(getString(R.string.multi)) || newTextNumber.contains(getString(R.string.divide))) && !newTextNumber.contains(getString(R.string.dot)))
                            setText(getString(R.string.dot));
                    }
                    showToast();
                }
            }
        });

        mButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasSign())
                    setText(getString(R.string.plus));
                showToast();

            }
        });

        mButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    if (!hasSign())
                        setText(getString(R.string.minus));
                    showToast();
                }
            }
        });

        mButtonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (!hasSign())
                        setText(getString(R.string.divide));
                    showToast();
                }
            }
        });

        mButtonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (!hasSign())
                        setText(getString(R.string.multi));
                    showToast();
                }
            }
        });

        mButtonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (!mTextNumber.endsWith(getString(R.string.dot)) && !mTextNumber.endsWith(getString(R.string.plus))
                            && !mTextNumber.endsWith(getString(R.string.minus)) && !mTextNumber.endsWith(getString(R.string.multi)) && !mTextNumber.endsWith(getString(R.string.divide))) {
                        printResult();
                        String res = "Answer is: " + mTextViewResult.getText().toString();
                        Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (mTextNumber.length() > 1) {
                        String s = mTextNumber.substring(0, mTextNumber.length() - 1);
                        mTextNumber = "";
                        setText(s);
                    } else if (mTextNumber.length() == 1) {
                        reset();
                    }
                    showToast();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    reset();
                }
            }
        });

    }

    private void showToast() {
        Toast.makeText(this, mTextViewResult.getText(), Toast.LENGTH_SHORT).show();
    }
}
