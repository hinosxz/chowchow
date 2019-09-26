function create_all_branches()
{
    # Keep track of where Travis put us.
    # We are on a detached head, and we need to be able to go back to it.
    # shellcheck disable=SC2155
    local build_head=$(git rev-parse HEAD)

    # Fetch all the remote branches. Travis clones with `--depth`, which
    # implies `--single-branch`, so we need to overwrite remote.origin.fetch to
    # do that.
    git config --replace-all remote.origin.fetch +refs/heads/*:refs/remotes/origin/*
    git fetch
    # optionally, we can also fetch the tags
    git fetch --tags

    # create the tacking branches
    for branch in $(git branch -r|grep -v HEAD) ; do
        git checkout -qf "${branch#origin/}"
    done

    # finally, go back to where we were at the beginning
    git checkout "${build_head}"
}

function setup_git() {
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis CI"
}

setup_git
create_all_branches

git fetch
git checkout staging
git merge domitille/push_in_CI
git push "https://${GITHUB_ACCESS_TOKEN}@github.com/hinosxz/chowchow" staging


#curl -o /tmp/travis-automerge https://raw.githubusercontent.com/cdown/travis-automerge/master/travis-automerge
#chmod a+x /tmp/travis-automerge
#export BRANCHES_TO_MERGE_REGEX='^'
#export BRANCH_TO_MERGE_INTO=staging
#export GITHUB_REPO=hinosxz/chowchow
#/tmp/travis-automerge
