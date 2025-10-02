package co.istad.cdc.model;

import co.istad.cdc.deserializer.AddressXmlDeserializer;
import co.istad.cdc.deserializer.ContactXmlDeserializer;
import co.istad.cdc.deserializer.KycXmlDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
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
    private Addresses addresses;

    @JsonProperty("CONTACT_XML")
    @JsonDeserialize(using = ContactXmlDeserializer.class)
    private Contacts contacts;

    @JsonProperty("KYC_XML")
    @JsonDeserialize(using = KycXmlDeserializer.class)
    private KYC kyc;
}
