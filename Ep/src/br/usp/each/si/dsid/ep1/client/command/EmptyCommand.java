package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public class EmptyCommand extends BaseCommand{
	
	public EmptyCommand(Client client) {
		super(client);
	}

	@Override
	public void execute() {
		System.out.println("Please type a command");
	}
}
