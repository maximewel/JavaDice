@echo off

rem -------------------------------------------
rem 			Rappel
rem -------------------------------------------

rem Separateur:
rem
rem		Windows		;
rem		Linux/Mac	: 
rem
rem Classpath:
rem
rem		Acronyme:
rem
rem 		-cp
rem	
rem		Configuration:
rem
rem 		.			=> 	met dans le cp tous       .class   du repertoire courant 
rem 		./*			=> 	met dans le cp tous les    jar     du repertoire courant
rem			./ext/* 	=>	met dans le cp tous les    jar	   du folder ext
rem 
rem	Memoire:
rem
rem 	-Xms:200m	memoire reservee par l'OS, que la jvm l'emploie ou pas!
rem 	-Xmx:500m	memoire max que l'OS peut allouer au processus
rem
rem 	-verbose:gc		=> utile pour optimisation, affiche l'activite du garbage collector  (Warning: ne pas livrer chez le client)
rem
rem	Assertion:
rem
rem		-ea  	enable assertion
rem		-da		disable assertion
rem
rem Propriete System:
rem	
rem		-Da=1 -Dc=3 -Db=3.14
rem

rem -------------------------------------------
rem 			Tools
rem -------------------------------------------

rem ---------------
rem deploy local
rem ---------------

set JRE_PATH=./jvm/bin
rem set JRE_PATH=C:\Soft\java64\jre\bin
set PATH=%JRE_PATH%;%PATH%

rem ---------------
rem debug
rem ---------------

rem java -version
echo.

rem -------------------------------------------
rem 			Run
rem -------------------------------------------

rem ---------------
rem version light
rem ---------------

rem java -cp .;./*;./ext/* ch.hearc.cours_01.kitbase.container.tableau.mono.quadratique.UseQuadratique
rem java -cp .;./*;./ext/* ch.hearc.cours_01.kitbase.container.tableau.mono.linear.UseLinear
rem java -cp .;./*;./ext/* ch.hearc.cours_01.kitbase.container.tableau.mono.linear.UseLinearArgs 6 2
java -cp .;./*;./ext/* -Da=4 -Db=5 ch.hearc.cours_01.kitbase.container.tableau.mono.linear.UseLinearSystemProperty

rem ---------------
rem version options
rem ---------------

rem java -cp .;./*;./ext/* -Xmx20m -Xms10m -verbose:gc -ea ch.hearc.cours_01.kitbase.hello.UseHello

rem -------------------------------------------
rem 			end
rem -------------------------------------------

echo.
pause