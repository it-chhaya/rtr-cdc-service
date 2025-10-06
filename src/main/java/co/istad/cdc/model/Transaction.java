package co.istad.cdc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("ACCOUNT_ID")
    private String accountId;

    @JsonProperty("TYPE_CODE")
    private String typeCode;

    @JsonProperty("AMOUNT")
    private String amount;

    @JsonProperty("CURRENCY")
    private String currency;

    @JsonProperty("REMARK")
    private String remark;

    @JsonProperty("CREATED_AT")
    private Long createdAt;

    @JsonProperty("UPDATED_AT")
    private Long updatedAt;

    @JsonProperty("CREATED_BY")
    private String createdBy;

    @JsonProperty("UPDATED_BY")
    private String updatedBy;

}
