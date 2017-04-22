package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public class QuitCommand extends BaseCommand{

	protected QuitCommand(Client client) {
		super(client);
	}

	@Override
	public void execute() {
		client.setRunning(false);
	}
}
