import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MRDataClean4JDCommets {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {
		Job job = Job.getInstance();
		job.setJobName("MRDataClean4JDCommets");
		job.setJarByClass(MRDataClean4JDCommets.class);

		job.setMapperClass(doMapper.class);
		// job.setReducerClass(doReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		Path in = new Path("hdfs://master:9000/MRDataClean/in");
		Path out = new Path("hdfs://master:9000/MRDataClean/out/1");
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	public static class doMapper extends Mapper<Object, Text, Text, Text> {  
	    @Override  
	    protected void map(Object key, Text value, Context context)  
	            throws IOException, InterruptedException {  
	        String initJsonString = value.toString();  
	        JSONObject initJson = JSONObject.parseObject(initJsonString );  
	        if (!initJsonString.contains("productCommentSummary") && !initJsonString.contains("comments")) {  
	            return;  
	        }  
	        JSONObject myjson = initJson.getJSONObject("ten");  
	        JSONObject productCommentSummary = myjson.getJSONObject("productCommentSummary");  
	        String productId       = productCommentSummary.get("productId").toString();  
	        String commentCount    = productCommentSummary.get("commentCount").toString();  
	        String goodCount       = productCommentSummary.get("goodCount").toString();  
	        String generalCount    = productCommentSummary.get("generalCount").toString();  
	        String poorCount       = productCommentSummary.get("poorCount").toString();  
	        String goodRateShow    = productCommentSummary.get("goodRateShow").toString();  
	        String generalRateShow = productCommentSummary.get("generalRateShow").toString();  
	        String poorRateShow    = productCommentSummary.get("poorRateShow").toString();  
	        /* comments 包括十条评论 */  
	        JSONArray comments = myjson.getJSONArray("comments");  
	        for (int i = 0; i < comments.size(); i++) {  
	            JSONObject comment    = comments.getJSONObject(i);  
	            String guid           = comment.getString("guid");  
	            String content        = comment.getString("content").replace('\n', ' ');  
	            String creationTime   = comment.getString("creationTime");  
	            String score          = comment.getString("score");  
	            String nickname       = comment.getString("nickname");  
	            String userLevelName  = comment.getString("userLevelName");  
	            String userClientShow = comment.getString("userClientShow");  
	            String isMobile       = comment.getString("isMobile");  
	            String days           = comment.getString("days");  
	            StringBuilder sb = new StringBuilder();  
	  
	            sb.append(productId);         sb.append("\t");  
	            sb.append(commentCount);      sb.append("\t");  
	            sb.append(goodCount);         sb.append("\t");  
	            sb.append(generalCount);      sb.append("\t");  
	            sb.append( poorCount );       sb.append("\t");  
	            sb.append( goodRateShow );    sb.append("\t");  
	            sb.append( generalRateShow ); sb.append("\t");  
	            sb.append( poorRateShow );    sb.append("\t");  
	            sb.append( guid );            sb.append("\t");  
	            sb.append( content );         sb.append("\t");  
	            sb.append( creationTime );    sb.append("\t");  
	            sb.append( score );           sb.append("\t");  
	            sb.append( nickname );        sb.append("\t");  
	            sb.append( userLevelName );   sb.append("\t");  
	            sb.append( userClientShow );  sb.append("\t");  
	            sb.append( isMobile );        sb.append("\t");  
	            sb.append( days );  
	            String result = sb.toString();  
	            context.write(new Text(result), new Text(""));  
	        }  
	    }  
	}  
}
