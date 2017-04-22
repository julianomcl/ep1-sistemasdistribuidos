package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;
import br.usp.each.si.dsid.ep1.common.IPartRepository;

public class ListpCommand extends BaseParsedCommand{

	protected ListpCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		if(client.getServerName() == null){
			System.out.println("Connect to a server using command bind");
			return;
		}
		IPartRepository stub;
		try {
			stub = (IPartRepository) client.getRegistry().lookup(client.getServerName());
			String response = stub.listP();
			System.out.println(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
