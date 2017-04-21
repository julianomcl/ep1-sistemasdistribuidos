package br.usp.each.si.dsid.ep1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Teste extends UnicastRemoteObject implements ITeste {

	protected Teste() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5875614312187009482L;
	
	private String nome;
	
	
	@Override
	public String getNome() throws RemoteException {
		// TODO Auto-generated method stub
		return nome;
	}


	@Override
	public void setNome(String nome) throws RemoteException {
		this.nome = nome;
	}

}
