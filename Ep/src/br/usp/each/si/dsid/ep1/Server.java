package br.usp.each.si.dsid.ep1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Server implements PartRepository, Part {

	private static String _serverName;
	private static ConcurrentHashMap<ConcretePart, Integer> _parts;

	public static void main(String args[]) {

		_serverName = args[0];
		_parts = new ConcurrentHashMap<ConcretePart, Integer>();

		try {
			Server obj = new Server();
			PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(obj, 0);

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
	public void getCod() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCod() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getName() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSubPart(Part subPart, int qty) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Part> getSubParts() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo() throws RemoteException {
		return "Server: " + _serverName + "\n" + "Number of Parts: " + _parts.size();
	}

	@Override
	public String listP() throws RemoteException {
		String result = "CODE\t | QUANTITY \n";
		for (Entry<ConcretePart, Integer> entry : _parts.entrySet()) {
			result += entry.getKey().getCode() + "\t | " + entry.getValue() + "\n";
		}
		return result;
	}

	@Override
	public ConcretePart getP(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConcretePart addP(ConcretePart part) throws RemoteException {
		part.setCode(_serverName, _parts.size());
		_parts.put(part, 1);
		return part;
	}

}
