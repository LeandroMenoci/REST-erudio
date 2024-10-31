package br.com.curso.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    // Constante para o tipo de mídia YAML
    private static final MediaType YAML_MEDIA_TYPE = MediaType.parseMediaType("application/x-yaml");

    public YamlJackson2HttpMessageConverter() {
        // Chama o construtor da superclasse com um YAMLMapper configurado e o tipo de mídia
        super(new YAMLMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL), // Não incluir campos nulos na saída
                YAML_MEDIA_TYPE);
    }

    // Adicionando suporte a mais tipos de mídia, se necessário
    @Override
    protected boolean canWrite(MediaType mediaType) {
        return YAML_MEDIA_TYPE.isCompatibleWith(mediaType) || super.canWrite(mediaType);
    }
    
    @Override
    protected boolean canRead(MediaType mediaType) {
        return YAML_MEDIA_TYPE.isCompatibleWith(mediaType) || super.canRead(mediaType);
    }
}
