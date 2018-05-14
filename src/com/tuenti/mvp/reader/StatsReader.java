package com.tuenti.mvp.reader;

import com.tuenti.mvp.MVPMain;
import com.tuenti.mvp.model.PlayerMatchStats;
import com.tuenti.mvp.model.Sport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Statistics reader util, reads match stats for the provided sport.
 *
 * @author Pavel Savinov
 */
public class StatsReader {

	/**
	 * Reads match statistics from the specified input stream.
	 *
	 * @param inputStream Input stream to read statistics from.
	 * @return List of players statistics
	 * @throws Exception in case of unsupported sport name, incorrect format,
	 * or i/o error.
	 */
	public static List<PlayerMatchStats> readMatchStats(InputStream inputStream)
		throws Exception {

		List<PlayerMatchStats> playerMatchStatsList = new ArrayList<>();

		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(inputStream));

		String sportName = bufferedReader.readLine();

		if (sportName.equals("") || !MVPMain.SPORTS.containsKey(sportName)) {
			throw new IllegalArgumentException(
				"Invalid sport name" + sportName);
		}

		// instantiate selected sport class
		Class<Sport> sportClass = MVPMain.SPORTS.get(sportName);

		Sport sport = sportClass.newInstance();

		Class<PlayerMatchStats> playerMatchStatsClass =
			sport.getPlayerMatchStatsClass();

		String inputString = null;

		List<String> nicknames = new ArrayList<>();

		// read players stats line by line, checking nicks uniqueness
		while ((inputString = bufferedReader.readLine()) != null) {
			PlayerMatchStats playerMatchStats =
				playerMatchStatsClass.getConstructor(
					String.class, Boolean.class).newInstance(
						inputString, false);

			if (nicknames.contains(playerMatchStats.getPlayerNick())) {
				throw new IllegalStateException(
					"Players nicks should be unique");
			}

			nicknames.add(playerMatchStats.getPlayerNick());

			playerMatchStatsList.add(playerMatchStats);
		}

		Map<String, Integer> teamPoints = new HashMap<>();

		// calculate teams scores to know which team won the match
		for (PlayerMatchStats playerMatchStats : playerMatchStatsList) {
			if (teamPoints.containsKey(playerMatchStats.getTeamName())) {
				continue;
			}

			teamPoints.put(
				playerMatchStats.getTeamName(),
				sport.calculateTeamScore(
					playerMatchStats.getTeamName(), playerMatchStatsList));
		}

		// get the winner team and assign corresponding player's property
		Map.Entry<String, Integer> teamWon = teamPoints.entrySet().stream()
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.limit(1).findFirst().get();

		for (PlayerMatchStats playerMatchStats : playerMatchStatsList) {
			if (teamWon.getValue().equals(playerMatchStats.getTeamName())) {
				playerMatchStats.setTeamWon(true);
			}
		}

		return playerMatchStatsList;
	}

}
