package my.blog.common.exceptions.dtos;

/**
 * Created by mohamed on 4/6/16.
 */
public class ErrorResponseDTO {
    private String statusCode;
    private String errorClientMsg;
    private String errorDeveloperMsg;

    public ErrorResponseDTO(String statusCode, String errorClientMsg, String errorDeveloperMsg) {
        this.statusCode = statusCode;
        this.errorClientMsg = errorClientMsg;
        this.errorDeveloperMsg = errorDeveloperMsg;
    }
}
