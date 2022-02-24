# 如何在这个项目中使用 git

注意：不要在每次要处理此project时都创建新的 Spring Boot Project。 事后集成demo费劲，也造成了大量的重复code。 请按照以下步骤操作 。

### 第零步：`git clone` git仓库

只需要执行一次此操作。 
```sh
$ git clone https://github.com/spe-uob/2021-HiddenMuseum.git
$ cd 2021-HiddenMuseum
```

### 第一步：移动到[`dev`](https://github.com/spe-uob/2021-HiddenMuseum/tree/dev)分支

```sh
$ git checkout -b dev origin/dev
```

### 第二步： pull 最新更改

```sh
$ git pull --ff-only
```
不要省略 `--ff-only` 标志，否则可能会导致混乱的 git 历史记录。 

### 第三步：分支 [`dev`](https://github.com/spe-uob/2021-HiddenMuseum/tree/dev)

```sh
$ git checkout -b <你的分支> dev
```

### 第四步：进行更改，测试，然后提交

遵循 the principle of atomic commits的原则。 使每个提交成为一个逻辑单元。 每次提交所做的更改应尽可能少，以使更改生效。 

```sh
# -- after making a change --
$ git add .
$ git commit -m "describe change"
$ git push --set-upstream origin/<你的分支>
```
你只需要在第一次提交时使用 `--set-upstream origin/<你的分支>` 标志。 

### 第 5 步：打开拉取请求以合并更改

确保将基础设置为 `dev`，并与你创建的分支进行比较。 

等待其他人查看你的代码并确认您的拉取请求，因为在包含之前可能存在合并冲突或其他需要修复的问题。

---

*Guide translated by [Jie](https://github.com/jieyeyang)*