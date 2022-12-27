package codetoon.system;

import codetoon.main.Main;
import codetoon.map.Loser;
import codetoon.map.Winner;

import java.awt.*;
import java.util.ArrayList;

public class CodeToon{
    public static final String GAME_VERSION = "Beta.0.6.1";
    public static boolean isGameStart = false;
    public static boolean DEBUG = false;
    public static int MEMORY_SIZE = 5;
    public static final int INSIDE_METHODS = 192010;

    public static final int HOST_MAP = 1101;

    public static final Graphics GRAPHICS = Main.getMainGraphics();
    public static final int PARCENT_ARGUMENT = 1098;
    public static final int INFINITY = -100000;

    public static void gameStart(){
        isGameStart = true;
        Thread t = new Thread(CodeToon::gameObserver);
        t.start();
    }
    public static void gameObserver(){
        while (isGameStart && !DEBUG){
            ArrayList<Memory> om = Memories.opponentMemory;
            ArrayList<Memory> o = Memories.memory;
            if(isAllHacked(om)){
                isGameStart = false;
                Main.getInstance().run(new Winner());
            }
            if(isAllHacked(o)){
                isGameStart = false;
                Main.getInstance().run(new Loser());
            }
        }
    }
    private static boolean isAllHacked(ArrayList<Memory> m){
        for(int i = 0; i < m.size(); i ++){
            if(m.get(i).getStates() != EnumMemoryStates.HACKED){
                return false;
            }
        }
        return true;
    }
}
