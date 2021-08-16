package com.rolandopalermo.facturacion.ec.common;

public interface Constants {

    String API_DOC_ANEXO_1 = "Ver Ficha Técnica de Comprobantes Electrónicos - Anexos";

    String DATE_MINIFIED_FORMAT = "ddMMyyyy";
    String DATE_FORMAT = "dd/MM/yyyy";
    String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    String DATE_TIME_FORMAT_WITH_TZ = "yyyy-MM-dd'T'HH:mm:ss";
    String DATE_TIME_FORMAT_WITH_FULL_TZ = "yyyy-MM-dd'T'HH:mm:ssZ";
    String SQL_DATE_FORMAT = "yyyy-MM-dd";
    String SQL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    String ISO_OFFSET_DATE_TIME_PATTERN = "^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{2}:\\d{2}-\\d{2}:\\d{2}$";
    String DATE_TIME_FORMAT_PATTERN = "^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$";

    String SRI_REJECTED = "DEVUELTA";
    String SRI_RECEIVED = "RECIBIDA";
    String SRI_APPLIED = "AUTORIZADO";
    String SRI_INVALID = "NO AUTORIZADO";

    int MAX_ACCESS_KEY_LENGTH = 49;

    String XML_XPATH_WITH_WRAPPER = "//*[local-name()='autorizaciones']/autorizacion[1]/comprobante";
    String XML_XPATH_WITHOUT_WRAPPER = "//*[local-name()='autorizacion']/comprobante";
    String XML_XPATH_AUTHORIZATION_DATE = "//*[local-name()='fechaAutorizacion']";
    String XML_XPATH_AUTHORIZATION_STATUS = "//*[local-name()='autorizacion']/estado";
    String XML_XPATH_AUTHORIZATION_ACCESS_KEY = "//*[local-name()='numeroAutorizacion']";
    String XML_XPATH_AUTHORIZATION_REQUESTED_ACCESS_KEY = "//*[local-name()='claveAccesoConsultada']";
    String XML_XPATH_EMAILS = "//infoAdicional/campoAdicional[@nombre='%s']";
    String XML_XPATH_DOC_NUMBER = "//infoTributaria/secuencial";
    String XML_XPATH_COD_DOC = "//infoTributaria/codDoc";
    String XML_XPATH_SUPPLIER_NAME = "//infoTributaria/razonSocial";
    String XML_XPATH_SUPPLIER_NUMBER = "//infoTributaria/ruc";
    String XML_XPATH_ACCESS_KEY = "//infoTributaria/claveAcceso";
    String XML_XPATH_BRANCH = "//infoTributaria/estab";
    String XML_XPATH_BRANCH_OFFICE = "//infoTributaria/ptoEmi";

    String ID_CE_CERTIFICATE_POLICIES = "2.5.29.32";

    String VERONICA_NOMBRE_DOCUMENTO = "VERONICA_NOMBRE_DOCUMENTO";
    String VERONICA_SECUENCIAL_DOCUMENTO = "VERONICA_SECUENCIAL_DOCUMENTO";
    String VERONICA_NOMBRE_EMISOR = "VERONICA_NOMBRE_EMISOR";
    String VERONICA_NOMBRE_RECEPTOR = "VERONICA_NOMBRE_RECEPTOR";
    String VERONICA_TOTAL = "VERONICA_TOTAL";
    String VERONICA_ESTABLECIMIENTO = "VERONICA_ESTABLECIMIENTO";
    String VERONICA_PUNTO_EMISION = "VERONICA_PUNTO_EMISION";

    String TEMPLATE_ID_PARAM = "templateId";

    String SWAGGER_CLAVE_ACCESO = "Clave de acceso del comprobante electrónico";
    String SWAGGER_CUENTA_CORREO_ELECTRONICO_ID = "ID de la cuenta de correo electrónico";
    String SWAGGER_PLANTILLA_CORREO_ELECTRONICO_ID = "ID de la cuenta de correo electrónico";
    String SWAGGER_PLANTILLA_ADJUNTO_ID = "ID del documento adjunto de la plantilla de correo electrónico";
    String SWAGGER_PLANTILLA_VARIABLE_ID = "ID de la variable de la plantilla de correo electrónico";
    String SWAGGER_NUM_DOC = "Número secuencial del comprobante";
    String SWAGGER_RUC_EMPRESA = "Número de R.U.C. de la empresa";
    String SWAGGER_ID_EMISOR = "CI/PASAPORTE/Número de R.U.C. del emisor";
    String SWAGGER_ID_RECEPTOR = "Identificación del receptor";
    String SWAGGER_FECHA_AUTORIZACION = "Fecha de emisión en formato ";
    String SWAGGER_ESTATUS_COMPROBANTE = "Estatus del comprobante";
    String SWAGGER_RANGO_INICIAL = "(Rango inicial)";
    String SWAGGER_RANGO_FINAL = "(Rango final)";
    String SWAGGER_CLIENTE = "Información del cliente";
    String SWAGGER_CLIENTE_ID = "ID del cliente";
    String SWAGGER_CERTIFICADO_DIGITAL = "Información del certificado digital";
    String SWAGGER_CERTIFICADO_DIGITAL_ID = "ID del certificado digital";
    String SWAGGER_CUENTA_CORREO_ELECTRONICO = "Información de la cuenta de correo electrónico";
    String SWAGGER_PRODUCTO_ID = "ID del producto";
    String SWAGGER_ESTABLECIMIENTO_ID = "ID del establecimiento";
    String SWAGGER_PTO_EMISION_ID = "ID del punto de emisión";

}
