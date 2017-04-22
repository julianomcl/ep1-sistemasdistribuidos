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
		
		String partId = parsedLine[1];
		try {
			IPartRepository stub = (IPartRepository) client.getRegistry().lookup(client.getServerName());
			IPart response = stub.getP(partId);
			if (response != null) {
				client.setCurrentPart(response);
				System.out.println("The current part is " + client.getCurrentPart().getCode());
			} else {
				System.out.println("No part found with the specified code.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
