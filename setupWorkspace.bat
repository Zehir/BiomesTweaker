@echo off

IF EXIST "%CD%\eclipse" GOTO ECLIPSEDIR
   echo extract eclipse.zip

	setlocal
	cd /d %~dp0
	Call :UnZipFile "%CD%\eclipse" "%CD%\gradle\eclipse.zip"
	exit /b

	:UnZipFile <ExtractTo> <newzipfile>
	set vbs="%temp%\_.vbs"
	if exist %vbs% del /f /q %vbs%
	>%vbs%  echo Set fso = CreateObject("Scripting.FileSystemObject")
	>>%vbs% echo If NOT fso.FolderExists(%1) Then
	>>%vbs% echo fso.CreateFolder(%1)
	>>%vbs% echo End If
	>>%vbs% echo set objShell = CreateObject("Shell.Application")
	>>%vbs% echo set FilesInZip=objShell.NameSpace(%2).items
	>>%vbs% echo objShell.NameSpace(%1).CopyHere(FilesInZip)
	>>%vbs% echo Set fso = Nothing
	>>%vbs% echo Set objShell = Nothing
	cscript //nologo %vbs%
	if exist %vbs% del /f /q %vbs%
:ECLIPSEDIR

@echo on
call gradlew.bat setupDecompWorkspace
call gradlew.bat eclipse
pause
