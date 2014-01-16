package org.anc.grid.data.masc.client.test;

import org.anc.grid.data.masc.client.Credentials;
import org.anc.grid.data.masc.client.MascDataSourceClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.InternalException;
import org.lappsgrid.discriminator.DiscriminatorRegistry;
import org.lappsgrid.discriminator.Types;

import javax.xml.rpc.ServiceException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DataSourceTest
{
   static final long ERROR = DiscriminatorRegistry.get("error");

   protected MascDataSourceClient service;

   @After
   public void teardown()
   {
      service = null;
   }

   @Test
   public void testUnknownUser() throws ServiceException
   {
      System.out.println("DataSourceTest.testUnknownUser");
      service = new MascDataSourceClient("unknown", Credentials.PASSWORD);
      try
      {
         String[] parts = service.list();
         fail("ERROR: unknown user accepted.");
      }
      catch (InternalException e)
      {
         String message = e.getMessage();
         assertTrue(message, message.equals("(403)Forbidden"));
      }
   }

   @Test
   public void testInvalidPassword() throws ServiceException
   {
      System.out.println("DataSourceTest.testInvalidPassword");
      service = new MascDataSourceClient(Credentials.USERNAME, "invalid");
      try
      {
         String[] list = service.list();
      }
      catch (InternalException e)
      {
         String message = e.getMessage();
         assertTrue(message, "(403)Forbidden".equals(message));
      }
   }

   @Test
   public void testList() throws InternalException, ServiceException
   {
      System.out.println("DataSourceTest.testList");
      service = new MascDataSourceClient(Credentials.USERNAME, Credentials.PASSWORD);
      String[] list = service.list();
      assertTrue("Null result returned.", list != null);
      assertTrue("Empty list returned.", list.length > 0);
      System.out.println("Test passed.");
   }

   @Test
   public void testGet() throws ServiceException
   {
      System.out.println("DataSourceTest.testGet");
      service = new MascDataSourceClient(Credentials.USERNAME, Credentials.PASSWORD);
      Data result = service.get("MASC3-0286-ne");
      System.out.println("Data type returned: " + DiscriminatorRegistry.get(result.getDiscriminator()));
      assertTrue("Null result", result != null);
      assertTrue("ERROR: " + result.getPayload(), result.getDiscriminator() != ERROR);
      String payload = result.getPayload();
      assertTrue("Null payload", payload != null);
      System.out.println("Test passed.");
   }

   @Ignore // Not a unit test.
   public void test() throws ServiceException
   {
      MascDataSourceClient client = new MascDataSourceClient("Tester", "tester");
//      Data listData = client.list();
//      long type = listData.getDiscriminator();
//      System.out.println("Returned type is: " + DiscriminatorRegistry.get(type));
//      String items = listData.getPayload();

      Data data = client.get("MASC2-0055-txt");
      long type = data.getDiscriminator();
      System.out.println("Returned type is: " + type + " " + DiscriminatorRegistry.get(type));
      System.out.println(data.getPayload());

      data = client.get("invalid id");
      type = data.getDiscriminator();
      System.out.println("Returned type is: " + type + " " + DiscriminatorRegistry.get(type));
      System.out.println(data.getPayload());

   }


   public DataSourceTest()
   {
   }

   public static void main(String[] args)
   {
      try
      {
         new DataSourceTest().test();
      } catch (ServiceException e)
      {
         e.printStackTrace();
      }
   }
}
