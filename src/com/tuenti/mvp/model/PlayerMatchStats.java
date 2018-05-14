package com.tuenti.mvp.model;

import java.util.Map;

/**
 * Basic API for a sport match statistics for the concrete player.
 *
 * @author Pavel Savinov
 */
public interface PlayerMatchStats<P extends Position, A extends Action> {

	/**
	 * Team won the match.
	 *
	 * @return True if team won the match.
	 */
	public boolean isTeamWon();

	public void setTeamWon(boolean teamWon);

	/**
	 * Player's name.
	 *
	 * @return Player's name.
	 */
	public String getPlayerName();

	/**
	 * Player's nickname.
	 *
	 * @return Player's nickname.
	 */
	public String getPlayerNick();

	/**
	 * Player's number.
	 *
	 * @return Player's number.
	 */
	public int getPlayerNumber();

	/**
	 * Player's team name.
	 *
	 * @return Player's team name.
	 */
	public String getTeamName();

	/**
	 * Player's position.
	 *
	 * @return Player's position.
	 */
	public P getPlayerPosition();

	/**
	 * Player's actions map.
	 *
	 * @return Player's actions map.
	 */
	public Map<A, Integer> getPlayerActions();

	/**
	 * Calculated player's rating.
	 *
	 * @return Calculated player's rating
	 */
	public int getRating() throws Exception;

}
