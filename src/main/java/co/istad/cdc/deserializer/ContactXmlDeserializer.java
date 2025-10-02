package co.istad.cdc.deserializer;

import co.istad.cdc.model.Contacts;

public class ContactXmlDeserializer extends XmlStringDeserializer<Contacts> {
    public ContactXmlDeserializer() {
        super(Contacts.class);
    }
}
