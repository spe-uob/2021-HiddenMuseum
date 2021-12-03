# How to use `git` with this project

**Important:** Do **NOT** make a new Spring Boot project each time you want to work on this. It is **a lot of effort** to integrate the demos after the fact, and it also causes a large amount of **duplicated code**. It is **faster** and **easier** to follow this guide.

### **Step 0 -** `git clone` the repository

*You only need to do this the first time.*

```bash
$ git clone https://github.com/spe-uob/2021-HiddenMuseum.git
$ cd 2021-HiddenMuseum
```

### **Step 1 -** Move to the [`dev`](https://github.com/spe-uob/2021-HiddenMuseum/tree/dev) branch

*You only need to do this the first time.*

```bash
$ git checkout -b dev origin/dev
```

### **Step 2 -** Pull the latest changes

```bash
$ git pull --ff-only
```
Don't omit the `--ff-only` flag, as it may cause a messy git history.

### **Step 3 -** Branch off [`dev`](https://github.com/spe-uob/2021-HiddenMuseum/tree/dev)

```bash
$ git checkout -b <your-branch-name-here> dev
```

### **Step 4 -** Make a change, test it, then commit it

Follow the principle of **atomic commits**. Make each commit into a *single logical unit*. The changes made with each commit should be as minimal as possible in order to get the change working.

```bash
# -- after making a change --
$ git add .
$ git commit -m "describe change"
$ git push --set-upstream origin/<your-branch-name-here>
```

*You only need the `--set-upstream origin/<your-branch-name-here>` flag on your first commit.*

### **Step 5 -** Open a [pull request](https://github.com/spe-uob/2021-HiddenMuseum/compare) to incorporate changes

Make sure to set the base to `dev`, and compare to the branch you created.

Wait for someone else to review your code and confirm your pull request, as there may be merge conflicts or other issues that need to be fixed before it is included.