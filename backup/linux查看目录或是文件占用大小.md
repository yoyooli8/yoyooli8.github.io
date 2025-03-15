# linux查看目录或是文件占用大小
### 查看当前目录下一级子文件和子目录占用的磁盘容量
```shell
# du -lh --max-depth=1
13M    ./.cache
0    ./.config
1.2M    ./mongo-hacker
0    ./.pki
14M    .

```
### 统计当前文件夹(目录)大小，并按文件大小排序
```shell
# du -sh * | sort -n
1.2M    mongo-hacker
4.0K    anaconda-ks.cfg
4.0K    installmongofor3.6.log
4.0K    securityforcs7.log
4.0K    security.log
16K    securityforcs7.py
24K    installmongofor3.6.py

```