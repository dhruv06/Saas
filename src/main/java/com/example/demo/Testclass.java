package com.example.demo;

import java.io.IOException;

public class Testclass {
	
	public static void main(String args[]) throws IOException
	{
	    Server myapp = new Server();
	    myapp.startServer("localhost", 4000, "/upload");
	}
	

}
