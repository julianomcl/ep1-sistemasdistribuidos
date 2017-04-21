package br.usp.each.si.dsid.ep1;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Client {

	private static String _host;
	private static String _serverName;
	private static Registry _registry;
	private static ConcurrentHashMap<IPart, Integer> _currentSubPartList;
	private static IPart _currentPart;

	private Client() {

	}

	public static void main(String[] args) {
		_host = (args.length < 1) ? null : args[0];
		_currentSubPartList = new ConcurrentHashMap<IPart, Integer>();
		
		System.out.println("Client started.");
		try {
			_registry = LocateRegistry.getRegistry(_host);

			boolean continueRunning = true;
			// create a scanner so we can read the command-line input
			Scanner scanner = new Scanner(System.in);
			while (continueRunning) {

				// get their input as a String
				String command = scanner.nextLine();
				
				String commands[] = command.trim().split(" ");
				if (commands[0].equals("bind")) {
					if(commands.length > 1)
						bind(commands[1]);
					else
						System.out.println("Server name not defined.");
				} else if (commands[0].equals("listp")) {
					listp();
				} else if (commands[0].equals("getp")) {
					if(commands.length > 1)
						getp(commands[1]);
					else
						System.out.println("Part code not defined.");
				} else if (commands[0].equals("showp")) {
					showp();
				} else if (commands[0].equals("clearlist")) {
					clearList();
				} else if (commands[0].equals("addsubpart")) {
					if(commands.length > 1)
						addSubPart(Integer.parseInt(commands[1]));
					else
						System.out.println("Quantity not defined.");
				} else if (commands[0].equals("addp")) {
					command = command.replace("addp -n ", "");
					String split[] = command.split("-d ");
					String name = split[0].trim();
					String description = (split.length > 1) ? split[1].trim() : "";

					addp(name, description);

				} else if (commands[0].equals("info")) {
					info();
				} else if (commands[0].equals("showlist")) {
					showlist();
				} else if (commands[0].equals("quit")) {
					continueRunning = false;
					System.out.println("Bye bye");
				}
				
			}

			scanner.close();

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	private static void showlist() throws RemoteException {
		if (_currentSubPartList.isEmpty()) {
			System.out.println("The current subparts list is empty.");
		} else {
			String result = "\nSUBPARTS CODE | QUANTITY: \n";
			for (Entry<IPart, Integer> entry : _currentSubPartList.entrySet()) {
				try {
					result += "\t" + entry.getKey().getCode() + " | " + entry.getValue() + "\n";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(result);
		}
	}

	private static void info() throws AccessException, RemoteException, NotBoundException {
		if(_serverName == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		IPartRepository stub = (IPartRepository) _registry.lookup(_serverName);
		String result = stub.getInfo();
		System.out.println(result);
	}

	private static void addSubPart(int quantity) throws RemoteException {
		if(_currentPart == null){
			System.out.println("Is necessary to add or get a part");
			return;
		}
		try {
			System.out.println("Adding " + quantity + " units of " + _currentPart.getCode() + " to subpart list.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		_currentSubPartList.put(_currentPart, quantity);
	}

	private static void addp(String name, String description)
			throws AccessException, RemoteException, NotBoundException {
		
		if(_serverName == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		
		IPartRepository stub = (IPartRepository) _registry.lookup(_serverName);
		_currentPart = stub.addP(name, description,_currentSubPartList);
		System.out.println("Part " + _currentPart.getCode() + " added. Current part is now clear again.");
	}

	private static void clearList() {
		_currentSubPartList.clear();
		System.out.println("Current list is now empty.");
	}

	private static void showp() {
		try {
			if(_currentPart != null)
				System.out.println(_currentPart.toFormattedString());
			else
				System.out.println("Is necessary to add or get a part");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void getp(String id) throws AccessException, RemoteException, NotBoundException {
		if(_serverName == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		IPartRepository stub = (IPartRepository) _registry.lookup(_serverName);
		IPart response = stub.getP(id);
		if (response != null) {
			_currentPart = response;
			System.out.println("The current part is " + _currentPart.getCode());
		} else {
			System.out.println("No part found with the specified code.");
		}
	}

	private static void listp() throws AccessException, RemoteException, NotBoundException {
		if(_serverName == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		IPartRepository stub = (IPartRepository) _registry.lookup(_serverName);
		String response = stub.listP();
		System.out.println(response);
	}

	private static void bind(String serverName) throws RemoteException, NotBoundException {
		_serverName = serverName;

		try {
			System.out.println("Binding to " + _serverName);
			IPartRepository stub = (IPartRepository) _registry.lookup(_serverName);
			String response = stub.sayHello();
			System.out.println(response);
		} catch(NotBoundException e) {
			System.err.println("Server doesn't exist");
		}
	}

}
