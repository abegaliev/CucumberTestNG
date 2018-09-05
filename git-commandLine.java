=========================================================================================
==============================Git commands on Command Line===============================
=========================================================================================

To Clone Remote Repository to Current Local Repository: git clone URL/projectname.git

u2FD2Up9N7svSvpPM474

See all branches:			 			git branch -a

See differences/changes on the code: 	git diff

See files in working dir,
that are not commited yet: 				git status

Add files to Staging area:		 		git add -A  (adds all files)
							  			git add fileName (adds one file)

Remove files from Staging area:		 	git reset HEAD -- <file>

Commit: 								git commit -m "message"

Pull(Get new codes from Remote):		git pull origin master

Push codes to Remote Repository: 		git push origin branchName

Create a branch: 						git branch branchName

See all branches: 						git branch

Switch to branch: 						git checkout branchName

Push always to one branch:				git push -u origin branchName		//so, next time you just do: git push
Pull always from one branch:			git pull -u origin branchName       //so, next time you just do: git pull

Push Existing local branch to remote
branch(new) with different name: 		git push origin existingLocalBranch:remoteBranch(new)

Delete a local branch: 					git branch -d branchName

Delete a remote branch: 				git push origin --delete branchName


Rename your local branch:

		1. If you are on the branch you want to rename:     git branch -m newname

		2. If you are on a different branch: 				git branch -m old-name new-me

Make a pull request from one branch to Master branch:

										1. git checkout Master 			==switching to master branch
										2. git pull origin Master       ==pulling latest code
										3. git branch --merged			==see merged branches
										4. git merger branchName		==to merge branch
										5. git push origin Master       ==To push to master


To Ignore some files while commiting: 

							1. Crete file: .gitignore  (type when creating file: .gitignore.)

							2. add extensions of files that you want to ignore to .gitignore file 
													
										1   FileName 		= to ignore specific file

										2   .txt			= to ignore every file that contains .txt extension

										3	video.*			= to ignore all files containing "video" in thire title

										4	video*			= will ignore all files starting with "video"

										5	folderName/*    =*/will ignore all files that are inside this folder

										6   !				= not ignore



