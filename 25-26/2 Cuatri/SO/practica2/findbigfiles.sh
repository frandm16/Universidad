#!/usr/bin/bash

find "$1" -type f -size +"${2}c" -exec stat --format='%n: %s bytes' {} \;