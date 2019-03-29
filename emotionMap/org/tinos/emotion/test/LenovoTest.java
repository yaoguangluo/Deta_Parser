package org.tinos.emotion.test;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.tinos.emotion.engine.LenovoInit;
import org.tinos.emotion.estimation.EmotionSample;
public class LenovoTest{
	public static void main(String[] argv) throws IOException {
		//init
		String text = "夏天的太阳像个大火球似的火辣辣地照射着大地，似乎要散发出"
				+ "全部的力量。它炙烤着大地，晒红了行人的脸膛，晒得大树不敢有丝毫"
				+ "摆动。如此热的天，我们更是盼望太阳能早点下山。盼啊盼，盼得太阳不"
				+ "好意思了，慢慢退出天空。夏天的傍晚，我可喜欢啦！女孩子穿着漂亮的"
				+ "泳装，神采飞扬。男孩子全身除了一条小裤衩，都裸露着，浑身光溜溜像"
				+ "条泥鳅。这时，小河成了我们的乐园。到了河边，就一头扎进去。有的打"
				+ "“狗刨”，有的“漂长江”，有的“蝶泳”，有的像泥鳅一样在河里穿梭来往"
				+ "。我最喜欢打水仗了，你泼我，我泼你。刹那时水花四溅，连抹把脸都顾不"
				+ "上。我们有时也会闹不愉快，被水淋得透不过气来，会哭，但一会儿便充满"
				+ "了笑声。河里溅起朵朵雪白的浪花，小伙伴的欢声笑语和击水的哗哗声交织"
				+ "在一起，就像一曲动听的田园交响曲。每当这时，我快乐极了，迷人的小河"
				+ "，带给我夏天最美的享受\r\n" + 
				"。";

		LenovoInit lenovoInit = new LenovoInit();
		lenovoInit.init(text);
		Map<String, EmotionSample> environmentSampleMap = lenovoInit.getEnvironmentInit().getEmotionSampleMap();
		Map<String, Object> lenovo = lenovoInit.getSensingMap().getLenovoMap();
		//reduce
		System.out.println("环    境：");
		Iterator<String> Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getDistinction()) {
				if(lenovo.containsKey(emotionSample.getDistinction())) {
					System.out.print(lenovo.get(emotionSample.getDistinction()).toString()+" ");
				}else {
					System.out.print(emotionSample.getDistinction()+" ");
				}
			}
		}
		System.out.println("");
		System.out.println("动机联想：");
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getMotivation()) {
				if(lenovo.containsKey(emotionSample.getMotivation())) {
					System.out.print(lenovo.get(emotionSample.getMotivation()).toString()+" ");
				}else {
					System.out.print(emotionSample.getMotivation()+" ");
				}
			}
		}
		System.out.println("");
		System.out.println("倾向探索：" );
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getTrending()) {
				if(lenovo.containsKey(emotionSample.getTrending())) {
					System.out.print(lenovo.get(emotionSample.getTrending()).toString()+" ");
				}else {
					System.out.print(emotionSample.getTrending()+" ");
				}
			}
		}

		//reduce
		System.out.println("");
		System.out.println("决策挖掘：");
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getPrediction()) {
				if(lenovo.containsKey(emotionSample.getPrediction())) {
					System.out.print(lenovo.get(emotionSample.getPrediction()).toString()+" ");
				}else {
					System.out.print(emotionSample.getPrediction()+" ");
				}
			}
		}
	}
}