#!/bin/bash

files=$(ls *doi.txt); for file in $files; do  sed -i '/doi\|usenix\|ndss-symposium/!d' $file;done
