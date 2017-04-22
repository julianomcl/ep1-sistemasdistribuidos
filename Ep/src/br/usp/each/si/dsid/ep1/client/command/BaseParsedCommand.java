package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public abstract class BaseParsedCommand extends BaseCommand{
	protected String[] parsedLine;
	
	protected BaseParsedCommand(Client client, String[] parsedLine) {
		super(client);
		this.parsedLine = parsedLine;
	}
}
