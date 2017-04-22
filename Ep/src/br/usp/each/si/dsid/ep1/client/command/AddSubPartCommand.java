package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public class AddSubPartCommand extends BaseParsedCommand{

	
	protected AddSubPartCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		if(parsedLine.length == 1){
			System.out.println("Quantity not defined.");
			return;
		}
		
		if(client.getCurrentPart() == null){
			System.out.println("Is necessary to add or get a part");
			return;
		}
		
		int quantity = Integer.parseInt(parsedLine[1]);
		
		try {
			System.out.println("Adding " + quantity + " units of " + client.getCurrentPart().getCode() + " to subpart list.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.getCurrentSubPartList().put(client.getCurrentPart(), quantity);
	}

}
