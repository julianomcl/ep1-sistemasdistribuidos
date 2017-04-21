package br.usp.each.si.dsid.ep1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITeste extends Remote {

	public String getNome() throws RemoteException;
	
	public void setNome(String nome) throws RemoteException;
	
}
