#!/bin/bash

# Définir le nom du processus à tuer
process_name="Launcher"

# Récupérer le PID du processus à tuer
pid=$(jps -l | grep $process_name | awk '{print $1}')
echo "pid: $pid"

# Tuer le processus en utilisant le PID
if [ -z "$pid" ]; then
  echo "Le processus $process_name n'est pas en cours d'exécution"
else
  echo "Arrêt du processus $process_name (PID $pid)"
  kill $pid
fi

cd /Users/bricelassissi/Desktop/OCI/my-facture-api/src/

mvn spring-boot:run -Dspring.profiles.active=local -Dlogging.file=~/Desktop/OCI/script.log
