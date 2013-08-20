package org.anc.grid.data.masc.client;

import javax.xml.rpc.ServiceException;

/**
 * @author Keith Suderman
 */
public class MascHeaderSourceClient extends AbstractDataSourceClient
{
   public static class Service {
      public static final String NAMESPACE = "http://picard:8080/service_manager/invoker/lapps:MASC_HEADERS";
      public static final String ENDPOINT = "http://picard:8080/service_manager/invoker/lapps:MASC_HEADERS";
   }

   public MascHeaderSourceClient(String user, String password) throws ServiceException
   {
      super(Service.NAMESPACE, Service.ENDPOINT);
      super.setCredentials(user, password);
   }

}
