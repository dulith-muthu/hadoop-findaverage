package com.dulith.bda;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

public class FindAverage {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Please specify the input and output path");
            System.exit(-1);
        }

        /* delete the output directory before running the job */
        FileUtils.deleteDirectory(new File(args[1]));

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FindAverage.class);
        job.setJobName("Find_Average");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(AverageMapper.class);
        job.setReducerClass(AverageReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}