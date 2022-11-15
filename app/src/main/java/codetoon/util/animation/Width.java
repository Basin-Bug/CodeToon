package codetoon.util.animation;

import codetoon.main.Main;

import javax.net.ssl.SNIHostName;
import java.awt.*;

public class Width extends Decorate{
    int w;
    boolean isFirst = true;
    public Width(Animation.Properties properties, int w) {
        super(properties);
        this.w = w;
    }

    @Override
    public void displayAction(Graphics g) {
        StringBuilder builder = new StringBuilder().append(properties.getAnimation().getMsg());
        Animation a = properties.getAnimation();
        int c = 0;
        int row = 0;
        for(int i = 0; i < builder.length(); i ++){
            c += g.getFontMetrics().charWidth(builder.charAt(i));
            if(c >= w * Main.DW){
                builder.insert(i, '\n');
                row ++;
                c = 0;
            }
        }
        if(isFirst) {
            boolean isFirstMsg = true;
            for (String str : builder.toString().split("\n")) {
                if (isFirstMsg) {
                    properties.getAnimation().myProp.setAllPosition(properties.getAnimation().getX(),
                            properties.getAnimation().getY() - g.getFontMetrics().getHeight() / 4 * row);
                    properties.getAnimation().setMsg(str);
                    isFirstMsg = false;
                } else {
                    Animation.Properties p = new Animation.Properties().copy(properties);
                    Animation.create(g).draw(str, a.getX(), a.getY() - g.getFontMetrics().getHeight() / 4 * row, p);
                    properties.setChild(p);
                    row--;
                }
            }
        }
        //properties.getAnimation().setMsg(builder.toString());
    }
}
