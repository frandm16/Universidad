#! /usr/bin/bash

FECHA=$(date +%y%m%d)

if [ ! -d "backup" ]
then
    mkdir backup
fi

if [ ! -d "backup/$FECHA" ]
then
    mkdir "backup/$FECHA"
fi

for i in "$@"
do
    if [ -f "$i" ]
    then
        
        cp "$i" "backup/$FECHA"
        echo "Copiado $i"
    fi
done
        