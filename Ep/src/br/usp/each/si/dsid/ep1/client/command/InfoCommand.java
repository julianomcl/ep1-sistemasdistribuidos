package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;
import br.usp.each.si.dsid.ep1.common.IPartRepository;

public class InfoCommand extends BaseParsedCommand{

	protected InfoCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		if(client.getServerName() == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		
		try {
			IPartRepository stub = (IPartRepository) client.getRegistry().lookup(client.getServerName());
			String result = stub.getInfo();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
