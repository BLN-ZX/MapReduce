package my.webcollector;

import cn.edu.hfut.dmic.webcollector.util.FileUtils;
import cn.edu.hfut.dmic.webcollector.util.GsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JDCommentStr2JSON {

    public static void main(String[] args) throws Exception {
    	// 读取文件内容为字符串
//    	//String body = FileUtils.read("/home/hfut/tmp/1293744-page0.txt", "UTF-8");
//    	String body = FileUtils.read("/home/hfut/tmp1/6136136-page0.html", "UTF-8");
// 
//    	// 获取字符串中的JSON格式数据
//    	int startIndex = body.indexOf("{");
//    	int endIndex = body.lastIndexOf("}") + 1;
//    	String jsonStr = body.substring(startIndex, endIndex);
//        JsonObject jo = GsonUtils.parse(jsonStr).getAsJsonObject();
//        System.out.println(jo);
//
//        //
//        
//        // 提取JsonObject中的每条评论信息
//        JsonArray commentJa = jo.get("comments").getAsJsonArray();
//        for(int i=0; i < commentJa.size(); i++){
//            JsonObject commentJo = commentJa.get(i).getAsJsonObject();
//            String content = commentJo.get("content").getAsString();
//            System.out.println(String.format("6136136-page0 -> Comment -> %d: %s", i, content));
//        }
    	
    	toJson();
    }
    
    public static void toJson() throws Exception{
    	String path = "/home/hfut/tmp1/6136136-page";
    	for(int i = 0; i < 100; i++)
    	{
    		String body = FileUtils.read(path + i + ".html", "UTF-8");
    		 
        	// 获取字符串中的JSON格式数据
        	int startIndex = body.indexOf("{");
        	int endIndex = body.lastIndexOf("}") + 1;
        	String jsonStr = body.substring(startIndex, endIndex);
            JsonObject jo = GsonUtils.parse(jsonStr).getAsJsonObject();
            
            FileUtils.write("/home/hfut/tmp1ToJson/6136136-page" + i + ".json", jo.toString().getBytes());
            //System.out.println(jo);
    	}
    	return;
    }
}

