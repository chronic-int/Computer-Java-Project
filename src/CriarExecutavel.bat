@echo off
setlocal

pushd "%~dp0"

set "BUILD_DIR=build\classes"
set "DIST_DIR=dist"
set "JAR_FILE=%DIST_DIR%\ComputadorRealista.jar"

where javac >nul 2>nul
if errorlevel 1 (
  echo Nao foi encontrado o javac. Instale o JDK e tente novamente.
  pause
  exit /b 1
)

where jar >nul 2>nul
if errorlevel 1 (
  echo Nao foi encontrado o comando jar. Instale o JDK e tente novamente.
  pause
  exit /b 1
)

if exist "build" rmdir /s /q "build"
if not exist "%BUILD_DIR%" mkdir "%BUILD_DIR%"
if not exist "%DIST_DIR%" mkdir "%DIST_DIR%"

echo A compilar o projecto...
javac -encoding UTF-8 --release 8 -d "%BUILD_DIR%" Main.java
if errorlevel 1 (
  echo A compilacao falhou.
  pause
  exit /b 1
)

echo A criar executavel Java...
jar cfm "%JAR_FILE%" MANIFEST.MF -C "%BUILD_DIR%" .
if errorlevel 1 (
  echo Nao foi possivel criar o ficheiro JAR.
  pause
  exit /b 1
)

rmdir /s /q "build"

echo Executavel criado em %JAR_FILE%
popd
endlocal
