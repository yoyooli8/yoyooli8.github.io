#!/bin/sh

branch_name=$2
git_remote_name="morigin"

case $1 in
pull)
	echo "git pull $git_remote_name $branch_name:$branch_name"
	git pull $git_remote_name $branch_name:$branch_name

;;
push)
	echo "git push $git_remote_name $branch_name:$branch_name"
	git push $git_remote_name $branch_name:$branch_name
;;
fetch)
	echo "git fetch $git_remote_name $branch_name"
	git fetch $git_remote_name $branch_name
	echo "git checkout -b $branch_name $git_remote_name/$branch_name"
	git checkout -b $branch_name $git_remote_name/$branch_name
;;
*)
	echo "Usage $0 {pull branchName|push branchName|fetch branchName}" >&2
esac	
