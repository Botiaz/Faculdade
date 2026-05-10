@echo off
setlocal

rem Compile LaTeX report (requires pdflatex in PATH)
where pdflatex >nul 2>nul
if errorlevel 1 (
  echo Erro: pdflatex nao encontrado no PATH.
  echo Instale MiKTeX ou TeX Live e tente novamente.
  exit /b 1
)

pdflatex -interaction=nonstopmode -halt-on-error relatorio.tex
if errorlevel 1 exit /b 1

pdflatex -interaction=nonstopmode -halt-on-error relatorio.tex
endlocal
