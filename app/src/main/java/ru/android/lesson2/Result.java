package ru.android.lesson2;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Result implements Parcelable {

    private String resultWindow;
    private String memWindow;
    private boolean checkResult;

    private String memNumber;

    public Result() {

    }

    protected Result(Parcel in) {
        resultWindow = in.readString();
        memWindow = in.readString();
        memNumber = in.readString();
        checkResult = in.readByte() != 0;
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resultWindow);
        dest.writeString(memWindow);
        dest.writeString(memNumber);
        dest.writeByte((byte) (checkResult ? 1 : 0));
    }

    public String getResultWindow() {
        return resultWindow;
    }

    public void setResultWindow(String resultWindow) {
        this.resultWindow = resultWindow;
    }

    public String getMemWindow() {
        return memWindow;
    }

    public void setMemWindow(String memWindow) {
        this.memWindow = memWindow;
    }

    public boolean isCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public String getMemNumber() {
        return memNumber;
    }

    public void setMemNumber(String memNumber) {
        this.memNumber = memNumber;
    }
}
