import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class MapReduce {
	public static class MapperClass extends MapReduceBase implements Mapper<LongWritable, Text, IntWritable, DoubleWritable> {
		//private final static IntWritable one = new IntWritable(1);
		private int customerKey;
		private double priceValue;
		public void map(LongWritable key, Text mapValue, OutputCollector<IntWritable, DoubleWritable> output, Reporter reporter) throws IOException {
			String oneLine = mapValue.toString();
			StringTokenizer iteration = new StringTokenizer(oneLine,"|");
			while (iteration.hasMoreTokens()) {
				iteration.nextToken();
				customerKey = Integer.parseInt(iteration.nextToken());
				iteration.nextToken();
				priceValue = Double.parseDouble(iteration.nextToken());
				iteration.nextToken();
				iteration.nextToken();
				iteration.nextToken();
				iteration.nextToken();
				iteration.nextToken();
				output.collect(new IntWritable(customerKey),new DoubleWritable(priceValue));
			}
		}
	}

	public static class ReducerClass extends MapReduceBase implements Reducer<IntWritable, DoubleWritable, IntWritable, DoubleWritable> {
		public void reduce(IntWritable customerKey, Iterator<DoubleWritable> priceValue, OutputCollector<IntWritable,DoubleWritable > output, Reporter reporter) throws IOException {
			double groupSum = 0.0;
			while (priceValue.hasNext()) {
				groupSum = groupSum + priceValue.next().get();
			}
			output.collect(customerKey, new DoubleWritable(groupSum));
		}
	}

	public static void main (String args[]) throws Exception {
		JobConf jobConfig = new JobConf(MapReduce.class);
		jobConfig.setJobName("MapReduce");
		//the keys are int (IntWritable)
		jobConfig.setOutputKeyClass(IntWritable.class);
		//the values are double (DoubleWritable)
		jobConfig.setOutputValueClass(DoubleWritable.class);
		jobConfig.setMapperClass(MapperClass.class);
		jobConfig.setCombinerClass(ReducerClass.class);
		jobConfig.setReducerClass(ReducerClass.class);
		jobConfig.setInputFormat(TextInputFormat.class);
	    jobConfig.setOutputFormat(TextOutputFormat.class);
		FileInputFormat.addInputPath(jobConfig, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobConfig, new Path(args[1]));
		JobClient.runJob(jobConfig);
	}
}