import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class AverageReducer extends Reducer<IntWritable,Text,LongWritable,IntWritable> {
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