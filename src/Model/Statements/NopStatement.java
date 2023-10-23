package Model.Statements;

import Model.Structures.ProgramState;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return null;
    }
}