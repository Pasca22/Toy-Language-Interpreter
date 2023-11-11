package Controller;

import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Structures.MyIStack;
import Model.Structures.ProgramState;
import Repository.IRepository;

public class Controller {

    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void addProgramState(ProgramState state) {
        this.repository.addProgramState(state);
    }

    public void oneStep(ProgramState state) throws Exception {
        MyIStack<IStatement> stack = state.getExeStack();

        if(stack.isEmpty()) {
            throw new MyException("Program state stack is empty");
        }
        IStatement createdStatement = stack.pop();
        createdStatement.execute(state);
    }

    public void allSteps() throws Exception {
        ProgramState programState;
            programState = repository.getCurrentProgramState();
            repository.logProgramStateExec();

        while (!programState.getExeStack().isEmpty()) {
            oneStep(programState);
            repository.logProgramStateExec();

        }
    }
}
