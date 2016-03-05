package my.blog.common.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

/**
 * Created by KLSX4388 on 10/23/2014.
 */
@NoArgsConstructor
public class CommonResponseCommand<T> extends AbstractResponseCommand {
    private T result;

    @JsonCreator
    public CommonResponseCommand(@JsonProperty("result") T result) {
        this.setStatus(CommandStatus.SUCCESS);
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
