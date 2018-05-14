package com.tuenti.mvp.model.impl;

import com.tuenti.mvp.MVPMain;
import com.tuenti.mvp.model.Sport;

/**
 * Common player match statistics functionality.
 *
 * @author Pavel Savinov
 */
public abstract class AbstractPlayerMatchStats {

	private boolean _teamWon;
	private String _playerName;
	private String _playerNick;
	private int _playerNumber;
	private String _teamName;

	public boolean isTeamWon() {
		return _teamWon;
	}

	public void setTeamWon(boolean teamWon) {
		_teamWon = teamWon;
	}

	public String getPlayerName() {
		return _playerName;
	}

	public void setPlayerName(String playerName) {
		_playerName = playerName;
	}

	public String getPlayerNick() {
		return _playerNick;
	}

	public void setPlayerNick(String playerNick) {
		_playerNick = playerNick;
	}

	public int getPlayerNumber() {
		return _playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		_playerNumber = playerNumber;
	}

	public String getTeamName() {
		return _teamName;
	}

	public void setTeamName(String teamName) {
		_teamName = teamName;
	}
}
