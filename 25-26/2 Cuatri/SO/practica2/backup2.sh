#! /usr/bin/bash

FECHA=$(date +%y%m%d)

for i in "$@"
do
    if [ -f "$i" ]
    then
        NOMBRE="${FECHA}_${i}"
        
        cp "$i" "$NOMBRE"
        echo "Copiado como $NOMBRE"
    fi
done
        