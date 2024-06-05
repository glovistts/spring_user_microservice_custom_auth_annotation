package user.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ResultDTO {
    private int code = 500; 
    private String message = "خطای سامانه"; 
    private Map<String, Object> data = new HashMap<>();
    private boolean success = false;

    public ResultDTO() {}

    public ResultDTO(int code, String message, Map<String, Object> data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }
}