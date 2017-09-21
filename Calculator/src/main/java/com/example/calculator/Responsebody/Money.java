package com.example.calculator.Responsebody;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Money {
    private String buyPic;
    private String  closePri;
    private String code;
    private String color;
    private String currency;
    private String datatime;
    private String date;
    private String diffAmo;
    private String diffPer;
    private String highPic;
    private String lowPic;
    private String openPri;
    private String range;
    private String sellPic;
    private String yesPic;

    public String getBuyPic() {
        return buyPic;
    }

    public String getClosePri() {
        return closePri;
    }

    public String getCode() {
        return code;
    }

    public String getColor() {
        return color;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDatatime() {
        return datatime;
    }

    public String getDate() {
        return date;
    }

    public String getDiffAmo() {
        return diffAmo;
    }

    public String getDiffPer() {
        return diffPer;
    }

    public String getHighPic() {
        return highPic;
    }

    public String getLowPic() {
        return lowPic;
    }

    public String getOpenPri() {
        return openPri;
    }

    public String getRange() {
        return range;
    }

    public String getSellPic() {
        return sellPic;
    }

    public String getYesPic() {
        return yesPic;
    }

    @Override
    public String toString() {
        return "Money{" +
                "buyPic='" + buyPic + '\'' +
                ", closePri='" + closePri + '\'' +
                ", code='" + code + '\'' +
                ", color='" + color + '\'' +
                ", currency='" + currency + '\'' +
                ", datatime='" + datatime + '\'' +
                ", date='" + date + '\'' +
                ", diffAmo='" + diffAmo + '\'' +
                ", diffPer='" + diffPer + '\'' +
                ", highPic='" + highPic + '\'' +
                ", lowPic='" + lowPic + '\'' +
                ", openPri='" + openPri + '\'' +
                ", range='" + range + '\'' +
                ", sellPic='" + sellPic + '\'' +
                ", yesPic='" + yesPic + '\'' +
                '}';
    }
}
