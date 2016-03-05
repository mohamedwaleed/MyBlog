package my.blog.common.commands;

import lombok.Data;

/**
 * Created by pzpl5764 on 10/22/2014.
 */
public abstract class AbstractResponseCommand {
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
