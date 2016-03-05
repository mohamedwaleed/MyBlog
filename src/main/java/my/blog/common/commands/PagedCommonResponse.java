package my.blog.common.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.blog.common.PageViewModel;

/**
 * Created by pzpl5764 on 10/26/2014.
 */
@NoArgsConstructor
public class PagedCommonResponse<DTO> extends CommonResponseCommand<PageViewModel> {

    public PagedCommonResponse(@JsonProperty("result") PageViewModel<DTO> result) {
        super(result);
    }
}
