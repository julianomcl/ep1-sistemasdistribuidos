package br.usp.each.si.dsid.ep1;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Currency;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Client {

	private static String _host;
	private static String _serverName;
	private static Registry _registry;
	private static ConcurrentHashMap<ConcretePart, Integer> _currentSubPartList;
	private static ConcretePart _currentPart;

	private Client() {

	}

	public static void main(String[] args) {
		_host = (args.length < 1) ? null : args[0];
		_currentSubPartList = new ConcurrentHashMap<ConcretePart, Integer>();
		_currentPart = new ConcretePart();

		try {
			_registry = LocateRegistry.getRegistry(_host);

			boolean continueRunning = true;
			// create a scanner so we can read the command-line input
			Scanner scanner = new Scanner(System.in);
			while (continueRunning) {

				// get their input as a String
				String command = scanner.nextLine();

				if (command.contains("bind")) {
					bind(command.substring(5).trim());
				} else if (command.contains("listp")) {
					listp();
				} else if (command.contains("getp")) {
					getp(command.substring(5).trim());
				} else if (command.contains("showp")) {
					showp();
				} else if (command.contains("clearlist")) {
					clearList();
				} else if (command.contains("addsubpart")) {
					addSubPart(Integer.parseInt(command.replace("addsubpart ", "").trim()));
				} else if (command.contains("addp")) {
					command = command.replace("addp -n ", "");
					String name = command.split("-d ")[0].trim();
					String description = command.split("-d ")[1].trim();

					addp(name, description);

				} else if (command.contains("quit")) {
					continueRunning = false;
				} else if (command.contains("info")) {
					info();
				} else if (command.contains("showlist")) {
					showlist();
				}

			}

			scanner.close();

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	private static void showlist() {
		if (_currentSubPartList.isEmpty()) {
			System.out.println("The current subparts list is empty.");
		} else {
			String result = "\nSUBPARTS CODE | QUANTITY: \n";
			for (Entry<ConcretePart, Integer> entry : _currentSubPartList.entrySet()) {
				result += "\t" + entry.getKey().getCode() + " | " + entry.getValue() + "\n";
			}
			System.out.println(result);
		}
	}

	private static void info() throws AccessException, RemoteException, NotBoundException {
		PartRepository stub = (PartRepository) _registry.lookup(_serverName);
		String result = stub.getInfo();
		System.out.println(result);
	}

	private static void addSubPart(int quantity) {
		System.out.println("Adding " + quantity + " units of " + _currentPart.getCode() + " to subpart list.");
		_currentSubPartList.put(_currentPart, quantity);
	}

	private static void addp(String name, String description)
			throws AccessException, RemoteException, NotBoundException {
		_currentPart.setName(name);
		_currentPart.setDescription(description);
		_currentPart.setSubParts(_currentSubPartList);

		PartRepository stub = (PartRepository) _registry.lookup(_serverName);
		String code = stub.addP(_currentPart);
		System.out.println("Part " + code + " added. Current part is now clear again.");
	}

	private static void clearList() {
		_currentSubPartList.clear();
		System.out.println("Current list is now empty.");
	}

	private static void showp() {
		System.out.println(_currentPart.toString());
	}

	private static void getp(String id) throws AccessException, RemoteException, NotBoundException {
		PartRepository stub = (PartRepository) _registry.lookup(_serverName);
		ConcretePart response = stub.getP(id);
		if (response != null) {
			_currentPart = response;
			System.out.println("The current part is " + _currentPart.getCode());
		} else {
			System.out.println("No part found with the specified code.");
		}
	}

	private static void listp() throws AccessException, RemoteException, NotBoundException {
		PartRepository stub = (PartRepository) _registry.lookup(_serverName);
		String response = stub.listP();
		System.out.println(response);
	}

	private static void bind(String serverName) throws RemoteException, NotBoundException {
		_serverName = serverName;

		System.out.println("Binding to " + _serverName);
		PartRepository stub = (PartRepository) _registry.lookup(_serverName);
		String response = stub.sayHello();
		System.out.println(response);
	}

}
