package br.usp.each.si.dsid.ep1.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import br.usp.each.si.dsid.ep1.client.command.BaseCommand;
import br.usp.each.si.dsid.ep1.client.command.CommandParser;
import br.usp.each.si.dsid.ep1.common.IPart;

public class Client {

	private static String _host;
	private String serverName;
	private Registry registry;
	private ConcurrentHashMap<IPart, Integer> currentSubPartList;
	private IPart currentPart;
	private boolean isRunning;

	private Client() {
		isRunning = true;
	}

	public static void main(String[] args) {
		Client c = new Client();
		
		_host = (args.length < 1) ? null : args[0];
		c.setCurrentSubPartList(new ConcurrentHashMap<IPart, Integer>());
		
		System.out.println("Client started.");
		CommandParser parser = new CommandParser(c);
		try {
			c.setRegistry(LocateRegistry.getRegistry(_host));

			// create a scanner so the command line input can be read
			Scanner scanner = new Scanner(System.in);
			while (c.isRunning()) {
				// parse command line input and get a BaseCommand type object
				BaseCommand command = parser.ParseLine(scanner.nextLine());
				
				// execute the command
				command.execute();
				System.out.println();
			}

			scanner.close();

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public IPart getCurrentPart() {
		return currentPart;
	}

	public void setCurrentPart(IPart _currentPart) {
		this.currentPart = _currentPart;
	}

	public ConcurrentHashMap<IPart, Integer> getCurrentSubPartList() {
		return currentSubPartList;
	}

	public void setCurrentSubPartList(ConcurrentHashMap<IPart, Integer> currentSubPartList) {
		this.currentSubPartList = currentSubPartList;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
