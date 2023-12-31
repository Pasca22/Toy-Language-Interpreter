package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class IntValue implements IValue {
    int value;
    public IntValue(int v) { value =v; }
    public int getValue() {return value;}

    @Override
    public String toString(){return "" + value;};
    @Override
    public IType getType() { return new IntType();}

    @Override
    public IValue deepCopy() {
        return new IntValue(value);
    }
}
