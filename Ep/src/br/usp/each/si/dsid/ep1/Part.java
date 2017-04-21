package br.usp.each.si.dsid.ep1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Part extends UnicastRemoteObject implements IPart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213311117634450119L;
	private String _code;
	private String _name;
	private String _description;
	private ConcurrentHashMap<IPart, Integer> _subParts = new ConcurrentHashMap<IPart, Integer>();
	private String _serverName;
	
	protected Part() throws RemoteException {
		super();
	}
	

	@Override
	public String getCode() throws RemoteException {
		return _code;
	}
	@Override
	public void setCode(String serverName, int numberOfParts) throws RemoteException {
		this._serverName = serverName;
		this._code = serverName + (numberOfParts + 1);
	}
	@Override
	public String getName() throws RemoteException {
		return _name;
	}
	@Override
	public void setName(String name) throws RemoteException {
		this._name = name;
	}
	@Override
	public String getDescription() throws RemoteException {
		return _description;
	}
	@Override
	public void setDescription(String description) throws RemoteException {
		this._description = description;
	}
	@Override
	public boolean isPrimitive() throws RemoteException {
		return this._subParts.isEmpty();
	}
	@Override
	public ConcurrentHashMap<IPart, Integer> getSubParts() throws RemoteException {
		return _subParts;
	}
	@Override
	public void setSubParts(ConcurrentHashMap<IPart, Integer> _subParts) throws RemoteException {
		this._subParts = _subParts;
	}
	@Override
	public void addSubPart(Part part, Integer quantity) throws RemoteException {
		this._subParts.put(part, quantity);
	}
	@Override
	public String toFormattedString() throws RemoteException {
		String result = "CODE: " + _code;
		result += "\nSERVER: " + _serverName; 
		result += "\nNAME: " + _name;
		result += "\nDESCRIPTION: " + _description;
		result += "\nPRIMITIVE: ";
		if (!isPrimitive()) {
			result+= "NO";
			result += "\nSUBPARTS CODE | QUANTITY: \n";
			for (Entry<IPart, Integer> entry : _subParts.entrySet()) {
				result += "\t" + entry.getKey().getCode() + " | " + entry.getValue() + "\n";
			}
		}else{
			result+="YES\nNO SUBPARTS TO SHOW.\n";
		}
		return result;
	}



	

}
