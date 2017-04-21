package br.usp.each.si.dsid.ep1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Server implements IPartRepository {

	private static String _serverName;
	private static ConcurrentHashMap<Part, Integer> _parts;

	public static void main(String args[]) {

		_serverName = args[0];
		_parts = new ConcurrentHashMap<Part, Integer>();

		try {
			Server obj = new Server();
			IPartRepository stub = (IPartRepository) UnicastRemoteObject.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			// Use server name args
			registry.bind(args[0], stub);

			System.out.println("Server " + _serverName + " ready");

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public String sayHello() {
		return "Connected to " + _serverName;
	}

	@Override
	public String getInfo() throws RemoteException {
		return "Server: " + _serverName + "\n" + "Number of Parts: " + _parts.size();
	}

	@Override
	public String listP() throws RemoteException {
		String result = "CODE\t | QUANTITY \n";
		for (Entry<Part, Integer> entry : _parts.entrySet()) {
			result += entry.getKey().getCode() + "\t | " + entry.getValue() + "\n";
		}
		return result;
	}

	@Override
	public Part getP(String code) throws RemoteException {
		for (Entry<Part, Integer> entry : _parts.entrySet()) {
			if(entry.getKey().getCode().equals(code)){
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public Part addP(String name, String description, ConcurrentHashMap<IPart, Integer> subParts) throws RemoteException {
		Part part = new Part();
		part.setCode(_serverName, _parts.size());
		part.setName(name);
		part.setDescription(description);
		part.setSubParts(subParts);
		
		_parts.put(part, 1);
		return part;
	}

}
