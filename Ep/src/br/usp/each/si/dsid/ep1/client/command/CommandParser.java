package br.usp.each.si.dsid.ep1.client.command;

import br.usp.each.si.dsid.ep1.client.Client;

public class CommandParser {
	private Client client;
	public CommandParser(Client client){
		this.client = client;
	}
	
	public BaseCommand ParseLine(String line){
		String[] parsedLine = line.split("\\s+");
		if(parsedLine.length == 0 || parsedLine[0].equals(""))
			return new EmptyCommand(client);
		
		if(parsedLine[0].equals("bind")){
			return new BindCommand(parsedLine[1], client, parsedLine);
		}else if(parsedLine[0].equals("listp")){
			return new ListpCommand(client, parsedLine);
		}else if(parsedLine[0].equals("getp")){
			return new GetpCommand(client, parsedLine);
		}else if(parsedLine[0].equals("showp")){
			return new ShowpCommand(client, parsedLine);
		}else if(parsedLine[0].equals("clearlist")){
			return new ClearListCommand(client, parsedLine);
		}else if(parsedLine[0].equals("addsubpart")){
			return new AddSubPartCommand(client, parsedLine);
		}else if(parsedLine[0].equals("addp")){
			return new AddpCommand(client, parsedLine);
		}else if(parsedLine[0].equals("info")){
			return new InfoCommand(client, parsedLine);
		}else if(parsedLine[0].equals("showlist")){
			return new ShowListCommand(client, parsedLine);
		}else if(parsedLine[0].equals("quit")){
			return new QuitCommand(client);
		}

		return new NullCommand(client, parsedLine[0]);
	}
}
