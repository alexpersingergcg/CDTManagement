package com.groundswellcg.utilities.cdtmanagement;

import com.appiancorp.services.ServiceContext;
import com.appiancorp.services.ServiceContextFactory;
import com.appiancorp.suiteapi.process.framework.*;
import com.appiancorp.suiteapi.type.config.ImportResult;
import com.appiancorp.suiteapi.type.config.xsd.XsdTypeImporter;
import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.palette.PaletteInfo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@PaletteInfo(paletteCategory = "Groundswell CG Utilities", palette = "CDT Management")
@Order({ "Fields" })
public class CDTManagement extends AppianSmartService {

  private static final Logger LOG = Logger.getLogger(CDTManagement.class);

  private final SmartServiceContext smartServiceCtx;
  private String[] fields;

  public CDTManagement(SmartServiceContext smartServiceCtx) {
    super();
    this.smartServiceCtx = smartServiceCtx;
  }

  /* This will build create the plugin without issue - just increment the version or change the name */
  @Override
  public void run() throws SmartServiceException {
    try {
      LOG.debug("Starting");

      String string =
          "<xsd:schema xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:tns=\"urn:com:appian:types:DPE\" targetNamespace=\"urn:com:appian:types:DPE\">" +
              "<xsd:complexType name=\"AP_H_AlexTesting\">"+
              "<xsd:annotation>"+
              "<xsd:appinfo source=\"appian.jpa\">@Table(name=\"DPE_ALEX_TESTING\")</xsd:appinfo>"+
              "<xsd:documentation><![CDATA[Programmatically made]]></xsd:documentation>"+
              "</xsd:annotation>"+
              "<xsd:sequence>"+
              "<xsd:element name=\"firstName\" nillable=\"true\" type=\"xsd:string\">"+
              "<xsd:annotation>"+
              "<xsd:appinfo source=\"appian.jpa\">@Column(name=\"FIRST_NAME\")</xsd:appinfo>"+
              "</xsd:annotation></xsd:element></xsd:sequence></xsd:complexType></xsd:schema> ";

      //use ByteArrayInputStream to get the bytes of the String and convert them to InputStream.
      InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));

      LOG.debug(inputStream);
      LOG.debug(String.join(", ", fields));
      LOG.debug(this.smartServiceCtx.getUsername());
      ServiceContext sc = ServiceContextFactory.getServiceContext(smartServiceCtx.getUsername());
      LOG.debug(sc.getName());
      ImportResult ir = XsdTypeImporter.importFromStream(
          inputStream,
          true,
          sc
      );
      LOG.debug("New datatype length" + ir.getNewDatatypes().length);
      LOG.debug("FIN");
    }
    catch(Exception e) {
      LOG.error(e, e);
      throw createException(e, "error.createcdt.general", e.getMessage());
    }

  }

  private SmartServiceException createException(Throwable t, String key,
                                                Object... args) {
    return new SmartServiceException.Builder(getClass(), t).userMessage(key,
        args).build();
  }


  /* Once you've set the fields - you're stuck with them. Choose wisely. */
  @Input(required = Required.ALWAYS)
  @Name("Fields")
  public void setUsername(String[] username) {
    this.fields = username;
  }
}
