package co.istad.cdc.deserializer;

import co.istad.cdc.model.Addresses;

public class AddressXmlDeserializer extends XmlStringDeserializer<Addresses> {
    public AddressXmlDeserializer() {
        super(Addresses.class);
    }
}
