package org.anc.grid.data.masc.client;

import javax.xml.rpc.ServiceException;
//import javax.xml.rpc.encoding.*;


public class MascDataSourceClient extends DataSourceClient
{
   public static class Service {
      private static final String HOST = "http://grid.anc.org:8080";
      public static final String NAMESPACE = HOST + "/service_manager/invoker/anc:MASC_DATA";
      public static final String ENDPOINT = HOST + "/service_manager/invoker/anc:MASC_DATA";
   };

   public MascDataSourceClient(String user, String password) throws ServiceException
   {
      super(Service.NAMESPACE, Service.ENDPOINT);
      super.setCredentials(user, password);
   }
}

