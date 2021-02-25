package br.com.zup.beta.microServico.core.config.segurança;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;

public class CryptoConverter implements AttributeConverter<String, String> {

    @Autowired
    private CryptDecrypt dataCrypt;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return dataCrypt.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dataCrypt.encode(dbData);
    }
}
