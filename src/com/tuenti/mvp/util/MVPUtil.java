package com.tuenti.mvp.util;

import com.tuenti.mvp.model.PlayerMatchStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVP utility methods.
 *
 * @author Pavel Savinov
 */
public class MVPUtil {

	/**
	 * Get MVP nickname based on the players statistics.
	 *
	 * @param playerMatchStatsList Players statistics list.
	 * @return MVP nickname
	 * @throws Exception
	 */
	public static String getMVPNick(
		List<PlayerMatchStats> playerMatchStatsList) throws Exception {

		Map<String, Integer> playerRatings = new HashMap<>();

		// calculate ratings for each player(assuming that nicknames are unique)
		for (PlayerMatchStats playerMatchStats : playerMatchStatsList) {
			int rating = playerRatings.getOrDefault(
				playerMatchStats.getPlayerNick(), 0);

			rating += playerMatchStats.getRating();

			playerRatings.put(playerMatchStats.getPlayerNick(), rating);
		}

		// get MVP entry this the highest rating
		Map.Entry<String, Integer> mvpEntry = playerRatings.entrySet().stream()
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.limit(1).findFirst().get();

		return mvpEntry.getKey();
	}

}
