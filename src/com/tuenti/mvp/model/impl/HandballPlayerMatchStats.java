package com.tuenti.mvp.model.impl;

import com.tuenti.mvp.MVPMain;
import com.tuenti.mvp.model.PlayerMatchStats;
import com.tuenti.mvp.model.Sport;

import java.util.HashMap;
import java.util.Map;

/**
 * Match statistics implementation for the Handball.
 *
 * @author Pavel Savinov
 */
public class HandballPlayerMatchStats extends AbstractPlayerMatchStats
	implements PlayerMatchStats<HandballPosition, HandballAction> {

	private HandballPosition _position;
	private Map<HandballAction, Integer> _actions;

	public HandballPlayerMatchStats(String input, Boolean teamWon) {
		String[] statsArray = input.split(";");

		if (statsArray.length != 7) {
			throw new IllegalArgumentException(
				"Invalid player stats format: " + input);
		}

		setTeamWon(teamWon);
		setPlayerName(statsArray[0]);
		setPlayerNick(statsArray[1]);
		setPlayerNumber(Integer.parseInt(statsArray[2]));
		setTeamName(statsArray[3]);

		_position = HandballPosition.valueOf(statsArray[4]);

		_actions = new HashMap<HandballAction, Integer>();

		_actions.put(
			HandballAction.GOAL_MADE, Integer.parseInt(statsArray[5]));
		_actions.put(
			HandballAction.GOAL_RECEIVED, Integer.parseInt(statsArray[6]));
	}

	@Override
	public HandballPosition getPlayerPosition() {
		return _position;
	}

	@Override
	public Map<HandballAction, Integer> getPlayerActions() {
		return _actions;
	}

	public int getRating() throws Exception {
		Class<Sport> sportClass = MVPMain.SPORTS.get("HANDBALL");

		Sport sport = sportClass.newInstance();

		return sport.calculatePlayerRaiting(this);
	}


}
