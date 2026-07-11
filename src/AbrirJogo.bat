@echo off
setlocal

pushd "%~dp0"

set "JAR_FILE=dist\ComputadorRealista.jar"

if not exist "%JAR_FILE%" (
  call "%~dp0CriarExecutavel.bat"
  if errorlevel 1 (
    popd
    exit /b 1
  )
)

where java >nul 2>nul
if not errorlevel 1 (
  java -jar "%JAR_FILE%"
  if errorlevel 1 (
    echo.
    echo O jogo fechou com erro. Veja a mensagem acima.
    pause
  )
  popd
  endlocal
  exit /b 0
)

echo Nao foi encontrado o Java. Instale o JDK ou JRE para abrir o jogo.
pause
popd
endlocal
