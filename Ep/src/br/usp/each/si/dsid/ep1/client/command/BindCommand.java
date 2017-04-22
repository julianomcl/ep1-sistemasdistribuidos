package br.usp.each.si.dsid.ep1.client.command;

import java.rmi.NotBoundException;

import br.usp.each.si.dsid.ep1.client.Client;
import br.usp.each.si.dsid.ep1.common.IPartRepository;

class BindCommand extends BaseParsedCommand{
	private String serverName;
	public BindCommand(String serverName, Client client, String[] parsedLine){
		super(client, parsedLine);
		this.serverName = serverName;
	}
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public void execute() {
		if(parsedLine.length == 1){
			System.out.println("Server name not defined.");
			return;
		}
		String sName = "";
		for(int i = 1;i < parsedLine.length; i++){
			sName += parsedLine[i]+" ";
		}
		sName = sName.trim();
		client.setServerName(sName);
		
		System.out.println("Binding to " + client.getServerName());
		IPartRepository stub;
		try {
			//client.getRegistry() - carrega ao registro do java
			//lookup() - procura pelo nome do servidor informado no comando bind
			stub = (IPartRepository) client.getRegistry().lookup(client.getServerName());
			//sayHello() - método teste, apenas para indicar que a conexão foi estabelecida
			//retorna mensagem do servidor conectaso
			String response = stub.sayHello();
			System.out.println(response);
		} catch(NotBoundException e){
			System.err.println("Server doesn't exist");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
