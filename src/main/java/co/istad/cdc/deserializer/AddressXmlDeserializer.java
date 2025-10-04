package co.istad.cdc.deserializer;

import co.istad.cdc.model.Address;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.List;

public class AddressXmlDeserializer extends XmlStringDeserializer<List<Address>> {
    public AddressXmlDeserializer() {
        super(new XmlMapper().getTypeFactory()
                .constructCollectionType(List.class, Address.class));
    }
}
