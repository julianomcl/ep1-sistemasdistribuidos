package br.usp.each.si.dsid.ep1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Part extends Remote {
	void getCod() throws RemoteException;
	void setCod() throws RemoteException;
	void getName() throws RemoteException;
	void setName() throws RemoteException;
	void addSubPart(Part subPart, int qty) throws RemoteException;
	ArrayList<Part> getSubParts() throws RemoteException;
}
