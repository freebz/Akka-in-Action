# 코드 14-11 노드 실행하기

java -DPORT=2551 \
     -Dconfig.resource=/seed.conf \
     -jar target/words-node.jar
java -DPORT=2554 \
     -Dconfig.resource=/master.conf \
     -jar target/words-node.jar
java -DPORT=2555 \
     -Dconfig.resource=/worker.conf \
     -jar target/words-node.jar
java -DPORT=2556 \
     -Dconfig.resource=/worker.conf \
     -jar target/words-node.jar
