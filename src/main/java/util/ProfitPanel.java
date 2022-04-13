package util;

import model.Profit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfitPanel extends JPanel {
    List<Profit> list;
    static int MAX_X = 160;
    static int MAX_Y = 240;

    public ProfitPanel(List<Profit> list){
        this.list = list;
        //setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0,200,160,200); // Horizontal axis
        g.drawLine(0,0,0,240); // Vertical axis

        // Searching for a minimum and maximum profit values
        Float min=0f;
        Float max=0f;
        try {
            List<Profit> p = DBUtil.getAllProfits();
            for (int n=0; n<p.size()-1; n++){
                Float profitN = p.get(n).getProfit();
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
        g.drawString(String.valueOf(Math.round(min/1000))+"k",5,MAX_Y-15);
        g.drawString(String.valueOf(Math.round(max)/1000)+"k",5,MAX_Y-220);
        int x=40;
        int y=20;
        int i=0;
        Profit prevProfit = null;
        for(Profit p:list){
            g.drawString(p.getShortPeriodAsString(),x,y);
            x = x+35;
            if(i%2==0){
                y=y-15;
            }
            else{
                y=y+15;
            }
            i++;

            // Building the graph
            int yPos=Math.round(MAX_Y-(p.getProfit()*MAX_Y)/max);
            yPos = yPos+10; // Shifting the position 10 units down
            g.fillOval(x-12,yPos-2,4,4);

            p.setX(x);
            p.setY(yPos);

            if(prevProfit!=null){
                g.drawLine(prevProfit.getX()-10,prevProfit.getY(),p.getX()-10,p.getY());
            }
            prevProfit = p;

        }

    }
}
