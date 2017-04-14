package br.usp.each.si.dsid.ep1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	private static String _host;
	private static String _serverName;
	private static Registry _registry;

	private Client() {
		
	}
	
	public static void main(String[] args) {
		_host = (args.length < 1) ? null : args[0];
		try {
			_registry = LocateRegistry.getRegistry(_host);
	        
	        boolean continueRunning = true;
	        // create a scanner so we can read the command-line input
            Scanner scanner = new Scanner(System.in);
	        while(continueRunning){
	        	
	            // get their input as a String
	            String command = scanner.nextLine();

	            if(command.contains("bind")){
	            	bind(command.substring(5).trim());
	            }else if(command.contains("listp")){
	            	listp();
	            }else if(command.contains("getp")){
	            	getp(command.substring(5).trim());
	            }else if(command.contains("showp")){
	            	showp();
	            }else if(command.contains("clearlist")){
	            	clearList();
	            }else if(command.contains("addsubpart")){
	            	addSubPart(new Scanner(command).nextInt());
	            }else if(command.contains("addp")){
	            	addp();
	            }else if(command.contains("quit")){
	            	continueRunning = false;
	            }
	        	
	        }
	        
	        scanner.close();
	        
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	private static void addp() {
		// TODO Auto-generated method stub
		
	}

	private static void addSubPart(int quantity) {
		// TODO Auto-generated method stub
		
	}

	private static void clearList() {
		// TODO Auto-generated method stub
		
	}

	private static void showp() {
		// TODO Auto-generated method stub
		
	}

	private static void getp(String id) {
		// TODO Auto-generated method stub
		
	}

	private static void listp() {
		// TODO Auto-generated method stub
		
	}

	private static void bind(String serverName) throws RemoteException, NotBoundException {
		_serverName = serverName;
		
		System.out.println("Binding to "+ _serverName);
		PartRepository stub = (PartRepository) _registry.lookup(_serverName);
		String response = stub.sayHello();
        System.out.println(response);
	}

}
