package codetoon.variable;

import java.util.ArrayList;
import java.util.HashMap;
import codetoon.system.*;
import codetoon.argument.*;
import codetoon.map.*;
import codetoon.main.*;
import org.jetbrains.annotations.NotNull;

public class MemoryVariable extends Variable<Memory> {

    Memory returnMemory;
    public MemoryVariable (){
    }
    @Override
    public Object newInstance() {
        return new MemoryVariable();
    }
    @Override
    public boolean setIsArray() {
        return true;
    }
    @Override
    public int getCount() {
        return 2;
    }
    
    @Override
    public String set(@NotNull HashMap<Integer, String> i) {
      //  System.out.println(i.get(0) + "!!!   " + i.get(1) + "!!!");
        int size = ((PazzleStage) Main.getInstance().getMap()).MEMORY_SIZE;
        int num = IntegerArgument.getInstance().indentification(i.get(0)) +
                    IntegerArgument.getInstance().indentification(i.get(1)) * size;
        if(i.get(2) != null) {
            if (i.get(2).equals("enemy")) {
                returnMemory = Memorys.opponentMemory.get(num);
            }
        }else {
            returnMemory = Memorys.memory.get(num);
        }

        return null;

    }
    @Override
    public Memory action() {

        return returnMemory;
    }
    
}
