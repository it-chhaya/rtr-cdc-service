package co.istad.cdc.deserializer;

import co.istad.cdc.model.Address;
import co.istad.cdc.model.Kyc;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.List;

public class KycXmlDeserializer extends XmlStringDeserializer<List<Kyc>> {
    public KycXmlDeserializer() {
        super(new XmlMapper().getTypeFactory()
                .constructCollectionType(List.class, Kyc.class));
    }
}
