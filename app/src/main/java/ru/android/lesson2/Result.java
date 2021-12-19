package ru.android.lesson2;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {

    private String resultWindow;
    private String memWindow;

    public Result() {
        resultWindow = "";
        memWindow = "";
    }

    protected Result(Parcel in) {
        resultWindow = in.readString();
        memWindow = in.readString();
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
}
