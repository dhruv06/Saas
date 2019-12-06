ls
exec  1> logfile.txt
exec  2> errors


gcc myfile.c


if [ $? -eq 0 ]	
then
     ./a.out -< stdin    
else
     echo "Compilation Failed"
fi 



#docker run -v ~/Documents/workspace-sts-3.9.10.RELEASE/demo/usercode:/home/code gcc /bin/bash -c "cd home/code;./shell.sh"


