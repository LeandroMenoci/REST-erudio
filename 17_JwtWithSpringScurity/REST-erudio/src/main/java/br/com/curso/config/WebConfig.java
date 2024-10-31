package br.com.curso.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.curso.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration // Indica que esta classe é uma configuração do Spring
public class WebConfig implements WebMvcConfigurer {
    
    // Define o tipo de mídia para arquivos YAML
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    // Obtém a configuração de padrões de origem para CORS a partir do arquivo de propriedades
    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns = "";

    // Método que estende os conversores de mensagem para incluir o conversor YAML
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Adiciona o conversor personalizado para YAML à lista de conversores
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    // Configuração de CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Divide as origens permitidas com base em vírgulas
        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**") // Permite o mapeamento para todos os endpoints
                .allowedMethods("*") // Permite todos os métodos HTTP
                .allowedOrigins(allowedOrigins) // Define as origens permitidas
                .allowCredentials(true); // Permite credenciais nas requisições
    }

    // Configuração de negociação de conteúdo
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Configuração para usar cabeçalhos para negociação de conteúdo
        configurer.favorParameter(false) // Desativa a favor de parâmetros na URL
            .ignoreAcceptHeader(false) // Não ignora o cabeçalho "Accept"
            .useRegisteredExtensionsOnly(false) // Permite extensões não registradas
            .defaultContentType(MediaType.APPLICATION_JSON) // Define o tipo de conteúdo padrão
                .mediaType("json", MediaType.APPLICATION_JSON) // Define o tipo de mídia JSON
                .mediaType("xml", MediaType.APPLICATION_XML) // Define o tipo de mídia XML
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML); // Define o tipo de mídia YAML
    }
}
