@echo off
title novel-web

echo "%~dp0target"
cd "%~dp0"

call mvn clean install -Dmaven.test.skip=true

cd "%~dp0target"
choice /t 1 /d y /n >nul

java -jar novel-web-1.0.0-SNAPSHOT.jar

pause
