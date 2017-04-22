package br.usp.each.si.dsid.ep1.client.command;

import java.util.Map.Entry;

import br.usp.each.si.dsid.ep1.client.Client;
import br.usp.each.si.dsid.ep1.common.IPart;

public class ShowListCommand extends BaseParsedCommand{

	protected ShowListCommand(Client client, String[] parsedLine) {
		super(client, parsedLine);
	}

	@Override
	public void execute() {
		if (client.getCurrentSubPartList().isEmpty()) {
			System.out.println("The current subparts list is empty.");
		} else {
			String result = "\nSUBPARTS CODE | QUANTITY: \n";
			for (Entry<IPart, Integer> entry : client.getCurrentSubPartList().entrySet()) {
				try {
					result += "\t" + entry.getKey().getCode() + " | " + entry.getValue() + "\n";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(result);
		}
	}

}
