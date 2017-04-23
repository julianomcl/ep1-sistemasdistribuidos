package br.usp.each.si.dsid.ep1.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import br.usp.each.si.dsid.ep1.common.IPart;
import br.usp.each.si.dsid.ep1.common.IPartRepository;

public class Server implements IPartRepository {

	private static String _serverName;
	private static ConcurrentHashMap<Part, Integer> _parts;

	public static void main(String args[]) {
		//O nome que identifica o servidor é recebido pela linha de comando
		_serverName = args[0];
		//é instanciada a lista de parts do servidor
		_parts = new ConcurrentHashMap<Part, Integer>();
		try {
			Server obj = new Server();
			//exportada a referencia do objeto servidor
			IPartRepository stub = (IPartRepository) UnicastRemoteObject.exportObject(obj, 0);
			//é feito o bind do objeto do servidor no registro do java
			Registry registry = LocateRegistry.getRegistry();
			//associado ao objeto o nome do servidor
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
