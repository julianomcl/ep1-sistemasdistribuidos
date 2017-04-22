package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;
import br.usp.each.si.dsid.ep1.common.IPartRepository;

public class AddpCommand extends BaseParsedCommand{

	protected AddpCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		if(client.getServerName() == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		String joined = "";
		for(String s : parsedLine){
			joined += s+" ";
		}
		joined = joined.trim().replace("addp -n ", "");
		String split[] = joined.split("-d ");
		String name = split[0].trim();
		String description = (split.length > 1) ? split[1].trim() : "";
		System.out.println(joined.trim().replace("addp -n ", ""));
		System.out.println(name);
		System.out.println(description);
		
		try{
			IPartRepository stub = (IPartRepository) client.getRegistry().lookup(client.getServerName());
			client.setCurrentPart(stub.addP(name, description,client.getCurrentSubPartList()));
			System.out.println("Part " + client.getCurrentPart().getCode() + " added. Current part is now clear again.");
		}catch(Exception e){
			
		}
	}
}
