package util;

import model.Profit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MiniProfitPanel extends JPanel {
    List<Profit> profitList;
    static int MAX_X = 170;
    static int MAX_Y = 100;

    public MiniProfitPanel(){
        //setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public MiniProfitPanel(List<Profit> profitList){
        this();
        setProfitList(profitList);
    }

    public List<Profit> getProfitList() {
        return profitList;
    }

    public void setProfitList(List<Profit> profitList) {
        this.profitList = profitList;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0,80,160,80); // Horizontal axis
        g.drawLine(25,0,25,100); // Vertical axis

        // Searching for a minimum and maximum profit values
        Float min=0f;
        Float max=0f;
        try {
            for (int n = 0; n< profitList.size(); n++){
                Float profitN = profitList.get(n).getProfit();
                if (n==0) {
                    min = profitN;
                    max = profitN;
                }
                if(profitN<min){
                    min=profitN;
                }
                if(profitN>max){
                    max=profitN;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Putting months on the x-axis
        g.drawString(String.valueOf(Math.round(min/1000))+"k",0,MAX_Y-5);
        g.drawString(String.valueOf(Math.round(max)/1000)+"k",0,MAX_Y-90);
        int x=30;
        int y=93;
        int i=0;
        int spacerX = 0;
        if (profitList.size()>0){
            spacerX = ((MAX_X - 10) / profitList.size())-10;
        }
        Profit prevProfit = null;
        for(Profit p: profitList){
            g.drawString(p.getShortestPeriodAsString(),x,y);

            // Building the graph
            int yPos=Math.round(MAX_Y-(p.getProfit()*MAX_Y)/max);
            yPos = yPos+30; // Shifting the position 10 units down
            g.fillOval(x+5,yPos-2,4,4);

            p.setX(x);
            p.setY(yPos);

            if(prevProfit!=null){
                g.drawLine(prevProfit.getX()+5,prevProfit.getY(),p.getX()+5,p.getY());
            }
            prevProfit = p;
            x = x+spacerX;

        }

    }
}
