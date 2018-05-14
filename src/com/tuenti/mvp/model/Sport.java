package com.tuenti.mvp.model;

import java.util.List;

/**
 * Basic sport API, to be implemented for the particular sports.
 *
 * @author Pavel Savinov
 */
public interface Sport<M extends PlayerMatchStats> {

	/**
	 * Calculates the player's rating based on player's match statistics.
	 *
	 * @param playerMatchStats Player's match statistics
	 *
	 * @return Player's match rating
	 */
	public int calculatePlayerRaiting(M playerMatchStats);

	/**
	 * Calculates the team's score based on player's match statistics.
	 *
	 * @param teamName Team's name
	 * @param playerMatchStatsList List of player's match statistics
	 *
	 * @return Player's match rating
	 */
	public int calculateTeamScore(
		String teamName, List<M> playerMatchStatsList);

	/**
	 * Player's match statistic class.
	 *
	 * @return Player's match statistic class.
	 */
	public Class getPlayerMatchStatsClass();

}
