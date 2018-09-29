#! /bin/bash
cd ~/google-images-download/
googleimagesdownload --keywords $1 --limit 1
cd downloads/
mv $1/ ../../Desktop/Java/SocketProgramming
cd ~/Desktop/Java/SocketProgramming/ 
file_name="ls $1/"
file_name=$(eval $file_name)
mv "$1/${file_name}" $1.jpg
rmdir $1/
