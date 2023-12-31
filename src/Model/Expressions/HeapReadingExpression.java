package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.IType;
import Model.Types.ReferenceType;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapReadingExpression implements Expression {

    Expression expression;

    public HeapReadingExpression(Expression e) {
        expression = e;
    }
    @Override
    public String toString() {
        return "rH(" + expression + ")";
    }
    @Override
    public IValue evaluation(MyIDictionary<String, IValue> table,  MyIHeap heap) throws Exception {

        IValue expressionValue = expression.evaluation(table, heap);

        if (!(expressionValue.getType() instanceof ReferenceType)) {
            throw new MyException("Expression not of type Reference");
        }

        ReferenceValue referenceValue = (ReferenceValue) expressionValue;

        if(!heap.isDefined(referenceValue.getAddress())) {
            throw new MyException("Address not in heap");
        }

        return heap.lookup(referenceValue.getAddress());
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws Exception {
        IType type = expression.typeCheck(typeEnv);
        if (type instanceof ReferenceType referenceType) {
            return referenceType.getInner();
        } else
            throw new MyException("the rH argument is not a Reference Type");
    }

    @Override
    public Expression deepCopy() {
        return new HeapReadingExpression(expression.deepCopy());
    }
}
