@echo off
echo ==============================================
echo Configurando repositorio Git para Farm APP
echo ==============================================

set /p GITHUB_URL="Ingresa la URL de tu repositorio de GitHub (ejemplo: https://github.com/usuario/FarmApp.git): "

if "%GITHUB_URL%"=="" (
    echo Error: Debes ingresar una URL valida.
    pause
    exit /b
)

git init
git add .
git commit -m "feat: configuracion inicial Farm APP (Auth, Login, Register, Home)"
git branch -M main
git remote add origin %GITHUB_URL%
git push -u origin main

echo.
echo Repositorio subido con exito a GitHub.
pause
