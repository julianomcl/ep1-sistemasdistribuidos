package br.usp.each.si.dsid.ep1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements PartRepository, Part {

	private static String _serverName;

	public static void main(String args[]) {

		_serverName = args[0];

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
	public void addPart(Part part) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Part findPart(int cod) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Part> getParts() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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

}
