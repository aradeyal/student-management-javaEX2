package org.example.stage1.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {
    private String status;
    private Object data;
    private Object error;
    private LocalDateTime timestamp = LocalDateTime.now();

    public StandardResponse(String status, Object data, Object error) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}
