package com.example.demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
//import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Regions;

import javax.servlet.http.HttpServletRequest;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.*;

import org.apache.commons.io.IOUtils;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
public class Server {
public  void startServer(String host,int port,String context_var) throws IOException
{
 
    HttpServer server = HttpServer.create(new InetSocketAddress(host,port), 0);
    HttpContext context = server.createContext(context_var);
     context.setHandler(Server::handleRequest);
     server.start();
     

System.out.println("Listening On Port 4000");
}


 private static void handleRequest(HttpExchange exchange) throws IOException {
   
   System.out.println(exchange.getRequestMethod());
  App1 app123=new App1();

  app123.handle(exchange);
 

      String response = "<html><p>Compilation log</p><br>",line;
      
      File newFile1 = new File("usercode/errors");
      BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(newFile1)));

       while ((line = bufferedReader1.readLine()) != null) {
          
           response+= line+"<br>";
       }
       bufferedReader1.close();
       response+="<p>output</p><br>";
      File newFile = new File("usercode/logfile.txt");
     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(newFile)));

      while ((line = bufferedReader.readLine()) != null) {
         
          response+= line+"<br>";
      }
      response+="</html>";
      bufferedReader.close();
  exchange.sendResponseHeaders(200, response.length());//res
    
     OutputStream os = exchange.getResponseBody();
   
     os.write(response.getBytes());

   
     os.close();
   
 }
}


class App1 implements HttpHandler {
App1()
{

}
    @SuppressWarnings("deprecation")
@Override
    public void handle(final HttpExchange t) throws IOException {
   
   

       
        for(Entry<String, List<String>> header : t.getRequestHeaders().entrySet()) {
       //     System.out.println(header.getKey() + ": " + header.getValue().get(0));
        }
        DiskFileItemFactory d = new DiskFileItemFactory();      

        try {
            ServletFileUpload up = new ServletFileUpload(d);

            Iterator<FileItem> result = up.parseRequest(new RequestContext() {

                @Override
                public String getCharacterEncoding() {
                    return "UTF-8";
                }

                @Override
                public int getContentLength() {
                    return 0; //tested to work with 0 as return
                }

                @Override
                public String getContentType() {
                    return t.getRequestHeaders().getFirst("Content-type");
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return t.getRequestBody();
                }

            }).iterator();
           
            InputStream fileIS1=null;
            String fname1=null;
            
            InputStream fileIS2=null;
            String fname2=null;
int flag=1;
String typeflag="0";
int inputflag=0;
String filestring="";
String inputstring="";
if(result.hasNext())
{
	FileItem item=result.next();

	 typeflag=item.getString();
//	 System.out.println(typeflag);
}
            while(result.hasNext())
            {
            	
            
            FileItem item=result.next();
            System.out.println(item.getString());
            if(typeflag.equals("1"))
            {
            if(!item.isFormField())
            {
            	if(flag==1)
            	{
            fileIS1=item.getInputStream();
            fname1=item.getName();
            System.out.println(fname1);
            flag=0;
            	}
            	else
            	{
            		 fileIS2=item.getInputStream();
                     fname2=item.getName();
                     System.out.println(fname2);
            	}
            	
           
            }
            }
            else
            {
            	if(inputflag==0)
            	{
            		filestring=item.getString();
            		inputflag=1;
            		fileIS1=new ByteArrayInputStream(filestring.getBytes(StandardCharsets.UTF_8));
            	}
            	else
            	{
            		inputstring=item.getString();
            		
            		fileIS2=new ByteArrayInputStream(inputstring.getBytes(StandardCharsets.UTF_8));
            	}
            }
            }          

            final String FILE_TO = "/home/dhruv/Documents/workspace-sts-3.9.10.RELEASE/demo/usercode/myfile.c" ;

                File file = new File(FILE_TO);
                OutputStream os =null;
                os=new FileOutputStream(file);
                IOUtils.copy(fileIS1, os);
                
                final String FILE_TO1 = "/home/dhruv/Documents/workspace-sts-3.9.10.RELEASE/demo/usercode/stdin" ;

                File file1 = new File(FILE_TO1);
                OutputStream os1 =null;
                os1=new FileOutputStream(file1);
                IOUtils.copy(fileIS2, os1);
                


        		ProcessBuilder pb = new ProcessBuilder(); 
        	//	pb.command("bash" , "-c" , "cd usercode");
        		pb.command("bash", "-c",  "docker run -v ~/Documents/workspace-sts-3.9.10.RELEASE/demo/usercode:/home/code gcc /bin/bash -c 'cd home/code;./shell.sh' ");
        		
        	
        		Process p = pb.start();
        		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      		  String line;
      		  List<String> output = new ArrayList<String>();
                while ((line = br.readLine()) != null) {
                    System.out.println(line);//(line) ;
                }
        		System.out.println("Here coming");

        		  
        		//ponse code and length
        	     
               // FileUtils.copyInputStreamToFile(fileIS, file);
               
}
        catch (IOException | FileUploadException e) {
        System.out.println(e.toString());
            e.printStackTrace();
           
        }
    }
   


}