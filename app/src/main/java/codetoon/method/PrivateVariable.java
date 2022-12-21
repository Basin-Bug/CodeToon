package codetoon.method;

import codetoon.argument.ObjectArgument;
import codetoon.system.CodeToon;
import codetoon.system.Player;
import codetoon.variable.Variables;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PrivateVariable extends MyMethod{
    public String id;
    public String variable;
    Player host;
    @Override
    public Object newInstance() {
        return new PrivateVariable();
    }

    @Override
    public String set(@NotNull HashMap<Integer, String> map) {
        id = map.get(0);
        variable = map.get(1);
        host = (Player) ObjectArgument.getInstance().indentification(map.get(CodeToon.HOST_MAP));

        return null;
    }

    @Override
    public void action(int i) {
        HashMap<Integer, String> data = new HashMap<>();
        data.put(0, variable);
        Variables.VARIABLE.getThis(id).set(data);
    }
}