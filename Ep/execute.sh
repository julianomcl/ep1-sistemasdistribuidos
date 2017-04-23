#!/bin/bash
mkdir $HOME/ep1
javac -d $HOME/ep1 $(find -name "*.java")
jar cvf $HOME/ep1/part.jar $HOME/ep1/$(find -name "*.class")
rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false &
java -cp $HOME/ep1:$HOME/ep1/part.jar -Djava.rmi.server.codebase=file:$HOME/ep1/ -Djava.security.policy=server.policy br.usp.each.si.dsid.ep1.server.Server A &
java -cp $HOME/ep1:$HOME/ep1/part.jar -Djava.rmi.server.codebase=file:$HOME/ep1/ -Djava.security.policy=server.policy br.usp.each.si.dsid.ep1.server.Server B &
java -classpath $HOME/ep1 br.usp.each.si.dsid.ep1.client.Client
