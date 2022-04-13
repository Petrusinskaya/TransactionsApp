package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Profit {
    Date period;
    Float profit;
    int x;
    int y;

    @Override
    public String toString() {
        return "Profit{" +
                "month=" + period +
                ", profit=" + profit +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float amount) {
        this.profit = amount;
    }

    public String getPeriodAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        return sdf.format(period);
    }

    public String getShortPeriodAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yy");
        return sdf.format(period);
    }

}
