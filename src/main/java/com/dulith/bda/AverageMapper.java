package com.dulith.bda;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.util.Random;
import java.io.IOException;

public class AverageMapper extends Mapper<Object,Text,Text,IntWritable> {

    public void map(Object key, Text value, Context context) throws IOException,InterruptedException{
        Random rand = new Random(); //instance of random class
        int numberOfSections = 3;
        //generate random values from 0-3
        int sectionInt = rand.nextInt(numberOfSections);

        int intVal = Integer.parseInt(value.toString());

        context.write(new Text("" + sectionInt), new IntWritable(intVal));
    }
}