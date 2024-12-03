package my.manager;

import java.util.List;

import my.dao.WordCloudDao;
import my.domain.WordCloud;

public class WordCloudManager {
	public List<WordCloud> getWordCloudList() {
		return WordCloudDao.getWordCloudList();
	}

}
