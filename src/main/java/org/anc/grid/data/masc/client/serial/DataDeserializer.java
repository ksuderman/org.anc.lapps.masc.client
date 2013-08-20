package org.anc.grid.data.masc.client.serial;

import java.util.*;

import javax.xml.namespace.QName;

import org.apache.axis.Constants;
import org.apache.axis.encoding.*;
import org.apache.axis.message.SOAPHandler;
import org.lappsgrid.api.Data;
import org.lappsgrid.discriminator.Types;
import org.slf4j.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DataDeserializer extends DeserializerImpl
{
   private Logger logger = LoggerFactory.getLogger(DataSerializer.class);
   public static final String DISCRIMINATOR = "discriminator";
   public static final String PAYLOAD = "payload";
   public static final QName QNAME = new QName("uri:org.lappsgrid.api/", "Data");

   private Map<String,QName> typesIndex = new HashMap<String,QName>();

   public DataDeserializer()
   {
      typesIndex.put(DISCRIMINATOR, Constants.XSD_LONG);
      typesIndex.put(PAYLOAD, Constants.XSD_BASE64);
      value = new Data(Types.ERROR, "Uninitialized");
   }

   public SOAPHandler onStartChild(String namespace, String localName,
                                   String prefix, Attributes attributes,
                                   DeserializationContext context)
      throws SAXException
   {
      logger.info("onStartChild: {}", localName);
      QName qName;
      Target target = null;
      if (localName.equals(DISCRIMINATOR))
      {
         qName = Constants.XSD_LONG;
         target = new DiscriminatorTarget(value);
      }
      else if (localName.equals(PAYLOAD))
      {
         qName = Constants.XSD_STRING;
         target = new PayloadTarget(value);
      }
      else
      {
         throw new SAXException("Invalid element in Data struct: " + localName);
      }

      Deserializer deserializer = context.getDeserializerForType(qName);
      if (deserializer == null)
      {
         throw new SAXException("No deserializer for " + qName);
      }

//      try
//      {
//         deserializer.registerValueTarget(new FieldTarget(value, localName));
//         deserializer.registerValueTarget(new MethodTarget(value, methodName));
         deserializer.registerValueTarget(target);
//      }
//      catch (NoSuchMethodException e)
//      {
//         throw new SAXException("Unable to register value target.", e);
//      }

      return (SOAPHandler) deserializer;
   }
   @Override
   public String getMechanismType()
   {
      return Constants.AXIS_SAX;
   }

   /*
   @Override
   public void setValue(Object arg0, Object arg1) throws SAXException
   {
      System.out.println("Setting " + arg0.toString() + " to " + arg1.toString());
   }

   @Override
   public boolean componentsReady()
   {
      System.out.println("DataDeserializer.componentsReady()");
      return true;
   }

   @Override
   public void endElement(String arg0, String arg1, DeserializationContext context)
         throws SAXException
   {
      System.out.println("end Element " + arg0 + " " + arg1);
   }

   @Override
   public QName getDefaultType()
   {
      System.out.println("DataDeserializer.getDefaultType()");
      return null;
   }

   @Override
   public Object getValue()
   {
      System.out.println("DataDeserializer.getValue()");
      return null;
   }

   @Override
   public Object getValue(Object arg0)
   {
      System.out.println("DataDeserializer.getValue() " + arg0.toString());
      return null;
   }

   @Override
   public Vector getValueTargets()
   {
      System.out.println("DataDeserializer.getValueTargets()");
      return null;
   }

   @Override
   public void moveValueTargets(Deserializer arg0)
   {
      System.out.println("DataDeserializer.moveValueTargets()");
   }

   @Override
   public void onEndElement(String arg0, String arg1,
         DeserializationContext arg2) throws SAXException
   {
      System.out.println("DataDeserializer.onEndElement() " + arg0 + " " + arg1);
   }

   @Override
   public SOAPHandler onStartChild(String namespace, String localName, String prefix,
         Attributes atts, DeserializationContext context) throws SAXException
   {
      System.out.println("DataDeserializer.onStartChild()");
      return null;
   }

   @Override
   public void onStartElement(String namespace, String localName, String prefix,
         Attributes atts, DeserializationContext context) throws SAXException
   {
      System.out.println("DataDeserializer.onStartElement()");
      System.out.println("  ns=" + namespace + " name=" + localName + " prefix" + prefix);
   }

   @Override
   public void registerValueTarget(Target target)
   {
      System.out.println("DataDeserializer.registerValueTarget() " + target.toString());
   }

   @Override
   public void removeValueTargets()
   {
      System.out.println("DataDeserializer.removeValueTargets()");
   }

   @Override
   public void setChildValue(Object arg0, Object arg1) throws SAXException
   {
      System.out.println("DataDeserializer.setChileValue() " + arg0.toString() + " " + arg1.toString());
   }

   @Override
   public void setDefaultType(QName arg0)
   {
      System.out.println("DataDeserializer.setDefaultType()" + arg0.toString());
   }

   @Override
   public void setValue(Object arg0)
   {
      System.out.println("DataDeserializer.setValue() " + arg0.toString());
   }

   @Override
   public void startElement(String namespace, String local, String prefix,
         Attributes atts, DeserializationContext context) throws SAXException
   {
      System.out.println("DataDeserializer.startElement()");
      System.out.println(String.format("   %s %s %s", namespace, local, prefix));
   }

   @Override
   public void valueComplete() throws SAXException
   {
      System.out.println("DataDeserializer.valueComplete()");
   }
   */
}

abstract class DataTarget implements Target
{
   protected Data target;

   public DataTarget(Object target)
   {
      this.target = (Data) target;
   }
}

class DiscriminatorTarget extends DataTarget
{
   private Logger logger = LoggerFactory.getLogger(DiscriminatorTarget.class);
   public DiscriminatorTarget(Object target)
   {
      super(target);
   }

   public void set(Object object)
   {
      logger.info("Setting discriminator: {}", object.toString());
      target.setDiscriminator((Long)object);
   }
}

class PayloadTarget extends DataTarget
{
   private Logger logger = LoggerFactory.getLogger(PayloadTarget.class);

   public PayloadTarget(Object target)
   {
      super(target);
   }

   public void set(Object object)
   {
      logger.info("Setting payload: {}", object.toString());
      target.setPayload(object.toString());
   }
}