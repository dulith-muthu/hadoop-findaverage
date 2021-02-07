# Hadoop MapReduce Calculating Average

## Into

This is a simple MapReduce code for calculating average of newline separated integers in input files


## Steps

1) Create EMR Cluster and ssh to master node  

2) install git
> `sduo yum install git-core`

2) Clone this repository
> `git clone https://github.com/dulith-muthu/hadoop-findaverage.git`

3) run `mvn clean install` or create jar as follows
> `export HADOOP_CLASSPATH=$(/usr/bin/hadoop classpath)`   
> `mkdir target`  
> `cd target`  
> `javac -classpath ${HADOOP_CLASSPATH} ../CustomAverageTuple.java`  
> `jar cf findaverage-1.0-SNAPSHOT.jar *.class`  
> `cd ../`

4) you can create your own dataset using the `inputFileGen.py` script  
> `cd input`  
> `python inputFileGen.py`  
> `cd ../`

6) copy inputs files to HDFS
> `hdfs dfs -mkdir average`  
> `hdfs dfs -mkdir average/input`  
> `hdfs dfs -put input/*.text average/input`  

7) Run MapReduce command.
> `hadoop jar target/findaverage-1.0-SNAPSHOT.jar FindAverage /user/hadoop/average/input /user/hadoop/average/output`  

8) Get the department-wise salary average output
> `hdfs dfs -get output average/output`


