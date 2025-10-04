package co.istad.cdc.model;

import co.istad.cdc.deserializer.AddressXmlDeserializer;
import co.istad.cdc.deserializer.ContactXmlDeserializer;
import co.istad.cdc.deserializer.KycXmlDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.avro.reflect.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("CUSTOMER_NUMBER")
    private String customerNumber;

    @JsonProperty("FIRST_NAME")
    private String firstName;

    @JsonProperty("LAST_NAME")
    private String lastName;

    @JsonProperty("EMAIL")
    private String email;

    @JsonProperty("ADDRESS_XML")
    @JsonDeserialize(using = AddressXmlDeserializer.class)
    @Nullable
    private List<Address> addresses;

    @JsonProperty("CONTACT_XML")
    @JsonDeserialize(using = ContactXmlDeserializer.class)
    private List<Contact> contacts;

    @JsonProperty("KYC_XML")
    @JsonDeserialize(using = KycXmlDeserializer.class)
    private List<Kyc> kyc;
}
