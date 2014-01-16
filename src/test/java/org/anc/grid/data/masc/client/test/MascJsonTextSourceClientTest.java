package org.anc.grid.data.masc.client.test;

import org.anc.grid.data.masc.client.Credentials;
import org.anc.grid.data.masc.client.DataSourceClient;
import org.anc.grid.data.masc.client.MascJsonTextSourceClient;
import org.junit.*;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.InternalException;
import org.lappsgrid.discriminator.Types;

import javax.xml.rpc.ServiceException;

import static org.junit.Assert.*;

/**
 * @author Keith Suderman
 */
public class MascJsonTextSourceClientTest
{
   DataSourceClient client;

   public MascJsonTextSourceClientTest()
   {

   }

   @Before
   public void before() throws ServiceException
   {
//      client = new MascJsonTextSourceClient("operator", "operator");
      client = new DataSourceClient("http://grid.anc.org:8080/service_manager/invoker/anc:masc.text.json_1.3.0", Credentials.USERNAME, Credentials.PASSWORD);
   }

   @After
   public void after()
   {
      client = null;
   }

   @Test
   public void testList() throws InternalException
   {
      String[] listing = client.list();
      assertFalse("Listing is null.", listing == null);
      assertFalse("Listing is empty", listing.length == 0);
   }

   @Test
   public void testGet() throws InternalException
   {
      String[] listing = client.list();
//      Data query = new Data(Types.GET, listing[0]);
//      Data data = client.query(query);
      Data data = client.get(listing[0]);
      assertFalse(data.getPayload(), data.getDiscriminator() == Types.ERROR);
      assertTrue("Expected JSON", data.getDiscriminator() == Types.JSON);
   }
}
