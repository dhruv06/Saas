package com.example.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller  {

	@GetMapping("/runcode") 
	public List<String> sayhelloworld() throws IOException 
	{
		ProcessBuilder pb = new ProcessBuilder(); 
		//pb.command("bash" , "-c" , "cd /user/dhruv/Documents/workspace-sts-3.9.10.RELEASE/demo/usercode");
		pb.command("bash", "-c", "ls");
		
		Process p = pb.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		  String line;
		  List<String> output = new ArrayList<String>();
          while ((line = br.readLine()) != null) {
              output.add(line) ;
          }
		return output;
	}
}
