package co.istad.cdc.deserializer;

import co.istad.cdc.model.Address;
import co.istad.cdc.model.Contact;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.List;

public class ContactXmlDeserializer extends XmlStringDeserializer<List<Contact>> {
    public ContactXmlDeserializer() {
        super(new XmlMapper().getTypeFactory()
                .constructCollectionType(List.class, Contact.class));
    }
}
