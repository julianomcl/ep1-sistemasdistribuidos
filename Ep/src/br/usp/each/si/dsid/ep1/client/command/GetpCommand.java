package br.usp.each.si.dsid.ep1.client.command;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.usp.each.si.dsid.ep1.client.Client;
import br.usp.each.si.dsid.ep1.common.IPart;
import br.usp.each.si.dsid.ep1.common.IPartRepository;

public class GetpCommand extends BaseParsedCommand{
	
	protected GetpCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		if(parsedLine.length == 1){
			System.out.println("Part code not defined.");
			return;
		}
		
		if(client.getServerName() == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		
		//c�digo da pe�a a ser buscada
		String partId = parsedLine[1];
		try {
			//� feita a conex�o ao repos�t�rio de pe�as
			IPartRepository stub = (IPartRepository) client.getRegistry().lookup(client.getServerName());
			//o cliente recebe ent�o a refer�ncia de um objeto Part,
			//identificada pela interface IPart
			IPart response = stub.getP(partId);
			if (response != null) {
				client.setCurrentPart(response);
				System.out.println("The current part is " + client.getCurrentPart().getCode());
			} else {
				System.out.println("No part found with the specified code.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}