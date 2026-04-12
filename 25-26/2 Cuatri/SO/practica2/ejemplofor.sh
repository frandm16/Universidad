#!/usr/bin/bash

for i in *
do
    if [ -d $i ]
    then
        echo $i es un directorio
    else
        echo $i es un fichero
    fi
done