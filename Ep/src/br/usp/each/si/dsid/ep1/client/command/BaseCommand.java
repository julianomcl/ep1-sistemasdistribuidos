package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public abstract class BaseCommand {
	/**
	 * Executes a command.
	 * */
	public abstract void execute();
	
	protected Client client;
	
	protected BaseCommand(Client client){
		this.client = client;
	}
}
