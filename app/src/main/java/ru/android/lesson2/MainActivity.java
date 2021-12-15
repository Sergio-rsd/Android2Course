package ru.android.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public class MainActivity extends AppCompatActivity {

    private NumbersButton numbersButton;
    private OperationsButton operationsButton;
    //    private EditText expression;
    private TextView expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        numbersButton = new NumbersButton();
        operationsButton = new OperationsButton();

        initView();
    }

    private void initView() {
        expression = findViewById(R.id.place_result);
        initOperationsButton();
        initNumberButton();
//        initButton7Click();
    }

    // button of operation

    private void initOperationsButton() {
        Button buttonMinus = findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(buttonMinusClickListener);

        Button buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(buttonPlusClickListener);

        Button buttonMultiply = findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(buttonMultiplyClickListener);

        Button buttonDivide = findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(buttonDivideClickListener);

        Button buttonComma = findViewById(R.id.button_comma);
        buttonComma.setOnClickListener(buttonCommaClickListener);

        Button buttonPercent = findViewById(R.id.button_percent);
        buttonPercent.setOnClickListener(buttonPercentClickListener);

    }

    public View.OnClickListener buttonMinusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getMINUS());
        }
    };

    public View.OnClickListener buttonPlusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getPLUS());
        }
    };
    public View.OnClickListener buttonMultiplyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getMULTIPLY());
        }
    };
    public View.OnClickListener buttonDivideClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getDIVIDE());
        }
    };
    public View.OnClickListener buttonCommaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getCOMMA());
        }
    };
    public View.OnClickListener buttonPercentClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getPERCENT());
        }
    };
    // button of numbers

    private void initNumberButton() {
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(button0ClickListener);

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(button1ClickListener);

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(button2ClickListener);

        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(button3ClickListener);

        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(button4ClickListener);

        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(button5ClickListener);

        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(button6ClickListener);

        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(button7ClickListener);

        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(button8ClickListener);

        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(button9ClickListener);
    }

    public View.OnClickListener button0ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_0());
        }
    };
    public View.OnClickListener button1ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_1());
        }
    };
    public View.OnClickListener button2ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_2());
        }
    };
    public View.OnClickListener button3ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_3());
        }
    };
    public View.OnClickListener button4ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_4());
        }
    };
    public View.OnClickListener button5ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_5());
        }
    };
    public View.OnClickListener button6ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_6());
        }
    };
    public View.OnClickListener button7ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_7());
        }
    };
    public View.OnClickListener button8ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_8());
        }
    };
    public View.OnClickListener button9ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, numbersButton.getNUMBER_9());
        }
    };

    private void setExpressionInTextView(TextView expression, int number) {
        expression.setText(new StringBuilder().append(expression.getText()).append(String.format(Locale.getDefault(), "%d", number)).toString());
    }

    private void setExpressionInTextView(TextView expression, char number) {
//        expression.setText(expression.getText() + String.format(Locale.getDefault(), "%c", number));
        expression.setText(new StringBuilder().append(expression.getText()).append(String.format(Locale.getDefault(), "%c", number)).toString());
    }

}