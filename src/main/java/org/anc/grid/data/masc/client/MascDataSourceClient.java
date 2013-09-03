package org.anc.grid.data.masc.client;

import javax.xml.rpc.ServiceException;
//import javax.xml.rpc.encoding.*;


public class MascDataSourceClient extends DataSourceClient
{
   public static class Service {
//      private static final String HOST = "http://picard:8080";
      private static final String HOST = "http://grid.anc.org:8080";
      public static final String NAMESPACE = HOST + "/service_manager/invoker/lapps:MASC_DATA_SOURCE";
      public static final String ENDPOINT = HOST + "/service_manager/invoker/lapps:MASC_DATA_SOURCE";
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

