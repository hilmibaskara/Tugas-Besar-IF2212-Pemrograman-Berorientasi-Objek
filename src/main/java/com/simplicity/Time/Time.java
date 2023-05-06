package com.simplicity.Time;

public class Time {
    private int day, hour, minute, second;

    public Time(int day, int hour, int minute, int second) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time(int hour, int minute, int second) {
        this(0, hour, minute, second);
    }

    public Time(int minute, int second) {
        this(0, 0, minute, second);
    }

    public Time(long timeSec) {
        this((int) (timeSec / (24 * 3600)), (int) (timeSec / 3600 % 24), (int) (timeSec / 60 % 60), (int) (timeSec % 60));
    }

    public Time() {
        this(0, 0, 0, 0);
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String toString() {
        return "Day: " + day + " Hour: " + hour + " Minute: " + minute + " Second: " + second;
    }

    public String displayTime() {
        return day + " day, " + hour + " hour, " + minute + " minute, " + second + " second";
    }

    public String displayHMS() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public int convertToSecond() {
        return day * 24 * 60 * 60 + hour * 60 * 60 + minute * 60 + second;
    }

    public Time addTime(Time time) {
        return new Time(this.convertToSecond() + time.convertToSecond());
    }
    
    public String remainingTime() {
        int remaining = 720 - (hour * 60 + minute) * 60 + second;
        return "Remaining time: " + (remaining / 60) + " minute(s) and " + (remaining % 60) + " second(s) in the day.";
    }
}
