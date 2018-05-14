package com.tuenti.mvp.model.impl;

import com.tuenti.mvp.model.Position;

/**
 * Available basketball player positions.
 *
 * @author Pavel Savinov
 */
public enum BasketballPosition implements Position<BasketballAction> {

	G("GUARD") {
		@Override
		public int getRating(BasketballAction action, int times) {
			switch (action) {
				case SCORE:
					return 2 * times;
				case ASSIST:
					return 1 * times;
				default:
					return 3 * times;
			}
		}
	},
	F("FORWARD") {
		@Override
		public int getRating(BasketballAction action, int times) {
			switch (action) {
				default:
					return 2 * times;
			}
		}
	},
	C("CENTER") {
		@Override
		public int getRating(BasketballAction action, int times) {
			switch (action) {
				case SCORE:
					return 2 * times;
				case ASSIST:
					return 3 * times;
				default:
					return 1 * times;
			}
		}
	};

	private String _brief;

	BasketballPosition(String brief) {
		_brief = brief;
	}
}
