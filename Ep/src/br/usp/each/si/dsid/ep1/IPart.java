package br.usp.each.si.dsid.ep1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentHashMap;

public interface IPart extends Remote {
	
	public String getCode() throws RemoteException;
	public void setCode(String serverName, int numberOfParts)  throws RemoteException;
	public String getName()  throws RemoteException;
	public void setName(String name)  throws RemoteException;
	public String getDescription()  throws RemoteException;
	public void setDescription(String description)  throws RemoteException;
	public boolean isPrimitive()  throws RemoteException;
	public ConcurrentHashMap<IPart, Integer> getSubParts()  throws RemoteException; 
	public void setSubParts(ConcurrentHashMap<IPart, Integer> _subParts)  throws RemoteException;
	public void addSubPart(Part part, Integer quantity)  throws RemoteException;
	public String toFormattedString() throws RemoteException;
	
}
