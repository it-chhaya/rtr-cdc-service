package co.istad.cdc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionType {
    @JsonProperty("TYPE_CODE")
    private String typeCode;
    @JsonProperty("DESCRIPTION")
    private String description;
}
