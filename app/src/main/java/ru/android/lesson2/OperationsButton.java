package ru.android.lesson2;

public class OperationsButton {
    private final char DIVIDE = '÷';
    private final char MULTIPLY = '*';
    private final char MINUS = '-';
    private final char PLUS = '+';
    private final char PERCENT = '%';
    private final char COMMA = '.';
    private final char EQUAL = '=';

    public OperationsButton() {
    }

    public char getEQUAL() {
        return EQUAL;
    }

    public char getCOMMA() {
        return COMMA;
    }

    public char getDIVIDE() {
        return DIVIDE;
    }

    public char getMULTIPLY() {
        return MULTIPLY;
    }

    public char getMINUS() {
        return MINUS;
    }

    public char getPLUS() {
        return PLUS;
    }

    public char getPERCENT() {
        return PERCENT;
    }
}
