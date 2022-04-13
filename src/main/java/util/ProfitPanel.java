package util;

import model.Profit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfitPanel extends JPanel {
    List<Profit> profitList;
    static int MAX_X = 500;
    static int MAX_Y = 240;

    public ProfitPanel(){
        //setBorder(BorderFactory.createLineBorder(Color.black));
    }
    public ProfitPanel(List<Profit> profitList){
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
        g.drawLine(0,200,480,200); // Horizontal axis
        g.drawLine(53,0,53,240); // Vertical axis

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
        g.drawString(String.valueOf(Math.round(min/1000))+"k",5,MAX_Y-40);
        g.drawString(String.valueOf(Math.round(max)/1000)+"k",5,MAX_Y-230);
        int x=60;
        int y=230;
        int i=0;
        int spacerX = (MAX_X - 70) / profitList.size();
        Profit prevProfit = null;
        for(Profit p: profitList){
            g.drawString(p.getShortPeriodAsString(),x,y);
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
