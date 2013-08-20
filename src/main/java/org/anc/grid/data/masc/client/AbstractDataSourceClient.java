package org.anc.grid.data.masc.client;

import org.anc.soap.client.AbstractSoapClient;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.lappsgrid.api.*;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.Types;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author Keith Suderman
 */
public abstract class AbstractDataSourceClient extends AbstractSoapClient implements DataSource
{
   public AbstractDataSourceClient(String namespace, String endpoint) throws ServiceException
   {
      super(namespace, endpoint);
      QName q = new QName ("uri:org.lappsgrid.api/", "Data");
      BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
      BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
//      SerializerFactory serializer = new DataSerializerFactory();
//      DeserializerFactory deserializer = new DataDeserializerFactory();

      call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
   }

   @Override
   public Data query(Data input)
   {
      Data[] args = { input };
      Data result = null;
      try
      {
         result = (Data) super.invoke("query", args);
      }
      catch (RemoteException e)
      {
         result = DataFactory.error(e.getMessage());
      }
      return result;
   }

   public Data get(String key)
   {
      return this.query(DataFactory.get(key));
   }

   public String[] list() throws InternalException
   {
      Data result = this.query(DataFactory.list());
      if (result.getDiscriminator() == Types.ERROR)
      {
         throw new InternalException(result.getPayload());
      }
      return result.getPayload().split("\\s+");
   }
}
