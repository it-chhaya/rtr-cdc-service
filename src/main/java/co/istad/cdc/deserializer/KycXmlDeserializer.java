package co.istad.cdc.deserializer;

import co.istad.cdc.model.KYC;

public class KycXmlDeserializer extends XmlStringDeserializer<KYC> {
    public KycXmlDeserializer() {
        super(KYC.class);
    }
}
