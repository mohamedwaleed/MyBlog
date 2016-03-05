package my.blog.common.commands;


/**
 * Created by pzpl5764 on 10/22/2014.
 */

public abstract class AbstractRequestCommand {
    private OperationType operationType;
    private String operationName;

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
