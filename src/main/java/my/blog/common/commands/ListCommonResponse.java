package my.blog.common.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pzpl5764 on 10/26/2014.
 */
@NoArgsConstructor
public class ListCommonResponse<DTO> extends CommonResponseCommand<List> {

    public ListCommonResponse(@JsonProperty("result") List<DTO> result) {
        super(result);
    }
}
