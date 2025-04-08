#!/bin/bash
files=$(ls *doi.txt); for file in $files; do sed -i '/^https:\/\/doi\.org/!d' $file;done
