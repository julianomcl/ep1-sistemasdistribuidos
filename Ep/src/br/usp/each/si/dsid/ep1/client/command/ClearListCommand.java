package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public class ClearListCommand extends BaseParsedCommand{

	protected ClearListCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		client.getCurrentSubPartList().clear();
		System.out.println("Current list is now empty.");
	}

}
