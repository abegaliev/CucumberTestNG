==================Git commands on Command Line==================
To Clone Remote Repository to Current Local Repository: git clone URL/projectname.git

To see all branches: git branch -a

To see differences/changes on the code: git diff

To see files in working dir, that are not pushed yet: git status

To add files to Staging area: git add -A  (adds all files)
							  git add fileName (adds one file)

To remove files from Staging area: git 

To commit: git commit -m "message"

To pull(Get new codes from Remote): git pull origin master

To push codes to Remote Repository: git push branchName

To create a branch: git branch branchName

To see all branches: git branch

To switch to branch: git checkout branchName

To push always to one branch: git push -u origin branchName			//so, next time you just do: git push
To pull always from one branch: git pull -u origin branchName       //so, next time you just do: git pull

To delete a local branch: git branch -d branchName

To delete a remote branch: git push origin --delete branchName

To make a pull request from one branch to Master branch:

										1. git checkout Master 			==switching to master branch
										2. git pull origin Master       ==pulling latest code
										3. git branch --merged			==see merged branches
										4. git merger branchName		==to merge branch
										5. git push origin Master       ==To push to master

To 
