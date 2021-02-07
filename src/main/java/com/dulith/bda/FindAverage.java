import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import java.util.Random;

public class FindAverage {

    public static class AverageMapper extends Mapper<LongWritable,Text,IntWritable,Text> {

        public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException{
            Random rand = new Random(); //instance of random class
            int numberOfSections = 3;
            //generate random values from 0-3
            int sectionInt = rand.nextInt(numberOfSections); 

            IntWritable section = new IntWritable(sectionInt);
            context.write(section, value);
        }
    }
    public static class AverageReducer extends Reducer<IntWritable,Text,LongWritable,IntWritable> {
        public void reduce(IntWritable key, Iterable<Text> values,Context context)
           throws IOException,InterruptedException {
                long sum = 0;
                int count = 0;

                for(Text x: values)
                {
                    int intVal = Integer.parseInt(x.toString());
                    sum += intVal;
                    count++;
                }
                context.write(new LongWritable(sum), new IntWritable(count));
           }
    }

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