package my.webcollector;

import java.io.*;
import okhttp3.Request;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;

public class JDCommentCrawler extends BreadthCrawler {
	public JDCommentCrawler(String crawlPath) {
		// 第二个参数表示不需要自动探测URL
		super(crawlPath, false);

		// 设置线程数为1
		setThreads(1);

		// 添加种子（评论API对应的URL，这里翻页10次）
		for (int pageIndex = 0; pageIndex < 100; pageIndex++) {
//			String seedUrl = String
//					.format("https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98&productId=1293744&score=0&sortType=5&page=%d&pageSize=10&isShadowSku=0&fold=1",
//							pageIndex);
//			// 在添加种子的同时，记录对应的页号
			String seedUrl = String
					.format("https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98&productId=6136136&score=0&sortType=5&page=%d&pageSize=10&isShadowSku=0&fold=1",
							pageIndex);
			// 在添加种子的同时，记录对应的页号
			addSeedAndReturn(seedUrl).meta("pageIndex", pageIndex);
		}
	}

	@Override
	public void visit(Page page, CrawlDatums crawlDatums) {
		// 模拟人访问网页的速度
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 获取之前保存的页号信息
		int pageIndex = page.metaAsInt("pageIndex");

		String body = page.html();
		// 保存当前访问的productPageComments页面信息
//		JDCommentCrawler.createFile(body, "/home/hfut/tmp/1293744-page"
//				+ pageIndex + ".html");
		
		JDCommentCrawler.createFile(body, "/home/hfut/tmp1/6136136-page"
				+ pageIndex + ".html");
	}

	/**
	 * 将字符串保存到文件
	 */
	public static boolean createFile(String content, String filePath) {
		// 标记文件生成是否成功
		boolean flag = true;
		try {
			// 保证创建一个新文件
			File file = new File(filePath);
			if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
				file.getParentFile().mkdirs();
			}
			if (file.exists()) { // 如果已存在,删除旧文件
				file.delete();
			}
			file.createNewFile();
			// 将格式化后的字符串写入文件
			Writer write = new OutputStreamWriter(new FileOutputStream(file),
					"UTF-8");
			write.write(content);
			write.flush();
			write.close();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	* 模拟普通用户使用浏览器访问
	*/
	public static class MyRequester extends OkHttpRequester {
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0";
		// 每次发送请求前都会执行这个方法来构建请求
		@Override
		public Request.Builder createRequestBuilder(CrawlDatum crawlDatum) {
			// 这里使用的是OkHttp中的Request.Builder
			// 可以参考OkHttp的文档来修改请求头
			return super.createRequestBuilder(crawlDatum)
					.removeHeader("User-Agent") //移除默认的UserAgent
					.addHeader("Referer", "https://item.jd.com/")
					.addHeader("User-Agent", userAgent);
			
		}
	}

	public static void main(String[] args) throws Exception {
		// 实例化一个评论爬虫，并设置临时文件夹为crawl
		JDCommentCrawler crawler = new JDCommentCrawler("crawl");
		// 抓取1层
		crawler.start(1);
	}
}
