package org.anc.grid.data.masc.client.test;

import org.anc.grid.data.masc.client.DataSourceClient;
import org.junit.*;
import org.lappsgrid.api.*;
import org.lappsgrid.discriminator.Types;

import javax.xml.rpc.ServiceException;

import static org.junit.Assert.*;

/**
 * @author Keith Suderman
 */
@Ignore
public class GigawordTest
{
   public static final String ENDPOINT = "http://grid.ldc.upenn.edu:8081/doc_service/services/DocumentDataSource";
   public GigawordTest()
   {

   }

   @Test
   public void testList() throws ServiceException, InternalException
   {
      DataSourceClient client = new DataSourceClient(ENDPOINT, "username", "password");
      String[] listing = client.list();
      assertTrue(listing != null);
      assertTrue(listing.length > 0);
      int count = 0;
      for (String key : listing)
      {
         ++count;
         System.out.println(count + ". " + key);
      }
   }

   @Test
   public void testGet() throws ServiceException
   {
      DataSourceClient client = new DataSourceClient(ENDPOINT, "username", "password");
      Data result = client.get("NYT_ENG_19940701.0001");
      assertTrue(result != null);
      assertTrue(result.getDiscriminator() != Types.ERROR);
      assertTrue(result.getPayload() != null);
      System.out.println(result.getPayload());
   }

   @Test
   public void testGetBadId() throws ServiceException
   {
      DataSourceClient client = new DataSourceClient(ENDPOINT, "username", "password");
      Data result = client.get("NYT_ENG_19940701.0011");
      assertTrue(result != null);
      assertTrue(result.getDiscriminator() == Types.ERROR);
      assertTrue(result.getPayload() != null);
      System.out.println("'" + result.getPayload() + "'");
   }

}
