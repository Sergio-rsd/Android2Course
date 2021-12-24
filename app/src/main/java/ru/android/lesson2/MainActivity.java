package ru.android.lesson2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private NumbersButton numbersButton;
    private OperationsButton operationsButton;
    private ClearButton clearButton;
    private DeleteSign deleteOneChar; //
    private MemoryButton memoryButton;
    private TextView expression;
    private TextView memoryInfo;
    private boolean checkCalculate = false;

    private StringBuilder mainWindow;
    private StringBuilder memoryWindow;
    private StringBuilder memoryNumber;

    public static final String RESULT = "RESULT";
    private Result resultText = new Result();
//    private Calculate calculate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersButton = new NumbersButton();
        operationsButton = new OperationsButton();
        clearButton = new ClearButton();
        deleteOneChar = new DeleteSign();
        memoryButton = new MemoryButton();
        // хранить
        mainWindow = new StringBuilder();
        memoryWindow = new StringBuilder();
        memoryNumber = new StringBuilder();

        expression = findViewById(R.id.place_result);
        memoryInfo = findViewById(R.id.place_memory);
        expression.setText("0");
        mainWindow.append("0");
        memoryNumber.append("0");

        if (savedInstanceState != null && savedInstanceState.containsKey(RESULT)) {
            resultText = savedInstanceState.getParcelable(RESULT);
            expression.setText(resultText.getResultWindow());
            memoryInfo.setText(resultText.getMemWindow());
            memoryNumber.replace(0, memoryNumber.length(), resultText.getMemNumber());
            checkCalculate = resultText.isCheckResult();
//            Toast.makeText(
//                    MainActivity.this,
////                    expression.getText(),
////                    resultText.getResultWindow(),
//                    (String) ("Info: " + resultText.isCheckResult()),
//                    Toast.LENGTH_LONG
//            ).show();
        }
        initView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RESULT, resultText);
    }

    private void initView() {
        initOperationsButton();
        initNumberButton();
        initClearButton();
        initDeleteSignButton();
        initMemoryButton();
    }

    // TODO обработка memory
    // button of memory operations
    private void initMemoryButton() {
        MaterialButton buttonMemoryPlus = findViewById(R.id.button_m_plus);
        buttonMemoryPlus.setOnClickListener(buttonMemoryPlusClickListener);

        MaterialButton buttonMemoryMinus = findViewById(R.id.button_m_minus);
        buttonMemoryMinus.setOnClickListener(buttonMemoryMinusClickListener);

        MaterialButton buttonMemoryClear = findViewById(R.id.button_mc);
        buttonMemoryClear.setOnClickListener(buttonMemoryClearClickListener);

        MaterialButton buttonMemoryRead = findViewById(R.id.button_mr);
        buttonMemoryRead.setOnClickListener(buttonMemoryReadClickListener);

        MaterialButton buttonMemorySave = findViewById(R.id.button_ms);
        buttonMemorySave.setOnClickListener(buttonMemorySaveClickListener);

        MaterialButton buttonPercent = findViewById(R.id.button_percent);
        buttonPercent.setOnClickListener(buttonMemoryMultiplyListener);
    }

    public View.OnClickListener buttonMemoryPlusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setMemoryInTextView(memoryInfo, memoryButton.getMEM_PLUS());
        }
    };
    public View.OnClickListener buttonMemoryMinusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setMemoryInTextView(memoryInfo, memoryButton.getMEM_MINUS());
        }
    };
    public View.OnClickListener buttonMemoryClearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setMemoryInTextView(memoryInfo, memoryButton.getMEM_CLEAR());
        }
    };
    public View.OnClickListener buttonMemoryReadClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setMemoryInTextView(memoryInfo, memoryButton.getMEM_READ());
        }
    };
    public View.OnClickListener buttonMemorySaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setMemoryInTextView(memoryInfo, memoryButton.getMEM_SAVE());
        }
    };
    public View.OnClickListener buttonMemoryMultiplyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setMemoryInTextView(memoryInfo, memoryButton.getMEM_MULTIPLY());
        }
    };

    @SuppressLint("SetTextI18n")
    private void setMemoryInTextView(TextView expression, String number) {
        if (number.equals(memoryButton.getMEM_CLEAR())) {
            expression.setText("");
            memoryWindow.replace(0, memoryWindow.length(), "");
            memoryNumber.replace(0, memoryNumber.length(), "0");
        } else {
            // TODO обработка кнопок памяти
            if (number.equals(memoryButton.getMEM_READ())) {
                if (this.expression.getText().equals("0") || checkCalculate) {
                    this.expression.setText(memoryNumber);
                    mainWindow.replace(0, mainWindow.length(), mainWindow.toString() + memoryNumber);
                    resultText.setResultWindow(mainWindow.toString());
                }
                if (!Character.isDigit(this.expression.getText().charAt(this.expression.getText().length() - 1))) {
                    this.expression.setText(this.expression.getText() + String.format(Locale.getDefault(), "%s", memoryNumber));
                    mainWindow.replace(0, mainWindow.length(), (String) this.expression.getText());
                    resultText.setResultWindow(mainWindow.toString());
//                    Toast.makeText(
//                            MainActivity.this,
////                    expression.getText(),
////                    resultText.getResultWindow(),
//                            ("Memory ADD to mainWin: " + (String) this.expression.getText()),
//                            Toast.LENGTH_LONG
//                    ).show();
                }
//                mainWindow.replace(0, mainWindow.length(), mainWindow.toString() + memoryNumber);

//                Toast.makeText(
//                        MainActivity.this,
////                    expression.getText(),
////                    resultText.getResultWindow(),
//                        ("Memory READ: " + memoryNumber),
//                        Toast.LENGTH_LONG
//                ).show();
            } else if (number.equals(memoryButton.getMEM_SAVE())) {
                memoryNumber.replace(0, memoryNumber.length(), new Calculate(mainWindow.toString()).getNumberCalc());
//                String aa = mainWindow.toString();
//                String aa = new Calculate(mainWindow.toString()).getNumberCalc();
//                memoryNumber.replace(0, memoryNumber.length(), mainWindow.toString());
//                setExpressionInTextView((TextView) this.expression.getText(), operationsButton.getEQUAL());
                this.expression.setText(memoryNumber);
                setExpressionInTextView(this.expression, operationsButton.getEQUAL());
                resultText.setMemNumber(memoryNumber.toString());
//                Toast.makeText(
//                        MainActivity.this,
//                        ("Memory SAVE:/ Основное окно READ: " + memoryNumber + " / " + this.expression.getText()),
//                        Toast.LENGTH_LONG
//                ).show();
            } else if (number.equals(memoryButton.getMEM_PLUS())) {
                setExpressionInTextView(this.expression, operationsButton.getEQUAL());
                memoryNumber.replace(0, memoryNumber.length(), new Calculate(memoryNumber.toString() + operationsButton.getPLUS() + mainWindow.toString()).getNumberCalc());
                resultText.setMemNumber(memoryNumber.toString());
//                Toast.makeText(
//                        MainActivity.this,
//                        ("Memory Calc: " + memoryNumber),
//                        Toast.LENGTH_LONG
//                ).show();

            } else if (number.equals(memoryButton.getMEM_MINUS())) {
                setExpressionInTextView(this.expression, operationsButton.getEQUAL());
                memoryNumber.replace(0, memoryNumber.length(), new Calculate(memoryNumber.toString() + operationsButton.getMINUS() + mainWindow.toString()).getNumberCalc());
                resultText.setMemNumber(memoryNumber.toString());

            } else if (number.equals(memoryButton.getMEM_MULTIPLY())) {
                setExpressionInTextView(this.expression, operationsButton.getEQUAL());
                memoryNumber.replace(0, memoryNumber.length(), new Calculate(memoryNumber.toString() + operationsButton.getMULTIPLY() + mainWindow.toString()).getNumberCalc());
                resultText.setMemNumber(memoryNumber.toString());
            }
// Завершение обработки кнопок памяти

            expression.setText(number);
            memoryWindow.replace(0, memoryWindow.length(), number);
        }
        resultText.setMemWindow(memoryWindow.toString());
    }

    // button of operation
    private void initOperationsButton() {
        MaterialButton buttonEqual = findViewById(R.id.button_equal);
        buttonEqual.setOnClickListener(buttonEqualClickListener);

        MaterialButton buttonMinus = findViewById(R.id.button_minus);
        buttonMinus.setOnClickListener(buttonMinusClickListener);

        MaterialButton buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(buttonPlusClickListener);

        MaterialButton buttonMultiply = findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(buttonMultiplyClickListener);

        MaterialButton buttonDivide = findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(buttonDivideClickListener);

        MaterialButton buttonComma = findViewById(R.id.button_comma);
        buttonComma.setOnClickListener(buttonCommaClickListener);
    }

    public View.OnClickListener buttonEqualClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, operationsButton.getEQUAL());
        }
    };
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


    // button of numbers
    private void initNumberButton() {
        MaterialButton button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(button0ClickListener);

        MaterialButton button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(button1ClickListener);

        MaterialButton button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(button2ClickListener);

        MaterialButton button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(button3ClickListener);

        MaterialButton button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(button4ClickListener);

        MaterialButton button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(button5ClickListener);

        MaterialButton button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(button6ClickListener);

        MaterialButton button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(button7ClickListener);

        MaterialButton button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(button8ClickListener);

        MaterialButton button9 = findViewById(R.id.button_9);
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

    // clear button
    private void initClearButton() {
        MaterialButton buttonClear = findViewById(R.id.button_m_clear);
        buttonClear.setOnClickListener(buttonClearClickListener);
    }

    public View.OnClickListener buttonClearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, clearButton.getCLEAR());
        }
    };

    // minus/plus button - no
    // replace ONE sign delete
    private void initDeleteSignButton() {
        MaterialButton buttonDeleteChar = findViewById(R.id.button_delete_char);
        buttonDeleteChar.setOnClickListener(buttonDeleteCharClickListener);
    }

    public View.OnClickListener buttonDeleteCharClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setExpressionInTextView(expression, deleteOneChar.getDELETE_CHAR());
        }
    };

    @SuppressLint("SetTextI18n")
    private void setExpressionInTextView(TextView expression, int number) {
//        Toast.makeText(
//                MainActivity.this,
//                (String) ("Info перед : " + resultText.isCheckResult()),
//                Toast.LENGTH_LONG
//        ).show();
        // проверка на начальный ноль.
        if ((expression.getText().toString().charAt(0) == '0' && expression.getText().length() == 1) || checkCalculate) {
            expression.setText("");

//            checkCalculate = false;
//            resultText.setCheckResult(checkCalculate);
        }
        if (checkCalculate) {
            expression.setText("");
//            Toast.makeText(
//                    MainActivity.this,
//                    ("Текст после поворота: " + expression.getText()),
////                    resultText.getResultWindow(),
////                    (String) ("Info: " + resultText.isCheckResult()),
//                    Toast.LENGTH_LONG
//            ).show();
        }
        checkCalculate = false;
        resultText.setCheckResult(checkCalculate);
//        Toast.makeText(
//                MainActivity.this,
//                ("Текст: " + expression.getText()),
////                    resultText.getResultWindow(),
////                    (String) ("Info: " + resultText.isCheckResult()),
//                Toast.LENGTH_LONG
//        ).show();
        expression.setText(expression.getText() + String.format(Locale.getDefault(), "%d", number));
        mainWindow.replace(0, mainWindow.length(), (String) expression.getText());
        resultText.setResultWindow(mainWindow.toString());
//        Toast.makeText(
//                MainActivity.this,
//                mainWindow,
//                Toast.LENGTH_LONG
//        ).show();
//
    }

    @SuppressLint("SetTextI18n")
    private void setExpressionInTextView(TextView expression, char number) {
//        expression.setText(expression.getText() + String.format(Locale.getDefault(), "%c", number));
        if (number == clearButton.getCLEAR()) {
            expression.setText("0");
            checkCalculate = false;
            resultText.setCheckResult(checkCalculate);
            mainWindow.replace(0, mainWindow.length(), "0");
        } else if (number == deleteOneChar.getDELETE_CHAR()) {
            if (expression.getText().length() == 0) {
                expression.setText("0");
                checkCalculate = false;
                resultText.setCheckResult(checkCalculate);
                mainWindow.replace(0, mainWindow.length(), "0");
            } else {
                expression.setText((String) ((String) expression.getText()).substring(0, expression.getText().length() - 1));
                if (expression.getText().length() == 0) {
                    expression.setText("0");
                    mainWindow.replace(0, mainWindow.length(), "0");
                }
                checkCalculate = false;
                resultText.setCheckResult(checkCalculate);
                mainWindow.replace(0, mainWindow.length(), (String) expression.getText());
            }
        } else if (number == operationsButton.getEQUAL()) {
            expression.setText(new Calculate((String) expression.getText()).getNumberCalc());
            checkCalculate = true;
            resultText.setCheckResult(checkCalculate);
            mainWindow.replace(0, mainWindow.length(), (String) expression.getText());
        } else {
//            expression.setText(new StringBuilder().append(expression.getText()).append(String.format(Locale.getDefault(), "%c", number)).toString());
//            if (number == expression.getText().charAt(expression.getText().length()-1)) {
            if (!Character.isDigit(expression.getText().charAt(expression.getText().length() - 1))) {
//                expression.setText((String) ((String) expression.getText()).substring(0, expression.getText().length() - 1));
                expression.setText(((String) expression.getText()).substring(0, expression.getText().length() - 1));
            }
            expression.setText(expression.getText() + String.format(Locale.getDefault(), "%c", number));
            checkCalculate = false;
            resultText.setCheckResult(checkCalculate);
            mainWindow.replace(0, mainWindow.length(), (String) expression.getText());
        }
        resultText.setResultWindow(mainWindow.toString());
    }

}