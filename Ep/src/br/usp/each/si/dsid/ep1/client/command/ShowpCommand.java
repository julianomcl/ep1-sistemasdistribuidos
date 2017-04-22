package br.usp.each.si.dsid.ep1.client.command;

import java.rmi.RemoteException;

import br.usp.each.si.dsid.ep1.client.Client;

public class ShowpCommand extends BaseParsedCommand{

	protected ShowpCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		try {
			if(client.getCurrentPart() != null)
				System.out.println(client.getCurrentPart().toFormattedString());
			else
				System.out.println("Is necessary to add or get a part");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
