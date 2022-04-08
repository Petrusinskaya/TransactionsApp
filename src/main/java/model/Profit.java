package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Profit {
    Date period;
    Float profit;

    @Override
    public String toString() {
        return "Profit{" +
                "month=" + period +
                ", profit=" + profit +
                '}';
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

}
