package com.tuenti.mvp.model.impl;

import com.tuenti.mvp.MVPMain;
import com.tuenti.mvp.model.PlayerMatchStats;
import com.tuenti.mvp.model.Sport;

import java.util.HashMap;
import java.util.Map;

/**
 * Match statistics implementation for the Basketball.
 *
 * @author Pavel Savinov
 */
public class BasketballPlayerMatchStats extends AbstractPlayerMatchStats
	implements PlayerMatchStats<BasketballPosition, BasketballAction> {

	private BasketballPosition _position;
	private Map<BasketballAction, Integer> _actions;

	public BasketballPlayerMatchStats(String input, Boolean teamWon) {
		String[] statsArray = input.split(";");

		if (statsArray.length != 8) {
			throw new IllegalArgumentException(
				"Invalid player stats format: " + input);
		}

		setTeamWon(teamWon);
		setPlayerName(statsArray[0]);
		setPlayerNick(statsArray[1]);
		setPlayerNumber(Integer.parseInt(statsArray[2]));
		setTeamName(statsArray[3]);

		_position = BasketballPosition.valueOf(statsArray[4]);

		_actions = new HashMap<>();

		_actions.put(BasketballAction.SCORE, Integer.parseInt(statsArray[5]));
		_actions.put(BasketballAction.REBOUND, Integer.parseInt(statsArray[6]));
		_actions.put(BasketballAction.ASSIST, Integer.parseInt(statsArray[7]));
	}

	@Override
	public BasketballPosition getPlayerPosition() {
		return _position;
	}

	@Override
	public Map<BasketballAction, Integer> getPlayerActions() {
		return _actions;
	}

	public int getRating() throws Exception {
		Class<Sport> sportClass = MVPMain.SPORTS.get("BASKETBALL");

		Sport sport = sportClass.newInstance();

		return sport.calculatePlayerRaiting(this);
	}


}
