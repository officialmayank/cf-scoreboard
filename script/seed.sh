#!/usr/bin/env sh

curl -X POST   http://localhost:8080/teams   -F name=A
curl -X POST   http://localhost:8080/teams   -F name=B
curl -X POST   http://localhost:8080/teams   -F name=C

curl -X POST http://localhost:8080/members -F name=M1 -F teamId=1
curl -X POST http://localhost:8080/members -F name=M2 -F teamId=1
curl -X POST http://localhost:8080/members -F name=M3 -F teamId=2
curl -X POST http://localhost:8080/members -F name=M4 -F teamId=2
curl -X POST http://localhost:8080/members -F name=M5 -F teamId=3
curl -X POST http://localhost:8080/members -F name=M6 -F teamId=3

curl -X POST http://localhost:8080/scores -F value=1 -F memberId=1
curl -X POST http://localhost:8080/scores -F value=2 -F memberId=2
curl -X POST http://localhost:8080/scores -F value=3 -F memberId=3
curl -X POST http://localhost:8080/scores -F value=4 -F memberId=4
curl -X POST http://localhost:8080/scores -F value=5 -F memberId=5
curl -X POST http://localhost:8080/scores -F value=4 -F memberId=1
curl -X POST http://localhost:8080/scores -F value=3 -F memberId=2
curl -X POST http://localhost:8080/scores -F value=2 -F memberId=3
curl -X POST http://localhost:8080/scores -F value=1 -F memberId=4

curl http://localhost:8080/teams
echo "";
