package com.rolandopalermo.facturacion.ec.common;

/**
 * Constantes globales
 *
 * @author Rolando
 */
public interface Constants {

    String API_DOC_ANEXO_1 = "Ver Ficha Técnica de Comprobantes Electrónicos - Anexos";

    String DATE_FORMAT = "dd/MM/yyyy";
    String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    String DATE_FORMAT_TZ = "yyyy-MM-dd'T'HH:mm:ss";

    String SRI_REJECTED = "DEVUELTA";
    String SRI_RECEIVED = "RECIBIDA";
    String SRI_APPLIED = "AUTORIZADO";
    String SRI_INVALID = "NO AUTORIZADO";

    String SWAGGER_CLAVE_ACCESO = "Clave de acceso del comprobante electrónico";
    String SWAGGER_PLANTILLA_ID = "Id de la plantilla de correo electrónico";
    String SWAGGER_PLANTILLA_ADJUNTO_ID = "Id del documento adjunto de la plantilla de correo electrónico";
    String SWAGGER_PLANTILLA_VARIABLE_ID = "Id de la variable de la plantilla de correo electrónico";
    String SWAGGER_NUM_DOC = "Número secuencial del comprobante";
    String SWAGGER_USUARIO = "Nombre de usuario de la aplicación";
    String SWAGGER_RUC_EMPRESA = "Número de R.U.C. de la empresa";
    String SWAGGER_ID_EMISOR = "Identificación del emisor";
    String SWAGGER_ID_RECEPTOR = "Identificación del receptor";
    String SWAGGER_FECHA_AUTORIZACION = "Fecha de emisión en formato ";
    String SWAGGER_ESTATUS_COMPROBANTE = "Estatus del comprobante";
    String SWAGGER_RANGO_INICIAL="(Rango inicial)";
    String SWAGGER_RANGO_FINAL="(Rango final)";

    String RUC_EMPRESA = "numero-ruc";
    String CLAVE_ACCESO = "clave-acceso";

    String QUEUE_NOTIFICATION = "notification.queue";

    int MAX_ACCESS_KEY_LENGTH = 49;

    String URI_API_BASE = "/api";

    String URI_API_V1 = URI_API_BASE + "/v1.0";

    // For users endpoints
    String URI_API_V1_ADMIN = URI_API_V1 + "/admin";
    String URI_API_V1_INFO = URI_API_V1 + "/info";
    String URI_API_V1_INVOICING = URI_API_V1 + "/comprobantes";
    String URI_API_V1_USER = URI_API_V1 + "/usuarios";
    String URI_API_V1_USERS_SUPPLIER = URI_API_V1 + "/empresas";
    String URI_API_V1_USERS_DASHBOARD = URI_API_V1_USERS_SUPPLIER + "/{numero-ruc}/dashboard";

    String URI_API_V1_OAUTH_TOKEN = URI_API_V1 + "/oauth/token";

    String XML_XPATH = "//*[local-name()='autorizaciones']/autorizacion[1]/comprobante";
    String MAX_ADDITIONAL_DETAILS_NUMBER_XPATH = "count(//detallesAdicionales[not(count(detAdicional) < //detallesAdicionales/count(detAdicional))]/detAdicional)";
    String EMAILS_XPATH = "//infoAdicional/campoAdicional[@nombre='%s']";

    Integer OAUTH2_ACCESS_TOKEN_DEFAULT_VALIDITY_SECOND = 60 * 60 * 12;
    Integer OAUTH2_REFRESH_TOKEN_DEFAULT_VALIDITY_SECOND = 60 * 60 * 24 * 30;

    String ID_CE_CERTIFICATE_POLICIES = "2.5.29.32";

    String[] SWAGGER_RESOURCES = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/swagger-resources/configuration/security"
    };

    String VERONICA_NOMBRE_DOCUMENTO = "VERONICA_NOMBRE_DOCUMENTO";
    String VERONICA_SECUENCIAL_DOCUMENTO = "VERONICA_SECUENCIAL_DOCUMENTO";
    String VERONICA_NOMBRE_EMISOR = "VERONICA_NOMBRE_EMISOR";

    int DEFAULT_PASSWORD_LENGTH = 12;
    String VALID_PW_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+{}[]|:;<>?,./";

}