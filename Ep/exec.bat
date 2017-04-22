Dir /s /B  *.java > sources.txt

mkdir C:\ep1

javac -d "C:\ep1" @sources.txt

set classpath=%classpath%;C:\ep1\

start rmiregistry

start java -classpath "C:\ep1" -Djava.rmi.server.codebase=file:"C:\ep1"/ br.usp.each.si.dsid.ep1.server.Server A

start java -classpath "C:\ep1" -Djava.rmi.server.codebase=file:"C:\ep1"/ br.usp.each.si.dsid.ep1.server.Server B

java -classpath "C:\ep1" br.usp.each.si.dsid.ep1.client.Client
