# 项目所需环境

1. 设备

   ​	一台windows10个人电脑，VMware虚拟机，三台centos7虚拟机

2. 节点

   ​	三个节点

   ​	两个从节点 hadoopslave1, hadoopslave2 

   ​	一个主节点 master

3. 文件传输软件

   ​	SSH Secure File Transfer

4. 开发平台

   ​	Hadoop

5. 其他组件

   ​	Eclipse、Hadoop、Mysql、Hdfs、Tomcat等



# 项目整体分为以下步骤：

## 1.爬取数据

​	这里直接使用现有的爬虫框架WebCollector，WebCollector 是一个无须配置、便于二次开发的 Java 爬虫框架（内核），它提供精简的的 API，只需少量代码即可实现一个功能强大的爬虫。

​	WebCollector-Hadoop 是 WebCollector 的 Hadoop 版本，支持分布式爬取。网址：https://github.com/CrawlScript/WebCollector



## 2.清洗数据

​	通过爬取获得的JSON格式的文件中包含很多不需要的信息，因此我们需要对其中有用的信息进行提取，通过分析我们知道，这里只需要进行map操作，可以省去reduce任务，具体实现策略是：

1. 每个JSON文件，包含JSON文本数据

	2. 通过map任务获取文件数据
	2. 通过fastjson类对JSON文件进行解析，获取需要的字段
	2. 最终将结果以字符串格式输出到文件中

## 3.分词

​	通过数据的清洗，我们可以获得结构比较清洗的文档，接下来需要对文本进行中文分词。这里采用IKAnalyzer分词架构。

## 4.词频统计

​	通过对文本进行分词，我们获得了大量的中文词，接下来统计词频，为生成词云做准备。

 1. 首先在HDFS上创建文件夹

    1.1  /user/hfut/input 存放待分词的文件

    1.2 /user/hfut/output 存放输出结果

 2. 编写mapreduce分词程序

 3. 修改程序运行时的参数，同1中配置，确保程序运行时可以正确的获取输入和输出

注：这个部分对mapreduce处理不同大小的文件进行了实验探索，详情见**README_EXP.md**

## 5.词云展示

1.  首先进行mysql数据库的配置
   1. 这里需要注意的是，因为处理的是中文可能会出现乱码，所以建议修改 **/etc/my.cnf** 中的**character-set-server=utf8**，同时增加以下内容**character-set-server=utf8**，**default-character-set=utf8**，**default-character-set=utf8**
   2. 重启数据库
2. 创建词云数据库，并创建相关的表，然后向数据库中导入分词结果
3.  部署词云服务器 启动tomacat
4. 打开firefox，网址：http://localhost:8080/ciyun/1/ksh1.jsp 即可展示分词结果













