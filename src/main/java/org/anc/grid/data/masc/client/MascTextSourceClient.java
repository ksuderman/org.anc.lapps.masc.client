package org.anc.grid.data.masc.client;

import javax.xml.rpc.ServiceException;

/**
 * @author Keith Suderman
 */
public class MascTextSourceClient extends AbstractDataSourceClient
{
   public static class Service {
      public static final String NAMESPACE = "http://picard:8080/service_manager/invoker/lapps:MASC_TEXT";
      public static final String ENDPOINT = "http://picard:8080/service_manager/invoker/lapps:MASC_TEXT";
   }

   public MascTextSourceClient(String user, String password) throws ServiceException
   {
      super(Service.NAMESPACE, Service.ENDPOINT);
      super.setCredentials(user, password);
   }
}
