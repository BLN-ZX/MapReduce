package my.ikanalyzer;
import java.io.*;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerTest implements Serializable {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException {
		
		//读取文件内容
		File f=new File("/home/hfut/DataClean1/part-r-00000");
		BufferedReader br=null;
		br=new BufferedReader(new FileReader(f));
		String line;
		
		//分词结果写入
		File ff=new File("/home/hfut/DataClean1/fenci-result.txt");
		FileWriter fw=null;
		BufferedWriter bw=null;
		fw=new FileWriter(ff.getAbsoluteFile(),true);  //true表示可以追加新内容
		bw=new BufferedWriter(fw);
		
		IKAnalyzer analyzer = new IKAnalyzer(true);
		while((line=br.readLine())!=null){
		try {
			
			TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(line));
			tokenStream.addAttribute(CharTermAttribute.class);
			while (tokenStream.incrementToken()) {
				CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
				bw.write(charTermAttribute.toString() + " ");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		}
		br.close();
		bw.close();
		analyzer.close();
		
	}

    public  String splitWords(String line){
    	String result = "";
    	IKAnalyzer analyzer = new IKAnalyzer(true);
    	try {
			TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(line));
			tokenStream.addAttribute(CharTermAttribute.class);
			StringBuilder sb = new StringBuilder();
			while (tokenStream.incrementToken()) {
				CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
				sb.append(charTermAttribute.toString());
				sb.append("\t");
			}
			result = sb.toString();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    	analyzer.close();
    	return  result;
    }
}

