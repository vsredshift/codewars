package com.vsredshift.main.kyu5;

public class HumanReadableTime {
    public static void main(String[] args) {
        System.out.println(makeReadable(5));
        System.out.println(makeReadableStringFormat(86399));

    }

    //--------------------------------------------------------------//
    //          My Submitted Solution                               //
    //--------------------------------------------------------------//
    public static String makeReadable(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = (seconds % 3600) % 60;

        return (hours < 10 ? "0" + hours : hours) + ":" +
                (minutes < 10 ? "0" + minutes : minutes ) + ":" +
                (secs < 10 ? "0" + secs : secs);
    }

    //--------------------------------------------------------------//
    //          Alternative Solutions                               //
    //--------------------------------------------------------------//

    public static String makeReadableStringFormat(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds / 60) % 60, seconds % 60);
    }

}
