package co.istad.cdc.client.dto;

import co.istad.cdc.deserializer.AddressXmlDeserializer;
import co.istad.cdc.deserializer.ContactXmlDeserializer;
import co.istad.cdc.deserializer.KycXmlDeserializer;
import co.istad.cdc.model.Address;
import co.istad.cdc.model.Contact;
import co.istad.cdc.model.Kyc;
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
public class CustomerSync {

    private String id;
    private String customerNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String segmentId;
    private String status;
    private Long createdAt;
    private Long updatedAt;
    private String createdBy;
    private String updatedBy;
    private List<Address> addresses;
    private List<Contact> contacts;
    private List<Kyc> kyc;
}
