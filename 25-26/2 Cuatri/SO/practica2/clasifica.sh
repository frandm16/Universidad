#!/usr/bin/bash

if [ ! -d "$2" ]
then
    mkdir -p "$2"
fi

for mp3 in "$1"/*.mp3
do
    AUTOR=$(grep "autor:" "$mp3" | cut -d":" -f2)
    TITULO=$(grep "titulo:" "$mp3" | cut -d":" -f2)

    if [ ! -d "$2/$AUTOR" ]
    then
        mkdir -p "$2/$AUTOR"
    fi

    cp "$mp3" "$2/$AUTOR/$TITULO.mp3"
done