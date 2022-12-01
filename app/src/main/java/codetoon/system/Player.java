package codetoon.system;

import java.awt.*;
import java.util.ArrayList;

import codetoon.method.*;
import codetoon.server.Server;
import codetoon.util.IsTick;
import codetoon.util.TickRegistory;
import org.jetbrains.annotations.NotNull;


public abstract class Player implements IsTick {
    ArrayList<MyMethod> method = new ArrayList<>();
    ArrayList<MyMethod> blackList = new ArrayList<>();
    protected TickRegistory<Player> ticker = getTick();

    protected int pass = 0;

    public Player(){
        blackList(blackList);
    }
    public void setRunMethod(ArrayList<MyMethod> m){
        m = removeBlackList(m);
        method = m;
    }
    public void run(){
        if(!method.isEmpty()) {
            for (int i = 0; i < method.size(); i++) {
                method.get(i).action(i);

            }
        }
    }
    public @NotNull ArrayList<MyMethod> removeBlackList(@NotNull ArrayList<MyMethod> m) {
        ArrayList<MyMethod> tmp = new ArrayList<>();
        for(int  i = 0; i < m.size(); i ++){
            boolean isNotBlackList = true;
            if(!blackList.isEmpty()){
                for(int c = 0; c < blackList.size(); c ++){
                    if(m.get(i).getClass() == blackList.get(c).getClass()){
                        System.out.println(m.get(i).getClass() + "はブラックリストに載っています。");
                        isNotBlackList = false;
                    }
                }
            }
            if(isNotBlackList){
                tmp.add(m.get(i));
            }
        }
        return tmp;
    }

    public abstract String getName();
    public abstract TickRegistory getTick();
    public abstract void endMethod(Console console, ArrayList<MyMethod> methods, StringBuilder source);
    protected abstract void blackList(ArrayList<MyMethod> m);
    public abstract String getID();

    public void setPassWord(int pass, int old_pass) {
        if(this.pass == old_pass){
            this.pass = pass;
            Server.server.sendMyCopy();
        }else{
            Message.addMessage(getName() + "のパスワードが違います。", Color.RED);
        }
    }
    @Override
    public boolean isClient() {
        return true;//特に意味はない
    }

    public abstract void connection(int password);

    public boolean getPass(int p) {
        return pass == p;
    }
}
