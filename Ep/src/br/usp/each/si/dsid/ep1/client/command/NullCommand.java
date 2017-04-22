package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public class NullCommand extends BaseCommand{

	private String commandName;
	public NullCommand(Client client, String commandName) {
		super(client);
		this.commandName = commandName;
	}

	@Override
	public void execute() {
		System.out.println("Command '" + commandName + "' not found.");
	}

}
