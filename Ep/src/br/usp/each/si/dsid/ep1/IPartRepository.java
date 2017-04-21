package br.usp.each.si.dsid.ep1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface IPartRepository extends Remote {
	/**
	 * @return a message informing to which 
	 * server the user is connected
	 * */
	String sayHello() throws RemoteException;
	/**
	 * @return a String as follows
	 * Servername has n parts.
	 * */
	String getInfo() throws RemoteException;
	/**
	 * @return a String listing all 
	 * parts in the server
	 * */
	String listP() throws RemoteException;
	/**
	 * @return the part associated with 
	 * the specified code
	 * @param code part code
	 * */
	IPart getP(String code) throws RemoteException;
	/**
	 * Add the specified part to the repository
	 * @return the code of the created part
	 * */
	String addP(String name, String description, ConcurrentHashMap<IPart, Integer> subParts) throws RemoteException;
	
	ITeste getTeste() throws RemoteException;
	
	/*Part findPart(String cod) throws RemoteException;
	ArrayList<Part> getParts() throws RemoteException; */
}
