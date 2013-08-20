package org.anc.grid.data.masc.client.serial;

import java.io.IOException;

import javax.xml.namespace.QName;

import org.lappsgrid.api.Data;
import org.lappsgrid.discriminator.DiscriminatorRegistry;
import org.apache.axis.Constants;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.wsdl.fromJava.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class DataSerializer implements org.apache.axis.encoding.Serializer
{
   private Logger logger = LoggerFactory.getLogger(DataSerializer.class);
   @Override
   public String getMechanismType()
   {
      return Constants.AXIS_SAX;
   }

   @Override
   public void serialize(QName qname, Attributes atts, Object object,
         SerializationContext context) throws IOException
   {
      if (!(object instanceof Data))
      {
         throw new IOException("Invalid type for serializer.");
      }
      Data data = (Data) object;
      logger.debug("Serializing data object: " + DiscriminatorRegistry.get(data.getDiscriminator()));
      logger.debug("Payload : " + data.getPayload());
      context.startElement(qname, atts);
//      AttributesImpl payloadAtts = new AttributesImpl();
//      payloadAtts.addAttribute("", "type", "type", "String", Long.toString(data.getDiscriminator()));
      context.serialize(new QName("", "discriminator"), null, data.getDiscriminator());
      context.serialize(new QName("", "payload"), null, data.getPayload());
      context.endElement();
//      public void serialize(
//           QName name,
//           Attributes attributes,
//           Object value,
//           SerializationContext context)
//      throws IOException {
//      if (!(value instanceof Book))
//         throw new IOException(
//                 "Can't serialize a "
//                         + value.getClass().getName()
//                         + " with a BookSerializer.");
//      Book data = (Book) value;
//
//      context.startElement(name, attributes);
//      context.serialize(new QName("", NAMEMEMBER), null, data.name);
//      context.serialize(new QName("", AUTHORMEMBER), null, data.author);
//      context.endElement();
   }

   @Override
   public Element writeSchema(Class theClass, Types types) throws Exception
   {
      Element complexType = types.createElement("complexType");
      QName qName = new QName("uri:org.lappsgrid.api/", "Data");
      types.writeSchemaElement(qName, complexType);
      complexType.setAttribute("name", qName.getLocalPart());
      Element sequence = types.createElement("sequence");
      complexType.appendChild(sequence);

      Element element = types.createElement("element");
      element.setAttribute("name", "discriminator");
      element.setAttribute("type", "xsd:long");
      sequence.appendChild(element);

      element = types.createElement("element");
      element.setAttribute("name", "payload");
      element.setAttribute("type", "xsd:string");
      sequence.appendChild(element);
      return complexType;
   }

//   public Element _writeSchema(Class arg0, Types types) throws Exception {
//      //  Auto-generated method stub
//      Element complexType = types.createElement("complexType");
//      types.writeSchemaElement(myTypeQName, complexType);
//      complexType.setAttribute("name", myTypeQName.getLocalPart());
//      Element seq = types.createElement("sequence");
//      complexType.appendChild(seq);
//
//      Element element = types.createElement("element");
//      element.setAttribute("name", "name");
//      element.setAttribute("type", "xsd:string");
//      seq.appendChild(element);
//      Element element2 = types.createElement("element");
//      element2.setAttribute("name", "author");
//      element2.setAttribute("type", "xsd:string");
//      seq.appendChild(element2);
//
//      return complexType;
//   }
}
