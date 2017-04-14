package br.usp.each.si.dsid.ep1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PartRepository extends Remote {
	String sayHello() throws RemoteException;
	void addPart(Part part) throws RemoteException;
	Part findPart(int cod) throws RemoteException;
	ArrayList<Part> getParts() throws RemoteException; 
}
