package co.istad.cdc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSegment {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("CODE")
    private String code;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("STATUS")
    private String status;
}
