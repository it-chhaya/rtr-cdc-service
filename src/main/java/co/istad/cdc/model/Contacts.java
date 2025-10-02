package co.istad.cdc.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {
    @JacksonXmlElementWrapper(useWrapping = false)  // Important for list parsing!
    private List<Contact> contact;
}
