package org.anc.grid.data.masc.client;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
//import javax.xml.rpc.encoding.*;

import org.lappsgrid.api.Data;
import org.lappsgrid.api.DataSource;

import org.anc.soap.client.AbstractSoapClient;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.SerializerFactory;
import org.lappsgrid.core.DataFactory;

import org.anc.grid.data.masc.client.serial.*;


public class MascDataSourceClient extends AbstractDataSourceClient
{
   public static class Service {
      public static final String NAMESPACE = "http://picard:8080/service_manager/invoker/lapps:MASC_ALL";
      public static final String ENDPOINT = "http://picard:8080/service_manager/invoker/lapps:MASC_ALL";
//	      public static final String NAMESPACE = "http://localhost:8080/MascDataSource/services/MascDataSource";
//	      public static final String ENDPOINT = "http://localhost:8080/MascDataSource/services/MascDataSource";
//      public static final String NAMESPACE = "http://localhost:9090/services/MascDataSource";
//      public static final String ENDPOINT = "http://localhost:9090/services/MascDataSource";
   };

	// The service on grid.anc.org
//   private static class Service {
//      public static final String NAMESPACE = "http://grid.anc.org:8080/MascDataSource/services/MascDataSource";
//      public static final String ENDPOINT = "http://grid.anc.org:8080/MascDataSource/services/MascDataSource"; 
//   };
   
   // The service on the service grid.
    
   public MascDataSourceClient(String user, String password) throws ServiceException
   {
      super(Service.NAMESPACE, Service.ENDPOINT);
      super.setCredentials(user, password);
   }
}

