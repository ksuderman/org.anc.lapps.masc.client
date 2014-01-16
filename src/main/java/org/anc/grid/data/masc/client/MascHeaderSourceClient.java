package org.anc.grid.data.masc.client;

import javax.xml.rpc.ServiceException;

/**
 * @author Keith Suderman
 */
public class MascHeaderSourceClient extends DataSourceClient
{
   public static class Service {
//      private static final String HOST = "http://picard:8080";
      private static final String HOST = "http://grid.anc.org:8080";
      public static final String NAMESPACE = HOST + "/service_manager/invoker/anc:masc.headers_1.3.0";
      public static final String ENDPOINT = HOST + "/service_manager/invoker/anc:masc.headers_1.3.0";
   }

   public MascHeaderSourceClient(String user, String password) throws ServiceException
   {
      super(Service.NAMESPACE, Service.ENDPOINT);
      super.setCredentials(user, password);
   }

}
