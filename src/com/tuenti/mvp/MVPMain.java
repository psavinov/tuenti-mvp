package com.tuenti.mvp;

import com.tuenti.mvp.model.PlayerMatchStats;
import com.tuenti.mvp.model.impl.Basketball;
import com.tuenti.mvp.model.impl.Handball;
import com.tuenti.mvp.reader.StatsReader;
import com.tuenti.mvp.util.MVPUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVP application entry point.
 *
 * @author Pavel Savinov
 */
public class MVPMain {

	public static final Map<String, Class> SPORTS = new HashMap<>();

	// add more implementations to support more sports
	static {
		SPORTS.put("BASKETBALL", Basketball.class);
		SPORTS.put("HANDBALL", Handball.class);
	}

	public static void main(String[] args) throws Exception {

		// put your fileset path or user command line argument
		String filePath = "/Users/pavelsavinov/tuenti";

		File fileSet = new File(filePath);

		List<PlayerMatchStats> playerMatchStatsList = new ArrayList<>();

		// read all match files and add appropriate stats
		for (File file : fileSet.listFiles()) {
			playerMatchStatsList.addAll(
				StatsReader.readMatchStats(new FileInputStream(file)));
		}

		// get our MVP nickname
		System.out.println(
			"MVP Nickname: " + MVPUtil.getMVPNick(playerMatchStatsList));
	}

}
