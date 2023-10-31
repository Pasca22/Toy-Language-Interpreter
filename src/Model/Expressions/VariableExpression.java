package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Values.IValue;

public class VariableExpression implements Expression {

    String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table) throws Exception {

        if (table.isDefined(id)) {
            return table.lookup(id);
        }
        throw new MyException("Variable " + id + " is not defined");

    }

    @Override
    public Expression deepCopy() {
        return new VariableExpression(id);
    }
}
