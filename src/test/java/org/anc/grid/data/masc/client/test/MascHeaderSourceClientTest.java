package org.anc.grid.data.masc.client.test;

import org.lappsgrid.api.*;
import org.lappsgrid.discriminator.Types;

import org.anc.grid.data.masc.client.*;
import org.xces.graf.api.GrafException;
import org.xces.graf.io.Validator;

import org.junit.*;
import org.lappsgrid.api.InternalException;
//import org.xml.sax.InputSource;

import javax.xml.rpc.ServiceException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * @author Keith Suderman
 */
public class MascHeaderSourceClientTest
{
   private static final String USER = "operator1";
   private static final String PASS = "operator1";
   private MascHeaderSourceClient service;

   @Before
   public void before() throws ServiceException
   {
      service = new MascHeaderSourceClient(USER, PASS);
   }

   @After
   public void after()
   {
      service = null;
   }

   @Test
   public void testList() throws InternalException, GrafException
   {
      Validator validator = Validator.newDocumentHeaderValidator();
      String[] list = service.list();
      for (String key : list)
      {
         Data data = service.get(key);
         assertTrue(Types.GRAF == data.getDiscriminator());
         String header = data.getPayload();
         Source input = new StreamSource(new StringReader(header));
         try
         {
            System.out.println("Validating " + key);
            validator.validate(input);
         }
         catch (GrafException e)
         {
            fail(e.getMessage());
         }
      }
   }
}
