echo off
set arg1=%1
set arg2=%2
set outputPath=%3
cd /D %outputPath%
echo %* > sample.txt