#!/bin/bash
echo "=============================================="
echo "Configurando repositorio Git para Farm APP"
echo "=============================================="

read -p "Ingresa la URL de tu repositorio de GitHub: " GITHUB_URL

if [ -z "$GITHUB_URL" ]; then
    echo "Error: Debes ingresar una URL valida."
    exit 1
fi

git init
git add .
git commit -m "feat: configuracion inicial Farm APP (Auth, Login, Register, Home)"
git branch -M main
git remote add origin "$GITHUB_URL"
git push -u origin main

echo ""
echo "Repositorio subido con exito a GitHub."
