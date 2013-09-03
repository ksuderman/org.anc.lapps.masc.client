package org.anc.grid.data.masc.client;

import javax.xml.rpc.ServiceException;

/**
 * @author Keith Suderman
 */
public class MascTextSourceClient extends DataSourceClient
{
   public static class Service {
//      private static final String HOST = "http://picard:8080";
      private static final String HOST = "http://grid.anc.org:8080";
      public static final String NAMESPACE = HOST + "/service_manager/invoker/lapps:MASC_TEXT_SOURCE";
      public static final String ENDPOINT = HOST + "/service_manager/invoker/lapps:MASC_TEXT_SOURCE";
   }

   public MascTextSourceClient(String user, String password) throws ServiceException
   {
      super(Service.NAMESPACE, Service.ENDPOINT);
      super.setCredentials(user, password);
   }
}
