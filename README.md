# Hadoop MapReduce Calculating Average

## Into

This is a simple MapReduce code for calculating average of newline separated integers in input files


## Steps

1) Create EMR Cluster and ssh to master node  

2) install git
> `sduo yum install git-core`

3) Clone this repository
> `git clone https://github.com/dulith-muthu/hadoop-findaverage.git`

4) run `mvn clean install` or create jar as follows
> `export HADOOP_CLASSPATH=$(/usr/bin/hadoop classpath)`   
> `mkdir target`  
> `cd target`  
> `javac -classpath ${HADOOP_CLASSPATH} ../src/main/java/com/dulith/bda/FindAverage.java`  
> `jar cf findaverage-1.0-SNAPSHOT.jar ../src/main/java/com/dulith/bda/*.class`  
> `cd ../`

5) you can create your own dataset using the `inputFileGen.py` script  
> `cd pyScripts`  
> `python inputFileGen.py`  

6) copy inputs files to HDFS
> `hdfs dfs -mkdir average`  
> `hdfs dfs -put input average`  
> `cd ../`  

7) Run MapReduce command.
> `hadoop jar target/findaverage-1.0-SNAPSHOT.jar /user/hadoop/average/input /user/hadoop/average/output`  

8) Get the (sum, count) output pairs from hdfs 
> `hdfs dfs -get average/output pyScripts`

9) get the average from the MapReduce output using the `outputAvgGen.py` script
> `cd pyScripts`  
> `python outputAvgGen.py`  


