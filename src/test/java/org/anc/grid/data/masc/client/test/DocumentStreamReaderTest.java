package org.anc.grid.data.masc.client.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.anc.grid.data.masc.client.DocumentStreamReader;
import org.anc.grid.data.masc.client.MascDataSourceClient;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class DocumentStreamReaderTest
{
   private static final String USER = "operator1";
   private static final String PASS = "operator1";

   @Test
   public void testHasNext() throws ServiceException, IOException
   {
      MascDataSourceClient service = new MascDataSourceClient(USER, PASS);
      DocumentStreamReader reader = new DocumentStreamReader(service, "txt");
      assertTrue(reader.hasNext());
   }
   
   @Test
   public void testNext() throws Exception
   {
      MascDataSourceClient service = new MascDataSourceClient(USER, PASS);
      DocumentStreamReader reader = new DocumentStreamReader(service, "txt");
      assertTrue(reader.hasNext());
      String output = reader.next();
      assertTrue(output != null);
      System.out.println(output);
   }

   @Test
   public void testCount() throws Exception
   {
      MascDataSourceClient service = new MascDataSourceClient(USER, PASS);
      DocumentStreamReader reader = new DocumentStreamReader(service, "txt");
      assertTrue(reader.hasNext());
      int count = 0;
      while (reader.hasNext())
      {
         String output = reader.next();
         assertTrue(output != null);
         ++count;
         System.out.println("Fetched document " + count);
      }
      assertTrue(count == 392);
      
   }
}
